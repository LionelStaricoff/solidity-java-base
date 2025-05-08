package com.openlab.configurationBlockchain;

import com.openlab.blockchain.model.DAO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@Component
public class BlockchainConnector {

    @Value("${spring.metamax.infuraSepoliaUrl}")
    private String sepoliaProjectId;

    @Value("${spring.metamax.credential}")
    private String privateKey;

    @PostConstruct
    public void connect() {
        try {
            // URL Infura completa
            String fullInfuraSepoliaUrl = "https://sepolia.infura.io/v3/" + sepoliaProjectId;
            System.out.println("Conectando a: " + fullInfuraSepoliaUrl);

            // Inicializar Web3j
            Web3j web3j = Web3j.build(new HttpService(fullInfuraSepoliaUrl));
            System.out.println("Web3 conectado.");

            // Credenciales desde la clave privada
            Credentials credentials = Credentials.create(privateKey);
            System.out.println("Credenciales cargadas.");

            // Dirección del contrato DAO desplegado
            String contractAddress = "0xF5C837590F56681E1ffdA0Da1696C47C6B7E3D17";
            DAO dao = DAO.load(contractAddress, web3j, credentials, new DefaultGasProvider());
            System.out.println("Contrato cargado.");

            // Verificar que hay código en la dirección (i.e., que es un contrato)
            String code = web3j.ethGetCode(contractAddress, DefaultBlockParameterName.LATEST)
                    .send().getCode();

            if (code != null && !code.equals("0x")) {
                System.out.println("Contrato verificado en red.");
            } else {
                System.out.println("No hay contrato en esa dirección.");
                return;
            }

            // ✅ Obtener el balance del contrato DAO (en wei)
            BigInteger balance = web3j.ethGetBalance(contractAddress, DefaultBlockParameterName.LATEST)
                    .send()
                    .getBalance();

            System.out.println("Balance del contrato DAO (wei): " + balance);

        } catch (Exception e) {
            System.err.println("Error al conectar con el contrato:");
            e.printStackTrace();
        }
    }
}
