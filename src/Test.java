public class Test {
    public static void main(String[] args) {
        // Defining functions describing walls of box
        AnalyticPath<Double, Double[]> wallConst1 = t -> new Double[] {t, Double.valueOf(0)};
        AnalyticPath<Double, Double[]> wallConst2 = t -> new Double[] {Double.valueOf(0), t};
        AnalyticPath<Double, Double[]> wallConst3 = t -> new Double[] {t, Double.valueOf(10)};
        AnalyticPath<Double, Double[]> wallConst4 = t -> new Double[] {Double.valueOf(10), t};

        CollisionObject wall1 = new CollisionObject(wallConst1);
        CollisionObject wall2 = new CollisionObject(wallConst2);
        CollisionObject wall3 = new CollisionObject(wallConst3);
        CollisionObject wall4 = new CollisionObject(wallConst4);


        // Defines pure function describing path
        AnalyticPath<Double, Double[]> fn1 = t -> new Double[] {t*t, 10*t, t+9};
        AnalyticBall b1 = new AnalyticBall(10, 1, 1, fn1);


        b1.setCurrentBound(20);
        double[][] pos = b1.calculateCurve(0, 19, 1);

        System.out.println("x = " + pos[0] );
        System.out.println("y = " + pos[1] );
    }
}
