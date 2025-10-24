package src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Recursion primitiva: f(x̄,0) = g(x̄)
 *                    f(x̄,y+1) = h(x̄, y, f(x̄,y))
 * donde g: N^n -> N y h: N^{n+2} -> N. Entonces f tiene aridad n+1.
 */
public class Recursion extends FuncionBase {
    private final FuncionBase g; // aridad n
    private final FuncionBase h; // aridad n+2
    private final int aridad; // n+1

    public Recursion(FuncionBase g, FuncionBase h) {
        this.g = g;
        this.h = h;
        this.aridad = g.getAridad() + 1;
    }

    @Override
    public int evaluar(List<Integer> args, AtomicInteger contador) {
        traceInc(contador, args);
        if (args.size() != aridad) {
            throw new IllegalArgumentException("Recursion: aridad incorrecta. Esperada " + aridad + " pero got " + args.size());
        }

        // separar x̄ y y
        int n = aridad - 1;
        List<Integer> xs = args.subList(0, n);
        int y = args.get(n);

        // caso base
        if (y == 0) {
            return g.evaluar(new ArrayList<>(xs), contador);
        }

        // recursión iterativa: calcular f(x,0), f(x,1), ..., f(x,y)
        int prev = g.evaluar(new ArrayList<>(xs), contador);
        for (int t = 0; t < y; t++) {
            // construir vector para h: x̄, t, prev
            List<Integer> hArgs = new ArrayList<>(n + 2);
            hArgs.addAll(xs);
            hArgs.add(t);
            hArgs.add(prev);
            prev = h.evaluar(hArgs, contador);
        }
        return prev;
    }

    @Override
    public int getAridad() {
        return aridad;
    }
}
