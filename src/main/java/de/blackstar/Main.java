package de.blackstar;

import de.blackstar.web.ADSBClient;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public Main() {

        ADSBClient adsbClient = new ADSBClient();
        adsbClient.run();

    }

    public static void main(String[] args) {
        new Main();
    }
}
