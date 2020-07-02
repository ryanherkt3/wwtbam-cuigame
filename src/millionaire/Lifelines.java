package millionaire;

import java.util.Random;

/**
 * This Lifelines class is an abstract class with an abstract method for the 
 * implementation of the lifeline. The FiftyFifty, AskAudience and PhoneAFriend 
 * classes all use this by extending this class. This class also contains 
 * data encapsulation for the boolean used status (isUsed & set methods) and 
 * questionNumber and a constructor which sets questionNumber and 
 * fiftyFiftyQuestion to 16, and firstWrong and secondWrong to empty strings.
 * 
 * It also has six protected variables: int questionNumber, Random integer, 
 * int option, int fiftyFiftyQuestion and String firstWrong and secondWrong, 
 * which are all encapsulated and can be accessed by any of the subclasses 
 * of this class.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public abstract class Lifelines 
{
    private boolean used;   //flag to determine if lifeline has been used
    
    //set as protected access modifier to maximise code reuse:
    protected int questionNumber;     //when lifeline was used
    protected Random integer;  
    protected int option; //to randomise the confidence level the friend has in their answer
    protected String firstWrong, secondWrong; //for the two incorrect answers
    protected int fiftyFiftyQuestion; //when 5050 lifeline is being used

    /**
     * This default Lifelines constructor has no parameters, and initializes/defines 
     * the Random integer, used, integer questionNumber, firstWrong and 
     * secondWrong values.
     * 
     * questionNumber and fiftyFiftyQuestion are both set out of bounds to 16 as 
     * all the lifelines will be unused to start with.
     */
    public Lifelines()
    {
        this.used = false;
        this.questionNumber = 16;       //set deliberately out of bounds
        this.fiftyFiftyQuestion = 16;   //set deliberately out of bounds
        this.integer = new Random();
        this.firstWrong = "";
        this.secondWrong = "";
    }
    
    /**
     * This data encapsulation method returns the question where the 50/50 
     * lifeline was used (if applicable).
     * 
     * @return the fiftyFiftyQuestion
     */
    public int getFiftyFiftyQuestion() 
    {
        return fiftyFiftyQuestion;
    }

    /**
     * This data encapsulation method sets the question where the 50/50 
     * lifeline was used (if applicable).
     * 
     * @param fiftyFiftyQuestion the fiftyFiftyQuestion to set
     */
    public void setFiftyFiftyQuestion(int fiftyFiftyQuestion) 
    {
        this.fiftyFiftyQuestion = fiftyFiftyQuestion;
    }

    /**
     * This data encapsulation method returns whether the lifeline has been 
     * used or not (true/false)
     * 
     * @return the used
     */
    public boolean isUsed() 
    {
        return used;
    }
    
    /**
     * This data encapsulation method sets the used value to true or false
     * 
     * @param used
     */
    public void setUsed(boolean used)
    {
        this.used = used;
    }
    
    /**
     * This abstract method invokes the algorithm for the lifeline, taking in 
     * the question number so it can get the correct answer. This ensures the 
     * lifelines can do operations which can either eliminate two wrong answers 
     * (50/50), give a confidence level for an answer (Phone A Friend), or poll 
     * the audience (Ask The Audience), where the correct answer is weighted to 
     * a higher random range than the other answers.
     * 
     * @param question
     * @return String representation of the result of the lifeline
     */
    public abstract String lifeline(Question question);

    /**
     * This data encapsulation method returns the questionNumber which 
     * the lifeline was used for
     * 
     * @return the questionNumber
     */
    public int getQuestionNumber() 
    {
        return questionNumber;
    }

    /**
     * This data encapsulation method sets the questionNumber
     * 
     * @param questionNumber the questionNumber to set
     */
    public void setQuestionNumber(int questionNumber) 
    {
        this.questionNumber = questionNumber;
    }
    
    /**
     * This data encapsulation method returns an instance of a Random variable
     * 
     * @return the integer
     */
    public Random getInteger() 
    {
        return integer;
    }

    /**
     * This data encapsulation method sets an instance of a Random variable
     * 
     * @param integer the integer to set
     */
    public void setInteger(Random integer) 
    {
        this.integer = integer;
    }
    
    /**
     * This data encapsulation method returns the option number the 'computer' 
     * takes when determining the two random wrong answers
     * 
     * @return option as int
     */
    public int getOption()
    {
        return option;
    }
    
    /**
     * This data encapsulation method sets the option number the 'computer' 
     * takes when determining the two random wrong answers
     * 
     * @param option as int
     */
    public void setOption(int option)
    {
        this.option = option;
    }
    
    /**
     * This data encapsulation method returns the first of two randomly 
     * selected wrong answers
     * 
     * @return firstWrong as String
     */
    public String getFirstWrong()
    {
        return firstWrong;
    }
    
    /**
     * This data encapsulation method sets the first of two randomly 
     * selected wrong answers
     * 
     * @param firstWrong as String
     */
    public void setFirstWrong(String firstWrong)
    {
        this.firstWrong = firstWrong;
    }
    
    /**
     * This data encapsulation method returns the second of two randomly 
     * selected wrong answers
     * 
     * @return secondWrong as String
     */
    public String getSecondWrong()
    {
        return secondWrong;
    }
    
    /**
     * This data encapsulation method sets the second of two randomly 
     * selected wrong answers
     * 
     * @param secondWrong as String
     */
    public void setSecondWrong(String secondWrong)
    {
        this.secondWrong = secondWrong;
    }
}