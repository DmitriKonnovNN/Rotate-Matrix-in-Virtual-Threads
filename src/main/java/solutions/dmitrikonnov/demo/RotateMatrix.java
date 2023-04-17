package solutions.dmitrikonnov.demo;

public interface RotateMatrix {

   void rotate90Sequential(MatrixRotatorTask[] tasks);
   void rotate90CompletableFuture(MatrixRotatorTask[] tasks);
   void rotate90CompletableFutureWithExecutor(MatrixRotatorTask[] tasks, int numberOfThreads);
   void rotate90ParallelStream(MatrixRotatorTask[] tasks);

   void rotate180Sequential(MatrixRotatorTask[] tasks);
   void rotate180CompletableFuture(MatrixRotatorTask[] tasks);
   void rotate180CompletableFutureWithExecutor(MatrixRotatorTask[] tasks, int numberOfThreads);
   void rotate180ParallelStream(MatrixRotatorTask[] tasks);

   void rotate90VirtualThead(MatrixRotatorTask [] task);

   void rotate270Sequential(MatrixRotatorTask[] tasks);
   void rotate270CompletableFuture(MatrixRotatorTask[] tasks);
   void rotate270CompletableFutureWithExecutor(MatrixRotatorTask[] tasks, int numberOfThreads);
   void rotate270ParallelStream(MatrixRotatorTask[] tasks);
}
