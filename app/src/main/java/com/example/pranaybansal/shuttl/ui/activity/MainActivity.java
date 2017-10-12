package com.example.pranaybansal.shuttl.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.pranaybansal.shuttl.R;
import com.example.pranaybansal.shuttl.dagger.component.ActivityComponent;
import com.example.pranaybansal.shuttl.dagger.component.DaggerActivityComponent;
import com.example.pranaybansal.shuttl.dagger.modules.ApiModule;
import com.example.pranaybansal.shuttl.dagger.modules.ContextMdule;
import com.example.pranaybansal.shuttl.model.pojo.Dummy;
import com.example.pranaybansal.shuttl.ui.adapter.PostsAdapter;
import com.example.pranaybansal.shuttl.ui.presenter.PostsPresenterImpl;
import com.example.pranaybansal.shuttl.ui.screen_contract.PostsView;
import com.example.pranaybansal.shuttl.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PostsView,PostsAdapter.FeedClicked {

    @BindView(R.id.rv_feeds)
    RecyclerView rvFeeds;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindString(R.string.shuttl)
    String strToolBar;

    @Inject
    PostsPresenterImpl postsPresenter;

    private PostsAdapter postsAdapter;
    private ProgressDialog dialog;
    private ArrayList<Dummy> dummyArrayList;
    private int savedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityComponent component = DaggerActivityComponent.builder()
                .contextMdule(new ContextMdule(this))
                .apiModule(new ApiModule(""))
                .build();
        component.inject(this);
        postsPresenter.setView(this);

        dialog = new ProgressDialog(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(strToolBar);

        setupRecyclerView();
        postsPresenter.fetchData();
    }

    private void setupRecyclerView() {
        rvFeeds.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        postsAdapter = new PostsAdapter(this,new ArrayList<Dummy>(),this);
        rvFeeds.setAdapter(postsAdapter);
    }

    @Override
    public void updateRecyclerView(List<Dummy> list) {
        dummyArrayList = new ArrayList<>(list);
        postsAdapter = new PostsAdapter(this,dummyArrayList,this);
        rvFeeds.setAdapter(postsAdapter);
        postsAdapter.notifyDataSetChanged();
    }

    @Override
    public void dismissDialog() {
        dialog.dismiss();
    }

    @Override
    public void showLoader() {
        dialog.setMessage("Loading Please Wait..");
        dialog.show();
    }

    @Override
    public void feedSelected(int position) {
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(this,DetailsActivity.class);
        intent.putExtra(Constants.FEED,dummyArrayList.get(position));
        savedPosition = position;
        startActivityForResult(intent,Constants.REQ_FEEDS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQ_FEEDS){
            if (data!=null){
                if (savedPosition != -1){
                    Dummy dummy = (Dummy) data.getSerializableExtra(Constants.DUMMY);
                    dummyArrayList.set(savedPosition,dummy);
                    postsAdapter.notifyItemChanged(savedPosition);
                }
            }
        }
    }
}
