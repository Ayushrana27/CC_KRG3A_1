import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            String team = sc.nextLine();
            map.put(team, map.getOrDefault(team, 0) + 1);
        }
        
        String winner = "";
        int maxGoals = 0;
        
        for (String team : map.keySet()) {
            if (map.get(team) > maxGoals) {
                maxGoals = map.get(team);
                winner = team;
            }
        }
        
        System.out.println(winner);
    }
}
