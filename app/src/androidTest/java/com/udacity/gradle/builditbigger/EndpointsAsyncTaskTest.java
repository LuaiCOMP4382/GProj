package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.util.Pair;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

public class EndpointsAsyncTaskTest {

    CountDownLatch signal = null;
    private String mJoke = null;

    @Before
    public void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @After
    public void tearDown() throws Exception {
        signal.countDown();
    }

    @Test
    public void JokeTest() throws InterruptedException {

        //signal = new CountDownLatch(1);

        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.setListener(new EndpointsAsyncTask.EndpointsTaskListener() {
            @Override
            public void onComplete(String joke) {
                mJoke = joke;
                signal.countDown();
            }
        }).execute(new Pair<Context, String>(null, "no-intent"));

        signal.await();

        assertNotNull("mJoke");
        assertTrue("Fetched Joke : " + mJoke, !mJoke.isEmpty());

        //signal.countDown();

    }

}