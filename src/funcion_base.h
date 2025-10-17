#ifndef FUNCION_BASE_H
#define FUNCION_BASE_H

#include <vector>
#include <memory>

// Clase base abstracta para todas las funciones primitivas recursivas
class FuncionBase {
public:
    virtual ~FuncionBase() = default;
    
    // Evalúa la función con los argumentos dados
    // Incrementa el contador de llamadas
    virtual unsigned int evaluar(const std::vector<unsigned int>& args, unsigned int& contador) = 0;
    
    // Obtiene la aridad de la función (número de argumentos)
    virtual unsigned int getAridad() const = 0;
};

#endif
