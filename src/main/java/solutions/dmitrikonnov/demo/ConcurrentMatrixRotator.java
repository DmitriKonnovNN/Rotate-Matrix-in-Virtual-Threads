package solutions.dmitrikonnov.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConcurrentMatrixRotator {
    public static void main(String[] args) {
        int size = 200;
        int numberOfMatrices = args.length == 0 ? 1000 : Integer.parseInt(args[0]) ;
        //      initialization with reversed length and width lets us create a new matrix with length and width of not equal size;
        //   int[][] newMatrix = new int[matrix[0].length][matrix.length]; but where?


        int length = 5;
        int width = 10;
        RotateMatrix rotateMatrix = new MatrixRotator();

        /**
         * generate list of matrices and print those;
         * */
        final List<int[][]> matrices = new ArrayList<>();
        IntStream.range(0,numberOfMatrices).forEach(index->{
            matrices.add(Utils.generateRandom2DMatrix(size));
        });

        /**
         * generate and run recursive tasks;
         * */

        MatrixRotatorTask[] recursiveTasks = matrices.stream()
                .map(MatrixRotatorRecursiveTask::new)
                .toArray(MatrixRotatorTask[]::new);
        System.out.println("Before rotation. Number of tasks: " + recursiveTasks.length);

        System.out.println("Rotate recursive tasks");


        System.out.println("Sequentially rotated matrices:");
        rotateMatrix.rotate90Sequential(recursiveTasks);
        System.out.println("");


        System.out.println("Rotate with CompleteableFuture");
        rotateMatrix.rotate90CompletableFuture(recursiveTasks);
        System.out.println("");


        System.out.println("Rotate in parallel streams");
        rotateMatrix.rotate90ParallelStream(recursiveTasks);
        System.out.println("");

        System.out.println("Rotate with CompleteableFutre with custom Thread pool");
        rotateMatrix.rotate90CompletableFutureWithExecutor(recursiveTasks,7);
        System.out.println("");

        System.out.println("====================SIMPLE TASK=================================");
        /**
         * generate and run simple tasks;
         * */

        MatrixRotatorTask[] sequentialTasks = matrices.stream()
                .map(MatrixRotatorSequentialTask::new)
                .toArray(MatrixRotatorTask[]::new);

        System.out.println("rotate simple tasks");
        System.out.println("Sequentially rotated matrices:");
        rotateMatrix.rotate90Sequential(sequentialTasks);
        System.out.println("");

        System.out.println("Rotate with CompleteableFuture");
        rotateMatrix.rotate90CompletableFuture(sequentialTasks);
        System.out.println("");

        System.out.println("Rotate in parallel streams");
        rotateMatrix.rotate90ParallelStream(sequentialTasks);
        System.out.println("");

//        System.out.println("Rotate with CompleteableFutre with custom Thread pool");
//        rotateMatrix.rotate90CompletableFutureWithExecutor(sequentialTasks,7);
//        System.out.println("");

    }

}
