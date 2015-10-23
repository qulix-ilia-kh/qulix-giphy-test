package qulix.com.giphytestapp.sharing;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import qulix.com.giphytestapp.R;
import qulix.com.giphytestapp.data.GifDescription;

public final class Sharing {
    private static final String SMS_PROTOCOL = "sms:";
    private static final String SMS_BODY_KEY = "sms_body";

    public static void shareViaSms(final Context context,
                                   final GifDescription gif) {
        final String message =
            context.getString(R.string.share_sms_prefix)
            + " "
            + gif.url();

        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(SMS_PROTOCOL));
        intent.putExtra(SMS_BODY_KEY, message);

        context.startActivity(intent);
    }

    public static void copyToClipBoard(final Context context,
                                       final GifDescription gif) {

        final ClipboardManager clipboard = (ClipboardManager)
            context.getSystemService(Context.CLIPBOARD_SERVICE);

        final ClipData clip = ClipData.newPlainText(context.getString(R.string.clipboard_text_description),
                                                    gif.url());

        clipboard.setPrimaryClip(clip);
    }
}