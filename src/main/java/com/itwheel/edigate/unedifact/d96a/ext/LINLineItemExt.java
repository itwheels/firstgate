package com.itwheel.edigate.unedifact.d96a.ext;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.milyn.edi.unedifact.d96a.common.LINLineItem;
import org.milyn.edisax.model.internal.DelimiterType;
import org.milyn.edisax.model.internal.Delimiters;
import org.milyn.edisax.util.EDIUtils;

public class LINLineItemExt extends LINLineItem {
public void write(Writer writer, Delimiters delimiters) throws IOException {
        
        Writer nodeWriter = new StringWriter();

        List<String> nodeTokens = new ArrayList<String>();

        if(this.getE1082LineItemNumber() != null) {
            nodeWriter.write(delimiters.escape(this.getE1082LineItemNumber().toString()));
            nodeTokens.add(nodeWriter.toString());
            ((StringWriter)nodeWriter).getBuffer().setLength(0);
        }
        nodeWriter.write(delimiters.getField());
        if(this.getE1229ActionRequestNotificationCoded() != null) {
            nodeWriter.write(delimiters.escape(this.getE1229ActionRequestNotificationCoded().toString()));
            nodeTokens.add(nodeWriter.toString());
            ((StringWriter)nodeWriter).getBuffer().setLength(0);
        }
        nodeWriter.write(delimiters.getField());
        if(this.getC212ItemNumberIdentification() != null) {
        	this.getC212ItemNumberIdentification().write(nodeWriter, delimiters);
            nodeTokens.add(nodeWriter.toString());
            ((StringWriter)nodeWriter).getBuffer().setLength(0);
        }
        nodeWriter.write(delimiters.getField());
        if(this.getC829SubLineInformation() != null) {
        	this.getC829SubLineInformation().write(nodeWriter, delimiters);
            nodeTokens.add(nodeWriter.toString());
            ((StringWriter)nodeWriter).getBuffer().setLength(0);
        }
        nodeWriter.write(delimiters.getField());
        if(this.getE1222ConfigurationLevel() != null) {
            nodeWriter.write(delimiters.escape(this.getE1222ConfigurationLevel().toString()));
            nodeTokens.add(nodeWriter.toString());
            ((StringWriter)nodeWriter).getBuffer().setLength(0);
        }
        nodeWriter.write(delimiters.getField());
        if(this.getE7083ConfigurationCoded() != null) {
            nodeWriter.write(delimiters.escape(this.getE7083ConfigurationCoded().toString()));
            nodeTokens.add(nodeWriter.toString());
            ((StringWriter)nodeWriter).getBuffer().setLength(0);
        }
        nodeTokens.add(nodeWriter.toString());
        writer.write(EDIUtils.concatAndTruncate(nodeTokens, DelimiterType.FIELD, delimiters));
        writer.write(delimiters.getSegmentDelimiter());
        writer.flush();
    }
	
}
