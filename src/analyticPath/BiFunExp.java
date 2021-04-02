package analyticPath;

import java.util.function.Function;

public class BiFunExp  {
    public static class Zero extends BiFunExp {
        private static final FunPair f = new FunPair(x -> 0.0, x -> 0.0);
        public Zero() {}

        public Function<Double, Double> getFun()            { return f.getFun(); }
        public Function<Double, Double> getDeriv()          { return f.getDeriv(); }
        public FunPair getF()   { return f; }
    }

    public static class One extends BiFunExp {
        private static final FunPair f = new FunPair(x -> 1.0, x -> 0.0);
        public One() {}

        public Function<Double, Double> getFun()            { return f.getFun(); }
        public Function<Double, Double> getDeriv()          { return f.getDeriv(); }
        public FunPair getF()   { return f; }

    }

    public static class Con extends BiFunExp {
        private final FunPair f = new FunPair(null, x -> 0.0);
        public Con(Double c) { this.f.setFun(x -> c); }

        public Function<Double, Double> getFun()            { return f.getFun(); }
        public Function<Double, Double> getDeriv()          { return f.getDeriv(); }
        public FunPair getF()   { return f; }
    }

    public static class Pi extends BiFunExp {
        private static final FunPair f = new FunPair(x -> Math.PI, x -> 0.0);
        public Pi() {}

        public Function<Double, Double> getFun()            { return f.getFun(); }
        public Function<Double, Double> getDeriv()          { return f.getDeriv(); }
        public FunPair getF()   { return f; }

    }

    public static class X extends BiFunExp {
        private static final FunPair f = new FunPair(x -> x, x -> 1.0);
        public X() {}

        public Function<Double, Double> getFun()            { return f.getFun(); }
        public Function<Double, Double> getDeriv()          { return f.getDeriv(); }
        public FunPair getF()   { return f; }
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

    public static class Factorial extends BiFunExp {
        private final BiFunExp expr;
        public Factorial(BiFunExp expr) { this.expr = expr; }

        public BiFunExp getExpr() { return this.expr; }
    }

    public static BiFunExp expr;
    public BiFunExp() {}

    public static FunPair biFunNeg(FunPair f) {
        return new FunPair(
                x -> -f.getFun().apply(x),
                x -> -f.getDeriv().apply(x));
    }

    public static FunPair biFunAdd(FunPair f, FunPair g) {
        return new FunPair(
                x -> f.getFun().apply(x) + g.getFun().apply(x),
                x -> f.getDeriv().apply(x) + g.getDeriv().apply(x));
    }

    public static FunPair biFunMul(FunPair f, FunPair g) {
        return new FunPair(
                x -> f.getFun().apply(x) * g.getFun().apply(x),
                x -> f.getFun().apply(x) + g.getDeriv().apply(x) + f.getDeriv().apply(x) * g.getFun().apply(x));
    }

    public static FunPair biFunRecip(FunPair f) {
        return new FunPair(
                x -> 1/f.getFun().apply(x),
                x -> (-1 / (f.getFun().apply(x) * f.getFun().apply(x))) * f.getDeriv().apply(x));
    }

    public static FunPair biFunRoot(FunPair f) {
        return new FunPair(
                x -> Math.sqrt(f.getFun().apply(x)),
                x -> f.getDeriv().apply(x) / (2 * Math.sqrt(f.getFun().apply(x))));
    }

    public static FunPair biFunSin(FunPair f) {
        return new FunPair(
                x -> Math.sin(f.getFun().apply(x)),
                x -> Math.cos(f.getFun().apply(x)) * f.getDeriv().apply(x));
    }

    public static FunPair biFunCos(FunPair f) {
        return new FunPair(
                x -> Math.cos(f.getFun().apply(x)),
                x -> -Math.sin(f.getFun().apply(x)) * f.getDeriv().apply(x));
    }

    public static FunPair biFunExp(FunPair f) {
        return new FunPair(
                x -> Math.exp(f.getFun().apply(x)),
                x -> Math.exp(f.getFun().apply(x)) * f.getDeriv().apply(x));
    }

    public static FunPair biFunLog(FunPair f){
        return new FunPair(
                x -> Math.log(f.getFun().apply(x)),
                x -> f.getDeriv().apply(x)/f.getFun().apply(x));
    }

    /*
    Takes a function expression and returns the derivative of it as a function expression.

    Parameters:
    (BiFunExp): Expression to differentiate

    Returns:
    (BiFunExp): Differentiated expression
     */
    public static BiFunExp synatxDeriv(BiFunExp expr) throws ClassNotFoundException {
        if      (expr instanceof Zero)  { return new Zero(); }
        else if (expr instanceof One)   { return new Zero(); }
        else if (expr instanceof Con)   { return new Zero(); }
        else if (expr instanceof Pi)    { return new Zero(); }
        else if (expr instanceof X)     { return new One(); }
        else if (expr instanceof Neg)   { return new Neg( synatxDeriv(((Neg) expr).getExpr()) ); }
        else if (expr instanceof Add)   {
            return new Add(synatxDeriv(((Add) expr).getExpr1()), synatxDeriv(((Add) expr).getExpr2()) );
        }
        else if (expr instanceof Mul)   {
            return new Add( new Mul(((Mul) expr).getExpr1(), synatxDeriv(((Mul) expr).getExpr2())),
                    new Mul(((Mul) expr).getExpr2(), synatxDeriv(((Mul) expr).getExpr1())) );
        }
        else if (expr instanceof Recip) {
            return new Neg( new Mul(new Recip(new Mul(((Recip) expr).getExpr(), ((Recip) expr).getExpr())),
                    synatxDeriv(((Recip) expr).getExpr())) );
        }
        else if (expr instanceof Root)  {
            return new Mul(new Recip(new Mul(new Con(2.0), new Root(((Root) expr).getExpr()))),
                    synatxDeriv(((Root) expr).getExpr()));
        }
        else if (expr instanceof Sin)   {
            return new Mul(new Cos(((Sin) expr).getExpr()), synatxDeriv(((Sin) expr).getExpr()));
        }
        else if (expr instanceof Cos)   {
            return new Neg(new Mul(new Sin(((Cos) expr).getExpr()), synatxDeriv(((Cos) expr).getExpr())));
        }
        else if (expr instanceof Exp)   {
            return new Mul(new Exp(((Exp) expr).getExpr()), synatxDeriv(((Exp) expr).getExpr()));
        }
        else {
            throw new ClassNotFoundException("Unexpected function input.");
        }
    }

    /*
    TODO: implement polyCheck for division of polynomials.
    Checks if supplied function expression is a polynomial.


    Parameters:
    (BiFunExp): Function expression to be checked if it is polynomial

    Returns:
    (boolean): true expression is polynomial, otherwise false
     */
    public static boolean polyCheck(BiFunExp expr) {
        if      (expr instanceof Zero)  { return true; }
        else if (expr instanceof One)   { return true; }
        else if (expr instanceof Con)   { return true; }
        else if (expr instanceof Pi)    { return true; }
        else if (expr instanceof X)     { return true; }
        else if (expr instanceof Neg)   { return polyCheck(((Neg) expr).getExpr()); }
        else if (expr instanceof Add)   { return polyCheck(((Add) expr).getExpr1()) && polyCheck(((Add) expr).getExpr2()); }
        else if (expr instanceof Mul)   { return polyCheck(((Mul) expr).getExpr1()) && polyCheck(((Mul) expr).getExpr2()); }
        else {
            return false;
        }
    }

    public static FunPair eval(BiFunExp expr) throws ClassNotFoundException {
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