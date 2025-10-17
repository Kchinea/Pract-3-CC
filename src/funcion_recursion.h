#ifndef FUNCION_RECURSION_H
#define FUNCION_RECURSION_H

#include "funcion_base.h"
#include <memory>

// Operación: Recursión Primitiva
// Si g: N^n -> N y h: N^(n+2) -> N, entonces f: N^(n+1) -> N se define como:
// f(x_1, ..., x_n, 0) = g(x_1, ..., x_n)
// f(x_1, ..., x_n, y+1) = h(x_1, ..., x_n, y, f(x_1, ..., x_n, y))
class FuncionRecursion : public FuncionBase {
private:
    std::shared_ptr<FuncionBase> g_;  // caso base
    std::shared_ptr<FuncionBase> h_;  // caso recursivo
    unsigned int aridad_;
    
public:
    FuncionRecursion(std::shared_ptr<FuncionBase> g, 
                     std::shared_ptr<FuncionBase> h)
        : g_(g), h_(h) {
        aridad_ = g->getAridad() + 1;
    }
    
    unsigned int evaluar(const std::vector<unsigned int>& args, unsigned int& contador) override {
        contador++;
        
        // Separar argumentos: x_1, ..., x_n, y
        unsigned int y = args.back();
        std::vector<unsigned int> x(args.begin(), args.end() - 1);
        
        // Caso base: y = 0
        if (y == 0) {
            return g_->evaluar(x, contador);
        }
        
        // Caso recursivo: y > 0
        // Calcular f(x_1, ..., x_n, y-1)
        std::vector<unsigned int> args_recursivos = x;
        args_recursivos.push_back(y - 1);
        unsigned int resultado_anterior = evaluar(args_recursivos, contador);
        
        // Calcular h(x_1, ..., x_n, y-1, f(x_1, ..., x_n, y-1))
        std::vector<unsigned int> args_h = x;
        args_h.push_back(y - 1);
        args_h.push_back(resultado_anterior);
        
        return h_->evaluar(args_h, contador);
    }
    
    unsigned int getAridad() const override {
        return aridad_;
    }
};

#endif
