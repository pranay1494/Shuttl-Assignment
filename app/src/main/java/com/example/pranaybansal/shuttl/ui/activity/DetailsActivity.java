package com.example.pranaybansal.shuttl.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pranaybansal.shuttl.R;
import com.example.pranaybansal.shuttl.dagger.component.ActivityComponent;
import com.example.pranaybansal.shuttl.dagger.component.DaggerActivityComponent;
import com.example.pranaybansal.shuttl.dagger.modules.ApiModule;
import com.example.pranaybansal.shuttl.dagger.modules.ContextMdule;
import com.example.pranaybansal.shuttl.model.pojo.Dummy;
import com.example.pranaybansal.shuttl.ui.presenter.DetailsPresenterImpl;
import com.example.pranaybansal.shuttl.ui.screen_contract.DetailsView;
import com.example.pranaybansal.shuttl.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pranay Bansal on 10/12/2017.
 */

public class DetailsActivity extends AppCompatActivity implements DetailsView,View.OnClickListener{
    @Inject
    DetailsPresenterImpl detailsPresenter;
    
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Dummy dummy;
    @BindView(R.id.llDetailsDate)
    LinearLayout llDate;
    @BindView(R.id.llImageOnly)
    LinearLayout llImageOnly;
    @BindView(R.id.llTitleRow)
    LinearLayout llTitleRow;
    @BindView(R.id.llTitleImage)
    LinearLayout llTitleImage;
    @BindView(R.id.llRowLikeListImage)
    LinearLayout llRowLikeListImage;
    @BindView(R.id.llRowLikeDetailsImage)
    LinearLayout llRowLikeDetailsImage;@BindView(R.id.llLiketxtImgRow)
    LinearLayout llLiketxtImgRow;@BindView(R.id.llLiketxtImgDetails)
    LinearLayout llLiketxtImgDetails;
    @BindView(R.id.llTextOnly)
    LinearLayout llTextOnly;
    @BindView(R.id.llImageText)
    LinearLayout llImageText;
    @BindView(R.id.llLikeTextDetails)
    LinearLayout llLikeTextDetails;
    @BindView(R.id.llLikeTextRow)
    LinearLayout llLikeTextRow;
    
    
    @BindView(R.id.tvLikeUnLike) TextView tvLike;
    @BindView(R.id.tvLikeUnLikeImage) TextView tvLikeUnLikeImage;
    @BindView(R.id.tvLikeUnLikeText) TextView tvLikeUnLikeText;
    @BindView(R.id.tvLikeUnLiketextimg) TextView tvLikeUnLiketextimg;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvFrom) TextView tvFrom;
    @BindView(R.id.tvFromImage) TextView tvFromImage;
    @BindView(R.id.tvFromText) TextView tvFromText;
    @BindView(R.id.tvFromtextimg) TextView tvFromtextimg;
    @BindView(R.id.tvInfo) TextView tvInfo;
    @BindView(R.id.tvDate) TextView tvDate;
    @BindView(R.id.ivProfile) ImageView ivProfile;
    @BindView(R.id.ivImageOnly) ImageView ivImageOnly;
    @BindView(R.id.tvQuote) TextView tvQuote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ActivityComponent component = DaggerActivityComponent.builder()
                .contextMdule(new ContextMdule(this))
                .apiModule(new ApiModule(""))
                .build();
        component.inject(this);
        detailsPresenter.setView(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        dummy = (Dummy) getIntent().getSerializableExtra(Constants.FEED);
        getSupportActionBar().setTitle(dummy.getTitle());
        
        detailsPresenter.identifyViewType(getIntent());
        tvLikeUnLikeImage.setOnClickListener(this);
        tvLikeUnLiketextimg.setOnClickListener(this);
        tvLikeUnLikeText.setOnClickListener(this);
    }

    @Override
    public void showParticularView(int viewType) {
        if (viewType == Constants.IMAGE){
            llImageOnly.setVisibility(View.VISIBLE);
            llRowLikeDetailsImage.setVisibility(View.VISIBLE);
            llRowLikeListImage.setVisibility(View.GONE);
            Glide.with(this).load(dummy.getImageUrl()).into(ivImageOnly);
            tvFromImage.setText(dummy.getName());
            if (dummy.isLiked()){
                tvLikeUnLikeImage.setText(getResources().getString(R.string.like));
                tvLikeUnLikeImage.setBackground(getResources().getDrawable(R.drawable.likebtn));
                tvLikeUnLikeImage.setTextColor(Color.WHITE);
            }else{
                tvLikeUnLikeImage.setText(getResources().getString(R.string.UNLIKE));
                tvLikeUnLikeImage.setBackground(getResources().getDrawable(R.drawable.unlikebtn));
                tvLikeUnLikeImage.setTextColor(Color.WHITE);
            }

        }else if ( viewType == Constants.TEXT){
            llTextOnly.setVisibility(View.VISIBLE);
            tvQuote.setText(dummy.getText());
            llLikeTextDetails.setVisibility(View.VISIBLE);
            llLikeTextRow.setVisibility(View.GONE);
            tvFromText.setText(dummy.getName());
            if (dummy.isLiked()){
                tvLikeUnLikeText.setText(getResources().getString(R.string.like));
                tvLikeUnLikeText.setBackground(getResources().getDrawable(R.drawable.likebtn));
                tvLikeUnLikeText.setTextColor(Color.WHITE);
            }else{
                tvLikeUnLikeText.setText(getResources().getString(R.string.UNLIKE));
                tvLikeUnLikeText.setBackground(getResources().getDrawable(R.drawable.unlikebtn));
                tvLikeUnLikeText.setTextColor(Color.WHITE);
            }

        }else{
            llImageText.setVisibility(View.VISIBLE);
            tvInfo.setText(dummy.getText());
            llLiketxtImgDetails.setVisibility(View.VISIBLE);
            llLiketxtImgRow.setVisibility(View.GONE);
            tvFromtextimg.setText(dummy.getName());
            if (dummy.isLiked()){
                tvLikeUnLiketextimg.setText(getResources().getString(R.string.like));
                tvLikeUnLiketextimg.setBackground(getResources().getDrawable(R.drawable.likebtn));
                tvLikeUnLiketextimg.setTextColor(Color.WHITE);
            }else{
                tvLikeUnLiketextimg.setText(getResources().getString(R.string.UNLIKE));
                tvLikeUnLiketextimg.setBackground(getResources().getDrawable(R.drawable.unlikebtn));
                tvLikeUnLiketextimg.setTextColor(Color.WHITE);
            }
            Glide.with(this).load(dummy.getImageUrl()).into(ivProfile);
        }
        setOtherData();
    }

    private void setOtherData() {
        tvDate.setText(dummy.getTime());
        tvDescription.setText(dummy.getDescription());
    }

    @Override
    public void onClick(View v) {
        dummy.setLiked(!dummy.isLiked());
        if (v.getId() == R.id.tvLikeUnLikeImage){
            if (dummy.isLiked()){
                tvLikeUnLikeImage.setText(getResources().getString(R.string.like));
                tvLikeUnLikeImage.setBackground(getResources().getDrawable(R.drawable.likebtn));
                tvLikeUnLikeImage.setTextColor(Color.WHITE);
            }else{
                tvLikeUnLikeImage.setText(getResources().getString(R.string.UNLIKE));
                tvLikeUnLikeImage.setBackground(getResources().getDrawable(R.drawable.unlikebtn));
                tvLikeUnLikeImage.setTextColor(Color.WHITE);
            }
        }else if (v.getId() == R.id.tvLikeUnLikeText){
            if (dummy.isLiked()){
                tvLikeUnLikeText.setText(getResources().getString(R.string.like));
                tvLikeUnLikeText.setBackground(getResources().getDrawable(R.drawable.likebtn));
                tvLikeUnLikeText.setTextColor(Color.WHITE);
            }else{
                tvLikeUnLikeText.setText(getResources().getString(R.string.UNLIKE));
                tvLikeUnLikeText.setBackground(getResources().getDrawable(R.drawable.unlikebtn));
                tvLikeUnLikeText.setTextColor(Color.WHITE);
            }
        }else{
            if (dummy.isLiked()){
                tvLikeUnLiketextimg.setText(getResources().getString(R.string.like));
                tvLikeUnLiketextimg.setBackground(getResources().getDrawable(R.drawable.likebtn));
                tvLikeUnLiketextimg.setTextColor(Color.WHITE);
            }else{
                tvLikeUnLiketextimg.setText(getResources().getString(R.string.UNLIKE));
                tvLikeUnLiketextimg.setBackground(getResources().getDrawable(R.drawable.unlikebtn));
                tvLikeUnLiketextimg.setTextColor(Color.WHITE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DUMMY,dummy);
        setResult(Constants.REQ_FEEDS,intent);
        finish();
    }
}
