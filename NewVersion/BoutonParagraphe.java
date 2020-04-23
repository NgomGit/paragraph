

import javax.swing.JButton;

public class BoutonParagraphe extends JButton{
  int iD;
  public BoutonParagraphe(String s, int i){
    super(s);
    this.iD=i;
  }
  public int getIDid(){
    return this.iD;
  }
}
