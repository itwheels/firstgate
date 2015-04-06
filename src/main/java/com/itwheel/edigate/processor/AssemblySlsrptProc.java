package com.itwheel.edigate.processor;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.time.DateFormatUtils;
import org.milyn.edi.unedifact.d96a.D96AInterchangeFactory;
import org.milyn.edisax.model.internal.Delimiters;
import org.milyn.edisax.unedifact.UNEdifactInterchangeParser;
import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;
import org.milyn.smooks.edi.unedifact.model.r41.types.DateTime;
import org.milyn.smooks.edi.unedifact.model.r41.types.Party;
import org.milyn.smooks.edi.unedifact.model.r41.types.SyntaxIdentifier;


public class AssemblySlsrptProc implements Processor {

	
	public void process(Exchange exchange) throws Exception {
		
		Object interchangeId = exchange.getIn().getHeader("interchange_id");
		
		D96AInterchangeFactory d96aFactory = D96AInterchangeFactory.getInstance();
		UNEdifactInterchange41 interchange = new UNEdifactInterchange41();
		Delimiters interchangeDelimiters = 
				(Delimiters)UNEdifactInterchangeParser.defaultUNEdifactDelimiters.clone();
		interchange.setInterchangeDelimiters(interchangeDelimiters);
		UNB41 interchangeHeader = new UNB41();
		Party p = new Party();
		p.setId("130012");
		p.setCodeQualifier("14");
		interchangeHeader.setSender(p);
		Party recipient = new Party();
		recipient.setId("5790000260676");
		recipient.setCodeQualifier("14");
		interchangeHeader.setRecipient(recipient);
		
		Date ts = (Date)exchange.getIn().getHeader("start_dte");
		String dateStr = DateFormatUtils.format(ts, "yyyyMMdd");
		String timeStr = DateFormatUtils.format(ts, "hhmm");
		DateTime dt = new DateTime();
		dt.setDate(dateStr);
		dt.setTime(timeStr);
		interchangeHeader.setDate(dt);
		SyntaxIdentifier si = new SyntaxIdentifier();
		si.setId("UNOC");
		si.setVersionNum("2");
		interchangeHeader.setSyntaxIdentifier(si);
		interchangeHeader.setControlRef(interchangeId.toString());
		interchange.setInterchangeHeader(interchangeHeader);
		
		
		List<UNEdifactMessage41> messages = 
				(List<UNEdifactMessage41>)exchange.getIn().getHeader("messages");
		int messageSegCont = 0;
		for(UNEdifactMessage41 msg : messages) {
			msg.setInterchangeHeader(interchangeHeader);
			messageSegCont += msg.getMessageTrailer().getSegmentCount();
		}
		interchange.setMessages(messages);
		
		UNZ41 interchangeTrailer = new UNZ41();
		interchangeTrailer.setControlRef(interchangeId.toString());
		interchangeTrailer.setControlCount(messageSegCont+3);
		interchange.setInterchangeTrailer(interchangeTrailer);
		
		StringWriter ediOutStream = new StringWriter();
        d96aFactory.toUNEdifact(interchange, ediOutStream);
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(ediOutStream.toString());
	}

}
