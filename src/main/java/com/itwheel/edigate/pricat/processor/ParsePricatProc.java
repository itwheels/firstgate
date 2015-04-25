package com.itwheel.edigate.pricat.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.milyn.edi.unedifact.d96a.D96AInterchangeFactory;
import org.milyn.smooks.edi.unedifact.model.UNEdifactInterchange;

import com.itwheel.edigate.utils.SpringLocator;



public class ParsePricatProc implements Processor {
	
	public void process(Exchange exchange) throws Exception {
		
		D96AInterchangeFactory d96aFactory = D96AInterchangeFactory.getInstance();
		GenericFile<File> fileObj = (GenericFile<File>)exchange.getIn().getBody();
		InputStream pricatis = new FileInputStream(fileObj.getFile());
		UNEdifactInterchange interchange = d96aFactory.fromUNEdifact(pricatis);
		
		ParseEdi edi = new ParseEdi();
		EdiPricatHeadBean head = edi.parse(interchange);
		
		DataSource ds = (DataSource)SpringLocator.getBean("edi_ds");
		Connection conn = ds.getConnection();
		
		EdiPricatDao dao = new EdiPricatDao();
		dao.handler(conn, head);
		conn.close();
		
		exchange.getOut().setHeaders(exchange.getIn().getHeaders());
		exchange.getOut().setBody(interchange);
	}

}
