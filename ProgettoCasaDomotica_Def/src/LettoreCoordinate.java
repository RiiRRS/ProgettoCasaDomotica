import java.io.*;
import java.util.ArrayList;
class LettoreCoordinate {

    public static ArrayList<EntryLampadina> leggiTutte() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("Assets/Coordinate.txt"));

        ArrayList<EntryLampadina> lista = new ArrayList<>();
        String riga;

        while ((riga = br.readLine()) != null) {

            // formato: potenza x y #stanza
            String[] parti = riga.split(" ");
            int x = Integer.parseInt(parti[0]);
            int y = Integer.parseInt(parti[1]);
            String stanza = parti[2].substring(0); // rimuove '#'

            lista.add(new EntryLampadina(x, y, stanza));
        }

        br.close();
        return lista;
    }
}