import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 50, Race.HOBBIT);
        TolkienCharacter sam = new TolkienCharacter("Sam", 52, Race.HOBBIT);
        TolkienCharacter merry = new TolkienCharacter("Merry", 36, Race.HOBBIT);
        TolkienCharacter pippin = new TolkienCharacter("Pippin", 28, Race.HOBBIT);
        TolkienCharacter aragorn = new TolkienCharacter("Aragorn", 87, Race.MAN);
        TolkienCharacter boromir = new TolkienCharacter("Boromir", 41, Race.MAN);
        TolkienCharacter legolas = new TolkienCharacter("Legolas", 3000, Race.ELF);
        TolkienCharacter gimli = new TolkienCharacter("Gimli", 139, Race.DWARF);
        List<TolkienCharacter> fellowshipOfTheRing = new ArrayList<>();

        fellowshipOfTheRing.add(frodo);
        fellowshipOfTheRing.add(sam);
        fellowshipOfTheRing.add(merry);
        fellowshipOfTheRing.add(pippin);
        fellowshipOfTheRing.add(aragorn);
        fellowshipOfTheRing.add(boromir);
        fellowshipOfTheRing.add(legolas);
        fellowshipOfTheRing.add(gimli);

        System.out.println(fellowshipOfTheRing.toString());
    }
}
