import graphics.*;
import graphics.Picture;
import pictures.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        ArrayList<EntryLampadina> tutte = LettoreCoordinate.leggiTutte();
        Picture p = new Picture("casa.png");
        p.draw();

        Set<String> stanze = new HashSet<>();
        for (EntryLampadina e: tutte) {
            stanze.add(e.stanza);
        }

        System.out.println("Stanze disponibili:");
        for (String s: stanze) {
            System.out.println("- " + s);
        }

        System.out.print("Scegli stanza: ");
        String stanzaScelta = in.nextLine();

        SistemaDomotico sistema = new SistemaDomotico();

/*
        // Carico le lampadine della stanza scelta
        for (EntryLampadina e: tutte) {
            if (e.stanza.equals(stanzaScelta)) {
                LampadinaIntelligente l = new LampadinaIntelligente(e.potenza);
                String nome = "lamp_" + e.x + "_" + e.y;
                l.setNome(nome);
                sistema.add(l);

                Rectangle r = new Rectangle(e.x, e.y, 20, 20);
                r.setColor(Color.LIGHT_GRAY);
                r.fill();
                r.draw();
                rects.add(r);
                names.add(nome);

                Text t = new Text(e.x, e.y - 10, nome);
                t.draw();
            }
        }
*/
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMENU:");
            System.out.println("1) Accendi tutte");
            System.out.println("2) Spegni tutte");
            System.out.println("3) Aggiungi lampadina");
            System.out.println("4) Rimuovi lampadina");
            System.out.println("5) Cerca lampadina");
            System.out.println("6) Mostra sistema");
            System.out.println("7) Salva");
            System.out.println("8) Carica");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");
            int s = in.nextInt();
            in.nextLine();

            switch (s) {
                case 1:
                    sistema.accendiTutte();
                    break;
                case 2:
                    sistema.spegniTutte();
                    break;
                case 3:
                    System.out.println("aggiungi lampadina");
                    System.out.println("inserisci potenza");
                    sistema.add(new LampadinaIntelligente(in.nextInt()));




                case 4:
                    System.out.print("Nome da rimuovere: ");
                    String nomeR = in.nextLine();
                    /*
                    if (sistema.rimuovi(nomeR)) {
                        for (int i = 0; i < names.size(); i++) {
                            if (names.get(i).equals(nomeR)) {
                                rects.get(i).delete();
                                rects.remove(i);
                                names.remove(i);
                                break;
                            }
                        }
                    }*/
                    break;
                case 5:
                    System.out.print("Nome: ");
                    String nomeC = in.nextLine();
                    LampadinaIntelligente found = sistema.cerca(nomeC);
                    System.out.println(found != null ? found : "Non trovata");
                    break;
                case 6:
                    System.out.println(sistema);
                    break;
                case 7:
                    System.out.print("File salvataggio: ");
                    String fs = in.nextLine();
                    SalvataggioBinario.salva(sistema, fs);
                    break;
                case 8:
                    System.out.print("File da caricare: ");
                    String fc = in.nextLine();
                    sistema = (SistemaDomotico) SalvataggioBinario.carica(fc);
                    // Potresti voler ridisegnare lo stato
                    break;
                case 0:
                    exit = true;
                    break;
            }
/*
            // dopo ogni operazione, aggiorno colore di tutte le lampadine
            for (int i = 0; i < rects.size(); i++) {
                Rectangle r = rects.get(i);
                String nome = names.get(i);
                LampadinaIntelligente l = sistema.cerca(nome);
                if (l != null && l.getAcceso()) {
                    r.setColor(Color.YELLOW);
                } else {
                    r.setColor(Color.LIGHT_GRAY);
                }
                r.fill();
            }*/
        }
        System.out.println("Fine.");
        in.close();
    }
}
