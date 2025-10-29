package pract3.operations;

import java.util.ArrayList;
import java.util.List;
import pract3.core.Counter;
import pract3.functions.FuncionBase;

/**
 * Represents the composition of functions, accepts a list of functions gi.
 *
 * Behavior in evaluate:
 * - traceInc is used (without increment) to record the call.
 * - Each gi is evaluated with the same input arguments.
 * - The list of results is passed to h for final evaluation.
 */
public class Composicion extends FuncionBase {
    private FuncionBase hFunction;
    private List<FuncionBase> gFunctions;
    private int arity;

    public Composicion(FuncionBase h, List<FuncionBase> g) {
        this.hFunction = h;
        this.gFunctions = g;
        if (!gFunctions.isEmpty()) this.arity = gFunctions.get(0).getArity();
        else this.arity = 0;
    }

    /**
     * Evaluate the composition: evaluate each gi with arguments, then
     * pass the results list to h.
     */
    @Override
    public int evaluar(List<Integer> arguments, Counter counter) {
        traceInc(counter, arguments, false);
        List<Integer> results = new ArrayList<>();
        for (FuncionBase gi : gFunctions) {
            results.add(gi.evaluar(arguments, counter));
        }
        return hFunction.evaluar(results, counter);
    }

    /**
     * Arity of the composition (assumed same for each gi).
     */
    @Override
    public int getArity() {
        return arity;
    }
}
