#ifndef FUNCIONES_PRIMITIVAS_H
#define FUNCIONES_PRIMITIVAS_H

#include "funcion_base.h"
#include "funcion_cero.h"
#include "funcion_sucesora.h"
#include "funcion_proyeccion.h"
#include "funcion_composicion.h"
#include "funcion_recursion.h"
#include <memory>

// Construcción de funciones primitivas recursivas complejas

// SUMA: suma(x, y) = x + y
// Definición recursiva:
// suma(x, 0) = x              -> g(x) = P_1^1(x) = x
// suma(x, y+1) = S(suma(x, y)) -> h(x, y, z) = S(P_3^3(x, y, z)) = S(z)
std::shared_ptr<FuncionBase> construirSuma() {
    // g(x) = P_1^1(x) = x
    auto g = std::make_shared<FuncionProyeccion>(1, 0);
    
    // h(x, y, z) = S(z) = S(P_3^3(x, y, z))
    auto succ = std::make_shared<FuncionSucesora>();
    auto p3_3 = std::make_shared<FuncionProyeccion>(3, 2);
    std::vector<std::shared_ptr<FuncionBase>> g_vec = {p3_3};
    auto h = std::make_shared<FuncionComposicion>(succ, g_vec);
    
    // Recursión primitiva
    return std::make_shared<FuncionRecursion>(g, h);
}

// MULTIPLICACIÓN: mult(x, y) = x * y
// Definición recursiva:
// mult(x, 0) = 0              -> g(x) = Z(x) = 0
// mult(x, y+1) = suma(x, mult(x, y)) -> h(x, y, z) = suma(x, z)
std::shared_ptr<FuncionBase> construirMultiplicacion() {
    // g(x) = Z(x) = 0
    auto g = std::make_shared<FuncionCero>();
    
    // h(x, y, z) = suma(x, z) = suma(P_1^3(x,y,z), P_3^3(x,y,z))
    auto suma = construirSuma();
    auto p1_3 = std::make_shared<FuncionProyeccion>(3, 0);
    auto p3_3 = std::make_shared<FuncionProyeccion>(3, 2);
    std::vector<std::shared_ptr<FuncionBase>> g_vec = {p1_3, p3_3};
    auto h = std::make_shared<FuncionComposicion>(suma, g_vec);
    
    // Recursión primitiva
    return std::make_shared<FuncionRecursion>(g, h);
}

// POTENCIA: potencia(x, y) = x^y
// Definición recursiva:
// potencia(x, 0) = 1              -> g(x) = S(Z(x)) = 1
// potencia(x, y+1) = mult(x, potencia(x, y)) -> h(x, y, z) = mult(x, z)
std::shared_ptr<FuncionBase> construirPotencia() {
    // g(x) = 1 = S(Z(x))
    auto cero = std::make_shared<FuncionCero>();
    std::vector<std::shared_ptr<FuncionBase>> g_vec = {cero};
    auto succ = std::make_shared<FuncionSucesora>();
    auto g = std::make_shared<FuncionComposicion>(succ, g_vec);
    
    // h(x, y, z) = mult(x, z) = mult(P_1^3(x,y,z), P_3^3(x,y,z))
    auto mult = construirMultiplicacion();
    auto p1_3 = std::make_shared<FuncionProyeccion>(3, 0);
    auto p3_3 = std::make_shared<FuncionProyeccion>(3, 2);
    std::vector<std::shared_ptr<FuncionBase>> h_vec = {p1_3, p3_3};
    auto h = std::make_shared<FuncionComposicion>(mult, h_vec);
    
    // Recursión primitiva
    return std::make_shared<FuncionRecursion>(g, h);
}

#endif
