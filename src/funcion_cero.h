#ifndef FUNCION_CERO_H
#define FUNCION_CERO_H

#include "funcion_base.h"

// Función inicial: Cero
// Z: N -> N
// Z(x) = 0
class FuncionCero : public FuncionBase {
public:
    FuncionCero() = default;
    
    unsigned int evaluar(const std::vector<unsigned int>& args, unsigned int& contador) override {
        contador++;
        return 0;
    }
    
    unsigned int getAridad() const override {
        return 1;
    }
};

#endif
