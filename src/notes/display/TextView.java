package notes.display;

import notes.text.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static notes.handler.MenuHandler.RGB_STEP;
import static notes.text.Text.*;

public class TextView extends JPanel implements Observer {

    private JTextArea   renderText ;
    private JScrollPane textWraper ;

    public TextView(Observable o) {
        super() ;
        setSize(new Dimension(500,700));
        o.addObserver (this) ;

        renderText = new JTextArea() ;
        renderText.setPreferredSize(
                new Dimension(
                        500,
                        550
                )
        );
        textWraper = new JScrollPane(renderText) ;

        renderText.setEditable(false) ;
        renderText.setLineWrap(true) ;
        // BUG : catch a residual image when opacity changed
        renderText.setBackground( new Color(255, 255, 255, 150) );

        textWraper.setOpaque(false);
        textWraper.getViewport().setOpaque(false);
        textWraper.setBorder(null);
        textWraper.setViewportBorder(null);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(25, 25, 25, 0) );
        add(textWraper) ;

        setOpaque (false) ;
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
