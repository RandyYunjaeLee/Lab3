COSC310_Lab3_Randy_Lee

The purpose of the program we created is to assist users in solving their technology problems. This chatbot is similar to ones you would see on websites such as TELUS where the chatbot leads the user through a series of questions in order to attempt to solve the users tech problems. In order to run the program in eclipse you will need to complete the following steps:


1.	Download and install Eclipse.
2.	In your browser, open the URL for the github repository , click accept, click the URL then click Clone and copy the URL
3.	In Eclipse, File - import - git - projects from Git - clone URI then enter the URI.


To run the chatbot you need to run GUI.java. There are 11 classes in this project : DecisionMatrix.java, GUI.java, EdgeBuilder.java, Question.java, QuestionBuilder.java, QuestionBuilderTest.java, Run.java, StackHandler.java, Testing.java, Tree.java, and UserInput.java. DecisionMatrix.java is the program that decides what the program does for a given input. For example, if the user answers a question in a way that the program does not understand, it will respond by saying one of 5 I don't understand comments. It also determines whether or not the program is finished or needs to keep going. GUI creates a simple GUI with user input and chat log. EdgeBuilder.java Takes an ArrayList of unlinked questions (children nodes empty), and links all of the nodes together using branchID and questionPriority. Question.java contains info from individual question file. QuestionBuilder.java gets all question files in a specified directory and reads them into new Question objects while QuestionBuilderTest.java tests it. StackHandler.java ensures that the stack we have created runs smoothly. Testing.java is the main testing class. Tree.java creates a tree that holds the questions. UserInput.java is all the answers that the user inputs. Finally, Run.java is the main class that runs the chatbot.

ADDED
5 random response uses a random number generator, and an array of answers 

Tech-Bot: Tech-Bot: Hello, I am Tech-bot. I will be assisting you today.
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
User: fish
Tech-Bot: I didn't get that, try again.
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
User: salmon
Tech-Bot: I didn't get that, try again.
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
User: f
Tech-Bot: Error in response you want to try that again?
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
User: pie
Tech-Bot: Sorry that confused me a little
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
User: chicken
Tech-Bot: Error in response you want to try that again?
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
User: I love you
Tech-Bot: Okay, not sure how that relates to the question, try that again
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
Tech-Bot: Sorry that confused me a little
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
User: doom
Tech-Bot: I didn't get that, try again.
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
Tech-Bot: Error in response you want to try that again?
Tech-Bot: Is your issue in regards to internet, or phone, or TV?

GUI uses JFrame for a simple ui

can't demonstrate in the read me

New branch TV, just added a few new text files and a new packet with a few new lines of code


Tech-Bot: Tech-Bot: Hello, I am Tech-bot. I will be assisting you today.
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
User: tv
Tech-Bot: Do you know your activation ID?
User: yes
Tech-Bot: Is there anything on screen?
User: yes
Tech-Bot: Is it showing a prompt for the ID?
User: yes
Tech-Bot: Please enter the ID into the prompt. Did this fix your problem?
User: yes
Tech-Bot: Is there anything else I can help you with?
User: yes
Tech-Bot: Is your issue in regards to internet, or phone, or TV?
