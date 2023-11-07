package src;

import java.util.ArrayList;

public class Magazzino {
    private ArrayList<Prodotto> magazzino;
    public Magazzino(ArrayList<Prodotto> magazzino) {
        this.magazzino = magazzino;
    }

    @Override
    public String toString() {
        return "Magazzino{" +
                "\narticolo=" + magazzino.get(0) +
                "\n}";
    }
    public void printMagazzino(ArrayList<Prodotto> magazzino) {
        magazzino.forEach(System.out::println);
        System.out.println();
    }
}