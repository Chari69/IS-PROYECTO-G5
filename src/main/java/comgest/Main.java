package comgest;

import javax.swing.*;

import comgest.controller.ControladorView;
import comgest.view.MainValidacion;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("pos")) {
            MainValidacion.main(args);
        } else {
            ControladorView.main(args);
        }
    }
}