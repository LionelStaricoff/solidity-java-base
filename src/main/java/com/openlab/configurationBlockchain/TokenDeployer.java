package com.openlab.configurationBlockchain;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

public class TokenDeployer {
    public void conectBlockchain() {
        try {
            // Conexión a la blockchain
            Web3j web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/YOUR_PROJECT_ID"));
            System.out.println("Conexión a la blockchain establecida.");

            // Cargar credenciales (clave privada)
            Credentials credentials = Credentials.create("TU_CLAVE_PRIVADA");

            // Inicializar parámetros de la transacción
            BigInteger nonce = web3j.ethGetTransactionCount(credentials.getAddress(), credentials::getAddress)
                    .send()
                    .getTransactionCount();
            BigInteger gasPrice = DefaultGasProvider.GAS_PRICE;
            BigInteger gasLimit = DefaultGasProvider.GAS_LIMIT;
            String contractBinary = "0x..."; // Bytecode del contrato compilado con solc

            // Crear y enviar la transacción
            EthSendTransaction ethSendTransaction = web3j.ethSendTransaction(
                    new org.web3j.protocol.core.methods.request.Transaction(
                            credentials.getAddress(),
                            nonce,
                            gasPrice,
                            gasLimit,
                            null, // Dirección destino (null para contrato nuevo)
                            BigInteger.ZERO, // Valor en ETH
                            contractBinary // Bytecode del contrato
                    )
            ).send();

            String transactionHash = ethSendTransaction.getTransactionHash();
            System.out.println("Hash de la transacción: " + transactionHash);

            // Intentar obtener el recibo de la transacción (esperar hasta que esté disponible)
            TransactionReceipt receipt = null;
            while (receipt == null) {
                Thread.sleep(1000); // Espera 1 segundo entre intentos
                receipt = web3j.ethGetTransactionReceipt(transactionHash).send().getTransactionReceipt().orElse(null);
            }

            System.out.println("Contrato desplegado con éxito, recibo: " + receipt.toString());

        } catch (Exception e) {
            System.err.println("Error al desplegar el contrato: " + e.getMessage());
        }
    }
}
