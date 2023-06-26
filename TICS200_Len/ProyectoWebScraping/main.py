import ripley
import falabella

lista_ripley = ripley.listResult
lista_falabella = falabella.listResult
listaProductos = lista_ripley + lista_falabella

from Menu import Menu

menu = Menu(listaProductos=listaProductos)
menu.ejecutar()
