import java.util.Scanner;

public class NewtonRaphson {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Digite o número inicial para a aproximação da raiz");
        double inicial = s.nextDouble();

        double precisao = 0.01;

        long starTime = System.nanoTime();

        double raiz = newtonRaphson(inicial, precisao);

        long endTime = System.nanoTime();

        double contagem = (endTime - starTime) / 1e9;

        if (!Double.isNaN(raiz)) {
            System.out.println("A raiz é aproximadamente: " + raiz);
        } else {
            System.out.println("Não foi possível encontrar a raiz com a precisão desejada.");
        }

        System.out.println("O tempo de execução: " + contagem  + " segundos" );
        s.close();
    }

    public static double funcao(double x) {
        return Math.pow(x, 5) + 2 * Math.pow(x, 3) + 2 * x + 1;
    }

    public static double derivada(double x) {
        return 5 * Math.pow(x, 4) + 6 * Math.pow(x, 2) + 2;
    }

    public static double newtonRaphson(double inicial, double precisao) {
        double x0 = inicial;
        double x1 = 0.0;

        while (true) {
            x1 = x0 - funcao(x0) / derivada(x0);

            if (Math.abs(x1 - x0) < precisao) {
                break;
            }

            x0 = x1;
        }

        return x1;
    }
}
