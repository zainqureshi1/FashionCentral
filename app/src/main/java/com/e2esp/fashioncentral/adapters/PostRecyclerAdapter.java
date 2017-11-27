package com.e2esp.fashioncentral.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afrozaar.wp_api_v2_client_android.model.Media;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.e2esp.fashioncentral.R;
import com.e2esp.fashioncentral.interfaces.OnPostClickListener;
import com.e2esp.fashioncentral.utils.GlideApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Zain on 2/1/2017.
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Post> postsList;
    private OnPostClickListener onPostClickListener;

    public PostRecyclerAdapter(Context context, ArrayList<Post> postsList, OnPostClickListener onPostClickListener) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.postsList = postsList;
        this.onPostClickListener = onPostClickListener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_post_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bindView(postsList.get(position));
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        private View topView;
        @BindView(R.id.imageViewPost) ImageView imageView;
        @BindView(R.id.textViewPostTitle)TextView textViewTitle;
        @BindView(R.id.textViewPostContent) TextView textViewContent;

        PostViewHolder(View itemView) {
            super(itemView);
            topView = itemView;
            ButterKnife.bind(this, itemView);
        }

        void bindView(final Post post) {
            topView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPostClickListener.onPostClick(post);
                }
            });
            textViewTitle.setText(post.getTitle().getRendered());
            textViewContent.setText(post.getContent().getRendered());

            Media media = post.getMedia();
            if (media != null) {
                GlideApp.with(context).load(media.getSourceUrl()).into(imageView);
            } else {
                GlideApp.with(context).clear(imageView);
                imageView.setImageDrawable(null);
            }
        }

    }

}
