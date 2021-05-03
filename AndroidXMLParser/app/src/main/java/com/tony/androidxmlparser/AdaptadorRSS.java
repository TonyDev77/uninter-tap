package com.tony.androidxmlparser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptadorRSS extends BaseAdapter {

    private List<RssUOL> rssUOL;
    Context context = null;
    private LayoutInflater inflater;

    public AdaptadorRSS(Context context, List<RssUOL> rssUOL){
        this.rssUOL = rssUOL;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.rssUOL.size();
    }

    @Override
    public Object getItem(int i) {
        return this.rssUOL.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final RssUOL rss = (RssUOL) getItem(i);

        if (null == view){
            view = inflater.inflate(R.layout.layout_rss, null);
        }

        TextView txtRss = (TextView) view.findViewById(R.id.txtRss);

        txtRss.setText(rss.getTitulo());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(rss.getLink()));
                context.startActivity(intent);
            }
        });

        return view;
    }
}
