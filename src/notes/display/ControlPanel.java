package notes.display;

import notes.handler.WriteHandler;
import notes.text.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import static notes.text.Text.FILE_MODIFICATION;
import static notes.text.Text.TEXT_MODIFICATION;

public class ControlPanel extends JPanel implements Observer {

    private JTextField writtenText ;
    private JButton sendText ;

    public ControlPanel(Observable o) {
        super () ;
        setSize(new Dimension(500,700));
        o.addObserver(this) ;

        writtenText = new JTextField (20) ;
        initTextField () ;

        sendText = new JButton("Write") ;
        initButton() ;
        sendText.addActionListener(new WriteHandler(
                this,
                (Text)o,
                writtenText
        )) ;

        JPanel mainPan = new JPanel() ;


        setOpaque(false) ;
        setLayout(new GridLayout(0, 1, 15, 15)) ;
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        add (writtenText) ;
        add (sendText) ;
    }

    private void initButton () {
        sendText.setForeground(Color.BLACK);
        sendText.setFont(new Font(
                "Serif",
                Font.PLAIN,
                20
        ));
        sendText.setPreferredSize(new Dimension(
                175,
                100
        ));
    }

    private void initTextField () {
        writtenText.setPreferredSize(new Dimension(writtenText.getWidth(), 150));
        writtenText.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                sendText.doClick() ;
            }
        }) ;
        writtenText.setFont(new Font(
                "Serif",
                Font.PLAIN,
                25
        ));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            if ((int) arg == TEXT_MODIFICATION || (int)arg == FILE_MODIFICATION) {
                writtenText.setText("") ;
                writtenText.requestFocus() ;
            }
        }
    }
}
