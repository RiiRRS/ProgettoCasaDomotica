/*attributi: private, metodi:public
differenza tra noi che creiamo la classe e noi che la utilizziamo nel codice
essendo potenza un attributo privato, non è possibile accedervi nella scrittura del codice*/
public class LampadinaIntelligente {
    private int potenza;
    private int luminosita;         //valore tra 0 e 100
    private String colore;
    private String nome;
    private boolean acceso;

    public LampadinaIntelligente(int potenza){
        if (potenza < 0){
            potenza = Math.abs(potenza);
        }
        this.potenza = potenza;     //this:a me stesso, indipendentemente dal nome che avrò--> potenza arriva da fuori
        this.nome = "";             //this si riferisce all'attributo privato esterno, ovvero: nome alla riga 8
        this.luminosita = 50;
        this.colore = "bianco";
        this.acceso = false;
    }

    public int getPotenza(){            //get: serve ad esporre il valore, getAttributo
        return this.potenza;
    }
    public void setNome(String nome) {      //parametro: il nome che vuoi settare
        this.nome = nome;                   //il nome di questo oggetto (this.nome) = il nome passato come parametro (nome)
    }
    public String getNome() {
        return this.nome = nome;
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
        if (luminosita <= 100) {
            this.luminosita = luminosita + (luminosita * 10 / 100);
        }
    }
    public void diminuisciIlluminazione (){
        if (luminosita >= 0) {
            this.luminosita = luminosita - (luminosita * 10 / 100);
        }
    }
    public void setColore(String colore) {
        this.colore = colore;
    }
    public String getColore() {
        return this.colore = colore;
    }

    public String toString(){
        return ("Nome: " + this.getNome() + " Potenza: " + this.getPotenza() + " Stato: " + this.getAcceso() + " illuminazione: " + this.getLuminosita() + " Colore: " + this.getColore());
    }


}