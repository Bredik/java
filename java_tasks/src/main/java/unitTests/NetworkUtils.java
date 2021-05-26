package unitTests;

public class NetworkUtils {
    public static void main(String[] args) {
        System.out.println("ssssss");
        getConnection();
        System.out.println("12321");
    }
    public static  void getConnection() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
