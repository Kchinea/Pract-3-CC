package src;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Combinacion extends FuncionBase {
    private FuncionBase f1;
    private FuncionBase f2;
    private int aridad;

    public Combinacion(FuncionBase f1, FuncionBase f2) {
        this.f1 = f1;
        this.f2 = f2;
        this.aridad = f1.getAridad();
    }

    @Override
    public int evaluar(List<Integer> args, AtomicInteger contador) {
        traceInc(contador, args);
        int r1 = f1.evaluar(args, contador);
        int r2 = f2.evaluar(args, contador);
        return r1 + r2;
    }

    @Override
    public int getAridad() {
        return aridad;
    }
}
