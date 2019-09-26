import controller.Controller;
import ordination.*
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

    private Laegemiddel laegemiddel;
    private Patient patient;
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        controller = Controller.getTestController();
    }

    @Test
    public void controllerOpretPNTest1() {
        laegemiddel = new Laegemiddel("Bacon", 1, 2, 3, "ml");
        patient = new Patient("12091980 2222", "Bob", 80);

        PN PN = controller.opretPNOrdination(LocalDate.of(2019, 9, 12), LocalDate.of(2019, 9, 17), patient, laegemiddel,
                5);

        assertNotNull(PN);

        assertEquals(PN.getStartDen(), LocalDate.of(2019, 9, 12));

        assertEquals(PN.getSlutDen(), LocalDate.of(2019, 9, 17));

        // Patient

        assertEquals(PN.getLaegemiddel(), laegemiddel);

        assertEquals(PN.getAntalEnheder(), 5, 0.001);

    }

    @Test
    public void controllerOpretPNTest2() {
        laegemiddel = new Laegemiddel("Bacon", 1, 2, 3, "ml");
        patient = new Patient("12091980 2222", "Bob", 80);

        try {
            PN PN = controller.opretPNOrdination(LocalDate.of(2019, 9, 12), LocalDate.of(2019, 9, 11), patient,
                    laegemiddel, 5);
            fail();

        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Slutdatoen må ikke komme før startdatoen");
        }

    }
}
