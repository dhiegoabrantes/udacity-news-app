package com.udacity.education.newsapp.requester;

import android.content.Context;
import android.os.AsyncTask;

import com.udacity.education.newsapp.domain.Feed;
import com.udacity.education.newsapp.processor.FeedProcessor;

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
public class FeedFetcherAsyncTask extends AsyncTask<Object, String, List<Feed>> {

    private AsyncTaskDelegator delegate = null;

    public FeedFetcherAsyncTask(Context context, AsyncTaskDelegator responder){
        this.delegate = responder;
    }

    @Override
    protected List<Feed> doInBackground(Object... objects) {
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
    protected void onPostExecute(List<Feed> feeds) {
        super.onPostExecute(feeds);
        if(delegate != null)
            delegate.processFinish(feeds);
    }
}
