import simple.GaussMethodDoubleImpl;
import simple.SimpleIterationDoubleImpl;
import simple.ThomasMethodDoubleImpl;
import utils.Builders.MatrixBuilder;
import utils.Builders.VectorBuilder;
import utils.InputUtils;
import utils.OutPutUtils;
import utils.Matrix.Matrix;


import static utils.Matrix.Matrix.subtractVectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("Размер системы: ");
        int n = InputUtils.getInt();

        System.out.println("epsilon для метода простых итераций: ");
        double epsilon = InputUtils.getDouble();
        double[] xVector = VectorBuilder.buildRandomlyGeneratedVectorOfDoubles(n, -10, 10);

        double[][] aMatrixForGaussMethod = MatrixBuilder.buildRandomlyGeneratedMatrix(n, n);
        double[][] aMatrixForThomasMethod = MatrixBuilder.buildRandomlyGeneratedTripleDiagMatrix(n, n);
        double[][] aMatrixForSimpleIterationsMethod = MatrixBuilder.buildrandomlygeneratedmatrixwithbiggerdiagelems(n, n);

        double[] bVectorForGaussMethod = Matrix.multiplyByMatrix(aMatrixForGaussMethod, xVector);
        double[] bVectorForThomasMethod = Matrix.multiplyByMatrix(aMatrixForThomasMethod, xVector);
        double[] bVectorForSimpleIterationsMethod = Matrix.multiplyByMatrix(aMatrixForSimpleIterationsMethod, xVector);

        OutPutUtils.outPut(aMatrixForGaussMethod, bVectorForGaussMethod, "Метод Гаусса: ");

        GaussMethodDoubleImpl gaussMethodDouble = new GaussMethodDoubleImpl(aMatrixForGaussMethod, bVectorForGaussMethod);
        ThomasMethodDoubleImpl thomasMethodDouble = new ThomasMethodDoubleImpl(aMatrixForThomasMethod, bVectorForThomasMethod);
        SimpleIterationDoubleImpl simpleIterationDouble = new SimpleIterationDoubleImpl(aMatrixForSimpleIterationsMethod, bVectorForSimpleIterationsMethod, epsilon);

        double[] xVectorGaussMethod = gaussMethodDouble.solve();
        double[] xVectorThomasMethod = thomasMethodDouble.solve();
        double[] xVectorSimpleIterationsMethod = simpleIterationDouble.solve();

        OutPutUtils.outPut(xVectorGaussMethod, "Решение методом Гаусса");
        OutPutUtils.outPut(subtractVectors(xVectorGaussMethod, xVector), "Погрешность решения методом Гаусса");

        OutPutUtils.outPut(aMatrixForThomasMethod, bVectorForThomasMethod, "Метод прогонки: ");
        OutPutUtils.outPut(xVectorThomasMethod, "Решение методом прогонки");
        OutPutUtils.outPut(subtractVectors(xVectorThomasMethod, xVector), "Погрешность решения методом прогонки");

        OutPutUtils.outPut(aMatrixForSimpleIterationsMethod, bVectorForSimpleIterationsMethod, "Метод прогонки: ");
        OutPutUtils.outPut(xVectorSimpleIterationsMethod, "Решение методом простых итераций");
        OutPutUtils.outPut(subtractVectors(xVectorSimpleIterationsMethod, xVector), "Погрешность решения методом простых итераций, epsilon = " + epsilon);

    }
}



   /* double[][] aMatrix = new double[][]{
            {
                    2.0, 5.0, 7.0, 11.0
            },
            {
                    99.0, 22.0, 75.0, 66.0
            },
            {
                    223.0, 228.0, 12.0, 9.0
            },
            {
                    4.0, 3.0, 2.0, 1.0
            }
    };*/