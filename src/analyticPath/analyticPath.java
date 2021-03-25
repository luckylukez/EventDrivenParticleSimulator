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
    Evaluates path from start of interval to end of interval.

    Parameters:
    (int): Resolution, i.e. number of evaluated points between every whole t.

    Returns:
    (LinkedList<Double[]>): linked list with elements on form [x(t), x'(t), y(t), y'(t), t].
     */
    public LinkedList<Double[]> evalPath(int res) throws ClassNotFoundException {
        LinkedList<Double[]> numericPath = new LinkedList<>();
        for (int i = 0; i < this.intervals.size(); i++) {
            ArrayList<Function<Double, Double>> xFun      = this.xPath.get(i).eval(this.xPath.get(i)); // Eval expr to lambda fun
            ArrayList<Function<Double, Double>> yFun      = this.xPath.get(i).eval(this.xPath.get(i)); // Eval expr to lambda fun

            Double t = this.intervals.get(i).get(0);
            Double[] point = new Double[5];
            for (int j = 0; j < (this.intervals.get(i).get(1) - this.intervals.get(i).get(0))/res; j++) {
                // Evaluates functions at time and stores to array
                point[0] = xFun.get(0).apply(t);
                point[1] = xFun.get(1).apply(t);
                point[2] = yFun.get(0).apply(t);
                point[3] = yFun.get(1).apply(t);
                point[4] = t;

                // Increments time
                t += (this.intervals.get(i).get(1) - this.intervals.get(i).get(0))/res;
            }
            // Adds point to linked list
            numericPath.add(point);
        }
        return numericPath;
    }
}
