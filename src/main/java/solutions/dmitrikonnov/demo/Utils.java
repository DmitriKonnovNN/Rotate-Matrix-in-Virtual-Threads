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

    public static int[][] generateExpandingSpiral2DMatrix(int finalNumber){
        if (finalNumber == 1) {
            int [][] matrix =  new int[1][1];
            matrix[0][0] = finalNumber;
            return matrix;
        }
        var sizeAndLayers = defineSize(finalNumber);
        int size = sizeAndLayers[0];
        int layers = sizeAndLayers[1];
        int [][] matrix = new int[size][size];
        int midI, midJ;
        midI = midJ = matrix.length/2;
        matrix[midI][midJ] = 1;
        int [] counter = new int[1];
        counter[0] = 1;
        for(int layer = 2; layer <= layers; layer++){
            generateLayers(matrix, layer, finalNumber, midI, midJ, counter);
        }

        return matrix;
    }

    private static void generateLayers(int[][] matrix, int layer, int finalNumber,int startI, int startJ, int []counter){
        int number = (layer-1)*2;
        int shift = layer-1;

            right(startI, startJ, matrix,counter,number,finalNumber,shift);
            bottom(startI, startJ, matrix,counter,number,finalNumber,shift);
            left(startI, startJ, matrix,counter,number,finalNumber,shift);
            top(startI, startJ, matrix,counter,number,finalNumber,shift);
    }
    private static void right(int i, int j, int[][]m, int[]counter, int number, int finalNumber, int shift){
        j+=shift;
        i-=shift-1;
        for(int k = 0; k < number && counter[0]<finalNumber; k++) {
            counter[0]++;
            m[i+k][j]=counter[0];
        }
    }
    private static void bottom(int i, int j, int[][]m, int[]counter, int number, int finalNumber, int shift){
        i+=shift;
        j+=shift-1;
        for(int k = 0; k < number && counter[0]<finalNumber; k++) {
            counter[0]++;
            m[i][j-k]=counter[0];
        }
    }
    private static void left(int i, int j, int[][]m, int[]counter, int number, int finalNumber, int shift){
        j-=shift;
        i+=shift-1;
        for(int k = 0; k < number && counter[0]<finalNumber; k++) {
            counter[0]++;
            m[i-k][j]=counter[0];
        }
    }
    private static void top(int i, int j, int[][]m, int[]counter, int number, int finalNumber, int shift){
        i-=shift;
        j-=shift-1;
        for(int k = 0; k < number && counter[0]<finalNumber; k++) {
            counter[0]++;
            m[i][j+k]=counter[0];
        }
    }

    private static int[] defineSize(int finalNumber){
        int layers = 1;
        int product = 1;
        int i = 1;
        while(product < finalNumber){
            i+=2;
            product = i*i;
            layers++;
        }
        int [] sizeAndLayers = new int[2];
        sizeAndLayers[0]= i;
        sizeAndLayers[1]= layers;

        return sizeAndLayers;
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
