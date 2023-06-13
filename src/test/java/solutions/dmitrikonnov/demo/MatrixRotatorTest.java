package solutions.dmitrikonnov.demo;




import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    }

    @AfterEach
    void tearDown() {
        System.gc();
    }

    @Test
    @Disabled
    void rotate90VirtualThread() {
    }

    @Test
    @Disabled

    void testRotate90VirtualThread() {
    }

    @Test
    @Disabled

    void rotate90VirtualThreadWithFactory() {
    }

    @Disabled

    void rotate90VirtualThreadWithExecutor() {
    }

    @Test
    @Disabled

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
    @Disabled
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

    @Disabled
    void rotate90CompletableFuture() {
    }

    @Test

    @Disabled
    void rotate90CompletableFutureWithExecutor() {
    }

    @Test

    @Disabled
    void rotate90ParallelStream() {
    }

}