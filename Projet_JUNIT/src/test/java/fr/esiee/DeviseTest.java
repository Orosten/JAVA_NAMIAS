package fr.esiee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviseTest {

    @Test
    void testAdd() {
        Devise m12CHF = new Devise(12, "CHF");
        Devise m14CHF = new Devise(14, "CHF");
        Devise expected = new Devise(26, "CHF");

        Devise result = m12CHF.add(m14CHF);

        assertEquals(expected, result, "L'addition des devises est incorrecte");
    }

    void testEquals() {
        Devise m12CHF = new Devise(12, "CHF");
        Devise m14CHF = new Devise(14, "CHF");
        Devise m14USD = new Devise(14, "USD");

        assertEquals(new Devise(12, "CHF"), m12CHF, "m12CHF devrait être égale à un objet identique");
        assertNotEquals(m12CHF, m14CHF, "m12CHF ne devrait pas être égale à m14CHF (quantité différente)");
        assertNotEquals(m14CHF, m14USD, "m14CHF ne devrait pas être égale à m14USD (monnaie différente)");
        assertNotEquals(null, m12CHF, "m12CHF ne devrait pas être égale à null");
        assertNotEquals("String", m12CHF, "m12CHF ne devrait pas être égale à un objet d'un autre type");
    }
}

