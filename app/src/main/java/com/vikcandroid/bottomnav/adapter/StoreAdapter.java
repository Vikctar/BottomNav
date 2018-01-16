package com.vikcandroid.bottomnav.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vikcandroid.bottomnav.R;
import com.vikcandroid.bottomnav.model.Movie;

import java.util.List;

/**
 * Created by android-dev on 1/16/18.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private Context context;
    private List<Movie> movies;

    public StoreAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_list, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.name.setText(movie.getTitle());
        holder.price.setText(movie.getPrice());

        Glide.with(context)
                .load(movie.getImage())
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public ImageView thumbnail;

        public StoreViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }
}
