package src;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Suma extends FuncionBase {
    private final FuncionBase impl;

    public Suma() {
        // plus(x,y): f(x,0)=x  (g = P1^1)
        //             f(x,y+1)=S(f(x,y)) (h = S o P3^3)
        FuncionSucesora s = new FuncionSucesora();
        FuncionProyeccion p1_1 = new FuncionProyeccion(1, 0);
        FuncionProyeccion p3_3 = new FuncionProyeccion(3, 2);
        Composicion h_plus = new Composicion(s, Arrays.asList(p3_3));
        impl = new Recursion(p1_1, h_plus);
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
