import analyticPath.BiFunExp;
import analyticPath.analyticPath;
import analyticPath.BiFunExpBuilder.Builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

public class FunExpTest {

    public static void main(String[] args) throws ClassNotFoundException {

        // Det här är det coola balla jag ville visa, men det
        // var ju helt onödigt i erat fall eftersom ni bara gör en action
        // per grej. I en påhittad domän kan man göra sånt här och då blir det relevant:

        // GangsterEmpireBuilder.Build(expression -> expression
        //         .Name("GurrasGs Gangsterliga")
        //         .Pengar(999999.69)
        //         .WithMembers(members -> members
        //                 .Member("Johan", HurFarligEnum.Max)
        //                 .Member("Bertil", HurFarligEnum.MerÄnMax)
        //                 .Member("Karlsson", HurFarligEnum.MerÄnMerÄnMax)
        //         )
        // );

        // Men i erat fall så är det så linjärt och man kör aldrig flera metoder
        // på raken liksom, så det blir mest bara overkill:

        BiFunExp xt = Builder.Build(expression -> expression
                .Exp(
                        a -> a.Add(
                                b -> b.Exp(c -> c.Pi()),
                                b -> b.X()
                        )
                )
        );

        BiFunExp yt = Builder.Build(expression -> expression
                .Exp(
                        a -> a.Add(
                                b -> b.Exp(c -> c.Pi()),
                                b -> b.Mul(c -> c.X(), c -> c.X())
                        )
                )
        );


        // Det här är ju den kanske rimligare facotry methods varianten:

        // analyticPath.BiFunExp.Factory b = new analyticPath.BiFunExp.Factory();

        // BiFunExp xt =
        //         b.Exp(b.Add(
        //                 b.Exp(b.Pi()),
        //                 b.X()
        //         ));

        // BiFunExp yt =
        //         b.Exp(b.Add(
        //                 b.Exp(b.Pi()),
        //                 b.Mul(b.X(), b.X())
        //         ));

        Vector<Double> I = new Vector<>(Arrays.asList(0.0,10.0));

        analyticPath testPath = new analyticPath(xt, yt);
        testPath.addInterval(I);

        LinkedList<Double[]> result = testPath.evalPath(1);
        for (int i = 0; i < result.size(); i++) {
                System.out.println("x = " + result.get(i)[0] + ", x' = " + result.get(i)[1] +
                        ", y = " + result.get(i)[2] + ", y' = " + result.get(i)[3] + ", t = " + result.get(i)[4]);
        }


        /*
        System.out.println("f(1) = " + f.get(0).apply(1.0));
        System.out.println("f'(1) = " + f.get(1).apply(1.0));
        System.out.println("f(2) = " + f.get(0).apply(2.0));
        System.out.println("f'(2) = " + f.get(1).apply(2.0));
        System.out.println("f(3) = " + f.get(0).apply(3.0));
        System.out.println("f'(3) = " + f.get(1).apply(3.0));
        System.out.println("f(100) = " + f.get(0).apply(100.0));
        System.out.println("f'(100) = " + f.get(1).apply(100.0));

        System.out.println("g(1) = " + g.apply(1.0));
        System.out.println("g(2) = " + g.apply(2.0));
        System.out.println("g(3) = " + g.apply(3.0));
        System.out.println("g(100) = " + g.apply(100.0));
         */


    }
}
