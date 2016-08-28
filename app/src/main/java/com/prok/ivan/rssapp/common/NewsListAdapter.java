package com.prok.ivan.rssapp.common;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.prok.ivan.rssapp.R;
import com.prok.ivan.rssapp.model.ItemNews;
import com.prok.ivan.rssapp.network.ImageService;


import java.util.List;

public class NewsListAdapter extends BaseAdapter {

    private List<ItemNews> itemTalkList;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private Runnable footerOnClickAction;
    protected int totalListSize;

    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_ACTIVITY = 1;


    public NewsListAdapter(Activity activity, List<ItemNews> itemTalkList, int totalListSize) {
        this.itemTalkList = itemTalkList;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;
        this.totalListSize = totalListSize;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) == VIEW_TYPE_ACTIVITY;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return itemTalkList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position >= itemTalkList.size()) ? VIEW_TYPE_LOADING : VIEW_TYPE_ACTIVITY;
    }

    @Override
    public ItemNews getItem(int position) {
        return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? itemTalkList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? position : -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == VIEW_TYPE_LOADING) {
            // возвращаем вместо строки с данными футер с кнопкой
            return getFooterView(position, convertView, parent);
        }
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_news_list, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.nameText = (TextView) view.findViewById(R.id.news_title);
            holder.descText = (TextView) view.findViewById(R.id.news_description);
            holder.publishedText = (TextView) view.findViewById(R.id.pub_date);
            holder.newsImage = (ImageView) view.findViewById(R.id.news_image);
            view.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        ItemNews itemNews = getItem(position);
        holder.nameText.setText(itemNews.getTitle());
        holder.descText.setText(itemNews.getDescription());
        holder.publishedText.setText(itemNews.getPubDate());
        ImageService.getInstance(activity).downloadImage(itemNews.getEnclosureUrl(), holder.newsImage);
        return view;
    }

    public void add(List<ItemNews> itemTalks) {
        itemTalkList.addAll(itemTalks);
        notifyDataSetChanged();
    }

    public View getFooterView(int position, View convertView, final ViewGroup parent) {
        if (position >= totalListSize && totalListSize > 0) {
            // the ListView has reached the last row
            Button btnLastRow = new Button(activity);
            btnLastRow.setHint("Tap to go up");
            btnLastRow.setGravity(Gravity.CENTER);
            btnLastRow.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  footerOnClickAction.run();
                                              }
                                          }
            );
            return btnLastRow;
        }
        View row = convertView;
        return row;
    }

    public void setFooterButtonOnClick(Runnable action){
        this.footerOnClickAction = action;
    }

    static class ViewHolder {
        TextView nameText;
        TextView descText;
        TextView publishedText;
        ImageView newsImage;
    }
}
