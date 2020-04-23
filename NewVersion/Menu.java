

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;


public class Menu extends JMenuBar{

  protected JMenu jmenuFile;
  protected JMenuItem itemEnregi;
  protected JMenuItem itemSuppri;
  protected JMenu jmenuEdi;
  protected JMenu jmenuNouv;
  protected Action quit;
  protected JMenuItem itemPara;
  protected JMenuItem itemEnreVrais;
  protected JMenuItem itemCharge;
  protected JButton bouton1;

  public Menu (MonInterface _interface){
    this.jmenuFile = new JMenu("File");
    this.add(jmenuFile);
    this.itemEnregi = new JMenuItem("Enregistre");
    this.itemSuppri= new JMenuItem("Supprimer");
    this.itemCharge = new JMenuItem("Charger");
    this.jmenuFile.add(this.itemEnregi);
    this.jmenuFile.add(this.itemSuppri);
    this.jmenuFile.add(this.itemCharge);
    this.bouton1 = new JButton("+");
    this.jmenuFile.add(this.bouton1);

    //ASSOCIATION DE L'ACTION ECOUTER AU BOUTON ENREGISTRER
    itemEnregi.addActionListener(_interface);
    itemCharge.addActionListener(_interface);
    itemSuppri.addActionListener(_interface);
    bouton1.addActionListener(_interface);
//CREATION DU BOUTON QUITTER
    this.quit = new Action("Quitter");
    this.jmenuFile.add(new JMenuItem(this.quit));

//CREATION DU jmenuEdi
    this.jmenuEdi = new JMenu("Edition");
    this.add(jmenuEdi);

//CREATION MENU NOUVEAU
    this.jmenuNouv = new JMenu("Nouveau");
    this.add(jmenuNouv);
    this.itemPara = new JMenuItem("ParagrapheIt");
    this.jmenuNouv.add(this.itemPara);
    itemPara.addActionListener(_interface);
  }
//CREATION GETTER
    public JMenuItem getMenuPara(){
      return this.itemPara;
  }
    public JMenuItem getMenuEnregistre(){
      return this.itemEnregi;
    }
    public JMenuItem getMenuCharge(){
      return this.itemCharge;
  }
  public JMenuItem getMenuSuprim(){
    return this.itemSuppri;
  }
  public JButton getBouton1(){
    return this.bouton1;
  }
}
