
package com.smartbt.vtsuite.vtcommon.nomenclators;

/**
 *
 * @author Alejo
 */

 public enum NomOEUSAStates {

    /*
     * ALABAMA - AL
     */
    AL(1),
    /*
     * ALASKA - AK
     */
    AK(2),
    /*
     * ARIZONA - AZ
     */
    AZ(3),
    /*
     * ARKANSAS - AR
     */
    AR(4),
    /*
     * CALIFORNIA - CA
     */
    CA(5),
    /*
     * COLORADO - CO
     */
    CO(6),
    /*
     * CONNECTICUT - CT
     */
    CT(7),
    /*
     * DELAWARE - DE
     */
    DE(8),
    /*
     * DISTRICT OF COLUMBIA - DC
     */
    DC(9),
    /*
     * FLORIDA - FL
     */
    FL(10),
    /*
     * GEORGIA - GA
     */
    GA(11),
    /*
     * HAWAII - HI
     */
    HI(12),
    /*
     * IDAHO - ID
     */
    ID(13),
    /*
     * ILLINOIS - IL
     */
    IL(14),
    /*
     * INDIANA - IN
     */
    IN(15),
    /*
     * IOWA - IA
     */
    IA(16),
    /*
     * KANSAS - KS
     */
    KS(17),
    /*
     * KENTUCKY - KY
     */
    KY(18),
    /*
     * LOUISIANA - LA
     */
    LA(19),
    /*
     * MAINE - ME
     */
    ME(20),
    /*
     * MARYLAND - MD
     */
    MD(21),
    /*
     * MASSACHUSETTS - MA
     */
    MA(22),
    /*
     * MICHIGAN - MI
     */
    MI(23),
    /*
     * MINNESOTA - MN
     */
    MN(24),
    /*
     * MISSISSIPPI - MS
     */
    MS(25),
    /*
     * MISSOURI - MO
     */
    MO(26),
    /*
     * MONTANA - MT
     */
    MT(27),
    /*
     * NEBRASKA - NE
     */
    NE(28),
    /*
     * NEVADA - NV
     */
    NV(29),
    /*
     * NEW HAMPSHIRE - NH
     */
    NH(30),
    /*
     * NEW JERSEY - NJ
     */
    NJ(31),
    /*
     * NEW MEXICO - NM
     */
    NM(32),
    /*
     * NEW YORK - NY
     */
    NY(33),
    /*
     * NORTH CAROLINA - NC
     */
    NC(34),
    /*
     * NORTH DAKOTA - ND
     */
    ND(35),
    /*
     * OHIO - OH
     */
    OH(36),
    /*
     * OKLAHOMA - OK
     */
    OK(37),
    /*
     * OREGON - OR
     */
    OR(38),
    /*
     * PENNSYLVANIA - PA
     */
    PA(39),
    /*
     * RHODE ISLAND - RI
     */
    RI(40),
    /*
     * SOUTH CAROLINA - SC
     */
    SC(41),
    /*
     * SOUTH DAKOTA - SD
     */
    SD(42),
    /*
     * TENNESSEE - TN
     */
    TN(43),
    /*
     * TEXAS - TX
     */
    TX(44),
    /*
     * UTAH - UT
     */
    UT(45),
    /*
     * VERMONT - VT
     */
    VT(46),
    /*
     * VIRGINIA - VA
     */
    VA(47),
    /*
     * WASHINGTON - WA
     */
    WA(48),
    /*
     * WEST VIRGINIA - WV
     */
    WV(49),
    /*
     * WISCONSIN - WI
     */
    WI(50),
    /*
     * WYOMING - WY
     */
    WY(51);
    private int id;

    private NomOEUSAStates(int id) {
        this.id = id;
    }

    /**
     * Get id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
