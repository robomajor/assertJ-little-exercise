import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TolkienCharacterTest implements WithAssertions {

    static TolkienCharacter frodo, sam, merry, pippin, aragorn, boromir, legolas, gimli, gandalf, sauron;
    static List<TolkienCharacter> fellowshipOfTheRing = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        frodo = new TolkienCharacter("Frodo", 50, Race.HOBBIT);
        sam = new TolkienCharacter("Sam", 52, Race.HOBBIT);
        merry = new TolkienCharacter("Merry", 36, Race.HOBBIT);
        pippin = new TolkienCharacter("Pippin", 28, Race.HOBBIT);
        aragorn = new TolkienCharacter("Aragorn", 87, Race.MAN);
        boromir = new TolkienCharacter("Boromir", 41, Race.MAN);
        legolas = new TolkienCharacter("Legolas", 3000, Race.ELF);
        gimli = new TolkienCharacter("Gimli", 139, Race.DWARF);
        sauron = new TolkienCharacter("Sauron", 9000, Race.MAIAR);
        gandalf = new TolkienCharacter("Gandalf", 6000, Race.MAIAR);

        fellowshipOfTheRing.add(frodo);
        fellowshipOfTheRing.add(sam);
        fellowshipOfTheRing.add(merry);
        fellowshipOfTheRing.add(pippin);
        fellowshipOfTheRing.add(aragorn);
        fellowshipOfTheRing.add(boromir);
        fellowshipOfTheRing.add(legolas);
        fellowshipOfTheRing.add(gimli);
        fellowshipOfTheRing.add(gandalf);
    }

    @Test
    void a_few_simple_assertions() {
        assertThat("The Lord of the Rings").isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith("Rings");
    }

    @Test
    void some_assertions_about_races_in_fellowship() {
        // filters use introspection to get property/field values
        assertThat(fellowshipOfTheRing).filteredOn("race", Race.HOBBIT)
                .containsOnly(sam, frodo, pippin, merry);

        // nested properties are supported
        assertThat(fellowshipOfTheRing).filteredOn("race.name", "MAN")
                .containsOnly(aragorn, boromir);

        // you can apply different comparison
        assertThat(fellowshipOfTheRing).filteredOn("race", notIn(Race.HOBBIT, Race.MAN))
                .containsOnly(gandalf, gimli, legolas);

        assertThat(fellowshipOfTheRing).filteredOn("race.name", in("MAIAR", "MAN"))
                .containsOnly(gandalf, boromir, aragorn);

        assertThat(fellowshipOfTheRing).filteredOn("race", not(Race.HOBBIT))
                .containsOnly(gandalf, boromir, aragorn, gimli, legolas);

        // you can chain multiple filter criteria
        assertThat(fellowshipOfTheRing).filteredOn("race.name", "MAN")
                .filteredOn("name", not("Boromir"))
                .containsOnly(aragorn);
    }

    @Test
    void frodo_is_not_sauron() {
        assertThat(frodo.getName()).isEqualTo("Frodo");
        assertThat(frodo).isNotEqualTo(sauron);
    }

    @Test
    void frodo_is_named_frodo() {
        // chaining string specific assertions
        assertThat(frodo.getName()).startsWith("Fro")
                .endsWith("do")
                .isEqualToIgnoringCase("frodo");
    }

    @Test
    void fellowship_has_nine_members() {
        // collection specific assertions (there are plenty more)
        // in the examples below fellowshipOfTheRing is a List<TolkienCharacter>
        assertThat(fellowshipOfTheRing).hasSize(9)
                .contains(frodo, sam)
                .doesNotContain(sauron);
    }

    @Test
    void frodo_has_50_years() {
        // as() is used to describe the test and will be shown before the error message
        assertThat(frodo.getAge()).as("check %s's age", frodo.getName()).isEqualTo(50);
    }
    @Test
    void some_assertion_about_errors() {
        // exception assertion, standard style ...
        assertThatThrownBy(() -> {
            throw new Exception("boom!");
        }).hasMessage("boom!");
        // ... or BDD style
        Throwable thrown = catchThrowable(() -> {
            throw new Exception("boom!");
        });
        assertThat(thrown).hasMessageContaining("boom");
    }

    @Test
    void there_are_different_races_in_fellowship() {
        // combining filtering and extraction (yes we can)
        assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o"))
                .containsOnly(aragorn, frodo, legolas, boromir)
                .extracting(character -> character.getRace().toString())
                .contains("HOBBIT", "ELF", "MAN");
    }

    @Test
    void sauron_and_elrond_are_not_in_fellowship() {
        // using the 'extracting' feature to check fellowshipOfTheRing character's names
        assertThat(fellowshipOfTheRing).extracting(TolkienCharacter::getName)
                .doesNotContain("Sauron", "Elrond");
    }

    @Test
    void orcs_are_not_in_fellowship() {
        // using the 'extracting' feature to check fellowshipOfTheRing character's names
        assertThat(fellowshipOfTheRing).extracting(TolkienCharacter::getRace)
                .doesNotContain(Race.valueOf("ORC"));
    }

    @Test
    void there_are_sam_boromir_and_legolas() {
        // extracting multiple values at once grouped in tuples
        assertThat(fellowshipOfTheRing).extracting("name", "age", "race.name")
                .contains(tuple("Boromir", 41, "MAN"),
                        tuple("Sam", 52, "HOBBIT"),
                        tuple("Legolas", 3000, "ELF"));
    }

    @Test
    void some_assertion_about_fellowship_members() {
        // filtering a collection before asserting
        assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o"))
                .containsOnly(aragorn, frodo, legolas, boromir);
    }

    @Test
    void assertion_about_frodo_being_a_hobbit() {
        // combining filtering and extraction (yes we can)
        assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o"))
                .containsOnly(aragorn, frodo, legolas, boromir)
                .extracting(character -> character.getRace().toString())
                .contains("HOBBIT", "ELF", "MAN");
    }

}