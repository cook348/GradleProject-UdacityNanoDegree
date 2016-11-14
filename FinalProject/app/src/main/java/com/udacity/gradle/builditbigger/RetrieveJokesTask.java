package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by danielcook on 10/25/16.
 * Based on Template found at: https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
 */

public class RetrieveJokesTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private RetrieveJokeListener mListener;

    public interface RetrieveJokeListener {
        public void onComplete(String joke);
    }

    public RetrieveJokesTask (RetrieveJokeListener retrieveJokeListener){
        mListener = retrieveJokeListener;
    }

    @Override
    protected String doInBackground(Void ... params) {
        if(myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
//            // end options for devappserver

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://udacity-built-it-bigger.appspot.com/_ah/api/");

            myApiService = builder.build();

            //.setRootUrl("http://192.168.1.154:8080/_ah/api/")

        }

        try {
            return myApiService.retrieveJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        mListener.onComplete(result);

    }

}
