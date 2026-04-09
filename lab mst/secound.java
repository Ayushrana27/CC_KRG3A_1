import java.util.*;

class Second {
    public static int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int left = 0;
        int right = tokens.length - 1;
        int score = 0;
        int maxScore = 0;
        
        while (left <= right) {
            if (power >= tokens[left]) {
                power -= tokens[left];
                score++;
                left++;
                maxScore = Math.max(maxScore, score);
            } 
            else if (score > 0) {
                power += tokens[right];
                score--;
                right--;
            } 
            else {
                break;
            }
        }

        return maxScore;
    }

    public static void main(String[] args) {
        int[] tokens = {100, 200, 300, 400};
        int power = 200;
        int result = bagOfTokensScore(tokens, power);
        System.out.println("Maximum Score: " + result);
    }
}
