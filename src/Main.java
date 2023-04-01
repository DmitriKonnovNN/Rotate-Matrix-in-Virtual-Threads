import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        var randomMatrix = createRandomMatrix(3,5);
        printMatrix(randomMatrix);
        System.out.println("====================================");
        printMatrix(rotateMatrix90(randomMatrix));

        var randomMatrix2 = createRandomMatrix(7,9);
        printMatrix(randomMatrix2);
        System.out.println("====================================");
        printMatrix(rotateMatrix180(randomMatrix2));

        var randomMatrix3 = createRandomMatrix(10,12);
        printMatrix(randomMatrix3);
        System.out.println("====================================");
        printMatrix(rotateMatrix270(randomMatrix3));

        var randomMatrix4 = createRandomMatrix(10000,10000);
        //printMatrix(randomMatrix4);
        System.out.println("====================================");
        var newEqualMatrix = rotateMatrix360(randomMatrix4);
        //printMatrix(newEqualMatrix);
        System.out.println(Arrays.deepEquals(randomMatrix4, newEqualMatrix));

    }

    // TODO: 1. Rotate each matrix outer layer in separate virtual thread and join them after all threads are done.
    // TODO: 2. Add animation to the rotation process.
    public static int[][] rotateMatrix90(int [][] matrix){
//      initialization with reversed length and width lets us create a new matrix with length and width of not equal size;
        int[][] newMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                newMatrix[j][matrix.length - 1 - i] = matrix[i][j];
            }
        }
        return newMatrix;
    }


    public static void printMatrix (int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int [][] rotateMatrix360(int[][] matrix) {
        return rotateMatrix90(rotateMatrix270(matrix));
    }

    public static int [][] rotateMatrix180(int[][] matrix) {
        return rotateMatrix90(rotateMatrix90(matrix));
    }

    public static int [][] rotateMatrix270(int[][] matrix) {
        return rotateMatrix90(rotateMatrix180(matrix));
    }

    public static int [][] createRandomMatrix(int length, int width){
        int[][] matrix = new int[length][width];
        for (int i = 0; i < length; i++){
            for (int j = 0; j < width; j++){
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }



    }
