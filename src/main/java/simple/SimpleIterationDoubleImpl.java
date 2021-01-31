package simple;


import utils.Builders.VectorBuilder;

import static utils.vectors.VectorUtil.error;

public class SimpleIterationDoubleImpl {
    private final double[][] aMatrix;
    private final double[] bVector;
    private final double epsilon;

    /**
     * Должно быть сильное диаганальное преобладание, иначе решение не сходится
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

    // Проверка корректности исходных данных
    private void exceptionsChecking() {
        if (aMatrix.length != bVector.length) {
            throw new IllegalArgumentException("A matrix and B vector has incompatible types");
        }
        if (aMatrix.length != aMatrix[0].length) {
            throw new IllegalArgumentException("A matrix should be of a square");
        }
    }

    public double[] solve() {
        exceptionsChecking();
        final int size = bVector.length;
        exceptionsChecking();
        int n = 0;
        double[] previousVariableValues = VectorBuilder.buildVectorOfZeros(size);
        while (true) {
            double[] currentVariableValues = VectorBuilder.buildVectorOfZeros(size);
            for (int i = 0; i < size; i++) {
                currentVariableValues[i] = bVector[i];
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        currentVariableValues[i] -= aMatrix[i][j] * previousVariableValues[j];
                    }
                }
                currentVariableValues[i] /= aMatrix[i][i];
            }
            if (error(currentVariableValues, previousVariableValues) < epsilon) {
                break;
            }
            previousVariableValues = currentVariableValues;
            n++;
        }
        System.out.println("iterations: " + n);
        return previousVariableValues;
    }

}
