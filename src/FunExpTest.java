import analyticPath.FunExp;

import java.nio.DoubleBuffer;
import java.util.function.Function;

public class FunExpTest {
    public static void main(String[] args) {
        FunExp.Zero test = new FunExp.Zero();
        Function<Double, Double> f = test.getFun();

        System.out.println(f.apply(10.0));
    }
}
