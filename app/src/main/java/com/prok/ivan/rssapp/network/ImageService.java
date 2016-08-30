package com.prok.ivan.rssapp.network;

import android.content.Context;
import android.widget.ImageView;

import com.prok.ivan.rssapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Ivan Prokofyev on 20.08.16.
 */
public class ImageService {
    private static ImageService ourInstance = new ImageService();
    private Context context;
    public static ImageService getInstance(Context context) {
        ourInstance.context = context;
        return ourInstance;
    }

    private ImageService() {
    }

    public void downloadImage(String url, ImageView target) {
//        Picasso.with(context).setIndicatorsEnabled(true);

        float scale = context.getResources().getDisplayMetrics().density;
        int height = (int) (280*scale + 0.5f);
        int width = (int) (420*scale + 0.5f);

        Picasso.with(context)
                .load(url)
                .resize(width, height)
                .error(R.drawable.image_placeholder)
                .placeholder(R.drawable.image_placeholder)
                .into(target);
    }
}
