package qulix.com.giphytestapp.ui.screens;

import android.os.Bundle;

public final class MainActivity extends SearchBaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayTrendingGifs();
    }

    protected void displayTrendingGifs() {
        displayGifsFromObservable(giphyApi().trendingGifs());
    }

}
