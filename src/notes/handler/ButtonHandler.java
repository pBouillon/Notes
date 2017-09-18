package notes.handler;

import notes.text.Text;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static notes.text.Text.TEXT_MODIFICATION;

/**
 * @author "Pierre Bouillon" [pierrebouillon.tech]
 * @version 1.0.0
 */

public class ButtonHandler implements ActionListener {

    private JTextField   textWritten;
    private Text         model ;

    public ButtonHandler(Text model, JTextField writtenText) {
        this.model = model ;
        this.textWritten = writtenText ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton)e.getSource()).getText()) {
            case "Write":
                if (textWritten.getText().length() > 0) {
                    model.addLine(textWritten.getText()) ;
                    model.setModelChanged() ;
                    model.notifyObservers(TEXT_MODIFICATION) ;
                }
                break ;

            case "Delete":
                model.setModelChanged() ;
                model.notifyObservers(TEXT_MODIFICATION) ;
                break ;

            default:
                System.exit(1) ;
        }
    }
}
