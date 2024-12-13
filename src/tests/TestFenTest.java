package tests;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import view.FenTest;
public  class TestFenTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    

    @Override
    public void onSetUp() { 
        

        FenTest frame = GuiActionRunner.execute(() -> new FenTest());
        window = new FrameFixture(frame);
        window.show();
    }


	@Test
    public void TestFenetreOpened(){
        // Afficher la fenêtre
        window.show();

        // Vérifier que la fenêtre est visible
        assertThat(window.target().isVisible()).isTrue();
    }

    
    // void teardown() {
    //     window.cleanUp(); // Nettoie après chaque test
    // }

}
