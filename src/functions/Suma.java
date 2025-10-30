package pract3.functions;

import java.util.Arrays;
import java.util.List;
import pract3.core.Counter;
import pract3.operations.Composicion;
import pract3.operations.Recursion;

/**
 * Implementation of the sum function like primitive recursion function.
 *
 * Construction of the addition function plus(x,y) using primitive recursion:
 *  - plus(x,0) = x                 (g(x) = P1^1)
 *  - plus(x,y+1) = S( plus(x,y) )  (h(x,y,z) = S(z))
 *
 * This class acts as a wrapper: in the constructor it creates
 * internally the Recursion function that implements the previous definition
 * and delegates all calls to the implementation object.
 */
public class Suma extends FuncionBase {
    private final FuncionBase implementation;
    /**
     * Constructs the plus function using the primitives:
     * - g = P1^1
     * - h = S ∘ P3^3
     */
    public Suma() {
        // plus(x,y): f(x,0)=x  (g = P1^1)
        //             f(x,y+1)=S(f(x,y)) (h = S o P3^3)
        FuncionSucesora successorFunction = new FuncionSucesora();
        FuncionProyeccion projectFirst = new FuncionProyeccion(1, 0);
        FuncionProyeccion projectThird = new FuncionProyeccion(3, 2);
        Composicion successorOfThird = new Composicion(successorFunction, Arrays.asList(projectThird));
        implementation = new Recursion(projectFirst, successorOfThird);
    }
    /**
     * Delegation: pass the arguments and the counter to the real implementation.
     */
    @Override
    public int evaluar(List<Integer> arguments, Counter counter) {
        return implementation.evaluar(arguments, counter);
    }
    /**
     * Returns the arity of the constructed function (delegates to implementation).
     */
    @Override
    public int getArity() {
        return implementation.getArity();
    }
}
