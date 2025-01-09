package test.java;

import dao.LocataireDAO;
import model.Locataire;
import model.Proprietaire;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LocataireTest {
  private Locataire locataire;
  private LocataireDAO locataireDao;
  private Locataire l1;
  private Locataire l2;
  private Proprietaire proprietaire;

  @BeforeEach
  public void setUpTest() {
    locataireDao = new LocataireDAO();
    proprietaire = new Proprietaire(1, "jack", "Mafia", "Jack@Mafia.com", "0123456789");
    locataire = new Locataire("67", "Mouloud", "Jean", "Jean@Mouloud.com", "0123456789");
    l1 = new Locataire("id1", "tic", "toc", "tic@toc.com", "1111111111");
    l2 = new Locataire("id2", "toc", "tic", "toc@tic.com", "2222222222");
  }

  @AfterEach
  public void tearDownTest() {
    locataireDao.delete(locataire);
    locataireDao.delete(l1);
    locataireDao.delete(l2);
  }

  @Test
  public void testCreateLocataire() {
    locataireDao.create(locataire, proprietaire);
    Locataire getLocataire = locataireDao.read(locataire.getId());
    assertNotNull(getLocataire);
    assertEquals(locataire.getNom(), getLocataire.getNom());
    assertEquals(locataire.getPrenom(), getLocataire.getPrenom());
    assertEquals(locataire.getEmail(), getLocataire.getEmail());
    assertEquals(locataire.getTelephone(), getLocataire.getTelephone());
  }

  @Test
  public void testReadLocataireQuiNExistePas() {
    Locataire getLocataire = locataireDao.read("Inexistant");
    assertNull(getLocataire);
  }

  @Test
  public void testCreatePlusieursLocataires() {
    locataireDao.create(l1, proprietaire);
    locataireDao.create(l2, proprietaire);
    assertNotNull(locataireDao.read(l1.getId()));
    assertNotNull(locataireDao.read(l2.getId()));
  }

  @Test
  public void testUpdateLocataire() {
    // Étape 1 : Créer le locataire
    locataireDao.create(locataire, proprietaire);

    // Étape 2 : Modifier le locataire
    Locataire updatedLocataire = new Locataire(locataire.getId(), "UpdatedName", "UpdatedPrenom",
        locataire.getEmail(), locataire.getTelephone());
    locataireDao.update(updatedLocataire);

    // Étape 3 : Lire et vérifier les nouvelles données
    Locataire getLocataire = locataireDao.read(locataire.getId());
    assertNotNull(getLocataire, "Le locataire mis à jour est introuvable");
    assertEquals("UpdatedName", getLocataire.getNom(), "Le nom n'a pas été mis à jour correctement");
    assertEquals("UpdatedPrenom", getLocataire.getPrenom(), "Le prénom n'a pas été mis à jour correctement");
  }

  @Test
  public void testGetAllLocataires() {
    List<Locataire> locatairesAvant = locataireDao.getAllLocataires(proprietaire);
    locataireDao.create(l1, proprietaire);
    List<Locataire> locatairesApres = locataireDao.getAllLocataires(proprietaire);
    assertEquals(locatairesAvant.size() + 1, locatairesApres.size());
  }

  @Test
  public void testGetAllLocatairesVide() {
    List<Locataire> locataires = locataireDao.getAllLocataires(proprietaire);
    if (!locataires.isEmpty()) {
      for (Locataire loc : locataires) {
        locataireDao.delete(loc);
      }
      locataires = locataireDao.getAllLocataires(proprietaire);
    }
    assertTrue(locataires.isEmpty());
  }

  @Test
  public void testGetters() {
    assertEquals("67", locataire.getId());
    assertEquals("Mouloud", locataire.getNom());
    assertEquals("Jean", locataire.getPrenom());
    assertEquals("Jean@Mouloud.com", locataire.getEmail());
    assertEquals("0123456789", locataire.getTelephone());
  }

  @Test
  public void testDeleteLocataire() {
    locataireDao.create(locataire, proprietaire);
    locataireDao.delete(locataire);
    assertNull(locataireDao.read(locataire.getId()));
  }
}
