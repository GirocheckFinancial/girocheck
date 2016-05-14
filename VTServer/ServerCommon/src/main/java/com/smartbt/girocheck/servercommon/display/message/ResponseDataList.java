/*
 ** File: ResponseDataList.java
 **
 ** Date Created: October 2013
 **
 ** Copyright @ 2004-2013 Smart Business Technology, Inc.
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Smart Business Technology, Inc.
 **
 */
package com.smartbt.girocheck.servercommon.display.message;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ariel Saavedra
 * @param <T>
 */
@XmlRootElement
public class ResponseDataList<T> extends BaseResponse {

    private Collection<T> data;
    private Integer totalPages;

    /**
     *
     * @return
     */
    public Collection<T> getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(Collection<T> data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     *
     * @param totalPages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
