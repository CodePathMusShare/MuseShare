package com.codepath.museshare.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.museshare.R;

public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";
   // public static final BaseApi
    private RecyclerView rvSongs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_example, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSongs = view.findViewById(R.id.rvSongs);
    }

//    public void playlists(JsonHttpResponseHandler handler ) {
//        String apiUrl = getApiUrl("playlists/playlist_id");
//    }
}