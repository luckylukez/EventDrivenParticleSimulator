public class CollisionObject{
    private AnalyticPath<Double, Double[]> path;

    /* Constructor */
    public CollisionObject( AnalyticPath<Double, Double[]> path) { this.path = path; }

    /* Getter for analytic path */
    public AnalyticPath<Double, Double[]> getPath() { return this.path; }

    /* Setter for analytic path */
    public void setPath( AnalyticPath<Double, Double[]> newPath ) { this.path = newPath; }

    /* Evaluator for path */
    public Double[] evalPath(Double t) {
        return this.path.apply(t);
    }
}
