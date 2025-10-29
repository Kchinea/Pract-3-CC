package pract3.functions;

import java.util.Arrays;
import java.util.List;
import pract3.core.Counter;
import pract3.operations.Composicion;
import pract3.operations.Recursion;

/**
 * Implementación de la suma como función recursiva primitiva.
 *
 * Construcción (intuitiva):
 *  - plus(x,0) = x                 (g(x) = P1^1)
 *  - plus(x,y+1) = S( plus(x,y) )  (h(x,y,z) = S(z))
 *
 * Esta clase actúa como un envoltorio (wrapper): en el constructor crea
 * internamente la función Recursion que implementa la definición
 * anterior y delega todas las llamadas al objeto implementation.
 */
public class Suma extends FuncionBase {
    private final FuncionBase implementation;
    /**
     * Construye la función plus usando las primitivas:
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
     * Delegación: pasar los argumentos y el contador a la implementación real.
     */
    @Override
    public int evaluar(List<Integer> arguments, Counter counter) {
        return implementation.evaluar(arguments, counter);
    }
    /**
     * Devuelve la aridad de la función construida (delega en implementation).
     */
    @Override
    public int getArity() {
        return implementation.getArity();
    }
}
