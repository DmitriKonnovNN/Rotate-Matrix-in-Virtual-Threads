package solutions.dmitrikonnov.demo;


/**
 * Let us initialize a subclass with a superclass' common method without using reflection.
 * */
@FunctionalInterface
public interface HexaInitializer<T> {
    T initialize(int[][]matrix, int[][]newMatrix, int i, int j, int k , int l);
}
