// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.8.2 <0.9.0;



import {AggregatorV3Interface} from "./AggregatorV3Interface.sol";

contract founMe {
    AggregatorV3Interface internal dataFeed;
    address payable public owner;
    //consultar transacciones desde direcciones
    mapping(address => uint256) public addressToAmountfunded;
    address[] public fonders; //aca se agregan las direcciones que envian dinero
 

    constructor() {
        dataFeed = AggregatorV3Interface(
            0x694AA1769357215DE4FAC081bf1f309aDC325306
        );
        owner = payable(msg.sender); // Asignar el propietario que es quien sube el contrato
    }

    //ennviar dinero desde una direccion
    function funde() public payable {
        uint256 minAmount = 50 * 10**18; //50 convertidos a wei,        getConverterEthAtUSd(50); //50 dolares en wei
        //fucion que verifica si el eth que se envia sea mayor a 50 usd sino manda el mensage de error.
        require(
            getConversionRate(msg.value) >= minAmount,
            "you need to spend more ETH"
        );
        addressToAmountfunded[msg.sender] += msg.value;
        fonders.push(msg.sender);
    }

    /**
     * Returns the latest price.
     */
    function getPrice() public view returns (uint256) {
        // ignorando datos desectructurados  que no se van a usar dejando el espacio vacio pero separando con la , como un opcional
        (, int256 answer, , , ) = dataFeed.latestRoundData();
        return uint256(answer);
    }

    function priceToWei() public view returns (uint256) {
        return getPrice() * 10000000000; // se multiplica para pasarlo a terminos de wei
    }

    function getVersion() public view returns (uint256) {
        return dataFeed.version();
    }

    function getConversionRate(uint256 etherAmount)
        public
        view
        returns (uint256)
    {
        return (etherAmount * priceToWei()) / 1000000000000000000;
    }

    //funcion que devuelve la conversion del valor en wei a dolares
    function getConverterEthAtUSd(uint256 amountWei)
        public
        view
        returns (uint256)
    {
        uint256 price = getPrice(); //precio del dolar en wei
        return (amountWei * price) / 10**18; //dividiendo por 10^18 para pasar a dolares
    }

    //modificadores de acceso
    modifier onlyOwner() {
        require(msg.sender == owner, "No tienes permiso para retirar fondos.");
        _;
    }

    //transferir todos los eth que tenga el contrato a la cuenta que ejecute este metodo
    function withdraw() public payable onlyOwner {
        // Usar call para transferir el balance del contrato
        (bool success, ) = msg.sender.call{value: address(this).balance}("");
        require(success, unicode"La transferencia de fondos falló.");
    }

    // Función para ver el balance actual del contrato
    function getBalance() public view returns (uint256) {
        return address(this).balance;
    }

    function vaciar_Lista_De_Fonders_Y_DataFeed() public onlyOwner {
        delete fonders;
    }

    function deleteAddress(address addresDirection) public onlyOwner {
        delete addressToAmountfunded[addresDirection];
    }

    

    function clearMapping_addressToAmountfunded() public onlyOwner {
        for (uint256 i = 0; i < fonders.length; i++) {
            delete addressToAmountfunded[fonders[i]];
        }
        delete fonders; // Esto limpia el array si así lo deseas
    }
}
