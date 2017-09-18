package notes.text;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * @author "Pierre Bouillon" [pierrebouillon.tech]
 * @version 1.0.0
 */

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

    /**
     * Add a color with its name
     *
     * @param s color name
     * @param c color to add
     */
    public void addColor(String s, Color c) {
        savedColor.put(s, c) ;
    }

    /**
     * Fill `textCore` with the content of a file
     *
     * @param source target file
     */
    public void loadFile(File source) {
        textCore = fileHandler.loadFile(source) ;
    }

    /**
     * Get all color names in an ArrayList
     *
     * @return all the color names
     */
    public ArrayList<String> getAllColorNames() {
        ArrayList<String> keyStr = new ArrayList<>() ;
        keyStr.addAll(savedColor.keySet()) ;
        return keyStr ;
    }

    /**
     * Get a stored color through its name
     *
     * @param  key name of the color
     * @return the matching color
     */
    public Color getColor(String key) {
        return savedColor.get(key) ;
    }

    /**
     * Put the content of `textCore` inside a file
     *
     * @param destination target file
     */
    public void saveFile(File destination) {
        fileHandler.saveFile(destination, textCore) ;
    }

    /**
     * Prepare for Observer.notifyAll()
     */
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
