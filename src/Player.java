package blackjack;

public class Player {
 private String Name;
 private int Score=0;//the values of all the cards in the player’s hand at the beginning and after each hit.
 boolean win=false;//indicates whether he already lost or not.
Card[] handCards=new Card[11];

public Player (String Name){
    this.Name=Name;
}

    public String getName() {
        return Name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score += score;
    }
}


