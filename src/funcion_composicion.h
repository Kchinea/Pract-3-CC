#ifndef FUNCION_COMPOSICION_H
#define FUNCION_COMPOSICION_H

#include "funcion_base.h"
#include <memory>

// Operación: Composición
// Si h: N^k -> N y g_1, ..., g_k: N^n -> N, entonces
// f(x_1, ..., x_n) = h(g_1(x_1, ..., x_n), ..., g_k(x_1, ..., x_n))
class FuncionComposicion : public FuncionBase {
private:
    std::shared_ptr<FuncionBase> h_;
    std::vector<std::shared_ptr<FuncionBase>> g_;
    unsigned int aridad_;
    
public:
    FuncionComposicion(std::shared_ptr<FuncionBase> h, 
                       std::vector<std::shared_ptr<FuncionBase>> g)
        : h_(h), g_(g) {
        if (!g.empty()) {
            aridad_ = g[0]->getAridad();
        } else {
            aridad_ = 0;
        }
    }
    
    unsigned int evaluar(const std::vector<unsigned int>& args, unsigned int& contador) override {
        contador++;
        std::vector<unsigned int> resultados_g;
        
        // Evaluar cada g_i con los argumentos
        for (const auto& gi : g_) {
            resultados_g.push_back(gi->evaluar(args, contador));
        }
        
        // Aplicar h a los resultados
        return h_->evaluar(resultados_g, contador);
    }
    
    unsigned int getAridad() const override {
        return aridad_;
    }
};

#endif
