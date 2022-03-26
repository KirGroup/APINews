package com.example.newsapi23;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public TextView text_title;
    public TextView text_source;
    public ImageView img_headline;
    public CardView cardview;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_title = itemView.findViewById(R.id.text_title);
        text_source = itemView.findViewById(R.id.text_source);
        img_headline = itemView.findViewById(R.id.img_headline);
        cardview = itemView.findViewById(R.id.main_container);


    }
}