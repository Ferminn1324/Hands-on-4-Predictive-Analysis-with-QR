package DataSets;

import java.util.ArrayList;

public class DatosVariables {
    private double x;
    private double y;

    public DatosVariables(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Simulación de un conjunto de datos para la regresión cuadrática
    public static ArrayList<DatosVariables> DatosQuadratic() {
        ArrayList<DatosVariables> datos = new ArrayList<>();
        datos.add(new DatosVariables(108, 95));
        datos.add(new DatosVariables(115, 96));
        datos.add(new DatosVariables(106, 95));
        datos.add(new DatosVariables(97, 97));
        datos.add(new DatosVariables(95, 93));
        datos.add(new DatosVariables(91, 94));
        datos.add(new DatosVariables(97, 95));
        datos.add(new DatosVariables(83, 93));
        datos.add(new DatosVariables(83, 92));
        datos.add(new DatosVariables(78, 86));
        datos.add(new DatosVariables(54, 73));
        datos.add(new DatosVariables(67, 80));
        datos.add(new DatosVariables(56, 65));
        datos.add(new DatosVariables(53, 69));
        datos.add(new DatosVariables(61, 77));
        datos.add(new DatosVariables(115, 96));
        datos.add(new DatosVariables(81, 87));
        datos.add(new DatosVariables(78, 89));
        datos.add(new DatosVariables(30, 60));
        datos.add(new DatosVariables(45, 63));
        datos.add(new DatosVariables(99, 95));
        datos.add(new DatosVariables(32, 61));
        datos.add(new DatosVariables(25, 55));
        datos.add(new DatosVariables(28, 56));
        datos.add(new DatosVariables(90, 94));
        datos.add(new DatosVariables(89, 93));

        return datos;
    }
}
