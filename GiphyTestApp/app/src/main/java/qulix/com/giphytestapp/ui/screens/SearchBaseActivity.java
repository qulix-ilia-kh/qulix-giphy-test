package qulix.com.giphytestapp.ui.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import qulix.com.giphytestapp.GiphyTestApplication;
import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.api.Api;
import qulix.com.giphytestapp.data.GifDescription;
import qulix.com.giphytestapp.ui.list.GifListAdapter;
import rx.Observable;
import rx.Subscription;

public abstract class SearchBaseActivity extends AppCompatActivity {

    private static final String STORED_QUERY_KEY = "stored query key";

    private final GifListAdapter mAdapter = new GifListAdapter(this::onSelected);

    private Subscription mCurrentSubscription;
    private String mQuery = "";
    private SearchView mSearchView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qulix_giphy_test);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gifs_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        if (savedInstanceState != null) {
            mQuery = savedInstanceState.getString(STORED_QUERY_KEY, "");
        }
    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);

        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                mSearchView.setQuery("", false);
                mSearchView.setIconified(true);
                startActivity(SearchResultActivity.intent(SearchBaseActivity.this, query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                return true;
            }
        });

        if (!mQuery.isEmpty()) {
            mSearchView.setQuery(mQuery, false);
            mSearchView.setIconified(false);
        }

        return true;
    }

    @Override
    protected final void onDestroy() {
        super.onDestroy();
        unsubscribeCurrent();
    }

    @Override
    protected final void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mSearchView != null) {
            outState.putString(STORED_QUERY_KEY, mSearchView.getQuery().toString());
        }
    }

    protected final Api giphyApi() {
        return GiphyTestApplication.instance().api();
    }

    protected final void displayGifsFromObservable(final Observable<GifDescription> observable) {
        unsubscribeCurrent();
        mCurrentSubscription
            = observable
            .toList()
            .subscribe(mAdapter::updateData,
                       this::notifyRequestError);
    }

    private void unsubscribeCurrent() {
        if (mCurrentSubscription != null) {
            mCurrentSubscription.unsubscribe();
            mCurrentSubscription = null;
        }
    }

    private void notifyRequestError(final Throwable error) {
        Toast.makeText(SearchBaseActivity.this, "Error: " + error, Toast.LENGTH_LONG).show();
    }

    private void onSelected(final GifDescription preview) {
        startActivity(DetailsActivity.intent(this, preview));
    }
}
