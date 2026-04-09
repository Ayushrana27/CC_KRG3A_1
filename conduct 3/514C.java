import java.io.*;
import java.util.*;

public class Main {

    static FastScanner fs = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = fs.nextInt();
        int m = fs.nextInt();

        HashSet<String> dictionary = new HashSet<>();

        for (int i = 0; i < n; i++) {
            dictionary.add(fs.next());
        }

        while (m-- > 0) {
            String query = fs.next();
            if (check(query, dictionary)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        out.flush();
    }

    static boolean check(String s, HashSet<String> set) {
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char original = arr[i];

            for (char c : new char[]{'a', 'b', 'c'}) {
                if (c == original) continue;

                arr[i] = c;
                String modified = new String(arr);

                if (set.contains(modified)) {
                    return true;
                }
            }

            arr[i] = original; // restore
        }

        return false;
    }

    // FastScanner for CP
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
