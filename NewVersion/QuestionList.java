
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class QuestionList {
  private List<String> listeQuestion = new ArrayList<>();

  public QuestionList(){

  }


  public void AddToList(){
    Scanner sc = new Scanner(System.in);
    System.out.print("Entrez une question ");
    String kestion = sc.nextLine();
    System.out.println("la question est" + kestion);
    this.listeQuestion.add(kestion);
    sc.close();
  }

  public void RemoveToList(){
    Scanner sc = new Scanner(System.in);
    System.out.print("Quelle question à supprimer ");
    int Nkestion = sc.nextInt();
    System.out.println("la question est" + Nkestion);
    this.listeQuestion.remove(Nkestion);
    sc.close();
  }

  public void AfficheList(){
    System.out.println(listeQuestion);
  }
}
