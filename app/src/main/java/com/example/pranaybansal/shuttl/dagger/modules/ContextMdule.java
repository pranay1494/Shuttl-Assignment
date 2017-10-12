package com.example.pranaybansal.shuttl.dagger.modules;

import android.app.Activity;
import android.content.Context;


import com.example.pranaybansal.shuttl.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pranay Bansal on 10/11/2017.
 */
@Module
public class ContextMdule {
    Context context;

    public ContextMdule(Activity activity) {
        context = activity;
    }

    @Provides
    @ActivityScope
    public Context activityContext() {
        return context;
    }
}
