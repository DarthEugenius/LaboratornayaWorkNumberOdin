package simple;


import utils.Builders.VectorBuilder;

public class SimpleIterationDoubleImpl {
    private final double[][] aMatrix;
    private final double[] bVector;
    private final double epsilon;

    /**
     * ДОЛЖНО БЫТЬ СИЛЬНОЕ ДИАГАНАЛЬНОЕ ПРЕОБЛАДАНИЕ!!!!!!!!!!!
     *
     * @param aMatrix квадратная матрица системы
     * @param bVector столбец свободных членов
     * @param epsilon точность
     */

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
        /* DEBUG
        Utils.outPut(aMatrix, bVector);*/

        double[] previousVariableValues = VectorBuilder.buildVectorOfZeros(size);

        while (true) {

            double[] currentVariableValues = VectorBuilder.buildVectorOfZeros(size);

            for (int i = 0; i < size; i++) {
                // 1ое приближение - столбец свободных членов
                currentVariableValues[i] = bVector[i];

                // приведение системы к нормальному виду
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        currentVariableValues[i] -= aMatrix[i][j] * previousVariableValues[j];
                    }
                }

                currentVariableValues[i] /= aMatrix[i][i];
            }

            double error = 0.0;

            for (int i = 0; i < size; i++) {
                error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
            }

            if (error < epsilon) {
                break;
            }
            previousVariableValues = currentVariableValues;
        }
        return previousVariableValues;
    }


}
