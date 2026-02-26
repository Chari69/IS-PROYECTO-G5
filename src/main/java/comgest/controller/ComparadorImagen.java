package comgest.controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// recibe 2 rutas compara las imagenes y devuelve un booleano indicando si son iguales o no
// tienes que crear un objeto de esta clase en el main y llamar al metodo compararImagenes con las rutas de las imagenes a comparar

public class ComparadorImagen {
    public boolean CompararImagenes(String ruta1, String ruta2) {
        try {
            BufferedImage img1 = ImageIO.read(new File(ruta1));
            BufferedImage img2 = ImageIO.read(new File(ruta2));

            // Primero verificamos que tengan las mismas dimensiones
            if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
                return false;
            }

            // Comparamos píxel por píxel
            for (int y = 0; y < img1.getHeight(); y++) {
                for (int x = 0; x < img1.getWidth(); x++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                        return false; // Diferencia encontrada
                    }
                }
            }
            return true; // Son idénticas
        } catch (IOException e) {
            System.err.println("Error al leer las imágenes: " + e.getMessage());
            return false;
        }
    }
}