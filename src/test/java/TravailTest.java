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
import dao.TravailDAO;
import model.BienImmobilier;
import model.FactureTravaux;
import model.Proprietaire;
import model.TypeBien;

@TestInstance(Lifecycle.PER_CLASS)
public class TravailTest {
  private BienImmobilier bien;
  private Proprietaire proprietaire;
  private FactureTravaux factureTravaux;
  private BienDAO bienDAO;
  private TravailDAO travailDAO;
  private String id;

  @BeforeAll
  public void setUp() {
    bienDAO = new BienDAO();
    travailDAO = new TravailDAO();

    proprietaire = new Proprietaire(1, "Voisin", "Clément", "clembs@clembs.com", "");
    bien = new BienImmobilier("BienTravauxTest", TypeBien.BATIMENT, "11 rue des tulipes", "145 étage 3", "31400",
        "Toulouse");
    factureTravaux = new FactureTravaux(
        "FactureTravauxTest", "Reparation robinet", 100, 1234, "ClembsIndustries", LocalDate.now(), bien);
    id = factureTravaux.getId();

  }

  @Test
  public void testConstructeurAvecValeurCorrecte() {
    assertNotNull(factureTravaux);
    assertEquals("1", factureTravaux.getId());
    assertEquals(bien, factureTravaux.getBien());
    assertEquals(100, factureTravaux.getMontantFacture());
    assertEquals("Reparation robinet", factureTravaux.getDescription());
    assertEquals(1234, factureTravaux.getMontantDevis());
    assertEquals("ClembsIndustries", factureTravaux.getEntreprise());
  }

  @Test
  public void TestInsertionEtRecuperationFactureTravaux() {
    bienDAO.create(bien, proprietaire);
    travailDAO.create(factureTravaux, bien);

    List<FactureTravaux> listFactureTravaux = travailDAO.getAllTravaux(bien);

    FactureTravaux factureTravauxTrouvé = listFactureTravaux
        .stream()
        .filter(f -> f.getId().equals(id))
        .findFirst()
        .orElse((null));

    assertNotNull(factureTravauxTrouvé);
    assertEquals(id, factureTravauxTrouvé.getId());

    travailDAO.delete(factureTravaux);
    bienDAO.delete(bien, proprietaire);
  }

  @Test
  public void TestSupressionFacture() {
    bienDAO.create(bien, proprietaire);
    travailDAO.create(factureTravaux, bien);

    travailDAO.delete(factureTravaux);

    List<FactureTravaux> listFactureTravaux = travailDAO.getAllTravaux(bien);

    FactureTravaux factureTravauxTrouvé = listFactureTravaux
        .stream()
        .filter(f -> f.getId().equals(id))
        .findFirst()
        .orElse((null));

    assertNull(factureTravauxTrouvé);
    bienDAO.delete(bien, proprietaire);

  }
}
