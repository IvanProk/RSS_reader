/*
 * Copyright (C) 2011 Mats Hofman <http://matshofman.nl/contact/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prok.ivan.rssapp.model.saxrssreader;

import com.prok.ivan.rssapp.model.ItemNews;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class RssHandler extends DefaultHandler {

	private RssFeed rssFeed;
	private ItemNews itemNews;
	private StringBuilder stringBuilder;
	private int itemNewsId = 0;

	@Override
	public void startDocument() {
		rssFeed = new RssFeed();
	}

	/**
	 * Return the parsed RssFeed with it's RssItems
	 * @return
	 */
	public RssFeed getResult() {
		return rssFeed;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		stringBuilder = new StringBuilder();

		if(qName.equals("item") && rssFeed != null) {
			itemNews = new ItemNews(itemNewsId++);
			rssFeed.addRssItem(itemNews);
		}
		else if(qName.equals("enclosure") && rssFeed != null){
			itemNews.setEnclosureUrl(attributes.getValue("url"));
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		stringBuilder.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) {

		if(rssFeed != null && itemNews == null) {
			// Parse feed properties

			try {
				if (qName != null && qName.length() > 0) {
				    String methodName = "set" + qName.substring(0, 1).toUpperCase() + qName.substring(1);
				    Method method = rssFeed.getClass().getMethod(methodName, String.class);
				    method.invoke(rssFeed, stringBuilder.toString());
				}
			} catch (SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
			}

		} else if (itemNews != null) {
			// Parse item properties

			try {
				String methodName = "set" + qName.substring(0, 1).toUpperCase() + qName.substring(1);
				Method method = itemNews.getClass().getMethod(methodName, String.class);
				method.invoke(itemNews, stringBuilder.toString());
			} catch (SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			    e.printStackTrace();
            }
        }

	}

}
