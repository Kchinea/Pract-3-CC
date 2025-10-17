#include <iostream>
#include <vector>
#include "funciones_primitivas.h"

void mostrarUso() {
    std::cout << "Uso: ./potencia [x y]\n";
    std::cout << "Calcula x^y usando funciones primitivas recursivas\n";
    std::cout << "Si no se proporcionan argumentos, se solicitarán por teclado\n";
}

int main(int argc, char* argv[]) {
    unsigned int x, y;
    
    // Leer argumentos desde la línea de comandos o desde teclado
    if (argc == 3) {
        try {
            x = std::stoi(argv[1]);
            y = std::stoi(argv[2]);
        } catch (...) {
            std::cerr << "Error: Los argumentos deben ser números naturales\n";
            mostrarUso();
            return 1;
        }
    } else if (argc == 1) {
        std::cout << "Cálculo de potencia usando funciones primitivas recursivas\n";
        std::cout << "=========================================================\n\n";
        std::cout << "Ingrese el valor de x (base): ";
        std::cin >> x;
        std::cout << "Ingrese el valor de y (exponente): ";
        std::cin >> y;
    } else {
        mostrarUso();
        return 1;
    }
    
    // Construir la función potencia
    auto potencia = construirPotencia();
    
    // Evaluar con contador de llamadas
    unsigned int contador = 0;
    std::vector<unsigned int> args = {x, y};
    unsigned int resultado = potencia->evaluar(args, contador);
    
    // Mostrar resultados
    std::cout << "\n=== RESULTADOS ===\n";
    std::cout << "Operación: " << x << "^" << y << "\n";
    std::cout << "Resultado: " << resultado << "\n";
    std::cout << "Número total de llamadas a funciones: " << contador << "\n";
    
    return 0;
}
