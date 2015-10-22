package qulix.com.giphytestapp.functional;

public interface Function<R, A> {
    R call(A a);
}