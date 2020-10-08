import simple.GaussMethodDoubleImpl;
import simple.SimpleIterationDoubleImpl;
import simple.ThomasMethodDoubleImpl;
import utils.Builders.MatrixBuilder;
import utils.Builders.VectorBuilder;
import utils.Utils;
import utils.Matrix.Matrix;


import static utils.Matrix.Matrix.subtractVectors;

public class Main {
    public static void main(String[] args) {

        int n = 10;
        double[] xVector = VectorBuilder.buildRandomlyGeneratedVectorOfDoubles(n, -10, 10);

        double[][] aMatrixForGaussMethod = MatrixBuilder.buildRandomlyGeneratedMatrix(n, n);
        double[][] aMatrixForThomasMethod = MatrixBuilder.buildRandomlyGeneratedTripleDiagMatrix(n, n);
        double[][] aMatrixForSimpleIterationsMethod = MatrixBuilder.buildRandomlyGeneratedMatrixWithBiggerDiagElems(n, n);

        double[] bVectorForGaussMethod = Matrix.multiplyByMatrix(aMatrixForGaussMethod, xVector);
        double[] bVectorForThomasMethod = Matrix.multiplyByMatrix(aMatrixForThomasMethod, xVector);
        double[] bVectorForSimpleIterationsMethod = Matrix.multiplyByMatrix(aMatrixForSimpleIterationsMethod, xVector);

        GaussMethodDoubleImpl gaussMethodDouble = new GaussMethodDoubleImpl(aMatrixForGaussMethod, bVectorForGaussMethod);
        ThomasMethodDoubleImpl thomasMethodDouble = new ThomasMethodDoubleImpl(aMatrixForThomasMethod, bVectorForThomasMethod);
        SimpleIterationDoubleImpl simpleIterationDouble = new SimpleIterationDoubleImpl(aMatrixForSimpleIterationsMethod, bVectorForSimpleIterationsMethod, 0.0000000000001);

        double[] xVectorGaussMethod = gaussMethodDouble.solve();
        double[] xVectorThomasMethod = thomasMethodDouble.solve();
        double[] xVectorSimpleIterationsMethod = simpleIterationDouble.solve();

        Utils.outPut(xVectorGaussMethod, "Решение методом Гаусса");
        Utils.outPut(subtractVectors(xVectorGaussMethod, xVector), "Погрешность решения методом Гаусса");

        Utils.outPut(xVectorThomasMethod, "Решение методом прогонки");
        Utils.outPut(subtractVectors(xVectorThomasMethod, xVector), "Погрешность решения методом прогонки");

        Utils.outPut(xVectorSimpleIterationsMethod, "Решение методом простых итераций");
        Utils.outPut(subtractVectors(xVectorSimpleIterationsMethod, xVector), "Погрешность решения методом простых итераций");



    }
}

/*double[][] testMatrix = new double[][]{
                {
                        2.0, -1.0, 0.0, 0.0, 0.0
                },
                {
                        -3.0, 8.0, -1.0, 0.0, 0.0
                },
                {
                        0.0, -5.0, 12.0, 2.0, 0.0
                },
                {
                        0.0, 0.0, -6.0, 18.0, -4.0
                },
                {
                        0.0, 0.0, 0.0, -5.0, 10.0
                }
        };*/
