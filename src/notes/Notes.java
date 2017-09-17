package notes;

import notes.display.ControlPanel;
import notes.display.NotesMenu;
import notes.display.TextView;
import notes.text.Text;

import javax.swing.*;
import java.awt.*;

public class Notes extends JFrame {
    public final int WINDOW_HEIGHT = 700 ;
    public final int WINDOW_WIDTH  = 1000 ;

    public Notes(Text model) {
        super() ;

        setTitle("Notes") ;
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;

        add (new TextView(model), BorderLayout.WEST) ;
        add (new ControlPanel(model), BorderLayout.EAST) ;

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
