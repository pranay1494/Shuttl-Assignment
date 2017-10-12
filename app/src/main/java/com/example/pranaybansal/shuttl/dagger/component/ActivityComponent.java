package com.example.pranaybansal.shuttl.dagger.component;


import com.example.pranaybansal.shuttl.ui.activity.DetailsActivity;
import com.example.pranaybansal.shuttl.ui.activity.MainActivity;
import com.example.pranaybansal.shuttl.dagger.modules.ApiModule;
import com.example.pranaybansal.shuttl.dagger.modules.ContextMdule;
import com.example.pranaybansal.shuttl.dagger.modules.ImageLoaderModule;
import com.example.pranaybansal.shuttl.dagger.modules.NetworkModules;
import com.example.pranaybansal.shuttl.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Pranay Bansal on 10/11/2017.
 */
@Component(modules = {ApiModule.class,NetworkModules.class, ContextMdule.class, ImageLoaderModule.class})
@ActivityScope
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailsActivity detailsActivity);
}
