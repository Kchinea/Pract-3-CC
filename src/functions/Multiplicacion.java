package pract3.functions;

import java.util.Arrays;
import java.util.List;
import pract3.core.Counter;
import pract3.operations.Composicion;
import pract3.operations.Recursion;

/**
 * Multiplication built by primitive recursion: mult(x,0)=0,
 * mult(x,y+1)=plus(mult(x,y), x).
 */
public class Multiplicacion extends FuncionBase {
    private final FuncionBase implementation;

    public Multiplicacion() {
        // mult(x,y): g(x)=0  ; h(x,y,z)=plus(z,x)
        FuncionCero z1 = new FuncionCero(); // arity 1
        Suma plus = new Suma();
        FuncionProyeccion p3_3 = new FuncionProyeccion(3, 2); // prev
        FuncionProyeccion p1_3 = new FuncionProyeccion(3, 0); // x
        Composicion h_mult = new Composicion(plus, Arrays.asList(p3_3, p1_3));
        implementation = new Recursion(z1, h_mult);
    }

    @Override
    public int evaluar(List<Integer> args, Counter counter) {
        return implementation.evaluar(args, counter);
    }

    @Override
    public int getArity() {
        return implementation.getArity();
    }
}
