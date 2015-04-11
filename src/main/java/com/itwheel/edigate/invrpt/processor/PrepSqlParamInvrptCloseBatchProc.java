package com.itwheel.edigate.invrpt.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class PrepSqlParamInvrptCloseBatchProc implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		Object rowCont = exchange.getIn().getHeader("invrpt_retail_cont");
		Object batchId = exchange.getIn().getHeader("invrpt_batch_id");
		List params = new ArrayList();
		params.add(rowCont);
		params.add(batchId);
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(params);
	}

}
