package qulix.com.giphytestapp.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.data.GifDescription;
import qulix.com.giphytestapp.sharing.Sharing;
import qulix.com.giphytestapp.ui.UiUtils;

public final class DetailsActivity extends AppCompatActivity {

    private static final String GIF_DESCRIPTION_EXTRA = "gif_description_extra.da550ac1-744a-4e13-8f61-0e38a75ea16f";

    public static Intent intent(final Context context,
                                final GifDescription gif) {
        final Intent intent = new Intent(context, DetailsActivity.class);

        intent.putExtra(GIF_DESCRIPTION_EXTRA, gif);

        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final Intent intent = getIntent();

        if (intent == null) return;

        final GifDescription gif = (GifDescription) intent.getSerializableExtra(GIF_DESCRIPTION_EXTRA);

        if (gif == null) return;

        final SimpleDraweeView image = (SimpleDraweeView) findViewById(R.id.detailed_image);

        image.setController(UiUtils.fromGifDescription(gif));

        // init share via sms button
        findViewById(R.id.share_via_sms)
            .setOnClickListener(view ->
                                Sharing.shareViaSms(this,
                                                    gif));

        // init copy to clipboard button
        findViewById(R.id.copy_to_clipboard)
            .setOnClickListener(view ->
                                Sharing.copyToClipBoard(this,
                                                        gif));

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
