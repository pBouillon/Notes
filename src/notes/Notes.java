package notes;

import notes.display.ControlPanel;
import notes.display.NotesMenu;
import notes.display.TextView;
import notes.text.Text;

import javax.swing.*;
import java.awt.*;

/**
 * @author "Pierre Bouillon" [pierrebouillon.tech]
 * @version 1.0.0
 */

public class Notes extends JFrame {
    private final int WINDOW_HEIGHT = 700 ;
    private final int WINDOW_WIDTH  = 1000 ;

    public Notes(Text model) {
        super() ;

        setTitle("Notes") ;
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;

        ImageIcon image_icon = new ImageIcon(getClass().getResource("/img/icon.png"));
        setIconImage(image_icon.getImage());

        JPanel mainPanel = new JPanel( new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image_background = new ImageIcon(getClass().getResource("/img/background.jpeg"));
                g.drawImage(image_background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        } ;
        mainPanel.add (new TextView(model), BorderLayout.WEST) ;
        mainPanel.add (new ControlPanel(model), BorderLayout.EAST) ;
        setContentPane(mainPanel);

        setVisible(true) ;
        setResizable(false) ;
        setLocationRelativeTo(null) ;
    }

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Text m = new Text();
            Notes v = new Notes(m) ;
            v.setJMenuBar(new NotesMenu(m));
        });
    }
}
