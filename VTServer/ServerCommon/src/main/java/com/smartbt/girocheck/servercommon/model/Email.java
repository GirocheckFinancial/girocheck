package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Alejo
 */


public class Email implements Serializable{
    
    private int id;
    private String name;
    private String username;
    private String password;
    private String recipients;
    private String title;
    private String body;
    
    private Map<String, String> values;
    
    public Email(){}

    public String getFormatBody(){
         Iterator<String> keys = values.keySet().iterator();
         String b = body;
        while (keys.hasNext()) {
            String keyword = keys.next();
            if(values.get(keyword) != null){
              b = b.replaceAll(keyword, values.get(keyword));   
            }
        }
       return b;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return the recipients
     */
    public String getRecipients() {
        return recipients;
    }

    /**
     * @param recipients the recipients to set
     */
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the values
     */
    public Map<String, String> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(Map<String, String> values) {
        this.values = values;
    }
    
}
