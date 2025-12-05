import java.io.Serializable;
import graphics.*;

public class LampadinaIntelligente implements Serializable {

    private int potenza;
    private int luminosita;    // valore tra 0 e 100
    private String colore;
    private String nome;
    private boolean acceso;

    private int x;
    private int y;
    private Ellipse cerchio;

    public LampadinaIntelligente(int potenza){
        if (potenza < 0){
            potenza = Math.abs(potenza);
        }
        else if (potenza == 0) {
            System.out.println("Potenza impostata a 1 (valore minimo).");
            potenza = 1;
        }
        else if (potenza>100) {
            System.out.println("potenza impostatata a 100 poichè è quella massima ");
            potenza=100;
        }
        this.potenza = potenza;
        this.nome = "";
        this.luminosita = 50;
        this.colore = "giallo";
        this.acceso = false;
    }

    public void setPosizione(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void creaGrafica(Picture p) {

        int r = Math.max(10, potenza / 2);

        cerchio = new Ellipse(x - r/2, y - r/2, r, r);
        cerchio.setColor(Color.GRAY);
        cerchio.draw();
    }


    public void aggiornaGrafica() {
        if (cerchio == null) return;

        Color c;

        switch (colore.toLowerCase()) {
            case "rosso":  c = Color.RED; break;
            case "verde":  c = Color.GREEN; break;
            case "blu":    c = Color.BLUE; break;
            case "giallo": c = Color.YELLOW; break;
            default:       c = Color.WHITE; break;
        }

        if (!acceso) c = Color.GRAY;

        int r = Math.max(10, potenza / 2);

        int glowLayers = luminosita / 20;

        for (int i = 1; i <= glowLayers; i++) {
            Ellipse glow = new Ellipse(
                    x - (r/2 + i*3),
                    y - (r/2 + i*3),
                    r + i*6,
                    r + i*6
            );

            glow.setColor(c);
            glow.draw();
        }

        cerchio = new Ellipse(x - r/2, y - r/2, r, r);
        cerchio.setColor(c);
        cerchio.fill();
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
        aggiornaGrafica();
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
