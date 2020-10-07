import simple.GaussMethodDoubleImpl;
import simple.SimpleIterationDoubleImpl;
import simple.ThomasMethodDoubleImpl;
import utils.Builders.MatrixBuilder;
import utils.Builders.VectorBuilder;
import utils.Utils;

public class Main {
    public static void main(String[] args) {
        double[][] testMatrix = new double[][]{
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
        };

        double[] bVector = new double[]{-25.0, 72.0, -69.0, -156.0, 20};
        int n = 10;
        /*double[][] aMatrix = MatrixBuilder.builder().columns(n).rows(n).build().buildRandomlyGeneratedMatrixWithBiggerDiagElemsOfDoubles();*/
       /* double[] bVector = VectorBuilder.builder().rows(n).build().buildRandomlyGeneratedVectorOfDoubles(0, 1);*/
        Utils.outPut(testMatrix, bVector);
        SimpleIterationDoubleImpl solver3 = new SimpleIterationDoubleImpl(MatrixBuilder.builder().columns(n).rows(n).build().buildRandomlyGeneratedMatrixWithBiggerDiagElemsOfDoubles(), VectorBuilder.builder().rows(n).build().buildRandomlyGeneratedVectorOfDoubles(0, 1), 0.001);
       /* double[][] testMatrix = MatrixBuilder.builder().rows(n).columns(n).build().buildRandomlyGeneratedTripleDiagMatrix();*/
        ThomasMethodDoubleImpl solver2 = new ThomasMethodDoubleImpl(testMatrix, bVector);
        GaussMethodDoubleImpl solver1 = new GaussMethodDoubleImpl(testMatrix, bVector);
        Utils.outPut(solver1.solve(), "Gauss");
        Utils.outPut(solver3.solve(), "Thomas huyomas");

    }
}
