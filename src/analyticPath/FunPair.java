package analyticPath;

import java.util.function.Function;

public class FunPair {
    private Function<Double, Double> f;
    private Function<Double, Double> fDeriv;

    public FunPair() {}

    public FunPair(Function<Double, Double> f, Function<Double, Double> fDeriv) {
        this.f = f;
        this.fDeriv = fDeriv;
    }

    public Function<Double, Double> getFun()    { return this.f; }
    public Function<Double, Double> getDeriv()  { return this.fDeriv; }
    public void setFun(Function<Double, Double> f) { this.f = f; }
    public void setDeriv (Function<Double, Double> fDerv) { this.fDeriv = fDerv; }
}
