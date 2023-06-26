import time
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from BancoOP import ValorUFScraper
from Producto import Producto

# Constantes
B_VERBOSE_DEBUG = True  # Para debug
B_VERBOSE_RESULT = True  # Para mostrar resultados de capturas de datos

# Patrones de búsqueda al leer el archivo .txt
with open("patrones_busqueda.txt", "r") as file:
    L_FIND = file.read().splitlines()

# Llamamos la funcion que nos entrega el valor de la UF a teaves del scraping de la pagina del banco central
scraper = ValorUFScraper()
valorUF = scraper.valor_uf()
scraper.close_driver()


# Hacer una pausa en segundos para que la funcion pare por unos segundos, esto nos ayudara para cuando se demore en cargar la pagina web
def mySleep(nTimeOut):
    nTimeInit = time.time()
    nTimeDifference = time.time() - nTimeInit
    while nTimeDifference < nTimeOut:
        nTimeDifference = time.time() - nTimeInit


# Hacer una pausa hasta una accion u ocurrencia especifica
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
            bContinuar = False
            bClickDone = True
        except:
            pass
    return bClickDone


# lista a rellenar y luego exportar al main
listResult = []

# Configuración del Driver Selenium para Chrome
chrome_options = Options()
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")
driver = webdriver.Chrome(options=chrome_options)

# Recorremos lista de patrones de búsqueda
for S_FIND in L_FIND:
    if B_VERBOSE_DEBUG:
        print("=" * len("Patrón de búsqueda: {}".format(S_FIND)))
        print("Patrón de búsqueda: {}".format(S_FIND))
        print("=" * len("Patrón de búsqueda: {}".format(S_FIND)))

    # Ingresamos patrón de búsqueda
    driver.get("https://www.ripley.com")
    mySleep(2)
    inputText = driver.find_element(By.XPATH, '//input[@type="search"]')
    inputText.send_keys(S_FIND)
    inputText.send_keys(Keys.ENTER)
    mySleep(1)

    # Verificar si hay datos
    bOkExistData = False
    try:
        sXpath = '//*[@id="catalog-page"]/div/div[2]/div[3]/section/div/div'
        btnPage1 = driver.find_element(By.XPATH, sXpath)
        bOkExistData = True
        print("Hay datos")
    except:
        if B_VERBOSE_DEBUG:
            print("No hay datos")
        pass

    cookiePath = '//button[text()="Aceptar"]'
    clickWithWait(3, driver, cookiePath)

    # Iterar en todas las páginas si es que existen
    nPage = 1
    while bOkExistData:
        if B_VERBOSE_DEBUG:
            print("{}: Página {}".format(S_FIND, nPage))

        # Capturar datos desde el contenedor
        try:
            sXpath = '//*[@id="catalog-page"]/div/div[2]/div[3]/section/div/div'
            mySleepUntilObject(20, driver, sXpath)
            mySleep(2)

            # Capturamos HTML del contenedor de productos tecnológicos
            contentData = driver.find_element(By.XPATH, sXpath)
            htmlData = contentData.get_attribute("innerHTML")
            lxmlData = BeautifulSoup(htmlData, "lxml")

            # Verificamos tipo de contenedor
            nContentType = 0
            sNames = lxmlData.find_all(
                "div",
                {"class": "catalog-product-details__name"},
            )
            if len(sNames) > 0:
                nContentType = 1
            else:
                if B_VERBOSE_DEBUG:
                    print("Contenedor no reconocido")
            if B_VERBOSE_DEBUG:
                print("Tipo contenedor: {}".format(nContentType))
            # Capturamos datos del contenedor
            if nContentType == 1:
                sPrices = lxmlData.find_all(
                    "li",
                    {"class": "catalog-prices__offer-price"},
                )

            # Recorremos el contenedor para llenar lista con un nuevo objeto Producto
            for i in range(len(sNames)):
                # Capturamos según tipo de contenedor
                if nContentType == 1:
                    nuevoProducto = Producto()
                    nPrecio = sPrices[i].text.replace("$", "").replace(".", "")
                    nuevoProducto.setPrecio(int(nPrecio))
                    nuevoProducto.setPrecioUf(round((float(nPrecio) / valorUF), 2))
                    nuevoProducto.setDescripcion(sNames[i].text)
                    nuevoProducto.setPatronBusqueda(S_FIND)
                    nuevoProducto.setMultiTienda("Ripley")
                    listResult.append(nuevoProducto)
                    print(listResult[-1].precio, listResult[-1].descripcion)
            print("done")
            try:
                # Obtenemos botón próxima página, sino se caerá y será capturado en except
                sXpath = '//*[@id="catalog-page"]/div/div[2]/div[4]/nav/ul/li[5]/a'

                button = driver.find_element(
                    By.XPATH,
                    '//*[@id="catalog-page"]/div/div[2]/div[4]/nav/ul/li[5]/a',
                )
                if "is-disabled" in button.get_attribute("class"):
                    bOkExistData = False

                else:
                    driver.execute_script(
                        "document.querySelector('div.footer').remove();"
                    )

                    scroll_height = driver.execute_script(
                        "return document.body.scrollHeight"
                    )

                    # Intentamos click por espera para próxima página
                    driver.execute_script(
                        "window.scrollTo(0, arguments[0]);", scroll_height
                    )

                    bOkExistData = clickWithWait(3, driver, sXpath)

                # Si retorna en False es porque existe el botón siguiente pero no quedó clickleable
            except:
                if B_VERBOSE_DEBUG:
                    print("No hay más páginas de información")
                    # print('ClassError: {} - NameError: {}'.format(sys.exc_info()[0], sys.exc_info()[1]))
                bOkExistData = False
        except:
            if B_VERBOSE_DEBUG:
                print("Caída al capturar contenedor")
                # print('ClassError: {} - NameError: {}'.format(sys.exc_info()[0], sys.exc_info()[1]))
            bOkExistData = False

        nPage = nPage + 1

# Cierre del driver
driver.close()
driver.quit()

# Proceso finalizado
if B_VERBOSE_DEBUG:
    print("Proceso finalizado")

""" Se genero este codigo de WebScrapping usando paradigma procedural, es decir con el uso de atribuciones imperativas con secuencias especificas, y en este caso se realizo para
la pagina web de la multitienda Ripley, este codigo fue una adaptacion del codigo de las clases de nuestro ramo Lenguajes y Paradigmas de programación con los diversos ejemplos
de Webscapping que se nos dio, asi pudiendolo usar para diversas situaciones, como en este caso para ripley.
                    
                    """
