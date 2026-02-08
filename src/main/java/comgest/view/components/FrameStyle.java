package comgest.view.components;
import javax.swing.*;
import java.awt.*;

public class FrameStyle {

public static JFrame crearFramePrincipal(String titulo) {
JFrame frame = new JFrame(titulo);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(800, 600);
frame.setLayout(new BorderLayout(10,5));
frame.setLocationRelativeTo(null);
frame.setResizable(false);
frame.getContentPane().setBackground(new Color(228, 228, 255));
ImageIcon icon = new ImageIcon(FrameStyle.class.getResource("/comgest/view/resources/logocompng.png"));
frame.setIconImage(icon.getImage());
return frame;
}
}
