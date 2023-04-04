import java.time.Instant;

public class MatrixRotatorTask {
    private final int[][] matrix;


    public MatrixRotatorTask(int[][] matrix) {
        this.matrix = matrix;
    }

    public void rotate(int [][] matrix){

//      initialization with reversed length and width lets us create a new matrix with length and width of not equal size;

        MatrixRotator.rotate(matrix);
    }

}
