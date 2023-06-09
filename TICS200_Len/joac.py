import numpy as np
import matplotlib.pyplot as plt

#Generar vector de puntos para discretizar el eje temporal t
t_positive = np.arange(0, 0.051, 0.001)
t_negative = np.arange(0, -0.051, -0.001)
t = np.concatenate((t_negative[::-1], t_positive))
#Generar vector y con los valores de la función vc(t)
ic = 0.000707 * np.cos(1063829.78*t + 45)

#Generar vector z con los valores de la función ic(t)
vc = 1.4142 * np.cos(1063829.78*t - 45)

#Graficar vc(t)
plt.plot(t, vc, label="vc-teorico(t)")

plt.legend()
plt.xlabel("t")
plt.ylabel("Valor")
plt.title("Curvas teóricas de vc(t)")

plt.show()