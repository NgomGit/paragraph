
import java.util.Scanner;
import java.util.*;

public class Livre1{
  public Livre1(){
  }


  public void question(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Choix du page : ");
    int a1,a2,a3;
    a1=1;
    a2=2;
    a3=3;
    int x =0;
    int i=0;
    while (i<5){
      i++;
      x=sc.nextInt();
      if (x==1){
        System.out.println("Page : " + a1);
      }
      if (x==2){
        System.out.println("Page : " + a2);
      }
      if (x==3){
        System.out.println("Page : " + a3);
      }
    }
     //a1 = sc.nextInt();
     //a2 = sc.nextInt();
     //a3 = sc.nextInt();

    //System.out.println("Page : " + a2);
    //System.out.println("Page : " + a3);


  }
}
