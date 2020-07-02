package millionaire;

import java.util.*;

/**
 * This main class contains the core functionalities of the Who Wants To Be A 
 * Millionaire game.
 * 
 * Excluding the main method, there are six helper methods which improve the 
 * core functionality of the game. These methods are: 
 * 1) game, which provides the core functionalities of the game;
 * 2) printCorrectAnswer, which prints the correct answer in word format as a 
 * String (e.g. "Grant Nisbett") to the user if they walk or get the question 
 * wrong;
 * 3) checkSecondaryAnswer, which evaluates an answer that is not A, B, C, D, or 
 * walk and determines what action to take from there;
 * 4) lifelineStatus, which tells a user which lifelines they have left 
 * (if any);
 * 5) lifelineRequested, which invokes the user's requested lifeline and prints 
 * out the result of the lifeline (even if it's already been used, in which case 
 * the user will be told this)
 * 6) moneyBalance, which when called tells a user how much money they currently 
 * have/have won/are guaranteed to win, depending on the parameters entered
 * 
 * More information about how each of these methods work (including the main) 
 * can be found in their Javadoc comments and general comments. This class 
 * also interacts with other classes (e.g. the FileOperations class), and also 
 * has a default constructor to initialize some variables.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class MillionaireGame 
{
    //Create MONEY_VALUES array and the questions ArrayList:
    private final int[] MONEY_VALUES;
    private static ArrayList<Question> questions;
    
    //Create three lifeline variables and FileOperations variable:
    private final Lifelines FF;
    private final Lifelines AA;
    private final Lifelines PAF;
    private final FileOperations FO;
    
    /**
     * Default constructor to initialize the five variables above (excluding 
     * questions, which is initialized in the main method).
     */
    public MillionaireGame()
    {
        this.MONEY_VALUES = new int[]{0, 100, 200, 300, 500, 
        1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000, 500000, 
        1000000};
        this.FF = new FiftyFifty();          //polymorphism
        this.AA = new AskAudience();         //polymorphism
        this.PAF = new PhoneAFriend();       //polymorphism
        this.FO = new FileOperations();
    }

    /**
     * This main method adds values to the questions ArrayList and introduces 
     * the user to the game. All functionality of the game is in the game() 
     * method.
     * 
     * @param args
     */
    public static void main(String[] args) 
    {
        String repeat = "";
        Scanner scan = new Scanner(System.in);
        
        while (!repeat.equalsIgnoreCase("y"))
        {
            //Create new instance of AddQuestions:
            AddQuestions add = new AddQuestions();
            MillionaireGame mg = new MillionaireGame();

            //Add questions to master ArrayList under the hood:
            add.easyQuestions();    //add easiest questions first
            add.mediumQuestions();  //add medium questions next
            add.hardQuestions();    //add hardest questions last

            //Grab the fully initialized master ArrayList that's under the hood, and 
            //use it to initialize the static questions ArrayList here:
            questions = add.getQuestions();

            System.out.println("Welcome to Who Wants To Be A Millionaire!");

            //show user list of commands by reading text file to console:
            System.out.println("Here is a list of commands you can use during the game:");
            FileOperations.readHelpDocument();

            System.out.println();
            System.out.println("For best user experience, play the game in full "
                    + "screen (i.e. maximise the output window).");
            System.out.println("Please note all answers are case-sensitive. "
                    + "Good luck! Let's play Who Wants To Be A Millionaire!");
            System.out.println();

            mg.game();  //start the game by calling this method
            
            do
            {
                System.out.println("Would you like to play again (y/n)?");
                repeat = scan.nextLine();
            } while (!repeat.equalsIgnoreCase("y") || !repeat.equalsIgnoreCase("n"));
            
            if (repeat.equalsIgnoreCase("y"))
                System.out.println("GREAT!");
            else
                System.out.println("Thanks for playing!");
        }
    }
    
    /**
     * This method contains the core functionalities of the game. Detailed 
     * comments can be found alongside important lines of code (such as variable 
     * initializations and game functionalities. 
     * 
     * The game runs on a while loop which goes until the user has answered 
     * all 15 questions correctly, or until they have answered a question wrong 
     * or walked away. Within this loop, the question and four possible answers 
     * are given to the user. If the answer isn't A, B, C, D, or walk, another 
     * nested while loop runs until the answer is one of those five answers. 
     * In this loop, checkSecondaryAnswer is called, where in that method, a switch 
     * case handles the user's input, giving the appropriate output to the user 
     * before giving them another chance to answer the question. 
     * Bad/invalid inputs are appropriately handled.
     * 
     * Once the inner loop has been exited (or not entered at all), the program 
     * evaluates the answer against the question's correct answer, where there 
     * are four cases:
     * 1) "walk" is entered - the user walks away with all the money they have 
     * earnt, are told the correct answer and the game/outer loop ends.
     * 2) A user enters an invalid answer - only applies on the same question as 
     * the 50/50 lifeline if a user attempts to enter one of the two incorrect 
     * eliminated answers. If this happens the user will be given another chance 
     * to answer the same question.
     * 3) Wrong answer entered - the user is told the correct answer. They walk 
     * away with either $0, $1000 or $32000 depending on how many safety nets 
     * they have passed. The game/outer loop will end.
     * 4) Correct answer entered - the user earns some money and moves on to the 
     * next question. They will be told if they have passed a safety net. 
     * questionsCorrect increments by one. If they have answered 15 questions, a 
     * congratulatory message is printed and the game/outer loop ends.
     */
    public void game()
    {
        Scanner keyboard = new Scanner(System.in);
        int questionsCorrect = 0;   //increments with each correct answer
        boolean finished = false;   //set to true if user 'walks away' or answers incorrectly

        //Runs if the number of questions correct is less than 15, and if the user
        //hasn't answered one wrong or requested to walk away from the game.
        while (questionsCorrect < 15 && finished == false)
        {
            System.out.println("Question number " + (questionsCorrect+1) + 
                    " for $" + MONEY_VALUES[questionsCorrect+1]);
            System.out.println(questions.get(questionsCorrect));
            //send question to text file:
            FO.consoleIOToFile(questionsCorrect, questions.get(questionsCorrect).getQuestion());

            for (int i = 0; i < 4; ++i)
            {
                //convert int i to char and add by 65 to represent A, B, C and D; 
                //then get the corresponding option:
                System.out.println((char)(i+65) + ": " + 
                        questions.get(questionsCorrect).getAnswers()[i]);
                //send answers to file:
                FO.consoleIOToFile(-1, (char)(i+65) + ": " + 
                        questions.get(questionsCorrect).getAnswers()[i]);
            }

            System.out.println();
            System.out.print("Your answer: ");
            String answer = keyboard.next();
            FO.consoleIOToFile(-1, "User's answer: " + answer);

            //Nested loop, allows user to re-enter their answer if it isn't...
            //one of the four possible options A, B, C or D; or the walk option.
            while (!(answer.equals("A") || answer.equals("B") || answer.equals("C") 
                    || answer.equals("D") || answer.equals("walk")))
            {
                checkSecondaryAnswer(answer, questionsCorrect, finished);
                
                System.out.println();
                System.out.print("Your answer: ");
                answer = keyboard.next();
                FO.consoleIOToFile(-1, "User's answer: " + answer);
            }
            
            //'walk' isn't a wrong answer, as the user has chosen to exit the game
            if (answer.equals("walk"))
            {
                System.out.println("Congratulations! You walk away with $" + 
                        moneyBalance(questionsCorrect, finished));
                System.out.println("The correct answer was " + 
                        questions.get(questionsCorrect).getRightAns() + " - " 
                        + printCorrectAnswer(questionsCorrect));
                System.out.println();
                
                //send correct answer to text file:
                FO.consoleIOToFile(-1, "Correct answer: " + 
                        questions.get(questionsCorrect).getRightAns() + " - " 
                        + printCorrectAnswer(questionsCorrect));
                
                FO.playerStatsToFile(questionsCorrect, moneyBalance(questionsCorrect, finished), finished);

                //the order is important here. If finished is set to true before the 
                //first print statement, then the money will show as one of the 
                //safety nets, or $0:
                finished = true;    //change of state; taking the money ends the game             
            }
            //**SPECIAL ANSWER CASE**:
            //if the ArrayList index # is equal to the question # (minus 1) when 
            //the user initiated the fifty-fifty lifeline, and the user selects 
            //one of the two eliminated answers, the user will be prompted to 
            //answer again, since their answer will no longer exist:
            else if (questionsCorrect == FF.getQuestionNumber() && (answer.equals(FF.getFirstWrong()) 
                    || answer.equals(FF.getSecondWrong())))
            {
                System.out.println("Answer no longer exists. Try again!");
                //no increment of questionsCorrect as the answer is incorrect... 
                //Allows question to be repeated.
                System.out.println();
                //print to file:
                FO.consoleIOToFile(-1, "Answer no longer exists. Try again!");
            }
            else if (!answer.equals(questions.get(questionsCorrect).getRightAns())) 
            {
                System.out.println("Wrong! The answer is " + 
                        questions.get(questionsCorrect).getRightAns() + " - " 
                        + printCorrectAnswer(questionsCorrect));
                
                finished = true;    //change of state; wrong answer ends the game
                
                System.out.println("You answered " + questionsCorrect + 
                        " question(s) correctly, and earnt $" + 
                        moneyBalance(questionsCorrect, finished) + "!");
                System.out.println();
                
                //send correct answer to text file:
                FO.consoleIOToFile(-1, "Correct answer: " + 
                        questions.get(questionsCorrect).getRightAns() + " - " 
                        + printCorrectAnswer(questionsCorrect));
                
                FO.playerStatsToFile(questionsCorrect, moneyBalance(questionsCorrect, finished), finished);
            }
            else
            {
                ++questionsCorrect;
                System.out.println("Correct for $" + MONEY_VALUES[questionsCorrect] + " dollars!");
                
                if (questionsCorrect == 5 || questionsCorrect == 10)    //reaching the two safety nets
                    System.out.println("You have reached the safety net of $" + 
                            MONEY_VALUES[questionsCorrect] + " dollars!");
                else if (questionsCorrect == 15)    //ie all questions answered correctly
                {
                    System.out.println("CONGRATULATIONS! You have just won $1000000! Well done!");
                    FO.playerStatsToFile(15, 1000000, false);
                    finished = true;    //change of state; user has won the game
                }
                System.out.println();
            }
        }
    }
    
    /**
     * This method checks a secondary answer a user can give (i.e. one that 
     * isn't A, B, C, D, or walk), and evaluates it to see what to do next.
     * 
     * @param answer to evaluate
     * @param questionsCorrect no. of questions correctly answered by the user
     * @param finished whether the user has finished the game or not
     */
    public void checkSecondaryAnswer(String answer, int questionsCorrect, boolean finished)
    {
        switch (answer)
        {
            case "5050":
            case "audience":    //fall through, as it's also a lifeline
            case "friend":      //fall through, as it's also a lifeline
                lifelineRequested(answer, questionsCorrect);
                break;
            //this case tells user how much money they have, and how ...
            //much they are guaranteed to win if they end the game:
            case "money":
                System.out.println("You currently have: $" + moneyBalance(questionsCorrect, finished)
                        + ". And are guaranteed to win: $" + moneyBalance(questionsCorrect, true));
                FO.consoleIOToFile(-1, "You currently have: $" + 
                        moneyBalance(questionsCorrect, finished)
                        + ". And are guaranteed to win: $" + moneyBalance(questionsCorrect, true));
                break;
            case "help":        //shows user a list of valid commands.
                FileOperations.readHelpDocument();
                break;
            case "lifelines":   //tells user what lifelines they have left (if any).
                System.out.println(lifelineStatus());
                break;
            case "question":    //reprints the question incase the user forgot it.
                System.out.println(questions.get(questionsCorrect));
                for (int i = 0; i < 4; ++i)
                {
                    System.out.println((char)(i+65) + ": " + 
                        questions.get(questionsCorrect).getAnswers()[i]);
                }
                break;
            default:            //any input other than the ones listed in help.txt
                System.out.println("Invalid input. Type 'help' for a list of valid inputs.");
                break;
        }
    }
    
    /**
     * This method returns the actual correct answer to a question for the user 
     * if they get the question wrong or walk away.
     * 
     * @param questionNumber as integer
     * @return the correct answer as a String.
     */
    public String printCorrectAnswer(int questionNumber)
    {
        //get the correct answer from the array so it can be printed 
        //alongside the letter
        String[] answers = questions.get(questionNumber).getAnswers();
        
        if (questions.get(questionNumber).getRightAns().equals("A"))
            return answers[0];
        if (questions.get(questionNumber).getRightAns().equals("B"))
            return answers[1];
        if (questions.get(questionNumber).getRightAns().equals("C"))
            return answers[2];
        else    //if the right answer equals "D"
            return answers[3];
    }
    
    /**
     * This method returns a summary of the all the lifelines, telling the user 
     * which lifelines they have used and what question they were used on, 
     * or if they are unused. A numerical value of lifelines left is also sent 
     * to the user.
     * 
     * @return String representation of the status of all the lifelines, plus 
     * how many they have left (if any).
     */
    public String lifelineStatus()
    {
        String summary = "Lifeline statuses:\n";
        int lifelinesLeft = 0;  //a counter
        
        if(!FF.isUsed())    //if lifeline isn't used/false
        {
            summary += "50/50 unused.\n";   //append to string
            ++lifelinesLeft;    //increments if unused
        }
        else    //plus 1 on questionNumber to get the correct question number:
            summary += "50/50 used on question #" 
                + (FF.getQuestionNumber()+1) + ".\n"; //append to string
        
        if(!AA.isUsed())    //if lifeline isn't used/false
        {
            summary += "Ask the Audience unused.\n";    //append to string
            ++lifelinesLeft;    //increments if unused
        }
        else    //plus 1 on questionNumber to get the correct question number:
            summary += "Ask the Audience used on question #" 
                + (AA.getQuestionNumber()+1) + ".\n"; //append to string
        
        if(!PAF.isUsed())   //if lifeline isn't used/false
        {
            summary += "Phone A Friend unused.\n";  //append to string
            ++lifelinesLeft;    //increments if unused
        }
        else    //plus 1 on questionNumber to get the correct question number:
            summary += "Phone A Friend used on question #" 
                + (PAF.getQuestionNumber()+1) + ".\n";    //append to string
        
        summary += "Lifelines unused: (" + lifelinesLeft + ")";
        FO.consoleIOToFile(-1, summary);    //write to file
        
        return summary;
    }
    
    /**
     * This method invokes a lifeline. The user's input determines which 
     * lifeline is invoked (switch cases). The questionNo is passed in so 
     * the lifeline knows what the correct answer is and what to do with the 
     * incorrect answers. The 50/50 and Phone A Friend lifelines just call the 
     * lifeline method, passing in the question from the questions ArrayList.
     * 
     * However, the Ask Audience method must first check if the 50/50 has been 
     * used on the same question, as a virtual audience shouldn't be allowed 
     * to vote on questions which have been eliminated. If it has been used on 
     * the same question, the AskAudience variable calls the following set methods: 
     * set FiftyFiftyUsed to true, set FiftyFiftyQuestion and QuestionNumber to 
     * the same question number and setFirst/SecondWrong to the two eliminated 
     * answers. Only after the if statement has been checked will the Ask Audience 
     * lifeline call its lifeline method.
     * 
     * @param lifeline
     * @param questionNo
     */
    public void lifelineRequested(String lifeline, int questionNo)
    {
        String lifelineResult = "";
        
        switch (lifeline) 
        {
            //invoke 50/50 lifeline
            case "5050":
                if (!FF.isUsed())  //set question number if unused
                    FF.setQuestionNumber(questionNo);
                lifelineResult = FF.lifeline(questions.get(questionNo));
                break;
            //invoke Ask Audience lifeline
            case "audience":
                //set fiftyFiftyQuestion, firstWrong and secondWrong variables 
                //for the Ask Audience lifeline if it is used after the 50/50 
                //lifeline, but only if both lifelines are used for the 
                //same question:
                if (FF.isUsed() && FF.getQuestionNumber() == questionNo)
                {
                    AA.setFiftyFiftyQuestion(questionNo);
                    AA.setFirstWrong(FF.getFirstWrong());
                    AA.setSecondWrong(FF.getSecondWrong());
                }   
                
                if (!AA.isUsed())  //set question number if unused
                    AA.setQuestionNumber(questionNo);
                
                lifelineResult = AA.lifeline(questions.get(questionNo));
                break;
            //invoke Phone A Friend lifeline
            case "friend":
                //set fiftyFiftyQuestion, firstWrong and secondWrong variables 
                //for the Phone A Friend lifeline if it is used after the 50/50 
                //lifeline, but only if both lifelines are used for the 
                //same question:
                if (FF.isUsed() && FF.getQuestionNumber() == questionNo)
                {
                    PAF.setFirstWrong(FF.getFirstWrong());
                    PAF.setSecondWrong(FF.getSecondWrong());
                    PAF.setFiftyFiftyQuestion(questionNo);
                }   
                
                if (!PAF.isUsed())  //set question number if unused
                    PAF.setQuestionNumber(questionNo);
                
                lifelineResult = PAF.lifeline(questions.get(questionNo));
                break;
            default:    //not really needed
                break;
        }
        
        System.out.println(lifelineResult);
        //print result to text file:
        FO.consoleIOToFile(-1, lifeline + ": " + lifelineResult);
    }
    
    /**
     * This method returns a value of money to the user. The value returned 
     * depends on two factors - the number of questions they have got 
     * correct, and whether they have finished. This method can return two 
     * types of money:
     * 1) How much money a user is guaranteed to win (the else statement)
     * 2) How much money a user has won (currently, if they have walked away, 
     * or if they answered a question wrong (case 1))
     * 
     * @param correctAnswers
     * @param finished
     * @return integer value of money
     */
    public int moneyBalance(int correctAnswers, boolean finished)
    {
        if (!finished)  //if user hasn't walked away or answered a question wrong
            return MONEY_VALUES[correctAnswers];
        else
        {
            if (correctAnswers < 5) //safety net 1 not reached
                return 0;
            else if (correctAnswers < 10)   //safety net 2 not reached
                return 1000;
            else
                return 32000;
        }
    }
}
