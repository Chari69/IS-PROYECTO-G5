package comgest;

import comgest.model.ControladorDB;
import comgest.model.ControladorMenuDB;
import comgest.model.MenuItem;
import java.util.List;

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

        ControladorMenuDB menuDB = new ControladorMenuDB();
        //menuDB.agregarMenuItem("Sopa du macaco", "SOPA DO MONO", "/img/imagen.png", 30.4);
        //menuDB.agregarMenuItem("Bolas do mono", "delisia", "/img/imagen.png", 666.69);
        //menuDB.agregarMenuItem("TGK", "efn", "only entendidos.png", 777.77);

        // imprimir menu item
        List<MenuItem> items = menuDB.getMenuItems();

        for (MenuItem item : items) {
            System.out.println(item.getName());
            System.out.println(item.getDescripcion());
            System.out.println(item.getPrecio());
            System.out.println("-----------");
        }
    }
}