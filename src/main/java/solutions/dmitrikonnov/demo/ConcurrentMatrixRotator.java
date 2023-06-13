package solutions.dmitrikonnov.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConcurrentMatrixRotator {
    public static void main(String[] args) {
        int size = 500;
        int numberOfMatrices = args.length == 0 ? 100: Integer.parseInt(args[0]) ;
        //      initialization with reversed length and width lets us create a new matrix with length and width of not equal size;
        //   int[][] newMatrix = new int[matrix[0].length][matrix.length]; but where?

        int length = 5;
        int width = 10;

        RotateMatrix rotateMatrix = new MatrixRotator();

        /**
         * generate list of matrices and print those;
         * */
        final List<int[][]> matrices = new ArrayList<>();
        IntStream.range(0,numberOfMatrices).forEach(index-> matrices.add(Utils.generateRandom2DMatrix(size)));

        /**
         * generate and run recursive tasks;
         * */

//        MatrixRotatorTask[] recursiveTasks = matrices.stream()
//                .map(MatrixRotatorRecursiveTask::new)
//                .toArray(MatrixRotatorTask[]::new);
//        System.out.println("Before rotation. Number of tasks: " + recursiveTasks.length);
//
//        System.out.println("Rotate recursive tasks");
//
//
//            System.out.println("Sequentially rotated matrices:");
//            rotateMatrix.rotate90Sequential(recursiveTasks);
//            System.out.println("");


//        System.out.println("Rotate with CompleteableFuture");
//        rotateMatrix.rotate90CompletableFuture(recursiveTasks);
//        System.out.println("");

//
//        System.out.println("Rotate in parallel streams");
//        rotateMatrix.rotate90ParallelStream(recursiveTasks);
//        System.out.println("");
//
//        System.out.println("Rotate with CompleteableFutre with custom Thread pool");
//        rotateMatrix.rotate90CompletableFutureWithExecutor(recursiveTasks,200);
//        System.out.println("");
//
//
//        System.out.println("Rotate with Virtual Threads");
//        rotateMatrix.rotate90VirtualThread(recursiveTasks);
//        System.out.println("");
//
//        System.out.println("Rotate with Virtual Threads with Executor");
//        rotateMatrix.rotate90VirtualThread(recursiveTasks,true,false);
//        System.out.println("");
////
//        System.out.println("Rotate with Virtual Threads with Executor and Factory"); // >>> THE FASTEST
//        rotateMatrix.rotate90VirtualThread(recursiveTasks,true,true);
//        System.out.println("");
//
//        System.out.println("Rotate with Virtual Threads with Factory");
//        rotateMatrix.rotate90VirtualThread(recursiveTasks,false,true);
//        System.out.println("");

        System.out.println("====================SIMPLE TASK=================================");
        /**
         * generate and run simple tasks;
         * */
        MatrixRotatorTask[] sequentialTasks = matrices.stream()
                .map(MatrixRotatorSequentialTask::new)
                .toArray(MatrixRotatorTask[]::new);

//        System.out.println("rotate simple tasks");
//        System.out.println("Sequentially rotated matrices:");
//        rotateMatrix.rotate90Sequential(sequentialTasks);
//        System.out.println("");

//        System.out.println("Rotate with CompleteableFuture");
//        rotateMatrix.rotate90CompletableFuture(sequentialTasks);
//        System.out.println("");
//
//        System.out.println("Rotate in parallel streams");
//        rotateMatrix.rotate90ParallelStream(sequentialTasks);
//        System.out.println("");
//
//        System.out.println("Rotate with CompleteableFutre with custom Thread pool");
//        rotateMatrix.rotate90CompletableFutureWithExecutor(sequentialTasks,7);
//        System.out.println("");
//
//        System.out.println("Rotate with Virtual Threads");
//        rotateMatrix.rotate90VirtualThread(sequentialTasks);
//        System.out.println("");
//
//        System.out.println("Rotate with Virtual Threads with Executor");
//        rotateMatrix.rotate90VirtualThread(sequentialTasks,true,false);
//        System.out.println("");
//
        System.out.println("Rotate with Virtual Threads with Executor and Factory"); /// much faster??
        rotateMatrix.rotate90VirtualThread(sequentialTasks,true,true);
        System.out.println("");
//
//        System.out.println("Rotate with Virtual Threads with Factory");
//        rotateMatrix.rotate90VirtualThread(sequentialTasks,false,true);
//        System.out.println("");


        System.out.println("====================RecursiveTasks with implicit VT =================================");

        /**
         * generate and run recursive tasks;
         * */

//        MatrixRotatorTask[] recursiveInVTTasks = matrices.stream()
//                .map(MatrixRotatorRecursiveInVirtualThreadsTask::new)
//                .toArray(MatrixRotatorTask[]::new);
//        System.out.println("Before rotation. Number of tasks: " + recursiveInVTTasks.length);

//        System.out.println("Rotate with CompleteableFutre with custom Thread pool");
//        rotateMatrix.rotate90CompletableFutureWithExecutor(recursiveInVTTasks,200);
//        System.out.println("");

//        System.out.println("Rotate recursive tasks");
//        rotateMatrix.rotate90Sequential(recursiveInVTTasks);
//        System.out.println("");

//        System.out.println("Rotate recursive vt tasks with exectuor");
//        rotateMatrix.rotate90VirtualThread(recursiveInVTTasks,true,true);
//        System.out.println();

//        rotateMatrix.rotate90ParallelStream(recursiveInVTTasks);


    }

}
