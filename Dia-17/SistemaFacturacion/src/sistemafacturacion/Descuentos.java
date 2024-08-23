package sistemafacturacion;

import java.util.function.Function;

public class Descuentos {

    public static Function<Double, Double> descuentoVIP() {
        return total -> total * 0.9;
    }

    public static Function<Double, Double> descuentoPorMonto() {
        return total -> total > 200 ? total - 20 : total;
    }
}

