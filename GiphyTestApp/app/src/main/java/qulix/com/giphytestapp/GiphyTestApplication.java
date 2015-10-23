package qulix.com.giphytestapp;


import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;

import qulix.com.giphytestapp.api.Api;

public final class GiphyTestApplication extends Application {
    private static GiphyTestApplication sInstance;

    private Api mApi;

    public GiphyTestApplication() {
        sInstance = this;
    }

    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient();

        final ImagePipelineConfig config =
            OkHttpImagePipelineConfigFactory
            .newBuilder(this, okHttpClient)
            .build();

        Fresco.initialize(this, config);

        mApi = new Api(okHttpClient);
    }

    public static GiphyTestApplication instance() {
        return sInstance;
    }

    public Api api() {
        return mApi;
    }

}