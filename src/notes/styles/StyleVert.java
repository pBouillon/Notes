package notes.styles;

import java.awt.*;

public class StyleVert {

    private String name  ;
    private Color  color ;

    public StyleVert() {
        name  = "Vert" ;
        color = Color.GREEN ;
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
