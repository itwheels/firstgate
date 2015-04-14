package com.itwheel.edigate.invrpt.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class PrepSqlParamInvrptRetailItmesProc implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		Date lastStartDte = 
				(Date)exchange.getIn().getHeader("invrpt_last_start_dte");
		Date startDte = 
				(Date)exchange.getIn().getHeader("invrpt_start_dte");
		
		Map<String, Object> retail = (Map<String, Object>)exchange.getIn().getHeader("invrpt_retail");
		
		List params = new ArrayList();
		params.add(lastStartDte);
		params.add(startDte);
		params.add((BigDecimal)retail.get("id"));
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(params);
	}

}
