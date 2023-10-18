public class Main {
    public static void main(String[]args){
     Magazzino smatphone1 = new Magazzino("Galaxy Z Flip","Nuovo","8 Pollici","256GB",1000,1500,6540, Magazzino.TipoProdotto.SMARTPHONE, Magazzino.MarcaProduttore.SAMSUNG);
     Magazzino smartphone2 = new Magazzino("15 Pro Max","Nuovo","6,5 Pollici","128GB",1200,1800,9871, Magazzino.TipoProdotto.SMARTPHONE, Magazzino.MarcaProduttore.APPLE);
     Magazzino tablet1 = new Magazzino("Galaxy S9","Usato","10,5 Pollici","256GB",619,750,3210, Magazzino.TipoProdotto.TABLET, Magazzino.MarcaProduttore.SAMSUNG);
     Magazzino tablet2 = new Magazzino("iPad Air 2022","Usato","10,9 Pollici","128GB",709,860,9631, Magazzino.TipoProdotto.TABLET, Magazzino.MarcaProduttore.APPLE);
     Magazzino notebook1 = new Magazzino("Galaxy Book Pro 15","Nuovo","15 Pollici","512GB",1200,1600,8520, Magazzino.TipoProdotto.NOTEBOOK, Magazzino.MarcaProduttore.SAMSUNG);
     Magazzino notebook2 = new Magazzino("Mac Book","Usato","13,5 Pollici","512GB",1379,1700,7411, Magazzino.TipoProdotto.NOTEBOOK, Magazzino.MarcaProduttore.APPLE);
    }
}
