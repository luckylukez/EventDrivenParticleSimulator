package analyticPath;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PowerSeries {
    private final BiFunction<Integer, Double, Double> an;

    public PowerSeries(BiFunExp expr) throws ClassNotFoundException {
        Function<Integer, BiFunExp> coeffExpr = n -> {
            try {
                return new BiFunExp.Mul(nDeriv(expr, n),
                        new BiFunExp.Recip(new BiFunExp.Factorial(new BiFunExp.Con((double)n))));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        };

        this.an = (n, x) -> {
            try {
                return coeffExpr.apply(n).eval(coeffExpr.apply(n)).getFun().apply(x);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        };
    }

    private BiFunExp nDeriv(BiFunExp expr, int n) throws ClassNotFoundException {
        if (n == 0) { return expr; }
        return nDeriv(expr.synatxDeriv(expr), n-1);
    }

    public double getCoeff(int n, double a) {
        return an.apply(n, a);
    }

    public PowerSeries integ(double init) {
        return null;
    }
}
