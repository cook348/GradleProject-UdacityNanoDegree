package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cookapps.jokeactivitylibrary.DisplayJokeActivity;


public class MainActivity extends ActionBarActivity implements RetrieveJokesTask.RetrieveJokeListener {

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        mProgress = ProgressDialog.show(this, getString(R.string.please_wait), getString(R.string.joke_loading));
        new RetrieveJokesTask(this).execute();

    }


    @Override
    public void onComplete(String joke) {
        mProgress.dismiss();
        Intent intent = new Intent(this, DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeActivity.EXTRA_JOKE, joke);
        this.startActivity(intent);
    }
}
