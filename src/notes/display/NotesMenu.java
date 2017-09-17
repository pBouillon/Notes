package notes.display;

import notes.handler.MenuHandler;
import notes.text.Text;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

import static notes.text.Text.STYLE_MODIFICATION;

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
            JMenuItem newFile  = new JMenuItem("New") ;
            newFile.addActionListener(ctrl) ;
            JMenuItem openFile = new JMenuItem("Open") ;
            openFile.addActionListener(ctrl) ;
            JMenuItem saveFile = new JMenuItem("Save") ;
            saveFile.addActionListener(ctrl) ;
            JMenuItem quitFile = new JMenuItem("Quit") ;
            quitFile.addActionListener(ctrl) ;
        fileMenu.add(newFile)  ;
        fileMenu.add(openFile) ;
        fileMenu.add(saveFile) ;
        fileMenu.add(quitFile) ;

        styleMenu = createColorMenu() ;

        add(fileMenu)  ;
        add(styleMenu) ;
    }

    private JMenu createColorMenu() {
        JMenu styleMenu = new JMenu("Style") ;
        JMenuItem newStyle = new JMenuItem("New style") ;
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
