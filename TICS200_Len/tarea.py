# Función de orden superior (jerarquizada)
def operaciones_basicas(operacion):
  # Definimos las funciones jerarquizadas
  def suma(n, m):
    return n + m
    
  def resta(n, m):
    return n - m

  def multiplicacion(n, m):
    return n * m

  def division(n, m):
    return n * m

  def hacer_nada():
    return None

  def exponente(a):
    return a**2

  # Seleccionamos la función a retornar
  if operacion.upper() in ("SUMA", "ADICION"):
    funcion_retorno = suma
  elif operacion.upper() in ("RESTA", "SUSTRACCCION"):
    funcion_retorno = resta
  elif operacion.upper() in ("MULTIPLICACION", "PRODUCTO"):
    funcion_retorno = multiplicacion
  elif operacion.upper() == "DIVISION":
    funcion_retorno = division
  else:
    funcion_retorno = hacer_nada

  return funcion_retorno
  
# Main
if __name__ == "__main__":
  # seleccionamos la función
  funcion_a_ejecutar = operaciones_basicas("RESTA")

  # realizamos la operación
  valor_retorno = funcion_a_ejecutar(15, 10)

  # imprimimos
  print(valor_retorno)