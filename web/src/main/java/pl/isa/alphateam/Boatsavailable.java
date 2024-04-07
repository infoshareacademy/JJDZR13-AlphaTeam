package pl.isa.alphateam;

public class Boatsavailable {
    private long id;
    private String nameboat;

    private int numberofdays;
    private boolean patent;


    public Boatsavailable(long id, String nameboat, boolean patent, int numberofdays) {
        this.id = id;
        this.nameboat = nameboat;
        this.patent = patent;
        this.numberofdays = numberofdays;
    }

    public long getId() {
        return id;
    }
    public String getNameboat() {
        return nameboat;
    }

    public int getNumberofdays() {
        return numberofdays;
    }

    public boolean getPatent() {
        return patent;
    }
}