package solutions.dmitrikonnov.demo;

@FunctionalInterface
public interface HexaInitializer<T> {
    T initializeWithInt(int[][]matrix, int[][]newMatrix, int i, int j,int k ,int l);
}
