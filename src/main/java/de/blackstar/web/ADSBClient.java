package de.blackstar.web;

import de.blackstar.database.DataManager;
import de.blackstar.database.ServiceFactory;
import de.blackstar.flightradar24.Flightradar24;
import de.blackstar.flightradar24.Fr24FlightDetail;
import de.blackstar.flightradar24.Fr24FlightSearchDetail;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ADSBClient {

    public static final String SERVER_HOSTNAME = "192.168.178.42";
    public static final int SERVER_PORT = 30003;

    private static final Logger LOGGER = Logger.getLogger(ADSBClient.class);

    private Flightradar24 flightradar24;
    private DataManager dataManager;

    private long connectionRetries = 0;

    public void run() {

        if (this.connectionRetries <= 3) {

            this.increaseConnectionRetries();

            this.flightradar24 = new Flightradar24();
            this.dataManager = new DataManager();

            BufferedReader bufferedReader;

            try {

                Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                LOGGER.debug("Connected to server " + SERVER_HOSTNAME + ":" + SERVER_PORT);

                String tcpMessage;
                while ((tcpMessage = bufferedReader.readLine()) != null) {
                    this.processMessage(tcpMessage);
                }

            } catch (IOException e) {
                LOGGER.error("ERROR_WHILE_RECEIVE_TCP_PAKETS: " + e);
                this.run();
            }

        } else {
            LOGGER.info("SERVER_NOT_AVAILABLE_PROGRAM_HAS_STOPPED");
            System.exit(-1);
        }
    }

    private void processMessage(final String message) {
        String[] csvData = message.split(",");
        if (csvData[1].equals("1")) {
            String searchURL = Flightradar24.FR24_SEARCH_URL + csvData[10].trim();
            Fr24FlightSearchDetail searchDetail = this.flightradar24.getFr24FlightSearchDetailFromJson(searchURL);
            if (searchDetail != null) {
                if (!this.dataManager.existFlightWithFr24Id(searchDetail.getId())) {
                    Fr24FlightDetail flightDetail = this.flightradar24.getFr24FlightDetail(Flightradar24.FR24_FLIGHT_DETAIL_URL + searchDetail.getId());
                    if (flightDetail != null) {
                        this.dataManager.manageData(flightDetail);
                    }
                }
            }
            LOGGER.debug("--------------------------------------------------------------");
        }
    }

    private void increaseConnectionRetries() {
        this.connectionRetries++;
    }

}
