package com.anamaniac.movieinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TvMoviesAdapter extends RecyclerView.Adapter<TvMoviesAdapter.DetailsViewHolder> {
    List<TvDataModel> MovieList = new ArrayList<>();
    public RecyclerViewInterface clickLIstener;
    @NonNull
    @Override
    public TvMoviesAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int id = R.layout.recycler_view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(id,parent,shouldAttachToParentImmediately);
        return new DetailsViewHolder(view);

    }

    public void setMovieList(List<TvDataModel> movieList) {
        this.MovieList = movieList;
    }

    @Override
    public void onBindViewHolder(@NonNull TvMoviesAdapter.DetailsViewHolder holder, int position) {
        TvDataModel tvDataModel = MovieList.get(position);
        TextView Name = holder.textView;
        ImageView Poster = holder.imageView;
        String Title = tvDataModel.title;
        String Url = "https://image.tmdb.org/t/p/original"+tvDataModel.poster_path;
        Name.setText(Title);
        Glide.with(holder.itemView).load(Url).into(Poster);

    }
    public int ResultNumber;

    public void getResults(Integer Number){
        this.ResultNumber = Number;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return MovieList.size();
    }

    public void setClickLIstener(RecyclerViewInterface clickLIstener) {
        this.clickLIstener = clickLIstener;
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView textView;

        public DetailsViewHolder(@NonNull View view) {
            super(view);
            view.setOnClickListener(this);
            imageView = view.findViewById(R.id.PosterView);
            textView = view.findViewById(R.id.Name);
        }

        @Override
        public void onClick(View v) {
            clickLIstener.onClick(v,getAdapterPosition(),MovieList);

        }
    }
}
