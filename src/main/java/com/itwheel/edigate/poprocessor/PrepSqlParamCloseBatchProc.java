package com.itwheel.edigate.poprocessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PrepSqlParamCloseBatchProc implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		Object rowCont = 
				exchange.getIn().getHeader("po_count");
		Object batchId = 
				exchange.getIn().getHeader("batch_id");
		List params = new ArrayList();
		params.add(rowCont);
		params.add(batchId);
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(params);
	}
}