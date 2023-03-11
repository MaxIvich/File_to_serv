package gb.ivlev.filetoserv.Client;

public class Example {
    public static int q(int x ){
        return  x*x*x;
    }
    public static void main(String[] args) {
        int x =1;
        int a = 2961;
        int b = 300 * x;
        int c = q(x);
        while (true){
            int z = c-b;
            if((z)==a){
                System.out.println( z + "   " + x);
                break;
            }
            x ++;
        }
    }
}
