package reflections;

public class CalculatorImpl implements Calculator {
    public int x;
    public String y;
    private int hiddenX;

    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "THURSDAY";
    public static final String THURSDAY = "";

    public int getHiddenX() {
        return hiddenX;
    }

    public void setHiddenX(int hiddenX) {
        this.hiddenX = hiddenX;
    }

    private void myPrivateMethod() {

    }

    @Override
    public int factorial(int arg) {
        {
            if (arg == 0) return 1;
            return arg * factorial(arg-1);
        }
    }
}
