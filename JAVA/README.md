# Pract-3-CC — Versión Java

Este README explica con todo detalle la práctica (Conceptos de Computabilidad/Funciones Recursivas) y la versión Java incluida en `Pract-3-CC/JAVA`. Está pensado para alguien con experiencia en C# que quiere entender Java y cómo se ha portado la práctica desde C++.

Tabla de contenidos
- Resumen de la práctica
- Fundamento matemático (recursión primitiva)
- Reglas del reto: crear nuevas funciones sólo con las primitivas
- Estructura del proyecto (archivos más importantes)
- Explicación de cada clase Java y sus firmas
- Cómo compilar y ejecutar (Linux y Windows)
- Detalles de implementación y decisiones de diseño
- Cómo añadir nuevas funciones (guía paso a paso)
- Comprobaciones de aridad y errores comunes
- Testing y verificación
- Preguntas frecuentes y troubleshooting
- Próximos pasos y mejoras sugeridas

---

Resumen de la práctica

El objetivo es implementar (y luego portar a Java) un conjunto de construcciones para definir funciones sobre números naturales: las funciones básicas (cero, sucesora, proyección), composición, combinación y recursión primitiva. Con estas construcciones se pueden definir operaciones aritméticas como suma, multiplicación y potencia respetando la restricción: "para definir funciones nuevas sólo puedes usar las funciones ya creadas".

En esta carpeta `JAVA` encontrarás una versión Java de la práctica con:
- Implementaciones de las primitivas: `FuncionCero`, `FuncionSucesora`, `FuncionProyeccion`.
- Construcciones: `Composicion`, `Combinacion`.
- Recursión primitiva: `Recursion` (implementada iterativamente para evitar desbordamiento de pila).
- Implementaciones concretas (usando sólo las primitivas): `Suma`, `Multiplicacion`, `Potencia`.
- Un `Main` que muestra ejemplos y un contador de llamadas.

Fundamento matemático (recursión primitiva)

- Z (función cero): Z(x) = 0.
- S (sucesora): S(x) = x + 1.
- P_i^n (proyección): P_i^n(x1,...,xn) = xi.
- Composición: si h: N^m -> N y g1..gm: N^n -> N entonces f(x1..xn) = h(g1(x1..xn), ..., gm(x1..xn)).
- Recursión primitiva: si g: N^n -> N e h: N^{n+2} -> N, la función f: N^{n+1} -> N está definida por
  - f(x̄, 0) = g(x̄)
  - f(x̄, y+1) = h(x̄, y, f(x̄, y))

Usando estas reglas se puede construir suma, multiplicación y potencia.

Reglas del reto

- Sólo puedes construir nuevas funciones combinando/componiendo/recursando sobre funciones ya definidas en el proyecto. No escribirás directamente el algoritmo de suma/multiplicación/potencia: las crearás con `Recursion` + `Composicion` + `Combinacion` y las primitivas.

Estructura del proyecto (carpeta `JAVA`)

- `src/cc/` — código fuente Java (package `cc`):
  - `FuncionBase.java` — clase base para todas las funciones.
  - `FuncionCero.java` — Z, devuelve 0.
  - `FuncionSucesora.java` — S, suma 1 al primer argumento.
  - `FuncionProyeccion.java` — P_i^n.
  - `Composicion.java` — Composición de funciones.
  - `Combinacion.java` — Combina dos funciones (aquí implementada como suma de resultados; útil para ejemplo).
  - `Recursion.java` — Implementa recursión primitiva (f(x̄,0)=g(x̄); f(x̄,y+1)=h(x̄,y,f(...))).
  - `Suma.java` — clase que construye la suma usando sólo las primitivas y `Recursion`.
  - `Multiplicacion.java` — clase que construye la multiplicación usando `Suma` y `Recursion`.
  - `Potencia.java` — clase que construye la potencia usando `Multiplicacion` y `Recursion`.
  - `Main.java` — ejemplo de uso y pruebas.
- `run.sh` — script para compilar y ejecutar en Linux.
- `run.bat` — equivalente para Windows.
- `bin/` — salida de compilación (generado por `javac -d bin ...`).

Explicación de cada clase (firma y comportamiento)

1) FuncionBase.java
- package: `cc`
- Firma clave:
  - `public int evaluar(List<Integer> args, AtomicInteger contador)` — por defecto incrementa el contador y devuelve 0.
  - `public int getAridad()` — devuelve la aridad esperada.
- Propósito: clase abstracta (no `abstract` estrictamente, pero sirve de base) que define la interfaz de evaluación.

2) FuncionCero.java
- Implementación de Z: `evaluar` ignora `args`, incrementa `contador` y devuelve `0`.
- `getAridad()` devuelve 1 por convención.

3) FuncionSucesora.java
- Implementa S: toma `args.get(0)` y devuelve `args.get(0)+1`. Incremente `contador`.
- `getAridad()` devuelve 1.

4) FuncionProyeccion.java
- Constructor: `FuncionProyeccion(int aridad, int indice)` con `indice` 0-based.
- `evaluar` devuelve `args.get(indice)`.

5) Composicion.java
- Constructor: `Composicion(FuncionBase h, List<FuncionBase> g)`.
- Al evaluar: primero evalúa cada gi con los argumentos dados, obtiene una lista de resultados R, y devuelve `h.evaluar(R, contador)`.
- `getAridad()` = aridad de g[0] si existe, 0 si g vacío.
- Nota práctica: aquí no se verifica que todas las gi tengan la misma aridad; podrías añadir esa comprobación.

6) Combinacion.java
- Constructor: `Combinacion(FuncionBase f1, FuncionBase f2)`.
- En esta implementación `evaluar` hace `r1 = f1(args)`; `r2 = f2(args)` y devuelve `r1 + r2`.
- `getAridad()` se toma de `f1.getAridad()`.
- Observación: "combinación" es un ejemplo; en teoría la combinación podría devolver un vector, pero para encajar con la jerarquía de `FuncionBase` que devuelve `int` hemos usado suma.

7) Recursion.java
- Constructor: `Recursion(FuncionBase g, FuncionBase h)`.
- `getAridad()` = `g.getAridad() + 1` (porque f(x̄,y) añade el parámetro y).
- `evaluar(List<Integer> args, AtomicInteger contador)`: valida la aridad; separa x̄ y y; si y==0 devuelve `g(x̄)`; si y>0 realiza una iteración desde t=0 hasta t=y-1 calculando `prev = h(x̄, t, prev)` empezando con `prev = g(x̄)`.
- Motivación de implementación iterativa: evita límites de pila para llamadas recursivas profundas. Semánticamente equivale a la definición recursiva.

8) Suma.java, Multiplicacion.java, Potencia.java
- Estas clases son envoltorios (wrappers) que construyen la función deseada usando las primitivas y `Recursion`:
  - `Suma` construye Recursion con g = P1^1 y h = S ∘ P3^3.
  - `Multiplicacion` construye Recursion con g = Z y h = plus(z, x) (plus es la instancia de `Suma`).
  - `Potencia` construye Recursion con g = S(Z(x)) (es decir 1) y h = mult(z,x) (mult es `Multiplicacion`).
- `evaluar` delega en la implementación interna.

Cómo compilar y ejecutar

Requisitos previos
- Java JDK 17 o superior (se probó con OpenJDK 17).
- En Linux: `javac` y `java` en el PATH.

Linux (bash)
```bash
cd Pract-3-CC/JAVA
chmod +x run.sh   # si aún no es ejecutable
./run.sh
```
Esto:
- crea `bin/` si no existe
- compila todos los .java (`javac -d bin src/cc/*.java`)
- ejecuta `java -cp bin cc.Main`.

Windows (cmd.exe)
- Doble clic en `run.bat` o ejecutar en cmd:
```
cd Pract-3-CC\JAVA
run.bat
```

Comandos manuales (alternativa)
```bash
cd Pract-3-CC/JAVA
mkdir -p bin
javac -d bin src/cc/*.java
java -cp bin cc.Main
```

Salida esperada (ejemplo)
- Verás la evaluación de S(S(S(10))) y los resultados de las funciones `Suma`, `Multiplicacion` y `Potencia`.

Detalles de implementación y decisiones de diseño

- Uso de `List<Integer>` como representación para argumentos: sencillo y cercano a `std::vector<unsigned int>` de C++.
- Uso de `AtomicInteger` para el contador: permite pasar un contador "por referencia" (como `unsigned int& contador` en C++). Además es seguro si en el futuro se hacen evaluaciones concurrentes.
- Aridad y validaciones: algunas comprobaciones básicas se realizan (e.g., `Recursion` valida la aridad de entrada), pero hay verificaciones adicionales que puedes añadir, por ejemplo:
  - en `Composicion` comprobar que cada gi tiene la misma aridad y que coincide con la longitud de los argumentos pasados.
  - en las primitivas lanzar `IllegalArgumentException` si la lista de argumentos no tiene la longitud esperada.
- Implementación de `Combinacion` como suma: elegí devolver un `int` (en lugar de `List<Integer>`) para encajar con `FuncionBase`. Si prefieres que `Combinacion` produzca vectores (por ejemplo como tu versión C++ original), habría que refactorizar `FuncionBase` y toda la jerarquía para soportar vectores como salida.

Cómo añadir nuevas funciones (guía paso a paso)

Supongamos que quieres definir `f(x,y)` de forma recursiva (o por composición):

1. Identifica si `f` es composición o recursión:
   - Si es composición: crea las funciones gi que necesites (pueden ser primitivas o otras ya definidas), luego usa `new Composicion(h, Arrays.asList(g1, g2, ...))`.
   - Si es recursión: identifica g y h y construye `new Recursion(g, h)`.

2. Garantiza la aridad correcta:
   - Si g: N^n -> N, `Recursion` generará f con aridad n+1.
   - Asegúrate que las funciones que pases a `Composicion` acepten la aridad de entrada adecuada.

3. Evita usar operaciones externas (no usar +, * directamente para implementar la operación en lugar de construirla con las primitivas). La idea es construir con composiciones y recursiones.

Ejemplo: definir Suma (ya incluida)
- g = P1^1 (devuelve x)
- h = S ∘ P3^3  (toma x,y,z -> S(z))
- plus = new Recursion(g, h)

Errores comunes y troubleshooting

- No encuentra `javac` o `java`:
  - Asegúrate que instalaste JDK (no sólo JRE). `java -version` y `javac -version` deben funcionar.
- Error de CLASSPATH al ejecutar `java -cp bin cc.Main`:
  - Verifica que `javac -d bin` colocó los `.class` en `bin/cc/...`.
- `IndexOutOfBoundsException` al evaluar una proyección o sucesora:
  - Es porque la lista de argumentos no tiene la aridad esperada; añade validaciones en las funciones primitivas o asegúrate de pasar correctamente los argumentos.
- Contador de llamadas muy alto:
  - El contador incrementa en cada evaluación de `evaluar`. La recursión y la composición realizan muchas evaluaciones internas, por lo que el número crece rápidamente.

Testing y verificación

- Manual: ejecutar `./run.sh` y revisar la salida.
- Unit tests recomendados (JUnit):
  - Test para `FuncionCero`, `FuncionSucesora`, `FuncionProyeccion` (casos básicos y aridad incorrecta).
  - Test para `Composicion`: con funciones simples comprobar composición correcta.
  - Test para `Recursion`: definir una función pequeña (e.g., f(x,y)=x+y) y comprobar valores.

Preguntas frecuentes

Q: ¿Por qué `Recursion` usa iteración interna en lugar de recursión de Java?  
A: Evita desbordamientos de pila para valores grandes de `y`. La iteración construye el mismo resultado paso a paso.

Q: ¿Puedo usar librerías externas para implementaciones internas (por ejemplo usar `*` en `Multiplicacion`)?  
A: Para respetar la práctica, no uses operaciones aritméticas directas para definir las funciones objetivo: debes construirlas con las primitivas y las combinaciones/recursiones.

Próximos pasos sugeridos

- Añadir comprobaciones de aridad en los constructores y lanzar excepciones claras.
- Añadir tests automáticos (JUnit) y un `pom.xml` o `gradle` si quieres integrarlo con un IDE.
- Refactorizar `FuncionBase` para admitir outputs vectores si te hace falta (esto implica cambiar firmas y adaptar todas las clases).
- Implementar memoización opcional para `Recursion` si necesitas optimizar llamadas repetitivas.

Contacto y mantenimiento

- Los archivos fuente están en `Pract-3-CC/JAVA/src/cc`.
- Si quieres que añada un `Makefile`, `pom.xml` o tests JUnit, dime cuál prefieres y lo preparo.

---

Modo traza (debugging)
----------------------

He incluido un modo de trazado (trace) para inspeccionar las llamadas a las funciones y validar que el orden y los argumentos de las evaluaciones son los esperados.

Qué hace
- Hay una clase `cc.Trace` que controla el trazado. Cuando el trazado está activado, cada llamada que incremente el contador imprimirá una línea con el nombre de la clase que se está evaluando y los argumentos recibidos.
- La salida de traza tiene este formato:
  [TRACE] NombreClase called with args=[...]

Cómo activarlo
- Desde la línea de comandos: pasa `--trace` al ejecutar el programa.
  - Linux:
    ```bash
    ./run.sh --trace
    ```
  - Windows (cmd.exe):
    ```bat
    run.bat --trace
    ```
  Ambos scripts (`run.sh` y `run.bat`) reenvían los argumentos al programa Java.

- También puedes activarlo desde el código llamando a `Trace.setEnabled(true);` antes de evaluar funciones (por ejemplo en `Main`).

Ejemplo de salida
- Fragmento típico:
  ```
  [TRACE] Composicion called with args=[10]
  [TRACE] FuncionProyeccion called with args=[10]
  [TRACE] FuncionSucesora called with args=[10]
  ```
- Cada línea indica una evaluación (por cada incremento del contador). Esto te permite seguir la secuencia de llamadas internas en `Composicion`, `Recursion`, etc.

Limitaciones y recomendaciones
- El modo traza imprime muchas líneas rápidamente (especialmente para `Recursion` con valores grandes). Para analizar la secuencia con tranquilidad usa valores pequeños (por ejemplo `plus(5,3)` o `pow(2,3)`).
- Actualmente la traza muestra el nombre de la clase y los argumentos de entrada. No muestra el valor devuelto ni la profundidad de la llamada. Si quieres, puedo extenderla para:
  - mostrar el valor devuelto (log "before" y "after" cada evaluación),
  - añadir indentación para visualizar la profundidad (usando un `ThreadLocal<Integer>`),
  - filtrar por clase (p. ej. sólo `Recursion`) o volcar la traza a un fichero (`--trace-file trace.log`).

Cómo usar la traza para depuración
- Activa `--trace` y ejecuta el caso que te interese. Observa las líneas que correspondan a la función de más alto nivel y sigue las llamadas internas.
- Para comprobar que `Recursion` está llamando a `h` con los argumentos correctos, busca líneas donde aparezca `Recursion called with args=[..., y]` y las líneas siguientes donde `Composicion`/`FuncionProyeccion`/`FuncionSucesora` son invocadas con los parámetros esperados.


Este README pretende ser exhaustivo para que, desde tu experiencia en C# y tu nueva instalación de Java, puedas: comprender la teoría, entender el mapeo C++ → Java, ejecutar los ejemplos y extender el proyecto siguiendo las reglas de la práctica.

Si quieres, ahora:
- añado comprobaciones de aridad en `Composicion` y `Recursion` (para lanzar errores tempranos),
- añado `Makefile` y tests JUnit,
- o hago una guía paso-a-paso para portar otra función que propongas (p. ej. factorial con recursión primitiva).

Dime qué prefieres y lo hago a continuación.
