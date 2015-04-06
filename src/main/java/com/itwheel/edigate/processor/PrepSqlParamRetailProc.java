package com.itwheel.edigate.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class PrepSqlParamRetailProc implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		Date lastStartDte = 
				(Date)exchange.getIn().getHeader("last_start_dte");
		Date startDte = 
				(Date)exchange.getIn().getHeader("start_dte");
		List params = new ArrayList();
		params.add(lastStartDte);
		params.add(startDte);
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(params);
	}

}
