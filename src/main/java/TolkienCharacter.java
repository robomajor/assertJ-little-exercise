import java.util.Objects;

public class TolkienCharacter {
    private final String name;
    private final int age;
    private final Race race;

    public TolkienCharacter(String name, int age, Race race) {
        this.name = name;
        this.age = age;
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Race getRace() {
        return race;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TolkienCharacter that = (TolkienCharacter) o;
        return age == that.age && name.equals(that.name) && race == that.race;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, race);
    }

    @Override
    public String toString() {
        return "TolkienCharacter{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", race=" + race +
                '}';
    }
}
