package simple;


import utils.Builders.VectorBuilder;

public class SimpleIterationDoubleImpl {
    private final double[][] aMatrix;
    private final double[] bVector;
    private final double epsilon;

    public SimpleIterationDoubleImpl(double[][] aMatrix, double[] bVector, double epsilon) {
        this.aMatrix = aMatrix.clone();
        this.bVector = bVector.clone();
        this.epsilon = epsilon;
    }

    private void exceptionsChecking() {
        if (aMatrix.length != bVector.length) {
            throw new IllegalArgumentException("A matrix and B vector has incompatible types");
        }
        if (aMatrix.length != aMatrix[0].length) {
            throw new IllegalArgumentException("A matrix should be of a square");
        }
    }
    public double[] solve() {
        final int size = bVector.length;
        exceptionsChecking();

            double[] previousVariableValues = VectorBuilder.builder().rows(size).build().buildVectorOfZeros();

            while (true)
            {

                double[] currentVariableValues = VectorBuilder.builder().rows(size).build().buildVectorOfZeros();

                for (int i = 0; i < size; i++)
                {

                    currentVariableValues[i] = bVector[i];

                    for (int j = 0; j < size; j++)
                    {
                        if (i != j)
                        {
                            currentVariableValues[i] -= aMatrix[i][j] * previousVariableValues[j];
                        }
                    }

                    currentVariableValues[i] /= aMatrix[i][i];
                }

                double error = 0.0;

                for (int i = 0; i < size; i++)
                {
                    error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
                }

                if (error < epsilon)
                {
                    break;
                }
                previousVariableValues = currentVariableValues;
            }
           return previousVariableValues;
        }

}
