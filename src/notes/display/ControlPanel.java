package notes.display;

import notes.handler.ButtonHandler;
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
    private JButton deleteText;

    public ControlPanel(Observable o) {
        super () ;
        setSize(new Dimension(500,700));
        o.addObserver(this) ;


        writtenText = new JTextField (20) ;
        initTextField () ;

        ButtonHandler ctrl = new ButtonHandler( this, (Text)o, writtenText) ;
        sendText   = new JButton("Write") ;
        deleteText = new JButton("Delete");
        initWriteButton() ;
        initDeleteButton() ;
        sendText.addActionListener(ctrl) ;
        deleteText.addActionListener(ctrl) ;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(
                buttonPanel.getWidth(),
                150
        ));
        buttonPanel.setLayout(new GridLayout(0,2, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(sendText);
        buttonPanel.add(deleteText);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(325, 0, 200, 25) );
        add(writtenText);
        add(Box.createRigidArea(new Dimension(1, 25)));
        add(buttonPanel) ;

        setOpaque(false) ;
    }

    private void initDeleteButton() {
        deleteText.setFont(new Font(
                "Serif",
                Font.PLAIN,
                24
        ));
        deleteText.setMaximumSize(new Dimension(
                175,
                115
        ));
    }

    private void initWriteButton() {
        sendText.setFont(new Font(
                "Serif",
                Font.PLAIN,
                24
        ));
        sendText.setMaximumSize(new Dimension(
                175,
                115
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
                28
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
