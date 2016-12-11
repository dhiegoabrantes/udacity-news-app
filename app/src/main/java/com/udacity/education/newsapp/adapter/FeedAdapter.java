package com.udacity.education.newsapp.adapter;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.udacity.education.newsapp.R;
import com.udacity.education.newsapp.domain.Feed;
import com.udacity.education.newsapp.domain.FeedEntry;
import com.udacity.education.newsapp.ui.recycler.OnItemClickListener;


/**
 * Created by dhiegoabrantes on 23/09/16.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private static final int EMPTY_VIEW = 10;

    private final Feed feed;

    public FeedAdapter(Feed feed) {
        this.feed = feed != null ? feed : new Feed();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        if (viewType == EMPTY_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
            EmptyViewHolder evh = new EmptyViewHolder(v);
            return evh;
        }

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_item, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FeedViewHolder){
            FeedViewHolder newsViewHolder = (FeedViewHolder) holder;
            final FeedEntry feedEntry = this.feed.getEntries().get(position);
            newsViewHolder.title.setText( feedEntry.getTitle() );
            newsViewHolder.contentSnippet.setText( feedEntry.getContentSnippet() );
            newsViewHolder.author.setText( feedEntry.getAuthor() );

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = feedEntry.getLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    v.getContext().startActivity(i);
                }
            });
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if(this.feed != null && this.feed.getEntries() != null)
            return this.feed.getEntries().size() > 0 ? this.feed.getEntries().size() : 1;
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(this.feed != null && this.feed.getEntries() != null && this.feed.getEntries().size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder{
        public Context context;
        public TextView title;
        public TextView contentSnippet;
        public TextView author;
//        public TextView authors;
//        public ImageView thumbnail;

        public FeedViewHolder(View view) {
            super(view);
            context = view.getContext();
            title = (TextView) view.findViewById(R.id.newsTitle);
            contentSnippet = (TextView) view.findViewById(R.id.newsContentSnippet);
            author = (TextView) view.findViewById(R.id.newsAuthor);
//            authors = (TextView) view.findViewById(R.id.authors);
//            thumbnail = (ImageView) view.findViewById(R.id.bookThumbnail);

        }
    }
}
