package qulix.com.giphytestapp.ui.screens;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Arrays;

import qulix.com.giphytestapp.GiphyTestApplication;
import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.api.Api;
import qulix.com.giphytestapp.api.data.GifsResponse;
import qulix.com.giphytestapp.data.GifDescription;
import qulix.com.giphytestapp.functional.Factory;
import qulix.com.giphytestapp.ui.list.GifListAdapter;
import rx.Observable;

public class MainActivity extends AppCompatActivity {

    private final Factory<Observable<GifDescription>> mDataFactory;
    private RecyclerView mRecyclerView;

    public MainActivity() {
        this(() -> GiphyTestApplication.instance().api().trendingGifs());
    }

    public MainActivity(final Factory<Observable<GifDescription>> dataFactory) {
        mDataFactory = dataFactory;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qulix_giphy_test);

        mRecyclerView = (RecyclerView) findViewById(R.id.gifs_list);
        mRecyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mDataFactory
            .get()
            .toList()
            .subscribe(gifPreviews -> {
                    final GifListAdapter adapter = new GifListAdapter(gifPreviews,
                                                                      this::onSelected);
                    mRecyclerView.setAdapter(adapter);
                },
                error -> {
                    Toast.makeText(this, "Error: " + error, Toast.LENGTH_LONG).show();
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);

        // Associate searchable configuration with the SearchView
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private void onSelected(final GifDescription preview) {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_LONG).show();
        }
    }

}
