package qulix.com.giphytestapp;


import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;

import qulix.com.giphytestapp.api.Api;

public final class GiphyTestApplication extends Application {

    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    private static final int MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB;
    private static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
    private static final String CACHE_DIR = "giphy-app-img-cache";

    private static GiphyTestApplication sInstance;

    private Api mApi;

    public GiphyTestApplication() {
        sInstance = this;
    }

    public void onCreate() {
        super.onCreate();

        final OkHttpClient okHttpClient = new OkHttpClient();

        initFresco(okHttpClient);

        mApi = new Api(okHttpClient);
    }

    private void initFresco(final OkHttpClient okHttpClient) {

        final MemoryCacheParams bitmapCacheParams
            = new MemoryCacheParams(MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                                    Integer.MAX_VALUE,                     // Max entries in the cache
                                    MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                                    Integer.MAX_VALUE,                     // Max length of eviction queue
                                    Integer.MAX_VALUE);                    // Max cache entry size

        final ImagePipelineConfig config
            = OkHttpImagePipelineConfigFactory
            .newBuilder(this, okHttpClient)
            .setBitmapMemoryCacheParamsSupplier(new Supplier<MemoryCacheParams>() {
                    public MemoryCacheParams get() {
                        return bitmapCacheParams;
                    }
                })
            .setMainDiskCacheConfig(DiskCacheConfig.newBuilder()
                                    .setBaseDirectoryPath(getCacheDir())
                                    .setBaseDirectoryName(CACHE_DIR)
                                    .setMaxCacheSize(MAX_DISK_CACHE_SIZE)
                                    .build())
            .build();

        Fresco.initialize(this, config);
    }

    public static GiphyTestApplication instance() {
        return sInstance;
    }

    public Api api() {
        return mApi;
    }

}