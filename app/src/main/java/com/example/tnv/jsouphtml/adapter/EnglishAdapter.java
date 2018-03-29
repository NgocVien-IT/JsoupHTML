package com.example.tnv.jsouphtml.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tnv.jsouphtml.R;
import com.example.tnv.jsouphtml.model.English;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TNV on 3/15/2018.
 */

public class EnglishAdapter extends BaseAdapter {
    private Context mContext;
    private int mLayout;
    private List<English> mlistEnglish;

    public EnglishAdapter(Context mContext, int mLayout, List<English> mlistEnglish) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mlistEnglish = mlistEnglish;
    }

    @Override
    public int getCount() {
        return mlistEnglish.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        ImageView imageViewItem;
        TextView textViewItem;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(mLayout,null);
            viewHolder.imageViewItem = view.findViewById(R.id.imageViewItem);
            viewHolder.textViewItem= view.findViewById(R.id.tvTopic);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        English english = mlistEnglish.get(i);
        Picasso.with(mContext).load(english.getmUrlImage()).error(R.drawable.ic_error).fit().centerInside().into(viewHolder.imageViewItem);

        viewHolder.textViewItem.setText(english.getmName());
        return view;
    }
}
