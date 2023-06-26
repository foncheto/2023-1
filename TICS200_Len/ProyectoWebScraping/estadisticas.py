from functools import reduce


# Precios Minimos por Patron de Busqueda y Multitienda
def obtener_precios_minimos_patron_multi(resultados):
    def actualizar_precio_minimo(precios_minimos, resultado):
        patron_busqueda = resultado.getPatronBusqueda()
        multitienda = resultado.getMultiTienda()
        precio_pesos = int(resultado.getPrecio())
        precio_uf = float(resultado.getPrecioUf())

        # Crea una nueva entrada en el diccionario si no existe
        precios_minimos[(patron_busqueda, multitienda)] = precios_minimos.get(
            (patron_busqueda, multitienda),
            {
                "precio_pesos": int(resultado.getPrecio()),
                "precio_uf": float(resultado.getPrecioUf()),
            },
        )

        # Actualiza el precio mínimo si el precio actual es menor
        precios_minimos[(patron_busqueda, multitienda)]["precio_pesos"] = min(
            precios_minimos[(patron_busqueda, multitienda)]["precio_pesos"],
            precio_pesos,
        )

        precios_minimos[(patron_busqueda, multitienda)]["precio_uf"] = min(
            precios_minimos[(patron_busqueda, multitienda)]["precio_uf"], precio_uf
        )

        return precios_minimos

    precios_minimos = reduce(actualizar_precio_minimo, resultados, {})

    return precios_minimos


# precio minimo patron de busqueda
def precio_minimo_patron(resultados):
    def actualizar_precio_minimo(precios_minimos, resultado):
        patron_busqueda = resultado.getPatronBusqueda()
        precio_pesos = int(resultado.getPrecio())
        precio_uf = float(resultado.getPrecioUf())

        # Crea una nueva entrada en el diccionario si no existe
        precios_minimos[patron_busqueda] = precios_minimos.get(
            patron_busqueda, {"precio_pesos": precio_pesos, "precio_uf": precio_uf}
        )

        # Actualiza el precio mínimo si el precio actual es menor
        precios_minimos[patron_busqueda]["precio_pesos"] = min(
            precios_minimos[patron_busqueda]["precio_pesos"], precio_pesos
        )

        precios_minimos[patron_busqueda]["precio_uf"] = min(
            precios_minimos[patron_busqueda]["precio_uf"], precio_uf
        )

        return precios_minimos

    precios_minimos_patron = reduce(actualizar_precio_minimo, resultados, {})

    return precios_minimos_patron


# Precio Maximo por Patron de Busqueda y Multitienda
def obtener_precios_maximos_patron_multi(resultados):
    def actualizar_precio_maximo(precios_maximos, resultado):
        patron_busqueda = resultado.getPatronBusqueda()
        multitienda = resultado.getMultiTienda()
        precio_pesos = int(resultado.getPrecio())
        precio_uf = float(resultado.getPrecioUf())

        # Crea una nueva entrada en el diccionario si no existe
        precios_maximos[(patron_busqueda, multitienda)] = precios_maximos.get(
            (patron_busqueda, multitienda),
            {
                "precio_pesos": int(resultado.getPrecio()),
                "precio_uf": float(resultado.getPrecioUf()),
            },
        )

        # Actualiza el precio maximo si el precio actual es mayor
        precios_maximos[(patron_busqueda, multitienda)]["precio_pesos"] = max(
            precios_maximos[(patron_busqueda, multitienda)]["precio_pesos"],
            precio_pesos,
        )

        precios_maximos[(patron_busqueda, multitienda)]["precio_uf"] = max(
            precios_maximos[(patron_busqueda, multitienda)]["precio_uf"], precio_uf
        )

        return precios_maximos

    precios_maximos = reduce(actualizar_precio_maximo, resultados, {})

    return precios_maximos


# Precio Maximo por Patron de Busqueda
def precio_maximo_patron(resultados):
    def actualizar_precio_maximo(precios_maximos, resultado):
        patron_busqueda = resultado.getPatronBusqueda()
        precio_pesos = int(resultado.getPrecio())
        precio_uf = float(resultado.getPrecioUf())

        # Crea una nueva entrada en el diccionario si no existe
        precios_maximos[patron_busqueda] = precios_maximos.get(
            patron_busqueda, {"precio_pesos": precio_pesos, "precio_uf": precio_uf}
        )

        # Actualiza el precio maximo si el precio actual es mayor
        precios_maximos[patron_busqueda]["precio_pesos"] = max(
            precios_maximos[patron_busqueda]["precio_pesos"], precio_pesos
        )

        precios_maximos[patron_busqueda]["precio_uf"] = max(
            precios_maximos[patron_busqueda]["precio_uf"], precio_uf
        )

        return precios_maximos

    precios_maximos_patron = reduce(actualizar_precio_maximo, resultados, {})

    return precios_maximos_patron


# Precio promedio por Patron de Busqueda y Multitienda
def calcular_promedio_precios_patron_multi(resultados):
    def actualizar_precios(promedios_precios, resultado):
        patron_busqueda = resultado.getPatronBusqueda()
        multitienda = resultado.getMultiTienda()
        precio_pesos = int(resultado.getPrecio())
        precio_uf = float(resultado.getPrecioUf())

        suma_pesos, suma_uf, cantidad = promedios_precios.get(
            (patron_busqueda, multitienda), (0, 0, 0)
        )
        suma_pesos += precio_pesos
        suma_uf += precio_uf
        cantidad += 1
        promedios_precios[(patron_busqueda, multitienda)] = (
            int(suma_pesos),
            round(suma_uf, 2),
            cantidad,
        )

        return promedios_precios

    promedios_precios = reduce(actualizar_precios, resultados, {})

    promedios_finales = {}

    def calcular_promedio(combinacion):
        patron_busqueda, multitienda = combinacion
        suma_pesos, suma_uf, cantidad = promedios_precios[
            (patron_busqueda, multitienda)
        ]
        promedio_pesos = int(suma_pesos / cantidad)
        promedio_uf = round(suma_uf / cantidad, 2)
        return (combinacion, (promedio_pesos, promedio_uf))

    promedios_finales = dict(map(calcular_promedio, promedios_precios))

    return promedios_finales


# precio promedio patron de busqueda
def calcular_promedio_precios_patron(resultados):
    def actualizar_precios(promedios_precios, resultado):
        patron_busqueda = resultado.getPatronBusqueda()
        precio_pesos = int(resultado.getPrecio())
        precio_uf = float(resultado.getPrecioUf())

        suma_pesos, suma_uf, cantidad = promedios_precios.get(
            patron_busqueda, (0, 0, 0)
        )
        suma_pesos += precio_pesos
        suma_uf += precio_uf
        cantidad += 1
        promedios_precios[patron_busqueda] = (
            int(suma_pesos),
            round(suma_uf, 2),
            cantidad,
        )

        return promedios_precios

    promedios_precios = reduce(actualizar_precios, resultados, {})

    def calcular_promedio(combinacion):
        patron_busqueda = combinacion
        suma_pesos, suma_uf, cantidad = promedios_precios[patron_busqueda]
        promedio_pesos = int(suma_pesos / cantidad)
        promedio_uf = round(suma_uf / cantidad, 2)
        return (combinacion, (promedio_pesos, promedio_uf))

    promedios_finales_patron = dict(map(calcular_promedio, promedios_precios))

    return promedios_finales_patron


def obtener_precio_minimo_paquete(resultados):
    if not resultados:
        return None

    precio_minimo = min(resultados, key=lambda x: int(x.getPrecio()))
    patron_busqueda = precio_minimo.getPatronBusqueda()
    multitienda = precio_minimo.getMultiTienda()
    descripcion_producto = precio_minimo.getDescripcion()
    precio_pesos = precio_minimo.getPrecio()
    precio_uf = precio_minimo.getPrecioUf()

    return {
        "Patrón de búsqueda": patron_busqueda,
        "Multitienda": multitienda,
        "Descripción del producto": descripcion_producto,
        "Precio mínimo en pesos": precio_pesos,
        "Precio mínimo en UF": precio_uf,
    }


def obtener_precio_maximo_paquete(resultados):
    if not resultados:
        return None

    precio_maximo = max(resultados, key=lambda x: int(x.getPrecio()))
    patron_busqueda = precio_maximo.getPatronBusqueda()
    multitienda = precio_maximo.getMultiTienda()
    descripcion_producto = precio_maximo.getDescripcion()
    precio_pesos = precio_maximo.getPrecio()
    precio_uf = precio_maximo.getPrecioUf()

    return {
        "Patrón de búsqueda": patron_busqueda,
        "Multitienda": multitienda,
        "Descripción del producto": descripcion_producto,
        "Precio mínimo en pesos": precio_pesos,
        "Precio mínimo en UF": precio_uf,
    }
