package pract3.functions;

import java.util.List;
import pract3.core.Counter;

/**
 * Proyección P_i^n: devuelve el i-ésimo argumento (iIndex 0-based).
 */
public class FuncionProyeccion extends FuncionBase {
    private int arity;
    private int index;

    public FuncionProyeccion(int arity, int index) {
        this.arity = arity;
        this.index = index;
    }

    @Override
    public int evaluar(List<Integer> args, Counter counter) {
        traceInc(counter, args);
        return args.get(index);
    }

    @Override
    public int getArity() {
        return arity;
    }
}
