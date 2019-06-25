package movieticketbooking;

public class Cost extends Gst{
    private String theatreName;
    private double elite, gold, silver, balcony, box;

    public Cost(String theatreName, double elite, double gold, double silver, double balcony, double box) {
        this.theatreName = theatreName;
        this.elite = elite;
        this.gold = gold;
        this.silver = silver;
        this.balcony = balcony;
        this.box = box;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public double getElite() {
        return elite;
    }

    public void setElite(double elite) {
        this.elite = elite;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getSilver() {
        return silver;
    }

    public void setSilver(double silver) {
        this.silver = silver;
    }

    public double getBalcony() {
        return balcony;
    }

    public void setBalcony(double balcony) {
        this.balcony = balcony;
    }

    public double getBox() {
        return box;
    }

    public void setBox(double box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return String.format("Theatre : %s\nElite Cost : %.2f\nGold Cost : %.2f\nSilver Cost : %.2f\nBalcony Cost : %.2f\nBox Cost : %.2f\n",theatreName,elite,gold,silver,balcony,box);
    }
    
    
    
}
