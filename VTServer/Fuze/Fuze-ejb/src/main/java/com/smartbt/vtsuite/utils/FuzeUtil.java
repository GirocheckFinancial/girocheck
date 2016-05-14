/* File        : FuzeAPI.java
 * Created by  : Fuze Network
 * Description : Java client library that is created for version 4 of the Fuze API.  
 */
package com.smartbt.vtsuite.utils;//custome
/*-----Import Statements-----*/
import com.smartbt.girocheck.servercommon.utils.CustomeLogger;
import com.smartbt.vtsuite.model.Biller;
import com.smartbt.vtsuite.model.Data;
import com.smartbt.vtsuite.model.FuzeResponse;
import java.io.*;
import java.math.*;
import java.net.URL;
import java.net.URLEncoder;
import java.security.*;
import java.util.*;
import javax.net.ssl.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FuzeUtil {
    
//    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FuzeUtil.class);

	// private instance variables
	private String fuzeID;
	private String fuzeSalt;

	/* Default Constructor */
	public FuzeUtil() {
//            this.fuzeID = "100149"; //dev
//            this.fuzeSalt = "n[TtU@_]<-%?$*Y.@Yk}b-Fb?yT]#nB"; //dev
//            this.fuzeID = "100090"; //prod
//            this.fuzeSalt = "BIj]nLJ,w;mc0bYY<>EdJ3zb8C2C=4&I"; //prod
            
            String fuzeIdd = System.getProperty("PARAM_FUZE_ID");
            String fuzeSaltt = System.getProperty("PARAM_FUZE_SALT");

            if(fuzeIdd == null){
                this.fuzeID = "100090";//prod
            }
            else{
                this.fuzeID = fuzeIdd;
            }
            
            if(fuzeSaltt == null){
                this.fuzeSalt = "BIj]nLJ,w;mc0bYY<>EdJ3zb8C2C=4&I";//prod
            }else{
                this.fuzeSalt = fuzeSaltt;
            }
            
            
	}

	/* This method sets a fuzeID */
	public void setFuzeID(String fuzeID) {
		this.fuzeID = fuzeID;
	}

	/* This method grabs the fuzeID */
	public String getFuzeID() {
		return fuzeID;
	}

	/* This method sets the fuzeSalt */
	public void setFuzeSalt(String fuzeSalt) {
		this.fuzeSalt = fuzeSalt;
	}

	/* This method grabs the fuzeSalt */
	public String getFuzeSalt() {
		return fuzeSalt;
	}

	/*
	 * This method will lookup the current balance on a card. It will return
	 * return the data as a Map Object
	 */
//	public Map<String, String> balanceInquiry(Map<String, String> userInput) {
//		try {
//			Map<String, String> balanceInquiry = userInput;
//			String balinqURL = "https://sandbox.fuzeflow.com/gateway/balance/inquiry";
//
//			// creates list of required fields
//			ArrayList<String> balinqRequired = new ArrayList<String>();
//			balinqRequired.add("fuze_id");
//			balinqRequired.add("reference_id");
//			balinqRequired.add("account");
//			balinqRequired.add("version");
//			balinqRequired.add("store_id");
//			balinqRequired.add("clerk_id");
//
//			// makes sure user input has all of the required fields
//			for (String req : balinqRequired) {
//				try {
//					if (!balanceInquiry.containsKey(req)) {
//						throw new Exception();
//					}
//				} catch (Exception e) {
//					throw new Exception(req + " is a required field!", e);
//				}
//
//			}
//
//			// creates string of parameters
//			String parameters = createParameterString(balanceInquiry);
//			// passes a URL and list of parameters to return XML data
//			String xmlString = sendPost(balinqURL, parameters);
//			// creates a map object of XML data
//			Map<String, String> balanceInquiryXML = convertNodesFromXml(xmlString);
//
//			return balanceInquiryXML;
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//
//	}

	/*
	 * This method searches Fuze�s entire biller directory or limit searches to
	 * Fuze Recharge billers using the S2P flag. It will return the data as a
	 * Map Object.
	 */
	public FuzeResponse billerSearch(Map<String, String> userInput) throws Exception{

		try {
			Map<String, String> billerSearch = userInput;
//			String billSearchUrl = "https://sandbox.fuzeflow.com/gateway/biller/search?&";
			String billSearchUrl = System.getProperty("WS_FUZE_BILLSEARCH_URL");
                        if (billSearchUrl == null) {
//                            billSearchUrl = "https://sandbox.fuzeflow.com/gateway/biller/search?&"; //dev
                            billSearchUrl = "https://connect.fuzeflow.com/gateway/biller/search?&"; // prod
                        }

			// creates list of required fields
			ArrayList<String> billSearchRequired = new ArrayList<String>();
			billSearchRequired.add("S2P");
			billSearchRequired.add("account");
			billSearchRequired.add("amount");
			billSearchRequired.add("clerk");
			billSearchRequired.add("error_multiples");
			//billSearchRequired.add("fuze_id");
			billSearchRequired.add("store");
			billSearchRequired.add("terminal");
			billSearchRequired.add("version");

			// makes sure user input has all of the required fields
			for (String req : billSearchRequired) {
				try {
					if (!billerSearch.containsKey(req)) {
						throw new Exception();
					}
				} catch (Exception e) {
					throw new Exception(req + " is a required field!", e);
				}

			}

			// creates string of parameters
			String parameters = createParameterString(billerSearch);
			// passes a URL and list of parameters to return XML data

			String xmlString = sendPost(billSearchUrl, parameters);

                        
                        JAXBContext jc = JAXBContext.newInstance( FuzeResponse.class );
                        FuzeResponse fuzeResponse = unMarshallResponse(jc,xmlString);
                        
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse status value " + fuzeResponse.getStatus(),null);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse message value " + fuzeResponse.getMessage(),null);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse time value " + fuzeResponse.getTime(),null);
                        
//                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse ",null);
                        
                        if(fuzeResponse.getStatus().equals("100")){
                            Data d = fuzeResponse.getData();
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data count value "+ d.getCount(),null);
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data billers size value "+ d.getBillers().size(),null);

                            for (Biller biller : d.getBillers()) {
                                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse biller estimated time value " + biller.getEstimated_posting_time(),null);
                                CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse biller id value " + biller.getId(),null);
                            }
                        }
//                        System.out.println("END PRINTING THE FUZE DATA ");
//                        log.debug("[FuzeUtil] END PRINTING THE FUZE DATA ");

			return fuzeResponse;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] Error",e.getMessage());
                        throw new Exception(e);
//			return null;
		}

	}

	/*
	 * This method will lookup previously submitted payments to verify receipt
	 * and/or status. Must include either reference_id or transaction_id in
	 * order to get a valid response. It will return the data as a Map Object.
	 */
	public FuzeResponse lookupTransaction(Map<String, String> userInput) {
		try {

//			String lookTransURL = "https://sandbox.fuzeflow.com/gateway/transaction/lookup";
			String lookTransURL = System.getProperty("WS_FUZE_LOOKTRANS_URL");
                        if (lookTransURL == null) {
//                            lookTransURL = "https://sandbox.fuzeflow.com/gateway/transaction/lookup"; // dev
                            lookTransURL = "https://connect.fuzeflow.com/gateway/transaction/lookup"; //prod
                        }
			Map<String, String> lookupTransaction = userInput;

			// creates a list of required fields
			ArrayList<String> lookTransRequired = new ArrayList<String>();
			lookTransRequired.add("fuze_id");
			lookTransRequired.add("version");

			// makes sure user input has all of the required fields
			for (String req : lookTransRequired) {
				try {
					if (!lookupTransaction.containsKey(req)) {
						throw new Exception();
					}
				} catch (Exception e) {
					throw new Exception(req + " is a required field!", e);
				}

			}
			// checks for the conditional fields reference_id and transaction_id
//			if ((!lookupTransaction.containsKey("reference_id") && (!lookupTransaction
			if ((!lookupTransaction.containsKey("reference") && (!lookupTransaction
					.containsKey("transaction_id")))) {
				throw new Exception(
						"Either transaction_id or reference must be provided!");

			}

			// creates string of parameters
			String parameters = createParameterString(lookupTransaction);
			// passes a URL and list of parameters to return XML data
			String xmlString = sendPost(lookTransURL, parameters);
//			// creates a map object of XML data
//			Map<String, String> lookupTransactionXML = convertNodesFromXml(xmlString);
//
//			return lookupTransactionXML;
                        
                        JAXBContext jc = JAXBContext.newInstance( FuzeResponse.class );
                        FuzeResponse fuzeResponse = unMarshallResponse(jc,xmlString);
                        
//                        System.out.println("printing FuzeResponse status value " + fuzeResponse.getStatus());
//                        System.out.println("printing FuzeResponse message value " + fuzeResponse.getMessage());
//                        System.out.println("printing FuzeResponse time value " + fuzeResponse.getTime());
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse status value " + fuzeResponse.getStatus(),null);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse message value " + fuzeResponse.getMessage(),null);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse time value " + fuzeResponse.getTime(),null);
                        if(fuzeResponse.getStatus().equals("100")){
                            Data d = fuzeResponse.getData();
//                            System.out.println("printing FuzeResponse data status value "+ d.getStatus());
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data status value "+ d.getStatus(),null);
                        }
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] END PRINTING THE FUZE DATA ",null);

			return fuzeResponse;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] Error ",e.getMessage());
			return null;
		}

	}

	/*
	 * This method withdraws a sum of money from the specified account. It will
	 * return the data as a Map Object
	 */
//	public Map<String, String> processDebit(Map<String, String> userIinput) {
//		try {
//			Map<String, String> processDebit = userIinput;
//			String procDebUrl = "https://sandbox.fuzeflow.com/gateway/process/debit";
//
//			// creates list of required fields
//			ArrayList<String> procDebRequired = new ArrayList<String>();
//			procDebRequired.add("account");
//			procDebRequired.add("amount");
//			procDebRequired.add("card_sec_code");
//			procDebRequired.add("clerk");
//			procDebRequired.add("expiration_month");
//			procDebRequired.add("expiration_year");
//			procDebRequired.add("first_name");
//			procDebRequired.add("phone_number");
//			procDebRequired.add("last_name");
//			procDebRequired.add("postal_code");
//			procDebRequired.add("fuze_id");
//			procDebRequired.add("store");
//			procDebRequired.add("terminal");
//			procDebRequired.add("reference_id");
//			// makes sure user input has all of the required fields
//			for (String req : procDebRequired) {
//				try {
//					if (!processDebit.containsKey(req)) {
//						throw new Exception();
//					}
//				} catch (Exception e) {
//					throw new Exception(req + " is a required field!", e);
//				}
//
//			}
//
//			// creates string of parameters
//			String parameters = createParameterString(processDebit);
//			// passes a URL and list of parameters to return XML data
//			String xmlString = sendPost(procDebUrl, parameters);
//			// creates a map object of XML data
//			Map<String, String> processDebitXML = convertNodesFromXml(xmlString);
//
//			return processDebitXML;
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//
//	}

	/*
	 * This method submits payments and loads to a biller using the biller_id
	 * found from the billerSearch method. It will return return the data as a
	 * Map Object.
	 */
	public FuzeResponse processPayment(Map<String, String> userInput) throws Exception{
		try {

//			String procPayUrl = "https://sandbox.fuzeflow.com/gateway/process/payment";
			String procPayUrl = System.getProperty("WS_FUZE_PROCPAY_URL");
                        if (procPayUrl == null) {
//                            procPayUrl = "https://sandbox.fuzeflow.com/gateway/process/payment"; //dev
                            procPayUrl = "https://connect.fuzeflow.com/gateway/process/payment"; //prod
                        }
                        Map<String, String> processPayment = userInput;

			// creates list of required fields
			ArrayList<String> procPayRequired = new ArrayList<String>();
			procPayRequired.add("S2P");
			procPayRequired.add("account");
			procPayRequired.add("amount");
			procPayRequired.add("biller_id");
			procPayRequired.add("clerk");
			procPayRequired.add("first_name");
			procPayRequired.add("fuze_id");
			procPayRequired.add("last_name");
			procPayRequired.add("phone");
//			procPayRequired.add("reference_id");
			procPayRequired.add("reference");
			procPayRequired.add("send_text");
			procPayRequired.add("store");
			procPayRequired.add("tender_type");
			procPayRequired.add("terminal");
			procPayRequired.add("version");

			// makes sure user input has all of the required fields
			for (String req : procPayRequired) {
				try {
					if (!processPayment.containsKey(req)) {
						throw new Exception();
					}
				} catch (Exception e) {
					throw new Exception(req + " is a required field!", e);
				}

			}

			// creates string of parameters
			String parameters = createParameterString(processPayment);
			// passes a URL and list of parameters to return XML data
			String xmlString = sendPost(procPayUrl, parameters);

                        JAXBContext jc = JAXBContext.newInstance( FuzeResponse.class );
                        FuzeResponse fuzeResponse = unMarshallResponse(jc,xmlString);
                        
//                        System.out.println("printing FuzeResponse status value " + fuzeResponse.getStatus());
//                        System.out.println("printing FuzeResponse message value " + fuzeResponse.getMessage());
//                        System.out.println("printing FuzeResponse time value " + fuzeResponse.getTime());
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse status value " + fuzeResponse.getStatus(),null);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse message value " + fuzeResponse.getMessage(),null);
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse time value " + fuzeResponse.getTime(),null);
                        if(fuzeResponse.getStatus().equals("100")){
                            Data d = fuzeResponse.getData();
//                            System.out.println("printing FuzeResponse data pending balance value "+ d.getPending_balance());
//                            System.out.println("printing FuzeResponse data Receipt_text value "+ d.getReceipt_text());
//                            System.out.println("printing FuzeResponse data Reference value "+ d.getReference());
//                            System.out.println("printing FuzeResponse data Timestamp value "+ d.getTimestamp());
//                            System.out.println("printing FuzeResponse data transaction id value "+ d.getTransaction_id());
//                            System.out.println("printing FuzeResponse data estimated_posting_time "+ d.getEstimated_posting_time());
//                            System.out.println("printing Fuze[FuzeUtil]Response data estimated_posting_time "+ d.getEstimated_posting_time());
                            
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data pending balance value "+ d.getPending_balance(),null);
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data Receipt_text value "+ d.getReceipt_text(),null);
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data Reference value "+ d.getReference(),null);
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data Timestamp value "+ d.getTimestamp(),null);
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data transaction id value "+ d.getTransaction_id(),null);
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data estimated_posting_time "+ d.getEstimated_posting_time(),null);
                            CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing FuzeResponse data estimated_posting_time "+ d.getEstimated_posting_time(),null);
                        }
//                        System.out.println("END PRINTING THE FUZE DATA ");
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] END PRINTING THE FUZE DATA ",null);
                        
                        
                        
                        return fuzeResponse;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] Error ",e.getMessage());
                        throw new Exception(e);
//			return null;
		}

	}

	/*
	 * This method Voids a previously successful transaction if the transaction
	 * can be voided. Most transactions can not be voided or can only be voided
	 * for a short period of time after the initial successful payment
	 * transaction.
	 */
//	public Map<String, String> voidTransaction(Map<String, String> userInput) {
//		try {
//
//			String voidTransURL = "https://sandbox.fuzeflow.com/gateway/process/void";
//			Map<String, String> voidTransaction = userInput;
//
//			// creates list of required fields
//			ArrayList<String> voidTransRequired = new ArrayList<String>();
//			voidTransRequired.add("fuze_id");
//
//			// makes sure user input has all of the required fields
//			for (String req : voidTransRequired) {
//
//				if (!voidTransaction.containsKey(req)) {
//					throw new Exception(req + " is a required field!");
//				}
//
//			}
//			// checks for the conditional fields reference_id and transaction_id
//			if ((!voidTransaction.containsKey("reference_id") && (!voidTransaction
//					.containsKey("transaction_id")))) {
//				throw new Exception(
//						"Either transaction_id or reference must be provided!");
//
//			}
//
//			// creates string of parameters
//			String parameters = createParameterString(voidTransaction);
//			// passes a URL and list of parameters to return XML data
//			String xmlString = sendPost(voidTransURL, parameters);
//			// creates a map object of XML data
//			Map<String, String> voidTransactionXML = convertNodesFromXml(xmlString);
//
//			return voidTransactionXML;
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//
//	}

	/*
	 * This method creates and sorts an array list of user input, then converts
	 * it to a string.
	 */
	protected String createParameterString(Map<String, String> mapToConvert) {

		Map<String, String> parameterMap = mapToConvert;
		Map<String, String> parameterMapToSend = mapToConvert;
//		ArrayList<String> parameterList = new ArrayList<String>();
//		long unixTime = System.currentTimeMillis() / 1000L;
//		String fuzeTime = String.valueOf(unixTime);
                
                String track1;
                String track2;

		// iterates through each element in the map and adds it to an Array List
//		for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
//			String result = entry.getKey() + "=" + entry.getValue();
//			parameterList.add(result);
//		}
//
//		// appends fuze_time to the list
//		parameterList.add("fuze_time=" + fuzeTime);
//
//		// sorts list
//		Collections.sort(parameterList);
//
//		// converts list to string
//		StringBuilder result = new StringBuilder();
//		for (String s : parameterList) {
//			if (s.equals(parameterList.get(parameterList.size() - 1))) {
//				result.append(s);
//
//			}
//
//			else {
//				result.append(s);
//				result.append("&");
//
//			}
//		}
                
                //generating sign string with tracks sentinels
                String sortedString = StringToSend(parameterMap);
		// converts buffer to string
//		String sortedString = result.toString();
                
		// generates Fuze signature using MD5 hash
//                String tempString = sortedString.replace("%", "").replace("?", "").replace(";", "");
//		String fuzeSig = MD5("&" + tempString + this.getFuzeSalt());
//                System.out.println("DATA TO MAKE THE MD5 "+ sortedString);

		String fuzeSig = MD5("&" + sortedString + this.getFuzeSalt());
                
                if(parameterMapToSend.containsKey("track1")){
                    track1 = parameterMapToSend.get("track1");
//                    track1 = track1.replace("%", "").replace("?", "").replace(";", "");
                    parameterMapToSend.put("track1", URLEncoder.encode(track1));
                }
                if(parameterMapToSend.containsKey("track2")){
                    track2 = parameterMapToSend.get("track2");
//                    track2 = track2.replace("%", "").replace("?", "").replace(";", "");
                    parameterMapToSend.put("track2", URLEncoder.encode(track2));
                }
                
                //generating send string without tracks sentinels
                String sortedStringToSend = StringToSend(parameterMapToSend);
//                System.out.println("DATA TO PUT IN THE CALL "+ sortedStringToSend);
                
//		String parameters = sortedString + "&fuze_sig=" + fuzeSig;
		String parameters = sortedStringToSend + "&fuze_sig=" + fuzeSig;

		return parameters;
	}
        
        /*String to send generation method*/
        protected String StringToSend(Map<String, String> parameterMap){
            
            long unixTime = System.currentTimeMillis() / 1000L;
            String fuzeTime = String.valueOf(unixTime);
            
            ArrayList<String> parameterList = new ArrayList<String>();
            
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
			String result = entry.getKey() + "=" + entry.getValue();
			parameterList.add(result);
		}

		// appends fuze_time to the list
		parameterList.add("fuze_time=" + fuzeTime);

		// sorts list
		Collections.sort(parameterList);

		// converts list to string
		StringBuilder result = new StringBuilder();
		for (String s : parameterList) {
			if (s.equals(parameterList.get(parameterList.size() - 1))) {
				result.append(s);

			}

			else {
				result.append(s);
				result.append("&");

			}
		}
            return result.toString();
            
        }

	/* This method MD5 encrypts a string argument */
	protected String MD5(String unencrypted) {

		try {

			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(unencrypted.getBytes(), 0, unencrypted.length());
			String MD5 = new BigInteger(1, m.digest()).toString(16);

			return MD5;

		} catch (Exception e) {

//			System.out.println(e.getMessage());
			CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] Error ",e.getMessage());
			return null;
		}

	}

	/* HTTP Post request */
	protected String sendPost(String url, String urlParameters)throws Exception{

		try {

			URL connURL = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) connURL
					.openConnection();

			// add request header
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/xml");
			conn.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));

			// Send post request
			conn.setDoOutput(true);
			DataOutputStream write = new DataOutputStream(conn.getOutputStream());
			write.writeBytes(urlParameters);
			write.flush();
			write.close();

			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Failed conection: HTTP error code : "
						+ conn.getResponseCode());
			}

//			System.out.println("Sending 'POST' request to URL : " + url);
//			System.out.println("Post parameters : " + urlParameters);
//			System.out.println("Response Code : " + responseCode);
//			System.out.println("--------------------------------");
			CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] Sending 'POST' request to URL : " + url,null);
			CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] Post parameters : " + urlParameters,null);
			CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] Response Code : " + responseCode,null);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
//                        System.out.println("printing the BufferReader in "+response.toString());
                        CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] printing the BufferReader in "+response.toString(),null);

			conn.disconnect();

			String responseXML = response.toString();
			return responseXML;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			CustomeLogger.Output(CustomeLogger.OutputStates.Debug, "[FuzeUtil] Error ",e.getMessage());
                        throw new Exception(e.getMessage());
		}

	}

	/* This method returns a map of the XML data */
	protected Map<String, String> convertNodesFromXml(String xml)
			throws Exception {

		InputStream inputstream = new ByteArrayInputStream(xml.getBytes());
		DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory
				.newInstance();
		docbuilderfactory.setNamespaceAware(true);
		DocumentBuilder docbbuilder = docbuilderfactory.newDocumentBuilder();
		Document document = docbbuilder.parse(inputstream);
		return createMap(document.getDocumentElement());
	}

	/* This method parses XML data and puts data into a HashMap object */
	protected Map<String, String> createMap(Node node) {
		Map<String, String> map = new HashMap<String, String>();
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.hasAttributes()) {
				for (int j = 0; j < currentNode.getAttributes().getLength(); j++) {
					Node item = currentNode.getAttributes().item(i);
					map.put(item.getNodeName(), item.getTextContent());
				}
			}
			if (node.getFirstChild() != null
					&& node.getFirstChild().getNodeType() == Node.ELEMENT_NODE) {
				map.putAll(createMap(currentNode));
			} else if (node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
				map.put(node.getLocalName(), node.getTextContent());
			}
		}
		return map;
	}
        
    public static FuzeResponse unMarshallResponse( JAXBContext ctx, String xmlString ) throws JAXBException {
        //convert XML String to Object (FuzeResponse)
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        FuzeResponse fuzeResponse = (FuzeResponse) unmarshaller.unmarshal( new StreamSource( new StringReader( xmlString ) ) );
        return fuzeResponse;
    }

}