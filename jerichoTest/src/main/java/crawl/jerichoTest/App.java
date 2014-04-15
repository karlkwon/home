package crawl.jerichoTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Source source	= null;
		try {
			source = new Source(new URL("http://www.mustgo100.or.kr/tourist.kto?m=touristListAll"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        source.fullSequentialParse();
        
        List<Element> liList = source.getAllElements(HTMLElementName.LI);
        
        for(Element e0: liList) {
        	System.out.println(e0.toString());
        }
    }
}
