package com.openlab.blockchain.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes4;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class DAO extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_DAOURI = "daoURI";

    public static final String FUNC_EXECUTE = "execute";

    public static final String FUNC_GETTRUSTEDFORWARDER = "getTrustedForwarder";

    public static final String FUNC_ISVALIDSIGNATURE = "isValidSignature";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_SETMETADATA = "setMetadata";

    public static final String FUNC_SETTRUSTEDFORWARDER = "setTrustedForwarder";

    public static final String FUNC_SETDAOURI = "setDaoURI";

    public static final Event METADATASET_EVENT = new Event("MetadataSet", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event DEPOSITED_EVENT = new Event("Deposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event NATIVETOKENDEPOSITED_EVENT = new Event("NativeTokenDeposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NEWURI_EVENT = new Event("NewURI", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected DAO(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
            BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DAO(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DAO(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DAO(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<MetadataSetEventResponse> getMetadataSetEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(METADATASET_EVENT, transactionReceipt);
        ArrayList<MetadataSetEventResponse> responses = new ArrayList<MetadataSetEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MetadataSetEventResponse typedResponse = new MetadataSetEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._metadata = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MetadataSetEventResponse getMetadataSetEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(METADATASET_EVENT, log);
        MetadataSetEventResponse typedResponse = new MetadataSetEventResponse();
        typedResponse.log = log;
        typedResponse._metadata = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<MetadataSetEventResponse> metadataSetEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMetadataSetEventFromLog(log));
    }

    public Flowable<MetadataSetEventResponse> metadataSetEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(METADATASET_EVENT));
        return metadataSetEventFlowable(filter);
    }

    public static List<DepositedEventResponse> getDepositedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEPOSITED_EVENT, transactionReceipt);
        ArrayList<DepositedEventResponse> responses = new ArrayList<DepositedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositedEventResponse typedResponse = new DepositedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.token = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.reference = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DepositedEventResponse getDepositedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEPOSITED_EVENT, log);
        DepositedEventResponse typedResponse = new DepositedEventResponse();
        typedResponse.log = log;
        typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.token = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.reference = (String) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDepositedEventFromLog(log));
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPOSITED_EVENT));
        return depositedEventFlowable(filter);
    }

    public static List<NativeTokenDepositedEventResponse> getNativeTokenDepositedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NATIVETOKENDEPOSITED_EVENT, transactionReceipt);
        ArrayList<NativeTokenDepositedEventResponse> responses = new ArrayList<NativeTokenDepositedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NativeTokenDepositedEventResponse typedResponse = new NativeTokenDepositedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NativeTokenDepositedEventResponse getNativeTokenDepositedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NATIVETOKENDEPOSITED_EVENT, log);
        NativeTokenDepositedEventResponse typedResponse = new NativeTokenDepositedEventResponse();
        typedResponse.log = log;
        typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NativeTokenDepositedEventResponse> nativeTokenDepositedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNativeTokenDepositedEventFromLog(log));
    }

    public Flowable<NativeTokenDepositedEventResponse> nativeTokenDepositedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NATIVETOKENDEPOSITED_EVENT));
        return nativeTokenDepositedEventFlowable(filter);
    }

    public static List<NewURIEventResponse> getNewURIEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWURI_EVENT, transactionReceipt);
        ArrayList<NewURIEventResponse> responses = new ArrayList<NewURIEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewURIEventResponse typedResponse = new NewURIEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.daoURI = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NewURIEventResponse getNewURIEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NEWURI_EVENT, log);
        NewURIEventResponse typedResponse = new NewURIEventResponse();
        typedResponse.log = log;
        typedResponse.daoURI = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<NewURIEventResponse> newURIEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNewURIEventFromLog(log));
    }

    public Flowable<NewURIEventResponse> newURIEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWURI_EVENT));
        return newURIEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> deposit(String _token, BigInteger _amount,
            String _reference, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _token), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.web3j.abi.datatypes.Utf8String(_reference)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> daoURI() {
        final Function function = new Function(FUNC_DAOURI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> execute(byte[] _callId, List<Action> _actions,
            BigInteger _allowFailureMap) {
        final Function function = new Function(
                FUNC_EXECUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_callId), 
                new org.web3j.abi.datatypes.DynamicArray<Action>(Action.class, _actions), 
                new org.web3j.abi.datatypes.generated.Uint256(_allowFailureMap)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getTrustedForwarder() {
        final Function function = new Function(FUNC_GETTRUSTEDFORWARDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<byte[]> isValidSignature(byte[] _hash, byte[] _signature) {
        final Function function = new Function(FUNC_ISVALIDSIGNATURE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_hash), 
                new org.web3j.abi.datatypes.DynamicBytes(_signature)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes4>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(byte[] _metadata, String _initialOwner,
            String _trustedForwarder, String daoURI_) {
        final Function function = new Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(_metadata), 
                new org.web3j.abi.datatypes.Address(160, _initialOwner), 
                new org.web3j.abi.datatypes.Address(160, _trustedForwarder), 
                new org.web3j.abi.datatypes.Utf8String(daoURI_)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setMetadata(byte[] _metadata) {
        final Function function = new Function(
                FUNC_SETMETADATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(_metadata)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTrustedForwarder(String _newTrustedForwarder) {
        final Function function = new Function(
                FUNC_SETTRUSTEDFORWARDER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _newTrustedForwarder)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDaoURI(String newDaoURI) {
        final Function function = new Function(
                FUNC_SETDAOURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(newDaoURI)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static DAO load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new DAO(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DAO load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DAO(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DAO load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new DAO(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DAO load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DAO(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class Action extends DynamicStruct {
        public String to;

        public BigInteger value;

        public byte[] data;

        public Action(String to, BigInteger value, byte[] data) {
            super(new org.web3j.abi.datatypes.Address(160, to), 
                    new org.web3j.abi.datatypes.generated.Uint256(value), 
                    new org.web3j.abi.datatypes.DynamicBytes(data));
            this.to = to;
            this.value = value;
            this.data = data;
        }

        public Action(Address to, Uint256 value, DynamicBytes data) {
            super(to, value, data);
            this.to = to.getValue();
            this.value = value.getValue();
            this.data = data.getValue();
        }
    }

    public static class MetadataSetEventResponse extends BaseEventResponse {
        public byte[] _metadata;
    }

    public static class DepositedEventResponse extends BaseEventResponse {
        public String sender;

        public String token;

        public BigInteger amount;

        public String reference;
    }

    public static class NativeTokenDepositedEventResponse extends BaseEventResponse {
        public String sender;

        public BigInteger amount;
    }

    public static class NewURIEventResponse extends BaseEventResponse {
        public String daoURI;
    }
}
