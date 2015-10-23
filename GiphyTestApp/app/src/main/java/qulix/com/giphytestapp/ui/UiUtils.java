package qulix.com.giphytestapp.ui;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;

import qulix.com.giphytestapp.data.GifDescription;

public final class UiUtils {
    public static DraweeController fromGifDescription(final GifDescription gif) {
        final Uri uri = Uri.parse(gif.url());

        return Fresco.newDraweeControllerBuilder()
            .setUri(uri)
            .setAutoPlayAnimations(true)
            .build();
    }
}