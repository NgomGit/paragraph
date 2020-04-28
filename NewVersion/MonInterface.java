

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.io.File;
import javax.swing.JFileChooser;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class MonInterface extends JFrame implements ActionListener{

    // Le livre courant
    protected Livre livre;
    // Le paragraphe affiché (courant)
    protected Paragraphe courant;

    // La liste des paragraphes précédents
    protected ArrayList<Paragraphe> precedents;

    // La vue du paragraphe courant
    protected ParagrapheView courantVue;

    // Des boutons pour l'instant inutiles
    private JButton bouton2;
    protected JButton bouton1;
    private JButton bouton3;

    // Le menu
    protected Menu menu;


    //CONSTRUCTEUR
    public MonInterface(){
        // On démarre avec une livre vide
        livre = new Livre();

        // On créé un paragraphe bidon
        Paragraphe bidon = new Paragraphe("Votre texte", "Votre question", false, false);
        
        // On l'ajoute au livre vide
        livre.addParagraphe(bidon);

        // On charge sa vue
        loadParagrapheView(bidon);
        
        // Au départ il n'y a pas de paragraphe précédent
        precedents = new ArrayList<>();
        
        courantVue = new ParagrapheView(this, livre, courant);
      
        //PARAMETRES DE LA FENETRE
        Container cp=this.getContentPane();
        this.menu = new Menu(this);
        
        this.setSize(900,450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Editeur Livre");
        this.setLayout(new BorderLayout());
        
        cp.add(courantVue, BorderLayout.CENTER);
        
        //CREATION PANEL1
        JPanel monPanelBas = new JPanel();
        monPanelBas.setPreferredSize(new Dimension(50,35));
        monPanelBas.setBackground(Color.YELLOW);
        monPanelBas.setLayout(new GridLayout(1,3));
        bouton1 =new JButton("+");
        bouton2 = new JButton("Bouton2");
        monPanelBas.add(bouton1);
        monPanelBas.add(bouton2);
        bouton3 =new JButton("Bouton3");
        monPanelBas.add(bouton3);
        this.setJMenuBar(menu);
        cp.add(monPanelBas,BorderLayout.SOUTH);

        bouton2.addActionListener(this);
        bouton1.addActionListener(this);
        bouton3.addActionListener(this);
        
        pack();
        this.setVisible(true);
    }

    /**
     * Charge la ParagrapheView d'un paragraphe
     *
     * @param p le paragraphe à charger
     */
    public void loadParagrapheView(Paragraphe p){
        // On sauvegarde les changements dans le paragraphe courant
        if (courantVue != null) courantVue.save();

        // On mets à jour les paragraphes précédents
        if (precedents != null) precedents.add(courant);

        // Le paragraphe devient le courant
        courant = p;

        // On mets à jour (recréé) la vue du paragraphe courant
        if (courantVue != null) this.getContentPane().remove(courantVue);
        courantVue = new ParagrapheView(this, livre, courant);
        this.getContentPane().add(courantVue, BorderLayout.CENTER);
        this.validate();
    }
    
    public JButton getBouton1(){
        return this.bouton1;
    }
    //ASSOCIATION EVENEMENT AUX BOUTON ET AUX ITEMMENU
    @Override
    public void actionPerformed(ActionEvent e)
    {
      Object obj = e.getSource();
      
      // Bouton/menu Nouveau paragraphe
      // TODO permettre de créer un paragraphe de Fin/Gagnant (Fenêtre avec checkboxes ?)
      if (obj.equals(bouton1) || obj.equals(menu.getMenuPara()))
      {          
          // On créé le nouveau paragraphe
          Paragraphe nouveau = new Paragraphe("Votre texte", "Votre question", false, false);
          
          // On l'ajoute au livre
          livre.addParagraphe(nouveau);


          // On charge le nouveau paragraphe
          loadParagrapheView(nouveau);        

      }
      // Menu enregistrer
      if (obj == menu.getMenuEnregistre()){
          JFileChooser dialogue = new JFileChooser(new File(".txt"));
        	PrintWriter sortie;
        	File fichier;

        	if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
        	    fichier = dialogue.getSelectedFile();
              // On sauvegarde les changements dans le paragraphe courant
              courantVue.save();

              // On sauvegarde le livre dans le fichier
              livre.save(fichier.getPath());
        	}
      }

      // Menu charger
      if (obj ==menu.getMenuCharge()){
        JFileChooser dialogue = new JFileChooser(new File(".txt"));
        PrintWriter sortie;
        File fichier;
        if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
            fichier = dialogue.getSelectedFile();
            // On charge le livre
            livre = Livre.load(fichier.getPath());

            // On charge son premier paragraphe
            loadParagrapheView(livre.getListeParagraphe().get(0));

            // Remise à zéro des paragraphes précédents
            precedents.clear();

        }
      }
    }
    
    public Paragraphe getPrevPara() {
    	
    	int lastIdx = precedents.size() -1;
    	
    	return precedents.get(lastIdx);
    }
}
