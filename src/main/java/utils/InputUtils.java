package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputUtils {

    private InputUtils() {
        throw new AssertionError("An util class");
    }

    public static String getLine() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Incorrect input format");
        }
    }

    public static double getDouble() {
        return Double.parseDouble(getLine());
    }

    public static int getInt() {
        return Integer.parseInt(getLine());
    }
}
