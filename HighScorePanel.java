import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class HighScorePanel extends JPanel{
	// Properties
	String strScores[][];
	
	// Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		Font newFont = new Font("SansSerif", Font.PLAIN, 40);
		g.setFont(newFont);
		g.drawString ("Name", 150, 90);
		g.drawString ("Score", 620, 90);
		
		// Open File
		BufferedReader txtHigh = null;
		try {
			txtHigh = new BufferedReader (new FileReader ("HighScores.txt"));
		}catch (FileNotFoundException e){
			System.out.println ("FILE NOT FOUND ERROR");
		}
		
		// Count how many there are
		int intCountScores = 0;
		String strCount = "";
		
		if (txtHigh != null){
			try{
				while (strCount != null){
					strCount = txtHigh.readLine();
					intCountScores++;
				}
				intCountScores = intCountScores - 1;
			}catch (IOException e){
				System.out.println ("ERROR READING FROM FILE");
			}
		}
		
		// Close File
		try{
			txtHigh.close();
		}catch (IOException e){
			System.out.println ("ERROR CLOSING FILE");
		}
		
		// 2 rows per person
		intCountScores = intCountScores / 2;
		
		// Make the Array [number of scores][2 columns]
		strScores = new String [intCountScores][2];
		
		// Fill the array with information
		// Open File
		try {
			txtHigh = new BufferedReader (new FileReader ("HighScores.txt"));
		}catch (FileNotFoundException e){
			System.out.println ("FILE NOT FOUND ERROR");
		}
		
		// Count how many there are
		int intCount;
		int intCounter;
		if (txtHigh != null){
			try{
				for (intCount = 0; intCount < intCountScores; intCount++){
					strScores[intCount][0] = txtHigh.readLine();
					strScores[intCount][1] = txtHigh.readLine();
				}
			}catch (IOException e){
				System.out.println ("ERROR READING FROM FILE");
			}
		}
		
		// Close File
		try{
			txtHigh.close();
		}catch (IOException e){
			System.out.println ("ERROR CLOSING FILE");
		}
		
		// Sort the stuff
		String strTemp;
		for (intCounter = 0; intCounter < intCountScores-1; intCounter++){
			for (intCount = 0; intCount < intCountScores-1; intCount++){
				if (Integer.parseInt(strScores[intCount][1]) < (Integer.parseInt(strScores[intCount+1][1]))){
					strTemp = strScores[intCount][0];
					strScores[intCount][0] = strScores[intCount+1][0];
					strScores[intCount+1][0] = strTemp;
					
					strTemp = strScores[intCount][1];
					strScores[intCount][1] = strScores[intCount+1][1];
					strScores[intCount+1][1] = strTemp;
				}
			}
		}
		
		// Draw the High Scores
		newFont = new Font("SansSerif", Font.PLAIN, 33);
		g.setFont(newFont);
		for (intCount = 0; intCount<10; intCount++){
			g.drawString (strScores[intCount][0],150, intCount*43+130);
			g.drawString (strScores[intCount][1],650, intCount*43+130);
		}
		
	}
	
	// Constructor
	public HighScorePanel(){
		super();
	}
}
