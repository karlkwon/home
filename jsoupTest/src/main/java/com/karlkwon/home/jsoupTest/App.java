package com.karlkwon.home.jsoupTest;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App 
{
    public static void main( String[] args )
    {
//    	String		url		= "http://www.insight.co.kr/";
    	String		url		= "http://www.insight.co.kr/content.php?Idx=4710&Code1=004";
    	try {
			Document	doc		= Jsoup.connect(url).get();
//			System.out.println(doc.toString());
			
//			Elements		title	= doc.select(".title");
//			
//			for(Element e: title) {
//				System.out.println("text: " + e.text());
//				System.out.println("html: " + e.html());
//			}
//
//			Elements		links	= doc.select("a[href]");
//			for(Element l: links) {
//				System.out.println("all : " + l.toString());
//				
//				String	str	= l.attr("abs:href");
////				String	str	= l.attr("href");
//				if(str.length() > 0)
//					System.out.println("link: " + str);
//			}
//
//			System.out.println();
			
//			Elements		content	= doc.select("#contView_detail div");
//			Elements		content	= doc.select("contView div");
//			Elements		content	= doc.select("div.contView_detail");
//			Elements		content	= doc.select("div.contView");
//			Elements		content	= doc.select(".contView");
			doc.select("div.content_left").remove();
			doc.select("div#view_thumnail").remove();
			Elements		content	= doc.select(".contView_detail div");
//			content	= content.removeClass("content_left");
			
			for(Element e: content) {
//				System.out.println("attr: " + e.attr("class"));
				if(e.attr("class").equals("content_left")) {
					e.remove();
//					System.out.println("~~~~");
					continue;
				}
				System.out.println("attr: " + e.text());
//				System.out.println("attr: " + e.html());
			}

			System.out.println();
			System.out.println();
			
			Elements		keys	= doc.select("div#detail_tag a.tag");
			
			for(Element e: keys) {
				System.out.println("tags: " + e.text());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        System.out.println( "Hello World!" );
    }
}
