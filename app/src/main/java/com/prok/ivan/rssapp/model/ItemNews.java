package com.prok.ivan.rssapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class ItemNews implements Parcelable {

    private String title;
    private String link;
    private Date pubDate;
    private String description;
    private String category;
    private String enclosureUrl;
    private int id = 0;

    public ItemNews(int id) {
        this.id = id;
    }

    public ItemNews(Parcel source) {

        Bundle data = source.readBundle();
        title = data.getString("title");
        link = data.getString("link");
        pubDate = (Date) data.getSerializable("pubDate");
        description = data.getString("description");
        category = data.getString("category");
        enclosureUrl = data.getString("enclosureUrl");
        id = data.getInt("id");

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        Bundle data = new Bundle();
        data.putString("title", title);
        data.putString("link", link);
        data.putSerializable("pubDate", pubDate);
        data.putString("description", description);
        data.putString("category", category);
        data.putString("enclosureUrl", enclosureUrl);
        data.putInt("id", id);
        dest.writeBundle(data);
    }

    public static final Parcelable.Creator<ItemNews> CREATOR = new Parcelable.Creator<ItemNews>() {
        public ItemNews createFromParcel(Parcel data) {
            return new ItemNews(data);
        }

        public ItemNews[] newArray(int size) {
            return new ItemNews[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm");
        return format.format(pubDate);
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setPubDate(String pubDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            this.pubDate = dateFormat.parse(pubDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String url) {
        this.enclosureUrl = url;
    }

    public String getCategory() {
        return category;
    }
//
//	@Override
//	public int compareTo(ItemNews another) {
//		if(getPubDate() != null && another.getPubDate() != null) {
//			return getPubDate().compareTo(another.getPubDate());
//		} else {
//			return 0;
//		}
//	}

    public class Enclosure {
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

}
