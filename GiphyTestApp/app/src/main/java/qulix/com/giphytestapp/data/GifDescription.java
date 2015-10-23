package qulix.com.giphytestapp.data;

import java.io.Serializable;

public final class GifDescription implements Serializable {

    public GifDescription(final String url) {
        if (url == null) throw new NullPointerException("url");

        mUrl = url;
    }

    public String url() {
        return mUrl;
    }

    private final String mUrl;

    @Override public String toString() {
        return "GifDescription{" +
            "mUrl = " + mUrl +
            "}";
    }
}
