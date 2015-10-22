package qulix.com.giphytestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Arrays;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements GifPreviewViewHolder.ClickListener {

    private RecyclerView mRecyclerView;

    private static final GifPreview DUMMY = new GifPreview("https://upload.wikimedia.org/wikipedia/en/f/f4/Fudge_bunny_rules_disco_diva.gif", "Fudge bunny rules disco");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qulix_giphy_test);

        mRecyclerView = (RecyclerView) findViewById(R.id.gifs_list);
        mRecyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        Observable.just(Arrays.asList(DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY, DUMMY))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gifPreviews -> {
                    final GifListAdapter adapter = new GifListAdapter(gifPreviews, this);
                    mRecyclerView.setAdapter(adapter);
                });
    }

    @Override
    public void onGifPreviewClicked(final GifPreview preview) {
        Toast.makeText(this, preview.previewName() + " clicked", Toast.LENGTH_LONG).show();
    }
}
