package serialization_and_proxy;

public class ServiceImpl implements Service {

    @Override
    public long hardWork(String user, int number) {
        return number * 2;
    }
}
