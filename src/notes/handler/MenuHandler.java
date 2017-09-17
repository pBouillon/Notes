package notes.handler;

import notes.text.Text;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static notes.text.Text.FILE_MODIFICATION;
import static notes.text.Text.STYLE_MODIFICATION;

public class MenuHandler implements ActionListener {
    public final static int RGB_STEP   = 3 ;
    private final String    RGB_FORMAT = "000" ;

    private Text model ;

    public MenuHandler(Text model) {
        this.model = model ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser ;
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text format", "txt", "md");

        switch (((JMenuItem)e.getSource()).getText()) {
            case "New" :
                model.clear();
                model.setModelChanged();
                model.notifyObservers(FILE_MODIFICATION);
                break ;

            case "Open" :
                chooser = new JFileChooser() ;
                chooser.setFileFilter(filter) ;

                if (chooser.showOpenDialog(null)
                        == JFileChooser.APPROVE_OPTION) {
                    model.clear() ;
                    model.loadFile(chooser.getSelectedFile()) ;
                }
                model.setModelChanged() ;
                model.notifyObservers(FILE_MODIFICATION) ;
                break ;

            case "Save":
                chooser = new JFileChooser() ;
                chooser.setFileFilter(filter) ;
                if (chooser.showOpenDialog(null)
                        == JFileChooser.APPROVE_OPTION) {
                    model.saveFile(chooser.getSelectedFile()) ;
                }
                break ;

            case "Quit":
                System.exit(0);
                break ;

            case "New style":
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
                break ;

            default:
                Color toChange = model.getColor(((JMenuItem)e.getSource()).getText()) ;
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
    }
}