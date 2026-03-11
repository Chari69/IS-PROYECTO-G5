package comgest;

import comgest.controller.ControladorView;
import comgest.view.MainValidacion;

public class Main {
    // CAMBIAR ACA PARA ENTRAR EN EL MODO POS
    private static boolean POS = false;

    public static void main(String[] args) {
        if ((args.length > 0 && args[0].equalsIgnoreCase("pos")) || POS) {
            MainValidacion.main(args);
        } else {
            ControladorView.main(args);
        }
    }
}