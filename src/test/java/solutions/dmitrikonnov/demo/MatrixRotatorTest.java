package solutions.dmitrikonnov.demo;



import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

class MatrixRotatorTest {
    RotateMatrix rotateMatrix;
    MatrixRotatorTask[] recursiveTasks;
    MatrixRotatorTask[] sequentialTasks;
    MatrixRotatorTask[] recursiveInVTTasks;
    List<int[][]> matrices;
    List<int[][]> expectedResult;

    @BeforeEach
    void setUp() {
        final int size = 20;
        final int numberOfMatrices = 1;
        rotateMatrix = new MatrixRotator();

        matrices  = new ArrayList<>();
        IntStream.range(0,numberOfMatrices).forEachOrdered(index-> matrices.add(Utils.generateRandom2DMatrix(size)));
        expectedResult  = matrices.stream().map(matrix -> Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new)).toList();

        MatrixRotator matrixRotator2 = new MatrixRotator();
        MatrixRotatorTask[] tasks2 = expectedResult.stream().map(MatrixRotatorSequentialTask::new).toArray(MatrixRotatorTask[]::new);
        matrixRotator2.rotate90ParallelStream(tasks2);

        recursiveTasks = matrices.stream()
                .map(MatrixRotatorRecursiveTask::new)
                .toArray(MatrixRotatorTask[]::new);
        sequentialTasks = matrices.stream()
                .map(MatrixRotatorSequentialTask::new)
                .toArray(MatrixRotatorTask[]::new);
        recursiveInVTTasks = matrices.stream()
                .map(MatrixRotatorRecursiveInVirtualThreadsTask::new)
                .toArray(MatrixRotatorTask[]::new);
    }

    @AfterEach
    void tearDown() {
        System.gc();
    }

    @Test
    @Disabled
    @Ignore
    void rotate90VirtualThread() {
    }

    @Test
    @Disabled
    @Ignore
    void testRotate90VirtualThread() {
    }

    @Test
    @Disabled
    @Ignore
    void rotate90VirtualThreadWithFactory() {
    }

    @Disabled
    @Ignore
    void rotate90VirtualThreadWithExecutor() {
    }

    @Test
    @Disabled
    @Ignore
    void rotate90VirtualThreadWithExecutorAndFactory() {
    }

    @Test
    @Disabled
    void rotate90SequentialSTs() {
        rotateMatrix.rotate90Sequential(sequentialTasks);
        matrices.stream()
                .forEachOrdered(m-> expectedResult.stream()
                        .forEachOrdered(exp2 -> assertTrue(deepEquals(m,exp2))));

    }
    @Test
    void rotate90SequentialRTs() {
        rotateMatrix.rotate90Sequential(recursiveTasks);
        matrices.stream()
                .forEachOrdered(m-> expectedResult.stream()
                        .forEachOrdered(exp2 -> assertTrue(deepEquals(m,exp2))));
    }
    @Test
    @Disabled
    void rotate90SequentialRTsVT() {
        rotateMatrix.rotate90Sequential(recursiveInVTTasks);
        matrices.stream()
                .forEachOrdered(m-> expectedResult.stream()
                        .forEachOrdered(exp2 -> assertTrue(deepEquals(m,exp2))));

    }


    @Test
    @Ignore
    @Disabled
    void rotate90CompletableFuture() {
    }

    @Test
    @Ignore
    @Disabled
    void rotate90CompletableFutureWithExecutor() {
    }

    @Test
    @Ignore
    @Disabled
    void rotate90ParallelStream() {
    }

}