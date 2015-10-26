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

public class DetailsActivity extends AppCompatActivity {

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

        final SimpleDraweeView image = (SimpleDraweeView) findViewById(R.id.detailed_image);
        final View shareViaSMS = findViewById(R.id.share_via_sms);
        final View copyToClipboard = findViewById(R.id.copy_to_clipboard);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final Intent intent = getIntent();

        if (intent != null) {
            final GifDescription gif = (GifDescription) intent.getSerializableExtra(GIF_DESCRIPTION_EXTRA);

            if (gif != null) {
                image.setController(UiUtils.fromGifDescription(gif));

                shareViaSMS.setOnClickListener(view ->
                                                       Sharing.shareViaSms(this,
                                                                           gif));

                copyToClipboard.setOnClickListener(view ->
                                                           Sharing.copyToClipBoard(this,
                                                                                   gif));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
