
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
import java.awt.event.*;
import javax.swing.AbstractAction;

public class ParagrapheView extends JPanel {

    // Saisie du texte du paragraphe
    protected JTextArea txt;
    // Saisie de la question
    protected JTextField question;
    // Liste des boutons de choix
    protected ArrayList<JButton> choix;
    // Le paragraphe affiché
    protected Paragraphe courant;
    // Lien vers l'interface principale
    protected MonInterface _interface;
    // Le livre courant
    protected Livre livre;
    // La combobox des paragraphes à ajouter
    protected JComboBox<String> paragrahes;
    
    //le numero des paragraphs
    protected String[] paraNumList ;

    public ParagrapheView(MonInterface i, Livre livre, Paragraphe courant){
        this._interface = i;
        this.livre = livre;
        this.courant=courant;
        txt = new JTextArea(courant.getText(), 20, 50);
        question = new JTextField(courant.getQuestion());
        System.out.println(question);
        choix = new ArrayList<JButton>();
        for (Reponse r : courant.getReponses()){
            JButton b = new JButton(r.getTexte());
            // Chaque bouton demande à l'interface de charger le paragraphe suivant
            // TODO Trouver un moyen de supprimer le lien (la réponse)
            // avec ce paragraphe (clic droit sur le bouton ?)
            b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        _interface.loadParagrapheView(r.getSuivant());
                    }
                });
            choix.add(b);
        }
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, choix.size()));
        setLayout(new BorderLayout());
        add(question, BorderLayout.NORTH);
        add(txt,BorderLayout.CENTER);
        for(JButton b : choix){
            buttons.add(b);
        }
        
        
        //Paragraphe[] tmp = new Paragraphe[0];
        
        getParagraphNum(livre.getListeParagraphe());
        
        paragrahes = new JComboBox(this.paraNumList);
        // ActionListener de la Combobox : un clic sur un item ajoute un choix
        // (une Reponse) au paragraphe courant et un bouton à la vue du paragraphe
        paragrahes.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                	
                    JComboBox cb = (JComboBox)e.getSource();
                 
                    int index = retrieveIndexFromString(cb.getSelectedItem().toString());
          
                    System.out.println(index);
                    Paragraphe p = livre.getListeParagraphe().get(index-1);
                    Reponse r = new Reponse("Choix " + courant.getReponses().size(), p);
                    courant.add(r);
                    JButton b = new JButton(r.getTexte());

                    // Chaque nouveau bouton demande à l'interface de charger le
                    // paragraphe correspondant via un ActionListener
                    // TODO Trouver un moyen de supprimer le lien (la réponse)
                    // avec ce paragraphe (clic droit sur le bouton ?)
                    
                    b.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							
							//Manage the right click
							if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
									// whatever
								JButton but = (JButton) e.getSource();
								//take the index of the paragraph 
								int paraIndex = retrieveIndexFromString(but.getText());
								//remove the paragraph from the book 
								// and update the bottom panel
								updateBottomPanel(paraIndex,but);
								
							}
							
						
						}

						private void updateBottomPanel(int paraIndex,JButton b) {
							// TODO Auto-generated method stub
							
							courant.removeAllResponses();
							choix.remove(paraIndex);
							livre.getListeParagraphe().remove(paraIndex);
							getParagraphNum(livre.getListeParagraphe());
							
						
							buttons.remove(b);
							paragrahes.remove(paraIndex);
							repaint();
							revalidate();
							
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
                    	
                    });
                    b.addActionListener(new ActionListener(){

                            public void actionPerformed(ActionEvent e) {
                            
                                _interface.loadParagrapheView(p);
                            }

                        });

                    buttons.add(b, choix.size());
                    choix.add(b);
                    buttons.validate();
                }

            });
        buttons.add(paragrahes);
        add(buttons,BorderLayout.SOUTH);
        
    }
    
    //this function helps to retrieve the index of the element

    public int retrieveIndexFromString(String text) {
    	return Integer.parseInt(text.split(" ")[1])  ;
    }
    
    public ArrayList<JButton> getChoix(){
        return this.choix;
    }
    
    public String[] getParagraphNum(ArrayList<Paragraphe> paragraphs){
    	
    	String paraNumPrefix = "Paragraph ";
    	int nunmberOfPara = paragraphs.size();
    	this.paraNumList = new String[nunmberOfPara];
    	
    	//parcourir l'ensemble des paragraph pour recuperer leurs index
    	for (int i =0; i < nunmberOfPara ; i++) {
    		
    		paraNumList [i] =paraNumPrefix + (i+1);
    	}
    	//Dans le cas où il n'y a pas de paragraph dans le livre 
    	//que l'utilisateur puisse voir un message lui montrant cela
    	if (nunmberOfPara == 0) paraNumList [0] ="Pas de paragraph dans le livre";
    	
    return paraNumList;
    }
    
    public void save(){
        // Sauvegarde du texte
        courant.setText(txt.getText());
        courant.setQuestion(question.getText());
        
        // TODO Sauvegarde de la question (ajouter setQuestion à Paragraphe)
                
    }
}
