package com.itwheel.edigate.poprocessor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InitDateProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		List params = new ArrayList();
		
		int y,m,d;    
		Calendar cal=Calendar.getInstance();
		y=cal.get(Calendar.YEAR);    
		m=cal.get(Calendar.MONTH);    
		d=cal.get(Calendar.DATE);
		
		params.add(y + "/" + m + "/" +d);
		params.add(y + "/" + m + "/" +d);
		System.out.println(params);
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(params);
	}

}
