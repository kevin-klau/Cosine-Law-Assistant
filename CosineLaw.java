// Program: Cosine Law
// Version: 3.1
// Created By: Kevin Lau
// Date Completed: May 4th, 2022

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class CosineLaw implements ActionListener, MenuListener, ChangeListener{
	// Properties
	JFrame theFrame = new JFrame("Cosine Law Calculator");
	Timer theTimer = new Timer(1000/48, this);
	
	// Make the Panels
	CosinePanel theSidePanel = new CosinePanel();
	CosinePanel theAnglePanel = new CosinePanel();
	HelpPanel theHelpPanel = new HelpPanel();
	AboutPanel theAboutPanel = new AboutPanel();
	TestPanel theTestPanel = new TestPanel();
	RecordsPanel thePastPanel = new RecordsPanel();
	HighScorePanel theHighPanel = new HighScorePanel();
	
	// Make the Menu Bar, Menus, and Menu Items
	JMenuBar theBar = new JMenuBar();
	JMenu theCalculateMenu = new JMenu("Calculate                                           ");
	JMenuItem theSideMenuItem = new JMenuItem("3 Sides");
	JMenuItem theAngleMenuItem = new JMenuItem("2 Sides 1 Angle");
	JMenuItem theTestMenuItem = new JMenuItem ("Test");
	JMenuItem theTestScoreMenuItem = new JMenuItem ("HighScores");
	JMenu theHelpMenu = new JMenu("Help	                                               ");
	JMenu theAboutMenu = new JMenu("About                                               ");
	JMenu theTestingMenu = new JMenu("Test                                                ");
	JMenu thePastMenu = new JMenu("Past Records                                        ");
	
	// Make the JComponents for the Side Page
	JLabel theImageDesc = new JLabel ("Example of the Diagram",SwingConstants.CENTER);
	JSlider theSideSliderSideA = new JSlider (1, 100, 50);
	JLabel theSideATitle = new JLabel ("Side A Length:");
	JTextField theASideAmount = new JTextField ("50");;
	JSlider theSideSliderSideB = new JSlider (1, 100, 50);
	JLabel theSideBTitle = new JLabel ("Side B Length:");
	JTextField theBSideAmount = new JTextField ("50");
	JSlider theSideSliderSideC = new JSlider (1, 100, 50);
	JLabel theSideCTitle = new JLabel ("Side C Length:");
	JTextField theCSideAmount = new JTextField ("50");
	
	// RadioButton stuff for the side page
	JRadioButton theSideAngleAButton = new JRadioButton ("Angle A");
	JRadioButton theSideAngleBButton = new JRadioButton ("Angle B");
	JRadioButton theSideAngleCButton = new JRadioButton ("Angle C");
	ButtonGroup theSideAngleButtonGroup = new ButtonGroup();
	
	// Calculate and save buttons for hte side page
	JButton theCalculateButton = new JButton ("Calculate!");
	JButton theSaveWorkButton = new JButton ("Save!");
	
	// RadioButton stuff to choose between angles
	JRadioButton theAngleSideAButton = new JRadioButton ("Side A");
	JRadioButton theAngleSideBButton = new JRadioButton ("Side B");
	JRadioButton theAngleSideCButton = new JRadioButton ("Side C");
	ButtonGroup theAngleSideButtonGroup = new ButtonGroup();
	
	// JComponents for the Angle Page (textfield, slider, and labels
	JSlider theAngleSliderSideA = new JSlider (1,100,50);
	JSlider theAngleSliderSideB = new JSlider (1,100,50);
	JSlider theAngleSliderSideC = new JSlider (1,100,50);
	JSlider theAngleSliderAngleA = new JSlider (1,180,90);
	JSlider theAngleSliderAngleB = new JSlider (1,180,90);
	JSlider theAngleSliderAngleC = new JSlider (1,180,90);
	JLabel theAngleTitleSideA = new JLabel ("Side A Length:");
	JLabel theAngleTitleSideB = new JLabel ("Side B Length:");
	JLabel theAngleTitleSideC = new JLabel ("Side C Length:");
	JLabel theAngleTitleAngleA = new JLabel ("Angle A Degree:");
	JLabel theAngleTitleAngleB = new JLabel ("Angle B Degree:");
	JLabel theAngleTitleAngleC = new JLabel ("Angle C Degree:");
	JTextField theAngleSideAAmount = new JTextField ("50");
	JTextField theAngleSideBAmount = new JTextField ("50");
	JTextField theAngleSideCAmount = new JTextField ("50");
	JTextField theAngleAngleAAmount = new JTextField ("90");
	JTextField theAngleAngleBAmount = new JTextField ("90");
	JTextField theAngleAngleCAmount = new JTextField ("90");
	
	// Calculate and save button for angle page
	JButton theAngleCalculateButton = new JButton ("Calculate!");
	JButton theAngleSaveButton = new JButton ("Save");
	
	// JComponents for the past records menu
	JButton thePastLeftButton = new JButton ("Previous");
	JButton thePastRightButton = new JButton ("Next");
	JTextField thePastCurrentTriangle = new JTextField (thePastPanel.intCurrentTriangleView + "");
	JLabel thePastTriangleNumbers = new JLabel ("of "+thePastPanel.intNumberOfTriangles);
	
	// JComponents for the test menu
	JLabel theTestScore = new JLabel ("Score: 0", SwingConstants.CENTER);
	JLabel theTestQuestion = new JLabel (theTestPanel.strQuestion, SwingConstants.LEFT);
	JTextField theTestAnswerField = new JTextField ();
	JButton theTestSubmitButton = new JButton ("Submit!");
	JTextField theTestNameField = new JTextField();
	JLabel theTestNameLabel = new JLabel ("Name :", SwingConstants. CENTER);
	JLabel theTestFinalScore = new JLabel ("Score: "+theTestPanel.intScore, SwingConstants.CENTER);
	JButton theTestFinalButton = new JButton ("Add To High Score!");
	
	JLabel theTestIntro1 = new JLabel ("Welcome to the Cosine Test!!! You will be given the diagram and either (1) 3 Sides, or (2) 2 sides and 1 angle", SwingConstants.CENTER);
	JLabel theTestIntro2 = new JLabel ("You must find the corresponding Angle/Side Value!!! You can only input rounded answers!", SwingConstants.CENTER);
	JLabel theTestIntro3 = new JLabel ("If your answer is correct, you move on and get a point! If your answer is wrong, your points are counted and submited to the score page!", SwingConstants.CENTER);
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == theSideMenuItem){
			// Remove Save Button
			theSidePanel.remove (theSaveWorkButton);
			
			// Go to Side Menu
			theFrame.setContentPane(theSidePanel);
			theFrame.pack();
			
		} else if (evt.getSource() == theAngleMenuItem){
			// Remove Save Buttons
			theSidePanel.remove (theSaveWorkButton);
			
			// Go to Angle Menu
			theFrame.setContentPane(theAnglePanel);
			theFrame.pack();
			
		} else if (evt.getSource() == theASideAmount){
			// Get the Values they enter for Side A
			try{
				theSidePanel.intSideA = Integer.parseInt(theASideAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			
			// Put it in the slider
			theSideSliderSideA.setValue (theSidePanel.intSideA);
			
		} else if (evt.getSource() == theBSideAmount){
			// Get the Values they enter for Side B
			try{
				theSidePanel.intSideB = Integer.parseInt(theBSideAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			// Put it in the slider
			theSideSliderSideB.setValue (theSidePanel.intSideB);
			
		} else if (evt.getSource() == theCSideAmount){
			// Get the Values they enter for Side C
			try{
				theSidePanel.intSideC = Integer.parseInt(theCSideAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			// Put it in the slider
			theSideSliderSideC.setValue (theSidePanel.intSideC);
			
		} else if (evt.getSource() == theCalculateButton){			
			// Make sure 1 of the angles are selected
			if (theSideAngleAButton.isSelected()){
				theSidePanel.strWhichAngle = "Angle A";
				theSidePanel.blnAngleSelected = true;
			}else if (theSideAngleBButton.isSelected()){
				theSidePanel.strWhichAngle = "Angle B";
				theSidePanel.blnAngleSelected = true;
			}else if (theSideAngleCButton.isSelected()){
				theSidePanel.strWhichAngle = "Angle C";
				theSidePanel.blnAngleSelected = true;
			}
			
			if (theSidePanel.blnAngleSelected == true){
				// Get information of the slides from all the JTextAreas
				try{
					theSidePanel.intSideA = Integer.parseInt(theASideAmount.getText());
					theSidePanel.intSideB = Integer.parseInt(theBSideAmount.getText());
					theSidePanel.intSideC = Integer.parseInt(theCSideAmount.getText());
				}catch(NumberFormatException e){
					System.out.println ("PLEASE ENTER NUMBERS ONLY");
				}
				
				theSidePanel.blnRepaintSides = true;
						
				// Add the Calculations (Calculate all 3 Angles);
				theSidePanel.dblAngleAnswerA =  (Math.round (((Math.acos ((Math.pow (theSidePanel.intSideB, 2.0) + Math.pow (theSidePanel.intSideC, 2.0) - Math.pow (theSidePanel.intSideA,2.0)) / (2.0*theSidePanel.intSideB*theSidePanel.intSideC)))* (180.0/Math.PI))*10.0))/10.0;
				theSidePanel.dblAngleAnswerB =  (Math.round (((Math.acos ((Math.pow (theSidePanel.intSideC, 2.0) + Math.pow (theSidePanel.intSideA, 2.0) - Math.pow (theSidePanel.intSideB,2.0)) / (2.0*theSidePanel.intSideC*theSidePanel.intSideA)))* (180.0/Math.PI))*10.0))/10.0;
				theSidePanel.dblAngleAnswerC =  (Math.round (((Math.acos ((Math.pow (theSidePanel.intSideA, 2.0) + Math.pow (theSidePanel.intSideB, 2.0) - Math.pow (theSidePanel.intSideC,2.0)) / (2.0*theSidePanel.intSideA*theSidePanel.intSideB)))* (180.0/Math.PI))*10.0))/10.0;
				
				// Add Save Button!
				theSidePanel.add (theSaveWorkButton);
			}			
			
			// Enable the save button
			theSaveWorkButton.setEnabled (true);
			
		} else if (evt.getSource() == theTimer){
			// Timer!
			// Only repaint with the timer if they click calculate (when it's done, it'll make the boolean false)
			if (theSidePanel.blnRepaintSides == true || theAnglePanel.blnRepaintSides == true){
				theSidePanel.repaint();
				theAnglePanel.repaint();
			}
			
		} else if (evt.getSource() == theAngleSideAButton){
			// Indicate that this side has been selected
			theAnglePanel.strWhichAngle = "Side A";
			
			// Remove all Previous Sliders, Labels, and TextAreas
			theAnglePanel.remove(theAngleTitleSideA);
			theAnglePanel.remove(theAngleSliderSideA);
			theAnglePanel.remove(theAngleSideAAmount);
			theAnglePanel.remove(theAngleTitleAngleB);
			theAnglePanel.remove(theAngleSliderAngleB);
			theAnglePanel.remove(theAngleAngleBAmount);
			theAnglePanel.remove(theAngleTitleAngleC);
			theAnglePanel.remove(theAngleSliderAngleC);
			theAnglePanel.remove(theAngleAngleCAmount);
			
			// Add the Sliders, Labels, and TextAreas
			theAnglePanel.add(theAngleTitleAngleA);
			theAnglePanel.add(theAngleSliderAngleA);
			theAnglePanel.add(theAngleAngleAAmount);			
			theAnglePanel.add(theAngleTitleSideB);
			theAnglePanel.add(theAngleSliderSideB);
			theAnglePanel.add(theAngleSideBAmount);
			theAnglePanel.add(theAngleTitleSideC);
			theAnglePanel.add(theAngleSliderSideC);
			theAnglePanel.add(theAngleSideCAmount);
			
			theAnglePanel.remove (theAngleSaveButton);
			theAnglePanel.remove (theAngleSaveButton);
			theAnglePanel.add (theAngleCalculateButton);
			theFrame.setContentPane (theAnglePanel);
			
		}else if (evt.getSource() == theAngleSideBButton){
			// Indicate that this side has been selected
			theAnglePanel.strWhichAngle = "Side B";
			
			// Remove all Previous Sliders, Labels, and TextAreas
			theAnglePanel.remove(theAngleTitleSideB);
			theAnglePanel.remove(theAngleSliderSideB);
			theAnglePanel.remove(theAngleSideBAmount);
			theAnglePanel.remove(theAngleTitleAngleA);
			theAnglePanel.remove(theAngleSliderAngleA);
			theAnglePanel.remove(theAngleAngleAAmount);
			theAnglePanel.remove(theAngleTitleAngleC);
			theAnglePanel.remove(theAngleSliderAngleC);
			theAnglePanel.remove(theAngleAngleCAmount);
			
			// Add the Sliders, Labels, and TextAreas
			theAnglePanel.add(theAngleTitleSideA);
			theAnglePanel.add(theAngleSliderSideA);
			theAnglePanel.add(theAngleSideAAmount);
			theAnglePanel.add(theAngleTitleAngleB);
			theAnglePanel.add(theAngleSliderAngleB);
			theAnglePanel.add(theAngleAngleBAmount);	
			theAnglePanel.add(theAngleTitleSideC);
			theAnglePanel.add(theAngleSliderSideC);
			theAnglePanel.add(theAngleSideCAmount);
			
			theAnglePanel.add (theAngleCalculateButton);
			theFrame.setContentPane (theAnglePanel);
			
		}else if (evt.getSource () == theAngleSideCButton){
			// Indicate that this side has been selected
			theAnglePanel.strWhichAngle = "Side C";
			
			// Remove all Previous Sliders, Labels, and TextAreas
			theAnglePanel.remove(theAngleTitleSideC);
			theAnglePanel.remove(theAngleSliderSideC);
			theAnglePanel.remove(theAngleSideCAmount);
			theAnglePanel.remove(theAngleTitleAngleA);
			theAnglePanel.remove(theAngleSliderAngleA);
			theAnglePanel.remove(theAngleAngleAAmount);
			theAnglePanel.remove(theAngleTitleAngleB);
			theAnglePanel.remove(theAngleSliderAngleB);
			theAnglePanel.remove(theAngleAngleBAmount);
			
			// Add the Sliders, Labels, and TextAreas
			theAnglePanel.add(theAngleTitleSideA);
			theAnglePanel.add(theAngleSliderSideA);
			theAnglePanel.add(theAngleSideAAmount);
			theAnglePanel.add(theAngleTitleSideB);
			theAnglePanel.add(theAngleSliderSideB);
			theAnglePanel.add(theAngleSideBAmount);
			theAnglePanel.add(theAngleTitleAngleC);
			theAnglePanel.add(theAngleSliderAngleC);
			theAnglePanel.add(theAngleAngleCAmount);
			
			theAnglePanel.remove (theAngleSaveButton);
			theAnglePanel.add (theAngleCalculateButton);
			theFrame.setContentPane (theAnglePanel);
			
			
		} else if (evt.getSource() == theAngleSideAAmount){
			// Get the Values they enter for Side A
			try{
				theAnglePanel.intSideA = Integer.parseInt(theAngleSideAAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			// Put it in the slider
			theAngleSliderSideA.setValue (theAnglePanel.intSideA);
			
		} else if (evt.getSource() == theAngleSideBAmount){
			// Get the Values they enter for Side B
			try{
				theAnglePanel.intSideB = Integer.parseInt(theAngleSideBAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			// Put it in the slider
			theAngleSliderSideB.setValue (theAnglePanel.intSideB);
			
		} else if (evt.getSource() == theAngleSideCAmount){
			// Get the Values they enter for Side C
			try{
				theAnglePanel.intSideC = Integer.parseInt(theAngleSideCAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			// Put it in the slider
			theAngleSliderSideC.setValue (theAnglePanel.intSideC);
			
		} else if (evt.getSource() == theAngleAngleAAmount){
			// Get the Values they enter for Angle A
			try{
				theAnglePanel.intAngleA = Integer.parseInt(theAngleAngleAAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			// Put it in the slider
			theAngleSliderAngleA.setValue (theAnglePanel.intAngleA);
			
		} else if (evt.getSource() == theAngleAngleBAmount){
			// Get the Values they enter for Angle B
			try{
				theAnglePanel.intAngleB = Integer.parseInt(theAngleAngleBAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			// Put it in the slider
			theAngleSliderAngleB.setValue (theAnglePanel.intAngleB);
			
		} else if (evt.getSource() == theAngleAngleCAmount){
			// Get the Values they enter for Angle C
			try{
				theAnglePanel.intAngleC = Integer.parseInt(theAngleAngleCAmount.getText());
			}catch(NumberFormatException e){
				System.out.println ("PLEASE ENTER NUMBERS ONLY");
			}
			// Put it in the slider
			theAngleSliderAngleC.setValue (theAnglePanel.intAngleC);
			
		} else if (evt.getSource() == theAngleCalculateButton){					
			// Enable the save button after clicking calculate
			theAngleSaveButton.setEnabled(true);
			
			// Find all 3 Sides to fit in the later equations
			if (theAnglePanel.strWhichAngle.equalsIgnoreCase ("Side A")){
				// If user is asking for side A
				System.out.println ("1");
				try{
					theAnglePanel.intSideB = Integer.parseInt(theAngleSideBAmount.getText());
					theAnglePanel.intSideC = Integer.parseInt(theAngleSideCAmount.getText());
					theAnglePanel.intAngleA = Integer.parseInt (theAngleAngleAAmount.getText());
				}catch (NumberFormatException e){
					System.out.println ("PLEASE ENTER NUMBERS");
				}
				
				theAnglePanel.intSideA = (int) Math.sqrt (Math.pow (theAnglePanel.intSideB, 2) + Math.pow (theAnglePanel.intSideC, 2) - 2 * theAnglePanel.intSideB * theAnglePanel.intSideC * (Math.cos(theAnglePanel.intAngleA * (Math.PI/180))));
				
			} else if (theAnglePanel.strWhichAngle.equalsIgnoreCase ("Side B")){
				// If user is asking for side B
				System.out.println ("2");
				try{
					theAnglePanel.intSideA = Integer.parseInt(theAngleSideAAmount.getText());
					theAnglePanel.intSideC = Integer.parseInt(theAngleSideCAmount.getText());
					theAnglePanel.intAngleB = Integer.parseInt (theAngleAngleBAmount.getText());
				}catch (NumberFormatException e){
					System.out.println ("PLEASE ENTER NUMBERS");
				}
				
				theAnglePanel.intSideB = (int) Math.sqrt (Math.pow (theAnglePanel.intSideA, 2) + Math.pow (theAnglePanel.intSideC, 2) - 2 * theAnglePanel.intSideA * theAnglePanel.intSideC * (Math.cos(theAnglePanel.intAngleB * (Math.PI/180))));
			
			} else if (theAnglePanel.strWhichAngle.equalsIgnoreCase ("Side C")){
				// If user is asking for side C
				System.out.println ("3");
				try{
					theAnglePanel.intSideA = Integer.parseInt(theAngleSideAAmount.getText());
					theAnglePanel.intSideB = Integer.parseInt(theAngleSideBAmount.getText());
					theAnglePanel.intAngleC = Integer.parseInt (theAngleAngleCAmount.getText());
				}catch (NumberFormatException e){
					System.out.println ("PLEASE ENTER NUMBERS");
				}
				theAnglePanel.intSideC = (int) Math.sqrt (Math.pow (theAnglePanel.intSideA, 2) + Math.pow (theAnglePanel.intSideB, 2) - 2 * theAnglePanel.intSideA * theAnglePanel.intSideB * (Math.cos(theAnglePanel.intAngleC * (Math.PI/180))));
			}	
			
			theAnglePanel.blnRepaintSides = true;
			
			// Add the Calculations (Calculate all 3 Angles);
			theAnglePanel.dblAngleAnswerA =  (Math.round (((Math.acos ((Math.pow (theAnglePanel.intSideB, 2.0) + Math.pow (theAnglePanel.intSideC, 2.0) - Math.pow (theAnglePanel.intSideA,2.0)) / (2.0*theAnglePanel.intSideB*theAnglePanel.intSideC)))* (180.0/Math.PI))*10.0))/10.0;
			theAnglePanel.dblAngleAnswerB =  (Math.round (((Math.acos ((Math.pow (theAnglePanel.intSideC, 2.0) + Math.pow (theAnglePanel.intSideA, 2.0) - Math.pow (theAnglePanel.intSideB,2.0)) / (2.0*theAnglePanel.intSideC*theAnglePanel.intSideA)))* (180.0/Math.PI))*10.0))/10.0;
			theAnglePanel.dblAngleAnswerC =  (Math.round (((Math.acos ((Math.pow (theAnglePanel.intSideA, 2.0) + Math.pow (theAnglePanel.intSideB, 2.0) - Math.pow (theAnglePanel.intSideC,2.0)) / (2.0*theAnglePanel.intSideA*theAnglePanel.intSideB)))* (180.0/Math.PI))*10.0))/10.0;
			
			// Add Save Button!
			theAnglePanel.add (theAngleSaveButton);
			
		} else if (evt.getSource() == theSaveWorkButton){
			// If the triangle is real, and they click save, save the details into a file
			if (theSidePanel.blnRealTriangle == true){
				// Opening File
				PrintWriter theSaveFile = null;
				try{
					theSaveFile = new PrintWriter (new FileWriter ("SavedWork.Txt", true));
				}catch (IOException e){
					System.out.println ("ERROR OPENING FILE");
				}
				
				if (theSaveFile != null){
					// Put Information into the file
					theSaveFile.println (theSidePanel.intSideA);
					theSaveFile.println (theSidePanel.intSideB);
					theSaveFile.println (theSidePanel.intSideC);
					theSaveFile.println (theSidePanel.dblAngleAnswerA);
					theSaveFile.println (theSidePanel.dblAngleAnswerB);
					theSaveFile.println (theSidePanel.dblAngleAnswerC);
				}
				
				// Close File
				theSaveFile.close();
				
				// Disable Save button so they do it once
				theSaveWorkButton.setEnabled (false);
			}
		} else if (evt.getSource () == theAngleSaveButton){
			// If the triangle is real, and they click save, save the details into a file
			if (theAnglePanel.blnRealTriangle == true){
				// Opening File
				PrintWriter theSaveFile = null;
				try{
					theSaveFile = new PrintWriter (new FileWriter ("SavedWork.Txt", true));
				}catch (IOException e){
					System.out.println ("ERROR OPENING FILE");
				}
				
				if (theSaveFile != null){
					// Put Information into the file
					theSaveFile.println (theAnglePanel.intSideA);
					theSaveFile.println (theAnglePanel.intSideB);
					theSaveFile.println (theAnglePanel.intSideC);
					theSaveFile.println (theAnglePanel.dblAngleAnswerA);
					theSaveFile.println (theAnglePanel.dblAngleAnswerB);
					theSaveFile.println (theAnglePanel.dblAngleAnswerC);
				}
				
				// Close File
				theSaveFile.close();
				
				// Disable the save button
				theAngleSaveButton.setEnabled (false);
				
			
			}
		} else if (evt.getSource() == thePastCurrentTriangle){
			// The Current Triangle Indicator and if they type in which number, then change it (only within the possible number of triangles)
			if (Integer.parseInt(thePastCurrentTriangle.getText()) <= thePastPanel.intNumberOfTriangles && Integer.parseInt(thePastCurrentTriangle.getText()) > 0){
				try{
					thePastPanel.intCurrentTriangleView = Integer.parseInt(thePastCurrentTriangle.getText());
				}catch (NumberFormatException e){
					System.out.println ("NUMBERS PLZ");
				}
			}else{
				thePastCurrentTriangle.setText (thePastPanel.intCurrentTriangleView+"");
				System.out.println ("Print within the possible triangles");
			}
			
			thePastPanel.repaint();
			
		} else if (evt.getSource() == thePastLeftButton){
			// Previous Button will change the triangle number by -1 if it is within the range
			if (thePastPanel.intCurrentTriangleView - 1 > 0){
				thePastPanel.intCurrentTriangleView--;
				thePastCurrentTriangle.setText (thePastPanel.intCurrentTriangleView+"");
			}
			thePastPanel.repaint();
			
		} else if (evt.getSource() == thePastRightButton){
			// Next Button
			if (thePastPanel.intCurrentTriangleView + 1 <= thePastPanel.intNumberOfTriangles){
				thePastPanel.intCurrentTriangleView++;
				thePastCurrentTriangle.setText (thePastPanel.intCurrentTriangleView+"");
			}
			
			// Repaint
			thePastPanel.repaint();
			
		} else if (evt.getSource() == theTestSubmitButton){			
			// In the test Panel, if they click the submit button
			if (Integer.parseInt(theTestAnswerField.getText()) == theTestPanel.intAnswer){
				// Add the Score
				System.out.println ("Correct");
				theTestPanel.intScore++;
				theTestScore.setText("Score: "+theTestPanel.intScore);
				
				// Make a True Triangle (since each side will be at least 70% of the previous side, it will be impossible to create an impossible triangle)
			theTestPanel.intSideA = (int)(Math.random()*90)+10;
			theTestPanel.intSideB = (int)((Math.random()*(theTestPanel.intSideA*0.3)) + (theTestPanel.intSideA*0.7));
			theTestPanel.intSideC = (int)((Math.random()*(theTestPanel.intSideB*0.3)) + (theTestPanel.intSideB*0.7));
			
			//System.out.println (theTestPanel.intSideA + " | " + theTestPanel.intSideB + " | " + theTestPanel.intSideC);
			
			// Mix up the Sides
			int intSideRandomizer;
			intSideRandomizer = (int)(Math.random()*5)+1;
			int intTemp = 0;
			if (intSideRandomizer == 1){
				// ACB
				intTemp = theTestPanel.intSideB;
				theTestPanel.intSideB = theTestPanel.intSideC;
				theTestPanel.intSideC = intTemp;
			} else if (intSideRandomizer == 2){
				// BCA
				intTemp = theTestPanel.intSideA;
				theTestPanel.intSideA = theTestPanel.intSideB;
				theTestPanel.intSideB = theTestPanel.intSideC;
				theTestPanel.intSideC = intTemp;
			} else if (intSideRandomizer == 3){
				// BAC
				intTemp = theTestPanel.intSideA;
				theTestPanel.intSideA = theTestPanel.intSideB;
				theTestPanel.intSideB = intTemp;
			} else if (intSideRandomizer == 4){
				// CAB
				intTemp = theTestPanel.intSideA;
				theTestPanel.intSideA = theTestPanel.intSideC;
				theTestPanel.intSideC = theTestPanel.intSideB;
				theTestPanel.intSideB = intTemp;
			} else if (intSideRandomizer == 5){
				// CBA
				intTemp = theTestPanel.intSideA;
				theTestPanel.intSideA = theTestPanel.intSideC;
				theTestPanel.intSideC = intTemp;
			}		
			
			// Do the Angles
			theTestPanel.dblAngleAnswerA =  (Math.round (((Math.acos ((Math.pow (theTestPanel.intSideB, 2.0) + Math.pow (theTestPanel.intSideC, 2.0) - Math.pow (theTestPanel.intSideA,2.0)) / (2.0*theTestPanel.intSideB*theTestPanel.intSideC)))* (180.0/Math.PI))*10.0))/10.0;
			theTestPanel.dblAngleAnswerB =  (Math.round (((Math.acos ((Math.pow (theTestPanel.intSideC, 2.0) + Math.pow (theTestPanel.intSideA, 2.0) - Math.pow (theTestPanel.intSideB,2.0)) / (2.0*theTestPanel.intSideC*theTestPanel.intSideA)))* (180.0/Math.PI))*10.0))/10.0;
			theTestPanel.dblAngleAnswerC =  (Math.round (((Math.acos ((Math.pow (theTestPanel.intSideA, 2.0) + Math.pow (theTestPanel.intSideB, 2.0) - Math.pow (theTestPanel.intSideC,2.0)) / (2.0*theTestPanel.intSideA*theTestPanel.intSideB)))* (180.0/Math.PI))*10.0))/10.0;
			
			
			// Ask for Side ABC or Angle ABC (1=SideA, 2=SideB, 3=SideC, 4=AngleA, 5=AngleB, 6=AngleC)
			theTestPanel.intAskAngleOrSide = (int)(Math.random()*6)+1;
			
			
			// Set Information and Question
			if (theTestPanel.intAskAngleOrSide == 1){
				// Side A
				theTestPanel.strQuestion = "Side A";
				theTestPanel.strInformation1 = "Angle A: "+theTestPanel.dblAngleAnswerA + " Degrees";
				theTestPanel.strInformation2 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = theTestPanel.intSideA;
				
			} else if (theTestPanel.intAskAngleOrSide == 2){
				// Side B
				theTestPanel.strQuestion = "Side B";
				theTestPanel.strInformation1 = "Angle B: "+theTestPanel.dblAngleAnswerB + " Degrees";
				theTestPanel.strInformation2 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = theTestPanel.intSideB;
				
			} else if (theTestPanel.intAskAngleOrSide == 3){
				// Side C
				theTestPanel.strQuestion = "Side C";
				theTestPanel.strInformation1 = "Angle C: "+theTestPanel.dblAngleAnswerC + " Degrees";
				theTestPanel.strInformation2 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation3 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.intAnswer = theTestPanel.intSideC;
				
			} else if (theTestPanel.intAskAngleOrSide == 4){
				// Angle A
				theTestPanel.strQuestion = "Angle A";
				theTestPanel.strInformation1 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation2 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = (int)Math.round (theTestPanel.dblAngleAnswerA);
				
			} else if (theTestPanel.intAskAngleOrSide == 5){
				// Angle B
				theTestPanel.strQuestion = "Angle B";
				theTestPanel.strInformation1 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation2 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = (int)Math.round (theTestPanel.dblAngleAnswerB);
				
			} else if (theTestPanel.intAskAngleOrSide == 6){
				// Angle C
				theTestPanel.strQuestion = "Angle C";
				theTestPanel.strInformation1 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation2 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = (int)Math.round (theTestPanel.dblAngleAnswerC);	
			}
			// Add The Question
			theTestQuestion.setText (theTestPanel.strQuestion+":");
			
			theTestPanel.repaint();
			}else{
				// Remove All the JComponents
				theTestPanel.removeAll();
				
				// Add the Stuff
				theTestPanel.repaint();
				theTestPanel.blnCorrect = false;
				theTestFinalScore.setText ("Score: "+theTestPanel.intScore);
				//theTestPanel.add(theTestFinalScore);
				theTestPanel.add(theTestNameLabel);
				theTestPanel.add(theTestNameField);
				theTestPanel.add (theTestFinalButton);
				
				// Set the Content Pane and Pack
				theFrame.setContentPane (theTestPanel);
				theFrame.pack();				
			}
		} else if (evt.getSource() == theTestFinalButton){
			// If they submit the score to the high score file
			// Open High Score File
			PrintWriter HighScores=null;
			try{
				HighScores = new PrintWriter (new FileWriter ("HighScores.Txt", true));
			}catch (IOException e){
				System.out.println ("ERROR OPENING FILE");
			}
			
			if (HighScores != null){
				// Put Information into the file
				HighScores.println (theTestNameField.getText());
				HighScores.println (theTestPanel.intScore);
			}
			
			// Close File
			HighScores.close();
			
			// Disable after 1 time use
			theTestFinalButton.setEnabled(false);
		}else if (evt.getSource() == theTestMenuItem){
			// Enable the score submit button
			theTestFinalButton.setEnabled (true); 
				
			// Open the Test Menu
			// Restart the score and the test if they redo the stuff
			theTestPanel.intScore = 0;
			theTestScore.setText ("Score :" + theTestPanel.intScore);
			theTestAnswerField.setText ("");
			theTestPanel.blnCorrect = true;
			
			//The Test Menu
			theTestPanel.repaint();
			
			// Remove the old JComponents
			theTestPanel.removeAll();
			// Add the JComponents
			theTestPanel.add(theTestIntro1);
			theTestPanel.add(theTestIntro2);
			theTestPanel.add(theTestIntro3);
			theTestPanel.add(theTestScore);
			theTestPanel.add (theTestQuestion);
			theTestPanel.add(theTestAnswerField);
			theTestPanel.add(theTestSubmitButton);
				
			// Make a True Triangle (since each side will be at least 70% of the previous side, it will be impossible to create an impossible triangle)
			theTestPanel.intSideA = (int)(Math.random()*90)+10;
			theTestPanel.intSideB = (int)((Math.random()*(theTestPanel.intSideA*0.3)) + (theTestPanel.intSideA*0.7));
			theTestPanel.intSideC = (int)((Math.random()*(theTestPanel.intSideB*0.3)) + (theTestPanel.intSideB*0.7));
			
			//System.out.println (theTestPanel.intSideA + " | " + theTestPanel.intSideB + " | " + theTestPanel.intSideC);
			
			// Mix up the Sides
			int intSideRandomizer;
			intSideRandomizer = (int)(Math.random()*5)+1;
			int intTemp = 0;
			if (intSideRandomizer == 1){
				// ACB
				intTemp = theTestPanel.intSideB;
				theTestPanel.intSideB = theTestPanel.intSideC;
				theTestPanel.intSideC = intTemp;
			} else if (intSideRandomizer == 2){
				// BCA
				intTemp = theTestPanel.intSideA;
				theTestPanel.intSideA = theTestPanel.intSideB;
				theTestPanel.intSideB = theTestPanel.intSideC;
				theTestPanel.intSideC = intTemp;
			} else if (intSideRandomizer == 3){
				// BAC
				intTemp = theTestPanel.intSideA;
				theTestPanel.intSideA = theTestPanel.intSideB;
				theTestPanel.intSideB = intTemp;
			} else if (intSideRandomizer == 4){
				// CAB
				intTemp = theTestPanel.intSideA;
				theTestPanel.intSideA = theTestPanel.intSideC;
				theTestPanel.intSideC = theTestPanel.intSideB;
				theTestPanel.intSideB = intTemp;
			} else if (intSideRandomizer == 5){
				// CBA
				intTemp = theTestPanel.intSideA;
				theTestPanel.intSideA = theTestPanel.intSideC;
				theTestPanel.intSideC = intTemp;
			}		
			
			// Do the Angles
			theTestPanel.dblAngleAnswerA =  (Math.round (((Math.acos ((Math.pow (theTestPanel.intSideB, 2.0) + Math.pow (theTestPanel.intSideC, 2.0) - Math.pow (theTestPanel.intSideA,2.0)) / (2.0*theTestPanel.intSideB*theTestPanel.intSideC)))* (180.0/Math.PI))*10.0))/10.0;
			theTestPanel.dblAngleAnswerB =  (Math.round (((Math.acos ((Math.pow (theTestPanel.intSideC, 2.0) + Math.pow (theTestPanel.intSideA, 2.0) - Math.pow (theTestPanel.intSideB,2.0)) / (2.0*theTestPanel.intSideC*theTestPanel.intSideA)))* (180.0/Math.PI))*10.0))/10.0;
			theTestPanel.dblAngleAnswerC =  (Math.round (((Math.acos ((Math.pow (theTestPanel.intSideA, 2.0) + Math.pow (theTestPanel.intSideB, 2.0) - Math.pow (theTestPanel.intSideC,2.0)) / (2.0*theTestPanel.intSideA*theTestPanel.intSideB)))* (180.0/Math.PI))*10.0))/10.0;
			
			
			// Ask for Side ABC or Angle ABC (1=SideA, 2=SideB, 3=SideC, 4=AngleA, 5=AngleB, 6=AngleC)
			theTestPanel.intAskAngleOrSide = (int)(Math.random()*6)+1;
			
			
			// Set Information and Question
			if (theTestPanel.intAskAngleOrSide == 1){
				// Side A
				theTestPanel.strQuestion = "Side A";
				theTestPanel.strInformation1 = "Angle A: "+theTestPanel.dblAngleAnswerA + " Degrees";
				theTestPanel.strInformation2 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = theTestPanel.intSideA;
				
			} else if (theTestPanel.intAskAngleOrSide == 2){
				// Side B
				theTestPanel.strQuestion = "Side B";
				theTestPanel.strInformation1 = "Angle B: "+theTestPanel.dblAngleAnswerB + " Degrees";
				theTestPanel.strInformation2 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = theTestPanel.intSideB;
				
			} else if (theTestPanel.intAskAngleOrSide == 3){
				// Side C
				theTestPanel.strQuestion = "Side C";
				theTestPanel.strInformation1 = "Angle C: "+theTestPanel.dblAngleAnswerC + " Degrees";
				theTestPanel.strInformation2 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation3 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.intAnswer = theTestPanel.intSideC;
				
			} else if (theTestPanel.intAskAngleOrSide == 4){
				// Angle A
				theTestPanel.strQuestion = "Angle A";
				theTestPanel.strInformation1 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation2 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = (int)Math.round (theTestPanel.dblAngleAnswerA);
				
			} else if (theTestPanel.intAskAngleOrSide == 5){
				// Angle B
				theTestPanel.strQuestion = "Angle B";
				theTestPanel.strInformation1 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation2 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = (int)Math.round (theTestPanel.dblAngleAnswerB);
				
			} else if (theTestPanel.intAskAngleOrSide == 6){
				// Angle C
				theTestPanel.strQuestion = "Angle C";
				theTestPanel.strInformation1 = "Side A: "+theTestPanel.intSideA;
				theTestPanel.strInformation2 = "Side B: "+theTestPanel.intSideB;
				theTestPanel.strInformation3 = "Side C: "+theTestPanel.intSideC;
				theTestPanel.intAnswer = (int)Math.round (theTestPanel.dblAngleAnswerC);	
			}
			// Add The Question
			theTestQuestion.setText (theTestPanel.strQuestion+":");
			
			//The Test Menu
			theFrame.setContentPane(theTestPanel);
			theFrame.pack();
		} else if (evt.getSource() == theTestScoreMenuItem){
			// High Score Menu
			// Repaint Menu
			theHighPanel.repaint();
			// Set it as the pane
			theFrame.setContentPane (theHighPanel);
			theFrame.pack();
		}
	}
	
	public void menuCanceled (MenuEvent evt){
		System.out.println ("MENU CANCELLED");
	}
	
	public void menuDeselected (MenuEvent evt){
		
	}
	
	public void menuSelected (MenuEvent evt){		
		// Remove Save Buttons
		theSidePanel.remove (theSaveWorkButton);
		
		if (evt.getSource() == theHelpMenu){
			theHelpMenu.repaint();
			theFrame.setContentPane(theHelpPanel);
			theFrame.pack();
			
		} else if (evt.getSource() == theAboutMenu){
			theFrame.setContentPane(theAboutPanel);
			theFrame.pack();
			
		} else if (evt.getSource() == theTestingMenu){
			
			
		} else if (evt.getSource() == thePastMenu){
			// Open the Past Stuff Menu
			theFrame.setContentPane(thePastPanel);
			theFrame.pack();
			
			// Count How Many Triangles There Are In The Saved TXT File
			BufferedReader txtSaved = null;
			// Open File
			try {
				txtSaved = new BufferedReader (new FileReader ("SavedWork.txt"));
			}catch (FileNotFoundException e){
				System.out.println ("FILE NOT FOUND ERROR");
				thePastPanel.blnIssueReading = true;
			}
			
			// Count how many there are
			int intCountTriangles = 0;
			String strCount = "";
			
			if (txtSaved != null){
				try{
					while (strCount != null){
						strCount = txtSaved.readLine();
						intCountTriangles++;
					}
					intCountTriangles = intCountTriangles - 1;
				}catch (IOException e){
					System.out.println ("ERROR READING FROM FILE");
					thePastPanel.blnIssueReading = true;
				}
			}
			
			// Close File
			try{
				txtSaved.close();
			}catch (IOException e){
				System.out.println ("ERROR CLOSING FILE");
				thePastPanel.blnIssueReading = true;
			}
			
			// Divide by 6 since there are 6 lines of data per triangles
			intCountTriangles = intCountTriangles / 6;
			
			// Set the intNumberOfTriangles variable and JLabel to be equal to the number of traingles
			thePastPanel.intNumberOfTriangles = intCountTriangles;
			thePastTriangleNumbers.setText ("of "+intCountTriangles);
			
			// Load all the data into an array [number of triangles][6 pieces of data]
			thePastPanel.strTriangles = new String[intCountTriangles][6];
			
			// Open File
			try {
				txtSaved = new BufferedReader (new FileReader ("SavedWork.txt"));
			}catch (FileNotFoundException e){
				System.out.println ("FILE NOT FOUND ERROR");
				thePastPanel.blnIssueReading = true;
			}
			
			// Load Data into Array
			int intCount;
			int intCount1;
			if (txtSaved != null){
				try{
					for (intCount = 0; intCount < intCountTriangles; intCount++){
						for (intCount1 = 0; intCount1 < 6; intCount1++){
							thePastPanel.strTriangles[intCount][intCount1] = txtSaved.readLine();
						}
					}
					intCountTriangles = intCountTriangles - 1;
				}catch (IOException e){
					System.out.println ("ERROR READING FROM FILE");
					thePastPanel.blnIssueReading = true;
				}
			}
			
			// Close File
			try{
				txtSaved.close();
			}catch (IOException e){
				System.out.println ("ERROR CLOSING FILE");
				thePastPanel.blnIssueReading = true;
			}			
		}
	}
	
	public void stateChanged (ChangeEvent evt){
		if (evt.getSource() == theSideSliderSideA){
			// For Side Panel Side A Slider
			theSidePanel.intSideA = theSideSliderSideA.getValue();
			theASideAmount.setText (theSidePanel.intSideA+"");
			
		} else if (evt.getSource() == theSideSliderSideB){
			// For Side Panel Side B Slider
			theSidePanel.intSideB = theSideSliderSideB.getValue();
			theBSideAmount.setText (theSidePanel.intSideB+"");
			
		} else if (evt.getSource() == theSideSliderSideC){
			// For Side Panel Side C Slider
			theSidePanel.intSideC = theSideSliderSideC.getValue();
			theCSideAmount.setText (theSidePanel.intSideC+"");
			
		} else if (evt.getSource() == theAngleSliderSideA){
			// For Angle Panel Side A Slider
			theAnglePanel.intSideA = theAngleSliderSideA.getValue();
			theAngleSideAAmount.setText (theAnglePanel.intSideA+"");
			
		} else if (evt.getSource() == theAngleSliderSideB){
			// For Angle Panel Side B Slider
			theAnglePanel.intSideB = theAngleSliderSideB.getValue();
			theAngleSideBAmount.setText (theAnglePanel.intSideB+"");
			
		} else if (evt.getSource() == theAngleSliderSideC){
			// For Angle Panel Side C Slider
			theAnglePanel.intSideC = theAngleSliderSideC.getValue();
			theAngleSideCAmount.setText (theAnglePanel.intSideC+"");
			
		} else if (evt.getSource() == theAngleSliderAngleA){
			// For Angle Panel Angle A Slider
			theAnglePanel.intAngleA = theAngleSliderAngleA.getValue();
			theAngleAngleAAmount.setText (theAnglePanel.intAngleA+"");
			
		} else if (evt.getSource() == theAngleSliderAngleB){
			// For Angle Panel Side B Slider
			theAnglePanel.intAngleB = theAngleSliderAngleB.getValue();
			theAngleAngleBAmount.setText (theAnglePanel.intAngleB+"");
			
		} else if (evt.getSource() == theAngleSliderAngleC){
			// For Angle Panel Side C Slider
			theAnglePanel.intAngleC = theAngleSliderAngleC.getValue();
			theAngleAngleCAmount.setText (theAnglePanel.intAngleC+"");
		}
	}
	
	// Constructor
	public CosineLaw(){
		// Add the Menu Bar, MenuItems, and their listeners (CHANGE THEIR DIMESNIONS LATER)
		theFrame.setJMenuBar (theBar);
		theBar.add (theCalculateMenu);
		theCalculateMenu.add (theSideMenuItem);
		theCalculateMenu.add (theAngleMenuItem);
		theTestingMenu.add(theTestMenuItem);
		theTestingMenu.add(theTestScoreMenuItem);
		theBar.add (theTestingMenu);
		theBar.add (thePastMenu);
		theBar.add (theHelpMenu);
		theBar.add (theAboutMenu);
		
		theSideMenuItem.addActionListener (this);
		theAngleMenuItem.addActionListener (this);
		theTestMenuItem.addActionListener (this);
		theTestScoreMenuItem.addActionListener (this);
		theTestingMenu.addMenuListener (this);
		thePastMenu.addMenuListener (this);
		theHelpMenu.addMenuListener (this);
		theAboutMenu.addMenuListener (this);
		
		// Side Panel
		// Set the Side Panel Dimensions
		theSidePanel.setPreferredSize (new Dimension (960, 540));
		theSidePanel.setLayout(null);
		
		// Text and Images
		JLabel theIntro = new JLabel ("Welcome to your friendly Cosine Law Calculator! By using the 3 sides of your triangle, we will find whichever angle you desire!",SwingConstants.CENTER);
		theIntro.setSize(980,60);
		theIntro.setLocation (0,-10);
		theSidePanel.add(theIntro);
		
		// Add Image Description
		theImageDesc.setSize(400,60);
		theImageDesc.setLocation (0,203);
		theSidePanel.add(theImageDesc);
		
		// Side A
		theSideATitle.setSize(300,60);
		theSideATitle.setLocation (400-370,30+230-5);
		theSidePanel.add(theSideATitle);
		
		// Slider for Side A
		theSideSliderSideA.setSize (350,30);
		theSideSliderSideA.setLocation (400-370,75+230-5);
		theSideSliderSideA.addChangeListener (this);
		theSidePanel.add(theSideSliderSideA);
		
		// JTextArea of Side A
		theASideAmount.setSize (100,25);
		theASideAmount.setLocation (487-370,48+230-5);
		theSidePanel.add(theASideAmount);
		theASideAmount.addActionListener (this);
		
		// Side B
		theSideBTitle.setSize(300, 60);
		theSideBTitle.setLocation (400-370, 90+230-5);
		theSidePanel.add(theSideBTitle);
		
		// Slider for Side B
		theSideSliderSideB.setSize (350, 30);
		theSideSliderSideB.setLocation (400-370, 135+230-5);
		theSideSliderSideB.addChangeListener (this);
		theSidePanel.add(theSideSliderSideB);
		
		// JTextArea of Side B
		theBSideAmount.setSize (100, 25);
		theBSideAmount.setLocation (487-370, 108+230-5);
		theSidePanel.add(theBSideAmount);
		theBSideAmount.addActionListener (this);
		
		// Side C
		theSideCTitle.setSize(300, 60);
		theSideCTitle.setLocation (400-370, 150+230-5);
		theSidePanel.add(theSideCTitle);
		
		// Slider for Side C
		theSideSliderSideC.setSize (350, 30);
		theSideSliderSideC.setLocation (400-370, 195+230-5);
		theSideSliderSideC.addChangeListener (this);
		theSidePanel.add(theSideSliderSideC);
		
		// JTextArea of Side C
		theCSideAmount.setSize (100, 25);
		theCSideAmount.setLocation (487-370, 168+230-5);
		theSidePanel.add(theCSideAmount);
		theCSideAmount.addActionListener (this);
		
		// Ask which side they desire
		JLabel theSideAskLabel = new JLabel ("Which Angle Do You Want To Find?", SwingConstants.CENTER);
		theSideAskLabel.setSize (200,30);
		theSideAskLabel.setLocation (400-370+100-25,225+220+5);
		theSidePanel.add(theSideAskLabel);
		
		// Set RadioButton Stuff
		theSideAngleAButton.setSize (100,30);
		theSideAngleAButton.setLocation (400-370,248+220+5);
		theSidePanel.add(theSideAngleAButton);
		
		theSideAngleBButton.setSize (100,30);
		theSideAngleBButton.setLocation (530-370,248+220+5);
		theSidePanel.add(theSideAngleBButton);
		
		theSideAngleCButton.setSize (100,30);
		theSideAngleCButton.setLocation (660-370,248+220+5);
		theSidePanel.add(theSideAngleCButton);
		
		// Group the RadioButtons
		theSideAngleButtonGroup.add (theSideAngleAButton);
		theSideAngleButtonGroup.add (theSideAngleBButton);
		theSideAngleButtonGroup.add (theSideAngleCButton);
		
		// Add A CALCULATE BUTTON!!!
		theCalculateButton.setSize (150,25);
		theCalculateButton.setLocation (125, 290+215);
		theSidePanel.add (theCalculateButton);
		theCalculateButton.addActionListener(this);
		
		// Create a SAVE BUTTON!
		theSaveWorkButton.setSize (120,25);
		theSaveWorkButton.setLocation (810, 49);
		theSaveWorkButton.addActionListener(this);
		
		// 2 Side and 1 Angle Panel
		// Set the size of the panel and layout
		theAnglePanel.setPreferredSize (new Dimension (960, 540));
		theAnglePanel.setLayout(null);
		
		// Introduction
		JLabel the2Side1AngleIntro = new JLabel ("Welcome to your friendly Cosine Law Calculator! Please enter which side you want to find, and fill in the required information!",SwingConstants.CENTER);
		the2Side1AngleIntro.setSize(960,60);
		the2Side1AngleIntro.setLocation (0,-10);
		theAnglePanel.add(the2Side1AngleIntro);
		
		// Add the Example Description
		JLabel theImageDesc1 = new JLabel ("Example of the Diagram", SwingConstants.CENTER);
		theImageDesc1.setSize(300,60);
		theImageDesc1.setLocation (30-370,248+225);
		theAnglePanel.add(theImageDesc1);
		
		// Ask which side they want to find
		JLabel theAskWhichSide = new JLabel ("Which side do you want to find?", SwingConstants.CENTER);
		theAskWhichSide.setSize (350,30);
		theAskWhichSide.setLocation (400-370, 40+225);
		theAnglePanel.add (theAskWhichSide);
		
		// Make the RadioButton and Button Groups to ask which side they want to find
		theAngleSideAButton.setSize (100,30);
		theAngleSideAButton.setLocation (400-370,62+225);
		theAnglePanel.add(theAngleSideAButton);
		theAngleSideAButton.addActionListener(this);
		
		theAngleSideBButton.setSize (100,30);
		theAngleSideBButton.setLocation (530-370,62+225);
		theAnglePanel.add(theAngleSideBButton);
		theAngleSideBButton.addActionListener(this);
		
		theAngleSideCButton.setSize (100,30);
		theAngleSideCButton.setLocation (660-370,62+225);
		theAnglePanel.add(theAngleSideCButton);
		theAngleSideCButton.addActionListener(this);
		
		// Group the RadioButtons
		theAngleSideButtonGroup.add (theAngleSideAButton);
		theAngleSideButtonGroup.add (theAngleSideBButton);
		theAngleSideButtonGroup.add (theAngleSideCButton);
		
		//Make the JSlider, JLabel, and JTextArea for all the angles and sides
		// Side A
		theAngleTitleSideA.setSize(300,60);
		theAngleTitleSideA.setLocation (400-370,90+225-10);		
		
		// Slider for Side A
		theAngleSliderSideA.setSize (350,30);
		theAngleSliderSideA.setLocation (400-370,135+225-10);
		theAngleSliderSideA.addChangeListener (this);
		
		// JTextArea of Side A
		theAngleSideAAmount.setSize (100,25);
		theAngleSideAAmount.setLocation (487-370,108+225-10);
		theAngleSideAAmount.addActionListener (this);
		
		// Side B
		theAngleTitleSideB.setSize(300,60);
		theAngleTitleSideB.setLocation (400-370,150+225-10);
		
		// Slider for Side B
		theAngleSliderSideB.setSize (350,30);
		theAngleSliderSideB.setLocation (400-370,195+225-10);
		theAngleSliderSideB.addChangeListener (this);
		
		// JTextArea of Side B
		theAngleSideBAmount.setSize (100,25);
		theAngleSideBAmount.setLocation (487-370,168+225-10);
		theAngleSideBAmount.addActionListener (this);
		
		// Side C
		theAngleTitleSideC.setSize(300,60);
		theAngleTitleSideC.setLocation (400-370,210+225-10);
		
		// Slider for Side C
		theAngleSliderSideC.setSize (350,30);
		theAngleSliderSideC.setLocation (400-370,255+225-10);
		theAngleSliderSideC.addChangeListener (this);
		
		// JTextArea of Side C
		theAngleSideCAmount.setSize (100,25);
		theAngleSideCAmount.setLocation (487-370,228+225-10);
		theAngleSideCAmount.addActionListener (this);
				
		// Angle A
		theAngleTitleAngleA.setSize(300,60);
		theAngleTitleAngleA.setLocation (400-370,90+225-10);		
		
		// Slider for Angle A
		theAngleSliderAngleA.setSize (350,30);
		theAngleSliderAngleA.setLocation (400-370,135+225-10);
		theAngleSliderAngleA.addChangeListener (this);
		
		// JTextArea of Angle A
		theAngleAngleAAmount.setSize (100,25);
		theAngleAngleAAmount.setLocation (497-370,108+225-10);
		theAngleAngleAAmount.addActionListener (this);
		
		// Angle B
		theAngleTitleAngleB.setSize(300,60);
		theAngleTitleAngleB.setLocation (400-370,150+225-10);		
		
		// Slider for Angle B
		theAngleSliderAngleB.setSize (350,30);
		theAngleSliderAngleB.setLocation (400-370,195+225-10);
		theAngleSliderAngleB.addChangeListener (this);
		
		// JTextArea of Angle B
		theAngleAngleBAmount.setSize (100,25);
		theAngleAngleBAmount.setLocation (497-370,168+225-10);
		theAngleAngleBAmount.addActionListener (this);
		
		// Angle C
		theAngleTitleAngleC.setSize(300,60);
		theAngleTitleAngleC.setLocation (400-370,210+225-10);		
		
		// Slider for Angle C
		theAngleSliderAngleC.setSize (350,30);
		theAngleSliderAngleC.setLocation (400-370,255+225-10);
		theAngleSliderAngleC.addChangeListener (this);
		
		// JTextArea of Angle C
		theAngleAngleCAmount.setSize (100,25);
		theAngleAngleCAmount.setLocation (497-370,228+225-10);
		theAngleAngleCAmount.addActionListener (this);
		
		// Add A CALCULATE BUTTON!!!
		theAngleCalculateButton.setSize (150,25);
		theAngleCalculateButton.setLocation (500-370, 290+225-10);
		theAngleCalculateButton.addActionListener(this);
		
		// Create a SAVE BUTTON!
		theAngleSaveButton.setSize (120,25);
		theAngleSaveButton.setLocation (810, 49);
		theAngleSaveButton.addActionListener(this);
		
		// PAST RECORDS MENU
		// Set the menu size
		thePastPanel.setPreferredSize (new Dimension (960,540));  
		thePastPanel.setLayout (null);
		
		// Add Introduction
		JLabel thePastIntro = new JLabel ("Welcome to the Past Calculations Menu!!! Here are all the past triangles (and their dimensions) that you've saved!", SwingConstants.CENTER);
		thePastIntro.setSize(960, 20);
		thePastIntro.setLocation (0, 10);
		thePastPanel.add (thePastIntro);
		
		JLabel thePastDesc1 = new JLabel ("Navigate the List by using the buttons or typing!", SwingConstants.CENTER);
		thePastDesc1.setSize (960, 20);
		thePastDesc1.setLocation (0, 35);
		thePastPanel.add (thePastDesc1);
		
		// Set the triangle number viewing section
		
		// Previous Button
		thePastLeftButton.setSize (90,30);
		thePastLeftButton.setLocation (250, 80);
		thePastPanel.add (thePastLeftButton);
		thePastLeftButton.addActionListener(this);
		
		// Next Button
		thePastRightButton.setSize (90,30);
		thePastRightButton.setLocation (620, 80);
		thePastPanel.add (thePastRightButton);
		thePastRightButton.addActionListener(this);
		
		// The JLabel telling how many total triangles there are
		thePastTriangleNumbers.setSize (50,25);
		thePastTriangleNumbers.setLocation (420+70,83);
		thePastPanel.add(thePastTriangleNumbers);
		
		// The JTextField telling which triangle they're on rn
		thePastCurrentTriangle.setSize (50,25);
		thePastCurrentTriangle.setLocation (360+70,83);
		thePastCurrentTriangle.setHorizontalAlignment (SwingConstants.CENTER);
		thePastCurrentTriangle.addActionListener(this);
		thePastPanel.add(thePastCurrentTriangle);
	
		// Test Panel
		// Set the dimensions and default things
		theTestPanel.setPreferredSize (new Dimension(960,540));
		theTestPanel.setLayout (null);
		
		// Add Introduction Labels		
		theTestIntro1.setSize (960,20);
		theTestIntro1.setLocation (0,10);	

		theTestIntro2.setSize (960,20);
		theTestIntro2.setLocation (0,30);
		
		theTestIntro3.setSize (960,20);
		theTestIntro3.setLocation (0,50);
		
		// Add the Current Score
		theTestScore.setSize (380,25);
		theTestScore.setLocation (580,90);
		
		// Add The Question
		theTestQuestion.setSize (60, 25);
		theTestQuestion.setLocation (585, 160);
		theTestQuestion.setHorizontalAlignment (SwingConstants.RIGHT);
		
		// Add the Answer Box
		theTestAnswerField.setSize (280,25);
		theTestAnswerField.setLocation (655,160);
		
		// Add the submit button
		theTestSubmitButton.setSize (100,40);
		theTestSubmitButton.setLocation (725, 230);
		theTestSubmitButton.addActionListener(this);
		
		// Add the Loss Stuff
		// Add the Score Label
		//theTestFinalScore.setSize (960,25);
		//theTestFinalScore.setLocation (0,300);
		
		// Add the Name Label
		theTestNameLabel.setSize (960,25);
		theTestNameLabel.setLocation (0,380);
		
		// Add the Name Field
		theTestNameField.setSize (300,25);
		theTestNameField.setLocation (330, 405);
		theTestNameField.setHorizontalAlignment (SwingConstants.CENTER);
		
		// Add The Submit Button
		theTestFinalButton.setSize (150,30);
		theTestFinalButton.setLocation (405, 444);
		theTestFinalButton.addActionListener (this);
		
		// High Scores Panel
		// Set the Panel Stuff
		theHighPanel.setPreferredSize (new Dimension(960,540));
		theHighPanel.setLayout (null);
		
		// Add JLabels
		JLabel theHighIntro1 = new JLabel ("Welcome to your Test High Scores!!!", SwingConstants.CENTER);
		theHighIntro1.setSize (960,25);
		theHighIntro1.setLocation (0,10);
		theHighPanel.add(theHighIntro1);
		
		JLabel theHighIntro2 = new JLabel ("Your top 10 scores will be displayed below with your entered name!!!", SwingConstants.CENTER);
		theHighIntro2.setSize (960,25);
		theHighIntro2.setLocation (0,35);
		theHighPanel.add(theHighIntro2);
		
		// Set these variables so theres the red star indicating that they have to fill out the options
		theSidePanel.intRedStarX = 307;
		theSidePanel.intRedStarY = 475;
		
		theAnglePanel.intRedStarX = 298;
		theAnglePanel.intRedStarY = 290;
		
		// Help Panel
		theHelpPanel.setPreferredSize (new Dimension(960,540));
		theHelpPanel.setLayout (null);
		
		// Intorduction
		JLabel theHelpIntro = new JLabel ("Welcome to the Help Screen! Here is what each section means for the calculator!!!", SwingConstants.CENTER);
		theHelpIntro.setSize (960,25);
		theHelpIntro.setLocation (0,10);
		theHelpPanel.add(theHelpIntro);
		
		// Label 1
		JLabel theHelpLabel1 = new JLabel ("1. Menu Bar - Choose which tab you want to go to", SwingConstants.CENTER);
		theHelpLabel1.setSize (960,20);
		theHelpLabel1.setLocation (0,320);
		theHelpPanel.add(theHelpLabel1);
		
		// Label 2
		JLabel theHelpLabel2 = new JLabel ("2. Example Diagram - Use as reference for which sides to label", SwingConstants.CENTER);
		theHelpLabel2.setSize (960,20);
		theHelpLabel2.setLocation (0,345);
		theHelpPanel.add(theHelpLabel2);
		
		// Label 3
		JLabel theHelpLabel3 = new JLabel ("3. Save Button - Save the triangle that you just created to view later! You can view it in the 'Past Records' tab", SwingConstants.CENTER);
		theHelpLabel3.setSize (960,20);
		theHelpLabel3.setLocation (0,370);
		theHelpPanel.add(theHelpLabel3);
		
		// Label 4
		JLabel theHelpLabel4 = new JLabel ("4. Diagram of Requested Triangle - This is done to scale, so it looks exactly like the triangle would in real life. Make sure to look out for the labels", SwingConstants.CENTER);
		theHelpLabel4.setSize (960,20);
		theHelpLabel4.setLocation (0,395);
		theHelpPanel.add(theHelpLabel4);
		
		// Label 5
		JLabel theHelpLabel5 = new JLabel ("5. Side / Angle Input - You can either use the text field or the slider to input the length of each triangle side / angles (make sure it's in degrees)", SwingConstants.CENTER);
		theHelpLabel5.setSize (960,20);
		theHelpLabel5.setLocation (0,420);
		theHelpPanel.add(theHelpLabel5);
		
		// Label 6
		JLabel theHelpLabel6 = new JLabel ("6. Angle / Side Selector - Make sure to select which side/angle you want to find", SwingConstants.CENTER);
		theHelpLabel6.setSize (960,20);
		theHelpLabel6.setLocation (0,445);
		theHelpPanel.add(theHelpLabel6);
		
		// Label 7
		JLabel theHelpLabel7 = new JLabel ("7. Calculate Button - Click on the calcucalte to find your desired angle / side!", SwingConstants.CENTER);
		theHelpLabel7.setSize (960,20);
		theHelpLabel7.setLocation (0,470);
		theHelpPanel.add(theHelpLabel7);
		
		// Label 8
		JLabel theHelpLabel8 = new JLabel ("8. Answer - The size of your desired angle / side!", SwingConstants.CENTER);
		theHelpLabel8.setSize (960,20);
		theHelpLabel8.setLocation (0,495);
		theHelpPanel.add(theHelpLabel8);
		
		// About Me Panel
		theAboutPanel.setPreferredSize (new Dimension(960,540));
		theAboutPanel.setLayout (null);
		
		// Writing the About Me
		JLabel theAboutLabel1 = new JLabel ("A Genius...");
		theAboutLabel1.setSize (170,20);
		theAboutLabel1.setLocation (225-50,10);
		theAboutPanel.add(theAboutLabel1);
		
		JLabel theAboutLabel2 = new JLabel ("A Rich Man...");
		theAboutLabel2.setSize (960,20);
		theAboutLabel2.setLocation (300-50,30);
		theAboutPanel.add(theAboutLabel2);
		
		JLabel theAboutLabel3 = new JLabel ("and a Handsome Man all walk into a bar. The bartender says, Table for One?");
		theAboutLabel3.setSize (580,20);
		theAboutLabel3.setLocation (390-50,50);
		theAboutPanel.add(theAboutLabel3);
		
		//JLabel theAboutLabel4 = new JLabel ("all walk into a bar. The bartender says, Table for One?", SwingConstants.RIGHT);
		//theAboutLabel4.setSize (450,20);
		//theAboutLabel4.setLocation (400,50);
		//theAboutPanel.add(theAboutLabel4);
		
		JLabel theAboutLabel5 = new JLabel ("HOW.   IS.   THIS.   POSSIBLE???", SwingConstants.CENTER);
		theAboutLabel5.setSize (960,20);
		theAboutLabel5.setLocation (0,80);
		theAboutPanel.add(theAboutLabel5);
		
		JLabel theAboutLabel6 = new JLabel ("Hi, my name is Kevin Lau, and I am that man!!! Known for being charming, funny, and muscular, what else could God do to sculpt an even more perfect specimen?");
		theAboutLabel6.setSize (960,20);
		theAboutLabel6.setLocation (30,360+50);
		theAboutPanel.add(theAboutLabel6);
		
		JLabel theAboutLabel7 = new JLabel ("Unless you combine the brains of Einstein and the looks of Brad Pitt, nothing can even come close to my perfection!!!", SwingConstants.CENTER);
		theAboutLabel7.setSize (960,20);
		theAboutLabel7.setLocation (0,385+50);
		theAboutPanel.add(theAboutLabel7);
		
		JLabel theAboutLabel8 = new JLabel ("Other than being humble, I also like swimming, sleeping, and eating!!!", SwingConstants.CENTER);
		theAboutLabel8.setSize (960,20);
		theAboutLabel8.setLocation (0,410+50);
		theAboutPanel.add(theAboutLabel8);
		
		JLabel theAboutLabel9 = new JLabel ("I hope you enjoyed my program and used it to the full extent!", SwingConstants.CENTER);
		theAboutLabel9.setSize (960,20);
		theAboutLabel9.setLocation (0,435+50);
		theAboutPanel.add(theAboutLabel9);
		
		JLabel theAboutLabel10 = new JLabel ("    <-- Real Life Picture Of Me");
		theAboutLabel10.setSize (200,20);
		theAboutLabel10.setLocation (750,260);
		theAboutPanel.add(theAboutLabel10);
		
		//  Breaking through all the world records, there will be nothing humanity can do besides fall in love with me when my name and face are plastered on all the billboards around the world!
		
		// Set the Frame's default settings and pack it
		theFrame.setContentPane(theSidePanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setVisible(true);
		theFrame.setResizable(false);

		theFrame.pack();
		
		// Start Timer
		theTimer.start();
	}
	// Main Program
	public static void main (String[]args){
		new CosineLaw();
	}
}
