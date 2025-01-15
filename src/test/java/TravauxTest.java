package test.java;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dao.BienDAO;
import dao.TravauxDAO;
import model.BienImmobilier;
import model.FactureTravaux;
import model.Proprietaire;
import model.TypeBien;

@TestInstance(Lifecycle.PER_CLASS)
public class TravauxTest {
  private BienImmobilier bien;
  private Proprietaire proprietaire;
  private FactureTravaux factureTravaux;
  private BienDAO bienDAO;
  private TravauxDAO travauxDAO;
  private String id;

  @BeforeAll
  public void setUp() {
    bienDAO = new BienDAO();
    travauxDAO = new TravauxDAO();

    proprietaire = new Proprietaire(1, "Voisin", "Clément", "clembs@clembs.com", "");
    bien = new BienImmobilier("BienTestTravaux", TypeBien.BATIMENT, "11 rue des tulipes", "145 étage 3", "31400",
        "Toulouse");
    factureTravaux = new FactureTravaux("1", bien, 100, "Reparation robinet", 1234, "ClembsIndustries");
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
    travauxDAO.create(factureTravaux, bien);

    List<FactureTravaux> listFactureTravaux = travauxDAO.getAllFacture(bien);

    FactureTravaux factureTravauxTrouvé = listFactureTravaux
        .stream()
        .filter(f -> f.getId().equals(id))
        .findFirst()
        .orElse((null));

    assertNotNull(factureTravauxTrouvé);
    assertEquals(id, factureTravauxTrouvé.getId());

    travauxDAO.delete(factureTravaux);
    bienDAO.delete(bien, proprietaire);
  }

  @Test
  public void TestSupressionFacture() {
    bienDAO.create(bien, proprietaire);
    travauxDAO.create(factureTravaux, bien);

    travauxDAO.delete(factureTravaux);

    List<FactureTravaux> listFactureTravaux = travauxDAO.getAllFacture(bien);

    FactureTravaux factureTravauxTrouvé = listFactureTravaux
        .stream()
        .filter(f -> f.getId().equals(id))
        .findFirst()
        .orElse((null));

    assertNull(factureTravauxTrouvé);
    bienDAO.delete(bien, proprietaire);

  }
}
