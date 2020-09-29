package simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.Utils;

@AllArgsConstructor
@Data


public final class GaussMethodDoubleImpl {
    private double[][] aMatrix;
    private double[] bVector;

    private void exceptionsChecking() {
        if (aMatrix.length != bVector.length) {
            throw new IllegalArgumentException("A matrix and B vector has incompatible types");
        }
        if (aMatrix.length != aMatrix[0].length) {
            throw new IllegalArgumentException("A matrix should be of a square");
        }
    }


    /**
     * @return решение СЛАУ
     * aMatrix.length - число уравнений (строк матрицы)
     * exceptionsChecking() - проверка корректности введённой СЛАУ
     */
    public double[] solve() {
        exceptionsChecking();

        // Прямой ход
        for (int i = 0; i < bVector.length; i++) {
            swapRows(i, findRowWithMaxFirstElement(i));
            divideAllRowElementsByNumber(i, i, aMatrix[i][i]);
            setZeroesUnder(i, i);
        }
        /*//TODO
        Utils.outPut(aMatrix, bVector);*/

        for (int i = bVector.length-1; i >= 0; i--) {
            setZerosUp(i, i);
        }
        /*//TODO
        Utils.outPut(aMatrix, bVector);*/
        return bVector;
    }

    /**
     * @return номер строки с максимальным (по модулю) элементом в заданном столбце
     * @param column - заданный столбец
     */
    public int findRowWithMaxFirstElement(int column) {
        if (column == bVector.length) {
            return column;
        }
        int maxIndex = column;
        for (int i = column + 1; i < aMatrix.length; i++) {
            if (Math.abs(aMatrix[i][column]) > Math.abs(aMatrix[maxIndex][column])) maxIndex = i;
        }
        return maxIndex;
    }
    /**
     * Деление строки под номером rowNum начиная с элемента номер colNum на число number
     */
    private void divideAllRowElementsByNumber(int rowNum, int colNum, double number) {
        for (int i = colNum; i < aMatrix.length; i++) {
            aMatrix[rowNum][i] = aMatrix[rowNum][i] / number;
        }
        bVector[rowNum] = bVector[rowNum] / number;
    }

    public void setZeroesUnder(int rowNum, int colNum) {
        final int aMatrixLength = aMatrix.length;
        if (rowNum == aMatrixLength) {
            return;
        }
        for (int i = rowNum + 1; i < aMatrixLength; i++) {
            double multiplier = aMatrix[i][colNum];
            for (int j = colNum; j < aMatrixLength; j++) {
                aMatrix[i][j] = aMatrix[i][j] - multiplier * aMatrix[rowNum][j];
            }
            //работаем с bVector
            bVector[i] = bVector[i] - bVector[rowNum] * multiplier;
        }
       /* Utils.outPut(aMatrix, bVector);*/
    }

    public void setZerosUp(int rowNum, int colNum) {
        int matrixLength = aMatrix.length;
        for (int i = rowNum; i > 0; i--) {
            double alpha = aMatrix[i - 1][colNum];
            for (int j = matrixLength - 1; j > colNum - 1; j--) {
                aMatrix[i - 1][j] = aMatrix[i - 1][j] - aMatrix[rowNum][j] * alpha;
            }
            bVector[i - 1] = bVector[i - 1] - bVector[rowNum] * alpha;
        }
        //TODO
        /*Utils.outPut(aMatrix, bVector);*/
    }

    public void swapRows(int currentIndex, int maxIndex) {
        double[] tempA = aMatrix[currentIndex];
        double tempB = bVector[currentIndex];
        aMatrix[currentIndex] = aMatrix[maxIndex];
        bVector[currentIndex] = bVector[maxIndex];
        aMatrix[maxIndex] = tempA;
        bVector[maxIndex] = tempB;
    }

}
