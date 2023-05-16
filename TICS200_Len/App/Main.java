package App;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Cargar los datos del archivo CSV en una lista de clientes
        Registro registro = new Registro();
        List<Cliente> registro_csv = registro.leerRegistro();

        if (registro_csv == null) {
            System.err.println("No se pudo cargar el registro");
            return;
        }
        Menu menu = new Menu();
        menu.runMenu(registro_csv);
    }
}
