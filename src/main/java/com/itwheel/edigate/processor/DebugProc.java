package com.itwheel.edigate.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class DebugProc implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		exchange.getOut().setBody(exchange.getIn().getBody());
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
	}

}
