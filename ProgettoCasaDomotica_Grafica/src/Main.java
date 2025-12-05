import graphics.Picture;

import java.util.ArrayList;
import java.util.Scanner;
import graphics.*;
import pictures.*;

public class Main {

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        Picture p =new Picture("casa.png");
        p.draw();

        ArrayList<EntryLampadina> tutte = new ArrayList<>();
        try {
            tutte = LettoreCoordinate.leggiTutte();
        } catch (Exception e) {
            System.out.println("Errore nella lettura del file.");
            return;
        }

        
        ArrayList<String> stanze = new ArrayList<>();

        for (int i = 0; i < tutte.size(); i++) {
            String s = tutte.get(i).stanza;

            boolean giàPresente = false;
            for (int j = 0; j < stanze.size(); j++) {
                if (stanze.get(j).equals(s)) {
                    giàPresente = true;
                }
            }

            if (!giàPresente) {
                stanze.add(s);
            }
        }


      


        SistemaDomotico sistema = new SistemaDomotico(100);

        // Carico solo lampadine della stanza scelta
        for (int i = 0; i < tutte.size(); i++) {
            EntryLampadina e = tutte.get(i);

            String stanzaScelta = "";
            if (e.stanza.equals(stanzaScelta)) {
                LampadinaIntelligente l = new LampadinaIntelligente(e.potenza);
                l.setNome("lamp_" + e.x + "_" + e.y);

                l.setPosizione(e.x, e.y);
                l.creaGrafica(p);

                sistema.add(l);
            }
        }

        boolean exit = false;

        while (!exit) {
            System.out.println("\nMENU");
            System.out.println("1) Accendi tutte");
            System.out.println("2) Spegni tutte");
            System.out.println("3) Aggiungi lampadina");
            System.out.println("4) Rimuovi lampadina");
            System.out.println("5) Cerca lampadina");
            System.out.println("6) Mostra sistema");
            System.out.println("7) Calcola potenza totale");
            System.out.println("8) Salva sistema");
            System.out.println("9) Carica sistema");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");

            int scelta =-1;
            try {
                scelta = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Inserisci un numero.");
                continue;
            }

            switch (scelta) {

                case 1: // accendi
                    sistema.accendiTutte();
                    System.out.println("Accese tutte.");
                    break;

                case 2: // spegni
                    sistema.spegniTutte();
                    System.out.println("Spente tutte.");
                    break;

                case 3: // AGGIUNGI LAMPADINA
                    System.out.println("Stanze disponibili:");
                    for (int i = 0; i < stanze.size(); i++) {
                        System.out.println("- " + stanze.get(i));
                    }

                    System.out.print("Scegli stanza: ");
                    String stanzaScelta = in.nextLine();

                    boolean trovata = false;
                    for (int i = 0; i < stanze.size(); i++) {
                        if (stanze.get(i).equals(stanzaScelta)) {
                            trovata = true;
                        }
                    }

                    if (!trovata) {
                        System.out.println("Stanza non valida.");
                        break;

                    }

                    System.out.print("Nome lampadina: ");
                    String nomeAgg = in.nextLine();

                    System.out.print("Potenza: ");
                    int potAgg = 0;
                    try {
                        potAgg = Integer.parseInt(in.nextLine());
                    } catch (Exception e) {
                        System.out.println("Potenza non valida.");
                        break;
                    }


                    ArrayList<EntryLampadina> posizioniDisponibili = new ArrayList<>();

                    System.out.println("Posizioni disponibili nella stanza '" + stanzaScelta + "':");

                    for (int i = 0; i < tutte.size(); i++) {
                        EntryLampadina e2 = tutte.get(i);

                        if (e2.stanza.equals(stanzaScelta)) {
                            posizioniDisponibili.add(e2);
                            System.out.println((posizioniDisponibili.size() - 1) +
                                    ") x=" + e2.x + " y=" + e2.y);
                        }
                    }

                    if (posizioniDisponibili.size() == 0) {
                        System.out.println("Nessuna posizione disponibile.");
                        break;
                    }

                    System.out.print("Scegli posizione (numero): ");

                    int sceltaPos = -1;
                    try {
                        sceltaPos = Integer.parseInt(in.nextLine());
                    } catch (Exception e) {
                        System.out.println("Valore non valido.");
                        break;
                    }

                    if (sceltaPos < 0 || sceltaPos >= posizioniDisponibili.size()) {
                        System.out.println("Posizione non valida.");
                        break;
                    }

                    EntryLampadina sceltaPosizione = posizioniDisponibili.get(sceltaPos);

                    LampadinaIntelligente nuovaLamp = new LampadinaIntelligente(potAgg);
                    nuovaLamp.setNome(nomeAgg);

                    nuovaLamp.setPosizione(sceltaPosizione.x, sceltaPosizione.y);

                    nuovaLamp.creaGrafica(p);

                    if (sistema.add(nuovaLamp)) {
                        System.out.println("Lampadina aggiunta nella posizione x=" +
                                sceltaPosizione.x + " y=" + sceltaPosizione.y);
                    } else {
                        System.out.println("Errore: nome già esistente o spazio pieno.");
                    }

                    break;

                case 4: // rimuovi
                    System.out.print("Nome da rimuovere: ");
                    String nomeR = in.nextLine();

                    if (sistema.rimuovi(nomeR)) {
                        System.out.println("Rimossa.");
                    } else {
                        System.out.println("Lampadina non trovata.");
                    }
                    break;

                case 5: // cerca
                    System.out.print("Nome da cercare: ");
                    String nomeC = in.nextLine();

                    LampadinaIntelligente trovataLamp = sistema.cerca(nomeC);
                    if (trovataLamp != null) {
                        System.out.println(trovataLamp);
                    } else {
                        System.out.println("Non trovata.");
                    }
                    break;

                case 6: // stampa sistema
                    System.out.println(sistema);
                    break;

                case 7: // potenza totale
                    System.out.println("Potenza totale: " + sistema.calcolaPotenzaAttuale());
                    break;

                case 8: // salva
                    String nomeSave = "./Assets/Salvataggio.bin";

                    try {
                        SalvataggioBinario.salva(sistema, nomeSave);
                    } catch (Exception e) {
                        System.out.println("Errore salvataggio.");
                    }
                    break;

                case 9: // carica
                    String nomeLoad = "./Assets/Salvataggio.bin";

                    try {
                        sistema = (SistemaDomotico) SalvataggioBinario.carica(nomeLoad);
                        System.out.println("Sistema caricato.");
                        for (int i = 0; i < sistema.getNumeroLampadine(); i++) {
                            LampadinaIntelligente l = sistema.getLampadina(i);
                            l.creaGrafica(p);
                            l.aggiornaGrafica();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 0:
                    exit = true;

                    break;


                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }

        System.out.println("Fine.");
        in.close();
        if (exit==true){
            System.exit(0);
        }
    }
}
