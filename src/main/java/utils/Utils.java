package utils;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Utils {

    public static void outPut(double[][] A, String info) {
        System.out.println(info);
        for (double[] row : A) {
            for (double element : row) {
                System.out.printf("│\t%f\t│", element);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void outPut(int[][] A, String info) {
        System.out.println(info);
        for (int[] row : A) {
            for (int element : row) {
                System.out.printf("│\t%d\t│", element);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void outPut(double[][] matrix) {
        outPut(matrix, "");
    }

    public static void outPut(int[][] matrix) {
        outPut(matrix, "");
    }

    public static void outPut(double[] vector, String info) {
        System.out.println(info);
        for (double element : vector) {
            /*System.out.println(df.format(element));*/
            System.out.printf("│\t%.30f\t│\n", element);
            /*System.out.println(element);*/
        }
        System.out.println();
    }

    public static void outPut(double[] vector) {
        outPut(vector, "");
    }

    public static void outPut(int[] vector, String info) {
        System.out.println(info);
        for (int element : vector) {
            System.out.printf("│\t%d\t│", element);
            System.out.println();
        }
        System.out.println();
    }


    public static void outPut(int[] vector) {
        outPut(vector, "");
    }

    public static void outPut(double[][] matrix, double[] vector, String info) {
        System.out.println(info);
        int i = 0;
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.printf("│\t%f\t│", element);
            }
            System.out.printf("    │\t%f\t│", vector[i]);
            i++;
            System.out.println();
        }
        System.out.println();
    }

    public static void outPut(double[][] matrix, double[] vector) {

    }



    public static void outPut(int[][] matrix, int[] vector) {
        int i = 0;
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.printf("│\t%d\t│", element);
            }
            System.out.printf("|\t%d\t|", vector[i]);
            i++;
            System.out.println();
        }
        System.out.println();
    }


}
