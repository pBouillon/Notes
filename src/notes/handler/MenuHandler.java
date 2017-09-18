package notes.handler;

import notes.text.Text;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static notes.text.Text.FILE_MODIFICATION;
import static notes.text.Text.STYLE_MODIFICATION;

/**
 * @author "Pierre Bouillon" [pierrebouillon.tech]
 * @version 1.0.0
 */

public class MenuHandler implements ActionListener {
    public final static int RGB_STEP   = 3 ;
    private final String    RGB_FORMAT = "000" ;

    private Text model ;

    public MenuHandler(Text model) {
        this.model = model ;
    }

    /**
     * Empty the current content of the text area
     */
    private void createNewDocument() {
        model.clear();
        model.setModelChanged();
        model.notifyObservers(FILE_MODIFICATION);
    }

    /**
     * Open a document and fill the text area with its content
     */
    private void openDocument() {
        JFileChooser chooser ;
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text format", "txt", "md");

        chooser = new JFileChooser() ;
        chooser.setFileFilter(filter) ;

        if (chooser.showOpenDialog(null)
                == JFileChooser.APPROVE_OPTION) {
            model.clear() ;
            model.loadFile(chooser.getSelectedFile()) ;
        }
        model.setModelChanged() ;
        model.notifyObservers(FILE_MODIFICATION) ;
    }

    /**
     * Save the content of the text area inside a document
     */
    private void saveDocument () {
        JFileChooser chooser ;
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text format", "txt", "md");

        chooser = new JFileChooser() ;
        chooser.setFileFilter(filter) ;
        if (chooser.showOpenDialog(null)
                == JFileChooser.APPROVE_OPTION) {
            model.saveFile(chooser.getSelectedFile()) ;
        }
    }

    /**
     * Open a color picker
     * Open a dialog to give this color a name
     * Save it and add it in the menu bar
     */
    private void createNewColor() {
        Color newColor = JColorChooser.showDialog(
                null,
                "Pick a new Color",
                Color.BLACK) ;
        if (newColor != null) {
            String newColorName = JOptionPane.showInputDialog(null, "Name this color : ",
                    "New color", JOptionPane.QUESTION_MESSAGE) ;
            if (newColorName != null) {
                model.addColor(newColorName, newColor) ;
                model.setModelChanged() ;
                model.notifyObservers(STYLE_MODIFICATION) ;
            }
        }
    }

    /**
     * Get the value of the clicked color name
     * Sent it as foreground for the text area
     *
     * @param key the key of the stored Color
     */
    private void sendSelectedColor(String key) {
        Color toChange = model.getColor(key) ;
        // initialization at 1 avoid the deletion of leading '0'
        String colVal = "1" ;


        colVal += (RGB_FORMAT + String.valueOf(toChange.getRed()))
                .substring(String.valueOf(toChange.getRed()).length()) ;

        colVal += (RGB_FORMAT + String.valueOf(toChange.getGreen()))
                .substring(String.valueOf(toChange.getGreen()).length()) ;

        colVal += (RGB_FORMAT + String.valueOf(toChange.getBlue()))
                .substring(String.valueOf(toChange.getBlue()).length()) ;

        model.setModelChanged() ;
        model.notifyObservers(Integer.parseInt(colVal)) ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JMenuItem)e.getSource()).getText()) {
            case "New" :
                createNewDocument() ;
                break ;

            case "Open" :
                openDocument() ;
                break ;

            case "Save":
                saveDocument() ;
                break ;

            case "Quit":
                System.exit(0) ;
                break ;

            case "New color":
                createNewColor() ;
                break ;

            default:
                sendSelectedColor(((JMenuItem)e.getSource()).getText()) ;
        }
    }
}
