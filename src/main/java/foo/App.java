package foo;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	CamelContext camelContext = configureAndStartCamel();
        System.out.println( "Hello World!" );
    }
    private static CamelContext configureAndStartCamel() {
		// TODO Auto-generated method stub
    	ApplicationContext springContext = new ClassPathXmlApplicationContext("camel-config.xml");
    	CamelContext camelContext = (CamelContext) springContext.getBean("edigate");
		return camelContext;
	}

    
}
