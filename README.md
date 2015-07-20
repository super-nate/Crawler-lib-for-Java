# CrawlerLib
This is a complete example for the usage of the crawler lib written by myself
#Quick Start
* 1, import the jar into classpath
* 2, implement the Parser interface
* 3, write a class to run the crawler<br>
 you can find the complete example in the project

#Implement the Parser interface
* implement the extractlink method, in this method you extract links and do some other operations like crud

#The usage of Crawler Class
* 1, create a new crawler thread<br>
Crawler crawler1 = new Crawler(
				new String[] { "http://www.amazon.com/Kindle-eBooks/b?ie=UTF8&node=154606011" }, parser);
* 2, set the name of the thread, if you didn't set, it will not show any name<br>
crawler1.setName("crawler1");
* 3, set the times of loop for the url links, if you don't set, it will try as more as possible<br>
crawler1.setTimes(100);//parse 100 url

#Example
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
