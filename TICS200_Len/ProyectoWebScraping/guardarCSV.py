import csv


def escribir_csv(productos, nombre_archivo):
    # Función auxiliar para obtener los valores de los atributos del objeto Producto
    def obtener_valores(producto):
        return [
            producto.getPatronBusqueda(),
            producto.getMultiTienda(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getPrecioUf(),
        ]

    # Obtener los encabezados
    encabezados = ["PatronBusqueda", "MultiTienda", "Descripcion", "Precio", "PrecioUF"]

    # Obtener una lista de listas con los valores de los productos
    datos = list(map(obtener_valores, productos))

    # Agregar los encabezados al principio de la lista de datos
    datos.insert(0, encabezados)

    # Escribir los datos en un archivo CSV
    with open(nombre_archivo, "w", newline="") as archivo_csv:
        escritor = csv.writer(archivo_csv)
        escritor.writerows(datos)

    print(f"Archivo CSV '{nombre_archivo}' creado exitosamente.")
