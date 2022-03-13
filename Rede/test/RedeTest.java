package rede;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Roberto Santos Cordeiro
 */
public class RedeTest {
    
    @Test
    public void validarIP(){
        assertTrue(Rede.validarIP("192.168.0.9"));
        assertTrue(Rede.validarIP("192.168.100.0"));
        assertTrue(Rede.validarIP("255.255.255.255"));
        assertTrue(Rede.validarIP("0.0.0.0"));
        
        assertFalse(Rede.validarIP(""));
        assertFalse(Rede.validarIP("..."));
        assertFalse(Rede.validarIP("192.168..9"));
        assertFalse(Rede.validarIP("192.168:0.9"));
        assertFalse(Rede.validarIP("192.1g8.0.9"));
        assertFalse(Rede.validarIP("192.168.0.009"));
        assertFalse(Rede.validarIP("192.168.0.9."));
        assertFalse(Rede.validarIP(".192.168.0.9"));
        assertFalse(Rede.validarIP("256.0.0.0"));
        assertFalse(Rede.validarIP("255.256.0.0"));
    }
    
    @Test
    public void buscarIP(){
        String[] ips = {"192.168.0.9", "192.168.100.0", "255.255.255.255", "0.0.0.0"};
        String[] vacio = new String[0];
        
        assertEquals(Rede.buscarIP("192.168.0.9", ips), 0);
        assertEquals(Rede.buscarIP("192.168.100.0", ips), 1);
        assertEquals(Rede.buscarIP("255.255.255.255", ips), 2);
        assertEquals(Rede.buscarIP("0.0.0.0", ips), 3);
        assertEquals(Rede.buscarIP("192.168.100.9", ips), -1);
        assertEquals(Rede.buscarIP("192.168.0.009", ips), -1);
        assertEquals(Rede.buscarIP("", ips), -1);
        assertEquals(Rede.buscarIP("0.0.0.0", vacio), -1);
    }
    
    @Test
    public void validarMascara(){
        assertTrue(Rede.validarMascara("255.255.255.255"));
        assertTrue(Rede.validarMascara("255.255.255.254"));
        assertTrue(Rede.validarMascara("255.255.0.0"));
        assertTrue(Rede.validarMascara("128.0.0.0"));
        assertTrue(Rede.validarMascara("0.0.0.0"));
        
        // Pruebas similares a los tests de IPs
        assertFalse(Rede.validarMascara(""));
        assertFalse(Rede.validarMascara("..."));
        assertFalse(Rede.validarMascara("255.255..0"));
        assertFalse(Rede.validarMascara("255.255:0.0"));
        assertFalse(Rede.validarMascara("255.2R5.0.0"));
        assertFalse(Rede.validarMascara("255.255.0.000"));
        assertFalse(Rede.validarMascara("255.255.0.0."));
        assertFalse(Rede.validarMascara("256.0.0.0"));
        assertFalse(Rede.validarMascara("255.256.0.0"));
        
        // Pruebas más específicas para máscaras
        assertFalse(Rede.validarMascara("0.255.0.0"));
        assertFalse(Rede.validarMascara("255.255.0.255"));
        assertFalse(Rede.validarMascara("255.255.248.248"));
        assertFalse(Rede.validarMascara("255.255.248.128"));
        assertFalse(Rede.validarMascara("255.255.123.0"));
        
    }

    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
