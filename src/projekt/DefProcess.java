package projekt;

public class DefProcess {
    private String name;
    private int referenceNumber;
    private int arrivalTime;
    private int referenceQuantity;
    private int timeRecentlyUsed;


    public DefProcess(String name, int arrivalTime, int reference) {
        this.name = name;
        this.referenceNumber = generateReference(reference);
        this.arrivalTime = arrivalTime;
        this.referenceQuantity = 0;
        this.timeRecentlyUsed = 0;
    }

    @Override
    public String toString() {
        return  "Nazwa procesu: " + name +
                "\t\tOdwolanie: " + referenceNumber +
                "\t\tCzas przyjscia: " + arrivalTime +
                "\t\tilosc odwolan do strony: " + referenceQuantity +
                "\t\tOd ostatniego uzycia minelo " + timeRecentlyUsed;
    }

    private int generateReference(int t){
        int x = (int) (Math.random()*t);
        if (x == 10)
            x = x-1;
        return x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getReferenceQuantity() {
        return referenceQuantity;
    }

    public void setReferenceQuantity(int referenceQuantity) {
        this.referenceQuantity = referenceQuantity;
    }

    public int getTimeRecentlyUsed() {
        return timeRecentlyUsed;
    }

    public void setTimeRecentlyUsed(int timeRecentlyUsed) {
        this.timeRecentlyUsed = timeRecentlyUsed;
    }
}
