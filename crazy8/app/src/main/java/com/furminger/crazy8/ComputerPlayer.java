package com.furminger.crazy8;

import java.util.List;

public class ComputerPlayer {

    public int playCard(List<Card>hand, int suit, int rank) {
        int play = 0;
        for(int i = 0; i < hand.size(); i++) {
            int tempId = hand.get(i).getId();
            int tempRank = hand.get(i).getRank();
            int tempSuit = hand.get(i).getSuit();
            if(tempRank != 8) {
                if(rank == 8) {
                    if(suit == tempSuit) {
                        play = tempId;
                    }
                } else if (suit == tempSuit || rank == tempRank) {
                    play = tempId;
                }
            }
        }
        if(play == 0) {
            for(int i = 0; i < hand.size(); i++) {
                int tempId = hand.get(i).getId();
                if(tempId == 108 || tempId == 208 || tempId == 308 || tempId == 408) {
                    play = tempId;
                }
            }
        }
        return play;
    }
}
