package comgest.utils;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import comgest.controller.ControladorView;
import comgest.model.UserSession;

import java.awt.image.BufferedImage;

public class Utils {

    public static boolean isAdminRole(String role) {
        if (role == null) {
            return false;
        }
        String normalized = role.trim().toLowerCase();
        return normalized.equals("administrador");
    }

    public static String normalizar(String value) {
        return value == null ? "" : value.trim();
    }

    public static boolean CompararImagenes(String ruta1, String ruta2) {
        try {
            BufferedImage img1 = ImageIO.read(new File(ruta1));
            BufferedImage img2 = ImageIO.read(new File(ruta2));

            // Primero verificamos que tengan las mismas dimensiones
            if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
                return false;
            }

            // Comparamos pixel por pixel
            for (int y = 0; y < img1.getHeight(); y++) {
                for (int x = 0; x < img1.getWidth(); x++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                        return false; // Diferencia encontrada
                    }
                }
            }
            return true; // Son identicas
        } catch (IOException e) {
            System.err.println("Error al leer las imagenes: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica si hay sesión activa. Si no, muestra mensaje y redirige al login.
     * 
     * @return UserSession activa o null si no hay sesión.
     */
    public static UserSession getSessionOrRedirect() {
        UserSession session = UserSession.getInstance();
        if (session == null || !session.isActive()) {
            ControladorView.mostrarLogin();
            return null;
        }
        return session;
    }

    /**
     * Parsea una hora en formato "7:00 AM" o "10:00 PM" a LocalTime.
     * Usa Locale.US para compatibilidad con AM/PM en cualquier locale del sistema.
     */
    public static LocalTime parsearHora(String texto) {
        if (texto == null || texto.trim().isEmpty())
            return null;
        try {
            texto = texto.trim().toUpperCase();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.US);
            return LocalTime.parse(texto, formatter);
        } catch (Exception e) {
            try {
                DateTimeFormatter formatter24 = DateTimeFormatter.ofPattern("H:mm");
                return LocalTime.parse(texto.trim(), formatter24);
            } catch (Exception e2) {
                System.out.println("No se pudo parsear la hora: " + texto);
                return null;
            }
        }
    }

    /**
     * Verifica si la hora dada está dentro del rango de horario.
     * Formato esperado: "7:00 AM - 10:00 AM"
     */
    public static boolean estaEnHorario(String horario, LocalTime ahora) {
        if (horario == null || horario.trim().isEmpty()) {
            return true;
        }
        try {
            String[] partes = horario.split("-");
            if (partes.length != 2)
                return true;

            LocalTime inicio = parsearHora(partes[0].trim());
            LocalTime fin = parsearHora(partes[1].trim());

            if (inicio == null || fin == null)
                return true;

            return !ahora.isBefore(inicio) && !ahora.isAfter(fin);
        } catch (Exception e) {
            System.out.println("Error parseando horario: " + e.getMessage());
            return true;
        }
    }

    /**
     * Abre un JFileChooser para seleccionar una imagen (JPG, PNG).
     * 
     * @return File seleccionado o null si se canceló.
     */
    public static File seleccionarArchivoImagen(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar imagen");
        chooser.setFileFilter(new FileNameExtensionFilter(
                "Imagenes (JPG, PNG)", "jpg", "png", "jpeg", "gif", "bmp", "webp"));

        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    /**
     * Parsea un string de precio quitando prefijos como "Precio: $" y
     * manejando separadores decimales de diferentes locales (, o .).
     */
    public static double parsearPrecio(String texto) {
        if (texto == null || texto.trim().isEmpty())
            return 0;
        String soloNumero = texto
                .replace("Precio: $", "")
                .replace(",", ".")
                .trim();
        if (soloNumero.isEmpty())
            return 0;
        return Double.parseDouble(soloNumero);
    }
}
