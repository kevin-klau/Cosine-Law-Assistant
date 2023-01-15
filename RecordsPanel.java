import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class RecordsPanel extends JPanel{
	// Properties
	int intNumberOfTriangles = 10;
	int intCurrentTriangleView = 1;
	
	int intSideA;
	int intSideB;
	int intSideC;
	double dblAngleAnswerA;
	double dblAngleAnswerB;
	double dblAngleAnswerC;
	
	boolean blnIssueReading = false;
	
	String strTriangles[][];
	
	String strBottomSideLabel = "";
	String strLeftSideLabel = "";
	String strRightSideLabel = "";
	String strLeftAngleLabel = "";
	String strRightAngleLabel = "";
	String strTopAngleLabel = "";
	
	// Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		// If theres no issue reading from the data file, continue
		if (blnIssueReading == false){
			// Draw BorderLine
			g.fillRect (330,130,2,395);
			
			// Load the Triangle Data from the array
			int intSideA = Integer.parseInt(strTriangles[intCurrentTriangleView-1][0]);
			int intSideB = Integer.parseInt(strTriangles[intCurrentTriangleView-1][1]);
			int intSideC = Integer.parseInt(strTriangles[intCurrentTriangleView-1][2]);
			double dblAngleAnswerA = Double.parseDouble(strTriangles[intCurrentTriangleView-1][3]);
			double dblAngleAnswerB = Double.parseDouble(strTriangles[intCurrentTriangleView-1][4]);
			double dblAngleAnswerC = Double.parseDouble(strTriangles[intCurrentTriangleView-1][5]);
			
			double dblBottomSideLength = 0;
			double dblLeftSideLength = 0;
			double dblRightSideLength = 0;
			
			double dblBottomLeftAngle = 0;
			double dblTopAngle = 0;
			double dblBottomRightAngle = 0;
			
			double dblScalar;
			double dblTriangleHeight;
			
			// Make the bottom length the longest
			if (intSideA >= intSideB && intSideA >= intSideC){
				// If Side A is the greatest side, change the orientation so side A is at the bottom
				dblBottomSideLength = intSideA;
				dblLeftSideLength = intSideC;
				dblRightSideLength = intSideB;
				dblBottomLeftAngle = dblAngleAnswerB;
				dblTopAngle = dblAngleAnswerA;
				dblBottomRightAngle = dblAngleAnswerC;
				strBottomSideLabel = "a";
				strLeftSideLabel = "c";
				strRightSideLabel = "b";
				strTopAngleLabel = "A";
				strLeftAngleLabel = "B";
				strRightAngleLabel = "C";
			} else if (intSideB >= intSideA && intSideB >= intSideC){
				// If Side B is the greatest side, change the orientation so side B is at the bottom
				dblBottomSideLength = intSideB;
				dblLeftSideLength = intSideA;
				dblRightSideLength = intSideC;
				dblBottomLeftAngle = dblAngleAnswerC;
				dblTopAngle = dblAngleAnswerB;
				dblBottomRightAngle = dblAngleAnswerA;
				strBottomSideLabel = "b";
				strLeftSideLabel = "a";
				strRightSideLabel = "c";
				strTopAngleLabel = "B";
				strLeftAngleLabel = "C";
				strRightAngleLabel = "A";
			} else if (intSideC >= intSideB && intSideC >= intSideA){
				// If Side C is the greatest side, change the orientation so side C is at the bottom
				dblBottomSideLength = intSideC;
				dblLeftSideLength = intSideB;
				dblRightSideLength = intSideA;
				dblBottomLeftAngle = dblAngleAnswerA;
				dblTopAngle = dblAngleAnswerC;
				dblBottomRightAngle = dblAngleAnswerB;
				strBottomSideLabel = "c";
				strLeftSideLabel = "b";
				strRightSideLabel = "a";
				strTopAngleLabel = "C";
				strLeftAngleLabel = "A";
				strRightAngleLabel = "B";
			}
			
			// Find Height of the Triangle (SOH CAH TOA)
			dblTriangleHeight = (dblRightSideLength) * (Math.sin(dblBottomRightAngle*(Math.PI/180.0)));	
			
			// Draw the Triangle
			// Find Scalar
			if (dblBottomSideLength / dblTriangleHeight >= 1.16){
				// If we use the entire 450 pixels as the base length and the height fits, we use this scaling
				dblScalar = 540/dblBottomSideLength;
			} else {
				// if the height is too tall, we base the scalar off the height rather than the base
				dblScalar = 350/dblTriangleHeight;
			}
			
			// Apply the Scalar to all the triangle sides / height
			dblBottomSideLength = dblBottomSideLength * dblScalar;
			dblLeftSideLength = dblLeftSideLength * dblScalar;
			dblRightSideLength = dblRightSideLength * dblScalar;
			dblTriangleHeight = dblTriangleHeight * dblScalar;

			// Find the Vertex Point
			double dblVertexDistanceFromStart;
			dblVertexDistanceFromStart = dblTriangleHeight / (Math.tan (dblBottomLeftAngle * (Math.PI/180)));
			
			// Draw the Triangle
			int intBottomLeftXY [] = {(int)(Math.round(920 - dblBottomSideLength - ((540 - dblBottomSideLength) / 2))), (int)(Math.round(150 + dblTriangleHeight + ((350 - dblTriangleHeight) / 2)))};
			int intBottomRightXY [] = {(int)(Math.round(920 - ((540 - dblBottomSideLength) / 2))), (int)(Math.round(150 + dblTriangleHeight + ((350-dblTriangleHeight) / 2)))};
			int intTopXY [] = {(int)(Math.round(920 - dblBottomSideLength + dblVertexDistanceFromStart - ((540 - dblBottomSideLength) / 2))), (int) (Math.round(150 + ((350-dblTriangleHeight) / 2)))};
			g.setColor (Color.BLACK);
			g.drawLine (intBottomLeftXY[0], intBottomLeftXY[1], intTopXY[0], intTopXY[1]);
			g.drawLine (intTopXY[0], intTopXY[1], intBottomRightXY[0], intBottomRightXY[1]);
			g.drawLine (intBottomRightXY[0], intBottomRightXY[1], intBottomLeftXY[0], intBottomLeftXY[1]);
			
			// Add the Diagram Labels
			Font newFont = new Font("SansSerif", Font.PLAIN, 30);
			g.setFont(newFont);
			g.drawString (strBottomSideLabel, ((intBottomLeftXY[0] + intBottomRightXY[0])/2) - 5, intBottomLeftXY[1]+25);
			g.drawString (strLeftSideLabel, (int)((intBottomLeftXY[0] * 2 + dblVertexDistanceFromStart)/2) - 12, ((intBottomLeftXY[1] + intTopXY[1])/2) - 12);
			g.drawString (strRightSideLabel, (int)((intBottomRightXY[0] + (intBottomLeftXY[0] + dblVertexDistanceFromStart))/2), ((intBottomRightXY[1] + intTopXY[1])/2) - 12);
			g.drawString (strLeftAngleLabel, intBottomLeftXY[0] - 27, intBottomLeftXY[1] + 12);
			g.drawString (strRightAngleLabel, intBottomRightXY[0] + 6, intBottomRightXY[1]+12);
			g.drawString (strTopAngleLabel, intTopXY[0] - 10 , intTopXY[1] - 5);
			
			// List the Dimensions
			newFont = new Font("SansSerif", Font.PLAIN, 28);
			g.setFont(newFont);
			
			g.drawString ("Side A = "+strTriangles[intCurrentTriangleView-1][0], 20, 190-5);
			g.drawString ("Side B = "+strTriangles[intCurrentTriangleView-1][1], 20, 250-5);
			g.drawString ("Side C = "+strTriangles[intCurrentTriangleView-1][2], 20, 310-5);
			g.drawString ("Angle A = "+strTriangles[intCurrentTriangleView-1][3].substring (0, strTriangles[intCurrentTriangleView-1][3].length() - 2) + " Degrees", 20, 370-5);
			g.drawString ("Angle B = "+strTriangles[intCurrentTriangleView-1][4].substring (0, strTriangles[intCurrentTriangleView-1][4].length() - 2)  + " Degrees", 20, 430-5);
			g.drawString ("Angle C = "+strTriangles[intCurrentTriangleView-1][5].substring (0, strTriangles[intCurrentTriangleView-1][5].length() - 2)  + " Degrees", 20, 490-5);
			
			//g.drawLine (380,150,380,500);
			//g.drawLine (380,500,920,500);
			//g.drawLine (920,500,920,150);
			//g.drawLine (920,150,380,150);
		}		
	}
	
	// Constructor
	public RecordsPanel(){
		super();
	}


}
