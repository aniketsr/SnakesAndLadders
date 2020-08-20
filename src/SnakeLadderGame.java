import java.util.HashMap;
import java.util.Map;
import java.util.Random;
 
public class SnakeLadderGame {

    private static Random dice = new Random();

    private static Map<Integer, Integer> snakeLadderMap = new HashMap<Integer, Integer>() {
        {
            put(7, 33);  // ladder
            put(37, 85);  // ladder
            put(51, 72);  // ladder
            put(63, 99);  // ladder
            put(36, 19);  // snake
            put(65, 35);  // snake
            put(87, 32);  // snake
            put(97, 21);  // snake
        }
    };
 
    private static int play(int player, int position) {
        while (true) {
            int roll = dice.nextInt(6) + 1;  // dice roll
            System.out.printf("Player %d - current position %d - has rolled %d", player, position, roll);
            if (position + roll > 100) {
                System.out.printf("- stays on position %d\n", position);
            } else {
                position += roll;  // position after dice roll
                System.out.printf("- moves to position %d\n", position);
                if (position == 100) return 100;
                int snakeOrLadderEndPosition = snakeLadderMap.getOrDefault(position, position);
                if (position < snakeOrLadderEndPosition) {
                    System.out.printf("Player %d lands on a ladder and jumps to position %d.\n", player, snakeOrLadderEndPosition);
                    if (snakeOrLadderEndPosition == 100) return 100;
                    position = snakeOrLadderEndPosition;  // Ladder end position
                } else if (position > snakeOrLadderEndPosition) {
                    System.out.printf("Player %d lands on a snake and falls back to position %d.\n", player, snakeOrLadderEndPosition);
                    position = snakeOrLadderEndPosition;  // Snake end position
                }
            }
            if (roll < 6) return position;
            System.out.println("It's a 6. Roll again!");
        }
    }
 
    public static void main(String[] args) {
        int[] players = {0};  // number of players with starting position
        while (true) {
            for (int i = 0; i < players.length; ++i) {
                System.out.println();
                int currentPosition = play(i + 1, players[i]); // current turn
                if (currentPosition == 100) {
                    System.out.printf("Player %d is winner.", i + 1);
                    return;
                }
                players[i] = currentPosition;
            }
        }
    }
}