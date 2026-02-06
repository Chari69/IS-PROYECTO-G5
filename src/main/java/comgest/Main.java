package comgest;

import comgest.model.DB_usuarios.ControladorDB;

public class Main {
    public static void main(String[] args) {
        ControladorDB controladorDB = new ControladorDB(); //controlador de la base de datos de usuarios
        controladorDB.RegistrarUsuario("julian alvarez", "pollitacock", "c", 0);

        var inicio = controladorDB.InicioDeSesion("c","pollitacock");
        if(inicio){
            System.out.println("bienvenido");
        }else{
            System.out.println("usuario o contraseña malo");
        }
    }
}