package com.codepath.museshare;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("u6SCnOyzDyqYEjkWcsjYs0Kh9eyV8xtkypOvm8KP")
                .clientKey("4gYj9FUJgiZq0pQOTG0OrKRrxVixXdAJp8goYG61")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
