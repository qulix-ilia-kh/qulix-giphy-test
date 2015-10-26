package qulix.com.giphytestapp.api.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents response from server
 */
public final class GifsResponse {

    public static final class GifEntry {

        public static final class GifImages {
            private GifImage original;

            public GifImage original() {
                return original;
            }
        }

        public static final class GifImage {
            private String url;

            public String url() {
                return url;
            }
        }

        public GifImage image() {
            return images.original();
        }

        private GifImages images;

    }

    // images
    private List<GifEntry> data;

    public List<GifEntry> data() {
        // copy to avoid mutability
        return new ArrayList<>(data);
    }
}