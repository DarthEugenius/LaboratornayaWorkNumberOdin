import simple.GaussMethodDoubleImpl;
import simple.ThomasMethodDoubleImpl;
import utils.Utils;

/**
 * @GaussMethodDoubleImpl -
 */


public class Main {
    public static void main(String[] args) {
        double[][] aMatrix = new double[][]{
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
        /*double[] bVector;*/
        /*bVector = Matrix.multiplyByMatrix(aMatrix, xVector);*/
        GaussMethodDoubleImpl solver1 = new GaussMethodDoubleImpl(aMatrix, bVector);
        ThomasMethodDoubleImpl solver2 = new ThomasMethodDoubleImpl(aMatrix, bVector);
        Utils.outPut(solver2.solve());

    }
}
