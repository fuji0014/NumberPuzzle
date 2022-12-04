package View;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * Class name: CustomOutputStream
 * Methods list: write
 * Constant list: N/A
 * Purpose: CustomOutputStream class sets the console output to Jtextarea
 * @author Amy Fujimoto
 * @version 04 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
class CustomOutputStream extends OutputStream {
	/**
	 * JTextArea variable for console on client/server gui
	 */
    private JTextArea textArea;

    /**
     * Method name: Overloaded constructor 
	 * Purpose: Initializes class variables
     * @param textArea JTextArea to be used for console
     */
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Appends and writes in console
     */
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
        // keeps the textArea up to date
        textArea.update(textArea.getGraphics());
    }
}