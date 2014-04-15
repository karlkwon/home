package crawl.jerichoTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class peopleDaum {
	public class personInfo {
		long	birthDate;
		long	home;
		int		height;
		int		weight;
		int		bloodType;
	}
	
	static	Set<Integer>		seedSet		= new HashSet<Integer>();
	static	List<String>		nameList	= new ArrayList<String>();
//	static	List<String>	seedList	= new LinkedList<String>();
	
	//	http://movie.daum.net/moviedetail/moviedetailMain.do?movieId=84415
	//	http://movie.daum.net/tv/detail/main.do?tvProgramId=58855
	
	//	손석희: http://k.daum.net/qna/item/view.html?sobid=homo&itemid=21635
	//	아이유: http://music.daum.net/artist/main?artistDetailId=170772
	//	김준호: http://movie.daum.net/movieperson/Summary.do?personId=121573&t__nil_main=tabName
	//	http://movie.daum.net/movieperson/Summary.do?personId=@@@@@@&t__nil_main=tabName

	public static String tempSummary	= "http://movie.daum.net/movieperson/Summary.do?personId=@@@@@@&t__nil_main=tabName";
	public static void getMainInfo(int id) {
		String	url	= tempSummary.replace("@@@@@@", Integer.toString(id));
		
        Source source	= null;
		try {
			source = new Source(new URL(url));
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
//        	System.out.println(e0);
        	String	value	= e0.getAttributeValue("class");
        	if(value!=null && value.compareTo("mainInfo")==0) {
        		
        		List<Element>	e1List	= e0.getAllElements(HTMLElementName.DL);
        		
        		for(Element e1: e1List) {
                	String	value2	= e1.getAttributeValue("class");
                	if(value2!=null && value2.compareTo("cu")==0) {
                		List<Element>	e2List	= e1.getAllElements(HTMLElementName.DT);
                		for(Element e2: e2List) {
                    		System.out.println(e2.getTextExtractor());
                		}
                		
                		List<Element>	e3List	= e1.getAllElements(HTMLElementName.DD);
                		for(Element e3: e3List) {
                			
                    		List<Element>	e4List	= e3.getAllElements(HTMLElementName.A);
                    		if(e4List!=null && e4List.size()>0) {
                        		for(Element e4: e4List) {
                                	String	value3	= e4.getAttributeValue("href");
                        			System.out.println(value3 + ", " + e4.getTextExtractor());
                        		}
                    		}
                    		else
                    			System.out.println(e3.getTextExtractor());
                		}
            		
                	}
        		}
        		
        	}
        }
	}
	
	
	
	//	url: http://movie.daum.net/movieperson/PersonalNetwork.do?personId=@@@@@@&t__nil_PersonalNetwork=tabName
	//	url: http://movie.daum.net/movieperson/PersonalNetwork.do?personId=121573&t__nil_PersonalNetwork=tabName
	public static String tempPersonalNetwork	= "http://movie.daum.net/movieperson/PersonalNetwork.do?personId=@@@@@@&t__nil_PersonalNetwork=tabName";
	public static void getRelatedPeople(Map<Integer, String> map) {
		Integer	idx	= map.keySet().iterator().next();
		String	url	= map.get(idx);
		
		
		getMainInfo(idx);
		
        Source source	= null;
		try {
			source = new Source(new URL(url));
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
        	String	value	= e0.getAttributeValue("id");
        	if(value!=null && value.compareTo("relativePerson")==0) {
        		List<Element>	e1List	= e0.getAllElements(HTMLElementName.A);
        		
        		for(Element e1: e1List) {
                	String	value2	= e1.getAttributeValue("class");
                	if(value2!=null && value2.compareTo("em")==0) {
                		String	newURL	= e1.getAttributeValue("href");
                		String	name	= e1.getTextExtractor().toString();
                		String	id		= null;
                		{
                			String	keyword	= "personId=";
                			int	si	= newURL.indexOf(keyword) + keyword.length();
                			int	ei	= newURL.indexOf("&", si);
                			
                			id	= newURL.substring(si, ei);
                		}
                		
//                		System.out.println(newURL);
//                		System.out.println(name + ", " + id);
                		
                		if(newURL.contains("movie.daum.net")) {
                			if(!map.containsKey(id)) {
                				int	id_i	= Integer.parseInt(id);
                				
                				map.put(id_i, tempPersonalNetwork.replace("@@@@@@", id));
                				nameList.add(name);
                			}
                		}
//                    	System.out.println(e1);
                	}
        		}
        	}
       	}
	}	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Map<Integer, String>	url	= new HashMap<Integer, String>();
        
        int	id	= 121573;
        url.put(121573, tempPersonalNetwork.replace("@@@@@@", Integer.toString(id)));
        
//        String	url	= "http://movie.daum.net/movieperson/PersonalNetwork.do?personId=121573&t__nil_PersonalNetwork=tabName";
        
        for(int i=0; i<2; ++i)
        	getRelatedPeople(url);
        
        for(String str: nameList)
        	System.out.println(str);
        	
        System.out.println("SIZE: " + url.size());
    }
}
