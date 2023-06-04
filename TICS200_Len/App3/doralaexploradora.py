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

def find_zeros_in_columns(matrix):
    zeros_first_column = list(filter(lambda row: row[0] == 0, matrix))
    zeros_first_column = list(map(lambda row: (matrix.index(row), 0), zeros_first_column))

    zeros_last_column = list(filter(lambda row: row[-1] == 0, matrix))
    zeros_last_column = list(map(lambda row: (matrix.index(row), len(row) - 1), zeros_last_column))

    return zeros_first_column, zeros_last_column


def find_paths(matrizLab, row, col, visited, path, paths, zeros_last_column):
    # Verificar límites de la matriz y si la celda ya fue visitada
    if (
        row < 0 or row >= len(matrizLab)
        or col < 0 or col >= len(matrizLab[0])
        or matrizLab[row][col] == 1
        or visited[row][col]
    ):
        return

    # Marcar la celda como visitada y agregarla al camino actual
    visited[row][col] = True
    path.append((row, col))

    # Si se llega a la posición de destino [4, 4], agregar el camino a la lista de caminos
    if row == zeros_last_column[0][0] and col == zeros_last_column[0][1]:
        paths.append(list(path))

    # Recursivamente explorar los vecinos en todas las direcciones
    find_paths(matrizLab, row - 1, col, visited, path, paths, zeros_last_column)  # vecino superior
    find_paths(matrizLab, row + 1, col, visited, path, paths, zeros_last_column)  # vecino inferior
    find_paths(matrizLab, row, col - 1, visited, path, paths, zeros_last_column)  # vecino izquierdo
    find_paths(matrizLab, row, col + 1, visited, path, paths, zeros_last_column)  # vecino derecho

    # Desmarcar la celda y eliminarla del camino actual antes de retroceder
    visited[row][col] = False
    path.pop()


# Leer la matriz desde el archivo de texto
with open("App3/input.txt", "r") as file:
    matrix = [list(map(int, line.strip().split())) for line in file]

# Encontrar las posiciones de los ceros en la primeraa y última columna
zeros_first_column, zeros_last_column = find_zeros_in_columns(matrix)

# Crear una matriz de visitados para rastrear las celdas visitadas
visited = np.full((len(matrix), len(matrix[0])), False, dtype=bool)

# Lista para almacenar los caminos encontrados
paths = []

# Llamar a la función para encontrar todos los caminos posibles
find_paths(matrix, zeros_first_column[0][0], zeros_first_column[0][1], visited, [], paths, zeros_last_column)

# Enumerar y imprimir los caminos encontrados
print(paths)

with open("App3/output.txt", "w") as output_file:
    for path in paths:
        output_file.write(",".join(map(str, path)) + "\n")