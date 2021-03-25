import analyticPath.BiFunExp;
import analyticPath.analyticPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;
import java.util.function.Function;

public class FunExpTest {
    public static void main(String[] args) throws ClassNotFoundException {
        BiFunExp.Exp xt = new BiFunExp.Exp(new BiFunExp.Add(new BiFunExp.Exp(new BiFunExp.Pi()), new BiFunExp.X()));
        BiFunExp.Exp yt = new BiFunExp.Exp(new BiFunExp.Add(new BiFunExp.Exp(new BiFunExp.Pi()), new BiFunExp.Mul(new BiFunExp.X(), new BiFunExp.X())));
        Vector<Double> I = new Vector<>(Arrays.asList(0.0,10.0));

        analyticPath testPath = new analyticPath(xt, yt);
        testPath.addInterval(I);

        LinkedList<Double[]> result = testPath.evalPath(20);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("x = " + result.get(i)[0] + ", x' = " + result.get(i)[1] +
                        ", y = " + result.get(i)[2] + ", y' = " + result.get(i)[3] + ", t = " + result.get(i)[4]);
            }
        }


        /*
        System.out.println("f(1) = " + f.get(0).apply(1.0));
        System.out.println("f'(1) = " + f.get(1).apply(1.0));
        System.out.println("f(2) = " + f.get(0).apply(2.0));
        System.out.println("f'(2) = " + f.get(1).apply(2.0));
        System.out.println("f(3) = " + f.get(0).apply(3.0));
        System.out.println("f'(3) = " + f.get(1).apply(3.0));
        System.out.println("f(100) = " + f.get(0).apply(100.0));
        System.out.println("f'(100) = " + f.get(1).apply(100.0));

        System.out.println("g(1) = " + g.apply(1.0));
        System.out.println("g(2) = " + g.apply(2.0));
        System.out.println("g(3) = " + g.apply(3.0));
        System.out.println("g(100) = " + g.apply(100.0));
         */


    }
}
