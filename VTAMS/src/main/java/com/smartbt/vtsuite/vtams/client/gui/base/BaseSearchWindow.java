/*
 ** File: SearchWindow.java
 **
 ** Date Created: January 2014
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
package com.smartbt.vtsuite.vtams.client.gui.base;

import com.smartbt.vtsuite.vtcommon.enums.EntityType;
import com.smartbt.vtsuite.vtams.client.classes.Properties;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.AgrupationDS;
import com.smartbt.vtsuite.vtams.client.gui.listener.TreeListener;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.MerchantDS;
import com.smartbt.vtsuite.vtams.client.gui.component.datasource.TerminalDS;
import com.smartbt.vtsuite.vtams.client.utils.Utils;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;

/**
 * The Merchant Services Window
 *
 * @author Ariel Saavedra
 */
public abstract class BaseSearchWindow extends BaseEntityDetailWindow {

    /**
     * Constructor
     *
     */
    public BaseSearchWindow() {
        super();

        // Remove Management View   
        if ( vManagementLayout.hasMember( managementWindow ) ) {
            vManagementLayout.removeMember( managementWindow );
        }

        addListener( new TreeListener() {
            /**
             * Method to execute when a Search event is fired.
             *
             */
            @Override
            public void SearchActionExecuted() {
                searchActionExecuted();
            }

            /**
             * Method to execute when a Select event is fired.
             *
             */
            @Override
            public void SelectActionExecuted( final BaseTreeNode node ) {
                selectActionExecuted( node );
                openFolderActionExecuted( node );
            }

            /**
             * Method to execute when an OpenFolder event is fired.
             *
             */
            @Override
            public void OpenFolderActionExecuted( final BaseTreeNode node ) {
                openFolderActionExecuted( node );
                selectActionExecuted( node );
            }
        } );

        searchActionExecuted();
    }

    protected final void searchActionExecuted() {
        Utils.debug( "searchActionExecuted ::" );
        Criteria criteria = new Criteria();
        if ( searchText.getValue() != null ) {
            criteria.addCriteria( "search", searchText.getValueAsString() );
            Utils.debug( "searchText.getValueAsString() ::" + searchText.getValueAsString() );
        }

        BaseTreeNode selectedNode = getSelectedNode();

        if ( selectedNode != null && selectedNode.getEntityType() == EntityType.AGRUPATION && searchText.getValue() != null ) {
            MerchantDS merchantDS = new MerchantDS();

            merchantDS.setFetchDataURL( Properties.GET_SEARCH_MERCHANTS_WS );

            merchantDS.fetchData( criteria, new DSCallback() {
                @Override
                public void execute( DSResponse response, Object rawData, DSRequest request ) {
                    Record[] searchRecords = response.getData();
                    BaseTreeNode copy = new BaseTreeNode();
                    copy = getSelectedNode();
                    searchTreeGrid.removeNodes();
                    searchTreeGrid.addNodes( searchTreeGrid.getRootNode(), EntityType.AGRUPATION, copy.getEntityRecord() );
                    searchTreeGrid.addNodes(copy, EntityType.MERCHANT, searchRecords);
 
                    
                }
            } );
        } else {
            Utils.debug( "searchActionExecuted :: 2" );
            AgrupationDS agrupationDS = new AgrupationDS();

            agrupationDS.setFetchDataURL( Properties.GET_SEARCH_AGRUPATIONS_WS );

            agrupationDS.fetchData( criteria, new DSCallback() {
                @Override
                public void execute( DSResponse response, Object rawData, DSRequest request ) {
                    Record[] searchRecords = response.getData();
                    searchTreeGrid.removeNodes();
                    searchTreeGrid.addNodes( searchTreeGrid.getRootNode(), EntityType.AGRUPATION, searchRecords );
                }
            } );
        }
        selectedNode = null;
    }

    protected abstract void selectActionExecuted( BaseTreeNode node );

    protected void openFolderActionExecuted( final BaseTreeNode node ) {
        Utils.debug( "BaseSearchWindow :: openFolderActionExecuted" );
        // If the node doesn't have children yet   openFolderActionExecuted            
        if ( node.getAttribute( "id" ) != null && searchTreeGrid.getSearchTree().getChildren( node ).length == 0 ) {
            Criteria criteria = new Criteria();

            switch ( node.getEntityType() ) {
                case AGRUPATION: {
                    criteria.addCriteria( "idAgrupation", node.getAttributeAsString( "id" ) );

                    new MerchantDS().fetchData( criteria, new DSCallback() {
                        @Override
                        public void execute( DSResponse response, Object rawData, DSRequest request ) {
                            Record[] searchRecords = response.getData();
                            searchTreeGrid.addNodes( node, EntityType.MERCHANT, searchRecords );
                        }
                    } );
                    break;
                }
                case MERCHANT: {
                    criteria.addCriteria( "idMerchant", node.getAttributeAsString( "id" ) );

                    new TerminalDS().fetchData( criteria, new DSCallback() {
                        @Override
                        public void execute( DSResponse response, Object rawData, DSRequest request ) {
                            Record[] searchRecords = response.getData();
                            searchTreeGrid.addNodes( node, EntityType.TERMINAL, searchRecords );
                        }
                    } );
                    break;
                }
                default: {
                    break;
                }
            }

        }
//        Utils.debug( "BaseSearchWindow :: openFolderActionExecuted --- before -> SelectActionExecuted " );
//        selectActionExecuted( node );
//
//        Utils.debug( "BaseSearchWindow :: openFolderActionExecuted --- after -> SelectActionExecuted " );
    }

    public abstract BaseTreeNode getSelectedNode();
}
