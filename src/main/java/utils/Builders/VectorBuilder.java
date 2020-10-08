package utils.Builders;

import java.util.concurrent.ThreadLocalRandom;


import java.util.concurrent.ThreadLocalRandom;

public final class VectorBuilder {

    private VectorBuilder() {
        throw new AssertionError("VectorBuilder is an util class");
    }

    public static double[] buildRandomlyGeneratedVectorOfDoubles(int numberOfRows, double lowerBound, double upperBound) {
        double[] matrix = new double[numberOfRows];

        for (int i = 0; i < numberOfRows; i++) {

            matrix[i] = ThreadLocalRandom.current().nextDouble(lowerBound, upperBound);
        }

        return matrix;
    }

    public static double[] buildVectorOfZeros(int numberOfRows) {
        double[] vector = new double[numberOfRows];

        for (int i = 0; i < numberOfRows; i++) {
            vector[i] = 0;
        }

        return vector;
    }
}
