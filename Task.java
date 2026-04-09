import java.util.*;

public class Task {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long[][] tasks = new long[n][2];

        for (int i = 0; i < n; i++) {
            tasks[i][0] = sc.nextLong();
            tasks[i][1] = sc.nextLong();
        }

        Arrays.sort(tasks, (a, b) -> Long.compare(a[0], b[0]));

        long time = 0, ans = 0;

        for (int i = 0; i < n; i++) {
            time += tasks[i][0];
            ans += tasks[i][1] - time;
        }

        System.out.println(ans);
    }
}