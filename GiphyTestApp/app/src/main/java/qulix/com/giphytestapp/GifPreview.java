package qulix.com.giphytestapp;

import android.support.annotation.NonNull;

public class GifPreview {

    private final String mPreviewUrl;

    private final String mPreviewName;

    public GifPreview(
            @NonNull final String previewUrl,
            @NonNull final String previewName) {
        mPreviewUrl = previewUrl;
        mPreviewName = previewName;
    }

    public String previewName() {
        return mPreviewName;
    }

    public String previewUrl() {
        return mPreviewUrl;
    }
}
