package notes.styles;

import java.awt.*;

public class StyleJaune {

    private String name  ;
    private Color  color ;

    public StyleJaune() {
        name  = "Jaune" ;
        color = Color.YELLOW ;
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
