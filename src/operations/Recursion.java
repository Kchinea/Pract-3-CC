package pract3.operations;

import java.util.ArrayList;
import java.util.List;
import pract3.core.Counter;
import pract3.functions.FuncionBase;

/**
 * Primitive recursion: f(x̄,0) = g(x̄)
 *                    f(x̄,y+1) = h(x̄, y, f(x̄,y))
 * where g: N^n -> N and h: N^{n+2} -> N. Then f has arity n+1.
 */
public class Recursion extends FuncionBase {
    private final FuncionBase baseFunction;
    private final FuncionBase hFunction;
    private final int arity;

    public Recursion(FuncionBase baseFunction, FuncionBase hFunction) {
        this.baseFunction = baseFunction;
        this.hFunction = hFunction;
        this.arity = baseFunction.getArity() + 1;
    }

    @Override
    public int evaluar(List<Integer> args, Counter counter) {
        traceInc(counter, args, false);
        if (args.size() != arity) {
            throw new IllegalArgumentException("Recursion: incorrect arity. Expected " + arity + " but got " + args.size());
        }
        int n = arity - 1;
        List<Integer> xs = args.subList(0, n);
        int y = args.get(n);
        if (y == 0) {
            return baseFunction.evaluar(new ArrayList<>(xs), counter);
        }
        int prevResult = baseFunction.evaluar(new ArrayList<>(xs), counter);
        for (int i = 0; i < y; i++) {
            List<Integer> hArgs = new ArrayList<>(n + 2);
            hArgs.addAll(xs);
            hArgs.add(i);
            hArgs.add(prevResult);
            prevResult = hFunction.evaluar(hArgs, counter);
        }
        return prevResult;
    }

    @Override
    public int getArity() {
        return arity;
    }
}
