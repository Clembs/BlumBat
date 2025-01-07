package test.java;

import dao.LocataireDAO;
import model.Locataire;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocataireTest {
    private Locataire locataire;
    private LocataireDAO locataireDao;
    private Locataire locataireinvalide;
    private Locataire locataireNull;
    private Locataire l1;
    private Locataire l2;

    @BeforeAll
    public void setUpTest() {
        locataireDao = new LocataireDAO();
        locataire = new Locataire("1", "Mouloud", "Jean", "Jean@Mouloud.com", "0123456789");
        locataireinvalide = new Locataire("", "", "", "", "");
        l1  = new Locataire("id1", "tic", "toc", "tic@toc.com", "1111111111");
        l2 = new Locataire("id2", "toc", "tic", "toc@tic.com", "2222222222");
    }

    @Test
    public void testCreateLocataire() {
        locataireDao.create(locataire);
        Locataire getLocataire = locataireDao.read("1");
        assertEquals(locataire.getNom(), getLocataire.getNom());
        assertEquals(locataire.getPrenom(), getLocataire.getPrenom());
        assertEquals(locataire.getEmail(), getLocataire.getEmail());
        assertEquals(locataire.getTelephone(), getLocataire.getTelephone());
    }
    @Test
    public void testCreateLocataireAvecValeurVide() {
        locataireDao.create(locataireinvalide);
        Locataire getLocataire = locataireDao.read("");
        assertNull(getLocataire);
    }
    //Rechercher un locataire qui n'existe pas dans la bd :
    @Test
    public void testReadLocataireQuiNExistePas() {
        Locataire getLocataire = locataireDao.read("ExistePas");
        assertNull(getLocataire);
    }
    @Test
    public void testUpdateLocataireQuiNExistePas() {
        locataireDao.update(locataireNull);
        Locataire getLocataire = locataireDao.read("ExistePas");
        assertNull(getLocataire);
    }
   @Test
   public void testCreatePlusieursLocataires() {
       locataireDao.create(l1);
       locataireDao.create(l2);
       assertNotNull(locataireDao.read("id1"));
       assertNotNull(locataireDao.read("id2"));
   }
   @Test
   public void testUpdateLocataire() {
       locataireDao.update(locataire);
       Locataire getLocataire = locataireDao.read("1");
       assertEquals(locataire.getNom(), getLocataire.getNom());
       assertEquals(locataire.getPrenom(), getLocataire.getPrenom());
       assertEquals(locataire.getEmail(), getLocataire.getEmail());
       assertEquals(locataire.getTelephone(), getLocataire.getTelephone());
   }
   @Test
    public void testDeleteLocataire() {
        locataireDao.delete("1");
        assertNull(locataireDao.read("1"));
    }
    @Test
    public void testDeleteLocataireQuiNExistePas() {
        assertNull(locataireDao.read("ExistePas"));
        locataireDao.delete("ExistePas");
        assertNull(locataireDao.read("ExistePas"));
    }
    @Test
    public void testGetAllLocataires() {
        locataireDao.create(l1);
        locataireDao.create(l2);
        List<Locataire> locataires = locataireDao.getAll();
        assertEquals(2, locataires.size());
    }
    @Test
    public void testGetAllLocatairesVide() {
        List<Locataire> locataires = locataireDao.getAll();
        assertEquals(0, locataires.size());
    }
    @Test
    public void testGetters() {
        assertEquals("1", locataire.getId());
        assertEquals("Mouloud", locataire.getNom());
        assertEquals("Jean", locataire.getPrenom());
        assertEquals("Jean@Mouloud.com", locataire.getEmail());
        assertEquals("0123456789", locataire.getTelephone());
    }

}
