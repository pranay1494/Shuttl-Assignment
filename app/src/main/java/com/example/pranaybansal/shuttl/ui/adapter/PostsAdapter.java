package com.example.pranaybansal.shuttl.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pranaybansal.shuttl.R;
import com.example.pranaybansal.shuttl.model.pojo.Dummy;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Pranay Bansal on 9/25/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TEXT_ONLY = 0;
    private static final int IMAGE_ONLY = 1;
    private static final int BOTH_IMAGE_TEXT = 2;

    private final ArrayList<Dummy> list;
    private final FeedClicked feedClicked;
    private Context context;

    public PostsAdapter(Context context, ArrayList<Dummy> dummies,FeedClicked feedClicked) {
        this.context = context;
        this.list = dummies;
        this.feedClicked = feedClicked;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TEXT_ONLY){
            return new TextOnlyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_text_only,parent,false));
        }else if (viewType == IMAGE_ONLY){
            return new ImageOnlyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_only,parent,false));
        }else {
            return new TextandImageVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_text,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        boolean showDate = false;
        showDate = checkDate(position);
        if (holder.getItemViewType() == IMAGE_ONLY){
            ImageOnlyVH imageOnlyVH = (ImageOnlyVH) holder;
            if (!showDate){
                imageOnlyVH.llDate.setVisibility(View.VISIBLE);
                imageOnlyVH.tvDate.setText(list.get(position).getTime());
            }else{
                imageOnlyVH.llDate.setVisibility(View.GONE);
            }
            imageOnlyVH.tvFrom.setText(context.getString(R.string.from)+" "+list.get(position).getName());
            imageOnlyVH.tvTitle.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getImageUrl()).into(imageOnlyVH.ivImageOnly);
            if (list.get(position).isLiked()){
                imageOnlyVH.tvLike.setText(context.getResources().getString(R.string.like));
                imageOnlyVH.tvLike.setBackground(context.getResources().getDrawable(R.drawable.likebtn));
                imageOnlyVH.tvLike.setTextColor(Color.WHITE);
            }else{
                imageOnlyVH.tvLike.setText(context.getResources().getString(R.string.UNLIKE));
                imageOnlyVH.tvLike.setBackground(context.getResources().getDrawable(R.drawable.unlikebtn));
                imageOnlyVH.tvLike.setTextColor(Color.WHITE);
            }
        }else if (holder.getItemViewType() == TEXT_ONLY){
            TextOnlyVH textOnlyVH = (TextOnlyVH) holder;
            if (!showDate){
                textOnlyVH.llDate.setVisibility(View.VISIBLE);
                textOnlyVH.tvDate.setText(list.get(position).getTime());
            }else{
                textOnlyVH.llDate.setVisibility(View.GONE);
            }
            textOnlyVH.tvFrom.setText(context.getString(R.string.from)+" "+list.get(position).getName());
            textOnlyVH.tvTitle.setText(list.get(position).getTitle());
            textOnlyVH.tvQuote.setText(list.get(position).getText());
            if (list.get(position).isLiked()){
                textOnlyVH.tvLike.setText(context.getResources().getString(R.string.like));
                textOnlyVH.tvLike.setBackground(context.getResources().getDrawable(R.drawable.likebtn));
                textOnlyVH.tvLike.setTextColor(Color.WHITE);
            }else{
                textOnlyVH.tvLike.setText(context.getResources().getString(R.string.UNLIKE));
                textOnlyVH.tvLike.setBackground(context.getResources().getDrawable(R.drawable.unlikebtn));
                textOnlyVH.tvLike.setTextColor(Color.WHITE);
            }
        }else{
            TextandImageVH textandImageVH = (TextandImageVH) holder;
            if (!showDate){
                textandImageVH.llDate.setVisibility(View.VISIBLE);
                textandImageVH.tvDate.setText(list.get(position).getTime());
            }else{
                textandImageVH.llDate.setVisibility(View.GONE);
            }
            textandImageVH.tvFrom.setText(context.getString(R.string.from)+" "+list.get(position).getName());
            textandImageVH.tvTitle.setText(list.get(position).getTitle());
            textandImageVH.tvInfo.setText(list.get(position).getText());
            Glide.with(context).load(list.get(position).getImageUrl()).into(textandImageVH.ivProfile);
            if (list.get(position).isLiked()){
                textandImageVH.tvLike.setText(context.getResources().getString(R.string.like));
                textandImageVH.tvLike.setBackground(context.getResources().getDrawable(R.drawable.likebtn));
                textandImageVH.tvLike.setTextColor(Color.WHITE);
            }else{
                textandImageVH.tvLike.setText(context.getResources().getString(R.string.UNLIKE));
                textandImageVH.tvLike.setBackground(context.getResources().getDrawable(R.drawable.unlikebtn));
                textandImageVH.tvLike.setTextColor(Color.WHITE);
            }
        }
    }

    private boolean checkDate(int position) {
        String prevDate="";
        String date = list.get(position).getTime();
        if (position!=0){
            prevDate = list.get(position-1).getTime();
        }
        if (prevDate.equalsIgnoreCase(date)){
            return true;
        }
        return false;
    }


    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(list.get(position).getText())){
            return IMAGE_ONLY;
        }else if (TextUtils.isEmpty(list.get(position).getImageUrl())){
            return TEXT_ONLY;
        }else{
            return BOTH_IMAGE_TEXT;
        }
    }

    public class TextOnlyVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.llDate) LinearLayout llDate;
        @BindView(R.id.llTextRow) LinearLayout llTextRow;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvLikeUnLike) TextView tvLike;
        @BindView(R.id.tvFrom) TextView tvFrom;
        @BindView(R.id.tvDate) TextView tvDate;
        @BindView(R.id.tvQuote) TextView tvQuote;
        public TextOnlyVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            tvLike.setOnClickListener(this);
            llTextRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tvLikeUnLike){
                if (list.get(getAdapterPosition()).isLiked()){
                    list.get(getAdapterPosition()).setLiked(false);
                }else{
                    list.get(getAdapterPosition()).setLiked(true);
                }
                notifyItemChanged(getAdapterPosition());
            }else{
                feedClicked.feedSelected(getAdapterPosition());
            }
        }
    }
    public class ImageOnlyVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.llDate) LinearLayout llDate;
        @BindView(R.id.llImageRow) LinearLayout llImageRow;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvLikeUnLike) TextView tvLike;
        @BindView(R.id.tvFrom) TextView tvFrom;
        @BindView(R.id.tvDate) TextView tvDate;
        @BindView(R.id.ivImageOnly) ImageView ivImageOnly;
        public ImageOnlyVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            tvLike.setOnClickListener(this);
            llImageRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tvLikeUnLike){
                if (list.get(getAdapterPosition()).isLiked()){
                    list.get(getAdapterPosition()).setLiked(false);
                }else{
                    list.get(getAdapterPosition()).setLiked(true);
                }
                notifyItemChanged(getAdapterPosition());
            }else{
                feedClicked.feedSelected(getAdapterPosition());
            }
        }
    }
    public class TextandImageVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.llDate) LinearLayout llDate;
        @BindView(R.id.llImgtxtRow) LinearLayout llImgtxtRow;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvLikeUnLike) TextView tvLike;
        @BindView(R.id.tvFrom) TextView tvFrom;
        @BindView(R.id.tvInfo) TextView tvInfo;
        @BindView(R.id.tvDate) TextView tvDate;
        @BindView(R.id.ivProfile) ImageView ivProfile;
        public TextandImageVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            tvLike.setOnClickListener(this);
            llImgtxtRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tvLikeUnLike) {
                if (list.get(getAdapterPosition()).isLiked()) {
                    list.get(getAdapterPosition()).setLiked(false);
                } else {
                    list.get(getAdapterPosition()).setLiked(true);
                }
                notifyItemChanged(getAdapterPosition());
            }else{
                feedClicked.feedSelected(getAdapterPosition());
            }
        }
    }

    public interface FeedClicked {
        void feedSelected(int position);
    }
}
