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
import qulix.com.giphytestapp.data.GifDescription;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Class representing access to the api.
 */
public final class Api {


    // endpoint urls
    private static final String TRENDING_GIFS_API_ENDPOINT = "http://api.giphy.com/v1/gifs/trending";
    private static final String SEARCH_GIFS_ENDPOINT = "http://api.giphy.com/v1/gifs/search";

    // keys
    private static final String KEY_API_KEY = "api_key";
    private static final String KEY_QUERY = "q";
    private static final String KEY_LIMIT = "limit";

    // how much search results to request
    private static final int SEARCH_RESULTS_TO_REQUEST = 50;
    // api key from the giphy samples
    private static final String VALUE_API_KEY = "dc6zaTOxFJmzC";

    // scheduler for server io
    private final Scheduler mIOScheduler = Schedulers.io();

    // http client to use
    private final OkHttpClient mClient;

    public Api(final OkHttpClient client) {
        mClient = client;
    }

    // get observable for trending gifs
    public Observable<GifDescription> trendingGifs() {
        return execute(url(TRENDING_GIFS_API_ENDPOINT),
                       GifsResponse.class)
            .flatMap(Api::toDescriptions);
    }

    // get observable for search results basing on term
    public Observable<GifDescription> search(final String term) {
        return execute(url(SEARCH_GIFS_ENDPOINT)
                       .newBuilder()
                       .addQueryParameter(KEY_QUERY, term)
                       .addQueryParameter(KEY_LIMIT, Integer.toString(SEARCH_RESULTS_TO_REQUEST))
                       .build(),
                       GifsResponse.class)
            .flatMap(Api::toDescriptions);
    }

    // extract gifs from GifResponse
    private static Observable<GifDescription> toDescriptions(final GifsResponse response) {
        return Observable
            .from(response.data())
            .map(entry -> new GifDescription(entry.image().url()));
    }

    // format url from endpoint
    private HttpUrl url(final String s) {
        return HttpUrl
            .parse(s)
            .newBuilder()
            .addQueryParameter(KEY_API_KEY, VALUE_API_KEY)
            .build();

    }

    // get result observable from the url
    private final <T> Observable<T> execute(final HttpUrl url,
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
            .subscribeOn(mIOScheduler)
            .observeOn(AndroidSchedulers.mainThread())
            .map(string -> new Gson().fromJson(string, expectedClass));
    }

    private final String readFromUrl(final HttpUrl url) throws IOException {
        final Request request = new Request.Builder()
            .url(url)
            .build();

        return mClient
            .newCall(request)
            .execute()
            .body()
            .string();
    }
}