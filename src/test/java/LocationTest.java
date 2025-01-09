package test.java;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dao.BienDAO;
import dao.LocataireDAO;
import dao.LocationDAO;
import model.BienLocatif;
import model.Locataire;
import model.Location;
import model.Proprietaire;
import model.TypeBien;

@TestInstance(Lifecycle.PER_CLASS)
public class LocationTest {

  private LocationDAO locationDAO;
  private Location location;
  private BienLocatif bien;
  private Locataire locataire;
  private LocalDate dateEntree;
  private LocalDate dateSortie;
  private BienDAO bienDAO;
  private LocataireDAO locataireDAO;
  private Proprietaire proprietaire;

  @BeforeAll
  public void setUp() {
    proprietaire = new Proprietaire(1, "Voisin", "Clément", "clembs@clembs.com", "");
    locationDAO = new LocationDAO();
    bienDAO = new BienDAO();
    locataireDAO = new LocataireDAO();

    dateEntree = LocalDate.of(2005, 3, 24);
    dateSortie = LocalDate.of(2010, 3, 24);

    locataire = new Locataire("LocataireTest", "Clément", "Voisin", "test@test.com", "0123456789");
    bien = new BienLocatif("BienTest", TypeBien.LOGEMENT, "11 rue des tulipes", "145  étage 3", "31400", "Toulouse",
        "123456789123", 19, 2);
    location = new Location(100, dateEntree, dateSortie, bien, locataire);

    bienDAO.create(bien, proprietaire);
    locataireDAO.create(locataire, proprietaire);
  }

  @Test
  public void testConstructorWithValidValues() {
    assertNotNull(location);
    assertEquals(100, location.getLoyer());
    assertEquals(dateEntree, location.getDateEntree());
    assertEquals(dateSortie, location.getDateSortie());
    assertEquals(bien, location.getBien());
    assertEquals(locataire, location.getLocataire());
  }

  @Test
  public void testGetters() {
    assertEquals(100, location.getLoyer());
    assertEquals(dateEntree, location.getDateEntree());
    assertEquals(dateSortie, location.getDateSortie());
    assertEquals(bien, location.getBien());
    assertEquals(locataire, location.getLocataire());
  }

  @Test
  public void TestInsertionLocation() {
    locationDAO.create(location);

    List<Location> listLoc = locationDAO.getAllLocations(bien);

    Location LocTrouvé = listLoc
        .stream()
        .filter(l -> l.getBien().getId().equals(bien.getId()) &&
            l.getLocataire().getId().equals(locataire.getId()))
        .findFirst()
        .orElse(null);

    assertNotNull(LocTrouvé);
    assertEquals(100, LocTrouvé.getLoyer());
    assertEquals(dateEntree, LocTrouvé.getDateEntree());
    assertEquals(dateSortie, LocTrouvé.getDateSortie());
    assertEquals(bien.getId(), LocTrouvé.getBien().getId());
    assertEquals(locataire.getId(), LocTrouvé.getLocataire().getId());
  }

  @AfterAll
  public void cleanUp() {
    bienDAO.delete(bien, proprietaire);
    locataireDAO.delete(locataire);
  }
}
