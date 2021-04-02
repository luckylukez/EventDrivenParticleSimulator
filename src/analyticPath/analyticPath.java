package analyticPath;

import java.util.LinkedList;
import java.util.Vector;
import java.util.function.Function;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;

public class analyticPath {
    private LinkedList<BiFunExp> xPath = new LinkedList<>();
    private LinkedList<BiFunExp> yPath = new LinkedList<>();
    private LinkedList<Vector<Double>> intervals = new LinkedList<>();

    public analyticPath(BiFunExp xPath, BiFunExp yPath) {
        this.xPath.add(xPath);
        this.yPath.add(yPath);
    }

    private static double brentsRootFinder(Function<Double, Double> inputFun, double intervalStart,
                                           double intervalEnd, double res) {
        BrentSolver solver = new BrentSolver();
        UnivariateFunction f = new UnivariateFunction() {

            @Override
            public double value(double x) {
                return inputFun.apply(x);
            }
        };

        // Iterates through every the interval (intervalStart, intervalEnd) and returns the first solution
        while(intervalStart < intervalEnd) {
            intervalStart += res;
            if(Math.signum(f.value(intervalStart)) != Math.signum(f.value(intervalStart+res))) {
                System.out.println("Root = " + solver.solve(1000, f, intervalStart, intervalStart+res));
            }
        }
        return 0.0;
    }

    /*
    Given two analyticPath objects the function finds the first time at which the paths are
    minDist apart.

    @param (analyticPath): path of first object
    @param (analyticPath): path of second object
    @param (double): the minimum distance between the paths
    @param (double):

    @ret (double): time at which the paths are minDist apart
     */
    public static double findIntersect(analyticPath f1, analyticPath f2, double minDist,
                                       double intervalStart, double intervalEnd, double res)
            throws ClassNotFoundException {
        // Converts f1 to lambda functions
        FunPair xFun1 = BiFunExp.eval(f1.xPath.getLast());
        FunPair yFun1 = BiFunExp.eval(f1.yPath.getLast());

        // Converts f2 to lambda functions
        FunPair xFun2 = BiFunExp.eval(f2.xPath.getLast());
        FunPair yFun2 = BiFunExp.eval(f2.yPath.getLast());

        // Constructs function to find roots to
        Function<Double, Double> f = x -> Math.sqrt(Math.pow(xFun1.getFun().apply(x) - xFun2.getFun().apply(x), 2) +
                Math.pow(yFun1.getFun().apply(x) - yFun2.getFun().apply(x), 2)) - minDist;

        // Finds root using Brent's method
        double root = brentsRootFinder(f, intervalStart, intervalEnd, res);

        return 0.0;
    }

    /*
    Takes a two paths together with collision time and calculates their
    respective new paths after they have collided.

    Parameters:
    (BiFunExp): Path of object 1
    (BiFunExp): Path of object 2
    (double):   Time at which objects collide

    Returns:
    (BiFunExp[]): List of new paths. First element is new path for first object, mutatis mutandis for second obj.
     */
    public static BiFunExp[] physicalCollision(analyticPath f1, analyticPath f2, double tCollide) {
        BiFunExp[] newPaths = new BiFunExp[2];
        return newPaths;
    }

    /*
    Takes a two paths together with collision time and calculates their
    respective new non-normalised paths after they have collided.

    Parameters:
    (BiFunExp): Path of object 1
    (BiFunExp): Path of object 2
    (double):   Time at which objects collide

    Returns:
    (BiFunExp[]): List of new paths. First element is new path for first object, mutatis mutandis for second obj.
     */
    public static BiFunExp[] nonNormalisedCollision(analyticPath f1, analyticPath f2, double tCollide) {
        BiFunExp[] newPaths = new BiFunExp[2];
        return newPaths;
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
            FunPair xFun      = BiFunExp.eval(this.xPath.get(i)); // Eval expr to lambda fun
            FunPair yFun      = BiFunExp.eval(this.yPath.get(i)); // Eval expr to lambda fun

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
