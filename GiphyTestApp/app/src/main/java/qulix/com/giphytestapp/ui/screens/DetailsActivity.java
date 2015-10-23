package qulix.com.giphytestapp.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.data.GifDescription;
import qulix.com.giphytestapp.ui.UiUtils;

public class DetailsActivity extends AppCompatActivity {

    private SimpleDraweeView mImage;
    private View mShareViaSMS;
    private View mCopyToClipboard;

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

        mImage = (SimpleDraweeView) findViewById(R.id.detailed_image);
        mShareViaSMS = findViewById(R.id.share_via_sms);
        mCopyToClipboard = findViewById(R.id.copy_to_clipboard);

        mShareViaSMS.setOnClickListener(view -> shareViewSMS());
        mCopyToClipboard.setOnClickListener(view -> copyToClipboard());


        final Intent intent = getIntent();

        if (intent != null) {
            final GifDescription gif = (GifDescription)intent.getSerializableExtra(GIF_DESCRIPTION_EXTRA);

            if (gif != null) {
                mImage.setController(UiUtils.fromGifDescription(gif));
            }
        }
    }

    private void copyToClipboard() {
        Toast.makeText(this, getString(R.string.copy_to_clipboard), Toast.LENGTH_LONG).show();
    }

    private void shareViewSMS() {
        Toast.makeText(this, getString(R.string.share_via_sms), Toast.LENGTH_LONG).show();
    }
}
