package qulix.com.giphytestapp;

import android.support.annotation.NonNull;

public class GifPreview {

    private final String mPreviewUrl;

    private final String mPreviewName;

    public GifPreview(
            @NonNull final String mPreviewUrl,
            @NonNull final String mPreviewName) {
        this.mPreviewUrl = mPreviewUrl;
        this.mPreviewName = mPreviewName;
    }

    public String previewName() {
        return mPreviewName;
    }

    public String previewUrl() {
        return mPreviewUrl;
    }
}
