def find_paths(matrix, row, col, visited, path, paths):
    start_end=find_start_end_coordinates(matrix)
    # Encuentra la coordenada de inicio (valor 0 en la primera columna)
    # Verificar límites de la matriz y si la celda ya fue visitada
    if (row < 0 or row >= len(matrix) or col < 0 or col >= len(matrix[0]) or
            matrix[row][col] != 0 or visited[row][col]):
        return

    # Marcar la celda como visitada y agregarla al camino actual
    visited[row][col] = True
    path.append((row, col))

    # Si se llega a la posición de destino [4, 4], agregar el camino a la lista de caminos
    if (row,col) == start_end[1]:
        paths.append(list(path))

    # Recursivamente explorar los vecinos en todas las direcciones
    find_paths(matrix, row - 1, col, visited, path, paths)  # vecino superior
    find_paths(matrix, row + 1, col, visited, path, paths)  # vecino inferior
    find_paths(matrix, row, col - 1, visited, path, paths)  # vecino izquierdo
    find_paths(matrix, row, col + 1, visited, path, paths)  # vecino derecho

    # Desmarcar la celda y eliminarla del camino actual antes de retroceder
    visited[row][col] = False
    path.pop()

def find_start_end_coordinates(matrix):
    # Encuentra la coordenada de inicio (valor 0 en la primera columna)
    start_coordinate = next(
        ((row, 0) for row in range(len(matrix)) if matrix[row][0] == 0), None
    )

    # Encuentra la coordenada de final (valor 0 en la última columna)
    end_coordinate = next(
        ((row, len(matrix[0]) - 1) for row in range(len(matrix)) if matrix[row][-1] == 0), None
    )

    return start_coordinate, end_coordinate

matrix = [
    [1, 0, 0, 0, 1],
    [1, 0, 1, 0, 1],
    [1, 0, 1, 0, 1],
    [0, 0, 1, 0, 0],
    [1, 0, 0, 1, 1]
]

# Crear una matriz de visitados para rastrear las celdas visitadas
visited = [[False for _ in range(len(matrix[0]))] for _ in range(len(matrix))]

# Lista para almacenar los caminos encontrados
paths = []

# Llamar a la función para encontrar todos los caminos posibles
find_paths(matrix, 0, 0, visited, [], paths)

# Imprimir los caminos encontrados
print(paths)
