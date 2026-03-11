package comgest.controller;

import comgest.view.MenuFormDialog;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MenuFormController {
    private final MenuFormDialog view;

    public MenuFormController(MenuFormDialog view) {
        if (view == null) {
            throw new IllegalArgumentException("view cannot be null");
        }
        this.view = view;
        conectarEventos();
    }

    private void conectarEventos() {
        view.setOnGuardar(e -> view.cerrarConResultado(true));
        view.setOnCancelar(e -> view.cerrarConResultado(false));
        view.setOnSeleccionarImagen(e -> seleccionarImagen());
    }

    private void seleccionarImagen() {
        java.io.File origen = comgest.utils.Utils.seleccionarArchivoImagen(view);
        if (origen == null) {
            return;
        }

        String rutaRelativa = guardarImagenEnData(origen);
        if (rutaRelativa == null) {
            JOptionPane.showMessageDialog(view, "No se pudo guardar la imagen.");
            return;
        }

        view.setImagenRuta(rutaRelativa);
    }

    private String guardarImagenEnData(File origen) {
        if (origen == null) {
            return null;
        }

        Path destinoBase = Paths.get("src", "main", "java", "comgest", "data", "img");
        try {
            Files.createDirectories(destinoBase);
            String nombreFinal = generarNombreUnico(destinoBase, origen.getName());
            Path destino = destinoBase.resolve(nombreFinal);
            Files.copy(origen.toPath(), destino);
            return Paths.get("data", "img", nombreFinal).toString().replace("\\", "/");
        } catch (IOException e) {
            System.err.println("Error guardando imagen: " + e.getMessage());
            return null;
        }
    }

    private String generarNombreUnico(Path destinoBase, String nombreOriginal) {
        if (nombreOriginal == null || nombreOriginal.trim().isEmpty()) {
            return "imagen";
        }

        String nombre = nombreOriginal;
        String base = nombreOriginal;
        String ext = "";
        int punto = nombreOriginal.lastIndexOf('.');
        if (punto > 0 && punto < nombreOriginal.length() - 1) {
            base = nombreOriginal.substring(0, punto);
            ext = nombreOriginal.substring(punto);
        }

        int contador = 1;
        Path candidato = destinoBase.resolve(nombre);
        while (Files.exists(candidato)) {
            nombre = base + "_" + contador + ext;
            candidato = destinoBase.resolve(nombre);
            contador++;
        }

        return nombre;
    }
}
