package com.openlab.configurationBlockchain;

import com.openlab.blockchain.model.FounMe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;

@Component
public class BlockchainConnector {
    @Value("${spring.metamax.infuraSepoliaUrl}")
    private String SepoliaUrl;

    @Value("${spring.metamax.credential}")
    private String credential;

    @PostConstruct
    public void conect() throws IOException {


        // URL del nodo Sepolia (puedes usar Infura o Alchemy)
        String infuraSepoliaUrl = "https://mainnet.infura.io/v3/".concat(SepoliaUrl);

        // Iniciar Web3j
        Web3j web3j = Web3j.build(new HttpService(infuraSepoliaUrl));

        // Cargar credenciales desde la clave privada
        String privateKey = credential;
        Credentials credentials = Credentials.create(privateKey);

        // Información del contrato
        String contractAddress = "0x905d692f12c0169E37D2a293363d2DE2073F96c3";

        // Conectar el contrato generado automáticamente
        FounMe contract = FounMe.load(contractAddress, web3j, credentials, new DefaultGasProvider());

        // Verificar conexión (por ejemplo, llamando un método del contrato)
        if (contract.isValid()) {
            System.out.println("¡Conexión exitosa al contrato en Sepolia!");
        } else {
            System.out.println("Error al conectar con el contrato." + contract);

        }


    }
}

