package qulix.com.giphytestapp.data;

public final class GifDescription {
    public GifDescription(final String caption) {
        mCaption = caption;

    }

    public String caption() {
        return mCaption;
    }


    private final String mCaption;


    @Override public String toString() {
        return "GifDescription{" +
            "mCaption = " + mCaption +
            "}";
    }

    public static final class Builder {

        private String mCaption;


        public Builder() {}
        public Builder(final GifDescription originalObject) {
            mCaption = originalObject.mCaption;

        }

        public Builder setCaption(final String caption) {
            mCaption = caption;
            return this;
        }

        public GifDescription build() {
            return new GifDescription(mCaption);
        }
    }
}
