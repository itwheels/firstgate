package com.itwheel.edigate.invrpt.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;

import com.itwheel.edigate.utils.Constant;


public class InitInvrptMsgListProc implements Processor {

	
	public void process(Exchange exchange) throws Exception {
		Constant.INVRPT_LIN = 0;
		List<UNEdifactMessage41> messages = new ArrayList<UNEdifactMessage41>();
		Map inHeaders = exchange.getIn().getHeaders();
		inHeaders.put("invrpt_messages", messages);
		exchange.getOut().setHeaders(inHeaders);
		exchange.getOut().setBody(exchange.getIn().getBody());
	}

}
