package simple;


public class ThomasMethodDoubleImpl {
    private final double[][] aMatrix;
    private final double[] bVector;
    private final double[] aArray;
    private final double[] bArray;
    private final double[] cArray;

    private final double[] xVector;

    public ThomasMethodDoubleImpl(final double[][] aMatrix, final double[] bVector) {
        this.aMatrix = aMatrix;
        this.bVector = bVector;
        this.aArray = aArrayCreate();
        this.bArray = bArrayCreate();
        this.cArray = cArrayCreate();
        this.xVector = new double[aMatrix.length];
    }

    /**
     * @return решение СЛАУ
     * aMatrix.length - число строк матрицы
     * bArray - диагональ, лежащая над главной (нумеруется: [0;n-2], b[n-1] = 0)
     * aArray - главная диагональ матрицы A (нумеруется: [0;n-1])
     * cArray - диагональ, лежащая под главной (нумеруется: [0;n-1], cArray[0] = 0)
     * bVector - правая часть (столбец)
     */
    public double[] solve() {

        double m;
        for (int i = 1; i < aMatrix.length; i++) {
            m = cArray[i - 1] / aArray[i - 1];
            aArray[i] = aArray[i] - m * bArray[i - 1];
            bVector[i] = bVector[i] - m * bVector[i - 1];
        }

        xVector[aMatrix.length - 1] = bVector[aMatrix.length - 1] / aArray[aMatrix.length - 1];

        for (int i = aMatrix.length - 2; i >= 0; i--) {
            xVector[i] = (bVector[i] - bArray[i] * xVector[i + 1]) / aArray[i];
        }
        return xVector;
    }

    public double[] aArrayCreate() {
        double[] a = new double[aMatrix.length];
        for (int i = 0; i < aMatrix.length; i++) {
            a[i] = aMatrix[i][i];
        }
        return a;
    }

    public double[] bArrayCreate() {
        double[] b = new double[aMatrix.length - 1];
        for (int i = 0; i < aMatrix.length - 1; i++) {
            b[i] = aMatrix[i][i + 1];
        }
        return b;
    }

    public double[] cArrayCreate() {
        double[] c = new double[aMatrix.length - 1];
        for (int i = 0; i < aMatrix.length - 1; i++) {
            c[i] = aMatrix[i + 1][i];
        }
        return c;
    }

    private void exceptionsChecking() {
        if (aMatrix.length != bVector.length) {
            throw new IllegalArgumentException("A matrix and B vector has incompatible types");
        }
        if (aMatrix.length != aMatrix[0].length) {
            throw new IllegalArgumentException("A matrix should be of a square");
        }
    }

}