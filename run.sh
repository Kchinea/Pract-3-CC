#!/usr/bin/env bash
# Compila y ejecuta la práctica Java desde la carpeta JAVA (Linux/WSL)
set -euo pipefail

BASEDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$BASEDIR"

# crear bin si no existe
mkdir -p bin

# compile all java sources under src recursively
echo "Compiling..."
javac -d bin $(find src -name "*.java")

# run the main class (updated package)
echo "Running..."
java -cp bin pract3.core.Main "$@"
