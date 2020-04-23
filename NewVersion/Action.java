
import java.awt.event.*;
import javax.swing.AbstractAction;

public class Action extends AbstractAction{

  public Action(String s){
    super(s);

  }
  public void actionPerformed(ActionEvent e){
      System.exit(0);
  }
}
