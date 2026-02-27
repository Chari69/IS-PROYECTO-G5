package comgest.utils;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
}
