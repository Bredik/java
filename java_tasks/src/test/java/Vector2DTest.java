import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import unitTests.Vector2D;

public class Vector2DTest {
    private final double EPS = 1e-9; // 1e-9 = 0.000...0001
    private static Vector2D v1;

    /*
    @BeforeClass — ставится над методом — аналог @Before.
    Но этот метод вызывается лишь однажды перед всеми тестами для данного класса и поэтому должен быть статическим.
    Он используется для выполнения более тяжелых операций, как например подъем тестовой БД.
     */
    @BeforeClass
    public static void createNewVector() {
        v1 = new Vector2D();
    }

    @Test
    public void newVectorShouldHaveZeroLength() {
        //assertEquals(Object expected, Object actual) — проверяет, равны ли передаваемые обьекты.
        Assert.assertEquals(0, v1.length(), EPS);
    }

    @Test
    public void newVectorShouldHaveZeroX() {
        Assert.assertEquals(0, v1.getX(), EPS);
    }

    @Test
    public void newVectorShouldHaveZeroY() {
        Assert.assertEquals(0, v1.getY(), EPS);
    }

    @Test
    public void VectorShouldHaveLength() {
        v1.setX(4);
        v1.setY(5);
        Assert.assertEquals(6.4, v1.length(), 0.01);
    }
}
