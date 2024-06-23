package com.alparslankilic.retrofitcinema;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    private Movies movies;
    private int rowLayout;
    private Context context;
    private View.OnClickListener mOnItemClickListener;

    public MoviesAdapter(Movies movies,int rowLayout,Context context)
    {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //kendisine gelen bir veri kaydı için viewHolder ı tetikleyecek
    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        holder.movieTitle.setText(movies.getMovies().get(position).getTitle());
        holder.data.setText(movies.getMovies().get(position).getYear());
        holder.movieDescription.setText(movies.getMovies().get(position).getSummary());
        holder.rating.setText(movies.getMovies().get(position).getRating());
        Picasso.with(context).load(movies.getMovies().get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movies.getMovies().size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    //getView Metodu
    public class ViewHolder extends RecyclerView.ViewHolder  {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView image;


        public ViewHolder(View v) {
            super(v);
            moviesLayout = v.findViewById(R.id.movies_layout);
            movieTitle = v.findViewById(R.id.title);
            data = v.findViewById(R.id.subtitle);
            movieDescription = v.findViewById(R.id.description);
            rating = v.findViewById(R.id.rating);
            image = v.findViewById(R.id.imageView);
            v.setTag(this);
            v.setOnClickListener(mOnItemClickListener);
        }

    }
}
