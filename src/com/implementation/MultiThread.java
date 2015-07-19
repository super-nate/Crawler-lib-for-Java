package com.implementation;

import nate.crawler.Crawler;

public class MultiThread {
	public static void main(String[] args) {
		//new a parser
		HtmlParser parser = new HtmlParser();
		
		//new a crawler
		Crawler crawler1 = new Crawler(
				new String[] { "http://www.amazon.com/Kindle-eBooks/b?ie=UTF8&node=154606011" }, parser);
		
		//set the name of the thread, if you didn't set, so it will not show name
		crawler1.setName("crawler1");
		
		//set the times of loop for the url links, if you don't set, it will try as more as possible
		// crawler1.setTimes(1);
		
		Thread thread1 = new Thread(crawler1);
		thread1.start();

		Crawler crawler2 = new Crawler(
				new String[] { "http://www.amazon.com/s/ref=sr_pg_199?rh=n%3A133140011%2Cn%3A%21133141011%2Cn%3A154606011&page=199&ie=UTF8&qid=1435690851" }, parser);
		crawler2.setName("crawler2");
		Thread thread2 = new Thread(crawler2);
		thread2.start();

		Crawler crawler3 = new Crawler(
				new String[] { "http://www.amazon.com/s/ref=sr_pg_99?rh=n%3A133140011%2Cn%3A%21133141011%2Cn%3A154606011&page=99&ie=UTF8&qid=1435690993" }, parser);
		crawler3.setName("crawler3");
		Thread thread3 = new Thread(crawler3);
		thread3.start();

		Crawler crawler4 = new Crawler(
				new String[] { "http://www.amazon.com/s/ref=sr_pg_299?rh=n%3A133140011%2Cn%3A%21133141011%2Cn%3A154606011&page=299&ie=UTF8&qid=1435691062" }, parser);
		crawler4.setName("crawler4");
		Thread thread4 = new Thread(crawler4);
		thread4.start();

	}

}
