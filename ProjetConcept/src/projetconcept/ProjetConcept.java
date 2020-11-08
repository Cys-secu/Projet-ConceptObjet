/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconcept;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Toine
 */
public class ProjetConcept {

    /**
     * @param args the command line arguments
     * 
     * Pour l'instant TESTS
     * 
     */
    public static void main(String[] args) throws InterruptedException {
        
        final int PAS_DE_TEMPS = 1;
        //final int NOMBRE_ETRE_PAR_EQUIPE = 2;       
        
        /////Création Carte/////
        
        Dimensions dim = new Dimensions(12, 12);
        
        Carte carte = new Carte(dim);
        
        /////Creation des EtreVivant/////
        
        Humain hu = new Humain(carte);
        Humain hu2 = new Humain(carte);
        Elfe el = new Elfe(carte);
        Elfe el2 = new Elfe(carte);
        Orque oc = new Orque(carte);
        Orque oc2 = new Orque(carte);
        Gobelin go = new Gobelin(carte);
        Gobelin go2 = new Gobelin(carte);
        
        ArrayList<EtreVivant> liste = new ArrayList();  //Liste des EtreVivant (non Maitre)
        
        liste.add(el);      //Ajout à la liste
        liste.add(hu);
        liste.add(oc);
        liste.add(go);
        liste.add(el2);
        liste.add(hu2);
        liste.add(oc2);
        liste.add(go2);
        
        MaitreElfe maitreElfe = MaitreElfe.getInstance(carte);
        MaitreHumain maitreHumain = MaitreHumain.getInstance(carte);
        MaitreOrque maitreOrque = MaitreOrque.getInstance(carte);
        MaitreGobelin maitreGobelin = MaitreGobelin.getInstance(carte);
        
        /////Création des Obstacle/////
        
        Obstacle pierre = new Obstacle("Pierre");
        Obstacle trou = new Obstacle("Trou");
        
        ArrayList<Obstacle> listeOb = new ArrayList();  //Liste des Obstacle
        
        listeOb.add(pierre);    //Ajout à la liste
        listeOb.add(trou);  
        
        /////Création des Message/////
                       
        Message m1 = new Message("My my, hey hey",1);
        Message m2 = new Message("Rock and roll is here to stay",2);
        Message m3 = new Message("It's better to burn out",3);
        Message m4 = new Message("Than to fade away",4);
        Message m5 = new Message("Out of the blue",5);
        Message m6 = new Message("and into the black",6);
        Message m7 = new Message("They give you this,",7);
        Message m8 = new Message("but you pay for that",8);
        Message m9 = new Message("And once you're gone,",9);
        Message m10 = new Message("you can never come back",10);
        Message m11 = new Message("When you're out of the blue",11);
        Message m12 = new Message("and into the black.",12);
        
        int nombreMessages = 12;
        
        /////Distribution des méssages de base aux EtreVivant/////
        
        ArrayList<Message> lH = new ArrayList();
        lH.add(m1);
        lH.add(m2);
        ArrayList<Message> lH2 = new ArrayList();
        lH2.add(m3);
        ArrayList<Message> lE = new ArrayList();
        lE.add(m4);
        lE.add(m5);
        ArrayList<Message> lE2 = new ArrayList();
        lE2.add(m6);
        ArrayList<Message> lG = new ArrayList();
        lG.add(m7);
        lG.add(m8);
        ArrayList<Message> lG2 = new ArrayList();
        lG2.add(m9);
        ArrayList<Message> lO = new ArrayList();
        lO.add(m10);
        lO.add(m11);
        ArrayList<Message> lO2 = new ArrayList();
        lO2.add(m12);
                 
        Savoir sH = new Savoir(lH);
        Savoir sE = new Savoir(lE);
        Savoir sG = new Savoir(lG);
        Savoir sO = new Savoir(lO);
        Savoir sH2 = new Savoir(lH2);
        Savoir sE2 = new Savoir(lE2);
        Savoir sG2 = new Savoir(lG2);
        Savoir sO2 = new Savoir(lO2);
        
        hu.setSavoir(sH);
        el.setSavoir(sE);
        oc.setSavoir(sO);
        go.setSavoir(sG); 
        hu2.setSavoir(sH2);
        el2.setSavoir(sE2);
        oc2.setSavoir(sO2);
        go2.setSavoir(sG2);     
        
        /////Initialisation de la Carte (Attention ordre des actions important)/////
        
        carte.ajoutMaitres(maitreHumain, maitreElfe, maitreGobelin, maitreOrque);
        carte.ajoutObstacle(listeOb);
        carte.ajoutEtresVivants(liste);
        
        ArrayList<EtreVivant> listeEtresVivants = new ArrayList();
        
        for (int i = 0; i < liste.size(); i++){
            
            listeEtresVivants.add(liste.get(i));
            
        }
        
        listeEtresVivants.add(maitreHumain);
        listeEtresVivants.add(maitreElfe);
        listeEtresVivants.add(maitreGobelin);
        listeEtresVivants.add(maitreOrque);
        
        Collections.shuffle(listeEtresVivants);
        
        /////Affichage de la situation de départ (Possession des Message + Carte)/////
        
                 
        carte.afficherCarte();
        
        /////Simulation/////
        
        int indiceListe = 0;
        int nombreTours = 0;
        
        while (maitreElfe.getSavoir().getMessages().size() < nombreMessages && maitreHumain.getSavoir().getMessages().size() < nombreMessages && maitreOrque.getSavoir().getMessages().size() < nombreMessages && maitreGobelin.getSavoir().getMessages().size() < nombreMessages){
                                                          
            listeEtresVivants.get(indiceListe).move();                        
            
            for (int k = 0; k < carte.getsafeCases().size(); k++){
                
                carte.getsafeCases().get(k).rechargePe();
                carte.getsafeCases().get(k).recolteSavoir();
                
            }
            
            Thread.sleep(PAS_DE_TEMPS);
        
            if (indiceListe == listeEtresVivants.size() - 1){
                
                indiceListe = 0;
                
                Collections.shuffle(listeEtresVivants);
                
                nombreTours++;
            }
            
            else{
                
                indiceListe++;
                
            }
            
            
           
        }
        
        /////Affichage final de la possession des Message/////
        
        
       
        /////Affichage des vainqueurs/////
        
        if (maitreElfe.getSavoir().getMessages().size() == nombreMessages){
            
            System.out.println("Les Elfes ont gagné");
            
        }
        
        if (maitreHumain.getSavoir().getMessages().size() == nombreMessages){
            
            System.out.println("Les Humains ont gagné");
            
        }
        
        if (maitreOrque.getSavoir().getMessages().size() == nombreMessages){
            
            System.out.println("Les Orques ont gagné");
            
        }
        
        if (maitreGobelin.getSavoir().getMessages().size() == nombreMessages){
            
            System.out.println("Les Gobelins ont gagné");
            
        }
        
        System.out.println("Nombre de tours: " + nombreTours);
        
    }
    
    
}
