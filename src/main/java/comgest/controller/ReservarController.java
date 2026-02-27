package comgest.controller;

import comgest.utils.*;
import comgest.view.ReservarGUI;
import comgest.model.UserSession;
import comgest.model.UserModel;
import comgest.model.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JOptionPane;

public class ReservarController implements ActionListener {
    public static final String ACTION_CONFIRMAR_RESERVA = "CONFIRMAR_RESERVA";
    public static final String ACTION_CERRAR = "CERRAR";
    public static final String ACTION_SELECCIONAR_FOTO = "SELECCIONAR_FOTO";
    public static final String ACTION_FINALIZAR = "FINALIZAR";

    private final ReservarGUI view;
    private final UserModel userModel;
    private File selectedFile;

    public ReservarController(ReservarGUI view) {
        this(view, new UserModel());
    }

    public ReservarController(ReservarGUI view, UserModel userModel) {
        if (view == null) {
            throw new IllegalArgumentException("view no puede ser null");
        }
        if (userModel == null) {
            throw new IllegalArgumentException("userModel no puede ser null");
        }
        this.view = view;
        this.userModel = userModel;
        this.view.asignarControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (ACTION_CONFIRMAR_RESERVA.equals(command)) {
                confirmarReserva();
            } else if (ACTION_CERRAR.equals(command)) {
                cerrar();
            } else if (ACTION_SELECCIONAR_FOTO.equals(command)) {
                seleccionarFoto();
            } else if (ACTION_FINALIZAR.equals(command)) {
                finalizar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Ocurrio un error inesperado: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarReserva() {
        view.mostrarSeccionSubida();
    }

    private void cerrar() {
        view.dispose();
    }

    private void seleccionarFoto() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes (JPG, PNG)", "jpg", "png", "jpeg");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(view);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image img = originalIcon.getImage();
            Image newImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            view.setPreviewImage(new ImageIcon(newImg));
        }
    }

    private void finalizar() {
        if (selectedFile == null) {
            view.showMessage("Por favor seleccione una imagen primero.");
            return;
        }

        UserSession session = UserSession.getInstance();
        if (session == null || !session.isActive()) {
            view.showMessage("No hay una sesion activa.");
            return;
        }

        String rutaEsperada = session.getPfpPath();

        boolean esValido = Utils.CompararImagenes(selectedFile.getAbsolutePath(), rutaEsperada);
        if (esValido) {
            Usuario currentUser = session.getUsuario();
            double precio = view.getPrecio();

            if (currentUser.getSaldo() < precio) {
                view.showMessage("Saldo insuficiente para realizar la reserva.");
                return;
            }
            currentUser.subSaldo((float) precio);
            boolean success = userModel.actualizarUsuario(currentUser);

            if (success) {
                view.showMessage("Reserva validada y pagada correctamente.");
                view.dispose();
            } else {
                // Revertir cambio en caso de error
                currentUser.addSaldo((float) precio);
                view.showMessage("Error al procesar el pago de la reserva.");
            }
        } else {
            view.showMessage("El comprobante no es valido.");
        }
    }
}
