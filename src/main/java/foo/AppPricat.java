package foo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.milyn.edi.unedifact.d96a.D96AInterchangeFactory;
import org.milyn.smooks.edi.unedifact.model.UNEdifactInterchange;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itwheel.edigate.pricat.processor.EdiPricatDao;
import com.itwheel.edigate.pricat.processor.EdiPricatHeadBean;
import com.itwheel.edigate.pricat.processor.ParseEdi;
import com.itwheel.edigate.utils.SpringLocator;

/**
 * Hello world!
 *
 */
public class AppPricat 
{
    public static void main( String[] args ) throws Exception
    {
    	CamelContext camelContext = configureAndStartCamel();
        System.out.println( "Hello World!" );
    }
    private static CamelContext configureAndStartCamel() throws Exception {
		// TODO Auto-generated method stub
    	ApplicationContext springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    	CamelContext camelContext = (CamelContext) springContext.getBean("pricatctx");
    	
    	D96AInterchangeFactory d96aFactory = D96AInterchangeFactory.getInstance();
		//GenericFile<File> fileObj = (GenericFile<File>)exchange.getIn().getBody();
		InputStream pricatis = new FileInputStream("D:/ERP/edioutput/pricat/PRICAT_BURGEON_20150421-214250-041.edi");
		UNEdifactInterchange interchange = d96aFactory.fromUNEdifact(pricatis);
    	
		ParseEdi edi = new ParseEdi();
		EdiPricatHeadBean head = edi.parse(interchange);
		
		DataSource ds = (DataSource)springContext.getBean("edi_ds");
		Connection conn = ds.getConnection();
		
		EdiPricatDao dao = new EdiPricatDao();
		dao.handler(conn, head);
		
		conn.close();
		return camelContext;
	}

    
}
