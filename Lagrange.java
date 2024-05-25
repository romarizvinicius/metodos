import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lagrange {
    public static double lagrangeInterpolate(DataPoint[] points, double x) {
        double result = 0.0;
        for (int i = 0; i < points.length; i++) {
            double term = points[i].y;
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    term *= (x - points[j].x) / (points[i].x - points[j].x);
                }
            }
            result += term;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<DataPoint> pointsList = new ArrayList<>();
        System.out.println("Digite os pontos (x e y) para interpolação de Lagrange (digite 'fim' para terminar):");
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

            pointsList.add(new DataPoint(x, y));
        }

        DataPoint[] points = pointsList.toArray(new DataPoint[0]);

        System.out.print("digite o valor de x que voce quer descobrir o y: ");
        double xToPredict = scanner.nextDouble();

        long startTime = System.nanoTime();
        double yPredicted = lagrangeInterpolate(points, xToPredict);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.printf("valor de x = %.2f valor de y = %.2f ", xToPredict, yPredicted);
        System.out.printf("Tempo %d", duration);

        scanner.close();
    }
}

class DataPoint {
    public double x;
    public double y;

    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
