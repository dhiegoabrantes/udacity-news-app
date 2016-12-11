package com.udacity.education.newsapp.requester;

import android.content.Context;
import android.os.AsyncTask;

import com.udacity.education.newsapp.domain.Feed;
import com.udacity.education.newsapp.domain.ResponseData;
import com.udacity.education.newsapp.processor.FeedProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dhiegoabrantes on 20/09/16.
 */
public class FeedFetcherAsyncTask extends AsyncTask<Object, String, Feed> {

    private AsyncTaskDelegator delegate = null;

    public FeedFetcherAsyncTask(Context context, AsyncTaskDelegator responder){
        this.delegate = responder;
    }

    @Override
    protected Feed doInBackground(Object... objects) {
        try {
            String urlAPI = "https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&q=https://www.reddit.com/r/technology/.rss";
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
                ResponseData response = FeedProcessor.process(sb.toString());
                return response.getFeed();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
               closeResource(br);
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
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

    @Override
    protected void onPostExecute(Feed feed) {
        super.onPostExecute(feed);
        if(delegate != null)
            delegate.processFinish(feed);
    }
}
