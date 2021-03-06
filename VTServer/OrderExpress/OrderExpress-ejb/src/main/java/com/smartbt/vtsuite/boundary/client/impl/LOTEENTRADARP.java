package com.smartbt.vtsuite.boundary.client.impl;

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.17 at 06:53:29 PM EDT 
//
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.smartbt.vtsuite.utils.OrderExpressConstantValues;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idlote",
    "corresponsal",
    "transaccione"
})

@XmlRootElement(name = "LOTE_ENTRADA")
public class LOTEENTRADARP {

    @XmlElement(name = "ID_LOTE")
    private String idlote = "NULL";
    @XmlElement(name = "CORRESPONSAL", required = true)
    private LOTEENTRADARP.CORRESPONSAL corresponsal;
    @XmlElement(name = "TRANSACCION_E", required = true)
    private LOTEENTRADARP.TRANSACCIONE transaccione;

    public LOTEENTRADARP() {
    }
    
    /**
     * @return the idlote
     */
    public String getIdlote() {
        return idlote;
    }

    /**
     * @param idlote the idlote to set
     */
    public void setIdlote(String idlote) {
        this.idlote = idlote;
    }

    /**
     * @return the corresponsal
     */
    public LOTEENTRADARP.CORRESPONSAL getCorresponsal() {
        return corresponsal;
    }

    /**
     * @param corresponsal the corresponsal to set
     */
    public void setCorresponsal(LOTEENTRADARP.CORRESPONSAL corresponsal) {
        this.corresponsal = corresponsal;
    }

    /**
     * @return the transaccione
     */
    public LOTEENTRADARP.TRANSACCIONE getTransaccione() {
        return transaccione;
    }

    /**
     * @param transaccione the transaccione to set
     */
    public void setTransaccione(LOTEENTRADARP.TRANSACCIONE transaccione) {
        this.transaccione = transaccione;
    }


/********************************CORRESPONSAL*****************************************/
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idmerchant",
        "login",
        "password"
    })
    public static class CORRESPONSAL {

        @XmlElement(name = "ID_MERCHANT", required = true)
        private String idmerchant = OrderExpressConstantValues.ID_MERCHANT;
        @XmlElement(name = "LOGIN", required = true)
        private String login = OrderExpressConstantValues.LOGIN;
        @XmlElement(name = "PASSWORD", required = true)
        private String password = OrderExpressConstantValues.PASSWORD;

        public CORRESPONSAL() {
        }
        
        /**
         * @return the idmerchant
         */
        public String getIdmerchant() {
            return idmerchant;
        }

        /**
         * @param idmerchant the idmerchant to set
         */
        public void setIdmerchant(String idmerchant) {
            this.idmerchant = idmerchant;
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

    }
    
/***********************************TRANSACCIONE*******************************************/
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "requesttype",
        "idtransaccion",
        "reportaPagoInput"
    })
    public static class TRANSACCIONE {

        @XmlElement(name = "REQUEST_TYPE", required = true)
        private String requesttype = OrderExpressConstantValues.REQUEST_TYPE;
        @XmlElement(name = "ID_TRANSACCION", required = true)
        private String idtransaccion = "NULL";
        @XmlElement(name = "REPORTAR_PAGO_INPUT", required = true)
        private LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT reportaPagoInput;

        public TRANSACCIONE() {
        }
        
        /**
         * @return the requesttype
         */
        public String getRequesttype() {
            return requesttype;
        }

        /**
         * @param requesttype the requesttype to set
         */
        public void setRequesttype(String requesttype) {
            this.requesttype = requesttype;
        }

        /**
         * @return the idtransaccion
         */
        public String getIdtransaccion() {
            return idtransaccion;
        }

        /**
         * @param idtransaccion the idtransaccion to set
         */
        public void setIdtransaccion(String idtransaccion) {
            this.idtransaccion = idtransaccion;
        }

        /**
         * @return the reportaPagoInput
         */
        public LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT getReportaPagoInput() {
            return reportaPagoInput;
        }

        /**
         * @param reportaPagoInput the reportaPagoInput to set
         */
        public void setReportaPagoInput(LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT reportaPagoInput) {
            this.reportaPagoInput = reportaPagoInput;
        }

        
/*********************************REPORTAPAGOINPUT****************************************/
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "autonumber",
            "idteller",
            "documentacioncliente"
        })
        public static class REPORTAPAGOINPUT {

            @XmlElement(name = "AUTO_NUMBER", required = true)
            private String autonumber = "NULL";
            @XmlElement(name = "ID_TELLER", required = true)
            private String idteller = OrderExpressConstantValues.ID_TELLER;
            @XmlElement(name = "DOCUMENTACION_CLIENTE", required = true)
            private LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT.DOCUMENTACIONCLIENTE documentacioncliente;

            public REPORTAPAGOINPUT() {
            }

            /**
             * @return the autonumber
             */
            public String getAutonumber() {
                return autonumber;
            }

            /**
             * @param autonumber the autonumber to set
             */
            public void setAutonumber(String autonumber) {
                this.autonumber = autonumber;
            }

            /**
             * @return the idteller
             */
            public String getIdteller() {
                return idteller;
            }

            /**
             * @param idteller the idteller to set
             */
            public void setIdteller(String idteller) {
                this.idteller = idteller;
            }

            /**
             * @return the documentacioncliente
             */
            public LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT.DOCUMENTACIONCLIENTE getDocumentacioncliente() {
                return documentacioncliente;
            }

            /**
             * @param documentacioncliente the documentacioncliente to set
             */
            public void setDocumentacioncliente(LOTEENTRADARP.TRANSACCIONE.REPORTAPAGOINPUT.DOCUMENTACIONCLIENTE documentacioncliente) {
                this.documentacioncliente = documentacioncliente;
            }

/*********************************DOCUMENTACIONCLIENTE*******************************************/
            
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "visa",
                "pasaporte",
                "greencard",
                "ss",
                "matriculaconsular",
                "ife",
                "licencia"
            })
            public static class DOCUMENTACIONCLIENTE {

                @XmlElement(name = "VISA", required = true)
                private String visa = "NULL";
                @XmlElement(name = "PASAPORTE", required = true)
                private String pasaporte = "NULL";
                @XmlElement(name = "GREENCARD", required = true)
                private String greencard = "NULL";
                @XmlElement(name = "SS", required = true)
                private String ss = "NULL";
                @XmlElement(name = "MATRICULACONSULAR", required = true)
                private String matriculaconsular = "NULL";
                @XmlElement(name = "IFE", required = true)
                private String ife = "NULL";
                @XmlElement(name = "LICENCIA", required = true)
                private String licencia = "NULL";

                public DOCUMENTACIONCLIENTE() {
                }

                /**
                 * @return the visa
                 */
                public String getVisa() {
                    return visa;
                }

                /**
                 * @param visa the visa to set
                 */
                public void setVisa(String visa) {
                    this.visa = visa;
                }

                /**
                 * @return the pasaporte
                 */
                public String getPasaporte() {
                    return pasaporte;
                }

                /**
                 * @param pasaporte the pasaporte to set
                 */
                public void setPasaporte(String pasaporte) {
                    this.pasaporte = pasaporte;
                }

                /**
                 * @return the greencard
                 */
                public String getGreencard() {
                    return greencard;
                }

                /**
                 * @param greencard the greencard to set
                 */
                public void setGreencard(String greencard) {
                    this.greencard = greencard;
                }

                /**
                 * @return the ss
                 */
                public String getSs() {
                    return ss;
                }

                /**
                 * @param ss the ss to set
                 */
                public void setSs(String ss) {
                    this.ss = ss;
                }

                /**
                 * @return the matriculaconsular
                 */
                public String getMatriculaconsular() {
                    return matriculaconsular;
                }

                /**
                 * @param matriculaconsular the matriculaconsular to set
                 */
                public void setMatriculaconsular(String matriculaconsular) {
                    this.matriculaconsular = matriculaconsular;
                }

                /**
                 * @return the ife
                 */
                public String getIfe() {
                    return ife;
                }

                /**
                 * @param ife the ife to set
                 */
                public void setIfe(String ife) {
                    this.ife = ife;
                }

                /**
                 * @return the licencia
                 */
                public String getLicencia() {
                    return licencia;
                }

                /**
                 * @param licencia the licencia to set
                 */
                public void setLicencia(String licencia) {
                    this.licencia = licencia;
                }

                

            }

        }

    }

}
