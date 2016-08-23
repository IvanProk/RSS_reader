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

        int scale = context.getResources().getDisplayMetrics().densityDpi;

        Picasso.with(context)
                .load(url)
                .centerCrop()
//                .resize((int) context.getResources().getDimension(R.dimen.image_width), (int) context.getResources().getDimension(R.dimen.image_height))
                .resize(420*scale, 280*scale)
                .placeholder(R.drawable.picasso_loading_animation)
                .into(target);
    }
}
