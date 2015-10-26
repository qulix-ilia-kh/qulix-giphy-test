package qulix.com.giphytestapp.data;

import java.io.Serializable;

/**
 * This class represents one gif
 */
public final class GifDescription implements Serializable {

    public GifDescription(final String url) {
        if (url == null) throw new NullPointerException("url");

        mUrl = url;
    }

    // get url of this gif
    public String url() {
        return mUrl;
    }

    @Override
    public String toString() {
        return "GifDescription{" +
            "mUrl = " + mUrl +
            "}";
    }

    private final String mUrl;
}
