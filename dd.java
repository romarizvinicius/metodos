import java.util.Scanner;

public class dd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("digite o numero de pontos:");
        int n = scanner.nextInt();
        
        double[] x = new double[n];
        double[] y = new double[n];
        
        System.out.println("digite os valores de x:");
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
        }
        
        System.out.println("digite os valores de y:");
        for (int i = 0; i < n; i++) {
            y[i] = scanner.nextDouble();
        }
        
        long startTime = System.nanoTime();
        
        double[][] dividedDifferenceTable = new double[n][n];
        for (int i = 0; i < n; i++) {
            dividedDifferenceTable[i][0] = y[i];
        }
        
        int iterations = 0;
        
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dividedDifferenceTable[i][j] = (dividedDifferenceTable[i + 1][j - 1] - dividedDifferenceTable[i][j - 1]) / (x[i + j] - x[i]);
                iterations++;
            }
        }
        
        long endTime = System.nanoTime();
        
        System.out.println("tabela:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%12.6f ", dividedDifferenceTable[i][j]);
            }
            System.out.println();
        }
        
        System.out.println("numero de iteraçoes: " + iterations);
        System.out.println("tempo: " + (endTime - startTime) / 1e6);
        
        scanner.close();
    }
}
