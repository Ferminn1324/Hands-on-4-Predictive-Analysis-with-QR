package EcuacionesALL;

import DataSets.DatosVariables;
import java.util.ArrayList;

public class Ecuaciones {

    // Método para generar la matriz de operaciones básicas para regresión cuadrática
    public static double[][] OperacionesBasicas(ArrayList<DatosVariables> Datos, int rows, int columns) {
        double[][] matriz = new double[rows][columns];

        // Simulación de una matriz de coeficientes para la regresión cuadrática
        // Aquí puedes implementar la lógica para construir la matriz de coeficientes
        matriz[0][0] = Datos.size(); // Ejemplo básico
        matriz[0][1] = sumX(Datos);
        matriz[0][2] = sumX2(Datos);
        matriz[0][3] = sumY(Datos);

        matriz[1][0] = sumX(Datos);
        matriz[1][1] = sumX2(Datos);
        matriz[1][2] = sumX3(Datos);
        matriz[1][3] = sumXY(Datos);

        matriz[2][0] = sumX2(Datos);
        matriz[2][1] = sumX3(Datos);
        matriz[2][2] = sumX4(Datos);
        matriz[2][3] = sumX2Y(Datos);

        return matriz;
    }

    // Método de resolución de matrices (ejemplo básico de Gauss Jordan)
    public static void GussJordan(double[][] matriz) {
        // Ejemplo de Gauss-Jordan para resolver matrices
        int n = matriz.length;
        for (int i = 0; i < n; i++) {
            // Escalado de filas
            double pivot = matriz[i][i];
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] /= pivot;
            }

            // Reducción a forma escalonada
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matriz[k][i];
                    for (int j = 0; j < matriz[i].length; j++) {
                        matriz[k][j] -= factor * matriz[i][j];
                    }
                }
            }
        }
    }

    // Métodos auxiliares para sumar valores
    public static double sumX(ArrayList<DatosVariables> Datos) {
        double sum = 0;
        for (DatosVariables dato : Datos) {
            sum += dato.getX();
        }
        return sum;
    }

    public static double sumY(ArrayList<DatosVariables> Datos) {
        double sum = 0;
        for (DatosVariables dato : Datos) {
            sum += dato.getY();
        }
        return sum;
    }

    public static double sumX2(ArrayList<DatosVariables> Datos) {
        double sum = 0;
        for (DatosVariables dato : Datos) {
            sum += dato.getX() * dato.getX();
        }
        return sum;
    }

    public static double sumX3(ArrayList<DatosVariables> Datos) {
        double sum = 0;
        for (DatosVariables dato : Datos) {
            sum += Math.pow(dato.getX(), 3);
        }
        return sum;
    }

    public static double sumX4(ArrayList<DatosVariables> Datos) {
        double sum = 0;
        for (DatosVariables dato : Datos) {
            sum += Math.pow(dato.getX(), 4);
        }
        return sum;
    }

    public static double sumXY(ArrayList<DatosVariables> Datos) {
        double sum = 0;
        for (DatosVariables dato : Datos) {
            sum += dato.getX() * dato.getY();
        }
        return sum;
    }

    public static double sumX2Y(ArrayList<DatosVariables> Datos) {
        double sum = 0;
        for (DatosVariables dato : Datos) {
            sum += dato.getX() * dato.getX() * dato.getY();
        }
        return sum;
    }

    // Método para imprimir una matriz
    public static void ImprimirMatriz(double[][] matriz) {
        for (double[] row : matriz) {
            for (double value : row) {
                System.out.printf("%10.4f ", value);
            }
            System.out.println();
        }
    }
}
