# Práctica 3 - Función Potencia como Función Primitiva Recursiva

## Descripción del Proyecto

Este proyecto implementa la función **potencia(x, y) = x^y** como una **función primitiva recursiva** en C++, utilizando un diseño orientado a objetos.

La implementación parte de las funciones recursivas iniciales (Cero, Sucesora, Proyección) y utiliza las operaciones de composición y recursión primitiva para construir funciones más complejas:
- **Suma(x, y)**: Construida mediante recursión primitiva sobre la función Sucesora
- **Multiplicación(x, y)**: Construida mediante recursión primitiva sobre la función Suma
- **Potencia(x, y)**: Construida mediante recursión primitiva sobre la función Multiplicación

## Diseño Orientado a Objetos

### Jerarquía de Clases

```
FuncionBase (clase abstracta)
├── FuncionCero           # Z(x) = 0
├── FuncionSucesora       # S(x) = x + 1
├── FuncionProyeccion     # P_i^n(x_1,...,x_n) = x_i
├── FuncionComposicion    # Composición de funciones
└── FuncionRecursion      # Recursión primitiva
```

### Funciones Primitivas Recursivas Implementadas

#### 1. **Suma(x, y)**
```
suma(x, 0) = x                    [g(x) = P_1^1(x)]
suma(x, y+1) = S(suma(x, y))      [h(x, y, z) = S(P_3^3(x, y, z))]
```

#### 2. **Multiplicación(x, y)**
```
mult(x, 0) = 0                    [g(x) = Z(x)]
mult(x, y+1) = suma(x, mult(x, y)) [h(x, y, z) = suma(P_1^3, P_3^3)]
```

#### 3. **Potencia(x, y)**
```
pot(x, 0) = 1                     [g(x) = S(Z(x))]
pot(x, y+1) = mult(x, pot(x, y))  [h(x, y, z) = mult(P_1^3, P_3^3)]
```

## Compilación

El proyecto incluye un `Makefile` para facilitar la compilación:

```bash
make
```

Esto generará el ejecutable `potencia` en el directorio raíz.

### Limpiar archivos compilados
```bash
make clean
```

## Ejecución

### Opción 1: Pasar argumentos por línea de comandos
```bash
./potencia x y
```

**Ejemplo:**
```bash
./potencia 2 10
```

**Salida:**
```
=== RESULTADOS ===
Operación: 2^10
Resultado: 1024
Número total de llamadas a funciones: 12345
```

### Opción 2: Entrada por teclado
```bash
./potencia
```

El programa solicitará los valores de `x` e `y` interactivamente.

## Salida del Programa

El programa muestra:
1. **Resultado de la operación**: El valor de x^y
2. **Número total de llamadas a funciones**: Cuenta todas las invocaciones a funciones primitivas recursivas durante el cálculo

## Estructura del Proyecto

```
Pract-3-CC/
├── README.md
├── Makefile
├── src/
│   ├── main.cc                      # Programa principal
│   ├── funcion_base.h               # Clase base abstracta
│   ├── funcion_cero.h               # Función inicial: Cero
│   ├── funcion_sucesora.h           # Función inicial: Sucesora
│   ├── funcion_proyeccion.h         # Función inicial: Proyección
│   ├── funcion_composicion.h        # Operación: Composición
│   ├── funcion_recursion.h          # Operación: Recursión Primitiva
│   └── funciones_primitivas.h       # Construcción de suma, mult, potencia
└── build/                            # Archivos objeto (generado)
```

## Requisitos

- **Compilador**: g++ con soporte para C++17 o superior
- **Sistema operativo**: Linux, macOS, Windows (con MinGW o WSL)

## Fundamento Teórico

### Funciones Recursivas Iniciales

1. **Función Cero**: `Z(x) = 0`
2. **Función Sucesora**: `S(x) = x + 1`
3. **Función Proyección**: `P_i^n(x_1, ..., x_n) = x_i`

### Operaciones

1. **Composición**: Si `h: N^k → N` y `g_1, ..., g_k: N^n → N`, entonces:
   ```
   f(x_1, ..., x_n) = h(g_1(x_1, ..., x_n), ..., g_k(x_1, ..., x_n))
   ```

2. **Recursión Primitiva**: Si `g: N^n → N` y `h: N^(n+2) → N`, entonces `f: N^(n+1) → N`:
   ```
   f(x_1, ..., x_n, 0) = g(x_1, ..., x_n)
   f(x_1, ..., x_n, y+1) = h(x_1, ..., x_n, y, f(x_1, ..., x_n, y))
   ```

## Ejemplos de Uso

### Ejemplo 1: Potencia básica
```bash
./potencia 3 4
```
**Resultado**: 81 (3^4)

### Ejemplo 2: Caso base
```bash
./potencia 5 0
```
**Resultado**: 1 (cualquier número elevado a 0 es 1)

### Ejemplo 3: Potencia de 1
```bash
./potencia 1 100
```
**Resultado**: 1 (1 elevado a cualquier número es 1)

## Autor

Práctica de Complejidad Computacional  
Fecha de entrega: 31 de octubre de 2025

## Notas Adicionales

- Todos los valores son números naturales (N = {0, 1, 2, 3, ...})
- El contador de llamadas incluye todas las invocaciones a funciones primitivas recursivas
- La implementación sigue estrictamente la teoría de funciones primitivas recursivas sin utilizar operaciones aritméticas nativas más allá de la suma unitaria (sucesora)