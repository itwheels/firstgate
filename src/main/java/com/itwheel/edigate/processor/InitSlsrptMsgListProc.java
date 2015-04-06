package com.itwheel.edigate.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;


public class InitSlsrptMsgListProc implements Processor {

	
	public void process(Exchange exchange) throws Exception {
		
		List<UNEdifactMessage41> messages = new ArrayList<UNEdifactMessage41>();
		Map inHeaders = exchange.getIn().getHeaders();
		inHeaders.put("messages", messages);
		exchange.getOut().setHeaders(inHeaders);
		exchange.getOut().setBody(exchange.getIn().getBody());
	}

}
