package com.openlab.configurationBlockchain;

import com.openlab.blockchain.model.FounMe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

@Component
public class BlockchainConnector {
    @Value("${spring.metamax.infuraSepoliaUrl}")
    private String SepoliaUrl;

    @Value("${spring.metamax.credential}")
    private String credential;

    @PostConstruct
    public void connect() {
        try {
            // Conexión al nodo Infura
            String fullInfuraSepoliaUrl = "https://sepolia.infura.io/v3/".concat(SepoliaUrl);
            System.out.println("URL de Infura: " + fullInfuraSepoliaUrl);

            // Iniciar Web3j
            Web3j web3j = Web3j.build(new HttpService(fullInfuraSepoliaUrl));
            System.out.println("Conexión con Web3j iniciada.");

            // Cargar credenciales desde la clave privada
            Credentials credentials = Credentials.create(credential);
            System.out.println("Credenciales cargadas correctamente.");

            // Información del contrato
            String contractAddress = "0x905d692f12c0169E37D2a293363d2DE2073F96c3";
            System.out.println("Dirección del contrato: " + contractAddress);

            // Cargar el contrato
            FounMe contract = FounMe.load(contractAddress, web3j, credentials, new DefaultGasProvider());

            // Verificar código en la dirección del contrato
            web3j.ethGetCode(contractAddress, DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .thenAccept(code -> {
                        if (!code.equals("0x")) {
                            System.out.println("¡El contrato está desplegado correctamente!");

                            // Obtener el balance de forma asíncrona
                            contract.getBalance().sendAsync().thenAccept(balance -> {
                                System.out.println("Balance: " + balance);
                            }).exceptionally(ex -> {
                                System.err.println("Error al obtener el balance: " + ex.getMessage());
                                return null;
                            });
                        } else {
                            System.out.println("No hay código en la dirección proporcionada.");
                        }
                    }).exceptionally(ex -> {
                        System.err.println("Error al verificar el código en la dirección del contrato: " + ex.getMessage());
                        return null;
                    });

            // Verificar conexión al contrato
            if (contract.isValid()) {
                System.out.println("¡Conexión exitosa al contrato en Sepolia!");
            } else {
                System.out.println("Error al conectar con el contrato.");
            }
        } catch (Exception e) {
            System.err.println("Se produjo un error al conectar con el contrato:");
            e.printStackTrace();
        }
    }
}
