package com.example.pranaybansal.shuttl.ui.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.example.pranaybansal.shuttl.model.pojo.Dummy;
import com.example.pranaybansal.shuttl.ui.activity.DetailsActivity;
import com.example.pranaybansal.shuttl.ui.screen_contract.DetailsPresenter;
import com.example.pranaybansal.shuttl.utils.Constants;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * Created by Pranay Bansal on 10/12/2017.
 */

public class DetailsPresenterImpl implements DetailsPresenter {

    @Nullable
    private DetailsActivity view;

    @Inject
    public DetailsPresenterImpl() {
    }
    public void setView(DetailsActivity view){
        this.view = view;
    }

    @Override
    public void identifyViewType(Intent intent) {
        if (intent!=null){
            Dummy dummy = (Dummy) intent.getSerializableExtra(Constants.FEED);
            if (TextUtils.isEmpty(dummy.getText())){
                view.showParticularView(Constants.IMAGE);
            }else if (TextUtils.isEmpty(dummy.getImageUrl())){
                view.showParticularView(Constants.TEXT);
            }else{
                view.showParticularView(Constants.IMAGE_TEXT);
            }
        }
    }

}
