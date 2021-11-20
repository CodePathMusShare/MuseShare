package com.codepath.museshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codepath.museshare.fragments.HomeFragment;
import com.codepath.museshare.fragments.MessageFragment;
import com.codepath.museshare.fragments.PlayListFragment;
import com.codepath.museshare.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        btnLogOut = findViewById(R.id.btnLogOut);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) { // TO DO: Create fragment files
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        break;
                    case R.id.action_playlist:
                        Toast.makeText(MainActivity.this, "PlayList!", Toast.LENGTH_SHORT).show();
                        fragment = new PlayListFragment();
                        break;
                    case R.id.action_message:
                        Toast.makeText(MainActivity.this, "Message!", Toast.LENGTH_SHORT).show();
                        fragment = new MessageFragment();
                        break;
                    case R.id.action_settings:
                    default:
                        Toast.makeText(MainActivity.this, "Settings!", Toast.LENGTH_SHORT).show();
                        fragment = new SettingsFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
                //fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit();
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.action_home);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity();
                Toast.makeText(MainActivity.this, "Logged out!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}