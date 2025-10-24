package src;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger contador = new AtomicInteger(0);

        // activar modo traza si se pasa --trace
        for (String a : args) {
            if ("--trace".equals(a)) {
                Trace.setEnabled(true);
            }
        }

        FuncionSucesora s = new FuncionSucesora();
        FuncionProyeccion p1_1 = new FuncionProyeccion(1, 0);

        // comp1(x) = S(P_1^1(x)) = S(x)
        Composicion comp1 = new Composicion(s, Arrays.asList(p1_1));

        // comp2(x) = S(comp1(x))
        Composicion comp2 = new Composicion(s, Arrays.asList(comp1));

        // comp3(x) = S(comp2(x))
        Composicion comp3 = new Composicion(s, Arrays.asList(comp2));

        Combinacion comb = new Combinacion(comp1, comp2);
        Combinacion comb2 = new Combinacion(comb, comp3);

        List<Integer> argumentos = Arrays.asList(10);
        int resultado = comp3.evaluar(argumentos, contador);

        int sol_comb = comb.evaluar(argumentos, contador);
        int sol_comb2 = comb2.evaluar(argumentos, contador);

        System.out.println("Resultado de S(S(S(10))) = " + resultado);
        System.out.println("Contador de llamadas = " + contador.get());
        System.out.println("Resultado de la combinacion (comp1 + comp2) = " + sol_comb);
        System.out.println("Resultado de la combinacion (comp1 + comp2 + comp3) = " + sol_comb2);

    // ---- Ejemplos usando clases: Suma, Multiplicacion, Potencia ----
    Suma plus = new Suma();
    Multiplicacion mult = new Multiplicacion();
    Potencia pow = new Potencia();

    System.out.println("\n== Recursion primitives tests (clases) ==");
    AtomicInteger contadorRec = new AtomicInteger(0);
    int plusRes = plus.evaluar(Arrays.asList(5, 3), contadorRec); // 5+3=8
    System.out.println("5 + 3 = " + plusRes + " (contador=" + contadorRec.get() + ")");
    int multRes = mult.evaluar(Arrays.asList(4, 3), contadorRec); // 4*3=12
    System.out.println("4 * 3 = " + multRes + " (contador=" + contadorRec.get() + ")");
    int powRes = pow.evaluar(Arrays.asList(2, 3), contadorRec); // 2^3=8
    System.out.println("2 ^ 3 = " + powRes + " (contador=" + contadorRec.get() + ")");

    }
}
