package pract3.functions;

import java.util.List;
import pract3.core.Counter;
import pract3.core.Trace;

/**
 * Clase base para todas las funciones del sistema.
 *
 * Diseño y contrato:
 * - Cada función recursiva primitiva será representada como una subclase de FuncionBase.
 * - El método evaluar(List<Integer> arguments, Counter contador)
 *   es el único punto de entrada para calcular el valor de la función.
 *   - arguments contiene los argumentos de la llamada actual.
 *   - contador es un entero mutable (usamos {@link Counter})
 *     que actúa como contador compartido de llamadas/traza.
 *
 * Axiomas (mapeo conceptual):
 * - FuncionCero  -> Z(x) = 0
 * - FuncionSucesora -> S(x) = x + 1
 * - FuncionProyeccion -> P_i^n(x1,...,xn) = xi
 *
 * Todos los constructores de funciones compuestas construyen nuevos objetos
 * (Composicion, Recursion, etc.) que, al evaluar, crean nuevas listas de
 * argumentos para las sub-llamadas y delegan en ellas.
 */
public abstract class FuncionBase {
    /**
     * Evaluar la función con los argumentos dados, se llama a la funcion traceInc por defecto.
     *
     * @param arguments lista de argumentos de la llamada (no nula)
     * @param contador contador mutable compartido (ahora de tipo Counter)
     * @return el resultado numérico de la función
     */
    public int evaluar(List<Integer> arguments, Counter counter) {
        traceInc(counter, arguments);
        return 0;
    }

    /**
     * Devuelve la aridad de la función (número de argumentos que espera).
     * Se usa para validar llamadas y construir funciones compuestas.
     *
     * @return aridad (>= 0)
     */
    public int getArity() {
        return 0;
    }

    /**
     * Metodo helper que incrementa el contador y registra una entrada de traza.
     * Todas las implementaciones deberían llamar a traceInc al entrar
     * en evaluar para que la llamada sea contabilizada o al menos
     * mostrada en traza. Hay dos sobrecargas:
     * - traceInc(Counter, java.util.List) incrementa el contador y
     *   registra la traza (comportamiento por defecto).
     * - traceInc(Counter, java.util.List, boolean) permite indicar si
     *   debe incrementarse el contador (cuando false sólo se traza).
     *
     * @param contador contador compartido
     * @param arguments argumentos de la llamada
     * @return el nuevo valor del contador tras la incrementación (o el valor
     *         actual si no se incrementó)
     */
    protected int traceInc(Counter counter, List<Integer> arguments) {
        return traceInc(counter, arguments, true);
    }
    protected int traceInc(Counter counter, List<Integer> arguments, boolean increment) {
        Trace.log(this.getClass().getSimpleName(), arguments == null ? java.util.Collections.emptyList() : arguments);
        if (increment) {
            return counter.incrementAndGet();
        } else {
            return counter.get();
        }
    }
}
