package com.example.pranaybansal.shuttl.ui.screen_contract;

import com.example.pranaybansal.shuttl.model.pojo.Dummy;

import java.util.List;

/**
 * Created by Pranay Bansal on 10/12/2017.
 */

public interface PostsView {
    void updateRecyclerView(List<Dummy> list);
    void dismissDialog();
    void showLoader();
}
