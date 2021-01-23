@FunctionalInterface
public interface AnalyticPath<T,R> {
    R apply(T t);
}
