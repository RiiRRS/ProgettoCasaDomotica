import java.io.*;

public class SalvataggioBinario {

    public static void salva(Object obj, String nomeFile) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeFile));
        out.writeObject(obj);
        out.close();
        System.out.println("Salvato");
    }

    public static Object carica(String nomeFile) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeFile));
        Object obj = in.readObject();
        in.close();
        return obj;
    }
}
