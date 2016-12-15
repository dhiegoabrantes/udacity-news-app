package com.udacity.education.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.udacity.education.newsapp.adapter.FeedAdapter;
import com.udacity.education.newsapp.domain.Feed;
import com.udacity.education.newsapp.requester.FeedFetcherAsyncTask;
import com.udacity.education.newsapp.ui.recycler.DividerItemDecoration;
import com.udacity.education.newsapp.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Feed>>  {

    @BindView(R.id.recyclerViewBooks) RecyclerView recyclerView;
    @BindView(R.id.refreshButton) Button refreshButton;

    private LinearLayoutManager mLayoutManager;
    private FeedAdapter mAdapter;
    private List<Feed> feeds;

    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(String.format("%s - %s", getString(R.string.app_name), getString(R.string.cathegory)));

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            initLoader(MainActivity.this);
            }
        });

        this.mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(this.mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        initLoader(MainActivity.this);
    }

    private void initLoader(Context context){
        if (Utils.checkConn(context)) {
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            Toast.makeText(context, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show();
            findViewById(R.id.recyclerViewBooks).setVisibility(View.GONE);
            findViewById(R.id.painel_internet_connection_unavailable).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLoader(MainActivity.this);
    }

    private void setupRecyclerAdapter(List<Feed> feeds) {
        mAdapter = new FeedAdapter(feeds);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void reDrawRecyclerView() {
        if(feeds == null) {
            findViewById(R.id.recyclerViewBooks).setVisibility(View.GONE);
            findViewById(R.id.painel_internet_connection_unavailable).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.recyclerViewBooks).setVisibility(View.VISIBLE);
            findViewById(R.id.painel_internet_connection_unavailable).setVisibility(View.GONE);
        }
    }

    @Override
    public Loader<List<Feed>> onCreateLoader(int id, Bundle args) {
        return new FeedFetcherAsyncTask(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Feed>> loader, List<Feed> data) {
        this.feeds = data;
        setupRecyclerAdapter(this.feeds);
        reDrawRecyclerView();
    }

    @Override
    public void onLoaderReset(Loader<List<Feed>> loader) {
        this.feeds = new ArrayList<>();
        setupRecyclerAdapter(this.feeds);
        reDrawRecyclerView();
    }

}
