public class UnsetBit {
    public static void main(String[] args) {
        int n = 7;
        int i = 1; 
        int mask = ~(1 << i); 
        n = n & mask; 

        System.out.println(n);
    }
}
