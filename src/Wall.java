public class Wall extends CollisionObject {
    public Wall(AnalyticPath<Double, Double[]> fn) {
        super(fn);
    }

    @Override
    public String type(){
        return "wall";
    }

    //TODO: something
}
