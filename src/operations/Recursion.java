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
    /**
     * Primitive recursion: f(x̄,0) = g(x̄)
     *                    f(x̄,y+1) = h(x̄, y, f(x̄,y))
     * where g: N^n -> N and h: N^{n+2} -> N. Then f has arity n+1.
     */
    @Override
    public int evaluar(List<Integer> arguments, Counter counter) {
        traceInc(counter, arguments, false);
        if (arguments.size() != arity) {
            throw new IllegalArgumentException("Recursion: incorrect arity. Expected " + arity + " but got " + arguments.size());
        }
        int prefixCount = arity - 1;
        List<Integer> prefixArgs = arguments.subList(0, prefixCount);
        int yValue = arguments.get(prefixCount);
        if (yValue == 0) {
            return baseFunction.evaluar(new ArrayList<>(prefixArgs), counter);
        }
        int prevResult = baseFunction.evaluar(new ArrayList<>(prefixArgs), counter);
        for (int iteration = 1; iteration <= yValue; iteration++) {
            List<Integer> hArguments = new ArrayList<>(prefixCount + 2);
            hArguments.addAll(prefixArgs);
            hArguments.add(iteration);
            hArguments.add(prevResult);
            prevResult = hFunction.evaluar(hArguments, counter);
        }
        return prevResult;
    }

    @Override
    public int getArity() {
        return arity;
    }
}
