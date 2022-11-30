package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.JTextArea;

class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

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





//String input = in.readLine();
//System.out.println("initial: " + input);

//while(true) {
//	
//	System.out.println("Here!");
//	if(!server.protocol(serverView)) {
//		System.out.println("Client disconnected!");	
//		server.close();
//		break;
//	}
//	System.out.println("Here after!");
//}
//}