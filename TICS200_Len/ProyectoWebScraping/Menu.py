import estadisticas
import guardarCSV


class Menu:
    def __init__(self, listaProductos):
        self.opcion_valida = False
        self.listaProductos = listaProductos

    def opcion_1(self):
        precios = estadisticas.obtener_precio_minimo_paquete(self.listaProductos)
        print(precios)
        # Aquí puedes escribir el código correspondiente a la opción 1

    def opcion_2(self):
        precios = estadisticas.obtener_precio_maximo_paquete(self.listaProductos)
        print(precios)
        # Aquí puedes escribir el código correspondiente a la opción 2

    def opcion_3(self):
        precios = estadisticas.calcular_promedio_precios_patron(self.listaProductos)
        print(precios)
        # Aquí puedes escribir el código correspondiente a la opción 3

    def opcion_4(self):
        precios = estadisticas.calcular_promedio_precios_patron_multi(
            self.listaProductos
        )
        print(precios)
        # Aquí puedes escribir el código correspondiente a la opción 1

    def opcion_5(self):
        precios = estadisticas.precio_maximo_patron(self.listaProductos)
        print(precios)
        # Aquí puedes escribir el código correspondiente a la opción 2

    def opcion_6(self):
        precios = estadisticas.obtener_precios_maximos_patron_multi(self.listaProductos)
        print(precios)

    def opcion_7(self):
        precios = estadisticas.precio_minimo_patron(self.listaProductos)
        print(precios)
        # Aquí puedes escribir el código correspondiente a la opción 1

    def opcion_8(self):
        precios = estadisticas.obtener_precios_minimos_patron_multi(self.listaProductos)
        print(precios)
        # Aquí puedes escribir el código correspondiente a la opción 2

    def mostrar_menu(self):
        print("Bienvenido al Menú de Opciones")
        print("1. Precio Minimo Paquete")
        print("2. Precio Maximo Paquete")
        print("3. Precio Promedio por Patron de Busqueda")
        print("4. Precio Promedio por Tienda y Patron de Busqueda")
        print("5. Precio Maximo por Patron de Busqueda")
        print("6. Precios Maximo por Patron de Busqueda y Multitienda")
        print("7. Precio Minimo por Patron de Busqueda")
        print("8. Precios Minimos por Patron de Busqueda y Multitienda")
        print("9. Salir")

    def ejecutar(self):
        while not self.opcion_valida:
            self.mostrar_menu()
            opcion = input("Selecciona una opción (1-9): ")

            if opcion == "1":
                self.opcion_1()
            elif opcion == "2":
                self.opcion_2()
            elif opcion == "3":
                self.opcion_3()
            elif opcion == "4":
                self.opcion_4()
            elif opcion == "5":
                self.opcion_5()
            elif opcion == "6":
                self.opcion_6()
            elif opcion == "7":
                self.opcion_7()
            elif opcion == "8":
                self.opcion_8()
            elif opcion == "9":
                print("¡Hasta luego!")
                guardarCSV.escribir_csv(self.listaProductos, "todosolo.csv")
                self.opcion_valida = True
            else:
                print(
                    "TU OPCIÓN NO ESTÁ ENTRE LAS QUE TE DIMOS A ESCOGER, VUELVE A COLOCAR UNA OPCIÓN"
                )
