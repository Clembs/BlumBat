package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import model.Proprietaire;

@TestInstance(Lifecycle.PER_CLASS)
public class ProprietaireTest {

    //private ProprietaireDAO PropDAO;
    private Proprietaire P;
    //private String id; 

    @BeforeAll
    public void setUpTest() {
        //PropDAO = new ProprietaireDAO();
        P = new Proprietaire("1", "Voisin", "Clément", "clembs@clembs.com", "");
        //id = P.getId();

    }

    @Test
    public void testConstructorWithValidValues() {
        assertNotNull(P);
        assertEquals("1", P.getId());
        assertEquals("Voisin", P.getNom());
        assertEquals("Clément", P.getPrenom());
        assertEquals("clembs@clembs.com", P.getEmail());
        assertEquals("", P.getMotDePasse());
    }

    @Test
    public void testConstructorWithNullValues() {
        Proprietaire P2 = new Proprietaire(
                null,
                null,
                null,
                null,
                null
        );

        assertNotNull(P2);
        assertNull(P2.getId());
        assertNull(P2.getNom());
        assertNull(P2.getPrenom());
        assertNull(P2.getEmail());
        assertNull(P2.getMotDePasse());

    }

    @Test
    public void testConstructorWithEmptyStrings() {
        Proprietaire P3 = new Proprietaire(
                "",
                "",
                "",
                "",
                ""
        );

        assertNotNull(P);
        assertEquals("", P3.getId());
        assertEquals("", P3.getNom());
        assertEquals("", P3.getPrenom());
        assertEquals("", P3.getEmail());
        assertEquals("", P3.getMotDePasse());
    }

    @Test
    public void testGetters() {
        assertEquals("1", P.getId());
        assertEquals("Voisin", P.getNom());
        assertEquals("Clément", P.getPrenom());
        assertEquals("clembs@clembs.com", P.getEmail());
        assertEquals("", P.getMotDePasse());
    }

}
