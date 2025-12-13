import java.io.Serializable;

public class SistemaDomotico implements Serializable {
    private LampadinaIntelligente[] lampadine;
    private int n_lamp;

    public SistemaDomotico(){
        lampadine = new LampadinaIntelligente[100];
    }

    public SistemaDomotico(int max){
        if(max >= 1) {
            lampadine = new LampadinaIntelligente[max];
        } else lampadine = new LampadinaIntelligente[100];
    }

    public boolean add(LampadinaIntelligente l) {
        if (n_lamp >= lampadine.length) return false;
        for (int i = 0; i < n_lamp; i++) {
            if (lampadine[i].getNome().equals(l.getNome())) {
                return false;
            }
        }
        lampadine[n_lamp] = l;
        n_lamp++;
        return true;
    }

    public LampadinaIntelligente cerca(String nome){
        for (int i = 0; i < n_lamp; i++) {
            if (lampadine[i].getNome().equals(nome)) return lampadine[i];
        }
        return null;
    }


    public int getNumeroLampadine() {
        return n_lamp;
    }

    public LampadinaIntelligente getLampadina(int i) {
        return lampadine[i];
    }


    public boolean rimuovi(String nome) {
        for (int i = 0; i < n_lamp; i++) {
            if (lampadine[i].getNome().equals(nome)) {
                for (int j = i; j < n_lamp - 1; j++) {
                    lampadine[j] = lampadine[j + 1];
                }
                lampadine[n_lamp - 1] = null;
                n_lamp--;
                return true;
            }
        }
        return false;
    }


    public void accendiTutte() {
        for (int i = 0; i < n_lamp; i++) {
            lampadine[i].setAcceso(true);
        }
    }


    public void spegniTutte() {
        for (int i = 0; i < n_lamp; i++) {
            lampadine[i].setAcceso(false);
        }
    }


    public double calcolaPotenzaAttuale() {
        double potenzaTotale = 0;
        for (int i = 0; i < n_lamp; i++) {
            if (lampadine[i].getAcceso()) {
                potenzaTotale += lampadine[i].getPotenza() * (lampadine[i].getPotenza() / 100.0);
            }
        }
        return potenzaTotale;
    }


    @Override
    public String toString() {
        String info = "Sistema Domotico con " + n_lamp + " lampadine (max " + lampadine.length + ")\n";
        for (int i = 0; i < n_lamp; i++) {
            info += lampadine[i].toString() + "\n";
        }
        info += "Potenza totale: " + calcolaPotenzaAttuale();
        return info;
    }

}