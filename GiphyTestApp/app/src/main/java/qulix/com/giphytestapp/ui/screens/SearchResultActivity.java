package qulix.com.giphytestapp.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

public class SearchResultActivity extends SearchBaseActivity {

    private static final String QUERY_KEY = "query key";

    public static Intent intent(final Context context,
                                final String query) {
        final Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra(QUERY_KEY, query);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    private void handleIntent(final Intent intent) {
        if (intent == null) return;

        final String query = intent.getStringExtra(QUERY_KEY);
        if (query == null) return;

        displayGifsFromObservable(giphyApi().search(query));

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(query);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
