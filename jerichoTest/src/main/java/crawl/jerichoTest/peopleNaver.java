package crawl.jerichoTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class peopleNaver {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Source source	= null;
		try {
			source = new Source(new URL("http://people.search.naver.com/search.naver?where=nexearch&query=%EA%B9%80%EC%A4%80%ED%98%B8&sm=tab_etc&ie=utf8&key=PeopleService&os=100075"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        source.fullSequentialParse();
        
        List<Element> liList = source.getAllElements(HTMLElementName.DIV);
        
        for(Element e0: liList) {
        	String	value	= e0.getAttributeValue("class");
        	if(value!=null && value.compareTo("profile_dsc")==0) {
        		List<Element>	e1List	= e0.getAllElements(HTMLElementName.DL);
        		
        		for(Element e1: e1List) {
                	String	value2	= e1.getAttributeValue("class");
                	if(value2!=null && value2.compareTo("dsc")==0) {
                		
                		List<Element>	e2List	= e1.getAllElements(HTMLElementName.DT);
                		for(Element e2: e2List) {
                    		System.out.println(e2.toString());
                		}
                		
                		System.out.println();
                		
                		List<Element>	e3List	= e1.getAllElements(HTMLElementName.DD);
                		for(Element e3: e3List) {
                    		System.out.println(e3.getTextExtractor());
//                    		System.out.println(e3);
                    		
                    		List<Element>	e4List	= e3.getAllElements(HTMLElementName.A);
                    		for(Element e4: e4List) {
                            	String	href	= e4.getAttributeValue("href");
                            	if(href != null)
                            		System.out.println(href);
//                    			System.out.println(e4);
                    			System.out.println(e4.getTextExtractor());
                    		}
                    		
                    		System.out.println();
                    		
//                    		System.out.println(e3.toString());
                		}
                	}
        		}
        	}
        }
    }
}
