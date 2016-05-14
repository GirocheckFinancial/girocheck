/*
 ** File: BoardingManagmentTab.java
 **
 ** Date Created: February 2014
 **
 ** Copyright @ 2004-2014 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.vtsuite.vtams.client.gui.window.tab;

import com.google.gwt.user.client.Window;
import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.classes.Settings;
import com.smartbt.vtsuite.vtams.client.classes.i18n.I18N;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseBoardingEditor;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseDatasource;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTab;
import com.smartbt.vtsuite.vtams.client.gui.base.BaseTreeNode;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.AgrupationDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.MerchantDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.ReportDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TerminalDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.FormBoardingListenerImp;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.boarding.AgrupationEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.boarding.MerchantEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.editor.boarding.TerminalEditor;
import com.smartbt.vtsuite.vtams.client.gui.window.search.SearchBoardingManagmentWindow;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartbt.vtsuite.vtcommon.Constants;
import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtcommon.nomenclators.NomUserPrivileges;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * The BoardingManagment Tab
 *
 * @author Ariel Saavedra
 */
public class BoardingManagmentTab extends BaseTab {

    private SearchBoardingManagmentWindow searchBoardingManagmentWindow;

    /**
     * Constructor
     *
     */
    public BoardingManagmentTab() {
        super( I18N.GET.TAB_BOARDING_MANAGMENT() );

        mainVLayout.removeMember( filterLayout );
        mainVLayout.removeMember( listLayout );

        searchBoardingManagmentWindow = new SearchBoardingManagmentWindow() {

            @Override
            public void clickHandlerAgrupationNode( int id, final int idParent ) {
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_AGRUPATION)) {
                    SelectAgrupationExcecuted( id, idParent );
//                }
            }

            @Override
            public void clickHandlerMerchantNode( int id, int idParent ) {
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_MERCHANT)) {
                    SelectMerchantExcecuted( id, idParent );
//                }
            }

            @Override
            public void clickHandlerTerminalNode( int id, int idParent ) {
//                if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_TERMINAL)) {
                    SelectTerminalExcecuted( id, idParent );
//                }
            }

            @Override
            public void clickHandlerRemoveButton() {
                SC.confirm( I18N.GET.MESSAGE_CONFIRM_DELETE(), new BooleanCallback() {
                    public void execute( Boolean value ) {
                        if ( value == Boolean.TRUE ) {
                            DeleteActionExecuted();
                        }
                    }
                } );

            }

        };

        mainVLayout.addMember( searchBoardingManagmentWindow );
    }

    public void openEntityEditor( int id, int idParent, EntityType entityType ) {
        openEntityEditor( id, idParent, entityType, null );
    }

    public void openEntityEditor( int id, int idParent, EntityType entityType, Record record ) {
        Utils.debug( "*************:: openEntityEditor :: entityType " + entityType );

        searchBoardingManagmentWindow.getDetailLayout().removeMembers(
                searchBoardingManagmentWindow.getDetailLayout().getMembers() );
        searchBoardingManagmentWindow.getManagementLayout().removeMembers(
                searchBoardingManagmentWindow.getManagementLayout().getMembers() );

        BaseBoardingEditor editor = null;
        switch ( entityType ) {
            case AGRUPATION:
                editor = getAgrupationEditor( id, idParent );
                break;
            case MERCHANT:
                editor = getMerchantEditor( id, idParent );
                break;
            case TERMINAL:
                editor = getTerminalEditor( id, idParent );
                break;
        }
        if ( editor != null ) {
            Utils.debug( "---------------------:: openEntityEditor :: record = " + ( record == null ? " NULL " : " NOT NULL" ) );

            Utils.debug( "---:: editor.getEntityTypeEditor() = " + editor.getEntityTypeEditor() );

            if ( record != null ) {
                Utils.debug( "before loadRecord" );
                editor.loadRecord( record );
                Utils.debug( "AFTER LOAD RECORD" );
            } else {
                Utils.debug( "Are differents" );
            }
            Utils.debug( "before addMember" );
            searchBoardingManagmentWindow.getManagementLayout().addMember( editor );
            Utils.debug( "after addMember" );
        } else {
            Utils.debug( "---:: openEntityEditor :: editor == null" );
        }
    }

    public AgrupationEditor getAgrupationEditor( final int id, final int idParent ) {
        Utils.debug( "***---:: getAgrupationEditor " );
        AgrupationEditor agrupationEditor = new AgrupationEditor( id, idParent );
        Utils.debug( "***---:: after constructor " );
        agrupationEditor.addListener( getFormBoardingListener() );
        return agrupationEditor;
    }

    public MerchantEditor getMerchantEditor( final int id, final int idParent ) {
        final MerchantEditor merchantEditor = new MerchantEditor( id, idParent );
        merchantEditor.addListener( getFormBoardingListener() );
        return merchantEditor;
    }

    public TerminalEditor getTerminalEditor( final int id, final int idParent ) {
        Utils.debug( "openEntityEditor :: getTerminalEditor idParent = " + idParent );
        final TerminalEditor terminalEditor = new TerminalEditor( id, idParent );
        Utils.debug( "openEntityEditor :: getTerminalEditor terminalEditor " );
        terminalEditor.addListener( getFormBoardingListener() );
        return terminalEditor;
    }

    public FormBoardingListenerImp getFormBoardingListener() {
        FormBoardingListenerImp boardingListener = new FormBoardingListenerImp() {

            @Override
            public void addTerminalActionExecuted( int id, int idParent ) {
                openEntityEditor( id, idParent, EntityType.TERMINAL );
            }

            @Override
            public void addMerchantActionExecuted( int id, int idParent ) {
                openEntityEditor( id, idParent, EntityType.MERCHANT );
            }

            @Override
            public void acceptActionExecuted( Record record, int idParent, EntityType entityType ) {
                AcceptActionExecuted( record, idParent, entityType );
            }
            
            @Override
            public void reportActionExecuted( Record record, int idParent, EntityType entityType) {
                Utils.debug( "****reportActionExecuted 1 ::");
                ReportActionExecuted( record, idParent, entityType );
            }

        };
        return boardingListener;
    }

    public void AcceptActionExecuted( final Record record, final int idParent, final EntityType entityType ) {
        Utils.debug( "****acceptActionExecuted :: entityType -> " + entityType );
        Utils.debug( "****acceptActionExecuted :: id -> " + record.getAttribute( "id" ) );

        final int originalId = record.getAttributeAsInt("id" );
        boolean validate = record.getAttributeAsBoolean( "validate" );
        Utils.debug( "acceptActionExecuted :: validate -> " + validate );
        if ( !validate ) {
            return;
        }

        switch ( entityType ) {
            case AGRUPATION:

                AgrupationDS agrupationDS = new AgrupationDS();

                agrupationDS.addData( record, new DSCallback() {

                    public void execute( DSResponse response, Object rawData, DSRequest request ) {
                        Utils.debug( "acceptActionExecuted :: 1" );
                        if ( response.getStatus() == Constants.CODE_SUCCESS ) {

                            Record record = response.getData()[0];
                            int id = record.getAttributeAsInt( "id" );
                            Utils.debug( "acceptActionExecuted :: 4    id = " + id );
                            openEntityEditor( id, idParent, entityType, record );

                            if ( originalId == 0 /*|| searchBoardingManagmentWindow.getSearchTreeGrid().getRecords().length == 0 */) {
                                searchBoardingManagmentWindow.SearchActionExecuted();
                            } else {
                                String selectedName = searchBoardingManagmentWindow.getSelectedNode().getAttribute( "name" );
                                String selectedName2 = searchBoardingManagmentWindow.getSelectedNode().getName();
                                String recordName = record.getAttribute( "name" );

                                if ( !selectedName2.equals( recordName ) ) {
                                    searchBoardingManagmentWindow.SearchActionExecuted();
                                }
                            }
                        }
                    }
                } );
                break;
            case MERCHANT:

                MerchantDS merchantDS = new MerchantDS();

                merchantDS.addData( record, new DSCallback() {

                    public void execute( DSResponse response, Object rawData, DSRequest request ) {
                        Utils.debug( "acceptActionExecuted :: 1" );
                        if ( response.getStatus() == Constants.CODE_SUCCESS ) {

                            Record record = response.getData()[0];
                            int id = record.getAttributeAsInt( "id" );
                            openEntityEditor( id, idParent, entityType, record );

                            if ( id == 0 ) {
                                searchBoardingManagmentWindow.SearchActionExecuted();
                            } else {
                                String selectedName = searchBoardingManagmentWindow.getSelectedNode().getName();
                                String recordName = record.getAttribute( "legalName" );

                                if ( !selectedName.endsWith( recordName ) ) {
                                    searchBoardingManagmentWindow.SearchActionExecuted();
                                }
                            }

                            Utils.debug( "acceptActionExecuted :: 3" );
                        } else {
                            Utils.debug( "acceptActionExecuted :: comeBack -> failes" );
                        }
                    }
                } );
                break;
            case TERMINAL:

                TerminalDS terminalDS = new TerminalDS();

                terminalDS.addData( record, new DSCallback() {

                    public void execute( DSResponse response, Object rawData, DSRequest request ) {
                        Utils.debug( "acceptActionExecuted :: 1" );
                        if ( response.getStatus() == Constants.CODE_SUCCESS ) {

                            Record record = response.getData()[0];
                            int id = record.getAttributeAsInt( "id" );
                            openEntityEditor( id, idParent, entityType, record );

                            if ( id == 0 ) {
                                searchBoardingManagmentWindow.SearchActionExecuted();
                            } else {
                                String selectedName = searchBoardingManagmentWindow.getSelectedNode().getName();
                                String recordName = record.getAttribute( "serialNumber" );

                                if ( !selectedName.endsWith( recordName ) ) {
                                    searchBoardingManagmentWindow.SearchActionExecuted();
                                }
                            }

                            Utils.debug( "acceptActionExecuted :: 3" );
                        } else {
                            Utils.debug( "acceptActionExecuted :: comeBack -> failes" );
                        }
                    }
                } );
        }
    }
    
    
    public void ReportActionExecuted( final Record record, final int idParent, final EntityType entityType ) {
        Utils.debug( "****ReportActionExecuted :: entityType -> " + entityType );
        Utils.debug( "****ReportActionExecuted :: id -> " + record.getAttribute( "id" ) );
        Utils.debug( "****ReportActionExecuted :: idParent -> " + idParent );

        final int entityId = record.getAttributeAsInt("id" );
        
        report(entityId, entityType);
//        
    }

    private void DeleteActionExecuted() {
        Utils.debug( "DeleteActionExecuted :: 1" );
        BaseTreeNode selectedNode = searchBoardingManagmentWindow.getSelectedNode();
        Utils.debug( "DeleteActionExecuted :: 2   selectedNode = " + ( selectedNode == null ? " NULL " : " NOT NULL" ) );

        int id = selectedNode.getAttributeAsInt( "id" );

        EntityType entityType = selectedNode.getEntityType();

        BaseDatasource ds = new BaseDatasource();

        Criteria criteria = new Criteria();
        criteria.addCriteria( "id", id );

        switch ( entityType ) {
            case AGRUPATION:
                ds.setFetchDataURL( Properties.DELETE_AGRUPATION_WS );
                ds.fetchData( criteria, new DSCallback() {

                    @Override
                    public void execute( DSResponse response, Object rawData, DSRequest request ) {
                        searchBoardingManagmentWindow.getSearchTreeGrid().removeSelectedData();

                        if ( searchBoardingManagmentWindow.getSearchTreeGrid().getRecords().length > 0 ) {
                            int id = searchBoardingManagmentWindow.getSearchTreeGrid().getRecords()[0].getAttributeAsInt( "id" );
                            Utils.debug( "DeleteActionExecuted :: calling to ... selectRecord 7.5" );
                            searchBoardingManagmentWindow.getSearchTreeGrid().selectRecord( 0 );
                            Utils.debug( "DeleteActionExecuted :: calling to ... SelectAgrupationExcecuted 8" );
                            SelectAgrupationExcecuted( id, 0 );
                        } else {
                            searchBoardingManagmentWindow.getDetailLayout().removeMembers(
                                    searchBoardingManagmentWindow.getDetailLayout().getMembers() );
                            searchBoardingManagmentWindow.getManagementLayout().removeMembers(
                                    searchBoardingManagmentWindow.getManagementLayout().getMembers() );
                        }
                    }
                } );
                break;
            case MERCHANT:
                ds.setFetchDataURL( Properties.DELETE_MERCHANT_WS );

                final int idParent = selectedNode.getParentNode().getAttributeAsInt( "id" );

                ds.fetchData( criteria, new DSCallback() {
                    @Override
                    public void execute( DSResponse response, Object rawData, DSRequest request ) {
                        searchBoardingManagmentWindow.getSearchTreeGrid().removeSelectedData();
                        SelectAgrupationExcecuted( idParent, 0 );
                    }
                } );
                break;
            case TERMINAL:
                ds.setFetchDataURL( Properties.DELETE_TERMINAL_WS );

                ds.fetchData( criteria, new DSCallback() {
                    @Override
                    public void execute( DSResponse response, Object rawData, DSRequest request ) {
                        searchBoardingManagmentWindow.getSearchTreeGrid().removeSelectedData();

                        searchBoardingManagmentWindow.getDetailLayout().removeMembers(
                                searchBoardingManagmentWindow.getDetailLayout().getMembers() );
                        searchBoardingManagmentWindow.getManagementLayout().removeMembers(
                                searchBoardingManagmentWindow.getManagementLayout().getMembers() );
                    }
                } );
        }
    }

    private void SelectAgrupationExcecuted( final int id, final int idParent ) {
        Utils.debug( "SelectAgrupationExcecuted :: idParent = " + idParent );
        if ( id != 0 ) {
            AgrupationDS agrupationDS = new AgrupationDS();
            agrupationDS.setFetchDataURL( Properties.GET_AGRUPATION_WS );

            Criteria criteria = new Criteria();
            criteria.addCriteria( "id", id );

            agrupationDS.fetchData( criteria, new DSCallback() {
                public void execute( DSResponse response, Object rawData, DSRequest request ) {
                    if ( response.getStatus() == Constants.CODE_SUCCESS ) {
                        Utils.debug( "SelectAgrupationExcecuted :: 2" );
                        // searchBoardingManagmentWindow.SearchActionExecuted();
                        Utils.debug( "SelectAgrupationExcecuted :: 3" );

                        Record record = response.getData()[0];

                        Utils.debug( "SelectAgrupationExcecuted :: 3" );

                        openEntityEditor( id, idParent, EntityType.AGRUPATION, record );

                        Utils.debug( "SelectAgrupationExcecuted :: 4" );
                        boolean hasTransaction = record.getAttributeAsBoolean( "hasTransaction" );
                        Utils.debug( "SelectAgrupationExcecuted :: 5,   hasTransaction = " + hasTransaction );
                        if ( !hasTransaction ) {
                            if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT)) {
                                searchBoardingManagmentWindow.getRemoveButton().enable();
                            }else{
                                searchBoardingManagmentWindow.getRemoveButton().disable();
                            }
                        } else {
                            searchBoardingManagmentWindow.getRemoveButton().disable();
                            
                        }
                    } else {
                        Utils.debug( "SelectAgrupationExcecuted :: comeBack -> failes" );
                    }
                }
            } );
        } else {
            Utils.debug( "SelectAgrupationExcecuted :: 10" );
            openEntityEditor( id, idParent, EntityType.AGRUPATION );
        }
    }
//en el select tengo k pasarle el id del node, y del selected node cojo el id del padre

    private void SelectMerchantExcecuted( final int id, final int idParent ) {
        Utils.debug( "SelectMerchantExcecuted :: idParent = " + id );
        if ( id != 0 ) {
            MerchantDS merchantDS = new MerchantDS();
            merchantDS.setFetchDataURL( Properties.GET_MERCHANTS_BY_ID_WS );

            Criteria criteria = new Criteria();
            criteria.addCriteria( "id", id );

            merchantDS.fetchData( criteria, new DSCallback() {
                public void execute( DSResponse response, Object rawData, DSRequest request ) {
                    if ( response.getStatus() == Constants.CODE_SUCCESS ) {
                        Utils.debug( "SelectMerchantExcecuted :: 2" );
                        // searchBoardingManagmentWindow.SearchActionExecuted();
                        Utils.debug( "SelectMerchantExcecuted :: 3" );

                        Record record = response.getData()[0];

                        Utils.debug( "SelectMerchantExcecuted :: 3" );

                        openEntityEditor( id, idParent, EntityType.MERCHANT, record );

                        Utils.debug( "SelectMerchantExcecuted :: 4" );
                        boolean hasTransaction = record.getAttributeAsBoolean( "hasTransaction" );
                        Utils.debug( "SelectMerchantExcecuted :: 5,   hasTransaction = " + hasTransaction );
                        if ( !hasTransaction ) {
                            if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT)) {
                                searchBoardingManagmentWindow.getRemoveButton().enable();
                            }else{
                                searchBoardingManagmentWindow.getRemoveButton().disable();
                            }
                        } else {
                            searchBoardingManagmentWindow.getRemoveButton().disable();
                        }
                    } else {
                        Utils.debug( "SelectMerchantExcecuted :: comeBack -> failes" );
                    }
                }
            } );
        }
    }

    private void SelectTerminalExcecuted( final int id, final int idParent ) {
        Utils.debug( "SelectTerminalExcecuted :: idParent = " + id );
        if ( id != 0 ) {
            TerminalDS merchantDS = new TerminalDS();
            merchantDS.setFetchDataURL( Properties.GET_TERMINAL_BY_ID_WS );

            Criteria criteria = new Criteria();
            criteria.addCriteria( "id", id );

            merchantDS.fetchData( criteria, new DSCallback() {
                public void execute( DSResponse response, Object rawData, DSRequest request ) {
                    if ( response.getStatus() == Constants.CODE_SUCCESS ) {
                        Utils.debug( "SelectTerminalExcecuted :: 2" );
                        // searchBoardingManagmentWindow.SearchActionExecuted();
                        Utils.debug( "SelectTerminalExcecuted :: 3" );

                        Record record = response.getData()[0];

                        Utils.debug( "SelectTerminalExcecuted :: 3" );

                        openEntityEditor( id, idParent, EntityType.TERMINAL, record );

                        Utils.debug( "SelectTerminalExcecuted :: 4" );
                        boolean hasTransaction = record.getAttributeAsBoolean( "hasTransaction" );
                        Utils.debug( "SelectTerminalExcecuted :: 5,   hasTransaction = " + hasTransaction );
                        if ( !hasTransaction ) {
                            if (Settings.INSTANCE.hasPrivilege(NomUserPrivileges.ALLOW_BOARDING_MANAGEMENT_REMOVE)) {
                                searchBoardingManagmentWindow.getRemoveButton().enable();
                            }else{
                                searchBoardingManagmentWindow.getRemoveButton().disable();
                            }
                        } else {
                            searchBoardingManagmentWindow.getRemoveButton().disable();
                        }
                    } else {
                        Utils.debug( "SelectTerminalExcecuted :: comeBack -> failes" );
                    }
                }
            } );
        }
    }
    
     public void report(int id, EntityType entityType) {
        Utils.debug( "[BoardingManagmentTab] :: report()" );
        Criteria criteria = new Criteria();
        
        criteria.addCriteria("entityId", id);
        criteria.addCriteria("entityType", entityType.toString());       
        Utils.debug( "[BoardingManagmentTab] :: report() 2" );
        ReportDS ds = new ReportDS();
        
        ds.setCustomeFetchDataUrl(Properties.ENTITYREPORTS_WS);
        Utils.debug( "[BoardingManagmentTab] :: report() 3" );
        ds.fetchData( criteria, new DSCallback() {
            
            /**
             * Callback to invoke on completion
             *
             * @param response Response sent by the server in response to a
             * DataSource request.
             * @param rawData data
             * @param request Request sent to the server to initiate a
             * DataSource operation.
             */
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                Utils.debug( "[BoardingManagmentTab] :: report() 4" );
               sendURL(response);
            }
        });
        
    }

    public void sendURL(DSResponse response) {
        Utils.debug( "[BoardingManagmentTab] :: sendURL() with response status: "+ response.getStatus());
        if (response.getStatus() == Constants.CODE_SUCCESS) {
            Record responseMap = response.getData()[0];

            String urlSimple = responseMap.getAttributeAsString("url");
                String url = urlSimple;
                Utils.debug("sendURL "+url);
                Window.open(url, "", "");
        } else {
            SC.warn(I18N.GET.MESSAGE_ERROR_ACTION(), I18N.GET.MESSAGE_ERROR_ACTION());
        }
    }
}
