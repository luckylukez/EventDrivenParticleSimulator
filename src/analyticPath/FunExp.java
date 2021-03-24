package analyticPath;

import java.util.function.Function;

public class FunExp  {
    public static class Zero extends FunExp {
        public static final Function<Double, Double> f     = x -> 0.0;
        public static final Function<Double, Double> fPrim = x -> 0.0;
        public Zero() {}

        public Function<Double, Double> getFun() { return f; }
    }

    public static class One extends FunExp {
        public static final Function<Double, Double> f     = x -> 1.0;
        public static final Function<Double, Double> fPrim = x -> 0.0;
        public One() {}

        public Function<Double, Double> getFun() { return f; }
    }

    public static class Con extends FunExp {
        public        final Function<Double, Double> f;
        public static final Function<Double, Double> fPrim = x -> 0.0;
        public Con(Double c) { this.f = x -> c; }

        public Function<Double, Double> getFun() { return f; }
    }

    public static class Pi extends FunExp {
        public static final Function<Double, Double> f      = x -> Math.PI;
        public static final Function<Double, Double> fDeriv = x -> 0.0;
        public Pi() {}

        public Function<Double, Double> getFun() { return f; }

    }

    public static class X extends FunExp {
        public static final Function<Double, Double> f     = x -> x;
        public static final Function<Double, Double> fPrim = x -> 1.0;
        public X() {}

        public Function<Double, Double> getFun() { return f; }
    }

    public static class Neg extends FunExp {
        private final FunExp expr;
        public Neg(FunExp expr) { this.expr = expr; }

        public FunExp getExpr() { return this.expr; }
    }

    public static class Add extends FunExp {
        private final FunExp expr1;
        private final FunExp expr2;
        public Add(FunExp expr1, FunExp expr2) { this.expr1 = expr1; this.expr2 = expr2; }

        public FunExp getExpr1() { return this.expr1; }
        public FunExp getExpr2() { return this.expr2; }
    }

    public static class Mul extends FunExp {
        private final FunExp expr1;
        private final FunExp expr2;
        public Mul(FunExp expr1, FunExp expr2) { this.expr1 = expr1; this.expr2 = expr2; }

        public FunExp getExpr1() { return this.expr1; }
        public FunExp getExpr2() { return this.expr2; }
    }

    public static class Recip extends FunExp {
        private final FunExp expr;
        public Recip(FunExp expr) { this.expr = expr; }

        public FunExp getExpr() { return this.expr; }
    }

    public static class Root extends FunExp {
        private final FunExp expr;
        public Root(FunExp expr) { this.expr = expr; }

        public FunExp getExpr() { return this.expr; }
    }

    public static class Sin extends FunExp {
        private final FunExp expr;
        public Sin(FunExp expr) { this.expr = expr; }

        public FunExp getExpr() { return this.expr; }
    }

    public static class Cos extends FunExp {
        private final FunExp expr;
        public Cos(FunExp expr) { this.expr = expr; }

        public FunExp getExpr() { return this.expr; }
    }

    public static class Exp extends FunExp {
        private final FunExp expr;
        public Exp(FunExp expr) { this.expr = expr; }

        public FunExp getExpr() { return this.expr; }
    }

    public static FunExp expr;
    public FunExp() {}

    public Function<Double, Double> funNeg(Function<Double, Double> f) {
        return x -> -f.apply(x);
    }

    public Function<Double, Double> funAdd(Function<Double, Double> f, Function<Double, Double> g) {
        return x -> f.apply(x) + g.apply(x);
    }

    public Function<Double, Double> funMul(Function<Double, Double> f, Function<Double, Double> g) {
        return x -> f.apply(x) * g.apply(x);
    }

    public Function<Double, Double> funRecip(Function<Double, Double> f) {
        return x -> 1/f.apply(x);
    }

    public Function<Double, Double> funRoot(Function<Double, Double> f) {
        return x -> Math.sqrt(f.apply(x));
    }

    public Function<Double, Double> funSin(Function<Double, Double> f) {
        return x -> Math.sin(f.apply(x));
    }

    public Function<Double, Double> funCos(Function<Double, Double> f) {
        return x -> Math.cos(f.apply(x));
    }

    public Function<Double, Double> funExp(Function<Double, Double> f) {
        return x -> Math.exp(f.apply(x));
    }

    public Function<Double, Double> eval(FunExp expr) throws ClassNotFoundException {
        if      (expr instanceof Zero)  { return ((Zero) expr).getFun(); }
        else if (expr instanceof One)   { return ((One) expr).getFun(); }
        else if (expr instanceof Con)   { return ((Con) expr).getFun(); }
        else if (expr instanceof Pi)    { return ((Pi) expr).getFun(); }
        else if (expr instanceof X)     { return ((X) expr).getFun(); }
        else if (expr instanceof Neg)   { return funNeg(eval(((Neg) expr).getExpr())); }
        else if (expr instanceof Add)   { return funAdd(eval(((Add) expr).getExpr1()), eval(((Add) expr).getExpr2())); }
        else if (expr instanceof Mul)   { return funMul(eval(((Mul) expr).getExpr1()), eval(((Mul) expr).getExpr2())); }
        else if (expr instanceof Recip) { return funRecip(eval(((Recip) expr).getExpr())); }
        else if (expr instanceof Root)  { return funRoot(eval(((Root) expr).getExpr())); }
        else if (expr instanceof Sin)   { return funSin(eval(((Sin) expr).getExpr())); }
        else if (expr instanceof Cos)   { return funCos(eval(((Cos) expr).getExpr())); }
        else if (expr instanceof Exp)   { return funExp(eval(((Exp) expr).getExpr())); }
        else {
            throw new ClassNotFoundException("Unexpected function input.");
        }
    }

}