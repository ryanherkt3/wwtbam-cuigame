package millionaire;

import java.io.*;

/**
 * This class handles all File input and output operations for the game. There 
 * are three methods - playerStatsToFile, consoleIOToFile and readHelpDocument, 
 * plus two encapsulation get & set methods for the MONEY_VALUES integer array.
 * 
 * It also contains a MONEY_VALUES integer array, just like the main game class. 
 * This is used only in the playerStatsToFile method where the money a user won 
 * is written to the "playerstats.txt" file.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class FileOperations 
{
    private int[] MONEY_VALUES;
    
    /**
     * This default constructor initializes the MONEY_VALUES integer array, 
     * similar to the array in the main game class.
     */
    public FileOperations()
    {
        this.MONEY_VALUES = new int[]{0, 100, 200, 300, 500, 1000, 2000, 
        4000, 8000, 16000, 32000, 64000, 128000, 250000, 500000, 1000000};
    }
    
    /**
     * This method, when called, reads the "help.txt" file to the console. This 
     * method is called at the start of the game and any other time the user 
     * requests it during the game. This method also catches two exceptions if 
     * they ever occur - but in reality these exceptions should never occur.
     */
    public static void readHelpDocument()
    {
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader("help.txt"));
            String line;
            
            System.out.println();
            while((line=inputStream.readLine())!=null)
            {
                System.out.println(line);
            }
            inputStream.close();
        }
        catch(FileNotFoundException e) 
        {
            System.out.println("File not found.");
        }
        catch(IOException e) 
        {
            System.out.println("Error reading from text file.");
        }
    }
    
    /**
     * This method is only called when the game ends, and writes a player's game 
     * statistics to the "playerstats.txt" file. The stats which are written in 
     * are the number of correct answers a player got, the player's money they 
     * won, and if applicable, what question the player got wrong (in terms of 
     * money - eg: "got the $16000 question wrong). 
     * 
     * Any information written in this method is also written to the 
     * "userinputs.txt" file, with the only difference being the questionNumber 
     * is set to -1 since the game has ended by that point. This method also 
     * catches two exceptions if they ever occur - but in reality these 
     * exceptions should never occur.
     * 
     * @param correctAnswers
     * @param money
     * @param wrongAnswer
     */
    public void playerStatsToFile(int correctAnswers, int money, boolean wrongAnswer)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter("playerstats.txt", true));
            String line = "Correct answers: " + correctAnswers + ". Money won: $" + money;
            
            if (wrongAnswer)
                line += ". Got the $" + MONEY_VALUES[correctAnswers+1] + " question wrong.";
            else if (correctAnswers < 15)
                line += ". Walked on the $" + MONEY_VALUES[correctAnswers+1] + " question.";
            
            consoleIOToFile(-1, line);
            
            //for playerstats.txt file only:
            if (correctAnswers < 15)
                line += "\n";
            else
                line += ".\n";
            
            bw.append(line);
            bw.close();
        }
        catch(FileNotFoundException e) 
        {
            System.out.println("File not found.");
        }
        catch(IOException e) 
        {
            System.out.println("Error reading from text file.");
        }
    }
    
    /**
     * This method writes any appropriate console I/O to the "useranswers.txt" 
     * text file, while also passing in the questionNumber so anyone who 
     * reviews the text file knows what question any console I/O happened in, 
     * but only once.
     * 
     * When questionNumber is -1, this indicates either that the game has 
     * finished, or the question number has been passed in already. Usually, 
     * it's the latter.
     *  
     * This method also catches two exceptions if they ever occur - but in 
     * reality these exceptions should never occur.
     * 
     * @param questionNumber
     * @param input
     */
    public void consoleIOToFile(int questionNumber, String input)
    {
        try
        {
            String line;
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("useranswers.txt", true));
            if (questionNumber != -1)   //only print question number once
                line = (questionNumber+1) + ": " + input;
            else
                line = input;
            
            if (input.equals("User's answer: A") || input.equals("User's answer: B") 
                    || input.equals("User's answer: C") 
                    || input.equals("User's answer: D") && questionNumber < 14)
                line += "\n";   //add newline only if game is not finished
            
            if (input.contains("Correct answers:"))
            {
                //add newline then several equals signs then another newline 
                //to separate games:
                line += "\n==========\n";
            }
            else
                line += "\n";   //add newline to separate results
            
            bw.append(line);
            bw.close();
        }
        catch(FileNotFoundException e) 
        {
            System.out.println("File not found.");
        }
        catch(IOException e) 
        {
            System.out.println("Error reading from text file.");
        }
    }

    /**
     * @return the MONEY_VALUES
     */
    public int[] getMONEY_VALUES() 
    {
        return MONEY_VALUES;
    }

    /**
     * @param MONEY_VALUES the MONEY_VALUES to set
     */
    public void setMONEY_VALUES(int[] MONEY_VALUES) 
    {
        this.MONEY_VALUES = MONEY_VALUES;
    }
}