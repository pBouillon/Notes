package notes.display;

import notes.handler.WriteHandler;
import notes.text.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(250, 0, 275, 25) );
        add(writtenText);
        add(Box.createRigidArea(new Dimension(1, 25)));
        add(sendText) ;

        setOpaque(false) ;
    }

    private void initButton () {
        sendText.setForeground(Color.BLACK);
        sendText.setFont(new Font(
                "Serif",
                Font.PLAIN,
                20
        ));
        sendText.setMaximumSize(new Dimension(
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
        writtenText.setBackground( new Color(255, 255, 255, 150) );
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
