package pl.isa.alphateam;

public class Main {
    public static void main(String[] args) {
        var boats = JSONParserBoat.getListOfBoatsFromDatabase();
        System.out.println(boats);
    }
}
