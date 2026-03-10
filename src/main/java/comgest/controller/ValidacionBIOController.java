
package comgest.controller;
import comgest.utils.*;
import comgest.view.MainValidacion;
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

public class ValidacionBIOController implements ActionListener {
    public static final String ACTION_CONFIRMAR_RESERVA = "CONFIRMAR_RESERVA";
    public static final String ACTION_CERRAR = "CERRAR";
    public static final String ACTION_SELECCIONAR_FOTO = "SELECCIONAR_FOTO";
    public static final String ACTION_FINALIZAR = "FINALIZAR"; // Este es el botón "VALIDAR" de la subida

    private final MainValidacion view;
    private final UserModel userModel;
    private File selectedFile;

    public ValidacionBIOController(MainValidacion view) {
        this(view, new UserModel());
    }

    public ValidacionBIOController(MainValidacion view, UserModel userModel) {
        if (view == null || userModel == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser null");
        }
        this.view = view;
        this.userModel = userModel;
        this.view.asignarControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case ACTION_SELECCIONAR_FOTO -> seleccionarFoto();
                case ACTION_FINALIZAR -> validarImagenYContinuar(); 
                case ACTION_CONFIRMAR_RESERVA -> procesarPagoYConfirmar(); 
                case ACTION_CERRAR -> view.dispose();
            }
        } catch (Exception ex) {
            view.showMessage("Error: " + ex.getMessage());
        }
    }

   
    //  Valida que la imagen coincida y cambia a la vista de detalles.
    
    private void validarImagenYContinuar() {
        if (selectedFile == null) {
            view.showMessage("Por favor seleccione una imagen primero.");
            return;
        }

        UserSession session = UserSession.getInstance();
        if (session == null || !session.isActive()) {
            view.showMessage("No hay una sesión activa.");
            return;
        }

        String rutaEsperada = session.getPfpPath();
        
        // Comparación biométrica/imagen
        boolean esValido = Utils.CompararImagenes(selectedFile.getAbsolutePath(), rutaEsperada);
        
        
        if (esValido) {
            // Si es válida, pasamos a la siguiente pestaña de la vista
            view.mostrarSeccionDetalles();
        } else {
            view.showMessage("La validación de identidad ha fallado. Intente de nuevo.");
        }
    }

  
    //  Realiza el cobro y finaliza la transacción.
    
    private void procesarPagoYConfirmar() {
        UserSession session = UserSession.getInstance();
        Usuario currentUser = session.getUsuario();
        double precio = view.getPrecio();

        // Verificación de saldo
        if (currentUser.getSaldo() < precio) {
            view.showMessage("Saldo insuficiente en el monedero.");
            return;
        }

        // Lógica de descuento
        currentUser.subSaldo((float) precio);
        boolean success = userModel.actualizarUsuario(currentUser);

        if (success) {
            view.showMessage("Pago procesado. ¡Reserva confirmada!");
            view.dispose();
        } else {
            currentUser.addSaldo((float) precio); // Revertir
            view.showMessage("Error crítico al actualizar el saldo.");
        }
    }

    private void seleccionarFoto() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Imagenes (JPG, PNG)", "jpg", "png", "jpeg"));

        if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image newImg = originalIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            view.setPreviewImage(new ImageIcon(newImg));
        }
    }
}