public interface RotateMatrix {

    int[][] rotate90InOneThread(int[][] matrix);
    int[][] rotate90CompletableFuture(int[][] matrix);
    int[][] rotate90CompletableFutureWithExecutor(int[][] matrix);
    int[][] rotate90ParallelStream(int[][] matrix);
}
