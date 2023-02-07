
import java.util.Scanner;

class testia {
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Syötä luku");
        Integer luku1 = lukija.nextInt();
        if (luku1 < 0) {
            System.out.println("Negatiivinen");
        }
        if (luku1 == 0) {
            System.out.println("nolla :D");
        }
        if (luku1 > 0) {
            System.out.println("pos");
        }
    }
}
