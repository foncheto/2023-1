class Producto:
    def __init__(self):
        self.patronBusqueda = ""
        self.multiTienda = ""
        self.descripcion = ""
        self.precio = ""
        self.precioUf = ""

    def getPatronBusqueda(self):
        return self.patronBusqueda

    def setPatronBusqueda(self, patronBusqueda):
        self.patronBusqueda = patronBusqueda

    def getMultiTienda(self):
        return self.multiTienda

    def setMultiTienda(self, multiTienda):
        self.multiTienda = multiTienda

    def getDescripcion(self):
        return self.descripcion

    def setDescripcion(self, descripcion):
        self.descripcion = descripcion

    def getPrecio(self):
        return self.precio

    def setPrecio(self, precio):
        self.precio = precio

    def getPrecioUf(self):
        return self.precioUf

    def setPrecioUf(self, precioUf):
        self.precioUf = precioUf
