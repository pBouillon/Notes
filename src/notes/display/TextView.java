package notes.display;

import notes.text.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * @author "Pierre Bouillon" [pierrebouillon.tech]
 * @version 1.0.0
 */

import static notes.handler.MenuHandler.RGB_STEP;
import static notes.text.Text.*;

public class TextView extends JPanel implements Observer {

    private JTextArea   renderText ;
    private JScrollPane textWraper ;

    public TextView(Observable o) {
        super() ;
        setSize(new Dimension(500,700));
        o.addObserver (this) ;
        setOpaque (false) ;

        renderText = new JTextArea() ;
        initTextArea() ;

        textWraper = new JScrollPane(renderText) ;
        initWraper() ;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(25, 25, 25, 0) );
        setOpaque(false);
        add(textWraper) ;
    }

    private void initTextArea() {
        renderText.setFont(new Font(
                "Serif",
                Font.PLAIN,
                18
        )) ;
        renderText.setEditable(false) ;
        renderText.setLineWrap(true) ;
    }

    private void initWraper() {
        textWraper.setPreferredSize(
                new Dimension(
                        500,
                        renderText.getHeight()
                )
        ) ;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            if ((int)arg == TEXT_MODIFICATION || (int)arg == FILE_MODIFICATION) {
                StringBuilder sb ;
                Text model ;

                model = (Text)o ;
                sb = new StringBuilder() ;
                for (int i = 0; i < model.getLineCount(); ++i) {
                    sb.append(model.getLine(i)).append("\r\n") ;
                }
                renderText.setText(sb.toString()) ;
            }
            else if ((int)arg != STYLE_MODIFICATION){
                String rgb = (int)arg + "" ;
                rgb = rgb.substring(1, rgb.length()) ;

                // slice rgb = rrrgggbbb as Color(rrr, ggg, bbb)
                renderText.setForeground(
                        new Color (
                                Integer.parseInt(rgb.substring(0 , RGB_STEP)),
                                Integer.parseInt(rgb.substring(RGB_STEP    , RGB_STEP * 2)),
                                Integer.parseInt(rgb.substring(RGB_STEP * 2, RGB_STEP * 3))
                                )
                        ) ;
            }
        }
    }
}
