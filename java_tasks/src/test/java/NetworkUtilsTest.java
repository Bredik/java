import org.junit.Test;
import unitTests.NetworkUtils;

public class NetworkUtilsTest {

    // @Test(timeout=100) — проверяет, что метод исполняется не более чем 100 миллисекунд.
    @Test(timeout = 2000)
    public void getConnectionShouldBeLess1second() {
        NetworkUtils.getConnection();
    }
}
