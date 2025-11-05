package ragatanga.service;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakeEmailServiceTest {

    @Test
    void deveArmazenarEmailEnviado() {
        FakeEmailService emailService = new FakeEmailService();

        emailService.send("thiago@test.com", "Bem-vindo", "Olá Thiago!");

        List<String> enviados = emailService.getSent();
        assertEquals(1, enviados.size());
        assertEquals("thiago@test.com|Bem-vindo|Olá Thiago!", enviados.get(0));
    }

    @Test
    void deveArmazenarMultiplosEmails() {
        FakeEmailService emailService = new FakeEmailService();

        emailService.send("a@test.com", "S1", "B1");
        emailService.send("b@test.com", "S2", "B2");

        List<String> enviados = emailService.getSent();
        assertEquals(2, enviados.size());
        assertTrue(enviados.contains("a@test.com|S1|B1"));
        assertTrue(enviados.contains("b@test.com|S2|B2"));
    }

    @Test
    void listaNaoDeveSerModificavel() {
        FakeEmailService emailService = new FakeEmailService();
        emailService.send("test@test.com", "A", "B");

        List<String> enviados = emailService.getSent();

        assertThrows(UnsupportedOperationException.class, () -> {
            enviados.add("outro@test.com|X|Y");
        });
    }
}
