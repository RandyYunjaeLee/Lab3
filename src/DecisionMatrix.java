package src;
//coded by Randy Lee
//started Feb 8 worked for 1 hour
//Feb 12 worked on it for 3 hours
//Feb 13 worked on it for 3 hours
//Feb 15 worked on it from 11:30 -
//note to self spilt up into helper methods for unit testing
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
public class DecisionMatrix {
  String[] tryAgain={"I didn't get that, try again.", "Okay, not sure how that relates to the question, try that again",
          "Error in response you want to try that again?", "Didn't quite get that can you say that again?", "Sorry that confused me a little"};
  //inputs are userInput, filename
  //note number of answer = 4th line in txt file
  
  Run run;
  
   public DecisionMatrix(Run run) {
	   this.run = run;
   }
  
  public String[] letsSplit(String s){
    String[] files_part1 = s.split("\\.");
    String[] files_part2 = files_part1[0].split("-");
    String[] split = {files_part2[0],files_part2[1],files_part1[1]};
    return split;
  }
  //for asking if there is anything else
  public String anythingElse(GUI gui, String userInput){
      //if the word says answer 1 point to begin the loop again
      if(userInput.contains("yes")){

        return "loop-0.txt";
      }
      //if the word says answer 2 point to end the program
      else if(userInput.contains("no")){
        String s = "end-0.txt";
        System.out.println(s);
        return s;
      }else {
        gui.print(tryAgain[(int)Math.floor(Math.random()*5)]);
        return "anythingElse-0.txt";
      }
  }



  public String[] threeOrTwo(GUI gui, int i, String userInput, String[] answers, String[] files_part2, String file){
    for(int j = 0; j<i; j++){
      if(userInput.contains(answers[j].split(" ")[1])){
        files_part2[0]=files_part2[0]+j;
        //reset priority
        files_part2[1]="0";
        //don't need to check anymore
       break;
      }
    }
    if((files_part2[0]+"-"+files_part2[1]+".txt").equals(file)){
      gui.print(tryAgain[(int)Math.floor(Math.random()*5)]);
      return letsSplit(file);
    }

    return files_part2;
  }
  public String Decision(GUI gui, String userInput, String file, int selection) throws FileNotFoundException {
    String decision;
    //these 2 lines separates the file name by - and .
    String[] files_part2 = letsSplit(file);
    //check selection
    File f = new File("");
    if(selection==1){
      f = new File("Internet");
    }
    else if(selection==2){
      f = new File("Phone");
    }
    else if(selection==3){
      f = new File("TV");
    }
    for(File check: f.listFiles()){
      if(check.getName().equals(file)){
        f= check;
      }
    }
    //standard file opening
    Scanner in = new Scanner(f);
    //reading the file and getting what this program needs
    in.nextLine();//skip branch id
    in.nextLine(); //skip priority
    //this part checks the answers
    String check = in.nextLine(); //question
    String s1 = in.nextLine(); //num answers
    String[] answers = new String[3];
    int count =0;
    while(in.hasNextLine()){
      answers[count]=in.nextLine();
      count++;
    }

    int i = Integer.parseInt(s1);
    if(check.contains("Did")||check.contains("Does"))
    {
      //if the word says answer 1 point to begin the loop again
      if(userInput.contains(answers[0].split(" ")[1])) { //if they say yes
        return "anythingElse-0.txt";
      }
    }
    if(check.contains("anything else"))
    {
      return anythingElse(gui, userInput);
    }
    if(i>1){
      files_part2 = threeOrTwo(gui, i, userInput, answers, files_part2, file);
    }

    if(i==1){
      //new priority if empty question
      int j = Integer.parseInt(files_part2[1]) + 1;
      files_part2[1]=j +"";
    }
    //decision = path, priority and file type
    decision = files_part2[0]+"-"+files_part2[1]+".txt";
    System.out.println(decision);
    return decision;
  }
}
