@ECHO OFF

REM *************************************************************************************
REM                     CLEAN-INSTALL-DEPLOY-X: Direxv6 PROJECT                         *
REM *************************************************************************************
REM *   SET PARAMENTER FIRST:											   				*
REM *************************************************************************************
REM * 	- domainPortNumber: The Domain Admin Console port number 						*
REM * 	- domainName: The name of the domain 				   							*
REM * 	- domainAppPath: The local path to the domain's application folder				*
REM * 	- domainPassPath: The local path to the XPASS.txt file							*
REM *************************************************************************************

SET domainPortNumber=4848
SET domainName=girocheck
SET domainAppPath="C:\glassfish4\glassfish\domains\GiroCheck\applications"
SET domainPassPath="C:\SVN\GiroCheck.WebGateway\trunk\etc\WebServer Command Files\XPASS.txt"


REM *******************************************************************MySQLConnectionPool

SET dbConnPoolName=MySQLConnectionPool	
SET dbConnPoolDSClassname=com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource
SET dbConnPoolType=javax.sql.ConnectionPoolDataSource
SET dbConnPoolDBPort=13306
SET dbConnPoolDBServer=miadev.warp68.net	
SET dbConnPoolDBName=direxv6
SET dbConnPoolDBPass=direxdb
SET dbConnPoolDBUser=direx
SET dbResourceName=jdbc/direxv6a

SET dbQueueConnFactType=javax.jms.QueueConnectionFactory
SET dbQueueConnFactName=jms/DxQueueConnectionFactory

SET dbDestQueueType=javax.jms.Queue
SET dbDestQueueCoreReply=jms/DirexQueueCoreOut
SET dbDestQueueCoreRequest=jms/DirexQueueCoreIn
SET dbDestQueueHostReply=jms/DirexQueueHostOut
SET dbDestQueueHostRequest=jms/DirexQueueHostIn


SET HIBERNATE_CON_PASSWORD=direxdb
SET HIBERNATE_CON_URL='jdbc:mysql://miadev.warp68.net:13306/direxv6a'
SET HIBERNATE_CON_USERNAME=direx
SET HIBERNATE_SHOW_SQL=true



@ECHO ******************************************************************
@ECHO *                                                                *
@ECHO *   1. Listing domains                                           *
@ECHO *                                                         [1/10] * 
@ECHO ******************************************************************
@ECHO. 

CALL ASADMIN list-domains

@ECHO. 
@ECHO ---------------------------------------------------------
@ECHO     Please stop the domain that is currently running!           
@ECHO ---------------------------------------------------------
@ECHO OFF
SET /P d=Enter the name of the domain to stop:

@ECHO. 
@ECHO ******************************************************************
@ECHO *                                                                *
@ECHO *   2. Stopping the domain                                       *
@ECHO *                                                         [2/10] * 
@ECHO ******************************************************************
@ECHO.  

CALL ASADMIN stop-domain %d%

@ECHO. 
@ECHO *****************************************************************
@ECHO *                                                               *
@ECHO *   3. Create the domain                                        *
@ECHO *                                                        [3/10] * 
@ECHO *****************************************************************
@ECHO. 

CALL ASADMIN create-domain --user admin --adminport %domainPortNumber% --savemasterpassword=true %domainName%

@ECHO. 
@ECHO *****************************************************************
@ECHO *                                                               *
@ECHO *   4. Restart the domain                                       *
@ECHO *                                                        [4/10] * 
@ECHO *****************************************************************
@ECHO.    
   
CALL ASADMIN restart-domain %domainName%

@ECHO.  
@ECHO ****************************************************************
@ECHO *								                                 *
@ECHO *   5. Creating the jdbc connection pool (MySQLConnectionPool) *  
@ECHO *                                                       [5/10] * 
@ECHO ****************************************************************
@ECHO. 

CALL ASADMIN --port %domainPortNumber% --user admin create-jdbc-connection-pool --datasourceclassname %dbConnPoolDSClassname% --restype %dbConnPoolType% --ping true --property portNumber=%dbConnPoolDBPort%:password=%dbConnPoolDBPass%:user=%dbConnPoolDBUser%:serverName=%dbConnPoolDBServer%:databaseName=%dbConnPoolDBName% %dbConnPoolName%

@ECHO.  
@ECHO ****************************************************************
@ECHO *								                                 *
@ECHO *   6. Creating the jdbc resource (MySQLConnectionPool)        *  
@ECHO *                                                       [6/10] * 
@ECHO ****************************************************************
@ECHO. 

CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-jdbc-resource --connectionpoolid %dbConnPoolName% %dbResourceName%

@ECHO.  
@ECHO *****************************************************************
@ECHO *								                                  *
@ECHO *   7. Creating the queue connection factory                    *  
@ECHO *                                                        [7/10] * 
@ECHO *****************************************************************
@ECHO. 

CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-jms-resource --restype %dbQueueConnFactType% %dbQueueConnFactName%

@ECHO.  
@ECHO *****************************************************************
@ECHO *								                                  *
@ECHO *   8. Creating destinations queues                             *  
@ECHO *                                                        [8/10] * 
@ECHO *****************************************************************
@ECHO. 

CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-jms-resource --restype %dbDestQueueType% %dbDestQueueCoreReply%
CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-jms-resource --restype %dbDestQueueType% %dbDestQueueCoreRequest%
CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-jms-resource --restype %dbDestQueueType% %dbDestQueueHostReply%
CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-jms-resource --restype %dbDestQueueType% %dbDestQueueHostRequest%

@ECHO.  
@ECHO *********************************************************************
@ECHO *								                                      *
@ECHO *   9. Creating server properties                                   *  
@ECHO *                                                            [9/10] * 
@ECHO *********************************************************************
@ECHO. 

CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-system-properties   HIBERNATE_CON_PASSWORD=%HIBERNATE_CON_PASSWORD%
CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-system-properties   HIBERNATE_CON_URL=%HIBERNATE_CON_URL%
CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-system-properties   HIBERNATE_CON_USERNAME=%HIBERNATE_CON_USERNAME%
CALL ASADMIN --port %domainPortNumber% --user admin --passwordfile %domainPassPath% create-system-properties   HIBERNATE_SHOW_SQL=%HIBERNATE_SHOW_SQL%

@ECHO. 
@ECHO ********************************************************************
@ECHO *								                                     *
@ECHO *   10. Restarting the domain                                      *
@ECHO *                                                          [10/10] * 
@ECHO ********************************************************************
@ECHO.  

CALL ASADMIN restart-domain %domainName%

CALL ASADMIN set configs.config.server-config.cdi-service.enable-implicit-cdi=false

@ECHO.  
@ECHO *********************************************************************
@ECHO *                                                                   *
@ECHO *                                                         Finished! *
@ECHO *                                                                   *
@ECHO *********************************************************************
@ECHO. 

PAUSE
