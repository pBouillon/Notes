package notes.text;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class Text extends Observable implements IText {

    public static int STYLE_MODIFICATION = 1 ;
    public static int FILE_MODIFICATION  = 2 ;
    public static int TEXT_MODIFICATION  = 3 ;

    private ArrayList<String>       textCore    ;
    private FileHandler             fileHandler ;
    private HashMap<String, Color>  savedColor  ;

    public Text () {
        super() ;

        fileHandler = new FileHandler() ;
        textCore    = new ArrayList<>() ;
        savedColor  = new HashMap<>() ;

        savedColor.put("Black", Color.BLACK) ;
    }

    public void addColor(String s, Color c) {
        savedColor.put(s, c) ;
    }

    public void loadFile(File source) {
        textCore = fileHandler.loadFile(source) ;
    }

    public ArrayList<String> getAllColorNames() {
        ArrayList<String> keyStr = new ArrayList<>() ;
        keyStr.addAll(savedColor.keySet()) ;
        return keyStr ;
    }

    public Color getDefaultColor() {
        String key = savedColor.keySet().stream().findFirst().get() ;
        return savedColor.get(key) ;
    }

    public Color getColor(String key) {
        return savedColor.get(key) ;
    }

    public void saveFile(File destination) {
        fileHandler.saveFile(destination, textCore) ;
    }

    public void setModelChanged() {
        setChanged() ;
    }

    @Override
    public void addLine(String line) {
        textCore.add(line) ;
    }

    @Override
    public void clear() {
        textCore.clear() ;
    }

    @Override
    public int getSize() {
        int size ;

        size = 0;
        for (String s : textCore) {
            size += s.length() ;
        }
        return size ;
    }

    @Override
    public String getLine(int index) {
        return textCore.get(index) ;
    }

    @Override
    public int getLineCount() {
        return textCore.size() ;
    }

    @Override
    public void setLine(int i, String line) {
        textCore.set(i, line) ;
    }
}
