package edu.cwru.eecs293.ttf10.uxb;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by theof_000 on 9/19/2016.
 */
public class BuilderTest {

    private Hub.Builder validBuilder;
    private Integer validVersion;
    private List<Connector.Type> validConnectors;

    private Hub.Builder invalidBuilder;

    @Before
    public void setUp() {
        validVersion = 2016;
        validBuilder = new Hub.Builder(validVersion);
        validConnectors = new ArrayList<>();
        validConnectors.add(Connector.Type.COMPUTER);
        validConnectors.add(Connector.Type.PERIPHERAL);
        validBuilder.connectors(validConnectors);

        invalidBuilder = new Hub.Builder(null);
        invalidBuilder.connectors(null);
    }

    @Test
    public void build() throws Exception {
        Hub validHub = validBuilder.build();
        assertTrue(validHub.getVersion().equals(validVersion));
        assertTrue(validHub.getProductCode().equals(Optional.empty()));
        assertTrue(validHub.getSerialNumber().equals(Optional.empty()));
        assertTrue(validHub.getConnectors().equals(validConnectors));
    }

    @Test
    public void getThis() throws Exception {
        assertTrue(validBuilder.getThis() == validBuilder);
        assertTrue(invalidBuilder.getThis() == invalidBuilder);
    }

    @Test
    public void validate() throws Exception {

    }

    @Test
    public void productCode() throws Exception {

    }

    @Test
    public void serialNumber() throws Exception {

    }

    @Test
    public void connectors() throws Exception {

    }

    @Test
    public void getThis1() throws Exception {

    }

    @Test
    public void getConnectors() throws Exception {

    }

    @Test
    public void validate1() throws Exception {

    }

}