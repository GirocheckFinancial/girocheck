
package com.smartbt.vtsuite.vtams.client.gui.component.datasource;

import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartgwt.client.data.Record;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
public class DataSourceBuilder {

  public static BaseDatasource getRiskDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", 1);
      item1.setAttribute( "name", "Risk 1");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 2);
      item2.setAttribute( "name", "Risk 2");
      
      Record item3 = new Record();
      item3.setAttribute( "id", 3);
      item3.setAttribute( "name", "Risk 3");
      
      return new BaseDatasource(item1,item2,item3);
  }  

  public static BaseDatasource getMerchantTypeDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", 1);
      item1.setAttribute( "name", "Type 1");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 2);
      item2.setAttribute( "name", "Type 2");
      
      Record item3 = new Record();
      item3.setAttribute( "id", 3);
      item3.setAttribute( "name", "Type 3");
      
      return new BaseDatasource(item1,item2,item3);
  }  

  public static BaseDatasource getCommissionTypeDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", 1);  //"D"
      item1.setAttribute( "name", "Direct");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 2); //"I"
      item2.setAttribute( "name", "ISO");
       
      
      return new BaseDatasource(item1,item2);
  }  

  public static BaseDatasource getDistributionChanelDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", 1);
      item1.setAttribute( "name", "Chanel 1");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 2);
      item2.setAttribute( "name", "Chanel 2");
      
      Record item3 = new Record();
      item3.setAttribute( "id", 3);
      item3.setAttribute( "name", "Chanel 3");
      
      return new BaseDatasource(item1,item2,item3);
  }  

  public static BaseDatasource getApplicationDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", 1);
      item1.setAttribute( "name", "Global");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 2);
      item2.setAttribute( "name", "IStream");
      
      Record item3 = new Record();
      item3.setAttribute( "id", 3);
      item3.setAttribute( "name", "Order Express");
      
      Record item4 = new Record();
      item4.setAttribute( "id", 4);
      item4.setAttribute( "name", "Tecnicard");
      
      return new BaseDatasource(item1,item2,item3,item4);
  }  

  public static BaseDatasource getTransactionTypeDS(){
      Record item0 = new Record();
      item0.setAttribute( "id", 0);
      item0.setAttribute( "name", "All");
     
      Record item1 = new Record();
      item1.setAttribute( "id", 1);
      item1.setAttribute( "name", "New Card Load");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 2);
      item2.setAttribute( "name", "Card Reload");
      
      Record item3 = new Record();
      item3.setAttribute( "id", 18);
      item3.setAttribute( "name", "Balance Inquiry");
      
      Record item4 = new Record();
      item4.setAttribute( "id", 19);
      item4.setAttribute( "name", "Card to Bank");
      
      return new BaseDatasource(item0, item1,item2,item3,item4);
  }  
  
  public static BaseDatasource getOperationDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", "");
      item1.setAttribute( "name", "All");
      
      Record item2 = new Record();
      item2.setAttribute( "id", "01");
      item2.setAttribute( "name", "Check");
      
      Record item3 = new Record();
      item3.setAttribute( "id", "02");
      item3.setAttribute( "name", "Cash");
      
      
      return new BaseDatasource(item1,item2,item3);
  }  
  
  public static BaseDatasource getAmmountTypeDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", 0);
      item1.setAttribute( "name", "Select");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 1);
      item2.setAttribute( "name", "Amount");
      
      Record item3 = new Record();
      item3.setAttribute( "id", 2);
      item3.setAttribute( "name", "Fee Amount");
      
      Record item4 = new Record();
      item4.setAttribute( "id", 3);
      item4.setAttribute( "name", "Payout Amount");
      
      
      return new BaseDatasource(item1,item2,item3,item4);
  }  
  
  public static BaseDatasource getOpTypeDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", 0);
      item1.setAttribute( "name", "Select");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 1);
      item2.setAttribute( "name", " > ");
      
      Record item3 = new Record();
      item3.setAttribute( "id", 2);
      item3.setAttribute( "name", " = ");
      
      Record item4 = new Record();
      item4.setAttribute( "id", 3);
      item4.setAttribute( "name", " < ");
      
      
      return new BaseDatasource(item1,item2,item3,item4);
  }  
  
    public static BaseDatasource getUserActiveDS(){
      Record item1 = new Record();
      item1.setAttribute( "id", 0);
      item1.setAttribute( "name", "Inactive");
      
      Record item2 = new Record();
      item2.setAttribute( "id", 1);
      item2.setAttribute( "name", "Active");     
      
      return new BaseDatasource(item1,item2);
  } 
  
}
