package utils.vectors;

public class VectorUtil {

    private VectorUtil() {
        throw new AssertionError("An util class");
    }

    public static double error(double[] firstVector, double[] secondVector) {
        int size = firstVector.length;
        double error = 0.0;
        for (int i = 0; i < size; i++) {
            error += (firstVector[i] - secondVector[i])*(firstVector[i] - secondVector[i]);
        }
        return Math.sqrt(error);
    }

}
