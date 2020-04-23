
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import javax.swing.JButton;
public class SaisirText extends JPanel{
  protected JTextArea clavier;
  protected int npage=0;
  protected Livre livre;

  public SaisirText(Livre livre){
    this.livre = livre;
    this.clavier = new JTextArea();
    this.clavier.setLineWrap(true);
    //texte de base
    this.clavier.setText("Livre de jeu editer\n les regles du  jeu est de choisir parmis les questions poser\n pour aller au paragraphe 2 cliquer sur Bouton 2\n pour aller au paragraphe 3 cliquer sur le bouton 3");

    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(700,600));
    this.add(this.clavier,BorderLayout.CENTER);
    this.setBackground(Color.YELLOW);
  }
  public int getnpage(){

    return this.npage;
  }
  public void setnpage(int npa){
    this.livre.getListeParagraphe().get(this.npage).setText(this.clavier.getText());
    this.npage= npa;
    this.afficheParagraphe(this.livre.getListeParagraphe().get(this.npage));
  }
  public void afficheParagraphe(Paragraphe para){
    this.clavier.setText(para.getText());
  }
  public String getText() {
     return this.clavier.getText();
  }
}
