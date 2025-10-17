#ifndef FUNCION_SUCESORA_H
#define FUNCION_SUCESORA_H

#include "funcion_base.h"

// Función inicial: Sucesora
// S: N -> N
// S(x) = x + 1
class FuncionSucesora : public FuncionBase {
public:
    FuncionSucesora() = default;
    
    unsigned int evaluar(const std::vector<unsigned int>& args, unsigned int& contador) override {
        contador++;
        return args[0] + 1;
    }
    
    unsigned int getAridad() const override {
        return 1;
    }
};

#endif
