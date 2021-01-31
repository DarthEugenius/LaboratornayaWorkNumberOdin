import simple.GaussMethodDoubleImpl;
import simple.SimpleIterationDoubleImpl;
import simple.ThomasMethodDoubleImpl;
import utils.Builders.MatrixBuilder;
import utils.Builders.VectorBuilder;
import utils.InputUtils;
import utils.Matrix.Matrix;

import java.util.concurrent.TimeUnit;

import static utils.vectors.VectorUtil.error;

public class Starter {
    public static void main(String[] args) {

        System.out.println("Размер системы: ");
        int n = InputUtils.getInt();
        System.out.println("epsilon для метода простых итераций: ");
        double epsilon = InputUtils.getDouble();
        System.out.println("----------------------------------------------------------------\n");
        double[] xVector = VectorBuilder.buildRandomlyGeneratedVectorOfDoubles(n, -10, 10);

        Runnable GaussMethodRunnable = () -> {

            double[][] aMatrixForGaussMethod = MatrixBuilder.buildRandomlyGeneratedMatrix(n, n);
            double[] bVectorForGaussMethod = Matrix.multiplyByMatrix(aMatrixForGaussMethod, xVector);
            GaussMethodDoubleImpl gaussMethod = new GaussMethodDoubleImpl(aMatrixForGaussMethod, bVectorForGaussMethod, true);
            long then = System.nanoTime();
            double[] xVectorCalculated = gaussMethod.solve();
            double millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - then);
            System.out.println("Gauss method error: " + error(xVectorCalculated, xVector));
            System.out.printf("Gauss method time: %.20f seconds\n", millis / 1000);
            System.out.println("----------------------------------------------------------------");
        };

        Runnable ThomasMethodRunnable = () -> {

            double[][] aMatrix = MatrixBuilder.buildRandomlyGeneratedTripleDiagMatrix(n, n);
            double[] bVector = Matrix.multiplyByMatrix(aMatrix, xVector);
            ThomasMethodDoubleImpl thomasMethod = new ThomasMethodDoubleImpl(aMatrix, bVector);
            long then = System.nanoTime();
            double[] xVectorCalculated = thomasMethod.solve();
            double nanos = TimeUnit.NANOSECONDS.toNanos(System.nanoTime() - then);
            System.out.println("Thomas method error: " + error(xVectorCalculated, xVector));
            System.out.printf("Thomas method time: %.20f seconds\n", nanos / 1000000000);
            System.out.println("----------------------------------------------------------------");
        };

        Runnable SimpleIterationsMethodRunnable = () -> {
            double[][] aMatrix = MatrixBuilder.buildRandomlyGeneratedMatrixWithBiggerDiagElems(n, n);
            double[] bVector = Matrix.multiplyByMatrix(aMatrix, xVector);
            SimpleIterationDoubleImpl simpleIterationsMethod = new SimpleIterationDoubleImpl(aMatrix, bVector, epsilon);
            long then = System.nanoTime();
            double[] xVectorCalculated = simpleIterationsMethod.solve();
            double millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - then);
            System.out.println("Simple iterations method error: " + error(xVectorCalculated, xVector));
            System.out.printf("Simple iterations method time: %.20f seconds\n", millis / 1000);
            System.out.println("----------------------------------------------------------------");
            /*OutPutUtils.outPut(aMatrix);*/
        };

        (new Thread(GaussMethodRunnable)).start();
        (new Thread(ThomasMethodRunnable)).start();
        (new Thread(SimpleIterationsMethodRunnable)).start();
    }
}
