package analyticPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.function.Function;

public class BiFunExp  {
    public static class Zero extends BiFunExp {
        private static final ArrayList<Function<Double, Double>> f = new ArrayList<>(Arrays.asList(x -> 0.0, x -> 0.0));
        public Zero() {}

        public Function<Double, Double> getFun()            { return f.get(0); }
        public Function<Double, Double> getDeriv()          { return f.get(1); }
        public ArrayList<Function<Double, Double>> getF()   { return f; }
    }

    public static class One extends BiFunExp {
        private static final ArrayList<Function<Double, Double>> f = new ArrayList<>(Arrays.asList(x -> 1.0, x -> 0.0));
        public One() {}

        public Function<Double, Double> getFun()            { return f.get(0); }
        public Function<Double, Double> getDeriv()          { return f.get(1); }
        public ArrayList<Function<Double, Double>> getF()   { return f; }

    }

    public static class Con extends BiFunExp {
        private        final ArrayList<Function<Double, Double>> f = new ArrayList<>(Arrays.asList(null, x -> 0.0));
        public Con(Double c) { this.f.set(0, x -> c); }

        public Function<Double, Double> getFun()            { return f.get(0); }
        public Function<Double, Double> getDeriv()          { return f.get(1); }
        public ArrayList<Function<Double, Double>> getF()   { return f; }
    }

    public static class Pi extends BiFunExp {
        private static final ArrayList<Function<Double, Double>> f = new ArrayList<>(Arrays.asList(x -> Math.PI, x -> 0.0));
        public Pi() {}

        public Function<Double, Double> getFun()            { return f.get(0); }
        public Function<Double, Double> getDeriv()          { return f.get(1); }
        public ArrayList<Function<Double, Double>> getF()   { return f; }

    }

    public static class X extends BiFunExp {
        private static final ArrayList<Function<Double, Double>> f = new ArrayList<>(Arrays.asList(x -> x, x -> 1.0));
        public X() {}

        public Function<Double, Double> getFun()            { return f.get(0); }
        public Function<Double, Double> getDeriv()          { return f.get(1); }
        public ArrayList<Function<Double, Double>> getF()   { return f; }
    }

    public static class Neg extends BiFunExp {
        private final BiFunExp expr;
        public Neg(BiFunExp expr) { this.expr = expr; }

        public BiFunExp getExpr() { return this.expr; }
    }

    public static class Add extends BiFunExp {
        private final BiFunExp expr1;
        private final BiFunExp expr2;
        public Add(BiFunExp expr1, BiFunExp expr2) { this.expr1 = expr1; this.expr2 = expr2; }

        public BiFunExp getExpr1() { return this.expr1; }
        public BiFunExp getExpr2() { return this.expr2; }
    }

    public static class Mul extends BiFunExp {
        private final BiFunExp expr1;
        private final BiFunExp expr2;
        public Mul(BiFunExp expr1, BiFunExp expr2) { this.expr1 = expr1; this.expr2 = expr2; }

        public BiFunExp getExpr1() { return this.expr1; }
        public BiFunExp getExpr2() { return this.expr2; }
    }

    public static class Recip extends BiFunExp {
        private final BiFunExp expr;
        public Recip(BiFunExp expr) { this.expr = expr; }

        public BiFunExp getExpr() { return this.expr; }
    }

    public static class Root extends BiFunExp {
        private final BiFunExp expr;
        public Root(BiFunExp expr) { this.expr = expr; }

        public BiFunExp getExpr() { return this.expr; }
    }

    public static class Sin extends BiFunExp {
        private final BiFunExp expr;
        public Sin(BiFunExp expr) { this.expr = expr; }

        public BiFunExp getExpr() { return this.expr; }
    }

    public static class Cos extends BiFunExp {
        private final BiFunExp expr;
        public Cos(BiFunExp expr) { this.expr = expr; }

        public BiFunExp getExpr() { return this.expr; }
    }

    public static class Exp extends BiFunExp {
        private final BiFunExp expr;
        public Exp(BiFunExp expr) { this.expr = expr; }

        public BiFunExp getExpr() { return this.expr; }
    }

    public static class Log extends BiFunExp {
        private final BiFunExp expr;
        public Log(BiFunExp expr) { this.expr = expr; }

        public BiFunExp getExpr() { return this.expr; }
    }

    public static BiFunExp expr;
    public BiFunExp() {}

    public ArrayList<Function<Double, Double>> biFunNeg(ArrayList<Function<Double, Double>> f) {
        return new ArrayList<>(Arrays.asList(
                x -> -f.get(0).apply(x),
                x -> -f.get(1).apply(x)));
    }

    public ArrayList<Function<Double, Double>> biFunAdd(ArrayList<Function<Double, Double>> f,
                                                        ArrayList<Function<Double, Double>> g) {
        return new ArrayList<>(Arrays.asList(
                x -> f.get(0).apply(x) + g.get(0).apply(x),
                x -> f.get(1).apply(x) + g.get(1).apply(x)));
    }

    public ArrayList<Function<Double, Double>> biFunMul(ArrayList<Function<Double, Double>> f,
                                                        ArrayList<Function<Double, Double>> g) {
        return new ArrayList<>(Arrays.asList(
                x -> f.get(0).apply(x) * g.get(0).apply(x),
                x -> f.get(0).apply(x) + g.get(1).apply(x) + f.get(0).apply(x) * g.get(0).apply(x)));
    }

    public ArrayList<Function<Double, Double>> biFunRecip(ArrayList<Function<Double, Double>> f) {
        return new ArrayList<>(Arrays.asList(
                x -> 1/f.get(0).apply(x),
                x -> (-1 / (f.get(0).apply(x) * f.get(0).apply(x))) * f.get(1).apply(x)));
    }

    public ArrayList<Function<Double, Double>> biFunRoot(ArrayList<Function<Double, Double>> f) {
        return new ArrayList<>(Arrays.asList(
                x -> Math.sqrt(f.get(0).apply(x)),
                x -> f.get(1).apply(x) / (2 * Math.sqrt(f.get(0).apply(x)))));
    }

    public ArrayList<Function<Double, Double>> biFunSin(ArrayList<Function<Double, Double>> f) {
        return new ArrayList<>(Arrays.asList(
                x -> Math.sin(f.get(0).apply(x)),
                x -> Math.cos(f.get(0).apply(x)) * f.get(1).apply(x)));
    }

    public ArrayList<Function<Double, Double>> biFunCos(ArrayList<Function<Double, Double>> f) {
        return new ArrayList<>(Arrays.asList(
                x -> Math.cos(f.get(0).apply(x)),
                x -> -Math.sin(f.get(0).apply(x)) * f.get(1).apply(x)));
    }

    public ArrayList<Function<Double, Double>> biFunExp(ArrayList<Function<Double, Double>> f) {
        return new ArrayList<>(Arrays.asList(
                x -> Math.exp(f.get(0).apply(x)),
                x -> Math.exp(f.get(0).apply(x)) * f.get(1).apply(x)));
    }

    public ArrayList<Function<Double, Double>> biFunLog(ArrayList<Function<Double, Double>> f) {
        return new ArrayList<>(Arrays.asList(
                x -> Math.log(f.get(0).apply(x)),
                x -> f.get(1).apply(x)/f.get(0).apply(x)));
    }

    public ArrayList<Function<Double, Double>> eval(BiFunExp expr) throws ClassNotFoundException {
        if      (expr instanceof Zero)  { return ((Zero) expr).getF(); }
        else if (expr instanceof One)   { return ((One) expr).getF(); }
        else if (expr instanceof Con)   { return ((Con) expr).getF(); }
        else if (expr instanceof Pi)    { return ((Pi) expr).getF(); }
        else if (expr instanceof X)     { return ((X) expr).getF(); }
        else if (expr instanceof Neg)   { return biFunNeg(eval(((Neg) expr).getExpr())); }
        else if (expr instanceof Add)   { return biFunAdd(eval(((Add) expr).getExpr1()), eval(((Add) expr).getExpr2())); }
        else if (expr instanceof Mul)   { return biFunMul(eval(((Mul) expr).getExpr1()), eval(((Mul) expr).getExpr2())); }
        else if (expr instanceof Recip) { return biFunRecip(eval(((Recip) expr).getExpr())); }
        else if (expr instanceof Root)  { return biFunRoot(eval(((Root) expr).getExpr())); }
        else if (expr instanceof Sin)   { return biFunSin(eval(((Sin) expr).getExpr())); }
        else if (expr instanceof Cos)   { return biFunCos(eval(((Cos) expr).getExpr())); }
        else if (expr instanceof Exp)   { return biFunExp(eval(((Exp) expr).getExpr())); }
        else {
            throw new ClassNotFoundException("Unexpected function input.");
        }
    }

}
