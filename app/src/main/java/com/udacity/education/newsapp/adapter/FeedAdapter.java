package com.udacity.education.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.udacity.education.newsapp.R;
import com.udacity.education.newsapp.domain.Feed;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by dhiegoabrantes on 23/09/16.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int EMPTY_VIEW = 10;

    private final List<Feed> feed;

    public FeedAdapter(List<Feed> feed) {
        if(feed == null)
            feed = new ArrayList<>();
        this.feed = feed;
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
            final FeedViewHolder newsViewHolder = (FeedViewHolder) holder;
            final Feed feed = this.feed.get(position);
            newsViewHolder.title.setText( feed.getTitle() );
            newsViewHolder.section.setText( feed.getSectionName() );

            //Loading thumbnail
            final Context glideContext = newsViewHolder.imageView.getContext();

            Glide.with(glideContext).load(feed.getThumbnail()).asBitmap().centerCrop().into(new BitmapImageViewTarget(newsViewHolder.imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(glideContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    newsViewHolder.imageView.setImageDrawable(circularBitmapDrawable);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = feed.getUrl();
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
        if(this.feed != null)
            return this.feed.size() > 0 ? this.feed.size() : 1;
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(this.feed != null && this.feed.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView) public ImageView imageView;
        @BindView(R.id.newsTitle)public TextView title;
        @BindView(R.id.newsSection) public TextView section;

        public FeedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
