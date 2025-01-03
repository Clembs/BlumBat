package test.java;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import model.BienImmobilier;
import model.Locataire;
import model.Location;
import model.TypeBien;

@TestInstance(Lifecycle.PER_CLASS)
public class LocationTest {

    private Location L;
    private BienImmobilier bien;
    private Locataire Loc;
    private LocalDate dateE;
    private LocalDate dateS;

    @BeforeAll
    public void setUpTest() {
        dateE = LocalDate.of(2005, 3, 24);
        dateS = LocalDate.of(2010, 3, 24);
        Loc = new Locataire("1234", "Clément", "Voisin", "test@test.com", "01234");
        bien = new BienImmobilier("dnwdndn2", TypeBien.BATIMENT, "11 rue des tulipes", " 145 étage 3 ", "31400", "Toulouse");
        L = new Location(100, dateE, dateS, bien, Loc);
    }

    @Test
    public void testConstructorWithValidValues() {
        assertNotNull(L);
        assertEquals(100, L.getLoyer());
        assertEquals(dateE, L.getDateEntree());
        assertEquals(dateS, L.getDateSortie());
        assertEquals(bien, L.getBien());
        assertEquals(Loc, L.getLocataire());

    }

    @Test
    public void testGuetters() {
        assertEquals(100, L.getLoyer());
        assertEquals(dateE, L.getDateEntree());
        assertEquals(dateS, L.getDateSortie());
        assertEquals(bien, L.getBien());
        assertEquals(Loc, L.getLocataire());
    }

}
