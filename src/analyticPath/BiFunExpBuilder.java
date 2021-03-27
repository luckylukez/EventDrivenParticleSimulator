package analyticPath;

import java.util.function.Consumer;

public class BiFunExpBuilder
{
    public static class Builder implements IBuilder
    {
        public static BiFunExp Build(Consumer<IBuilder> builder){
            Builder b = new Builder();
            builder.accept(b);
            return b.Expr;
        }

        public BiFunExp Expr;

        public Builder X() {
            Expr = new BiFunExp.X();
            return this;
        }

        public Builder Pi() {
            Expr = new BiFunExp.Pi();
            return this;
        }

        public Builder One() {
            Expr = new BiFunExp.One();
            return this;
        }

        public Builder Exp(Consumer<IBuilder> arg) {
            var builder = new Builder();
            arg.accept(builder);

            Expr = new BiFunExp.Exp(builder.Expr);
            return this;
        }

        public IBuilder Add(Consumer<IBuilder> a, Consumer<IBuilder> b) {
            var aBuilder = new Builder();
            var bBuilder = new Builder();

            a.accept(aBuilder);
            b.accept(bBuilder);

            Expr = new BiFunExp.Add(aBuilder.Expr, bBuilder.Expr);
            return this;
        }

        public IBuilder Mul(Consumer<IBuilder> a, Consumer<IBuilder> b) {
            var aBuilder = new Builder();
            var bBuilder = new Builder();

            a.accept(aBuilder);
            b.accept(bBuilder);

            Expr = new BiFunExp.Mul(aBuilder.Expr, bBuilder.Expr);
            return this;
        }
    }

    // Interfacet ser till att man inte kan komma åt Expr direkt
    // utan tvingas gå genom buildern vilket är bra.
    public interface IBuilder
    {
        IBuilder X();
        IBuilder Pi();
        IBuilder Exp(Consumer<IBuilder> arg);
        IBuilder Add(Consumer<IBuilder> a, Consumer<IBuilder> b);
        IBuilder Mul(Consumer<IBuilder> a, Consumer<IBuilder> b);
    }
}
