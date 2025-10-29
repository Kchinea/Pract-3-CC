package pract3.functions;

import java.util.List;
import pract3.core.Counter;

/**
 * Sucesora S(x) = x + 1.
 */
public class FuncionSucesora extends FuncionBase {
    @Override
    public int evaluar(List<Integer> arguments, Counter counter) {
        traceInc(counter, arguments);
        return arguments.get(0) + 1;
    }

    @Override
    public int getArity() {
        return 1;
    }
}
