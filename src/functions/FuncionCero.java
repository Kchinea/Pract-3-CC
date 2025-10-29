package pract3.functions;

import java.util.List;
import pract3.core.Counter;

/**
 * Función cero: devuelve siempre 0. Se comporta como Z(x) = 0.
 */
public class FuncionCero extends FuncionBase {
    @Override
    public int evaluar(List<Integer> arguments, Counter counter) {
        traceInc(counter, arguments);
        return 0;
    }

    @Override
    public int getArity() {
        return 1;
    }
}
