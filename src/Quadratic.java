
// QuadraticRegression.Quadratic Regression

import DataSets.DatosVariables;
import EcuacionesALL.Ecuaciones;

import java.util.ArrayList;

public class Quadratic {

    public static void main(String[] args) {
        ArrayList<DatosVariables> Datos = new ArrayList<>(DatosVariables.DatosQuadratic());

        // Proceso de segmentación y selección del mejor modelo basado en el coeficiente de determinación
        double bestRSquared = -1;
        double bestBO = 0, bestB1 = 0, bestB2 = 0;

        for (int iteration = 0; iteration < 2; iteration++) {
            ArrayList<DatosVariables>[] splitData = splitDataSet(Datos, 0.7);
            ArrayList<DatosVariables> trainData = splitData[0];
            ArrayList<DatosVariables> testData = splitData[1];

            // Realizar operaciones de regresión cuadrática
            double[] coefficients = OPERATIONS(trainData);

            // Calcular el coeficiente de determinación para el conjunto de prueba
            double rSquared = calculateRSquared(testData, coefficients);
            System.out.println("Coeficiente de Determinación (R^2): " + rSquared);

            // Guardar el mejor modelo
            if (rSquared > bestRSquared) {
                bestRSquared = rSquared;
                bestBO = coefficients[0];
                bestB1 = coefficients[1];
                bestB2 = coefficients[2];
            }
        }

        // Imprimir el mejor modelo
        System.out.println("\nMejor modelo encontrado:");
        System.out.println("Ecuación: Y = " + bestBO + "x^2 + " + bestB1 + "x + " + bestB2);
        System.out.println("Mejor Coeficiente de Determinación (R^2): " + bestRSquared);

        // Realizar predicciones hardcoded con valores conocidos y desconocidos
        performHardcodedPredictions(bestBO, bestB1, bestB2);

        // Calcular y mostrar la correlación
        double correlation = calculateCorrelation(Datos);
        System.out.println("Correlación: " + correlation);
    }

    public static double[] OPERATIONS(ArrayList<DatosVariables> Datos) {
        double[][] MatrizX = Ecuaciones.OperacionesBasicas(Datos, 3, 4);
        System.out.println("Matriz X:");
        Ecuaciones.ImprimirMatriz(MatrizX);

        System.out.println("Matriz Resuelta por Gauss Jordan: ");
        Ecuaciones.GussJordan(MatrizX);
        Ecuaciones.ImprimirMatriz(MatrizX);

        double BO = MatrizX[0][3];
        double B1 = MatrizX[1][3];
        double B2 = MatrizX[2][3];

        System.out.println("\nEcuacion:\nY = " + BO + "x^2 + " + B1 + "x + " + B2);

        return new double[]{BO, B1, B2};
    }

    // Método para realizar predicciones hardcoded
    public static void performHardcodedPredictions(double BO, double B1, double B2) {
        // Valores conocidos (presentes en el dataset)
        double[] knownBatchSizes = {108, 115, 106, 97, 95};

        // Valores desconocidos (fuera del dataset, extrapolación)
        double[] unknownBatchSizes = {120, 130, 140};

        System.out.println("\nPredicciones con valores conocidos:");
        for (double batchSize : knownBatchSizes) {
            double predictedY = BO * Math.pow(batchSize, 2) + B1 * batchSize + B2;  // Ecuación corregida
            System.out.println("Para Batch Size = " + batchSize );
        }

        System.out.println("\nPredicciones con valores desconocidos:");
        for (double batchSize : unknownBatchSizes) {
            double predictedY = BO * Math.pow(batchSize, 2) + B1 * batchSize + B2;  // Ecuación corregida
            System.out.println("Para Batch Size = " + batchSize );
        }
    }

    // Método de segmentación de datos (70%-30%)
    public static ArrayList<DatosVariables>[] splitDataSet(ArrayList<DatosVariables> datos, double ratio) {
        int trainSize = (int) (datos.size() * ratio);
        ArrayList<DatosVariables> trainData = new ArrayList<>(datos.subList(0, trainSize));
        ArrayList<DatosVariables> testData = new ArrayList<>(datos.subList(trainSize, datos.size()));

        return new ArrayList[]{trainData, testData};
    }

    // Método para calcular el coeficiente de determinación (R^2)
    public static double calculateRSquared(ArrayList<DatosVariables> testData, double[] coefficients) {
        double ssTot = 0;
        double ssRes = 0;
        double yMean = 0;

        for (DatosVariables data : testData) {
            yMean += data.getY();
        }
        yMean /= testData.size();

        for (DatosVariables data : testData) {
            double predictedY = coefficients[0] * Math.pow(data.getX(), 2) + coefficients[1] * data.getX() + coefficients[2];
            ssRes += Math.pow(data.getY() - predictedY, 2);
            ssTot += Math.pow(data.getY() - yMean, 2);
        }

        return 1 - (ssRes / ssTot);
    }

    // Método para calcular la correlación
    public static double calculateCorrelation(ArrayList<DatosVariables> datos) {
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0, sumY2 = 0;
        int n = datos.size();

        for (DatosVariables data : datos) {
            sumX += data.getX();
            sumY += data.getY();
            sumXY += data.getX() * data.getY();
            sumX2 += Math.pow(data.getX(), 2);
            sumY2 += Math.pow(data.getY(), 2);
        }

        double numerator = (n * sumXY) - (sumX * sumY);
        double denominator = Math.sqrt((n * sumX2 - Math.pow(sumX, 2)) * (n * sumY2 - Math.pow(sumY, 2)));

        return numerator / denominator;
    }
}
