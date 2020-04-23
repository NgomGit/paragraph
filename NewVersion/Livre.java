
import java.util.ArrayList;
import java.io.Serializable;
import java.io.File;
import javax.swing.JFileChooser;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Livre implements Serializable{
  protected ArrayList<Paragraphe> mesParagraphe;

  public Livre(){
    this.mesParagraphe = new ArrayList<>();

    /*Paragraphe p1 = new Paragraphe("blabla1", "Ou voulez vous aller1", false, false);
    Paragraphe p2 = new Paragraphe("blabla2", "Ou voulez vous aller2", false, false);
    Paragraphe p3 = new Paragraphe("blabla3", "Ou voulez vous aller3", false, false);
    this.addParagraphe(p1);
    this.addParagraphe(p2);
    this.addParagraphe(p3);*/

    // this.fauxText();
  }

  public void addParagraphe(Paragraphe p) {
    this.mesParagraphe.add(p);
  }

  public ArrayList<Paragraphe> getListeParagraphe(){
    return this.mesParagraphe;
  }
  public void fauxText(){

    for(int i =0; i<3;i++){
      this.mesParagraphe.add(new Paragraphe("Paragraphe originel n°"+i));
    }
  }
// POUR SERALISER LE LIVRE
  public void save(String fileName){
    ObjectOutputStream oos = null;

    try {
      final FileOutputStream fichier = new FileOutputStream(fileName);
      oos = new ObjectOutputStream(fichier);
      oos.writeObject(this);
      oos.flush();
    } catch (final java.io.IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (oos != null) {
          oos.flush();
          oos.close();
        }
      } catch (final IOException ex) {
        ex.printStackTrace();
      }
    }
  }
//POUR CHARGER LE LIVRE
  public static Livre load(String fileName) {
    ObjectInputStream ois = null;
      try {
        final FileInputStream fichier =  new FileInputStream(fileName);
        ois = new ObjectInputStream(fichier);
      
        final Livre livre = (Livre) ois.readObject();
        return livre;
      } catch (final java.io.IOException e) {
        e.printStackTrace();
      } catch (final ClassNotFoundException e) {
        e.printStackTrace();
      }
      return new Livre();
  }

}
