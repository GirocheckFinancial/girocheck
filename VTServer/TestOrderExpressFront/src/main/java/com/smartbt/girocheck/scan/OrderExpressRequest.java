package com.smartbt.girocheck.scan;

import com.smartbt.girocheck.servercommon.enums.TransactionType;
import com.smartbt.girocheck.servercommon.enums.ParameterName;
import com.smartbt.girocheck.servercommon.messageFormat.IdType;
import com.smartbt.girocheck.servercommon.utils.IMap;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderExpressRequest", propOrder = {
    "requestType",
    "terminalId",
    "login",
    "password",
    "idPos",
    "idService",
    "dateTime",
    "deposit",
    "tax",
    "total",
    "rate",
    "relieve",
    "idDestiny",
    "bankAutho",
    "firstName",
    "lastName",
    "bornDate",
    "street",
    "number",
    "zipCode",
    "idPais",
    "idEstado",
    "poblacion",
    "telephone",
    "idOcupation",
    "idType",
    "id"
})
public class OrderExpressRequest implements IMap {

private String requestType ;
private String terminalId ;
private String login ;
private String password ;
private String idPos ;
private String idService ;
private String dateTime ;
private String deposit ;
private String tax ;
private String total ;
private String rate ;
private String relieve ;
private String idDestiny ;
private String bankAutho ;
private String firstName ;
private String lastName ;
private String bornDate ;
private String street ;
private String number ;
private String zipCode ;
private String idPais ;
private String idEstado ;
private String poblacion ;
private String telephone ;
private String idOcupation ;
private int idType ;
private String id ;

    @Override
    public Map toMap() {
        Map map = new HashMap();

        map.put(TransactionType.TRANSACTION_TYPE, TransactionType.ORDER_EXPRESS_CONTRATACIONES);
        
        map.put(ParameterName.REQUEST_TYPE, requestType);
        map.put(ParameterName.TERMINAL_ID, terminalId);
        map.put(ParameterName.LOGIN, login);
        map.put(ParameterName.PASSWORD, password);
        map.put(ParameterName.IDPOS, idPos);
        map.put(ParameterName.IDSERVICE, idService);
        map.put(ParameterName.DATETIME, dateTime);
        map.put(ParameterName.DEPOSIT, deposit);
        map.put(ParameterName.TAX, tax);
        map.put(ParameterName.TOTAL, total);
        map.put(ParameterName.RATE, rate);
        map.put(ParameterName.RELIEVE, relieve);
        map.put(ParameterName.IDDESTINY, idDestiny);
        map.put(ParameterName.BANK_AUTHO, bankAutho);
        map.put(ParameterName.FIRST_NAME, firstName);
        map.put(ParameterName.LAST_NAME, lastName);
        map.put(ParameterName.BORNDATE, bornDate);
        map.put(ParameterName.STREET, street);
        map.put(ParameterName.NUMBER, number);
        map.put(ParameterName.ZIPCODE, zipCode);
        map.put(ParameterName.IDPAIS, idPais);
        map.put(ParameterName.IDESTADO, idEstado);
        map.put(ParameterName.POBLACION, poblacion);
        map.put(ParameterName.TELEPHONE, telephone);
        map.put(ParameterName.IDOCUPACION, idOcupation);
        map.put(ParameterName.IDTYPE, IdType.getIdType( idType));
        map.put(ParameterName.ID, id);
        return map;
    }

    /**
     * @return the requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * @param requestType the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the terminalId
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * @param terminalId the terminalId to set
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the idPos
     */
    public String getIdPos() {
        return idPos;
    }

    /**
     * @param idPos the idPos to set
     */
    public void setIdPos(String idPos) {
        this.idPos = idPos;
    }

    /**
     * @return the idService
     */
    public String getIdService() {
        return idService;
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(String idService) {
        this.idService = idService;
    }

    /**
     * @return the dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the deposit
     */
    public String getDeposit() {
        return deposit;
    }

    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    /**
     * @return the tax
     */
    public String getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the relieve
     */
    public String getRelieve() {
        return relieve;
    }

    /**
     * @param relieve the relieve to set
     */
    public void setRelieve(String relieve) {
        this.relieve = relieve;
    }

    /**
     * @return the idDestiny
     */
    public String getIdDestiny() {
        return idDestiny;
    }

    /**
     * @param idDestiny the idDestiny to set
     */
    public void setIdDestiny(String idDestiny) {
        this.idDestiny = idDestiny;
    }

    /**
     * @return the bankAutho
     */
    public String getBankAutho() {
        return bankAutho;
    }

    /**
     * @param bankAutho the bankAutho to set
     */
    public void setBankAutho(String bankAutho) {
        this.bankAutho = bankAutho;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the bornDate
     */
    public String getBornDate() {
        return bornDate;
    }

    /**
     * @param bornDate the bornDate to set
     */
    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the idPais
     */
    public String getIdPais() {
        return idPais;
    }

    /**
     * @param idPais the idPais to set
     */
    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    /**
     * @return the idEstado
     */
    public String getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado the idEstado to set
     */
    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * @return the poblacion
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * @param poblacion the poblacion to set
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the idOcupation
     */
    public String getIdOcupation() {
        return idOcupation;
    }

    /**
     * @param idOcupation the idOcupation to set
     */
    public void setIdOcupation(String idOcupation) {
        this.idOcupation = idOcupation;
    }

    /**
     * @return the idType
     */
    public int getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(int idType) {
        this.idType = idType;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    
    
}
