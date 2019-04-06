package src;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class GUI implements ActionListener {

	UserInput ui;

	JFrame frame;
	JButton button;
	JTextArea ChatWindow;
	JTextField UserInput;
	JScrollPane scroll;

	String userInput;
	String[] unknown={"I didn't get that, try again.", "Okay, not sure how that relates to the question, try that again",
	"Error in response you want to try that again?", "Didn't quite get that can you say that again?", "Sorry that confused me a little"};

	public GUI(){
		frame = new JFrame();
		openWindow();
		setPanel();
		makeButton();
		setUserText();
		frame.add(scroll, BorderLayout.NORTH);
		frame.add(button, BorderLayout.EAST);
		frame.add(UserInput, BorderLayout.WEST);
		frame.setVisible(true);
		ui = new UserInput();
		setUserInput("");
	}
	public void actionPerformed(ActionEvent e) {
		String user = UserInput.getText();
		if(user.length()>0) {
			setUserInput(user);
			ui.setInput(user);
			ChatWindow.append("\nUser: "+user);
			UserInput.setText(""); //clears text field
		}
		else {
			int x = (int)Math.floor(Math.random()*5);
			print(unknown[x]);
			print(x + " f");
		}
	}

	public void setUserInput(String user) {
		userInput = user;
	}
	public String getUserInput() {
		return userInput;
	}

	public void print(String output) {
		ChatWindow.append("\nTech-Bot: "+output);
	}


	public boolean getInputBool() {
		return (getUserInput() != null);
	}
	private void openWindow() {
		frame.setTitle("Chatbot.exe");

		frame.setSize(500,770);
		frame.setLayout(new BorderLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	private void setPanel() {
		ChatWindow = new JTextArea();
		ChatWindow.setEditable(false);
		scroll = new JScrollPane(ChatWindow);
		ChatWindow.setAutoscrolls(true);
		scroll.setPreferredSize(new Dimension(500,700));
	}
	private void setUserText() {
		UserInput = new JTextField();
		UserInput.setPreferredSize(new Dimension(375,10));
		UserInput.addActionListener(this);
	}
	private void makeButton() {
		button = new JButton("Send");
		button.setPreferredSize(new Dimension(100,30));
		button.setBackground(Color.BLUE);
		button.setForeground(Color.white);
		button.addActionListener(this);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		GUI gui = new GUI();
		Run run = new Run(gui);
		gui.print("Tech-Bot: Hello, I am Tech-bot. I will be assisting you today.");
		run.initialize();
		run.runLoop();

	}
	public void exit() throws InterruptedException {
		ChatWindow.append("\nSystem Exiting");
		Thread.sleep(500);
		System.exit(0);
	}
}