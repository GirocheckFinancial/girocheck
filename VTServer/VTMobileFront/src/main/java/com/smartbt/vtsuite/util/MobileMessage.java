package com.smartbt.vtsuite.util;

/**
 * @author @Roberto Rodriguez :: <RobertoSoftwareEngineer@gmail.com>
 */
public enum MobileMessage { 
    INVALID_LOGIN_CREDENTIALS("Invalid Credentials","Credenciales no válidas"),
    CARD_NOT_PERSONALIZED("Card is not personalized.", "Tarjeta no personalizada"),
    CARD_BELONG_TO_ANOTHER_CLIENT("This card belongs to another client.", "Esta tarjeta pertenece a otro cliente."),
    MOBILE_CLIENT_ALREADY_EXIST("There is already a mobile user asociated to this SSN.", "Ya existe otro usuario asociado a este número de Seguridad Social"),
    USERNAME_IN_USE("Username already in use.", "Usuario en uso"),
    REQUIRED_FIELD("Required field: ", "Campo requerido: "),
    MOBILE_CLIENT_NOT_EXIST("Mobile client does not exist.", "Usuario no existe"),
    FORGOT_PASSWORD_KEY_MISMATCH("Invalid Access Code.", "Código de Acceso inválido"),
    FORGOT_PASSWORD_KEY_EXPIRED("Access Code has expired.", "Código de Acceso ha expirado."),
    INVALID_TOKEN("Invalid Token.", "Token inválido"),
    COULD_NOT_SEND_ACCESS_CODE("We were not able to send you the Access Code. Please contact customer service.", "No pudimos enviarte el Código de Acceso, contacte a Servicio al Cliente"),
    INVALID_OLD_PASSWORD("Invalid Old Password.", "Antigua contraseña incorrecta"), 
    ERROR_GENERAL("System Error, please try again or call customer support.", "Se ha producido un error. Contacte a Servicio al Cliente"),
    CLIENT_DOES_NOT_EXIST("Client does not exist", "Cliente no existe");

    private String en;
    private String es;

    private MobileMessage(String en, String es) {
        this.es = es;
        this.en = en;
    }

    public String get(String lang) { 
        switch (lang) {
            case "en":
                return this.getEn();
            default:
                return this.getEs();
        }
    }

    /**
     * @return the es
     */
    public String getEs() {
        return es;
    }

    /**
     * @param es the es to set
     */
    public void setEs(String es) {
        this.es = es;
    }

    /**
     * @return the en
     */
    public String getEn() {
        return en;
    }

    /**
     * @param en the en to set
     */
    public void setEn(String en) {
        this.en = en;
    }
}
