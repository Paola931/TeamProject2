package src;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class carrello {

    public carrello(ArrayList<carrello> carrelloList) {
        this.carrelloList = carrelloList;
    }

    @Override
    public String toString() {
        return "Prodotti nel carrello: " + getCarrelloList() + " ";
    }

    ArrayList<carrello> carrelloList = new ArrayList<>();

    public ArrayList<carrello> getCarrelloList() {
        return carrelloList;
    }

    public void setCarrelloList(ArrayList<carrello> carrelloList) {
        this.carrelloList = carrelloList;
    }
}
