package com.codepath.museshare.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.museshare.LoginActivity;
import com.codepath.museshare.R;
import com.codepath.museshare.databinding.FragmentPlayListBinding;
//import com.wrapper.spotify.SpotifyApi;
//import com.wrapper.spotify.exceptions.SpotifyWebApiException;
//import com.wrapper.spotify.model_objects.specification.Paging;
//import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
//import com.wrapper.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
//import com.wrapper.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

//import org.apache.hc.core5.http.ParseException;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyCallback;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import retrofit.client.Response;


public class PlayListFragment extends Fragment {
    public static final String TAG = "PlaylistFragment";
    String [] playlists;
    //ListView lvPlaylist;
    TextView tvPlaylist;
    TextView tvPlaylist2;
    TextView tvPlaylist3;
    TextView tvPlaylist4;


    //TextView playlistText;





    FragmentPlayListBinding binding;
    LoginActivity loginActivity;
    Context thiscontext;
    //public static final String USER_PLAYLIST_URL = ""
    //private RecyclerView rvSongs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // Log.d(TAG, "Test 11");
        thiscontext = container.getContext();
        Log.d(TAG, thiscontext.toString());




        //Log.d(TAG, "Test 13");
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken("BQChMWE6AsQP_GhJNur-oSXiEygItJKKu6wm9haQfBObDFvBBA0nkcrgnmNPcsYU3EyJcowcGGikZKMpFWM");
        SpotifyService spotify = api.getService();// connects to spotify api and is ready to use
        Log.d(TAG, "Test 14");
        spotify.getPlaylists("hamzaali360", new SpotifyCallback<Pager<PlaylistSimple>>() {
            @Override
            public void failure(SpotifyError spotifyError) {
                // handle error
                Log.d(TAG, "Error getting list of Playlists "+spotifyError.getMessage());
            }

            @Override
            public void success(Pager<PlaylistSimple> playlistSimplePager, Response response) {
               // Log.d(TAG, "List of Playlists : ");
                playlists = new String[playlistSimplePager.items.size()];
                int i=0;
                for (PlaylistSimple playlistSimple: playlistSimplePager.items) {
                    Log.d(TAG, playlistSimple.name);
                    playlists[i++] = playlistSimple.name;
                }

                //tvPlaylist.setText("List of Playlists: ");
                tvPlaylist.setText("List of Playlists: ");
                tvPlaylist2.setText(playlists[0]);
                tvPlaylist3.setText(playlists[1]);
                tvPlaylist4.setText(playlists[3]);


//                ArrayAdapter adapter = new ArrayAdapter<String>(thiscontext,
//                        R.layout.activity_listview, playlists);
//
//                lvPlaylist.setAdapter(adapter);
            }


        });

        binding = FragmentPlayListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        return view;

        //String apiUrl = "https://api.spotify.com/v1/playlists/3Q7RAs7QYNToyczdtWsO7M";
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //tvPlaylist = view.findViewById(R.id.tvPlaylist);
       // lvPlaylist = view.findViewById(R.id.lvPlaylist);
        tvPlaylist = view.findViewById(R.id.tvplaylist);
        tvPlaylist2 = view.findViewById(R.id.tvplaylist2);
        tvPlaylist3 = view.findViewById(R.id.tvplaylist3);
        tvPlaylist4 = view.findViewById(R.id.tvplaylist4);

        // playlistText.setText(playlists.get(0));
       // Log.d(TAG, "Test 12");
    }
}