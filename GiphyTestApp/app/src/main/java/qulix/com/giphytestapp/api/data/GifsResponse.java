package qulix.com.giphytestapp.api.data;

import java.util.ArrayList;
import java.util.List;

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

        public String caption() {
            return caption;
        }

        public GifImage image() {
            return images.original();
        }

        private String caption;
        private GifImages images;

    }

    private List<GifEntry> data;

    public List<GifEntry> data() {
        return new ArrayList<>(data);
    }
}