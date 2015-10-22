package qulix.com.giphytestapp.observables;


import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class TrendingGifs {

    private final Scheduler mIOScheduler = Schedulers.newThread();
    private final OkHttpClient mClient = new OkHttpClient();

    private final String TRENDING_GIFS_API_ENDPOINT = "http://api.giphy.com/v1/gifs/trending?api_key=dc6zaTOxFJmzC";


    private final <T> Observable<T> execute(final String url,
                                            final Class<T> expectedClass) {
        return Observable.<String>create(subscriber -> {
                try {
                    final String data = readFromUrl(url);

                    subscriber.onNext(data);

                    subscriber.onCompleted();
                } catch (final IOException e) {
                    subscriber.onError(e);
                }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map(string -> new Gson().fromJson(string, expectedClass));
    }

    private final String readFromUrl(final String url) throws IOException {
        final Request request = new Request.Builder()
            .url(url)
            .build();

        return mClient
            .newCall(request)
            .execute()
            .body()
            .string();
    }

    public static final class TrendingGifsResponse {

        public static final class GifEntry {

            public static final class GifImages {
                private GifImage original;

                public GifImage original() {
                    return original;
                }
            }

            public static final class GifImage {
                private String url;

                public String url() {
                    return url;
                }
            }

            public String caption() {
                return caption;
            }

            public GifImage image() {
                return images.original();
            }

            private String caption;
            private GifImages images;

        }

        private List<GifEntry> data;

        public List<GifEntry> data() {
            return new ArrayList<>(data);
        }
    }

    public Observable<TrendingGifsResponse> trendingGifs() {
        return execute(TRENDING_GIFS_API_ENDPOINT, TrendingGifsResponse.class);
    }
}