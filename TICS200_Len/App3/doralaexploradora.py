"""
El objetivo del codigo es a partir de una red de funciones recursivas que buscan el camino apropiado
para encontrar caminos de un "laberinto", con distintas posiciones con valores 0 para un camino libre,
y un 1 para un camino cerrado, Este codigo busca en primer lugar hacia arriba, lo que hace es buscar un 0 arriba,
luego abajo, luego izquierda y derecha. Este codigo recorre la rama en cada direccion añadiendo
variables de tipo Booleanas en cada casilla que recorre para ver si es valido o no, despues avanzará
hasta el final de la rama y después cuando llega a un final que no sea "valido" (valido se refiere al final o al objetivo)
este se devuelve por el camino que paso hasta llegar a la ultima posición que tenga mas de un camino,
y luego prueba en la siguiente dirección, hasta asi llegar al objetivo , y cuando llegue este igual se devuelve
hasta que prueba todos los caminos y puede verificar cual es el mas corto por ejemplo.
Esto se basa en un archivo txt para el input, el codigo trabaja con este y se expresa en el output.
"""
import numpy as np

def buscarInicioFinal(matrizLab):
    inicio = list(filter(lambda fila: fila[0] == 0, matrizLab))
    inicio = list(map(lambda fila: (matrizLab.index(fila), 0), inicio))

    final = list(filter(lambda fila: fila[-1] == 0, matrizLab))
    final = list(map(lambda fila: (matrizLab.index(fila), len(fila) - 1), final))

    return inicio, final

def buscarRecorridos(matrizLab, fila, columna, matrizVisitas, recorrido, recorridosValidos, coordenadasFinal):
    # Verificar límites de la matriz y si la celda ya fue visitada
    if (
        fila < 0 or fila >= len(matrizLab)
        or columna < 0 or columna >= len(matrizLab[0])
        or matrizLab[fila][columna] == 1
        or matrizVisitas[fila][columna]
    ):
        return

    # Marcar la celda como visitada y agregarla al camino actual
    matrizVisitas[fila][columna] = True
    recorrido.append((fila, columna))

    # Si se llega a la posición de destino  [4, 4], agregar el camino a la lista de caminos
    if fila == coordenadasFinal[0][0] and columna == coordenadasFinal[0][1]:
        recorridosValidos.append(list(recorrido))

    # Recursivamente explorar los vecinos en todas las direcciones en sentido horario comenznado por la derecha 
    # ya que es el supuesto de los laberintos entregados:
    # Derecha, Abajo, Izquierda, Arriba
    buscarRecorridos(matrizLab, fila, columna + 1, matrizVisitas, recorrido, recorridosValidos, coordenadasFinal)  # vecino derecho
    buscarRecorridos(matrizLab, fila + 1, columna, matrizVisitas, recorrido, recorridosValidos, coordenadasFinal)  # vecino inferior
    buscarRecorridos(matrizLab, fila, columna - 1, matrizVisitas, recorrido, recorridosValidos, coordenadasFinal)  # vecino izquierdo
    buscarRecorridos(matrizLab, fila - 1, columna, matrizVisitas, recorrido, recorridosValidos, coordenadasFinal)  # vecino superior

    # Desmarcar la celda y eliminarla del camino actual antes de retroceder
    matrizVisitas[fila][columna] = False
    recorrido.pop()

def leer_Archivo(nombreArchivo):
    with open(nombreArchivo, "r") as file:
        return [list(map(int, line.strip().split())) for line in file]

# Leer la matriz desde el archivo de texto
with open("App3/input.txt", "r") as file:
    matrizLab = [list(map(int, line.strip().split())) for line in file]

# Encontrar las posiciones de los ceros en la primeraa y última columna
coordenadasInicio, coordenadasFinal = buscarInicioFinal(matrizLab)

# Crear una matriz de visitados para rastrear las celdas visitadas
matrizVisitas = np.full((len(matrizLab), len(matrizLab[0])), False, dtype=bool)

# Lista para almacenar los caminos encontrados
recorridos = []

# Llamar a la función para encontrar todos los caminos posibles
buscarRecorridos(matrizLab, coordenadasInicio[0][0], coordenadasInicio[0][1], matrizVisitas, [], recorridos, coordenadasFinal)

# Enumerar y imprimir los caminos encontrados
print(recorridos)

def escribir_Archivo(nombreArchivo, recorridos):
    with open(nombreArchivo, "w") as file:
        file.write(",".join(map(str, recorridos)))

if __name__ == "__main__":
    leer_Archivo("App3/input.txt")
    buscarInicioFinal(matrizLab)
    buscarRecorridos(matrizLab, coordenadasInicio[0][0], coordenadasInicio[0][1], matrizVisitas, [], recorridos, coordenadasFinal)
    escribir_Archivo("App3/output.txt", recorridos)
    print(recorridos)
    print(matrizLab)
    print(coordenadasInicio)
    print(coordenadasFinal)
    print(matrizVisitas)