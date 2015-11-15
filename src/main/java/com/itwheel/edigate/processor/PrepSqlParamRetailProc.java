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
		Integer pageIndex = (Integer)exchange.getProperty("CamelLoopIndex");
		Integer endIndex = (pageIndex+1)*5000;
		Integer startIndex = pageIndex*5000+1;
		List params = new ArrayList();
		params.add(lastStartDte);
		params.add(startDte);
		params.add(endIndex);
		params.add(startIndex);
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(params);
	}

}
