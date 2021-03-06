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

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.prok.ivan.rssapp.model.ItemNews;

public class RssFeed implements Parcelable {

	private String title;
	private String link;
	private String description;
	private String language;
	private ArrayList<ItemNews> itemNewses;
	
	public RssFeed() {
		itemNewses = new ArrayList<ItemNews>();
	}
	
	public RssFeed(Parcel source) {
		
		Bundle data = source.readBundle(getClass().getClassLoader());
		title = data.getString("title");
		link = data.getString("link");
		description = data.getString("description");
		itemNewses = data.getParcelableArrayList("itemNewses");
		
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		Bundle data = new Bundle();
		data.putString("title", title);
		data.putString("link", link);
		data.putString("description", description);
		data.putParcelableArrayList("itemNewses", itemNewses);
		dest.writeBundle(data);
	}
	
	public static final Parcelable.Creator<RssFeed> CREATOR = new Parcelable.Creator<RssFeed>() {
		public RssFeed createFromParcel(Parcel data) {
			return new RssFeed(data);
		}
		public RssFeed[] newArray(int size) {
			return new RssFeed[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}
	
	void addRssItem(ItemNews itemNews) {
		itemNewses.add(itemNews);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<ItemNews> getItemNewses() {
		return itemNewses;
	}

	public void setItemNewses(ArrayList<ItemNews> itemNewses) {
		this.itemNewses = itemNewses;
	}

}
