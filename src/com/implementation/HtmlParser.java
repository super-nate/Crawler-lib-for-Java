package com.implementation;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import nate.crawler.Parser;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//implement the parser interface
public class HtmlParser implements Parser{

	public Set<String> extracLinks(String visitUrl) {
		// TODO
		Document doc;
		Set<String> set = new HashSet<String>();
		try {
			doc = Jsoup.connect(visitUrl).get();

			Elements spans = doc.getElementsByClass("pagnLink");
			for (Element span : spans) {
				// System.out.println("text: " + link.text());
				Elements links = span.getElementsByTag("a");
				for (Element link : links) {
					System.out.println(link.attr("abs:href"));
					System.out.println(link.text());
					set.add(link.attr("abs:href"));
				}
			}

			Element resultlist = doc.getElementById("searchTemplate");
			Elements products = resultlist.getElementsByClass("prod");

			// for other pages except 1st
			if (products.size() == 0) {
				// get all the books in one page,like
				// http://www.amazon.com/s?ie=UTF8&page=16&rh=n%3A154606011
				products = resultlist.getElementsByClass("s-result-item");
				System.out.println(products.size());
				// for each book, get the book link and the review
				for (Element product : products) {
					try {
						String booklink = null;
						String review = null;
						String title = null;
						String price = null;

						// get the link and review
						Elements productlink = product
								.select("a.a-link-normal.s-access-detail-page.a-text-normal");
						for (Element link : productlink) {

							booklink = link.attr("href");
							// System.out.println("booklink: "+booklink);
							Document bookDoc = null;
							try {
								bookDoc = Jsoup.connect(booklink).get();
							} catch (HttpStatusException e) {
								// TODO
								e.printStackTrace();
								continue;
							}

							Element div = bookDoc.getElementById("avgRating");

							Elements productReviews = div
									.getElementsByTag("span");
							review = productReviews.get(1).text();
							//System.out.println("Review: " + review);
							review = review.split(" ")[0];

						}
						// get the title of the book
						Elements titles = product
								.select("a.a-link-normal.s-access-detail-page.a-text-normal>h2");
						title = titles.get(0).text();
						// System.out.println("title: " + title);

						// get price
						Elements prices = product
								.select("span.a-size-base.a-color-price.s-price.a-text-bold");
						price = prices.get(0).text();
						//System.out.println("price: " + price);
						price = price.substring(1);
						
						System.out.println("title: " + title + " price: "
								+ price + " review: " + review + " booklink: "
								+ booklink);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						continue;
					}
				}

			}

			else
			// for the 1st page
			{
				System.out.println(products.size());
				for (Element product : products) {

					String booklink = null;
					String review = null;
					String title = null;
					String price = null;

					// get the link of the book and the overall review
					Elements productlink = product.select("h3.newaps > a");
					for (Element link : productlink) {

						booklink = link.attr("href");
						// System.out.println("booklink: "+booklink);
						Document bookDoc = null;
						try {
							bookDoc = Jsoup.connect(booklink).get();
						} catch (HttpStatusException e) {
							// TODO
							e.printStackTrace();
							continue;
						}

						Element div = bookDoc.getElementById("avgRating");
						Elements productReviews = div.getElementsByTag("span");
						review = productReviews.get(1).text();
						// System.out.println("Review: " + review);
						review = review.split(" ")[0];

					}
					Elements names = product.getElementsByClass("lrg");
					// get the title of the book
					title = names.get(0).text();
					// System.out.println("title: "+title);
					// get the price of the book
					price = names.get(1).text();
					// System.out.println("price: "+price);
					price = price.substring(1);
					System.out.println("title: " + title + " price: " + price
							+ " review: " + review + " booklink: " + booklink);
				}
			}
		} catch (HttpStatusException e) {
			// TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}

		return set;
	}

}
