import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class AsyncTaskTest {

    @Test
    public void JokeTest() throws Exception {
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.execute(new Pair<Context, String>(null, "no-intent"));
        Thread.sleep(7000);
        String randomJoke = asyncTask.get();
        Log.d("AsyncTastTest", "Joke is: " + randomJoke);
            //assertNotNull(randomJoke);
        assertTrue("Fetched joke: " + randomJoke, randomJoke != null);


    }

}
