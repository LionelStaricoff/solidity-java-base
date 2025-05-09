package com.openlab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import java.util.Map;

@RestController
@RequestMapping("/api/voting")
public class VotingController {

    private final Web3j web3j;

    public VotingController() {
        this.web3j = Web3j.build(new HttpService("https://sepolia.infura.io/v3/tu_project_id"));
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitSignedTransaction(@RequestBody Map<String, String> body) {
        try {
            String rawTx = body.get("rawTx");
            EthSendTransaction response = web3j.ethSendRawTransaction(rawTx).send();

            if (response.hasError()) {
                return ResponseEntity.badRequest().body(response.getError().getMessage());
            }

            return ResponseEntity.ok(Map.of("txHash", response.getTransactionHash()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar transacción: " + e.getMessage());
        }
    }
}

