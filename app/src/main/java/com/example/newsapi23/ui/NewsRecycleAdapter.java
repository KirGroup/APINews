package com.example.newsapi23.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapi23.R;
import com.example.newsapi23.SelectListener;
import com.example.newsapi23.domen.ListNewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsRecycleAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";
    private int changedItemPosition;
    private boolean isLiked;

    private final Context context;
    private List<ListNewsHeadlines> headlines = new ArrayList<>();
    private final SelectListener listener;

    public NewsRecycleAdapter(Context context, SelectListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items, parent, false)); // создает превью, строчку новости.
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getName());

        if(headlines.get(position).getUrlToImage()!=null){
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);
        }
        holder.cardview.setOnClickListener(view -> listener.onNewsClicked(headlines.get(position)));
        holder.favoriteButton.setOnClickListener(view -> listener.setFavorite(headlines.get(position), holder.favoriteButton.isChecked())); //
        holder.favoriteButton.setChecked(headlines.get(position).isFavorite()); //утанавливаем значение значку избранное. Если избранное, то подсвечиваем. Если не избранное, выключаем.
        holder.favoriteButton.jumpDrawablesToCurrentState(); //пропуск анимации при прокручивании вью
    }

    @Override
    public int getItemCount() {
        if (headlines == null) return 0;
        return headlines.size();
    }

    public void setNews(List<ListNewsHeadlines> list) {
        headlines = list;
        notifyDataSetChanged();  //обновил список, поле иницилизации
    }
}