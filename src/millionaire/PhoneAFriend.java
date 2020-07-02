package millionaire;

/**
 * This PhoneAFriend class contains the algorithm for the Phone A Friend lifeline 
 * (lifeline method). It also has a default constructor.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class PhoneAFriend extends Lifelines
{
    /**
     * This default PhoneAFriend constructor only calls the Lifelines superclass, 
     * as all variables are declared there in its constructor.
     */
    public PhoneAFriend() 
    {
        super();
    }
    
    /**
     * This method contains the algorithm for the Phone A Friend lifeline. The 
     * algorithm first sets option as a random integer between 0 and 2 (for the 
     * confidence level of the player), declares confidenceLevel as an int, and 
     * defines the friendConfidence as empty (ie ""). 
     * 
     * After this, it checks if the lifeline has been used. If so, it tells the 
     * user it has already used the lifeline, otherwise the algorithm continues.
     * 
     * It then checks if the 50/50 lifeline has been used on the same question, 
     * where there are two cases. 
     * 
     * Case 1 - 50/50 not used on the question, or prior to it, or on a 
     * different question:
     * A switch is performed on the option int, where if option = 0 a string 
     * returns saying the user doesn't know the answer. If option = 1, a random 
     * confidence level between 40%-70% is chosen, with a random number 
     * generated from 0 to 3 (A to D). A second switch is then performed on 
     * option, where the friendConfidence string is returned along with 
     * " [answer]". If option = 2, a random confidence level between 
     * 70%-100% is chosen, with a string returned saying the user is x% confident 
     * in the right answer being the correct answer.
     * 
     * Case 2 - 50/50 was used prior to the question:
     * A switch is performed on the option int, where if option = 0 a string 
     * returns saying the user doesn't know the answer. If option = 1, a random 
     * confidence level between 40%-70% is chosen and there are six if/else statements, 
     * which check which two answers were eliminated with the 50/50 lifeline, 
     * until it finds the two letter choices that were eliminated. Inside these 
     * if statements, another switch is performed on option to randomly generate 
     * either 0 or 1, which determines the answer the friend chooses. The final 
     * result is then returned.
     * 
     * In both cases setUsed(true) is called. Also in both cases, if after the 
     * the first time option is set to a random number and it equals 2, 
     * a random confidence level between 70%-100% is chosen, with a string 
     * returned saying the user is x% confident in the right answer being the 
     * correct answer.
     * 
     * @param question as Question
     * @return lifeline outcome as String
     */
    @Override
    public String lifeline(Question question) 
    {
        //random options - 0 if unsure, 1 if slightly sure, 2 if extremely confident:
        option = getInteger().nextInt(3);
        int confidenceLevel;
        String poll = "";
        
        if(!isUsed())
        {
            setUsed(true);
            /* Thread's sleep method called in order to delay result of 
            lifeline to user by 2 seconds */
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException ex) {}

            //case 1: Phone A Friend if the 50/50 lifeline hasn't been used; or 
            //has been used, but for a different question:
            if (getFiftyFiftyQuestion() != getQuestionNumber())  //only activates if lifeline hasn't been used
            {
                switch(option)
                {
                    case 0:
                        poll = "I don't know the answer sorry.";
                        break;
                    case 1:
                        confidenceLevel = (getInteger().nextInt(4)+4) * 10;   //min 40 max 70
                        poll = "I'm " + confidenceLevel + "% confident the answer is ";

                        option = getInteger().nextInt(4);    //for the four options A-D
                        switch(option)  //reuse to determine which answer user is unsure with
                        {
                            case 0:
                                poll += "A";
                                break;
                            case 1:
                                poll += "B";
                                break;
                            case 2:
                                poll += "C";
                                break;
                            case 3:
                                poll += "D";
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        confidenceLevel = (getInteger().nextInt(4)+7) * 10;   //min 70 max 100
                        poll = "I'm " + confidenceLevel + "% confident the answer is " + question.getRightAns();
                        break;
                    default:
                        break;
                }
            }
            //case 2: Phone A Friend if the 50/50 lifeline has been used prior 
            //to the Phone A Friend lifeline for the same question:
            else if (getFiftyFiftyQuestion() == getQuestionNumber())
            {
                switch(option)
                {
                    case 0:
                        poll = "I don't know the answer sorry.";
                        break;
                    case 1:
                    {
                        confidenceLevel = (getInteger().nextInt(4)+4) * 10;   //min 40 max 70
                        poll = "I'm " + confidenceLevel + "% confident the answer is ";

                        if (getFirstWrong().equals("A") && 
                                getSecondWrong().equals("B"))    //A or B
                        {
                            option = getInteger().nextInt(2);//for the two remaining options
                            switch(option)  //reuse to determine which answer user is unsure with
                            {
                                case 0:
                                    poll += "C";
                                case 1:
                                    poll += "D";
                                default:
                                    break;
                            }
                        }
                        else if (getFirstWrong().equals("A") && 
                                getSecondWrong().equals("C"))    //A or C
                        {
                            option = getInteger().nextInt(2);//for the two remaining options
                            switch(option)  //reuse to determine which answer user is unsure with
                            {
                                case 0:
                                    poll += "B";
                                case 1:
                                    poll += "D";
                                default:
                                    break;
                            }
                        }
                        else if (getFirstWrong().equals("A") && 
                                getSecondWrong().equals("D"))    //A or D
                        {
                            option = getInteger().nextInt(2);//for the two remaining options
                            switch(option)  //reuse to determine which answer user is unsure with
                            {
                                case 0:
                                    poll += "B";
                                case 1:
                                    poll += "C";
                                default:
                                    break;
                            }
                        }
                        else if (getFirstWrong().equals("B") && 
                                getSecondWrong().equals("C"))    //B or C
                        {
                            option = getInteger().nextInt(2);//for the two remaining options
                            switch(option)  //reuse to determine which answer user is unsure with
                            {
                                case 0:
                                    poll += "A";
                                case 1:
                                    poll += "D";
                                default:
                                    break;
                            }
                        }
                        else if (getFirstWrong().equals("B") && 
                                getSecondWrong().equals("D"))    //B or D
                        {
                            option = getInteger().nextInt(2);//for the two remaining options
                            switch(option)  //reuse to determine which answer user is unsure with
                            {
                                case 0:
                                    poll += "A";
                                case 1:
                                    poll += "C";
                                default:
                                    break;
                            }
                        }
                        else if (getFirstWrong().equals("C") && 
                                getSecondWrong().equals("D"))    //C or D
                        {
                            option = getInteger().nextInt(2);//for the two remaining options
                            switch(option)  //reuse to determine which answer user is unsure with
                            {
                                case 0:
                                    poll += "A";
                                case 1:
                                    poll += "B";
                                default:
                                    break;
                            }
                        }
                    }
                    case 2:
                    {
                        confidenceLevel = (getInteger().nextInt(4)+7) * 10;   //min 70 max 100
                        poll = "I'm " + confidenceLevel + "% confident the answer is " + question.getRightAns();
                        break;
                    }
                }
            }
            return poll;
        }
        else 
            return "You have already used this lifeline!";  //if lifeline has previously been used
    }
}