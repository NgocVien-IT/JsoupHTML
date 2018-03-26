package com.example.tnv.jsouphtml.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tnv.jsouphtml.R;
import com.example.tnv.jsouphtml.model.EnglishDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TNV on 3/15/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private Context mContext;
    private List<EnglishDetails> listEnglish = new ArrayList<>();
    OnClickItemRecyclerview monClickItemRecyclerview;

    public RecyclerViewAdapter(Context mContext, List<EnglishDetails> englishDetails, List<EnglishDetails> listEnglish, OnClickItemRecyclerview onClickItemRecyclerview) {
        this.mContext = mContext;
        this.listEnglish = listEnglish;
        this.monClickItemRecyclerview = onClickItemRecyclerview;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item_details, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(listEnglish.get(position).getmImage())
                .fit().centerInside()
                .into(holder.imageViewDetails);
        holder.textViewVocabulary.setText("Từ : "+listEnglish.get(position).getmWord());
        holder.textViewTranslation.setText(listEnglish.get(position).getmEasyRead());
        holder.textViewCategory.setText("Từ loại : "+listEnglish.get(position).getmCategory());
        holder.textViewExample.setText("Ví dụ : "+listEnglish.get(position).getmExample());
        holder.textViewTranslate.setText("Dịch : "+listEnglish.get(position).getmTranslate());

    }



    public void getTen(){

    }

    @Override
    public int getItemCount() {
        return listEnglish.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDetails;
        TextView textViewVocabulary, textViewCategory, textViewTranslation,
                textViewExample, textViewTranslate;
        ImageButton imageButton;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageViewDetails = itemView.findViewById(R.id.imageDetails);
            textViewVocabulary = itemView.findViewById(R.id.tvVocabulary);
            textViewTranslation = itemView.findViewById(R.id.tvTranslation);
            textViewCategory = itemView.findViewById(R.id.tvCategory);
            textViewExample = itemView.findViewById(R.id.tvExample);
            textViewTranslate = itemView.findViewById(R.id.tvTranslate);
            imageButton = itemView.findViewById(R.id.imagebuttonPlay);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monClickItemRecyclerview.playService(getPosition());
                }
            });
        }
    }
    public interface OnClickItemRecyclerview{
        public void playService(int i);
    }
}
