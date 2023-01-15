import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class TestPanel extends JPanel{
	// Properties
	boolean blnCorrect = true;
	int intAskAngleOrSide = 0;
	
	int intSideRandomizer= 0;
	int intSideA = 0;
	int intSideB = 0;
	int intSideC = 0;
	double dblAngleAnswerA;
	double dblAngleAnswerB;
	double dblAngleAnswerC;
	
	String strBottomSideLabel = "";
	String strLeftSideLabel = "";
	String strRightSideLabel = "";
	String strLeftAngleLabel = "";
	String strRightAngleLabel = "";
	String strTopAngleLabel = "";
	
	int intScore = 0;
	
	String strQuestion = "";
	String strInformation1 = "";
	String strInformation2 = "";
	String strInformation3 = "";
	
	int intAnswer;
	
	// Methods
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		if (blnCorrect == true){
			// Add Lines
			g.fillRect (570, 80, 2, 460);
			g.fillRect (570, 310, 390, 2);
/*
			// Make a True Triangle (since each side will be at least 70% of the previous side, it will be impossible to create an impossible triangle)
			intSideA = (int)(Math.random()*90)+10;
			intSideB = (int)((Math.random()*(intSideA*0.3)) + (intSideA*0.7));
			intSideC = (int)((Math.random()*(intSideB*0.3)) + (intSideB*0.7));
			
			//System.out.println (intSideA + " | " + intSideB + " | " + intSideC);
			
			// Mix up the Sides
			intSideRandomizer = (int)(Math.random()*5)+1;
			int intTemp = 0;
			if (intSideRandomizer == 1){
				// ACB
				intTemp = intSideB;
				intSideB = intSideC;
				intSideC = intTemp;
			} else if (intSideRandomizer == 2){
				// BCA
				intTemp = intSideA;
				intSideA = intSideB;
				intSideB = intSideC;
				intSideC = intTemp;
			} else if (intSideRandomizer == 3){
				// BAC
				intTemp = intSideA;
				intSideA = intSideB;
				intSideB = intTemp;
			} else if (intSideRandomizer == 4){
				// CAB
				intTemp = intSideA;
				intSideA = intSideC;
				intSideC = intSideB;
				intSideB = intTemp;
			} else if (intSideRandomizer == 5){
				// CBA
				intTemp = intSideA;
				intSideA = intSideC;
				intSideC = intTemp;
			}		
			System.out.println (intSideA + " | " + intSideB + " | " + intSideC);
*/
			// Draw the Triangle
			// Find the other 3 angles
			dblAngleAnswerA =  (Math.round (((Math.acos ((Math.pow (intSideB, 2.0) + Math.pow (intSideC, 2.0) - Math.pow (intSideA,2.0)) / (2.0*intSideB*intSideC)))* (180.0/Math.PI))*10.0))/10.0;
			dblAngleAnswerB =  (Math.round (((Math.acos ((Math.pow (intSideC, 2.0) + Math.pow (intSideA, 2.0) - Math.pow (intSideB,2.0)) / (2.0*intSideC*intSideA)))* (180.0/Math.PI))*10.0))/10.0;
			dblAngleAnswerC =  (Math.round (((Math.acos ((Math.pow (intSideA, 2.0) + Math.pow (intSideB, 2.0) - Math.pow (intSideC,2.0)) / (2.0*intSideA*intSideB)))* (180.0/Math.PI))*10.0))/10.0;
			
			// Make the bottom length the longest
			double dblBottomSideLength = 0;
			double dblLeftSideLength = 0;
			double dblRightSideLength = 0;
			
			double dblBottomLeftAngle = 0;
			double dblTopAngle = 0;
			double dblBottomRightAngle = 0;
			
			double dblScalar;
			double dblTriangleHeight;
			
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
			if (dblBottomSideLength / dblTriangleHeight >= (460/360)){
				// If we use the entire 450 pixels as the base length and the height fits, we use this scaling
				dblScalar = 460/dblBottomSideLength;
			} else {
				// if the height is too tall, we base the scalar off the height rather than the base
				dblScalar = 360/dblTriangleHeight;
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
			int intBottomLeftXY [] = {(int)(Math.round(520 - dblBottomSideLength - ((460 - dblBottomSideLength) / 2))), (int)(Math.round(120 + dblTriangleHeight + ((360 - dblTriangleHeight) / 2)))};
			int intBottomRightXY [] = {(int)(Math.round(520 - ((460 - dblBottomSideLength) / 2))), (int)(Math.round(120 + dblTriangleHeight + ((360-dblTriangleHeight) / 2)))};
			int intTopXY [] = {(int)(Math.round(520 - dblBottomSideLength + dblVertexDistanceFromStart - ((460 - dblBottomSideLength) / 2))), (int) (Math.round(120 + ((360-dblTriangleHeight) / 2)))};
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
			/*
			// Ask for Side ABC or Angle ABC (1=SideA, 2=SideB, 3=SideC, 4=AngleA, 5=AngleB, 6=AngleC)
			intAskAngleOrSide = (int)(Math.random()*6)+1;
			
			// Set Information and Question
			if (intAskAngleOrSide == 1){
				// Side A
				strQuestion = "Side A";
				strInformation1 = "Angle A: "+dblAngleAnswerA + " Degrees";
				strInformation2 = "Side B: "+intSideB;
				strInformation3 = "Side C: "+intSideC;
				intAnswer = intSideA;
				
			} else if (intAskAngleOrSide == 2){
				// Side B
				strQuestion = "Side B";
				strInformation1 = "Angle B: "+dblAngleAnswerB + " Degrees";
				strInformation2 = "Side A: "+intSideA;
				strInformation3 = "Side C: "+intSideC;
				intAnswer = intSideB;
				
			} else if (intAskAngleOrSide == 3){
				// Side C
				strQuestion = "Side C";
				strInformation1 = "Angle C: "+dblAngleAnswerC + " Degrees";
				strInformation2 = "Side A: "+intSideA;
				strInformation3 = "Side B: "+intSideB;
				intAnswer = intSideC;
				
			} else if (intAskAngleOrSide == 4){
				// Angle A
				strQuestion = "Angle A";
				strInformation1 = "Side A: "+intSideA;
				strInformation2 = "Side B: "+intSideB;
				strInformation3 = "Side C: "+intSideC;
				intAnswer = (int)Math.round (dblAngleAnswerA);
				
			} else if (intAskAngleOrSide == 5){
				// Angle B
				strQuestion = "Angle B";
				strInformation1 = "Side A: "+intSideA;
				strInformation2 = "Side B: "+intSideB;
				strInformation3 = "Side C: "+intSideC;
				intAnswer = (int)Math.round (dblAngleAnswerB);
				
			} else if (intAskAngleOrSide == 6){
				// Angle C
				strQuestion = "Angle C";
				strInformation1 = "Side A: "+intSideA;
				strInformation2 = "Side B: "+intSideB;
				strInformation3 = "Side C: "+intSideC;
				intAnswer = (int)Math.round (dblAngleAnswerC);			
				
			}
			*/
			// Print the 3 Pieces of Information
			newFont = new Font("SansSerif", Font.PLAIN, 35);
			g.setFont(newFont);
			g.drawString (strInformation1, 590, 360);
			g.drawString (strInformation2, 590, 430);
			g.drawString (strInformation3, 590, 500);
			
			System.out.println ("Correct Test Answer: "+intAnswer);
			
		} else if (blnCorrect == false){
			Font newFont = new Font("SansSerif", Font.PLAIN, 80);
			g.setFont(newFont);
			g.drawString ("WRONG ANSWER :(", 100,200);
			
			newFont = new Font("SansSerif", Font.PLAIN, 60);
			g.setFont(newFont);
			g.drawString ("Score: "+intScore, 370, 300);
			System.out.println ("GOT QUESTION WRONG :D");
		}
		
	}
	
	// Constructor
	public TestPanel(){
		super();
	}
}


