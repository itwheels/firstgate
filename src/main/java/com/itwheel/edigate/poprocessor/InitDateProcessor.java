package com.itwheel.edigate.poprocessor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.sql.DATE;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InitDateProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		List params = new ArrayList();
		  
		Calendar cal=Calendar.getInstance();
		
		DateFormat s1 = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat s2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		Date d1 = s1.parse(s1.format(cal.getTime()) + " 00:00:00");
		Date d2 = s1.parse(s1.format(cal.getTime()) + " 23:59:59");
		
		params.add(new java.sql.Date(d1.getTime()));
		params.add(new java.sql.Date(d2.getTime()));
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(params);
	}

}
