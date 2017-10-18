package com.student.luai.jokeactivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import java.io.IOException;

public class JokeActivity extends AppCompatActivity {

    private static TextView mTextViewJokeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        mTextViewJokeDetails = (TextView) findViewById(R.id.tv_joke_details);

        mTextViewJokeDetails.setText(getIntent().getStringExtra("the_very_funny_joke"));

    }

    public static String getJokeFromText() {
        return mTextViewJokeDetails.getText().toString();
    }

}