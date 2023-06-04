def read_labyrinth(file_name):
  labyrinth = []
  with open(file_name, 'r') as file:
    for line in file:
      row = [int(cell) for cell in line.strip() if cell.strip()]
      labyrinth.append(row)
  return labyrinth

def solve_labyrinth(labyrinth):
  rows = len(labyrinth)
  cols = len(labyrinth[0])
  start_row = -1
  end_row = -1

  # Encontrar la posición de entrada y salida
  for i in range(rows):
    if labyrinth[i][0] == 0:
      start_row = i
    if labyrinth[i][cols - 1] == 0:
      end_row = i

  if start_row == -1 or end_row == -1:
    return []  # No se encontró una entrada o salida válida

  # Función de validación de movimiento
  def is_valid_move(row, col):
    if row < 0 or row >= rows or col < 0 or col >= cols:
      return False  # Fuera de los límites del laberinto
    if labyrinth[row][col] == 1:
      return False  # Obstáculo en la celda
    return True

  # Función de búsqueda recursiva
  def search(row, col, path):
    if col == cols - 1 and row == end_row:
      # Se alcanzó la salida
      paths.append(path + [(row, col)])
    else:
      # Generar movimientos válidos
      moves = [(0, 1), (0, -1), (1, 0), (-1, 0)
               ]  # Movimientos posibles: derecha, izquierda, abajo, arriba
      for move in moves:
        new_row = row + move[0]
        new_col = col + move[1]
        if is_valid_move(new_row, new_col):
          labyrinth[row][col] = 1  # Marcar la celda actual como visitada
          search(new_row, new_col, path + [(row, col)])
          labyrinth[row][
            col] = 0  # Restaurar la celda después de la exploración

  paths = []
  search(start_row, 0, [])
  return paths


# Ejemplo de uso
file_name = 'input.txt'  # Reemplaza con el nombre de tu archivo de entrada
labyrinth = read_labyrinth(file_name)
solutions = solve_labyrinth(labyrinth)

# Guardar las soluciones en un archivo de salida
output_file = 'output.txt'  # Nombre del archivo de salida
with open(output_file, 'w') as file:
  for solution in solutions:
    file.write(' '.join([f'({row},{col})' for row, col in solution]) + '\n')
