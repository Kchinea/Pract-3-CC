package pract3.functions;

import java.util.Arrays;
import java.util.List;
import pract3.core.Counter;
import pract3.operations.Composicion;
import pract3.operations.Recursion;

/**
 * Power pow(x,y) = x^y using primitive recursion.
 *
 * Construction:
 *  - pow(x,0) = 1          (g(x) = S(Z(x)) -> 1)
 *  - pow(x,y+1) = mult(pow(x,y), x)
 */
public class Potencia extends FuncionBase {
    private final FuncionBase implementation;

    /**
     * Builds pow via Recursion(g,h).
     */
    public Potencia() {
        // pow(x,y): g(x)=1  (S(Z(x))) ; h(x,y,z)=mult(z,x)
        FuncionCero z1 = new FuncionCero();
        FuncionSucesora s = new FuncionSucesora();
        Composicion g_pow = new Composicion(s, Arrays.asList(z1)); // g(x)=1

        Multiplicacion mult = new Multiplicacion();
        FuncionProyeccion p3_3 = new FuncionProyeccion(3, 2); // prev
        FuncionProyeccion p1_3 = new FuncionProyeccion(3, 0); // x
        Composicion h_pow = new Composicion(mult, Arrays.asList(p3_3, p1_3));

        implementation = new Recursion(g_pow, h_pow);
    }

    /**
     * Delegates to internal implementation.
     */
    @Override
    public int evaluar(List<Integer> arguments, Counter counter) {
        return implementation.evaluar(arguments, counter);
    }

    /**
     * Returns the arity of pow (delegates to implementation).
     */
    @Override
    public int getArity() {
        return implementation.getArity();
    }
}
