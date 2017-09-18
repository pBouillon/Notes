package notes.display;

import notes.handler.MenuHandler;
import notes.text.Text;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.util.Observable;
import java.util.Observer;

import static java.awt.event.InputEvent.ALT_DOWN_MASK;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static notes.text.Text.STYLE_MODIFICATION;

/**
 * @author "Pierre Bouillon" [pierrebouillon.tech]
 * @version 1.0.0
 */

public class NotesMenu extends JMenuBar implements Observer {

    private Text m ;
    private MenuHandler ctrl ;
    private JMenu styleMenu ;

    public NotesMenu (Text m) {
        super () ;
        m.addObserver(this) ;
        this.m = m ;

        ctrl = new MenuHandler(m) ;

        JMenu fileMenu = new JMenu("File") ;
        fileMenu.setMnemonic('F') ;
            JMenuItem newFile  = new JMenuItem("New") ;
                newFile.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
                newFile.addActionListener(ctrl) ;
            JMenuItem openFile = new JMenuItem("Open") ;
                openFile.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
                openFile.addActionListener(ctrl) ;
            JMenuItem saveFile = new JMenuItem("Save") ;
                saveFile.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
                saveFile.addActionListener(ctrl) ;
            JMenuItem quitFile = new JMenuItem("Quit") ;
                quitFile.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
                quitFile.addActionListener(ctrl) ;
        fileMenu.add(newFile)  ;
        fileMenu.add(openFile) ;
        fileMenu.add(saveFile) ;
        fileMenu.add(quitFile) ;

        styleMenu = createColorMenu() ;

        add(fileMenu)  ;
        add(styleMenu) ;
    }

    /**
     * Builds the style menu and its options:
     *  > adds a new style
     *  > chose an existing style
     * @return Style menu
     */
    private JMenu createColorMenu() {
        JMenu styleMenu = new JMenu("Style") ;
            styleMenu.setMnemonic('S');
        JMenuItem newStyle = new JMenuItem("New color") ;
            newStyle.setAccelerator(KeyStroke.getKeyStroke('C', CTRL_DOWN_MASK));
        styleMenu.add(newStyle) ;
        newStyle.addActionListener(ctrl) ;
        for (String s : m.getAllColorNames()) {
            JMenuItem addedColor = new JMenuItem(s) ;
            addedColor.addActionListener(ctrl);
            styleMenu.add(addedColor) ;
        }
        return styleMenu ;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            if ((int)arg == STYLE_MODIFICATION) {
                // updating content
                remove(styleMenu) ;
                styleMenu = createColorMenu() ;
                add (styleMenu) ;
                // refreshing UI
                revalidate() ;
                repaint() ;
            }
        }
    }
}
