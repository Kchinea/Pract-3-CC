#!/usr/bin/env bash
# Compila y ejecuta la práctica Java desde la carpeta JAVA (Linux/WSL)
set -euo pipefail

BASEDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$BASEDIR"

# crear bin si no existe
mkdir -p bin

# compilar
echo "Compilando..."
javac -d bin src/*.java

# ejecutar
echo "Ejecutando..."
java -cp bin src.Main "$@"
