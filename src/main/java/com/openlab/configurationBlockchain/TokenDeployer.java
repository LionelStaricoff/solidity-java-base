package com.openlab.configurationBlockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class TokenDeployer {
    public void conectBlockchain() {
        try {
            // Conexión a la blockchain
            Web3j web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/YOUR_PROJECT_ID"));
            System.out.println("Conexión a la blockchain establecida.");

            // Cargar credenciales (clave privada)
            Credentials credentials = Credentials.create("TU_CLAVE_PRIVADA");

            // Inicializar parámetros de la transacción
            BigInteger nonce = web3j.ethGetTransactionCount(
                    credentials.getAddress(), // Dirección de la cuenta (origen de la transacción)
                    DefaultBlockParameterName.LATEST // Indica que queremos el último valor del nonce
            ).send().getTransactionCount();

            BigInteger gasPrice = DefaultGasProvider.GAS_PRICE;
            BigInteger gasLimit = DefaultGasProvider.GAS_LIMIT;
            String contractBinary = "0x..."; // Bytecode del contrato compilado con solc

            // Crear la transacción
            org.web3j.protocol.core.methods.request.Transaction transaction = new org.web3j.protocol.core.methods.request.Transaction(
                    credentials.getAddress(),
                    nonce,
                    gasPrice,
                    gasLimit,
                    null, // Dirección destino (null para contrato nuevo)
                    BigInteger.ZERO, // Valor en ETH
                    contractBinary // Bytecode del contrato
            );

            // Enviar la transacción de manera asincrónica
            CompletableFuture<EthSendTransaction> futureTransaction = web3j.ethSendTransaction(transaction).sendAsync();
            futureTransaction.thenAccept(ethSendTransaction -> {
                String transactionHash = ethSendTransaction.getTransactionHash();
                System.out.println("Hash de la transacción: " + transactionHash);

                // Obtener el recibo de la transacción de forma asincrónica
                CompletableFuture<EthGetTransactionReceipt> futureReceipt = web3j.ethGetTransactionReceipt(transactionHash).sendAsync();
                futureReceipt.thenAccept(receipt -> {
                    if (receipt.getTransactionReceipt().isPresent()) {
                        System.out.println("Contrato desplegado con éxito, recibo: " + receipt.getTransactionReceipt().get());
                    } else {
                        System.out.println("El recibo aún no está disponible.");
                    }
                }).exceptionally(e -> {
                    System.err.println("Error al obtener el recibo: " + e.getMessage());
                    return null;
                });
            }).exceptionally(e -> {
                System.err.println("Error al enviar la transacción: " + e.getMessage());
                return null;
            });
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
}

