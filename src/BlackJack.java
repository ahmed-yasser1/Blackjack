package blackjack;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
 static Game g1;
static Scanner input;
   static GUI gui;

    public static void buildGame(){
    System.out.println("________________________________WELCOME TO BLACKJACK________________________________");
    g1=new Game();
    //Generates the card deck and then sets the information of the players
    g1.generateCD();

    //get players names
    System.out.println("Enter players names:");
    String[]PNames=new String[4];
     input=new Scanner(System.in);
    for(int i=0; i<4; i++){
        PNames[i]=input.next();
    }//end of for loop
    g1.setPlayersInfo(PNames);
    g1.updateMaxScore();
    g1.PlayerState();
}//end of buildGame

//___________________________________________________________________________________________________________

 public static void StartPlaying(){
        int choice,j=2;

        for(int i=0; i<3; i++){
            do{
                System.out.println(g1.Players[i].getName()+" turn...");
                System.out.println("1-Hit.");
                System.out.println("2-Stand.");
                choice=input.nextInt();
                System.out.println("__________________________________________________________________");
                if(choice==1){
                    g1.Players[i].handCards[j]=g1.drawingCards();
                    gui.updatePlayerHand(g1.Players[i].handCards[j], i );
                    g1.Players[i].setScore(g1.Players[i].handCards[j].getValue());
                    g1.updateMaxScore();
                    g1.PlayerState();

                }//end of if-condition
                    j++;
            }while(!(choice==2||g1.Players[i].getScore()>=21));//end of do-while loop
        }//end of for loop
                int k=2;

                    g1.DealerState();
                if(!(g1.Players[3].getScore()> g1.getMaxScore())){
                   // g1.DealerState();
              //  }//end of if-condition
                    //else {
                    while (true) {
                     //   g1.DealerState();
                        System.out.println(g1.Players[3].getName() + "(dealer)" + " turn...");
                        g1.Players[3].handCards[k] = g1.drawingCards();
                        gui.updateDealerHand(g1.Players[3].handCards[k], g1.cardDeck);
                        g1.Players[3].setScore(g1.Players[3].handCards[k].getValue());
                        g1.DealerState();
                         if(g1.Players[3].getScore()>=21){
                           break;
                         }

                    }//end of while loop
                }//end of if-condition
    }//end of StartPlaying

//___________________________________________________________________________________________________________

    public static void TheWinner(){

    String [] Winners=new String[4];
    int[]results=new int[4];
        results=g1.Scores;
        results[3]=g1.Players[3].getScore();

        if(g1.Players[3].getScore()>g1.getMaxScore()&&g1.Players[3].getScore()<=21){
            g1.Players[3].win=true;
            Winners[0]=g1.Players[3].getName();
        }//end of first if-condition
        else if(g1.Players[3].win==false) {
            int c = 0;
            for (int i = 0; i < 4; i++) {
                if (g1.Players[i].getScore() == g1.getMaxScore()) {
                    Winners[c++] = g1.Players[i].getName();
                }//end of if_condition
            }//end of for loop
        }//end of second if-condition
        int counter=0;
        for(int i=0;i<4;i++){
            if(Winners[i]!=null)
                counter++;
        }

        //determine the result
        System.out.println("_________________________________THE RESULT_________________________________");
        System.out.println("");
        if(counter==1){
            if(Winners[0]==g1.Players[3].getName())
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Player "+Winners[0]+"(dealer)  WINS! <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            else
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Player "+Winners[0]+" WINS! <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }else{
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PUSH! <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }//end of TheWinner

//___________________________________________________________________________________________________________



public static void main(String[] args) {
    gui = new GUI();

buildGame();
    gui.runGUI( g1.cardDeck,g1.Players[0].handCards,g1.Players[1].handCards, g1.Players[2].handCards, g1.Players[3].handCards );
StartPlaying();
TheWinner();


    }
}
