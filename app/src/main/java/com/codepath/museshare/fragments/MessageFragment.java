package com.codepath.museshare.fragments;

import static com.parse.Parse.getApplicationContext;
import static com.parse.Parse.getParseCacheDir;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.museshare.ChannelActivity;
import com.codepath.museshare.LoginActivity;
import com.codepath.museshare.R;
import com.codepath.museshare.databinding.FragmentMessageBinding;
import com.parse.ParseUser;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.client.models.Channel;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.livedata.ChatDomain;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory;

import static java.util.Collections.singletonList;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {
    public static final String TAG = "MessageFragment";
    FragmentMessageBinding binding;
    //private RecyclerView rvSongs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMessageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Step 1 - Set up the client for API calls and the domain for offline storage
        ChatClient client = new ChatClient.Builder("y4crq24crcyz", getApplicationContext())
                .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
                .build();
        new ChatDomain.Builder(client, getApplicationContext()).build();

        // Step 2 - Authenticate and connect the user
        User user = new User();

        ParseUser currentUser = ParseUser.getCurrentUser();
        user.setId(currentUser.getUsername());
        Toast.makeText(getContext(), "User = "+currentUser.getUsername(), Toast.LENGTH_SHORT).show();
        Log.i(TAG, "User"+ currentUser.getUsername());
        //user.getExtraData().put("name", "Sarah Iqbal");
        //user.getExtraData().put("image", "https://bit.ly/2TIt8NR");

        String token = client.devToken(user.getId());

        client.connectUser(
                user,
                token
        ).enqueue(result -> {
            if (result.isSuccess()) {
                Toast.makeText(getContext(), "User Success!", Toast.LENGTH_SHORT).show();
            } else {
                // Handle result.error()
                Log.e(TAG, "Issue with User!"+result.error().getMessage(), result.error().getCause());
                Toast.makeText(getContext(), "Issue with User!", Toast.LENGTH_SHORT).show();
            }
        });

//        String channelType1 = "messaging";
//        String channelID1 = "group1";
//        List<String> members1 = Arrays.asList("sarah-iqbal");
//        client.createChannel(channelType1, channelID1, members1).enqueue(result -> {
//            if (result.isSuccess()) {
//                Channel channel = result.data();
//            } else {
//                // Handle result.error()
//                Log.e(TAG, "Issue with channel!", result.error().getCause());
//                Toast.makeText(getContext(), "Issue with channel1!", Toast.LENGTH_SHORT).show();
//            }
//        });


//
        // Step 3 - Set the channel list filter and order
        // This can be read as requiring only channels whose "type" is "messaging" AND
        // whose "members" include our "user.id"
        FilterObject filter = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.in("members", singletonList(user.getId()))
        );

        ChannelListViewModelFactory factory = new ChannelListViewModelFactory(
                filter,
                ChannelListViewModel.DEFAULT_SORT
        );

        ChannelListViewModel channelsViewModel =
                new ViewModelProvider(this, factory).get(ChannelListViewModel.class);

        // Step 4 - Connect the ChannelListViewModel to the ChannelListView, loose
        //          coupling makes it easy to customize
        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, this);
        binding.channelListView.setChannelItemClickListener(channel -> {
            startActivity(ChannelActivity.newIntent(getContext(), channel)
            );
        });
    }




}