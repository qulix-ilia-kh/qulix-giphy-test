package qulix.com.giphytestapp.ui.screens;

import android.app.SearchManager;
import android.content.Context;
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

public class MainActivity extends AppCompatActivity {

    private static final String QUERY_KEY = "query key";

    private final List<GifDescription> mDataSet = new ArrayList<>();
    private final GifListAdapter mAdapter = new GifListAdapter(mDataSet, this::onSelected);
    private String mQueryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qulix_giphy_test);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gifs_list);
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            displayTrendingGifs();
        } else {
            mQueryString = savedInstanceState.getString(QUERY_KEY, "");
        }
    }

    private void updateDataSet(final List<GifDescription> gifPreviews) {
        mDataSet.clear();
        mDataSet.addAll(gifPreviews);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);

        // Associate searchable configuration with the SearchView
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (isEmpty(newText)) {
                    displayTrendingGifs();
                } else {
                    displaySearchedGifs(newText);
                }
                return true;
            }
        });
        searchView.setQuery(mQueryString, false);
        if (!isEmpty(mQueryString)) {
            searchView.setIconified(false);
        }

        return true;
    }

    private boolean isEmpty(final String newText) {
        return newText == null || newText.isEmpty();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(QUERY_KEY, mQueryString);
    }

    private void displayTrendingGifs() {
        mQueryString = "";
        displayGifsFromObservable(giphyApi().trendingGifs());
    }

    private void displaySearchedGifs(final String newText) {
        mQueryString = newText;
        displayGifsFromObservable(giphyApi().search(mQueryString));
    }

    private Api giphyApi() {
        return GiphyTestApplication.instance().api();
    }

    private void displayGifsFromObservable(Observable<GifDescription> observable) {
        observable
                .toList()
                .subscribe(MainActivity.this::updateDataSet,
                           MainActivity.this::notifyRequestError);
    }

    private void notifyRequestError(final Throwable error) {
        Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_LONG).show();
    }

    private void onSelected(final GifDescription preview) {
        startActivity(DetailsActivity.intent(this, preview));
    }

}
