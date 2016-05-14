/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smartbt.girocheck.servercommon.utils;

import java.util.Map;

/*
 *  @author Roberto Rodriguez :: <roberto.rodriguez@smartbt.com>
 */
public interface IBuilder {
    public IBuilder build(Map map)  throws Exception;
}
