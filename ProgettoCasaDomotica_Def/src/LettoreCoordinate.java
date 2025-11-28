import java.io.*;
import java.util.ArrayList;
class LettoreCoordinate {

    public static ArrayList<EntryLampadina> leggiTutte() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("coordinate.txt"));

        ArrayList<EntryLampadina> lista = new ArrayList<>();
        String riga;

        while ((riga = br.readLine()) != null) {

            // formato: potenza x y #stanza
            String[] parti = riga.split(" ");
            int potenza = Integer.parseInt(parti[0]);
            int x = Integer.parseInt(parti[1]);
            int y = Integer.parseInt(parti[2]);
            String stanza = parti[3].substring(1); // rimuove '#'

            lista.add(new EntryLampadina(potenza, x, y, stanza));
        }

        br.close();
        return lista;
    }
}