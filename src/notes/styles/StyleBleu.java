package notes.styles;

import java.awt.*;

public class StyleBleu {

    private String name  ;
    private Color  color ;

    public StyleBleu() {
        name  = "Bleu" ;
        color = Color.BLUE ;
    }

    public Color getColor() {
        return color ;
    }

    public String getName() {
        return name ;
    }

    public void setColor(Color newColor) {
        color = newColor ;
    }

    public void setName(String newName) {
        name = newName ;
    }
}
