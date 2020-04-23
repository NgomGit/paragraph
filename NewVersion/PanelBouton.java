
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.*;

public class PanelBouton extends JPanel implements ActionListener{
  protected Livre livre1;
  protected MonInterface mi;

  public PanelBouton(MonInterface m,Livre liv){
    this.mi=m;
    this.livre1 = liv;
    this.crerBouton();
    //this.setPreferredSize(new Dimension(10,30));
  }
  public void crerBouton(){
    this.removeAll();
      for(int i =0; i < livre1.getListeParagraphe().size(); i++){
        BoutonParagraphe jbutton = new BoutonParagraphe("Parragraphe n°"+(i+1),i);
        jbutton.addActionListener(this);
        this.add(jbutton);
      }
  }
  @Override
  public void actionPerformed(ActionEvent e){
    BoutonParagraphe obj = (BoutonParagraphe)e.getSource();
    int i=  obj.getIDid();
    // this.mi.monPanelText.setnpage(i);
  }
}
