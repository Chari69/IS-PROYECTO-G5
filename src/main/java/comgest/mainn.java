package comgest;

import comgest.controller.RegisterController;
import comgest.view.*;

import javax.swing.*;
import java.awt.*;

public class mainn {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            RegisterGUI view = new RegisterGUI();
            RegisterController controller = new RegisterController(view);
            view.asignarControlador(controller);
            view.mostrar();
        });
    }
}
