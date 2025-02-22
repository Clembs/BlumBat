package test.java;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dao.BienDAO;
import dao.LocataireDAO;
import dao.LocationDAO;
import model.BienImmobilier;
import model.BienLocatif;
import model.Locataire;
import model.Location;
import model.Proprietaire;
import model.TypeBien;

@TestInstance(Lifecycle.PER_CLASS)
public class BienTest {
  private BienDAO bienDAO;
  private Proprietaire P;
  private BienImmobilier bien;
  private String id;

  @BeforeAll
  public void setUp() {
    bienDAO = new BienDAO();
    P = new Proprietaire(1, "Voisin", "Clément", "clembs@clembs.com", "");
    bien = new BienImmobilier("BienTest", TypeBien.BATIMENT, "11 rue des tulipes", "145 étage 3", "31400",
        "Toulouse");
    id = bien.getId();
  }

  @Test
  public void testConstructeurAvecValeurCorrecte() {
    assertNotNull(bien);
    assertEquals("BienTest", bien.getId());
    assertEquals(TypeBien.BATIMENT, bien.getTypeBien());
    assertEquals("11 rue des tulipes", bien.getAdresse());
    assertEquals("145 étage 3", bien.getComplementAdresse());
    assertEquals("31400", bien.getCodePostal());
    assertEquals("Toulouse", bien.getVille());
  }

  @Test
  public void testConstructeurAvecValeurNull() {
    BienImmobilier bien2 = new BienImmobilier(
        null,
        null,
        null,
        null,
        null,
        null);

    assertNotNull(bien2);
    assertNull(bien2.getId());
    assertNull(bien2.getTypeBien());
    assertNull(bien2.getAdresse());
    assertNull(bien2.getComplementAdresse());
    assertNull(bien2.getCodePostal());
    assertNull(bien2.getVille());
  }

  @Test
  public void testConstructeurAvecStringVide() {
    BienImmobilier bien3 = new BienImmobilier(
        "",
        TypeBien.BATIMENT,
        "",
        "",
        "",
        "");

    assertNotNull(bien3);
    assertEquals("", bien3.getId());
    assertEquals(TypeBien.BATIMENT, bien3.getTypeBien());
    assertEquals("", bien3.getAdresse());
    assertEquals("", bien3.getComplementAdresse());
    assertEquals("", bien3.getCodePostal());
    assertEquals("", bien3.getVille());
  }

  @Test
  public void testToStringAvecValeurValide() {
    String expected = "Bâtiment - 11 rue des tulipes, 31400 Toulouse";
    assertEquals(expected, bien.toString());
  }

  @Test
  public void testToStringAvecValeurNull() {
    BienImmobilier bien4 = new BienImmobilier(
        null,
        null,
        null,
        null,
        null,
        null);

    String expected = "null - null, null null";
    assertEquals(expected, bien4.toString());
  }

  @Test
  public void testToStringAvecStringVide() {
    BienImmobilier bien5 = new BienImmobilier(
        "",
        TypeBien.BATIMENT,
        "",
        "",
        "",
        "");

    String expected = "Bâtiment - ,  ";
    assertEquals(expected, bien5.toString());
  }

  @Test
  public void TestInsertionEtRecuperationBien() {
    bienDAO.create(bien, P);

    List<BienImmobilier> listbien = bienDAO.getAllBiens(P);

    BienImmobilier bienTrouvé = listbien
        .stream()
        .filter(b -> b.getId().equals(id))
        .findFirst()
        .orElse(null);

    assertNotNull(bienTrouvé);
    assertEquals(id, bienTrouvé.getId());

    bienDAO.delete(bien, P);
  }

  public void TestModificationAvecValeurValide() {
    bienDAO.create(bien, P);

    BienImmobilier nouveauBien = new BienImmobilier(
        bien.getId(),
        bien.getTypeBien(),
        bien.getAdresse(),
        bien.getComplementAdresse(),
        bien.getCodePostal(), "Paris");

    bienDAO.update(nouveauBien);

    List<BienImmobilier> listbien = bienDAO.getAllBiens(P);

    BienImmobilier bienTrouvé = listbien
        .stream()
        .filter(b -> b.getId().equals(id))
        .findFirst()
        .orElse(null);

    assertNotNull(bienTrouvé);
    assertEquals(id, bienTrouvé.getId());
    assertEquals("Paris", bienTrouvé.getVille());

    bienDAO.delete(bien, P);
  }

  @Test
  public void TestSuppressionBien() {
    bienDAO.create(bien, P);

    bienDAO.delete(bien, P);

    List<BienImmobilier> listbien = bienDAO.getAllBiens(P);

    BienImmobilier bienTrouvé = listbien
        .stream()
        .filter(b -> b.getId().equals(id))
        .findFirst()
        .orElse(null);

    assertNull(bienTrouvé);
  }

  @Test
  public void TestSuppressionBienSupprimeLocations() {
    BienLocatif bienLocatif = new BienLocatif(bien.getId(), TypeBien.LOGEMENT, bien.getAdresse(),
        bien.getComplementAdresse(), bien.getCodePostal(), bien.getVille(), "123456789012", 12, 20);
    bienDAO.create(bienLocatif, P);

    LocataireDAO locataireDAO = new LocataireDAO();
    Locataire locataire = new Locataire("LocataireTest", "Dupont", "Jean", "jean@dupont.com", "0123456789");
    locataireDAO.create(locataire, P);

    LocationDAO locationDAO = new LocationDAO();
    Location location = new Location(1000, LocalDate.now(), null, bien, locataire);
    locationDAO.create(location);

    bienDAO.delete(bienLocatif, P);

    List<Location> locations = locationDAO.getAllLocations(bienLocatif);

    assertEquals(0, locations.size());

    locataireDAO.delete(locataire);
  }
}
