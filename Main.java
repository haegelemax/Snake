import java.util.*;
import java.util.Scanner; 
import java.util.Random;
public class Main
{
	public static void main(String[] args) {
		Play run = new Play();
		run.playGame();
	}
}
class UI {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void drawDivider() {
        System.out.println(CYAN + "====================================================" + RESET);
    }

    public static void header(String text) {
        drawDivider();
        System.out.println("          " + YELLOW + "--- " + text.toUpperCase() + " ---" + RESET);
        drawDivider();
    }
}
public class Play{ 
 Scanner scan = new Scanner (System.in); 
 String board [] []; 
 String color [] []; 
 String guess_hold []; 
 String guess="";
 Random random = new Random();
 String word_list []= {
            "apple", "beach", "brain", "bread", "brush",
            "chair", "chest", "chord", "click", "clock",
            "cloud", "dance", "diary", "drink", "earth",
            "flute", "fruit", "glass", "grape", "green",
            "heart", "house", "juice", "light", "lemon",
            "melon", "money", "music", "night", "ocean",
            "party", "piano", "pilot", "plant", "phone",
            "pizza", "plate", "point", "power", "price",
            "queen", "quiet", "radio", "river", "robin",
            "shirt", "shoes", "smile", "snake", "space",
            "spoon", "storm", "table", "tiger", "toast",
            "touch", "train", "truck", "voice", "water",
            "watch", "whale", "world", "write", "zebra",
            "angry", "brave", "clear", "daily", "early",
            "fancy", "great", "happy", "inner", "jolly",
            "large", "lucky", "magic", "noble", "outer",
            "proud", "quick", "rough", "sharp", "smart",
            "thick", "under", "vital", "white", "young",
            "prick"
 };
            
 String c_word = word_list[random.nextInt(word_list.length - 1)];
 String the_word[] = to_Array(c_word);
 boolean end;
 boolean w;
 
 int guess_count =0; 

 
 Play () {
     board = new String [6][5]; 
     color = new String [6][5]; 
     guess_hold = new String [5]; 
        int ass_count =0;
        for (int i=0;i<6;i++){ 
        for (int j = 0; j<5; j++){
            
            board[i][j]="_";
            color[i][j]= "reset";
            
            
            
                 }
             }
         }
        
        
 
public String [] to_Array(String word) {
    String temp1 [] = new String [5];
    for (int i=0; i<5;i++){
        temp1[i] = word.substring(i,i+1);
    }
    return temp1;
}
public String to_String(String word []) {
    String temp2 = "";
    for (int i=0; i<5;i++){
        temp2 += word[i];
    }
    return temp2;

}
public void printBoard(){
    for (int i=0; i<6;i++){
        for (int j = 0; j<5; j++){
            
        System.out.print("|");
        if (color[i][j].equals("none"))
            System.out.print(UI.RED+board[i][j] + UI.RESET);
        else if (color[i][j].equals("GREEN"))
            System.out.print(UI.GREEN+board[i][j] + UI.RESET);
        else if (color[i][j].equals("YELLOW"))
            System.out.print(UI.YELLOW+board[i][j] + UI.RESET);
        else
            System.out.print(board[i][j]);
            }
        
        System.out.print("|");
        if (i == guess_count){
            System.out.println("<----");
        }else
            System.out.println("");
    }
}

public void guess(){
    boolean waiting = true;
    while (waiting) {
    System.out.println("choose a five letter word");
    guess = scan.nextLine();
    guess = guess.toLowerCase();
    if (guess.length()== 5)
        waiting = false;
    }
    
    guess_hold = to_Array(guess);
    
}
public void check() {
    
        for (int i=0;i<5;i++){
            if (guess_hold[i].equals(the_word[i]))
                color[guess_count][i] = "GREEN";
            else{
                for (String letter: the_word){
                    
                    if (guess_hold[i].equals(letter))
                        color[guess_count][i] = "YELLOW";
                
                }
            }
            
            
            board [guess_count][i]=guess.substring(i,i+1);
            
        }
    
    guess_count++;
    if (guess.equals(c_word)){
        end = true;
        w = true;
    }else if (guess_count == 6){
        end = true;
        w = false;
    }
}

   

public void playGame(){
    printBoard();
    while (end == false){
        guess();
        check();
        printBoard();
    }
    
   
    if (w == true)
        System.out.println("PLAYER WINS!!!");
    else 
        System.out.println("player loses the word was : "+c_word);
    }

}
    
    
