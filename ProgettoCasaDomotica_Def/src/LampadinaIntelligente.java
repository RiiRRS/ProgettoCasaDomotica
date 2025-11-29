import java.io.Serializable;

public class LampadinaIntelligente implements Serializable {

    private int potenza;
    private int luminosita;    // valore tra 0 e 100
    private String colore;
    private String nome;
    private boolean acceso;

    public LampadinaIntelligente(int potenza){
        if (potenza < 0){
            potenza = Math.abs(potenza);
        }
        this.potenza = potenza;
        this.nome = "";
        this.luminosita = 50;
        this.colore = "bianco";
        this.acceso = false;
    }

    public int getPotenza(){
        return this.potenza;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;   // CORRETTO
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public boolean getAcceso(){
        return this.acceso;
    }

    public int getLuminosita() {
        return this.luminosita;
    }

    public void aumentaIlluminazione (){
        if (luminosita < 100) {
            luminosita += luminosita * 10 / 100;
            if (luminosita > 100) luminosita = 100;
        }
    }

    public void diminuisciIlluminazione (){
        if (luminosita > 0) {
            luminosita -= luminosita * 10 / 100;
            if (luminosita < 0) luminosita = 0;
        }
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getColore() {
        return this.colore;   // CORRETTO
    }

    @Override
    public String toString(){
        return "Nome: " + getNome() +
                " | Potenza: " + getPotenza() +
                " | Stato: " + getAcceso() +
                " | Illuminazione: " + getLuminosita() +
                " | Colore: " + getColore();
    }
}
