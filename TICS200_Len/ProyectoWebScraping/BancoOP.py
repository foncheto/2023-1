from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import csv


class ValorUFScraper:
    def __init__(self):
        self.driver = self._initialize_driver()

    def _initialize_driver(self):
        chrome_options = Options()
        chrome_options.add_argument("--no-sandbox")
        chrome_options.add_argument("--disable-dev-shm-usage")
        return webdriver.Chrome(options=chrome_options)

    def scrape_valor_uf(self):
        self.driver.get("https://www.bcentral.cl")

        weUF = self.driver.find_element(
            By.XPATH,
            "/html/body/div[1]/section/div/div[2]/div/div/div[1]/section/div/div[2]/div/div/div/div/div[1]/div/div/div[1]/div/p[2]",
        )
        fecha_uf = self.driver.find_element(
            By.XPATH,
            "/html/body/div[1]/section/div/div[2]/div/div/div[1]/section/div/div[2]/div/div/div/div/div[1]/p",
        )
        sUF = weUF.text
        fecha_uf = fecha_uf.text
        print("String nativo:    {}".format(sUF))
        sUF = sUF.replace("$", "").replace(".", "").replace(",", ".")
        print("String preparado: {}".format(sUF))
        nUF = float(sUF)
        print("Float:            {}".format(nUF))
        print(fecha_uf)
        self.save_to_csv(nUF, fecha_uf)
        return nUF

    # csv
    def save_to_csv(self, valor, fecha):
        with open("parametros.csv", "w", newline="") as archivo_csv:
            writer = csv.writer(archivo_csv)
            writer.writerow(["Fecha", "Valor Uf"])
            writer.writerow([fecha, valor])

    def valor_uf(self):
        val_uf = self.scrape_valor_uf()
        return val_uf

    def close_driver(self):
        self.driver.quit()
