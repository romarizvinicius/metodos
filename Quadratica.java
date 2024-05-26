import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quadratica {
    public static double[] quadraticFit(double[] xs, double[] ys) {
        int n = xs.length;
        double[][] A = new double[n][3];
        double[] b = new double[n];

        for (int i = 0; i < n; i++) {
            double x = xs[i];
            A[i][0] = x * x;
            A[i][1] = x;
            A[i][2] = 1;
            b[i] = ys[i];
        }

        return solveLinearSystem(A, b);
    }

    public static double solveQuadratic(double[] coeffs, double x) {
        return coeffs[0] * x * x + coeffs[1] * x + coeffs[2];
    }

    private static double[] solveLinearSystem(double[][] A, double[] b) {
        int n = b.length;
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = b[i];
        }

        for (int k = 0; k < n; k++) {
            int max = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(A[i][k]) > Math.abs(A[max][k])) {
                    max = i;
                }
            }
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            double t = x[k];
            x[k] = x[max];
            x[max] = t;

            for (int i = k + 1; i < n; i++) {
                double factor = A[i][k] / A[k][k];
                x[i] -= factor * x[k];
                for (int j = k; j < 3; j++) {
                    A[i][j] -= factor * A[k][j];
                }
            }
        }

        double[] solution = new double[3];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < 3; j++) {
                sum += A[i][j] * solution[j];
            }
            solution[i] = (x[i] - sum) / A[i][i];
        }
        return solution;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        System.out.println("Digite os pontos (x e y) para interpolação quadrática (digite 'fim' para terminar):");
        while (true) {
            System.out.print("x: ");
            String xInput = scanner.next();
            if (xInput.equalsIgnoreCase("fim")) {
                break;
            }
            double x = Double.parseDouble(xInput);

            System.out.print("y: ");
            String yInput = scanner.next();
            double y = Double.parseDouble(yInput);

            xList.add(x);
            yList.add(y);
        }

        double[] xs = new double[xList.size()];
        double[] ys = new double[yList.size()];
        for (int i = 0; i < xList.size(); i++) {
            xs[i] = xList.get(i);
            ys[i] = yList.get(i);
        }

        System.out.print("digite o valor de x que voce quer descobrir o y: ");
        double xToPredict = scanner.nextDouble();

        System.out.print("Escolha o modo de exibição (1 para direta, 2 para interativa): ");
        int mode = scanner.nextInt();

        long startTime = System.nanoTime();

        double[] quadraticCoeffs = quadraticFit(xs, ys);

        if (mode == 1) {
            double yPredicted = solveQuadratic(quadraticCoeffs, xToPredict);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.printf("valor de x = %.2f valor de y = %.2f ", xToPredict, yPredicted);
            System.out.printf("Tempo %d", duration);
        } else if (mode == 2) {
            for (int i = 0; i < xs.length; i++) {
                double yPredicted = solveQuadratic(quadraticCoeffs, xs[i]);
                System.out.printf("iteraçao %d - %.2f : %.2f ", i + 1, xs[i], yPredicted);
            }
            double yPredicted = solveQuadratic(quadraticCoeffs, xToPredict);
            System.out.printf("valor de x = %.2f valor de y = %.2f n", xToPredict, yPredicted);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.printf("Tempo %d", duration);
        }

        scanner.close();
    }
}

