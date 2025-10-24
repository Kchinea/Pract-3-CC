package src;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FuncionProyeccion extends FuncionBase {
    private int aridad;
    private int indice; // 0-based

    public FuncionProyeccion(int aridad, int indice) {
        this.aridad = aridad;
        this.indice = indice;
    }

    @Override
    public int evaluar(List<Integer> args, AtomicInteger contador) {
        traceInc(contador, args);
        return args.get(indice);
    }

    @Override
    public int getAridad() {
        return aridad;
    }
}
