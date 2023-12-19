package src;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuAdmin {
    public static void main() throws SQLException {
        Scanner in = new Scanner(System.in); // inizializzo Scanner
        Magazzino magazzino = new Magazzino(new ArrayList<>(),in);
        Carrello carrello = new Carrello(new ArrayList<>(),in);

        //MENU PRINCIPALE
        String input = Integer.toString(1);
        System.out.println("Inserisci un numero per iniziare un operazione:");
        while (!input.equals("0")) {
            System.out.println("0 = Esci dal programma");
            System.out.println("1 = Aggiungi  un articolo al magazzino");
            System.out.println("2 = Ricerca un dispositivo nel magazzino");
            System.out.println("3 = Aggiungi elemento al carrello tramite ID");
            System.out.println("4 = Rimuovi elemento dal carrello tramite ID");
            System.out.println("5 = Visualizza il magazzino");
            System.out.println("6 = Visualizza il carrello");
            System.out.println("7 = Visualizza il prezzo totale degli articoli presenti nel carrello");
            System.out.println("8 = Visualizza il prezzo medio degli articoli presenti nel carrello");
            System.out.println("9 = Completa il tuo acquisto\n");
            input = in.nextLine();
            switch (input) {
                case "0": //Esci dal programma
                    System.out.println("Arrivederci e grazie per averci scelto!");
                    break;
                case "1": //Aggiungi  un articolo al magazzino
//                    TODO
                    Prodotto p = Prodotto.creaArticolo();
                    System.out.println(p);
                    try {
//                        System.out.println("Inserisci la quantità di prodotti che desideri aggiungere al magazzino");
//                        int quantita = in.nextInt();
//                        String s = in.nextLine();
                     //   System.out.println(magazzino.aggiungiAMagazzino(p));
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2": //Ricerca un dispositivo nel magazzino
//                    TODO
                    try {
                        System.out.println(magazzino.ricercaDispositivo());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3": // Aggiungi elemento al carrello tramite ID
//                    TODO: DONE
                    try {
                        System.out.println("Inserisci l'ID del prodotto che desideri aggiungere al carrello");
                        int id = in.nextInt();
                        String inp = in.nextLine();
                        System.out.println(magazzino.rimuoviProdotto(id));
                        System.out.println(carrello.aggiungiProdotto(id));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4": // Rimuovi elemento dal carrello tramite ID
//                    TODO: DONE
                    try {
                        System.out.println("Inserisci l'ID del prodotto che desideri rimuovere dal carrello");
                        int id = in.nextInt();
                        String inp = in.nextLine();
                        System.out.println(carrello.rimuoviProdottoId(id));
                        System.out.println(magazzino.aggiungiProdottoId(id));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5": // Visualizza i dispositivi presenti nel magazzino
//                    TODO: DONE | ADD JOIN?
                    magazzino.visualizza();
                    break;
                case "6": // Visualizza il carrello
//                    TODO DONE | ADD JOIN?
                   carrello.visualizza();
                    break;
                case "7": // Visualizza il prezzo totale degli articoli presenti nel carrello
//                    TODO
                    System.out.println(carrello.calcoloCostoTotale());
                    break;
                case "8": // Visualizza il prezzo medio degli articoli presenti nel carrello
//                    TODO
                    System.out.println(carrello.calcoloCostoMedio());
                    break;
            }
        }
    }

}
