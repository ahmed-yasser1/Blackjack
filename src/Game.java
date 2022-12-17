package blackjack;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Player[] Players=new Player[4];
    Card[] cardDeck=new Card[52];
    private int maxScore=0;//keeps track of the existing VALID high score of all players (<= 21)

    public int getMaxScore() {
        return maxScore;
    }

    //A function that generates the card deck array
    public void generateCD(){
        int j=0;
        for(int i=0;i<4;i++){
            int h=0;//help to set the value and rank of the card
            for( ; h<13;j++,h++){
                cardDeck[j]=new Card(i, h,(h>9)? 10:h+1);

            }//end of second for loop

        }//end of first for loop

    }//end of generateCD

//_____________________________________________________________________________________________

//A function that draws a card randomly from the card deck array
    public Card drawingCards(){
        Random rand=new Random();
        int index;
        do {
            index = rand.nextInt(52);
        } while(cardDeck[index]==null);
          Card temp=cardDeck[index];
          cardDeck[index]=null;
          return temp;
    }//end of drawingCards

//______________________________________________________________________________________________

    //A function that sets the information of the players at the beginning of the game.
    public void setPlayersInfo(String[]pNames){
        for(int i=0; i<4; i++){
            Players[i]=new Player(pNames[i]);//create 4 objects of class Player and give them names.
            for(int j=0; j<2; j++){
                Players[i].handCards[j]=drawingCards();//give a random card to the player.
                Players[i].setScore(Players[i].handCards[j].getValue());
            }//end of second for loop
        }//end of first for loop

    }//end of setPlayersInfo.

//______________________________________________________________________________________________

int[]Scores;
    public void updateMaxScore(){
       //Get the scores of the 3 players
        Scores =new int[4];
            for(int i=0; i<3; i++){
                Scores[i]=Players[i].getScore();
            }

       //Bubble Sort
        int temp;
     for(int i=0; i<2; i++){
         for(int j=0; j<2-i; j++){
            if(Scores[j]>Scores[j+1]){
                //swap
                temp=Scores[j];
                Scores[j]=Scores[j+1];
                Scores[j+1]=temp;
            }//end of if_condition
         }//end of second for loop
     }//end of first for loop

        //which score match the condition that (score<=21).
        for(int k=2; k>-1; k--){
            if(Scores[k]<=21){
                maxScore=Scores[k];
                break;
            }//end of first if-condition
           else if(k==0){
                maxScore=0;
            }//end of second if-condition
        }//end of for loop

        System.out.println("Maximum Score is: "+maxScore);
        System.out.print("Maximum Score is scored by:");
        //who achieve maxScore
        for(int p=0; p<3; p++){
            if(Players[p].getScore()==maxScore){
                System.out.print("  "+Players[p].getName());
            }//end of if_condition
        }//end of for loop.
        System.out.println("");
        System.out.println("__________________________________________________________________");

    }//end of updateMaxScore

//___________________________________________________________________________________________________

    public  void PlayerState(){
        for(int m=0;m<3;m++){
            System.out.print(Players[m].getName()+"'s score is: "+Players[m].getScore());
            if (Players[m].getScore() == 21) {
                System.out.print("  =>  BLACKJACK");
            }//end of first if-condition
            else if (Players[m].getScore() > 21) {
                System.out.print("  =>  BASTED");
            }//end of second if-condition
            System.out.println("");
        }//end of for loop
        System.out.println("__________________________________________________________________");

    }//end of PlayerState

//___________________________________________________________________________________________________

public void DealerState(){
    System.out.print(Players[3].getName()+"(dealer)'s score is: "+Players[3].getScore());
    if (Players[3].getScore() == 21) {
        System.out.print("  =>  BLACKJACK");
    }//end of first if-condition
    else if (Players[3].getScore() > 21) {
        Players[3].win = false;
        System.out.print("  =>  BASTED");
    }//end of second if-condition
    System.out.println("");
}//end of DealerState




}//end of Game class
