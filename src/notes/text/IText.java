package notes.text;

/**
 * @author gautier - UHP
 * @version 2014
 */
public interface IText {

    public void addLine(String line);

    public void clear();

    public int getSize();

    public String getLine(int index);

    public int getLineCount();

    public void setLine(int i, String line);
}