import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class CosinePanel extends JPanel{
	// Properties
	int intSideA = 50;
	int intSideB = 50;
	int intSideC = 50;
	double dblAngleAnswerA;
	double dblAngleAnswerB;
	double dblAngleAnswerC;
	int intAngleA;
	int intAngleB;
	int intAngleC;

	String strWhichAngle = "";
	String strBottomSideLabel = "";
	String strLeftSideLabel = "";
	String strRightSideLabel = "";
	String strLeftAngleLabel = "";
	String strRightAngleLabel = "";
	String strTopAngleLabel = "";
	
	boolean blnRepaintSides = false;
	boolean blnAngleSelected = false;
	boolean blnRealTriangle;
	
	int intImageCoverX= 404;
	
	int intRedStarY = -100;
	int intRedStarX = -100;
	
	// Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		// Add Example Diagram
		BufferedImage theExample = null;
		try{
			theExample = ImageIO.read(new File("TriangleDiagram.png"));
		}catch(IOException e){
			System.out.println("Image unable to load!");
		}
		g.drawImage (theExample, 70,45,this);
		
		// Draw Lines
		g.setColor (Color.BLACK);
		g.fillRect (400,40,2,560);
		g.fillRect (0,255,400,2);
		
		//g.fillRect (420, 80, 520, 400);
		
		// Draw the Diagram (400x280 at 350,350)
		if (blnRepaintSides == true){
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
			if (dblBottomSideLength / dblTriangleHeight >= (380/380)){
				// If we use the entire 450 pixels as the base length and the height fits, we use this scaling
				dblScalar = 380/dblBottomSideLength;
			} else {
				// if the height is too tall, we base the scalar off the height rather than the base
				dblScalar = 380/dblTriangleHeight;
			}
			
			// Apply the Scalar to all the triangle sides / height
			dblBottomSideLength = dblBottomSideLength * dblScalar;
			dblLeftSideLength = dblLeftSideLength * dblScalar;
			dblRightSideLength = dblRightSideLength * dblScalar;
			dblTriangleHeight = dblTriangleHeight * dblScalar;

			// Find the Vertex Point
			double dblVertexDistanceFromStart;
			dblVertexDistanceFromStart = dblTriangleHeight / (Math.tan (dblBottomLeftAngle * (Math.PI/180)));
		
			
			blnRealTriangle = true;
			
			if (dblBottomSideLength + dblLeftSideLength <= dblRightSideLength || dblRightSideLength + dblLeftSideLength <= dblBottomSideLength || dblBottomSideLength + dblRightSideLength <= dblLeftSideLength){
				blnRealTriangle = false;
			}
			
			if (blnRealTriangle == true){
				// Draw the Triangle
				int intBottomLeftXY [] = {(int)(Math.round(890 - dblBottomSideLength - ((400 - dblBottomSideLength) / 2))), (int)(Math.round(80 + dblTriangleHeight + ((380 - dblTriangleHeight) / 2)))};
				int intBottomRightXY [] = {(int)(Math.round(890 - ((400 - dblBottomSideLength) / 2))), (int)(Math.round(80 + dblTriangleHeight + ((380-dblTriangleHeight) / 2)))};
				int intTopXY [] = {(int)(Math.round(890 - dblBottomSideLength + dblVertexDistanceFromStart - ((400 - dblBottomSideLength) / 2))), (int) (Math.round(80 + ((380-dblTriangleHeight) / 2)))};
				g.setColor (Color.BLACK);
				g.drawLine (intBottomLeftXY[0], intBottomLeftXY[1], intTopXY[0], intTopXY[1]);
				g.drawLine (intTopXY[0], intTopXY[1], intBottomRightXY[0], intBottomRightXY[1]);
				g.drawLine (intBottomRightXY[0], intBottomRightXY[1], intBottomLeftXY[0], intBottomLeftXY[1]);
				
				// Add the Diagram Labels
				Font newFont = new Font("SansSerif", Font.PLAIN, 30);
				g.setFont(newFont);
				g.drawString (strBottomSideLabel, ((intBottomLeftXY[0] + intBottomRightXY[0])/2) - 5, intBottomLeftXY[1]+28);
				g.drawString (strLeftSideLabel, (int)((intBottomLeftXY[0] * 2 + dblVertexDistanceFromStart)/2) - 12, ((intBottomLeftXY[1] + intTopXY[1])/2) - 12);
				g.drawString (strRightSideLabel, (int)((intBottomRightXY[0] + (intBottomLeftXY[0] + dblVertexDistanceFromStart))/2), ((intBottomRightXY[1] + intTopXY[1])/2) - 12);
				g.drawString (strLeftAngleLabel, intBottomLeftXY[0] - 27, intBottomLeftXY[1] + 12);
				g.drawString (strRightAngleLabel, intBottomRightXY[0] + 6, intBottomRightXY[1]+12);
				g.drawString (strTopAngleLabel, intTopXY[0] - 10 , intTopXY[1] - 5);
				
				// Add the Diagram Title
				g.drawString ("Diagram:", 420, 70);
				
				newFont = new Font("SansSerif", Font.PLAIN, 25);
				g.setFont(newFont);
				//g.drawString ("The Size Of "+strWhichAngle+" is ", 30, 480);
				if (strWhichAngle.equalsIgnoreCase("Side A")){
					g.drawString ("The Size Of "+strWhichAngle+" is "+intSideA+"!", 430, 520);
				} else if (strWhichAngle.equalsIgnoreCase("Side B")){
					g.drawString ("The Size Of "+strWhichAngle+" is "+intSideB+"!", 430, 520);
				} else if (strWhichAngle.equalsIgnoreCase("Side C")){
					g.drawString ("The Size Of "+strWhichAngle+" is "+intSideC+"!", 430, 520);
				} else if (strWhichAngle.equalsIgnoreCase("Angle A")){
					g.drawString ("The Size Of "+strWhichAngle+" is "+dblAngleAnswerA + " Degrees!", 430, 520);
				} else if (strWhichAngle.equalsIgnoreCase("Angle B")){
					g.drawString ("The Size Of "+strWhichAngle+" is "+dblAngleAnswerB + " Degrees!", 430, 520);
				} else if (strWhichAngle.equalsIgnoreCase("Angle C")){
					g.drawString ("The Size Of "+strWhichAngle+" is "+dblAngleAnswerC + " Degrees!", 430, 520);
				}
				//strWhichAngle
				
				//int intTriangleX[] = {(int)(Math.round(750 - dblBottomSideLength - ((400 - dblBottomSideLength) / 2))), (int)(Math.round(750 - dblBottomSideLength + dblVertexDistanceFromStart - ((400 - dblBottomSideLength) / 2))) , (int)(Math.round(750 - ((400 - dblBottomSideLength) / 2)))};			
				//int intTriangleY[] = {(int)(Math.round(350 + dblTriangleHeight + ((275 - dblTriangleHeight) / 2))), (int) (Math.round(350 + ((275-dblTriangleHeight) / 2))) , (int)(Math.round(350 + dblTriangleHeight + ((275-dblTriangleHeight) / 2)))};
				//g.drawPolygon(intTriangleX, intTriangleY, 3);
			}else if (blnRealTriangle == false){
				// If the triangle is impossible, tell them it's impossible
				Font newFont = new Font("SansSerif", Font.PLAIN, 55);
				g.setFont(newFont);
				g.drawString ("Impossible Triangle...", 420, 260);
				g.drawString ("Try Again!", 550, 330);
			}
			
			// Do the animation where the diagram is being revealed from left to right			
			g.setColor (new Color (238,238,238));
			g.fillRect (intImageCoverX,77, 600, 520);
			intImageCoverX = intImageCoverX + 10;
			
			// When the image is fully revealed, stop doing the animation
			if (intImageCoverX >= 1000){
				blnRepaintSides = false;
				intImageCoverX = 404;
			}
			
			// Make bottom length the scalar = to 400 pixels only if it's heigh fits within the limit
			// Else make the height the scalar		
		}
		// Draw the "REQUIRED" red star
		Font newFont = new Font("SansSerif", Font.BOLD, 20);
		g.setColor (Color.RED);
		g.setFont(newFont);
		g.drawString ("*", intRedStarX, intRedStarY);
		
	}
	
	// Constructor
	public CosinePanel(){
		super();
	}
}
