import time
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from BancoOP import ValorUFScraper
from Producto import Producto

# Llamamos la funcion que nos entrega el valor de la UF a teaves del scraping de la pagina del banco central
scraper = ValorUFScraper()
valorUF = scraper.valor_uf()
scraper.close_driver()

# Constantes
B_VERBOSE_DEBUG = True  # Para debug
B_VERBOSE_RESULT = True  # Para mostrar resultados de capturas de datos

# Patrones de búsqueda: Lee el archivo y guarda los nombres en una lista
with open("patrones_busqueda.txt", "r") as file:
    L_FIND = file.read().splitlines()


# Hacer una pausa en segundos para saltarse sleep de Python (le causa problemas al web driver)
def mySleep(nTimeOut):
    nTimeInit = time.time()
    nTimeDifference = time.time() - nTimeInit
    while nTimeDifference < nTimeOut:
        nTimeDifference = time.time() - nTimeInit


# Hacer una pausa MÁXIMA en segundos o hasta que aparezca sXpath
def mySleepUntilObject(nTimeOut, driver, sXpath):
    nTimeInit = time.time()
    nTimeDifference = time.time() - nTimeInit
    bContinuar = True
    while (nTimeDifference < nTimeOut) and (bContinuar):
        nTimeDifference = time.time() - nTimeInit
        try:
            contentData = driver.find_element(By.XPATH, sXpath)
            bContinuar = False
        except:
            pass


# Hacer click con espera MÁXIMA. Devuelve True si hizo click
def clickWithWait(nTimeOut, driver, sXpath):
    nTimeInit = time.time()
    nTimeDifference = time.time() - nTimeInit
    bContinuar = True
    bClickDone = False
    while (nTimeDifference < nTimeOut) and (bContinuar):
        nTimeDifference = time.time() - nTimeInit
        try:
            btnToBeClick = driver.find_element(By.XPATH, sXpath)
            btnToBeClick.click()
            bContinuar = (
                False  # Sino se cae la línea anterior es porque ya hizo el click
            )
            bClickDone = True
        except:
            pass
    return bClickDone


# Lista de resultados a rellenar con objetos Producto y luego exportar al main.py
listResult = []

# Configuración del Driver Selenium para Chrome
chrome_options = Options()
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")
chrome_options.add_argument("--ignore-ssl-errors")
chrome_options.add_argument("--ignore-certificate-errors")
driver = webdriver.Chrome(options=chrome_options)

# Recorremos lista de patrones de búsqueda
for S_FIND in L_FIND:
    if B_VERBOSE_DEBUG:
        print("=" * len("Patrón de búsqueda: {}".format(S_FIND)))
        print("Patrón de búsqueda: {}".format(S_FIND))
        print("=" * len("Patrón de búsqueda: {}".format(S_FIND)))

    # Ingresamos patrón de búsqueda
    driver.get("https://www.falabella.com/falabella-cl")
    mySleep(2)
    inputText = driver.find_element(By.XPATH, '//*[@id="testId-SearchBar-Input"]')
    inputText.send_keys(S_FIND)
    inputText.send_keys(Keys.ENTER)
    mySleep(1)

    # Verificar si hay datos
    bOkExistData = False
    try:
        sXpath = '//*[@id="testId-searchResults-products"]'
        btnPage1 = driver.find_element(By.XPATH, sXpath)
        bOkExistData = True
    except:
        if B_VERBOSE_DEBUG:
            print("No hay datos")
        pass

    # Iterar en todas las páginas
    nPage = 1
    while bOkExistData:
        if B_VERBOSE_DEBUG:
            print("{}: Página {}".format(S_FIND, nPage))

        # Capturar datos desde el contenedor
        try:
            sXpath = '//*[@id="testId-searchResults-products"]'
            mySleepUntilObject(20, driver, sXpath)
            mySleep(2)

            # Capturamos HTML del contenedor de productos tecnológicos
            sXpath = '//*[@id="testId-searchResults-products"]'
            contentData = driver.find_element(By.XPATH, sXpath)
            htmlData = contentData.get_attribute("innerHTML")
            lxmlData = BeautifulSoup(htmlData, "lxml")

            # Verificamos tipo de contenedor
            # Se busca en diferentes clases de contenedores
            nContentType = 0
            sNames = lxmlData.find_all(
                "div",
                class_="jsx-1833870204 jsx-3831830274 pod-details pod-details-4_GRID has-stickers",
            )
            if len(sNames) > 0:
                nContentType = 1
            else:
                sNames = lxmlData.find_all(
                    "b",
                    class_="jsx-1576191951 title2 primary jsx-2889528833 bold pod-subTitle subTitle-rebrand",
                )
                if len(sNames) > 0:
                    nContentType = 2
                else:
                    if B_VERBOSE_DEBUG:
                        print("Contenedor no reconocido")
            if B_VERBOSE_DEBUG:
                print("Tipo contenedor: {}".format(nContentType))

            # Capturamos datos del contenedor
            if nContentType == 1:
                sNames = lxmlData.find_all(
                    "div",
                    class_="jsx-1833870204 jsx-3831830274 pod-details pod-details-4_GRID has-stickers",
                )
                sPrices = lxmlData.find_all(
                    "a",
                    class_="jsx-1833870204 jsx-3831830274 pod-summary pod-link pod-summary-4_GRID",
                )
            else:
                sNames = lxmlData.find_all(
                    "b",
                    class_="//div[contains(@class, 'pod-details')]",
                )
                sPrices = lxmlData.find_all(
                    "div", class_="jsx-2112733514 prices prices-4_GRID"
                )

            # Recorremos el contenedor para llenar lista de objetos Producto
            for i in range(len(sNames)):
                # Capturamos según tipo de contenedor y creamos objeto Producto
                nuevoProducto = Producto()
                if nContentType == 1:
                    nPrecio = (
                        sPrices[i]
                        .div.ol.li.div.span.string.replace("$", "")
                        .replace(" ", "")
                        .replace(".", "")
                    )
                    # Se pobla objeto Producto con los atributos y sus setters
                    nuevoProducto.setPatronBusqueda(S_FIND)
                    nuevoProducto.setMultiTienda("Falabella")
                    nuevoProducto.setDescripcion(sNames[i].a.span.b.string)
                    nuevoProducto.setPrecio(nPrecio)
                    nuevoProducto.setPrecioUf(round(float(nPrecio) / valorUF, 2))
                    listResult.append(nuevoProducto)
                else:
                    nPrecio = (
                        sPrices[i]
                        .ol.li.div.span.string.replace("$", "")
                        .replace(" ", "")
                        .replace(".", "")
                    )
                    # Se pobla objeto Producto con los atributos y sus setters
                    nuevoProducto.setPatronBusqueda(S_FIND)
                    nuevoProducto.setMultiTienda("Falabella")
                    nuevoProducto.setDescripcion(sNames[i].string)
                    nuevoProducto.setPrecio(nPrecio)
                    nuevoProducto.setPrecioUf(round(float(nPrecio) / valorUF, 2))
                    listResult.append(nuevoProducto)

                # Imprimimos
                if B_VERBOSE_DEBUG:
                    print(listResult[len(listResult) - 1])

            # Dar click a la siguiente página
            try:
                # Obtenemos botón próxima página, sino se caerá y será capturado en except
                sXpath = '//*[@id="testId-pagination-bottom-arrow-right"]/i'
                contentData = driver.find_element(By.XPATH, sXpath)

                # Intentamos click por espera para próxima página
                driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
                bOkExistData = clickWithWait(2, driver, sXpath)

                # Si retorna en False es porque existe el botón siguiente pero no quedó clickleable
                if not (bOkExistData):
                    if B_VERBOSE_DEBUG:
                        print("Reintento con scroll fin + F5")

                    # Hacemos scroll hasta el final y luego F5 para refrescar
                    driver.execute_script(
                        "window.scrollTo(0, document.body.scrollHeight);"
                    )
                    driver.get(driver.current_url)

                    # Intentamos NUEVAMENTE click por espera para próxima página
                    bOkExistData = clickWithWait(2, driver, sXpath)
                    if not (bOkExistData):
                        if B_VERBOSE_DEBUG:
                            print("No se logró hacer click a la siguiente página")
            except:
                if B_VERBOSE_DEBUG:
                    print("No hay más páginas de información")
                bOkExistData = False
        except:
            if B_VERBOSE_DEBUG:
                print("Caída al capturar contenedor")
            bOkExistData = False

        nPage = nPage + 1

# Cierre del driver
driver.close()
driver.quit()
# Proceso finalizado
if B_VERBOSE_DEBUG:
    print("Proceso finalizado")

    """ Se genero este codigo de WebScrapping usando paradigma procedural, es decir con el uso de atribuciones imperativas con secuencias especificas, y en este caso se realizo para
la pagina web de la multitienda Falabella, este codigo fue una adaptacion del codigo de las clases de nuestro ramo Lenguajes y Paradigmas de programación con los diversos ejemplos
de Webscapping que se nos dio, asi pudiendolo usar para diversas situaciones y aplicarlo para distintas busquedas, como en este caso para falabella.
                    
                    """
