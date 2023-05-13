package solutions.dmitrikonnov.demo;

import java.time.Duration;

public class Utils {

    public static void generateHighCpuLoad (){
        double result = 0;
        int iterations = (int) (Math.random() * 1000);
        for (int i = 0; i < iterations; i++) {
            result += Math.sqrt(i);
        }
        System.out.println(">>computation result: " + result );
    }

    public static void mockDelay(){
        try {
            Duration delay = Duration.ofSeconds((int) (Math.random() * 10));
            System.out.println("delay " + delay);
            Thread.sleep(delay);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int [][] generateRandom2DMatrix(){
        int length = (int) (Math.random() * 100);
        int width = (int) (Math.random() * 100);
        return generateMatrix(length, width);
    }

    public static int [][] generateRandom2DMatrix(int length, int width){
        return generateMatrix(length, width);
    }

    public static int [][] generateRandom2DMatrix(int size){
        return generateMatrix(size, size);
    }

    public static void printMatrix (int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static int [][] generateMatrix(int length, int width){
        int[][] matrix = new int[length][width];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }
}
