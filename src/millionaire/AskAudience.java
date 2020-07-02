package millionaire;
/**
 * This AskAudience class contains the algorithm for the Ask the Audience lifeline 
 * (lifeline method). It also has a default constructor.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class AskAudience extends Lifelines
{
    /**
     * This default AskAudience constructor only calls the Lifelines superclass, 
     * as all variables are declared there in its constructor.
     */
    public AskAudience() 
    {
        super();
    }
    
    /**
     * This method contains the algorithm for the Ask the Audience lifeline. 
     * The algorithm has two cases (in addition to the one where the lifeline 
     * has already been used, in this case the user will receive a message 
     * saying they've already used this lifeline). String poll is initialized 
     * at the start of the method to an empty string.
     * 
     * Case 1 checks if this lifeline isn't being used at the same time as the 
     * 50/50 lifeline (so either before it on the same question, or 
     * after it on a different question). If this is the case, each member of 
     * the votes array will be set to a random number, where the correct answer 
     * (votes[0]) is deliberately set to higher bounds than the rest of the 
     * members of the array. The numbers will be set and reset until all four 
     * add up to 100. After this, a switch case is used on the right answer, 
     * where poll is set to the result of the audience poll (e.g. votes[0] + 
     * "% voted for A, " + votes[1] + "% voted for B, " + votes[2] + "% voted 
     * for C, and " + votes[3] + "% voted for D."). setUsed(true) is then called, 
     * before String poll is returned to the caller.
     * 
     * Case 2 is very similar to case 1, although because this case only 
     * triggers when the 50/50 lifeline is used before this one on the same 
     * question, there are only two options to vote for. So two members of the 
     * votes array will be set and reset until they add up to 100, but like in 
     * case 1, the correct answer (votes[0]) is deliberately set to higher bounds 
     * than the rest of the members of the array (except when the question is a 
     * hard one). An if/else block runs, finding both the eliminated answers 
     * from the 50/50 lifeline. Once the two choices are found, another if-else 
     * chain runs finding the correct answer, then the poll String will be set 
     * to the result with votes[0]'s number appearing beside the correct answer. 
     * The user will not know which is the correct answer, however. setUsed(true) 
     * is then called, before String poll is returned to the caller.
     * 
     * For both cases, the values in the votes array are weighted based on the 
     * difficulty of the question. For example, an easier question (with an 
     * index from 0 to 4) will see a majority of the votes go to the option 
     * which is the correct answer (votes[0]).
     * 
     * @param question
     * @return lifeline outcome as string
     */
    @Override
    public String lifeline(Question question) 
    {
        String poll = "";
        int[] votes = new int[]{0, 0, 0, 0};
            
        //case 1: Ask Audience poll if the 50/50 lifeline hasn't been used; or 
        //has been used, but for a different question:
        if (!isUsed() && getFiftyFiftyQuestion() != getQuestionNumber())  //only activates if lifeline hasn't been used
        {
            System.out.println("A, B, C or D - all vote now!");
            
            /* Thread's sleep method called in order to delay result of 
            lifeline to user by 2 seconds */
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException ex) {}
            
            while (votes[0] + votes[1] + votes[2] + votes[3] != 100)
            {
                //weight percentage balance according to question difficulty:
                if (getQuestionNumber() < 5)    //an easy question
                {
                    votes[0] = integer.nextInt(31)+70;   //min 70 max 100; right answer
                    votes[1] = integer.nextInt(11)+integer.nextInt(6);//0-15
                    votes[2] = integer.nextInt(11)+integer.nextInt(6);//0-15
                    votes[3] = integer.nextInt(11)+integer.nextInt(6);//0-15 
                }
                else if (getQuestionNumber() < 10)    //a medium question
                {
                    votes[0] = integer.nextInt(26)+45;    //min 45 max 70; right answer
                    votes[1] = integer.nextInt(16)+10;    //10-25
                    votes[2] = integer.nextInt(16)+10;    //10-25
                    votes[3] = integer.nextInt(16)+10;    //10-25
                }
                else //a hard question
                {
                    votes[0] = integer.nextInt(36)+20;    //min 20 max 55; right answer
                    votes[1] = integer.nextInt(26)+15;    //15-40
                    votes[2] = integer.nextInt(26)+15;    //15-40
                    votes[3] = integer.nextInt(26)+15;    //15-40
                }
            }
            
            //set poll as the result for the matching case:
            switch (question.getRightAns())
            {
                case "A":
                    poll = votes[0] + "% voted for A, " + votes[1] + "% voted for B, " + votes[2] 
                            + "% voted for C, and " + votes[3] + "% voted for D.";
                    break;
                case "B":
                    poll = votes[1] + "% voted for A, " + votes[0] + "% voted for B, " + votes[2] 
                            + "% voted for C, and " + votes[3] + "% voted for D.";
                    break;
                case "C":
                    poll = votes[1] + "% voted for A, " + votes[2] + "% voted for B, " + votes[0] 
                            + "% voted for C, and " + votes[3] + "% voted for D.";
                    break;
                case "D":
                    poll = votes[1] + "% voted for A, " + votes[2] + "% voted for B, " + votes[3] 
                            + "% voted for C, and " + votes[0] + "% voted for D.";
                    break;
                default:
                    break;
            }
            setUsed(true);    //stops abuse of the lifeline
            return poll;
        }
        //case 2: Ask Audience poll if the 50/50 lifeline has been used prior 
        //to the Ask Audience lifeline for the same question:
        else if (!isUsed() && getFiftyFiftyQuestion() == getQuestionNumber())
        {
            //tell 'audience' which answers are eliminated:
            System.out.println(getFirstWrong() + " and " + getSecondWrong() 
                + " have been eliminated! Audience all vote now on the "
                + "remaining two options!");
            
            /* Thread's sleep method called in order to delay result of 
            lifeline to user by 2 seconds */
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException ex) {}
            
            while (votes[0] + votes[1] != 100)
            {
                //weight percentage balance according to question difficulty:
                if (getQuestionNumber() < 5)    //an easy question
                {
                    votes[0] = integer.nextInt(31)+75;  //min 75 max 100, right answer
                    votes[1] = integer.nextInt(26);     //0-25
                }
                else if (getQuestionNumber() < 10)    //a medium question
                {
                    votes[0] = integer.nextInt(46)+50;    //min 50 max 90, right answer
                    votes[1] = integer.nextInt(41)+10;    //10-50
                }
                else //a hard question
                {
                    votes[0] = integer.nextInt(66)+35;    //min 35 max 100
                    votes[1] = integer.nextInt(66)+35;    //min 35 max 100
                }
            }
            
            //set poll as the result for the matching case:
            if (getFirstWrong().equals("A") && getSecondWrong().equals("B"))
            {
                if (question.getRightAns().equals("C"))
                    poll = votes[0] + "% voted for C, and " + votes[1] + "% voted for D.";
                else if (question.getRightAns().equals("D"))
                    poll = votes[1] + "% voted for C, and " + votes[0] + "% voted for D.";
            }
            else if (getFirstWrong().equals("A") && getSecondWrong().equals("C"))
            {
                if (question.getRightAns().equals("B"))
                    poll = votes[0] + "% voted for B, and " + votes[1] + "% voted for D.";
                else if (question.getRightAns().equals("D"))
                    poll = votes[1] + "% voted for B, and " + votes[0] + "% voted for D.";
            }
            else if (getFirstWrong().equals("A") && getSecondWrong().equals("D"))
            {
                if (question.getRightAns().equals("B"))
                    poll = votes[0] + "% voted for B, and " + votes[1] + "% voted for C.";
                else if (question.getRightAns().equals("C"))
                    poll = votes[1] + "% voted for B, and " + votes[0] + "% voted for C.";
            }
            else if (getFirstWrong().equals("B") && getSecondWrong().equals("C"))
            {
                if (question.getRightAns().equals("A"))
                    poll = votes[0] + "% voted for A, and " + votes[1] + "% voted for D.";
                else if (question.getRightAns().equals("D"))
                    poll = votes[1] + "% voted for A, and " + votes[0] + "% voted for D.";
            }
            else if (getFirstWrong().equals("B") && getSecondWrong().equals("D"))
            {
                if (question.getRightAns().equals("A"))
                    poll = votes[0] + "% voted for A, and " + votes[1] + "% voted for C.";
                else if (question.getRightAns().equals("C"))
                    poll = votes[1] + "% voted for A, and " + votes[0] + "% voted for C.";
            }
            else if (getFirstWrong().equals("C") && getSecondWrong().equals("D"))
            {
                if (question.getRightAns().equals("A"))
                    poll = votes[0] + "% voted for A, and " + votes[1] + "% voted for B.";
                else if (question.getRightAns().equals("B"))
                    poll = votes[1] + "% voted for A, and " + votes[0] + "% voted for B.";
            }
            
            setUsed(true);    //stops abuse of the lifeline
            return poll;
        }
        else 
            return "You have already used this lifeline!";  //if lifeline has previously been used
    }
}