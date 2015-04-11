package com.itwheel.edigate.invrpt.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class PrepSqlParamInitInvrptBatchProc implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		Date lastStartDte = 
				(Date)exchange.getIn().getHeader("invrpt_last_start_dte");
		Date startDte = 
				(Date)exchange.getIn().getHeader("invrpt_start_dte");
		Object batchId = exchange.getIn().getHeader("invrpt_batch_id");
		
		List params = new ArrayList();
		params.add(batchId);
		params.add(2); // type invrpt
		params.add(startDte);
		params.add(1);
		params.add(lastStartDte);
		params.add(startDte);
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(params);
	}

}
