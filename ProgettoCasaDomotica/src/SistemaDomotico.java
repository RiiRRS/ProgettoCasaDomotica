public class SistemaDomotico {
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

    
}
