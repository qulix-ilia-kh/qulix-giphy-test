package qulix.com.giphytestapp.ui.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import qulix.com.giphytestapp.R;

public class DetailsActivity extends AppCompatActivity {

    private ImageView mDetailedImageView;
    private Button mShareViaSMS;
    private Button mCopyToClipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDetailedImageView = (ImageView) findViewById(R.id.detailed_image);
        mShareViaSMS = (Button) findViewById(R.id.share_via_sms);
        mCopyToClipboard = (Button) findViewById(R.id.copy_to_clipboard);

        mShareViaSMS.setOnClickListener(view -> shareViewSMS());
        mCopyToClipboard.setOnClickListener(view -> copyToClipboard());
    }

    private void copyToClipboard() {
        Toast.makeText(this, getString(R.string.copy_to_clipboard), Toast.LENGTH_LONG).show();
    }

    private void shareViewSMS() {
        Toast.makeText(this, getString(R.string.share_via_sms), Toast.LENGTH_LONG).show();
    }
}
