public class Matrix {
    private final ComplexNumber[][] data;
    private final int rows;
    private final int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new ComplexNumber[rows][cols];
    }

    public void setElement(int i, int j, ComplexNumber value) {
        data[i][j] = value;
    }

    public ComplexNumber getElement(int i, int j) {
        return data[i][j];
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(i, j, this.data[i][j].add(other.data[i][j]));
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно совпадать количеству строк второй матрицы для умножения");
        }
        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    ComplexNumber product = this.data[i][k].multiply(other.data[k][j]);
                    sum = sum.add(product);
                }
                result.setElement(i, j, sum);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setElement(j, i, this.data[i][j]);
            }
        }
        return result;
    }

    public ComplexNumber determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Определитель можно найти только для квадратной матрицы!");
        }
        return calculateDeterminant(this.data);
    }

    private ComplexNumber calculateDeterminant(ComplexNumber[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            return matrix[0][0].multiply(matrix[1][1])
                    .subtract(matrix[0][1].multiply(matrix[1][0]));
        }

        ComplexNumber det = new ComplexNumber(0, 0);

        for (int col = 0; col < n; col++) {
            ComplexNumber[][] minor = getMinor(matrix, 0, col);
            ComplexNumber minorDet = calculateDeterminant(minor);
            ComplexNumber term = matrix[0][col].multiply(minorDet);

            if (col % 2 != 0) {
                term = term.negate();
            }

            det = det.add(term);
        }

        return det;
    }

    private ComplexNumber[][] getMinor(ComplexNumber[][] matrix, int row, int col) {
        int n = matrix.length;
        ComplexNumber[][] minor = new ComplexNumber[n - 1][n - 1];

        int minorRow = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;

            int minorCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;

                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    }
}