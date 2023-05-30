import numpy as np
import functools as fu
import copy

def leer_archivo(filename):
    return np.loadtxt(filename, dtype=int)


#Primero se establece la funcion de orden superior que se encargara de evaluar si el movimiento es posible
#Se establecen las condiciones que debe cumplir el movimiento para ser posible: bordes, libre y no volver

def evaluar_avance(nXAvanzar, nYAvanzar):
    limites = [fu.partial(borde, nXAvanzar, nYAvanzar), 
                   fu.partial(libre, nXAvanzar, nYAvanzar),
                   fu.partial(no_volver, nXAvanzar, nYAvanzar),
    ]
    return all(map(lambda x: x(), limites))

    #Se adjuntan links para donde se referencia parte del codigo utilizado
    
    #link 1: https://www.youtube.com/watch?v=B9yM53qkbW0
    #link 2: https://rico-schmidt.name/pymotw-3/functools/index.html
    #link 3: https://www.youtube.com/watch?v=_yoS44fnK1o&list=PLM-p96nOrGcb9HZLicMbapBch0UjRAMZP
    
#Se crea la funcion avanzar_labertino que se encargara de avanzar por el laberinto dadas las condiciones establecidas
# por evaluar_avance (limites), ademas se agregan las coordenadas como tal al recorrido y se evalua si se llego a la salida


def avanzar_laberinto(aCoordenadaActual):
    aRecorrido.append(aCoordenadaActual)
    if (aCoordenadaActual == aSalida):
        aSoluciones.append(copy.copy(aRecorrido))
    else:
        movimientos = [
            [aCoordenadaActual[0] - 1, aCoordenadaActual[1]],
            [aCoordenadaActual[0], aCoordenadaActual[1] + 1],
            [aCoordenadaActual[0] + 1, aCoordenadaActual[1]],
            [aCoordenadaActual[0], aCoordenadaActual[1] - 1],  
        ]
        nuevos = list(filter(lambda x: evaluar_avance(x[0], x[1]), movimientos))
        list(map(lambda x: avanzar_laberinto(x), nuevos))

#OBSEVACIONES: notar abajo el siguiente codigo, lo ideal era crear otra funcion de orden superior con avanza_laberinto
# donde se agregan 4 funciones más, una por cada movimiento posible, pero no logre hacerlo funcionar, por lo que se opto por el codigo actual

#def avanzar_laberinto(aCoordenadaActual):
#    aRecorrido.append(aCoordenadaActual)
#    if (aCoordenadaActual == aSalida):
#        aSoluciones.append(copy.copy(aRecorrido))
#    else:
#       movimientos = [
#            fu.partial(izquierda, nXAvanzar, nYAvanzar),   
#            fu.partial(arriba, nXAvanzar, nYAvanzar),
#            fu.partial(derecha, nXAvanzar, nYAvanzar),
#            fu.partial(abajo, nXAvanzar, nYAvanzar),



#def izquierda(aCoordenadaActual):
#    if (borde(aCoordenadaActual[0], aCoordenadaActual[1]) and libre(aCoordenadaActual[0], aCoordenadaActual[1]) and no_volver(aCoordenadaActual[0], aCoordenadaActual[1])):
#        avanzar_laberinto(aCoordenadaActual)
#
#def arriba(aCoordenadaActual):
#    if (borde(aCoordenadaActual[0], aCoordenadaActual[1]) and libre(aCoordenadaActual[0], aCoordenadaActual[1]) and no_volver(aCoordenadaActual[0], aCoordenadaActual[1])):
#        avanzar_laberinto(aCoordenadaActual)
#
#def derecha(aCoordenadaActual):
#    if (borde(aCoordenadaActual[0], aCoordenadaActual[1]) and libre(aCoordenadaActual[0], aCoordenadaActual[1]) and no_volver(aCoordenadaActual[0], aCoordenadaActual[1])):
#        avanzar_laberinto(aCoordenadaActual)
#
#def abajo(aCoordenadaActual):
#    if (borde(aCoordenadaActual[0], aCoordenadaActual[1]) and libre(aCoordenadaActual[0], aCoordenadaActual[1]) and no_volver(aCoordenadaActual[0], aCoordenadaActual[1])):
#        avanzar_laberinto(aCoordenadaActual)

def borde(nXAvanzar, nYAvanzar):
    return ((nXAvanzar >= 0) and (nXAvanzar <= aLaberinto.shape[0] - 1)
            and (nYAvanzar >= 0)
            and (nYAvanzar <= aLaberinto.shape[1] - 1))

def libre(nXAvanzar, nYAvanzar):
    return (aLaberinto[nXAvanzar, nYAvanzar] == 0)

def no_volver(nXAvanzar, nYAvanzar):
    return ([nXAvanzar, nYAvanzar] not in aRecorrido)

#se crea la funcion crear_output que se encargara de crear el archivo de salida con las coordenadas de la solucion
def crear_output(filename, sol):
    with open(filename, 'w') as f:
        for solucion in sol:
            coordenadas = list(map(lambda x: str(x), solucion))
            f.write(",".join(coordenadas) + "\n")
    
#se mantiene el resto del codigo de paradigma procedural. 
if __name__ == "__main__":
  # Crea laberinto en un arreglo numpy
  aLaberinto = leer_archivo("input.txt")
  print("Laberinto de {} filas por {} columnas:".format(aLaberinto.shape[0], aLaberinto.shape[1]))
  print(aLaberinto)

  # Obtiene la coordenada de entrada al laberinto que corresponde al primer 1 de la columna 0
  for x in range(aLaberinto.shape[0]):
    if aLaberinto[x, 0] == 0:
      aEntrada = [x, 0]
      break
  print("Entrada: fila {} - columna {}".format(aEntrada[0], aEntrada[1]))

  # Obtiene la coordenada de salida que corresponde al primer 1 de la última columna
  for x in range(aLaberinto.shape[0]):
    if aLaberinto[x, aLaberinto.shape[1] - 1] == 0:
      aSalida = [x, aLaberinto.shape[1] - 1]
      break
  print("Salida: fila {} - columna {}:".format(aSalida[0], aSalida[1]))

  # Creamos un listas vacías para el recorrido y para las soluciones
  aRecorrido = []
  aSoluciones = []

  # Activamos función para avanzar desde la entrada (llamada recursiva)
  avanzar_laberinto(aEntrada)

  # Impimimos soluciones (falta vaciado a output.txt)
  print(aSoluciones)
  
crear_output("output.txt", aSoluciones)
  