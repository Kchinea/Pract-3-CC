package src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Composicion extends FuncionBase {
    private FuncionBase h;
    private List<FuncionBase> gVec;
    private int aridad;

    public Composicion(FuncionBase h, List<FuncionBase> g) {
        this.h = h;
        this.gVec = g;
        if (!gVec.isEmpty()) this.aridad = gVec.get(0).getAridad();
        else this.aridad = 0;
    }

    @Override
    public int evaluar(List<Integer> args, AtomicInteger contador) {
        traceInc(contador, args);
        List<Integer> resultados = new ArrayList<>();
        for (FuncionBase gi : gVec) {
            resultados.add(gi.evaluar(args, contador));
        }
        return h.evaluar(resultados, contador);
    }

    @Override
    public int getAridad() {
        return aridad;
    }
}
