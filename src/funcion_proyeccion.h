#ifndef FUNCION_PROYECCION_H
#define FUNCION_PROYECCION_H

#include "funcion_base.h"

// Función inicial: Proyección
// P_i^n: N^n -> N
// P_i^n(x_1, ..., x_n) = x_i
class FuncionProyeccion : public FuncionBase {
private:
    unsigned int aridad_;
    unsigned int indice_;  // índice a proyectar (0-based)
    
public:
    FuncionProyeccion(unsigned int aridad, unsigned int indice) 
        : aridad_(aridad), indice_(indice) {}
    
    unsigned int evaluar(const std::vector<unsigned int>& args, unsigned int& contador) override {
        contador++;
        return args[indice_];
    }
    
    unsigned int getAridad() const override {
        return aridad_;
    }
};

#endif
