public class Test {
    public static void main(String[] args) {
        // Defines pure function describing path
        AnalyticPath<Double, Double[]> fn = new AnalyticPath<Double,Double[]>(){
            @Override
            public Double[] apply(Double t) {
                return new Double[] {t*t, 10*t, t+9};
            }
        };
        AnalyticBall b1 = new AnalyticBall(10, 1, 0, 1, fn);


        Double time = Double.valueOf(9);
        Double[] pos = b1.evalPath(time);

        System.out.println("x = " + pos[0].doubleValue() );
        System.out.println("y = " + pos[1].doubleValue() );
    }
}
