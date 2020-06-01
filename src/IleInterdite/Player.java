package IleInterdite;

import Controller.CardDraggable;
import Enumeration.Artefacts;
import Enumeration.Etat;
import Enumeration.TresorCard;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Player{

    protected Zone zone;
    protected Color color;
    protected ArrayList<TresorCard> playerCards = new ArrayList<>(); //Todo : Instancier un tas de carte
    protected ArrayList<CardDraggable> playerCardsDragtgable = new ArrayList<>(); //Todo : Instancier un tas de carte
    protected static int nbActionsRestant;
    protected Island modele;

    public Player(Zone zone, Color colo, Island modele){
        this.zone = zone;
        this.color = colo;
        this.modele = modele;
    }
    /**
    * Deplacer le joueur
    **/
    public void movePlayer(Zone z){
        this.zone = z;
    }

    /**
     * assecher zone zone
     **/
    public void drainWaterZone(Zone z){
        z.setEtat(Etat.normale); //TODO: Vérifier si la zone est bien innondée et rien d'autres ? Normalment verifier avant utilisation donc est ce nécessaire ?
    }

    /**
     * récuperer un Artefact
     **/
    public void takeArtefact(ArrayList<Artefacts> listArtefacts){
        int compteur = 0;
        Artefacts artefacts = zone.getArtefacts();
        for(TresorCard t : playerCards)
            if(t.getArtefactsAssociated() == artefacts && artefacts != Artefacts.none)
                compteur++;
        if(compteur >= 4){
            zone.setArtefacts(Artefacts.none);
            listArtefacts.add(artefacts);
            for (int i = 0; i < 4; i++)
                this.playerCards.remove(artefacts);
        }else{
            System.out.println("Not allow here !");
        }
    }

    /**
     * change la quantite de clé d'un joueur
     **/

    /**
     * rechercher une clé avec proba de 0.5 d'en trouver
     **/
    public void searchKey(ArrayList<TresorCard> tas, ArrayList<TresorCard> defausse, Island island){
        if(tas.size() == 0){
            Collections.shuffle(defausse);
            tas.addAll(defausse);
            defausse.clear();
        }

        TresorCard card = tas.get(0);

        if(card == TresorCard.rising_water) {
            System.out.println(card.toString());
            island.risingWater();
            defausse.add(card);
            tas.remove(card);
        }else{
            this.playerCards.add(0,card);
            System.out.println(card.toString());
            tas.remove(card);
        }

        /*if(this.playerCards.size() > 8){
            card = playerCards.get(0);
            defausse.add(card);
            playerCards.remove(card);
        }*/
    }



    public Color getColor(){return color;}

    public Zone getZone(){
        return this.zone;
    }

    public void addAction(){
        if(nbActionsRestant <3){
            nbActionsRestant +=1;
        }
        else{
            System.out.println("PLUS DE DEPLACEMENT POSSIBLE");
        }
    }

    public boolean canAct(){
        return nbActionsRestant <3;
    }

    public void resetNbActionRestant(){
        nbActionsRestant = 0;
    }

    public ArrayList<TresorCard> getCards(){
        return this.playerCards;
    }
    public void setCard(TresorCard c){
        playerCards.add(0,c);
    }

    public void discardCard(ArrayList<TresorCard> toDiscard){
        this.getCards().removeAll(toDiscard);
    }


    public ArrayList<CardDraggable> getPlayerCardsDragtgable() {
        return playerCardsDragtgable;
    }

    public void setPlayerCardsDragtgable(ArrayList<CardDraggable> playerCardsDragtgable) {
        this.playerCardsDragtgable = playerCardsDragtgable;
    }

    public void removeCard(TresorCard card){
        playerCards.remove(card);
    }

    public int nombreCarte(){
        int compteur = 0;
        for(TresorCard card : this.playerCards)
            if(card != TresorCard.empty)
                compteur++;
        return compteur;
    }

    /**
     * @param zP zone ou se trouve le joueur
     * @return une liste de zone
     */
    public ArrayList<Zone>  zonesReachable(){
        Position pos = zone.getPosition();
        ArrayList<Zone> zonesSafe = new ArrayList<>();
        Zone [][] zones = modele.getGrille();
        if (pos.y-1>=0)
            if(zones[pos.x][pos.y-1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y-1]);
            }
        if(pos.x-1>=0)
            if(zones[pos.x-1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x-1][pos.y]);
            }

        if(pos.y+1<modele.HAUTEUR)
            if(zones[pos.x][pos.y+1].isSafe()){
                zonesSafe.add(zones[pos.x][pos.y+1]);
            }

        if(pos.x+1<modele.LARGEUR)
            if(zones[pos.x+1][pos.y].isSafe()){
                zonesSafe.add(zones[pos.x+1][pos.y]);
            }

        if(zone.isSafe())
            zonesSafe.add(zone);

        return zonesSafe;
    }


    public boolean isReachable(ArrayList<Zone> listZone){
        for( Zone z : listZone){
            if(z.getPosition().equals( zone.getPosition())) {
                return true;
            }
        }
        return false;
    }
}
