package com.udacity.education.newsapp.requester;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.udacity.education.newsapp.domain.Feed;
import com.udacity.education.newsapp.processor.FeedProcessor;
import com.udacity.education.newsapp.util.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by dhiegoabrantes on 20/09/16.
 */
public class FeedFetcherAsyncTask extends AsyncTaskLoader<List<Feed>> {

    private List<Feed> mFeeds;

    public FeedFetcherAsyncTask(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mFeeds != null) {
            // Use cached data
            deliverResult(mFeeds);
        } else {
            // We have no data, so kick off loading it
            forceLoad();
        }
    }

    @Override
    public List<Feed> loadInBackground() {
        try {
            String urlAPI = "http://content.guardianapis.com/search?q=technology&api-key=2053c5d0-51de-4cba-af73-80ec2f4a3e69&show-fields=thumbnail";

            URL url = new URL(urlAPI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                List<Feed> feeds = FeedProcessor.process(sb.toString());
                return feeds;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
               closeResource(br);
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
            Log.e(Utils.LOG_TAG, ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public void deliverResult(List<Feed> feeds) {
        // We’ll save the data for later retrieval
        mFeeds = feeds;
        // We can do any pre-processing we want here
        // Just remember this is on the UI thread so nothing lengthy!
        super.deliverResult(mFeeds);
    }

    private void closeResource(BufferedReader br){
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
