package millionaire;
/**
 * This FiftyFifty class contains the algorithm for the 50/50 lifeline (lifeline 
 * method). It also has a default constructor.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class FiftyFifty extends Lifelines
{
    /**
     * This default FiftyFifty constructor only calls the Lifelines superclass, 
     * as all variables are declared there in its constructor.
     */
    public FiftyFifty() 
    {
        super();
    }
    
    /**
     * This method contains the algorithm for the 50/50 lifeline. The algorithm 
     * first sets option to be a random integer between 0 and 1, then checks 
     * the state of isUsed(). If it's false, a message will print saying the 
     * lifeline has been used, otherwise the algorithm continues by doing a 
     * switch check with the right answer used as the condition check. 
     * 
     * When the check hits one of the letters, a conditional check is used to find 
     * out if option is equal to zero. If it isn't, the algorithm sets the wrong 
     * answers to the two highest letters (e.g. if the right answer is D, the 
     * algorithm will choose B and C if option equals 1). Otherwise, it again 
     * sets option to be a random integer between 0 and 1. If option is 0, it 
     * sets the second wrong answer to be the closest available letter; 
     * otherwise secondWrong is set to the last available letter.
     * 
     * After the algorithm is complete, setUsed(true) is called, then the 
     * statement: firstWrong + " and " + secondWrong + " are incorrect!", is 
     * returned to the caller of this method (and the end user).
     * 
     * A list of the possible outcomes for the two randomly selected incorrect 
     * answers are shown next to each switch case (e.g. AB, AC, BC if the 
     * correct answer is D).
     * 
     * @param question as Question
     * @return lifeline outcome as String
     */
    @Override
    public String lifeline(Question question) 
    {
        option = integer.nextInt(2);    //to determine 'first' wrong answer
        
        if (!isUsed())  //only activates if lifeline hasn't been used
        {
            System.out.println("Computer take away two random wrong answers.");
            
            /* Thread's sleep method called in order to delay result of 
            lifeline to user by 2 seconds */
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException ex) {}
            
            switch (question.getRightAns())
            {
                case "A":   //BC, BD, CD 
                    if (option == 0)
                    {
                        firstWrong = "B";
                        option = getInteger().nextInt(2);
                        if (option == 0)
                            secondWrong = "C";
                        else
                            secondWrong = "D";
                    }
                    else
                    {
                        firstWrong = "C";
                        secondWrong = "D";
                    }
                    break;
                case "B":   //AC, AD, CD
                    if (option == 0)
                    {
                        firstWrong = "A";
                        option = getInteger().nextInt(2);
                        if (option == 0)
                            secondWrong = "C";
                        else
                            secondWrong = "D";
                    }
                    else
                    {
                        firstWrong = "C";
                        secondWrong = "D";
                    }
                    break;
                case "C":   //AB, AD, BD
                    if (option == 0)
                    {
                        firstWrong = "A";
                        option = getInteger().nextInt(2);
                        if (option == 0)
                            secondWrong = "B";
                        else
                            secondWrong = "D";
                    }
                    else
                    {
                        firstWrong = "B";
                        secondWrong = "D";
                    }
                    break;
                case "D":   //AB, AC, BC
                    if (option == 0)
                    {
                        firstWrong = "A";
                        option = getInteger().nextInt(2);
                        if (option == 0)
                            secondWrong = "B";
                        else
                            secondWrong = "C";
                    }
                    else
                    {
                        firstWrong = "B";
                        secondWrong = "C";
                    }
                    break;
                default:
                    break;
            }
            setUsed(true);    //stops abuse of the lifeline
            return firstWrong + " and " + secondWrong + " are incorrect!";  //returns the two wrong answers
        }
        else 
            return "You have already used this lifeline!";  //if lifeline has previously been used
    }
}