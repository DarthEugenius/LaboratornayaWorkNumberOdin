package utils.Builders;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@Builder
public class VectorBuilder {
    private final int rows;

    public double[] buildRandomlyGeneratedVectorOfDoubles(double lowerBound, double upperBound) {
        double[] matrix = new double[rows];
        for (int i = 0; i < rows; i++) {

            matrix[i] = ThreadLocalRandom.current().nextDouble(lowerBound, upperBound);
        }
        return matrix;
    }

    public double[] buildRandomlyGeneratedVectorOfDoubles() {
        return buildRandomlyGeneratedVectorOfDoubles(-10.0, 10.0);
    }
    public double[] buildVectorOfZeros()
    {
        double[] vector = new double[rows];
        for (int i = 0; i < rows; i++) {
            vector[i] = 0;
        }
        return vector;
    }







}
