import analyticPath.BiFunExp;
import analyticPath.analyticPath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

public class FunExpTest {
    public static void main(String[] args) throws ClassNotFoundException {
        BiFunExp x1t = new BiFunExp.Add(new BiFunExp.Mul(new BiFunExp.X(), new BiFunExp.X()), new BiFunExp.Mul(new BiFunExp.Con(3.0), new BiFunExp.X()));
        BiFunExp y1t = new BiFunExp.Add(new BiFunExp.Mul(new BiFunExp.X(), new BiFunExp.Mul(new BiFunExp.X(), new BiFunExp.X())), new BiFunExp.Mul(new BiFunExp.One(), new BiFunExp.X()));

        BiFunExp x2t = new BiFunExp.Add(new BiFunExp.Add(new BiFunExp.Mul(new BiFunExp.X(), new BiFunExp.X()), new BiFunExp.Mul(new BiFunExp.Con(2.0), new BiFunExp.X())), new BiFunExp.Con(4.0));
        BiFunExp y2t = new BiFunExp.Add(new BiFunExp.Mul(new BiFunExp.X(), new BiFunExp.Mul(new BiFunExp.X(), new BiFunExp.X())), new BiFunExp.Mul(new BiFunExp.X(), new BiFunExp.X()));

        analyticPath p1 = new analyticPath(x1t, y1t);
        analyticPath p2 = new analyticPath(x2t, y2t);

        Vector<Double> I = new Vector<>(Arrays.asList(0.0,60.0));
        p1.addInterval(I);
        p2.addInterval(I);

        double intersectTime = analyticPath.findIntersect(p1, p2, 2, 0, 60, 0.1);
        System.out.println("time of collision: " + intersectTime);

    }
}
