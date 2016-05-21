
package com.smartbt.girocheck.servercommon.utils;

/**
 *
 * @author Carlos Romero
 */


public class CustomeLogger {
    
    
    /**
     * Prints the string to the output console
     *
     * @param value
     */
    public static void Output(String value) {
        Output(OutputStates.Debug, null, value);
    }
    
    /**
     * Prints the string to the output console
     *
     * @param state
     * @param msg
     * @param value
     */
    public static void Output(OutputStates state,String msg, String value) {

        // log rbs transactions to debug table when debug and tracing is enabled
        String flagDebug = System.getProperty("debug.mode");
        String flagTrace = System.getProperty("debug.trace");
        boolean traceEnabled = true; //= (flagDebug != null && flagTrace != null && flagDebug.equalsIgnoreCase("true") && flagTrace.isEmpty() == false);

        if (traceEnabled) {
            boolean output = false;
            boolean traceDebug = true;//flagTrace.equalsIgnoreCase("debug");
            boolean traceInfo = true; //flagTrace.equalsIgnoreCase("info");

//            System.out.println("state = " + state.toString());
//            System.out.println("flagDebug = " + flagDebug);
//            System.out.println("flagTrace = " + flagTrace);
//            System.out.println("traceDebug = " + traceDebug);
//            System.out.println("traceInfo = " + traceInfo);
//            System.out.println("traceProduction = " + traceProduction);

            switch (state) {

                case Debug:
                    if (traceDebug) {
                        output = true;
                    }
                    break;

                case Info:
                    if (traceDebug || traceInfo) {
                        output = true;
                    }
                    break;

                case Production:
                    output = true;
                    break;

                default:
                    return;
            }

            if(value == null){
                value = "";
            }
            
            if (output) {
                if (msg == null || msg.isEmpty()) {
                    System.out.println(">> [" + state.toString() + "] -> " + value);
                } else {
                    System.out.println(">> [" + state.toString() + "] " + msg + " -> " + value);
                }
            }
        }
    }
public enum OutputStates {
        Debug,
        Info,
        Production;
    }
    
    
}
