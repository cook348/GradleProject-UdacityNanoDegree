package com.udacity.gradle.builditbigger;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;

/**
 * Created by danielcook on 10/28/16.
 * To test that the RetrieveJokesTask returns a non empty string
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class RetrieveJokesTaskTest {

    @Test
    public void testRetrieveJokeTask(){
        new RetrieveJokesTask(new retrieverListener()).execute();
    }

    public class retrieverListener implements RetrieveJokesTask.RetrieveJokeListener{
        @Override
        public void onComplete(String joke) {
            assertFalse(TextUtils.isEmpty(joke));
        }
    }
}