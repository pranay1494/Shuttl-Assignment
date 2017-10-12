package com.example.pranaybansal.shuttl.dagger.modules;



import com.example.pranaybansal.shuttl.dagger.scope.ActivityScope;
import com.example.pranaybansal.shuttl.model.ApiCalls;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pranay Bansal on 10/11/2017.
 */

@Module
public class ApiModule {

    String baseURL;
    public ApiModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    @ActivityScope
    public ApiCalls callApi(Retrofit retrofit){
        return retrofit.create(ApiCalls.class);
    }

    @Provides
    @ActivityScope
    public Retrofit retrofitClient(OkHttpClient client){
        return new Retrofit.Builder().baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

}
