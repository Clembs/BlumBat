package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import model.Proprietaire;

class NomTest {

    @Test
    void testNomProp() {
        String Pnom = "Malefoy";
        String Pprenom = "Drago";
        String Pid = "1234";
        String Pemail = "drago.malefoy@mail.com";
        String Pmdp = "hatePotter";
        Proprietaire proprio = new Proprietaire(Pid, Pnom, Pprenom, Pemail, Pmdp);

        String Name = proprio.getPrenom();

        assertEquals(Name, "Drago");
    }
}
