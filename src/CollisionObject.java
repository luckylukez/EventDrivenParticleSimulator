import java.util.ArrayList;

public class CollisionObject{
    private ArrayList<AnalyticPath<Double, Double[]>> paths = new ArrayList<>();
    private ArrayList<Double> upperTimeBounds = new ArrayList<>();
    private int collisionCount = 0;

    /* Constructor */
    public CollisionObject( AnalyticPath<Double, Double[]> path) { this.paths.add(path); }

    /* Getter for analytic path. This returns the function with the largest upper bound */
    public AnalyticPath<Double, Double[]> getCurrentPath() { return this.paths.get(this.paths.size()-1); }

    /* Setter, getter and updater for collision count */
    public void setCollisionCount(int c) { this.collisionCount = c; }
    public int getCollisionCount() { return this.collisionCount; }
    public void updateCollisionCount() { this.setCollisionCount( this.getCollisionCount() + 1 ); }
 
    /* Adder and setter for analytic path */
    public void addPath( AnalyticPath<Double, Double[]> newPath ) { this.paths.add(newPath); }
    public void setPath(int index, AnalyticPath<Double, Double[]> newPath) { this.paths.set(index, newPath); }

    /* Evaluator for current path at given time */
    public Double[] evalCurrentPath(double t) {
        return this.getCurrentPath().apply( Double.valueOf(t) );
    }

    // TODO: Should be implemented with BST
    public Double[] evalPath(double t) {
        int i = 0;
        while (t < this.getBound(i)) {
            i += 1;
        } 
        return this.evalPath(i, t);
    }

    /* Evaluator for path at given index and at given time */
    public Double[] evalPath(int index, double t) {
        return this.paths.get(index).apply( Double.valueOf(t) );
    }

    /* Adds and gets upper bound for current, i.e. path at end of list, path */
    public void setCurrentBound(double t) { this.upperTimeBounds.add( Double.valueOf(t) ); }
    public double getCurrentBound() { return this.upperTimeBounds.get(this.upperTimeBounds.size() - 1).doubleValue(); }

    /* Changes and gets upper time bound for function at given index */
    public void setBound(int index, double t) { this.upperTimeBounds.set(index, Double.valueOf(t) ); }
    public double getBound(int index) { return this.upperTimeBounds.get(index).doubleValue(); }

    /* Evaluates CollisionObject position over provided interval with given resolution */
    public double[][] calculateCurve(double t0, double T, double dt) {
        double[][] coordMatrix = new double[2][(int)Math.ceil((T-t0)/dt) + 1];

        int j = 0;
        for (int i = 0; i < coordMatrix[0].length; i++) {
            if (t0 > this.getBound(j)) {
                j += 1;
            }
            coordMatrix[0][i] = this.evalPath(j, t0)[0].doubleValue();
            coordMatrix[1][i] = this.evalPath(j, t0)[1].doubleValue();
            t0 += dt;
        }
        return coordMatrix;
    }

    /* Updates paths for two objects that is colliding at given time */
    public static void collision(CollisionObject obj1, CollisionObject obj2, double t) {
        obj1.setCurrentBound(t);
        obj2.setCurrentBound(t);
    }
}
