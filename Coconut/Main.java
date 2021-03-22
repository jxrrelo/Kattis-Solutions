import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loh Fah Yao Jaryl A0202023H
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSyllables = sc.nextInt();
        int numPlayers = sc.nextInt();
        int index = 0;

        ArrayList<Player> players = new ArrayList<>();
        for(int i = 1; i <= numPlayers; i++){
            players.add(i - 1, new Player(i, 0));
        }

        while(players.size() > 1){
            index = (index + numSyllables - 1) % players.size();
            if(players.get(index).getState() == 0){
                players.get(index).setState(1);
                players.add(index, new Player(players.get(index).getUID(), 1));
            } else if (players.get(index).getState() == 1){
                players.get(index).setState(2);
                index++;
            } else {
                players.remove(index);
            }
        }

        int winner = players.get(0).getUID();
        System.out.println(winner);
    }
}