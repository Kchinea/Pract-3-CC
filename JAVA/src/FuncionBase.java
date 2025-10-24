package src;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class FuncionBase {
    // Evaluar con lista de argumentos y contador de llamadas
    public int evaluar(List<Integer> args, AtomicInteger contador) {
        // por defecto, incrementar contador y trazar si está activo
        traceInc(contador, args);
        return 0;
    }

    // Aridad (número de argumentos que espera)
    public int getAridad() {
        return 0;
    }

    // helper para incrementar contador y registrar traza
    protected int traceInc(AtomicInteger contador, List<Integer> args) {
        int c = contador.incrementAndGet();
        // registrar quién llamó si está activo el tracing
        Trace.log(this.getClass().getSimpleName(), args == null ? java.util.Collections.emptyList() : args);
        return c;
    }
}
