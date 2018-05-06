package com.mc.mistplayfrontendchallenge;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.MyViewHolder>{
    private List<Game> gamesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subgenre;
        public TextView rating;
        public TextView ratingCount;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            subgenre = (TextView) view.findViewById(R.id.subgenre);
            rating = (TextView) view.findViewById(R.id.rating);
            ratingCount = (TextView) view.findViewById(R.id.ratingCount);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }

    public SearchResultsAdapter(List<Game> gamesList){
        this. gamesList = gamesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // When a game etners view, set values of game to display
        Game game = gamesList.get(position);
        holder.title.setText(game.getTitle());
        holder.subgenre.setText(game.getSubGenre());
        holder.rating.setText(String.valueOf(game.getRating()));
        holder.ratingCount.setText(String.valueOf(game.getRatingCount()));

        // Use ImageRequest to fetch game image in a different thread, as networking cannot be done in the main thread.
        new ImageRequest(holder.image, game.getImgURL()).execute();
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }
}
