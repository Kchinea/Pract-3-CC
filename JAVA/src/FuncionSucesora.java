package src;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FuncionSucesora extends FuncionBase {
    @Override
    public int evaluar(List<Integer> args, AtomicInteger contador) {
        traceInc(contador, args);
        return args.get(0) + 1;
    }

    @Override
    public int getAridad() {
        return 1;
    }
}
