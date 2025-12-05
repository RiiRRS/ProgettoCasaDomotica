import java.io.*;
import java.util.*;

class LettoreCoordinate implements Serializable {

    public static ArrayList<EntryLampadina> leggiTutte() throws Exception {

        ArrayList<EntryLampadina> lista = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("Assets/Coordinate.txt"));

        String riga = br.readLine();

        while (riga != null) {

            String[] parti = riga.split(" ");

            int x = Integer.parseInt(parti[0]);
            int y = Integer.parseInt(parti[1]);
            String stanza = parti[2];

            lista.add(new EntryLampadina(50, x, y, stanza));

            riga = br.readLine();  // legge la riga successiva
        }

        br.close();
        return lista;
    }
}
