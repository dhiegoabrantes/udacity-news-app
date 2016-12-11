package com.udacity.education.newsapp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.udacity.education.newsapp.adapter.FeedAdapter;
import com.udacity.education.newsapp.domain.Feed;
import com.udacity.education.newsapp.requester.AsyncTaskDelegator;
import com.udacity.education.newsapp.requester.FeedFetcherAsyncTask;
import com.udacity.education.newsapp.ui.recycler.DividerItemDecoration;
import com.udacity.education.newsapp.util.Utils;

public class MainActivity extends AppCompatActivity implements AsyncTaskDelegator {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private FeedAdapter mAdapter;
    private Feed feed;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(String.format("%s - %s", getString(R.string.app_name), getString(R.string.cathegory)));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewBooks);
        refreshButton = (Button) findViewById(R.id.refreshButton);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews(MainActivity.this);
            }
        });

        this.mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(this.mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        getNews(MainActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean keepList = true;
        getNews(MainActivity.this);
    }

    private void getNews(Context ctx) {
        if (Utils.checkConn(ctx)) {
            FeedFetcherAsyncTask service = new FeedFetcherAsyncTask(MainActivity.this, MainActivity.this);
            service.execute();
        } else {
            Toast.makeText(ctx, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show();
            findViewById(R.id.recyclerViewBooks).setVisibility(View.GONE);
            findViewById(R.id.painel_internet_connection_unavailable).setVisibility(View.VISIBLE);
        }
    }

    private void setupRecyclerAdapter(Feed feed) {
        mAdapter = new FeedAdapter(feed);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void processFinish(Object obj) {
        if (obj != null) {
            this.feed = (Feed) obj;
            setupRecyclerAdapter(this.feed);

            findViewById(R.id.recyclerViewBooks).setVisibility(View.VISIBLE);
            findViewById(R.id.painel_internet_connection_unavailable).setVisibility(View.GONE);
        }
    }

}
