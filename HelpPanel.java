import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class HelpPanel extends JPanel{
	// Properties

	// Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		// Draw Image
		try{
			BufferedImage theImage = ImageIO.read(new File("Help1.png"));
			g.drawImage (theImage, 230, 45,this);
		}catch(IOException e){
			System.out.println("Image unable to load!");
		}
		
	}
	
	public HelpPanel(){
		super();
	}
}
