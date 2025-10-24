package src;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FuncionCero extends FuncionBase {
    @Override
    public int evaluar(List<Integer> args, AtomicInteger contador) {
        traceInc(contador, args);
        return 0;
    }

    @Override
    public int getAridad() {
        return 1;
    }
}
