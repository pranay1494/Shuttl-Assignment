package com.example.pranaybansal.shuttl.ui.presenter;

import android.util.Log;

import com.example.pranaybansal.shuttl.comparator.DateComparator;
import com.example.pranaybansal.shuttl.model.pojo.Dummy;
import com.example.pranaybansal.shuttl.model.pojo.DummyList;
import com.example.pranaybansal.shuttl.ui.activity.MainActivity;
import com.example.pranaybansal.shuttl.ui.screen_contract.PostsPresenter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * Created by Pranay Bansal on 10/12/2017.
 */

public class PostsPresenterImpl implements PostsPresenter {

    @Nullable
    private MainActivity view;
    private List<Dummy> list;

    @Inject
    public PostsPresenterImpl() {
    }

    public void setView(MainActivity view){
        this.view = view;
    }
    @Override
    public void fetchData() {
        view.showLoader();
        String response = readJSONFromAsset();
        parseResponse(response);
        view.dismissDialog();
        view.updateRecyclerView(list);
    }

    private void parseResponse(String response) {
        Dummy[] posts = new Gson().fromJson(response,Dummy[].class);
        list = Arrays.asList(posts);
        Collections.sort(list,new DateComparator());
        convertMillisToDate();
    }

    private void convertMillisToDate() {
        for (Dummy dummy:list) {
            String x = dummy.getTime();

            DateFormat formatter = new SimpleDateFormat("d MMMM yyyy");

            long milliSeconds= Long.parseLong(x);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            Log.d("date",formatter.format(calendar.getTime()));
            dummy.setTime(formatter.format(calendar.getTime()));
            dummy.setLiked(true);
        }
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            if (view!=null) {
                InputStream is = view.getAssets().open("dummy.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
