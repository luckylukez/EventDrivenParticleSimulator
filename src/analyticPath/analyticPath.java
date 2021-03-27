package analyticPath;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.function.Function;

public class analyticPath {
    private LinkedList<BiFunExp> xPath = new LinkedList<>();
    private LinkedList<BiFunExp> yPath = new LinkedList<>();
    private LinkedList<Vector<Double>> intervals = new LinkedList<>();

    public analyticPath(BiFunExp xPath, BiFunExp yPath) {
        this.xPath.add(xPath);
        this.yPath.add(yPath);
    }

    public static Double findIntersect(analyticPath f1, analyticPath f2) {
        return null;
    }

    public static void collisionUpdate(analyticPath f1, analyticPath f2) {

    }

    public void addExpr(BiFunExp xExpr, BiFunExp yExpr) {
        this.xPath.add(xExpr);
        this.yPath.add(yExpr);
    }

    public void addInterval(Vector<Double> interval) {
        this.intervals.add(interval);
    }

    /*
    Evaluates path from start of interval to end of interval. Note that the intervals are interpreted as [a,b).

    Parameters:
    (int): Resolution, i.e. number of evaluated points between every whole t.

    Returns:
    (LinkedList<Double[]>): linked list with elements on form [x(t), x'(t), y(t), y'(t), t].
     */
    public LinkedList<Double[]> evalPath(double res) throws ClassNotFoundException {
        LinkedList<Double[]> numericPath = new LinkedList<>();
        for (int i = 0; i < this.intervals.size(); i++) {
            FunPair xFun      = this.xPath.get(i).eval(this.xPath.get(i)); // Eval expr to lambda fun
            FunPair yFun      = this.yPath.get(i).eval(this.yPath.get(i)); // Eval expr to lambda fun

            Double t = this.intervals.get(i).get(0);
            for (int j = 0; j < (int) (this.intervals.get(i).get(1) - this.intervals.get(i).get(0))*res; j++) {
                Double[] point = new Double[5];

                // Evaluates functions at time and stores to array
                point[0] = xFun.getFun().apply(t);
                point[1] = xFun.getDeriv().apply(t);
                point[2] = yFun.getFun().apply(t);
                point[3] = yFun.getDeriv().apply(t);
                point[4] = t;

                // Adds point to list and increments time
                numericPath.add(point);
                t += 1/res;
            }
        }
        return numericPath;
    }
}
