package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/*
 * Class: Run
 * Description: -	Main class to run the Chatbot
 * 				-	Incorporates all other classes (directly or indirectly)
 *
 * 	Dependencies- 	UserInput.java
 * 				-	DecisionMatrix.java
 * 				-	Hashmap.java
 *
 * 	Parameters	-   name -> class -> (type)
 * 				-
 * 				- 	selection -> (int)
 * 				-	user -> UserInput -> (String)
 * 				-	ui -> (UserInput)
 * 				-	file -> (String) = File Name
 * 				-	questions -> QuestionBuilder -> (Hashmap)
 * 				-	d -> (DecisionMatrix)
 *
 * Authors: Daulton Baird
 */

public class Run {
	int selection;
	String user;
	UserInput ui;
	String file;
	HashMap<String, Question> questions;
	DecisionMatrix d;
	StackHandler sh;
	Stack<String> convo;
	Stack<String> fileStack;
	GUI gui;
	String[] unknown={"I didn't get that, try again.", "Okay, not sure how that relates to the question, try that again",
			"Error in response you want to try that again?", "Didn't quite get that can you say that again?", "Sorry that confused me a little"};
	boolean input = false;

	public Run(GUI gui) {
		sh = new StackHandler();
		convo = sh.initConversationLog();
		fileStack = sh.initFileLog();
		this.gui = gui;
	}
	/*
	 * Method: initialize
	 * Outputs:		-	Initial Tree
	 *
	 * Description:	-	Creates Tree that asks the first Question
	 * 				-	sets int selection to 0
	 * 				-	while loop makes method loop until user gives correct input (Defensive Programming)
	 * 				-	Prints the First Question
	 * 				-	Assigns UserInput ui to new UserInput
	 *  			-	Assigns String user to the user's input
	 * 				-	Assigns selection based on the new Tree to be built
	 * 				-	If input is invalid print that it is invalid
	 */

	public void initialize() throws IOException, InterruptedException{
		Tree start = new Tree(0);
		ArrayList<Question> initial = new ArrayList<>(start.getNextQuestion().values());
		setSelection(0);
		setUI(new UserInput());
		int counter = 0;

		while(true) {
			if(counter>0) {
				int x = (int)Math.floor(Math.random()*5);
				gui.print(unknown[x]);
			}
			gui.print(initial.get(0).getQuestion());
			counter++;
			convo.push("Chatbot: "+initial.get(0).getQuestion());
			Thread.sleep(4500); //give user 4.5 secs to respond
			input = gui.getInputBool(); //boolean storing whether user has inputted
			if(input) { //makes sure user input is not null
				ui.setInput(gui.getUserInput());
				setUser(ui.getInput2(gui));
				convo.push("User: "+getUser());
				if(getUser().contains("net")) {
					setSelection(1);
					initializeTree();
					break;
				}
				else if(getUser().contains("phone")) {
					setSelection(2);
					initializeTree();
					break; }
				else if(getUser().contains("tv")) {
					setSelection(3);
					initializeTree();
					break; }
				else {
					int x = (int)Math.floor(Math.random()*5);
					gui.print(unknown[x]);
					counter=0;}
			}
		}
	}

	/*
	 * Method: initializeTree
	 * Outputs:		-	"Internet" Tree or "Phone" Tree
	 *
	 * Description:	-	Creates Tree based on input from initialize Method
	 * 				-	Sets File to the initial file of the Folder
	 * 				-	Sets Hashmap questions via the nextQuestion method from the Tree
	 * 				-	Sets DecisionMatrix d to new DecisionMatrix
	 */

	public void initializeTree() throws IOException {
		Tree bot = new Tree(getSelection());
		setFile("0-0.txt");
		fileStack.push(getFile());
		setQuestions(bot.getNextQuestion());
		setDecisionMatrix(new DecisionMatrix(this));
	}

	/*
	 * Method: runLoop
	 * Outputs:		-	Chatbot and User Conversation
	 *
	 * Description:	-	while loop to continue until solution is found
	 * 				-	If the current file is the loop file, break out of the while loop (goes back to top of outer while loop)
	 * 				-	If the current file is the end file, print the "Thank you" string and then exit the program
	 * 				-	Otherwise Print current question
	 * 				-	Set String user to the user's input
	 *  			-	Decide the next file via DecisionMatrix d
	 */

	public void runLoop() throws IOException, InterruptedException {
		input = false;
		while (true) {
			if(getFile().equals("loop-0.txt")){
				initialize(); //restarts the process

			}else if(getFile().equals("end-0.txt")){
				gui.print(getQuestions().get(getFile()).getQuestion()); //set bot to print question
				convo.push("Chatbot: "+getQuestions().get(getFile()).getQuestion()); //adds bot output to log
				sh.conToFile(); //sends chat log to file
				sh.pathToFile();//sends file log to file
				gui.exit(); //runs exit process
			}
			input = gui.getInputBool();//boolean storing whether user has inputted
			if(input) {//makes sure user input is not null
				Bot(); //prints bot response, write to log
				Thread.sleep(4500); //give user 4.5 seconds to answer
				User(); //get user input, write to log
				File(); //get next file (question) from decision matrix, add to filelog
				gui.setUserInput(""); //set user to blank so that previous answere isn't reused
			}
		}
	}

	void Bot() {
		gui.print(getQuestions().get(getFile()).getQuestion()); //print question
		convo.push("Chatbot: "+getQuestions().get(getFile()).getQuestion()); //add bot output to convo log
	}
	private void User() {
		setUser(ui.getInput2(gui)); //read user input
		convo.push("User: "+getUser());//add to convo log
	}

	private void File() throws FileNotFoundException, IOException {
		setFile(d.Decision(gui, getUser(), getFile(), getSelection())); //decide next question
		fileStack.push(getFile()); //add to file log
	}


	//setters (only used locally)
	private void setSelection(int selection) {this.selection=selection;}
	private void setUser(String user) {this.user= user;}
	private void setUI(UserInput ui) {this.ui=ui;}
	private void setFile(String file) {this.file= file;}
	private void setQuestions(HashMap<String, Question> questions) {this.questions=questions;}
	private void setDecisionMatrix(DecisionMatrix decisionMatrix) {this.d=decisionMatrix;}

	//getters (only used locally)
	private int getSelection() {return this.selection;}
	private String getUser() {return this.user;}
	private String getFile() {return this.file;}
	private HashMap<String, Question> getQuestions(){return this.questions;}


}