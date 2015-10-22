package qulix.com.giphytestapp.api;


import com.google.gson.Gson;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import qulix.com.giphytestapp.api.data.GifsResponse;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class Api {

    private static final Scheduler mIOScheduler = Schedulers.newThread();
    private static final OkHttpClient mClient = new OkHttpClient();

    private static final String KEY_API_KEY = "api_key";
    // api key from the giphy samples
    private static final String VALUE_API_KEY = "dc6zaTOxFJmzC";

    private static final String KEY_QUERY = "q";


    private static final String TRENDING_GIFS_API_ENDPOINT = "http://api.giphy.com/v1/gifs/trending";
    private static final String SEARCH_GIFS_ENDPOINT = "http://api.giphy.com/v1/gifs/search";

    private static final <T> Observable<T> execute(final HttpUrl url,
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

    private static final String readFromUrl(final HttpUrl url) throws IOException {
        final Request request = new Request.Builder()
            .url(url)
            .build();

        return mClient
            .newCall(request)
            .execute()
            .body()
            .string();
    }

    public Observable<GifsResponse> trendingGifs() {
        return execute(url(TRENDING_GIFS_API_ENDPOINT), GifsResponse.class);
    }

    public Observable<GifsResponse> search(final String term) {
        return execute(url(SEARCH_GIFS_ENDPOINT)
                       .newBuilder()
                       .addQueryParameter(KEY_QUERY, term)
                       .build(),
                       GifsResponse.class);
    }

    private HttpUrl url(final String s) {
        return HttpUrl
            .parse(s)
            .newBuilder()
            .addQueryParameter(KEY_API_KEY, VALUE_API_KEY)
            .build();

    }
}