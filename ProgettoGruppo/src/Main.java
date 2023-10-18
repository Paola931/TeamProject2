import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {



    public static void main(String[]args){

        System.out.println("Seleziona un numero: ");
        Scanner scan = new Scanner(System.in); // Aggiunto costruttore scanner, aggiunto in automatico "Import java.util.scanner"


        Prodotti smatphone1 = new Prodotti("Galaxy Z Flip","Nuovo","8 Pollici","256GB",1000,1500,6540, Prodotti.TipoProdotto.SMARTPHONE, Prodotti.MarcaProduttore.SAMSUNG);
     Prodotti smartphone2 = new Prodotti("15 Pro Max","Nuovo","6,5 Pollici","128GB",1200,1800,9871, Prodotti.TipoProdotto.SMARTPHONE, Prodotti.MarcaProduttore.APPLE);
     Prodotti tablet1 = new Prodotti("Galaxy S9","Usato","10,5 Pollici","256GB",619,750,3210, Prodotti.TipoProdotto.TABLET, Prodotti.MarcaProduttore.SAMSUNG);
     Prodotti tablet2 = new Prodotti("iPad Air 2022","Usato","10,9 Pollici","128GB",709,860,9631, Prodotti.TipoProdotto.TABLET, Prodotti.MarcaProduttore.APPLE);
     Prodotti notebook1 = new Prodotti("Galaxy Book Pro 15","Nuovo","15 Pollici","512GB",1200,1600,8520, Prodotti.TipoProdotto.NOTEBOOK, Prodotti.MarcaProduttore.SAMSUNG);
     Prodotti notebook2 = new Prodotti("Mac Book","Usato","13,5 Pollici","512GB",1379,1700,7411, Prodotti.TipoProdotto.NOTEBOOK, Prodotti.MarcaProduttore.APPLE);
    }
}
