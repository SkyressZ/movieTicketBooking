package movieticketbooking;

public class Snack {
    private String theatreName;
    private String snackName;
    private double cost;

    public Snack(String theatreName, String snackName, double cost) {
        this.theatreName = theatreName;
        this.snackName = snackName;
        this.cost = cost;
    }

    public String getSnackName() {
        return snackName;
    }
    
    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("Item : %s\tCost : %.2f",this.snackName,this.cost);
    }

    
    
    
}
