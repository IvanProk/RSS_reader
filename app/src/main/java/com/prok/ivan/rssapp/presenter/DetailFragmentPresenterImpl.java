package com.prok.ivan.rssapp.presenter;

import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.simple.SimpleTextRequest;
import com.prok.ivan.rssapp.view.IDetailFragmentView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Inject;

public class DetailFragmentPresenterImpl implements IDetailFragmentPresenter {

    private IDetailFragmentView view;
    private String mediaURL;
    @Inject
    public DetailFragmentPresenterImpl() {
    }

    @Override
    public void init(IDetailFragmentView view) {
        this.view = view;
    }

    @Override
    public void onResume(SpiceManager spiceManager, String link, String spareImageUrl) {
        mediaURL = spareImageUrl;
        view.startService();
        sendRequest(link, spiceManager);
    }

    @Override
    public void onPause() {
        view.stopService();
    }

    @Override
    public void backButtonOnClick() {view.close();}

    public void toSiteButtonOnClick(){
        view.goToSite();
    }

    private void sendRequest(String url, SpiceManager spiceManager) {
        SimpleTextRequest request = new SimpleTextRequest(url);
        spiceManager.execute(request, "htmlNews" + url, DurationInMillis.ONE_DAY, new RSSFeedRequestListener());
        view.showProgressDialog();
    }


    // ----- Inner Class for RequestListener

    private final class RSSFeedRequestListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            view.hideProgressDialog();
        }

        @Override
        public void onRequestSuccess(String result) {
            String imageUrl;
            String name;
            String desc = "";

            view.hideProgressDialog();
            Log.d("RSS", "onRequestSuccess: result is: " + result);
            Document document = Jsoup.parse(result);
            String url = document.body().getElementsByClass("g-picture").select("[itemprop = image]").attr("src");
            if(!url.equals(""))
                imageUrl = url;
            else
            imageUrl = mediaURL;
            name = document.body().getElementsByTag("h1").first().text();
            Elements topic = document.body().getElementsByClass("b-text");
            for (Element element : topic.select("p"))
                desc += element.text() + "\n\n";
            view.hideProgressDialog();
            Log.d("RSS", "onRequestSuccess: imgUrl -- " + imageUrl +
                    "\n name -- " + name +
                    "\n description -- " + desc);
            view.updateViews(imageUrl, name, desc);
        }
    }
}

