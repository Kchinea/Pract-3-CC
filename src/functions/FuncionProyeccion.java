package pract3.functions;

import java.util.List;
import pract3.core.Counter;

/**
 * Proyection P_i^n: return the i-th argument (0-based index).
 */
public class FuncionProyeccion extends FuncionBase {
    private int arity;
    private int index;

    public FuncionProyeccion(int arity, int index) {
        this.arity = arity;
        this.index = index;
    }

    @Override
    public int evaluar(List<Integer> arguments, Counter counter) {
        traceInc(counter, arguments);
        return arguments.get(index);
    }

    @Override
    public int getArity() {
        return arity;
    }
}
