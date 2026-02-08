/*
 * /package comgest;
 * 
 * import comgest.model.ControladorDB;
 * import comgest.model.ControladorMenuDB;
 * import comgest.model.MenuItem;
 * import java.util.List;
 * 
 * import javax.swing.*;
 * import comgest.view.components.Panel_Inferior_PUI;
 * import comgest.view.components.panel_menu_UI;
 * import java.awt.*;
 * 
 * 
 * public class Main {
 * public static void main(String[] args) {
 * DbControlador();
 * IGUControlador();
 * }
 * 
 * public static void IGUControlador(){
 * JFrame vp = new JFrame();
 * 
 * vp.setSize(800, 600);
 * vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * 
 * Panel_Inferior_PUI panel = new Panel_Inferior_PUI();
 * vp.add(panel.getPanel_inf(), BorderLayout.SOUTH);
 * panel_menu_UI panel_menu = new panel_menu_UI();
 * vp.add(panel_menu, BorderLayout.CENTER);
 * vp.revalidate();
 * vp.repaint();
 * vp.setVisible(true);
 * vp.setResizable(false);
 * }
 * 
 * public static void DbControlador(){
 * ControladorDB controladorDB = new ControladorDB(); // controlador de la base
 * de datos de usuarios
 * controladorDB.RegistrarUsuario("julian alvarez", "pollitacock", "c",
 * "12345678", 0);
 * 
 * var inicio = controladorDB.InicioDeSesion("c", "pollitacock");
 * if (inicio) {
 * System.out.println("bienvenido");
 * } else {
 * System.out.println("usuario o contraseña malo");
 * }
 * 
 * ControladorMenuDB menuDB = new ControladorMenuDB();
 * // menuDB.agregarMenuItem("Sopa du macaco", "SOPA DO MONO",
 * "/img/imagen.png",
 * // 30.4);
 * // menuDB.agregarMenuItem("Bolas do mono", "delisia", "/img/imagen.png",
 * // 666.69);
 * // menuDB.agregarMenuItem("TGK", "efn", "only entendidos.png", 777.77);
 * 
 * // imprimir menu item
 * List<MenuItem> items = menuDB.getMenuItems();
 * 
 * for (MenuItem item : items) {
 * System.out.println(item.getName());
 * System.out.println(item.getDescripcion());
 * System.out.println(item.getPrecio());
 * System.out.println("-----------");
 * }
 * }
 * }/
 */