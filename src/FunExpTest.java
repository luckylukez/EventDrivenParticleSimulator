import analyticPath.FunExp;

import java.nio.DoubleBuffer;
import java.util.function.Function;

public class FunExpTest {
    public static void main(String[] args) throws ClassNotFoundException {
        FunExp.Exp test = new FunExp.Exp(new FunExp.Add(new FunExp.Exp(new FunExp.Pi()), new FunExp.X()));
        Function<Double, Double> f = test.eval(test);
        Function<Double, Double> g = x -> Math.exp(Math.exp(Math.PI) + x);

        System.out.println("f(1) = " + f.apply(1.0));
        System.out.println("f(2) = " + f.apply(2.0));
        System.out.println("f(3) = " + f.apply(3.0));
        System.out.println("f(100) = " + f.apply(100.0));

        System.out.println("g(1) = " + g.apply(1.0));
        System.out.println("g(2) = " + g.apply(2.0));
        System.out.println("g(3) = " + g.apply(3.0));
        System.out.println("g(100) = " + g.apply(100.0));


    }
}
