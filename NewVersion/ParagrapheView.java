
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
    protected JComboBox<Paragraphe> paragrahes;

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
        Paragraphe[] tmp = new Paragraphe[0];
        paragrahes = new JComboBox<>(livre.getListeParagraphe().toArray(tmp));
        // ActionListener de la Combobox : un clic sur un item ajoute un choix
        // (une Reponse) au paragraphe courant et un bouton à la vue du paragraphe
        paragrahes.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox)e.getSource();
                    Paragraphe p = (Paragraphe)cb.getSelectedItem();
                    Reponse r = new Reponse("Choix " + courant.getReponses().size(), p);
                    courant.add(r);
                    JButton b = new JButton(r.getTexte());

                    // Chaque nouveau bouton demande à l'interface de charger le
                    // paragraphe correspondant via un ActionListener
                    // TODO Trouver un moyen de supprimer le lien (la réponse)
                    // avec ce paragraphe (clic droit sur le bouton ?)
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

    public ArrayList<JButton> getChoix(){
        return this.choix;
    }
    
    public void save(){
        // Sauvegarde du texte
        courant.setText(txt.getText());
        courant.setQuestion(question.getText());
        
        // TODO Sauvegarde de la question (ajouter setQuestion à Paragraphe)
                
    }
}
