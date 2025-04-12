package com.openlab.blockchain.model;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
public class FounMe extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b505f80546001600160a01b031990811673694aa1769357215de4fac081bf1f309adc3253061790915560018054909116331790556108598061004f5f395ff3fe6080604052600436106100d9575f3560e01c80638da5cb5b1161007c578063c70cb2c111610057578063c70cb2c1146101fc578063e36a61fb14610204578063f3df0f2414610223578063f92c831714610242575f5ffd5b80638da5cb5b1461018657806398d5fdca146101bd57806398df4433146101d1575f5ffd5b80633ccfd60b116100b75780633ccfd60b1461012c5780636e5b6b2814610134578063742887ff146101535780638c979f1114610172575f5ffd5b80630d8e6e2c146100dd57806312065fe01461010457806327216b9d14610116575b5f5ffd5b3480156100e8575f5ffd5b506100f1610256565b6040519081526020015b60405180910390f35b34801561010f575f5ffd5b50476100f1565b348015610121575f5ffd5b5061012a6102cf565b005b61012a61030f565b34801561013f575f5ffd5b506100f161014e3660046106ac565b6103dc565b34801561015e575f5ffd5b5061012a61016d3660046106c3565b610408565b34801561017d575f5ffd5b5061012a61044b565b348015610191575f5ffd5b506001546101a5906001600160a01b031681565b6040516001600160a01b0390911681526020016100fb565b3480156101c8575f5ffd5b506100f16104cf565b3480156101dc575f5ffd5b506100f16101eb3660046106c3565b60026020525f908152604090205481565b61012a610545565b34801561020f575f5ffd5b506101a561021e3660046106ac565b61060e565b34801561022e575f5ffd5b506100f161023d3660046106ac565b610636565b34801561024d575f5ffd5b506100f1610666565b5f5f5f9054906101000a90046001600160a01b03166001600160a01b03166354fd4d506040518163ffffffff1660e01b8152600401602060405180830381865afa1580156102a6573d5f5f3e3d5ffd5b505050506040513d601f19601f820116820180604052508101906102ca91906106e9565b905090565b6001546001600160a01b031633146103025760405162461bcd60e51b81526004016102f990610700565b60405180910390fd5b61030d60035f61067e565b565b6001546001600160a01b031633146103395760405162461bcd60e51b81526004016102f990610700565b6040515f90339047908381818185875af1925050503d805f8114610378576040519150601f19603f3d011682016040523d82523d5f602084013e61037d565b606091505b50509050806103d95760405162461bcd60e51b815260206004820152602260248201527f4c61207472616e73666572656e63696120646520666f6e646f732066616c6cc3604482015261599760f11b60648201526084016102f9565b50565b5f670de0b6b3a76400006103ee610666565b6103f8908461075a565b6104029190610771565b92915050565b6001546001600160a01b031633146104325760405162461bcd60e51b81526004016102f990610700565b6001600160a01b03165f90815260026020526040812055565b6001546001600160a01b031633146104755760405162461bcd60e51b81526004016102f990610700565b5f5b6003548110156104c35760025f6003838154811061049757610497610790565b5f9182526020808320909101546001600160a01b03168352820192909252604001812055600101610477565b5061030d60035f61067e565b5f805460408051633fabe5a360e21b8152905183926001600160a01b03169163feaf968c9160048083019260a09291908290030181865afa158015610516573d5f5f3e3d5ffd5b505050506040513d601f19601f8201168201806040525081019061053a91906107c2565b509195945050505050565b6802b5e3af16b188000080610559346103dc565b10156105a75760405162461bcd60e51b815260206004820152601a60248201527f796f75206e65656420746f207370656e64206d6f72652045544800000000000060448201526064016102f9565b335f90815260026020526040812080543492906105c5908490610810565b9091555050600380546001810182555f919091527fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0180546001600160a01b0319163317905550565b6003818154811061061d575f80fd5b5f918252602090912001546001600160a01b0316905081565b5f5f6106406104cf565b9050670de0b6b3a7640000610655828561075a565b61065f9190610771565b9392505050565b5f61066f6104cf565b6102ca906402540be40061075a565b5080545f8255905f5260205f20908101906103d991905b808211156106a8575f8155600101610695565b5090565b5f602082840312156106bc575f5ffd5b5035919050565b5f602082840312156106d3575f5ffd5b81356001600160a01b038116811461065f575f5ffd5b5f602082840312156106f9575f5ffd5b5051919050565b60208082526026908201527f4e6f207469656e6573207065726d69736f20706172612072657469726172206660408201526537b73237b99760d11b606082015260800190565b634e487b7160e01b5f52601160045260245ffd5b808202811582820484141761040257610402610746565b5f8261078b57634e487b7160e01b5f52601260045260245ffd5b500490565b634e487b7160e01b5f52603260045260245ffd5b805169ffffffffffffffffffff811681146107bd575f5ffd5b919050565b5f5f5f5f5f60a086880312156107d6575f5ffd5b6107df866107a4565b60208701516040880151606089015192975090955093509150610804608087016107a4565b90509295509295909350565b808201808211156104025761040261074656fea2646970667358221220d68621d3eda85a1656eaa15d3ff6393e6a2a06297ce27b56d48a4a9296c2bd1764736f6c634300081d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ADDRESSTOAMOUNTFUNDED = "addressToAmountfunded";

    public static final String FUNC_CLEARMAPPING_ADDRESSTOAMOUNTFUNDED = "clearMapping_addressToAmountfunded";

    public static final String FUNC_DELETEADDRESS = "deleteAddress";

    public static final String FUNC_FONDERS = "fonders";

    public static final String FUNC_FUNDE = "funde";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETCONVERSIONRATE = "getConversionRate";

    public static final String FUNC_GETCONVERTERETHATUSD = "getConverterEthAtUSd";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_GETVERSION = "getVersion";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PRICETOWEI = "priceToWei";

    public static final String FUNC_VACIAR_LISTA_DE_FONDERS_Y_DATAFEED = "vaciar_Lista_De_Fonders_Y_DataFeed";

    public static final String FUNC_WITHDRAW = "withdraw";

    @Deprecated
    protected FounMe(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected FounMe(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected FounMe(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected FounMe(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> addressToAmountfunded(String param0) {
        final Function function = new Function(FUNC_ADDRESSTOAMOUNTFUNDED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> clearMapping_addressToAmountfunded() {
        final Function function = new Function(
                FUNC_CLEARMAPPING_ADDRESSTOAMOUNTFUNDED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteAddress(String addresDirection) {
        final Function function = new Function(
                FUNC_DELETEADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, addresDirection)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> fonders(BigInteger param0) {
        final Function function = new Function(FUNC_FONDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> funde(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_FUNDE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> getBalance() {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getConversionRate(BigInteger etherAmount) {
        final Function function = new Function(FUNC_GETCONVERSIONRATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(etherAmount)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getConverterEthAtUSd(BigInteger amountWei) {
        final Function function = new Function(FUNC_GETCONVERTERETHATUSD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amountWei)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPrice() {
        final Function function = new Function(FUNC_GETPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getVersion() {
        final Function function = new Function(FUNC_GETVERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> priceToWei() {
        final Function function = new Function(FUNC_PRICETOWEI, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> vaciar_Lista_De_Fonders_Y_DataFeed() {
        final Function function = new Function(
                FUNC_VACIAR_LISTA_DE_FONDERS_Y_DATAFEED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static FounMe load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new FounMe(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static FounMe load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new FounMe(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static FounMe load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new FounMe(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static FounMe load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new FounMe(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<FounMe> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(FounMe.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<FounMe> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(FounMe.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<FounMe> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(FounMe.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<FounMe> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(FounMe.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
