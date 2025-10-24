package src;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Multiplicacion extends FuncionBase {
    private final FuncionBase impl;

    public Multiplicacion() {
        // mult(x,y): g(x)=0  ; h(x,y,z)=plus(z,x)
        FuncionCero z1 = new FuncionCero(); // aridad 1
        Suma plus = new Suma();
        FuncionProyeccion p3_3 = new FuncionProyeccion(3, 2); // prev
        FuncionProyeccion p1_3 = new FuncionProyeccion(3, 0); // x
        Composicion h_mult = new Composicion(plus, Arrays.asList(p3_3, p1_3));
        impl = new Recursion(z1, h_mult);
    }

    @Override
    public int evaluar(List<Integer> args, AtomicInteger contador) {
        return impl.evaluar(args, contador);
    }

    @Override
    public int getAridad() {
        return impl.getAridad();
    }
}
