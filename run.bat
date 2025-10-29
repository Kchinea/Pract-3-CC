@echo off
REM Compila y ejecuta la práctica Java desde la carpeta JAVA

:: Crear carpeta bin si no existe
if not exist bin mkdir bin

:: Compilar todos los .java en src hacia bin
javac -d bin src\*.java
if errorlevel 1 (
    echo Error: compilacion fallo.
    pause
    exit /b 1
)

:: Ejecutar la clase principal (pasa argumentos del batch)
java -cp bin src.Main %*

:: Mantener ventana abierta para ver la salida
pause
