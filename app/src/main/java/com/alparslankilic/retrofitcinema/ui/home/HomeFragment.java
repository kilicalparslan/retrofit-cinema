package com.alparslankilic.retrofitcinema.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.alparslankilic.retrofitcinema.ApiClient;
import com.alparslankilic.retrofitcinema.ApiInterface;
import com.alparslankilic.retrofitcinema.Movie;
import com.alparslankilic.retrofitcinema.MovieDetailActivity;
import com.alparslankilic.retrofitcinema.MovieResponse;
import com.alparslankilic.retrofitcinema.Movies;
import com.alparslankilic.retrofitcinema.MoviesAdapter;
import com.alparslankilic.retrofitcinema.R;
import com.alparslankilic.retrofitcinema.databinding.FragmentHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Movies movies;
    MoviesAdapter mAdapter;
    RecyclerView recyclerView;

    //Recylerview deki bir eleman tıklanma olayını yakaladık
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //reclerview o an sıradik elemanın tag bilgisine eriştim
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            //tıklamış olduğumuz filmin position bilgisini yani sıra numarasını elde ederiz
            int position =  viewHolder.getAdapterPosition();
            Movie movie = movies.getMovies().get(position);
            Intent i = new Intent(getActivity(), MovieDetailActivity.class);
            i.putExtra("description", movie.getDescription());
            i.putExtra("background_image_original", movie.getImage());
            startActivity(i);

        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView =  view.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Retrofit nesnesi oluşturulacak ancak bu nesne ApiInterface cast edilerek bize verilecek

        ApiInterface apiInterface =
                ApiClient.getRetrofitInstance().create(ApiInterface.class);
        /// https://yts.mx/api/v2/list_movies.json?sort_by=date_added
        Call<MovieResponse> call = apiInterface.getLastMovies();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movies = response.body().getData();
                mAdapter = new MoviesAdapter(movies, R.layout.list_item_movie, getActivity());
                recyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(onItemClickListener);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                int a = 5;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}