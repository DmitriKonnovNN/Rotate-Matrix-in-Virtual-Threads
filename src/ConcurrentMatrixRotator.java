import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConcurrentMatrixRotator {
    public static void main(String[] args) {
        int size = 10;
        int numberOfMatrices = 1;
        //      initialization with reversed length and width lets us create a new matrix with length and width of not equal size;
        //   int[][] newMatrix = new int[matrix[0].length][matrix.length]; but where?
        int length = 5;
        int width = 10;
        RotateMatrix rotateMatrix = new MatrixRotator();
//        var matrix = Utils.generateRandom2DMatrix(size);
//        Utils.printMatrix(matrix);

        /**
         * generate list of matrices and print those;
         * */
        final List<int[][]> matrices = new ArrayList<>();
        IntStream.range(0,numberOfMatrices).forEach(index->{
            matrices.add(Utils.generateRandom2DMatrix(size));
        });
        matrices.forEach(Utils::printMatrix);


        /**
         * generate and run simple tasks;
         * */

        MatrixRotatorTask[] simpleTasks = matrices.stream()
                .map(MatrixRotatorRecursiveTask::new)
                .toArray(MatrixRotatorTask[]::new);
        System.out.println("Before rotation. Number of tasks: " + simpleTasks.length);


        rotateMatrix.rotate90Sequential(simpleTasks);
        System.out.println("Sequentially rotated matrices:");
        matrices.forEach(Utils::printMatrix);
    }

}
