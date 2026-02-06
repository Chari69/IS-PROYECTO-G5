package comgest;

import comgest.model.ControladorDB;

public class Main {
    public static void main(String[] args) {
        ControladorDB controladorDB = new ControladorDB(); //controlador de la base de datos de usuarios
        controladorDB.RegistrarUsuario("leonardo", "Palmpilot2?", "lespinolafranco@gmail.com");
        controladorDB.InicioDeSesion("lespinolafranco@gmail.com", "Palmpilot2?");
    }
}