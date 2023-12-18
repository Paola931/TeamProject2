//package src;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class MenuAdmin {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in); // inizializzo Scanner
//
//        //MENU PRINCIPALE
//        String input = Integer.toString(1);
//        System.out.println("Inserisci un numero per iniziare un operazione:");
//        while (!input.equals("0")) {
//            System.out.println("0 = Esci dal programma");
//            System.out.println("1 = Aggiungi  un articolo al magazzino");
//            System.out.println("2 = Ricerca un dispositivo nel magazzino");
//            System.out.println("3 = Aggiungi elemento al carrello tramite ID");
//            System.out.println("4 = Rimuovi elemento dal carrello tramite ID");
//            System.out.println("5 = Visualizza i dispositivi presenti nel magazzino");
//            System.out.println("6 = Visualizza il carrello");
//            System.out.println("7 = Visualizza il prezzo totale degli articoli presenti nel carrello");
//            System.out.println("8 = Visualizza il prezzo medio degli articoli presenti nel carrello");
//            System.out.println("9 = Completa il tuo acquisto");
//            System.out.println();
//            input = in.nextLine();
//
//            switch (input) {
//                case "0": //Esci dal programma
//                    System.out.println("Arrivederci e grazie per averci scelto!");
//                    break;
//                case "1": //Aggiungi  un articolo al magazzino
//                    Prodotto prodotto = Prodotto.creaArticolo();
//                    System.out.println(prodotto);
//                    try {
//                        System.out.println(magazzino.aggiungiAMagazzino(prodotto));
//                    } catch(Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case "2": //Ricerca un dispositivo nel magazzino
//                    try {
//                        System.out.println(magazzino.ricercaDispositivo());
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case "3": // Aggiungi elemento al carrello tramite ID
//                    aggiungiCarrelloId(in, listaMagazzino,listaCarrello);
//                    break;
//                case "4": // Rimuovi elemento dal carrello tramite ID
//                    try {
//                        prodotto = carrello.getProdotto();
//                        carrello.rimuoviDalCarrello(prodotto);
//                        magazzino.aggiungiAMagazzino(prodotto);
//                    } catch(NullPointerException npe) {
//                        System.out.println(npe);
//                    }
//                    break;
//                case "5": // Visualizza i dispositivi presenti nel magazzino
//                    magazzino.printMagazzino(listaMagazzino);
//                    break;
//                case "6": // Visualizza il carrello
//                    carrello.printCarrello(listaCarrello);
//                    break;
//                case "7": // Visualizza il prezzo totale degli articoli presenti nel carrello
//                    System.out.println(calcoloCostoTotale(listaCarrello));
//                    break;
//                case "8": // Visualizza il prezzo medio degli articoli presenti nel carrello
//                    System.out.println();
//                    System.out.println(carrello.calcoloCostoMedio());
//                    System.out.println();
//                    break;
//                case "9": //  Completa il tuo acquisto
//                    System.out.println(carrello);
//                    carrello.completaAcquisto();
//                    System.out.println("Acquisto effettuato!");
//                    break;
//                default:
//                    System.out.println("Valore non supportato: " + input);
//                    System.out.println();
//            }
//        }
//    }
//
//}
