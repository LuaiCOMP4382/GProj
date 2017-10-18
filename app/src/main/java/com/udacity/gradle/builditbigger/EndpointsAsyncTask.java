package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.sax.EndElementListener;
import android.util.Pair;
import android.widget.Toast;

import com.example.luai.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.student.luai.jokeactivity.JokeActivity;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, Pair<String, String>> {
    private static MyApi myApiService = null;
    private Context context;
    private EndpointsTaskListener mListener;
    public String randomJoke;

    @Override
    protected Pair<String, String> doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return new Pair<String, String>(myApiService.tellJoke().execute().getData(), name);
        } catch (IOException e) {
            return new Pair<String, String>(e.getMessage(), e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Pair<String, String> result) {

        randomJoke = result.first;

        if (!result.second.equals("no-intent")) {
            Intent i = new Intent(context, JokeActivity.class);

            i.putExtra("the_very_funny_joke", result.first);

            context.startActivity(i);
        } else {

            //if (this.mListener != null)
                this.mListener.onComplete(result.first);

        }
    }

    public EndpointsAsyncTask setListener(EndpointsTaskListener l) {
        this.mListener = l;
        return this;
    }

    public static interface EndpointsTaskListener {
        public void onComplete(String joke);
    }

}