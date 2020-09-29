package simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.Builders.VectorBuilder;

@AllArgsConstructor
@Data
public class SimpleIterationDoubleImpl {
    private double[][] aMatrix;
    private double[] bVector;
    private double epsilon;
    private void exceptionsChecking() {
        if (aMatrix.length != bVector.length) {
            throw new IllegalArgumentException("A matrix and B vector has incompatible types");
        }
        if (aMatrix.length != aMatrix[0].length) {
            throw new IllegalArgumentException("A matrix should be of a square");
        }
    }
    public double[] solve() {
        final int size = bVector.length;
        exceptionsChecking();

        double[] xVector = new double[size];
            // Считываем размер вводимой матрицы
            // Будем хранить матрицу в векторе, состоящем из
            // векторов вещественных чисел
            // Матрица будет иметь размер (size) x (size + 1),
            // c учетом столбца свободных членов
            // Считываем необходимую точность решения
            // Введем вектор значений неизвестных на предыдущей итерации,
            // размер которого равен числу строк в матрице, т.е. size,
            // причем согласно методу изначально заполняем его нулями
            double[] previousVariableValues = VectorBuilder.builder().rows(size).build().buildVectorOfZeros();
            // Будем выполнять итерационный процесс до тех пор,
            // пока не будет достигнута необходимая точность
            while (true)
            {
                // Введем вектор значений неизвестных на текущем шаге
                double[] currentVariableValues = VectorBuilder.builder().rows(size).build().buildVectorOfZeros();

                // Посчитаем значения неизвестных на текущей итерации
                // в соответствии с теоретическими формулами
                for (int i = 0; i < size; i++)
                {
                    // Инициализируем i-ую неизвестную значением
                    // свободного члена i-ой строки матрицы
                    currentVariableValues[i] = bVector[i];

                    // Вычитаем сумму по всем отличным от i-ой неизвестным
                    for (int j = 0; j < size; j++)
                    {
                        if (i != j)
                        {
                            currentVariableValues[i] -= aMatrix[i][j] * previousVariableValues[j];
                        }
                    }

                    // Делим на коэффициент при i-ой неизвестной
                    currentVariableValues[i] /= aMatrix[i][i];
                }

                // Посчитаем текущую погрешность относительно предыдущей итерации
                double error = 0.0;

                for (int i = 0; i < size; i++)
                {
                    error += Math.abs(currentVariableValues[i] - previousVariableValues[i]);
                }

                // Если необходимая точность достигнута, то завершаем процесс
                if (error < epsilon)
                {
                    break;
                }

                // Переходим к следующей итерации, так
                // что текущие значения неизвестных
                // становятся значениями на предыдущей итерации
                previousVariableValues = currentVariableValues;
            }

            // Выводим найденные значения неизвестных с 8 знаками точности
           return previousVariableValues;
        }

}
