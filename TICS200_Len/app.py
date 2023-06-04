def operaciones_basicas(operacion):
    def suma(a, b):
        return a + b
    def resta(a, b):
        return a - b
    def multiplicacion(a, b):
        return a * b
    def division(a, b):
        return a / b
    def exponente(a):
        return a**2
    def raiz_cuadrada(a):
        return a**0.5
    def hacer_nada():
        return None
    if operacion.upper() in ("SUMA", "ADICION"):
        funcion_retorno = suma
    elif operacion.upper() in ("RESTA", "SUSTRACCCION"):
        funcion_retorno = resta
    elif operacion.upper() in ("MULTIPLICACION", "PRODUCTO"):
        funcion_retorno = multiplicacion
    elif operacion.upper() == "DIVISION":
        funcion_retorno = division
    elif operacion.upper() == "EXPONENTE":
        funcion_retorno = exponente
    elif operacion.upper() == "RAIZ CUADRADA":
        funcion_retorno = raiz_cuadrada
    else:
        funcion_retorno = hacer_nada
    return funcion_retorno

def main():
    lista1 = list(range(1, 11))

    funcion1 = operaciones_basicas("EXPONENTE")

    lista2 = list(map(funcion1, lista1))

    funcion2 = operaciones_basicas("RAIZ CUADRADA")

    lista3 = [int(funcion2(x)) for x in lista2]
    
    print("lista1:", lista1)
    print("lista2:", lista2)
    print("lista3:", lista3)
    
if __name__ == "__main__":
    main()
