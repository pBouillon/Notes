package notes.text;

import java.io.*;
import java.util.ArrayList;

/**
 * @author "Pierre Bouillon" [pierrebouillon.tech]
 * @version 1.0.0
 */

public class FileHandler {

    public FileHandler() {}

    /**
     * Read a file and return its content
     *
     * @param source target file
     * @return an arrayList with the content of the target file
     */
    public ArrayList<String> loadFile (File source) {
        InputStream       ips = null ;
        BufferedReader    br  = null ;

        ArrayList<String> content     = new ArrayList<>() ;
        String            currentLine ;

        try {
            ips = new FileInputStream(source) ;
            br  = new BufferedReader(new InputStreamReader(ips)) ;

            while((currentLine = br.readLine()) != null) {
                content.add (currentLine) ;
            }

            br.close() ;
        } catch (IOException e) {
            e.printStackTrace();
            return null ;
        }

        return content ;
    }

    /**
     * Write `content` inside a file
     *
     * @param destination target file
     * @param content     content to put inside `destination`
     */
    public void saveFile (File destination, ArrayList<String> content) {
        PrintWriter pw = null ;

        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(destination, false))) ;
            for (String s : content) {
                pw.println (s) ;
            }
        }
        catch(IOException ioe){
            ioe.getMessage() ;
        }
        finally {
            if (pw != null) {
                pw.close() ;
            }
        }
    }
}
