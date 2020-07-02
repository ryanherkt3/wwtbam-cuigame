package millionaire;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains a master ArrayList which is to be populated with Question 
 * instances, a constructor, get and set methods for this ArrayList, and three 
 * methods which help to populate this ArrayList based on the difficulty of the 
 * questions (determined by me): easyQuestions(), mediumQuestions(), and 
 * hardQuestions(). There's also encapsulation methods for the master ArrayList.
 * 
 * Inside each method is an ArrayList which is populated with Question 
 * instances, a call to Collections.shuffle() for these ArrayLists and a for 
 * loop which starts at the first question number - 1 of each segment (for 
 * example the medium questions begin at question #6, so the loop begins at 5), 
 * and goes up to last question number - 1 of each segment (for example the 
 * last medium question is at question #10, so the loop terminates after i = 9).
 * 
 * Inside the for loop is a call to questions.add(int index, Questions q), which 
 * adds a Question instance to index i, and grabs an item from the ArrayList 
 * inside the method to send to the master ArrayList, where the index of the 
 * item grabbed is between 0 and 5 - the first five items of the list.
 * 
 * @author Ryan (SID: 18022861, Group ID #: 17)
 */
public class AddQuestions 
{
    //The master ArrayList, this will be populated with 15 questions, five from 
    //each of the three categories/methods: easy, medium and hard:
    private ArrayList<Question> questions;
    
    /**
     * Default constructor for this class initializing the master ArrayList.
     */
    public AddQuestions()
    {
        this.questions = new ArrayList<>();     //define as new ArrayList
    }
    
    /**
     * This class creates a new ArrayList of Question instances, populates it 
     * with easy questions, randomly shuffles the contents in the list then 
     * sends the first five items to the master ArrayList.
     */
    public void easyQuestions()
    {
        ArrayList<Question> easy = new ArrayList<>();
        
        //Add fifty questions to the ArrayList, to be shuffled and added to the 
        //master list later:
        easy.add(new Question("Which of these is the name of a beer and a virus?", 
                "Steinlager", "Speights", "Corona", "Carlsberg", "C"));
        easy.add(new Question("What is the name of the red light at the rear of a motor vehicle?", 
                "Brake light", "Floodlight", "Tea light", "Candlelight", "A"));
        easy.add(new Question("Which of these is a New Zealand political party?", 
                "Reds", "Greens", "Blues", "Yellows", "B"));
        easy.add(new Question("Which month does ANZAC day fall on?", "May", "November", "December", "April", "D"));
        easy.add(new Question("James May is known as Captain what?", "Speedy", "Slow", "Haddock", "Underpants", "B"));
        /* -------- (5) */
        easy.add(new Question("Richie McCaw played in which rugby position?", 
                "Prop", "Lock", "Hooker", "Loose forward", "D"));
        easy.add(new Question("Sternum is an anagram of which city?", 
                "Dublin", "Belfast", "Munster", "Connacht", "C"));
        easy.add(new Question("Adolf Hitler became Chancellor of Germany in what year?", 
                "1918", "1921", "1933", "1934", "C"));
        easy.add(new Question("Stephen Hawking specialised in which scientific field?", 
                "Chemistry", "Biology", "Astronomy", "Physics", "D"));
        easy.add(new Question("A common programming output is Hello what?", 
                "Everybody", "World", "Hello", "Boy", "B"));
        /* -------- (10) */
        easy.add(new Question("Which of these is a programming language?", 
                "Serpent", "Viper", "Python", "Nessie", "C"));
        easy.add(new Question("Which event triggered New Zealand's first declaration of a national state of emergency?", "2010 Pike River Mine Disaster", "2011 Christchurch Earthquake", "World War II", "2007 RWC QF loss to France", "B"));
        easy.add(new Question("Prior to its conversion to university halls of residence, what was Carlaw Park?", 
                "Recreational centre", "Giant playground", "Nancy", "Rugby league venue", "D"));
        easy.add(new Question("Pukekohe is the furtherest stop from Britomart on which train line?", 
                "Southern", "Western", "Onehunga", "Eastern", "A"));
        easy.add(new Question("Which of these is a palindrome?", "Cook", "9393", "13579", "Deed", "D"));
        /* -------- (15) */
        easy.add(new Question("Which of these is a type of image file?", "DOC", "PDF", "JPEG", "HTML", "C"));
        easy.add(new Question("Which of these is a (former) name of a TV station in New Zealand?", 
                "One", "Seven", "Nine", "Ten", "A"));
        easy.add(new Question("State of Origin is an annual sports contest between which two Australian states?", 
                "Victoria & SA", "NSW & QLD", "QLD & WA", "WA & SA", "B"));
        easy.add(new Question("IBM's Deep Blue beat which of these chess players in 1997?", 
                "Anatoly Karpov", "Boris Spassky", "Garry Kasparov", "Bobby Fischer", "C"));
        easy.add(new Question("'The Gambler' is a song made famous by which artist?", 
                "Kenny Rogers", "Neil Diamond", "Dolly Parton", "Lionel Richie", "A"));
        /* -------- (20) */
        easy.add(new Question("What is the Maori name for New Zealand?", "Aotearoa", "Aoraki", "Tamaki Makaurau", "Aroha", "A"));
        easy.add(new Question("A person who breeds pigeons is known as a pigeon what?", 
                "Brooder", "Groomer", "Fancier", "Lover", "C"));
        easy.add(new Question("How many circles appear in the Olympic logo?", 
                "Five", "Four", "Three", "Two", "A"));
        easy.add(new Question("When was the last NZ General Election?", 
                "2011", "2013", "2015", "2017", "D"));
        easy.add(new Question("What is the name of the sea separating New Zealand and Australia?", 
                "Abel Sea", "Tasman Sea", "Cook Sea", "TransTasman Sea", "B"));
        /* -------- (25) */
        easy.add(new Question("Jeremy Clarkson was controversially sacked from which show in 2015?", "Fifth Gear", "The Grand Tour", "Top Gear", "The Three Stooges", "C"));
        easy.add(new Question("Christmas is observed in which month?", 
                "November", "December", "January", "February", "B"));
        easy.add(new Question("Kim Jong Un is the supreme leader of which country?", 
                "North Korea", "South Korea", "China", "Japan", "A"));
        easy.add(new Question("When did Donald Trump become inaugurated as President of the US?", 
                "2015", "2016", "2017", "2018", "C"));  //20 Jan 2017
        easy.add(new Question("How many Rugby World Cups did Sir Steve Hansen win as head coach?", 
                "Zero", "Two", "Three", "One", "D"));
        /* -------- (30) */
        easy.add(new Question("Which musician's music video was the first to have one billion YouTube views?", "Justin Bieber", "Psy", "Taylor Swift", "Katy Perry", "B"));
        easy.add(new Question("Which of these is the number one sport in New Zealand?", 
                "Soccer", "Cricket", "Netball", "Rugby Union", "D"));
        easy.add(new Question("The Harbour Bridge is located in which city?", 
                "Auckland", "Wellington", "Christchurch", "Hamilton", "A"));
        easy.add(new Question("Sir John Key represented which political party?", 
                "NZ First", "ACT", "National", "United Future", "C"));
        easy.add(new Question("Which New Zealander was the first to receive the Victoria Cross?", 
                "Willie Apiata", "Richie McCaw", "Corporal D", "Brendon McCullum", "A"));
        /* -------- (35) */
        easy.add(new Question("Baldwin St, the world's steepest street, is in which city?", 
                "Nelson", "Christchurch", "Dunedin", "Queenstown", "C"));
        easy.add(new Question("Which of these is New Zealand's national animal?", 
                "Kiwi", "Kea", "Haast Eagle", "Moa", "A"));
        easy.add(new Question("How many letters appear in the English alphabet?", 
                "25", "26", "27", "28", "B"));
        easy.add(new Question("What is New Zealand's population to the nearest million?", 
                "3", "4", "5", "6", "C"));
        easy.add(new Question("Which of these countries is NOT part of the UK?", 
                "England", "Scotland", "Wales", "Ireland", "D"));
        /* -------- (40) */
        easy.add(new Question("Which of these is NOT an NZ beer?", 
                "Lion Red", "Monteith's", "Carlsberg", "Speights", "C"));
        easy.add(new Question("Three hours consists of how many minutes?", 
                "90", "180", "270", "360", "B"));
        easy.add(new Question("Which of these is the name of a well known treaty and town?", 
                "Russell", "Paihia", "Whangarei", "Waitangi", "D"));
        easy.add(new Question("In what year did World War II end?", 
                "1918", "1939", "1945", "1947", "C"));
        easy.add(new Question("A sleeveless shirt is otherwise known as a what?", 
                "Singlet", "Teepee", "Suit", "Jacket", "A"));
        /* -------- (45) */
        easy.add(new Question("Where is the South Pole located?", 
                "The Arctic", "Arctic Ocean", "Antarctica", "Southern Ocean", "C"));
        easy.add(new Question("A rugby ball is what shape?", 
                "Rectangle", "Circle", "Diamond", "Oval", "D"));
        easy.add(new Question("Which of these is a logo for many New Zealand national sporting teams?", 
                "Fern", "Clover", "Kiwi", "Rose", "A"));
        easy.add(new Question("New Zealand's Parliament building is known by what name?", 
                "The Basin", "The Beehive", "Cake Tin", "Hornet's Nest", "B"));
        easy.add(new Question("What is the first name of the person behind Newton's Laws?", 
                "Albert", "Isaac", "Stephen", "Ben", "B"));
        /* -------- (50) */
        
        Collections.shuffle(easy);  //shuffle current items in list
        for (int i = 0; i < 5; i++)   //add questions 1 to 5
            questions.add(i, easy.get(i));
    }
    
    /**
     * This class creates a new ArrayList of Question instances, populates it 
     * with medium level questions, randomly shuffles the contents in the list then 
     * sends the first five items to the master ArrayList.
     */
    public void mediumQuestions()
    {
        ArrayList<Question> medium = new ArrayList<>();
        
        //Add fifty questions to the ArrayList, to be shuffled and added to the 
        //master list later:
        medium.add(new Question("In which of these towns can you find the famous Pania statue?", 
                "Whangarei", "Napier", "Dunedin", "Gisborne", "B"));
        medium.add(new Question("In 501-darts, what's the fewest amount of darts it takes to get 501?", 
                "9", "10", "11", "12", "A"));
        medium.add(new Question("Kabul is the capital of which country?", 
                "Pakistan", "Bangladesh", "Nepal", "Afghanistan", "D"));
        medium.add(new Question("The International Rugby Academy NZ is run by which former rugby star?", 
                "Dan Carter", "Richie McCaw", "Murray Mexted", "Justin Marshall", "C"));
        medium.add(new Question("The biggest day of the U.S. presidential primary season is known as Super when?", 
                "Monday", "Tuesday", "Thursday", "Saturday", "B"));
        /* -------- (5) */
        medium.add(new Question("Which boxing title remains the only one Joseph Parker has won?", 
                "IBF", "WBC", "WBA", "WBO", "D"));
        medium.add(new Question("In which city does the band Six60 originate from?", 
                "Auckland", "Wellington", "Christchurch", "Dunedin", "D"));
        medium.add(new Question("Which New Zealand town has the big sheep and dog buildings?", 
                "Matamata", "Tirau", "Ohakune", "Paeroa", "B"));
        medium.add(new Question("Twitter was founded in what year?", "2004", "1998", "2006", "2005", "C"));
        medium.add(new Question("What was the name of the bat New Zealand cricketer Lance Cairns was known for using?", 
                "Woodworm", "Excalibur", "Gray-Nicolls", "Kookaburra", "B"));
        /* -------- (10) */
        medium.add(new Question("Who was the second man to walk the moon?", 
                "Neil Armstrong", "Gene Cernan", "Alan Bean", "Buzz Aldrin", "D"));
        medium.add(new Question("What was the acronym of the protest group against the 1981 Springbok Tour?", "BART", 
                "MART", "PART", "HART", "D"));
        medium.add(new Question("The mathematical expression '4 x 3 x 2 x 1' can be better expressed as 4 'what'?", 
                "Squared (^2)", "Factorial (!)", "Pi (π)", "Square (□)", "B"));
        medium.add(new Question("The O in NATO stands for what?", 
                "Operations", "Overlords", "Organization", "Orders", "C"));
        medium.add(new Question("A cheesecutter goes on what part of the body?", 
                "Hand", "Feet", "Head", "Stomach", "C"));
        /* -------- (15) */
        medium.add(new Question("The 'Father/Mother of the House' is an unofficial title given to which MP?", 
                "Youngest", "Longest continuously serving", "Oldest", "Speaker", "B"));
        medium.add(new Question("James May was fired from which car magazine?", 
                "Top Gear", "DriveTribe", "Car", "Autocar", "D"));
        medium.add(new Question("Which of these countries lies entirely in South Africa?", 
                "Namibia", "Mozambique", "Lesotho", "Zimbabwe", "C"));
        medium.add(new Question("Dan Carter scored how many points in the Second Lions Test in 2005?", 
                "11", "22", "33", "44", "C"));
        medium.add(new Question("The Sky Tower has how many levels/stories?", "50", "51", "60", "49", "C"));
        /* -------- (20) */
        medium.add(new Question("The sum of one suit of cards (Ace to King) equals what number?", 
                "90", "91", "92", "93", "B"));
        medium.add(new Question("In what geographical region did the Orient Express operate in?", 
                "SE Asia", "Europe", "Africa", "Middle East", "B"));
        medium.add(new Question("Wayne Smith has coached in which sport?", 
                "Soccer", "Cricket", "Rugby league", "Rugby union", "D"));
        medium.add(new Question("Which team won the 2019 Mitre 10 Cup?", 
                "Auckland", "Tasman", "Canterbury", "Wellington", "B"));
        medium.add(new Question("Nelson Mandela was president of which country?", 
                "Zimbabwe", "Namibia", "South Africa", "Egypt", "C"));
        /* -------- (25) */
        medium.add(new Question("New Zealand cricketer Trent Boult was born in which city?", 
                "Rotorua", "Tauranga", "Hamilton", "Taupo", "A"));
        medium.add(new Question("Australian cricketer Steve Smith was banned for what offence in 2018?", 
                "Match fixing", "Corruption", "Ball tampering", "Using an illegal bat", "C"));
        medium.add(new Question("How many continents is Russia a part of?", 
                "One", "Two", "Three", "Zero", "B"));
        medium.add(new Question("Ping-pong is the colloquial name for which sport?", 
                "Squash", "Tennis", "Croquet", "Table tennis", "D"));
        medium.add(new Question("Who is the current mayor of Auckland?",
                "Len Brown", "Phil Goff", "John Banks", "John Key", "B"));
        /* -------- (30) */
        medium.add(new Question("What was the result of the Tests in the 1981 Springbok Tour?", 
                "3-0 NZ", "3-0 SA", "2-1 NZ", "2-1 SA", "C"));
        medium.add(new Question("What is the name of the treaty between New Zealand, Australia and the USA?", 
                "NZAUS", "USANZ", "AUSNZ", "ANZUS", "D"));
        medium.add(new Question("Barack Obama was what president number?", 
                "42", "44", "41", "43", "B"));
        medium.add(new Question("Which of these is a piece of cricket equipment and furniture item?", 
                "Pad", "Box", "Ball", "Glove", "B"));
        medium.add(new Question("Which city is known as the sporting capital of the world?",
                "Melbourne", "London", "Athens", "Tokyo", "A"));
        /* -------- (35) */
        medium.add(new Question("Reddit was founded in what year?", 
                "2003", "2004", "2005", "2002", "C"));
        medium.add(new Question("Craig Bellamy coaches which NRL team?", 
                "Brisbane Broncos", "Sydney Roosters", "Cronulla Sharks", "Melbourne Storm", "D"));
        medium.add(new Question("Cape Canaveral is in which US state?", 
                "Texas", "Florida", "Washington", "California", "B"));
        medium.add(new Question("A Coldplay band member and ex-New Zealand cricketer share what name?", 
                "Chris Martin", "Daniel Vettori", "Peter Fulton", "Jamie How", "A"));
        medium.add(new Question("Tim Shadbolt is the mayor of which city?",
                "Christchurch", "Invercargill", "Dunedin", "Queenstown", "B"));
        /* -------- (40) */
        medium.add(new Question("On what date did the Japanese attack Pearl Harbor occur (1941)?", 
                "7 September", "7 October", "7 November", "7 December", "D"));
        medium.add(new Question("Latrell Mitchell plays for which NRL team?", 
                "St George Dragons", "Sydney Roosters", "South Sydney Rabbitohs", "Melbourne Storm", "C"));
        medium.add(new Question("Which of these is an e-scooter business?", 
                "Lemon", "Lime", "Banana", "Orange", "B"));
        medium.add(new Question("Molesworth Street is in which city?", 
                "Wellington", "Hamilton", "Christchurch", "New Plymouth", "A"));
        medium.add(new Question("Whakaari is the Maori name for which island?",
                "Waiheke Island", "White Island", "Great Barrier Island", "Motatapu Island", "B"));
        /* -------- (45) */
        medium.add(new Question("What was the nationaility of the terrorists who sunk the Rainbow Warrior?", 
                "German", "French", "Italian", "Spanish", "B"));
        medium.add(new Question("The Pope resides in which country?", 
                "Italy", "Argentina", "Switzerland", "Vatican City", "D"));
        medium.add(new Question("Who was the original Millionaire UK host on ITV?", 
                "Chris Tarrant", "Jeremy Clarkson", "Anne Robinson", "Jeremy Vine", "A"));
        medium.add(new Question("Billy Bowden suffers from which health condition?", 
                "IBS", "Crohn's", "Arthritis", "Deafness", "C"));
        medium.add(new Question("South Africa came out of apartheid in (roughly) which year?",
                "1990", "1992", "1994", "1996", "C"));
        /* -------- (50) */
        
        Collections.shuffle(medium);    //shuffle current items in list
        for (int i = 5; i < 10; i++)   //add questions 5 to 10
            questions.add(i, medium.get(i-5));  //subtract by 5 to get items 0-5
    }
    
    /**
     * This class creates a new ArrayList of Question instances, populates it 
     * with hard questions, randomly shuffles the contents in the list then 
     * sends the first five items to the master ArrayList.
     */
    public void hardQuestions()
    {
        ArrayList<Question> hard = new ArrayList<>();
        
        //Add forty questions to the ArrayList, to be shuffled and added to the 
        //master list later:
        hard.add(new Question("What was the end result of the 2019 Cricket World Cup Final?", 
                "Tie", "Washout", "Win on countback", "Outright win", "C"));
        hard.add(new Question("Which of these U.S. presidents does NOT feature on Mount Rushmore?", 
                "Abraham Lincoln", "Thomas Jefferson", "George Washington", "Franklin D. Roosevelt", "D"));
        hard.add(new Question("What type of wild animal is an ibex?", "Hare", "Deer", "Pig", "Goat", "D"));
        hard.add(new Question("Which of these international NZ rugby players was the first to get a red card?", 
                "Sonny Bill Williams", "Cyril Brownlie", "Scott Barrett", "Colin Meads", "B"));
        hard.add(new Question("Which of these colours appears on the flags of Colombia, Thailand and Belgium?", 
                "Blue", "Green", "Red", "Yellow", "C"));
        /* -------- (5) */
        hard.add(new Question("What was the name of the Soviet dog who oribted the Earth in 1957?", 
                "Laika", "Kaila", "Anna", "Alexandra", "A"));
        hard.add(new Question("Who was the first president to be impeached?", "Andrew Johnson", "Bill Clinton", 
                "Richard Nixon", "Donald Trump", "A"));
        hard.add(new Question("Who wrote the song 'American Pie'?", 
                "Madonna", "Don McLean", "Buddy Holly", "Tyson Fury", "B"));
        hard.add(new Question("What was the time difference between the deaths of Thomas Jefferson and John Adams?", 
                "0 days", "1 day", "1 week", "1 month", "A"));
        hard.add(new Question("How many NRL games did Paul Kent play?", "64", "28", "1", "193", "C"));
        /* -------- (10) */
        hard.add(new Question("Which of these is a name of an Egghead from the quiz show?", 
                "Shane", "Barry", "Nancy", "Charlie", "B"));
        hard.add(new Question("Which of these is a synonym for 'ostensibly'?", 
                "Apparently", "Legitimately", "Ironically", "Fatefully", "A"));
        hard.add(new Question("Where is New Zealand's highest finish at a FIFA World Cup (all ages, both genders)?", 
                "Runners-up", "Third", "Quarter finalists", "Second round", "B"));
        hard.add(new Question("Which of these TV shows did Bear Grylls host?", 
                "Survivor", "Shark Tank", "Born Survivor", "Naked and Afraid", "C"));
        hard.add(new Question("How old will Helen Clark be at the end of 2020?", "65", "68", "70", "72", "C"));
        /* -------- (15) */
        hard.add(new Question("Who was the first New Zealand rugby commentator "
                + "to call 300 Tests involving New Zealand?", "Keith Quinn", 
                "Tony Johnson", "Grant Nisbett", "John McBeth", "C"));
        hard.add(new Question("The Kansas City Chiefs are located in which US state?", 
                "Kansas", "Missouri", "Colorado", "Oklahoma", "B"));
        hard.add(new Question("Which sport features a 50 metre penalty?", 
                "Australian Rules Football", "Rugby League", "Rugby Union", "American Football", "A"));
        hard.add(new Question("Peter Oakley went by which username on YouTube?", 
                "geriatric1927", "POakley1927", "The Internet Grandad", "Peter Oakley", "A"));
        hard.add(new Question("From 1980, how many years did the West Indies cricket "
                + "team go without losing a Test series?", "10", "15", "20", "25", "B"));
        /* -------- (20) */
        hard.add(new Question("Which of these is the largest desert?", 
                "Antarctic", "Sahara", "Gobi", "Atacama", "A"));
        hard.add(new Question("What is the name of Newcastle Knights legend Andrew Johns' brother?", 
                "Ben", "Greg", "Matthew", "Paul", "C"));
        hard.add(new Question("Australia and England compete for which cricket trophy every two years?", 
                "Border-Gavaskar Trophy", "The Ashes", "Chappell-Hadlee Trophy", "Bradman-Grace Trophy", "B"));
        hard.add(new Question("Malcolm Black was the lead singer for which Kiwi hit song?", 
                "Ten Guitars", "Bliss", "Don't Dream It's Over", "For Today", "D"));
        hard.add(new Question("Fleetwood Mac recently added which Kiwi musician to its band?", 
                "Neil Finn", "Stan Walker", "Tim Finn", "Tiki Taane", "A"));
        /* -------- (25) */
        hard.add(new Question("Who conducted the famous Nixon interviews in 1977?", 
                "John Humphrys", "Michael Parkinson", "David Frost", "Trevor McDonald", "C"));
        hard.add(new Question("How many miles is in a marathon (nearest number)?", 
                "13", "26", "39", "42", "B"));
        hard.add(new Question("What animal features on the WWF logo?", 
                "Panda", "Polar bear", "Tiger", "Elephant", "A"));
        hard.add(new Question("Paul Henry controversially mocked which politician in 2010?", 
                "Gordon Brown", "Kevin Rudd", "Moe Ron", "Sheila Dikshit", "D"));
        hard.add(new Question("Newstalk ZB broadcaster Andrew Dickens had which type of cancer in 2019?", 
                "Prostate", "Pancreas", "Lung", "Liver", "A"));
        /* -------- (30) */
        hard.add(new Question("What is the Otago rugby team's nickname?", 
                "Stags", "Makos", "Razorbacks", "Turbos", "C"));
        hard.add(new Question("Sir John Key was a Minister of what?", 
                "Finance", "Primary Industries", "Roading", "Tourism", "D"));
        hard.add(new Question("What is Abraham Lincoln's famous 1863 address known as?", 
                "Washington Rally", "Gettysburg Address", "Philadelphia Protest", "Chicago Speech", "B"));
        hard.add(new Question("Which of these represents Alec Stewart's birthday and his test run tally for England?", 
                "8/4/63", "4/8/68", "11/3/61", "6/9/69", "A"));
        hard.add(new Question("Possum Bourne was from which Auckland town/suburb?", 
                "Pokeno", "Tuakau", "Pukekohe", "Waiuku", "C"));
        /* -------- (35) */
        hard.add(new Question("How many years did Leighton Smith work on Newstalk ZB's 8:30 to midday show?", 
                "24", "28", "33", "30", "C"));
        hard.add(new Question("Baron von Haussman renovated which European city?", 
                "Rome", "Paris", "Berlin", "Madrid", "B"));
        hard.add(new Question("Charles Ingram, the Millionaire coughing cheat, held which Army rank?", 
                "Major", "Sergeant", "Lieutenant", "Captain", "A"));
        hard.add(new Question("Counties Manukau will play in which provincial rugby competition in 2020?", 
                "Mitre 10 Cup's Premiership division", "Meads Cup", "Lochore Cup", 
                "Mitre 10 Cup's Championship division", "D"));
        hard.add(new Question("Sir Donald Bradman scored how many runs in Australia's "
                + "1930/31 Test series against England?", "732", "812", "974", "1067", "C"));
        /* -------- (40) */
        
        Collections.shuffle(hard);  //shuffle current items in list
        for (int i = 10; i < 15; i++)   //add questions 10 to 15
            questions.add(i, hard.get(i-10));  //subtract by 10 to get items 0-5
    }

    /**
     * @return the questions
     */
    public ArrayList<Question> getQuestions() 
    {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(ArrayList<Question> questions) 
    {
        this.questions = questions;
    }
}