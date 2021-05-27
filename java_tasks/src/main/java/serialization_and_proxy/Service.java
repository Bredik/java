package serialization_and_proxy;

public interface Service {
    @CacheAnnotation
    long hardWork(String user, int number);
}
