public class Test {
    public static void main(String[] args) {
        // Defines pure function describing path
        AnalyticPath<Double, Double[]> fn1 = t -> new Double[] {t*t, 10*t, t+9};
        AnalyticBall b1 = new AnalyticBall(10, 1, 0, 1, fn1);


        b1.setCurrentBound(20);
        double[][] pos = b1.calculateCurve(0, 19, 1);

        System.out.println("x = " + pos[0] );
        System.out.println("y = " + pos[1] );
    }
}
