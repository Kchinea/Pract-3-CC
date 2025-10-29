package pract3.core;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import pract3.functions.Potencia;
import pract3.functions.Multiplicacion;

/**
 * Main interactive program: asks for two positive integers and computes
 * power (base^expo) using `Potencia` implementation.
 */
public class Main {
    public static void main(String[] args) {
        // enable trace mode if --trace is passed
        for (String a : args) {
            if ("--trace".equals(a)) {
                Trace.setEnabled(true);
            }
        }

        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduce la base (entero positivo): ");
            if (!sc.hasNextInt()) {
                System.out.println("Entrada inválida: se esperaba un entero positivo.");
                return;
            }
            int base = sc.nextInt();

            System.out.print("Introduce el exponente (entero positivo): ");
            if (!sc.hasNextInt()) {
                System.out.println("Entrada inválida: se esperaba un entero positivo.");
                return;
            }
            int expo = sc.nextInt();

            if (base <= 0 || expo <= 0) {
                System.out.println("Por favor introduce enteros positivos (> 0) para base y exponente.");
                return;
            }
            Potencia pow = new Potencia();
            Multiplicacion mult = new Multiplicacion();
            Counter counter = new Counter(0);
            int result = pow.evaluar(Arrays.asList(base, expo), counter);
            System.out.println(base + " ^ " + expo + " = " + result);
            System.out.println("(internal call counter = " + counter.get() + ")");
        } finally {
            sc.close();
        }
    }
}
