package notes.handler;

import notes.display.ControlPanel;
import notes.text.Text;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static notes.text.Text.TEXT_MODIFICATION;

public class WriteHandler implements ActionListener {

    private ControlPanel view ;
    private JTextField   textWritten;
    private Text         model ;

    public WriteHandler(ControlPanel view, Text model, JTextField writtenText) {
        this.model = model ;
        this.textWritten = writtenText ;
        this.view  = view ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.addLine(textWritten.getText()) ;
        model.setModelChanged();
        model.notifyObservers(TEXT_MODIFICATION);
    }
}
