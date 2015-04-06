package com.itwheel.edigate.processor;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.milyn.edi.unedifact.d96a.D96AInterchangeFactory;
import org.milyn.edisax.model.internal.Delimiters;
import org.milyn.edisax.unedifact.UNEdifactInterchangeParser;
import org.milyn.payload.JavaSource;
import org.milyn.payload.JavaSourceWithoutEventStream;
import org.milyn.smooks.edi.unedifact.model.UNEdifactInterchange;
import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNH41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;
import org.milyn.smooks.edi.unedifact.model.r41.types.DateTime;
import org.milyn.smooks.edi.unedifact.model.r41.types.MessageIdentifier;
import org.milyn.smooks.edi.unedifact.model.r41.types.Party;
import org.milyn.smooks.edi.unedifact.model.r41.types.SyntaxIdentifier;


public class InitSlsrptProc implements Processor {

	
	public void process(Exchange exchange) throws Exception {
		
		D96AInterchangeFactory d96aFactory = D96AInterchangeFactory.getInstance();
		UNEdifactInterchange41 interchange = new UNEdifactInterchange41();
		Delimiters interchangeDelimiters = UNEdifactInterchangeParser.defaultUNEdifactDelimiters;
		interchange.setInterchangeDelimiters(interchangeDelimiters);
		UNB41 interchangeHeader = new UNB41();
		Party p = new Party();
		p.setId("130012");
		p.setCodeQualifier("14");
		interchangeHeader.setSender(p);
		Party recipient = new Party();
		recipient.setId("12345");
		recipient.setCodeQualifier("14");
		interchangeHeader.setRecipient(recipient);
		DateTime dt = new DateTime();
		dt.setDate("20150321");
		dt.setTime("1400");
		interchangeHeader.setDate(dt);
		SyntaxIdentifier si = new SyntaxIdentifier();
		si.setId("UNOC");
		si.setVersionNum("2");
		interchangeHeader.setSyntaxIdentifier(si);
		interchangeHeader.setControlRef("EDI02336900");
		interchange.setInterchangeHeader(interchangeHeader);
		
		UNZ41 interchangeTrailer = new UNZ41();
		interchangeTrailer.setControlRef("EDI02336900");
		interchangeTrailer.setControlCount(23);
		interchange.setInterchangeTrailer(interchangeTrailer);
		
		List<UNEdifactMessage41> messages = new ArrayList<UNEdifactMessage41>();
		UNEdifactMessage41 msg = new UNEdifactMessage41();
		msg.setInterchangeHeader(interchangeHeader);
		UNH41 messageHeader = new UNH41();
		messageHeader.setMessageRefNum("");
		MessageIdentifier messageIdentifier = new MessageIdentifier();
		messageIdentifier.setId("");
		messageHeader.setMessageIdentifier(messageIdentifier);
		msg.setMessageHeader(messageHeader);
//		msg.setMessageTrailer(messageTrailer);
		messages.add(msg);
		interchange.setMessages(messages);
		StringWriter ediOutStream = new StringWriter();

        d96aFactory.toUNEdifact(interchange, ediOutStream);
        System.out.println(ediOutStream);
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
//		exchange.getOut().setBody(js);
	}

}
