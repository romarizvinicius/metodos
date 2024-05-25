import java.util.Scanner;

public class df {


    private static double[][] calcularDiferencasFinitas(double[] x, double[] y) {
        int n = y.length;
        double[][] tabela = new double[n][n];
        
        for (int i = 0; i < n; i++) {
            tabela[i][0] = y[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                tabela[i][j] = tabela[i + 1][j - 1] - tabela[i][j - 1];
            }
        }

        return tabela;
    }

    private static double interpolar(double[] x, double[][] diferencasFinitas, double valorX) {
        double resultado = diferencasFinitas[0][0];
        double produto = 1;
        double h = x[1] - x[0];
        double p = (valorX - x[0]) / h;

        for (int i = 1; i < x.length; i++) {
            produto *= (p - (i - 1)) / i;
            resultado += produto * diferencasFinitas[0][i];
        }

        return resultado;
    }

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

        System.out.println("digite o valor de x para interpolaçao:");
        double valorX = scanner.nextDouble();
        long startTime = System.nanoTime();

        double[][] diferencasFinitas = calcularDiferencasFinitas(x, y);
        double resultado = interpolar(x, diferencasFinitas, valorX);

        long endTime = System.nanoTime();
        long tempoExecucao = endTime - startTime;
        int numIteracoes = n * (n - 1) / 2; // Número de iterações na tabela de diferenças finitas

        System.out.println("valor interpolado em " + valorX + " é " + resultado);
        System.out.println("iterações " + numIteracoes);
        System.out.println("tempo " + tempoExecucao);

        scanner.close();
    }
}

