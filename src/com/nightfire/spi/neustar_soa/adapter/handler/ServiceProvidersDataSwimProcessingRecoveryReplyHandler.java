/**
 * This is the Reply Handler, Once the SwimProcessingResultRecoveryReply for the 
 * SP data received by the NPAC COM server then it will initiate this reply handler.
 *  
 * @author Sreedhar K
 * @version 3.3
 * @Copyright (c) 2003-04 NeuStar, Inc. All rights reserved. The source code
 * provided herein is the exclusive property of NeuStar, Inc. and is considered
 * to be confidential and proprietary to NeuStar.
 * 
 * @see com.nightfire.framework.message.MessageException;
 * @see com.nightfire.framework.message.parser.xml.XMLMessageParser;
 * @see com.nightfire.framework.util.Debug;
 * @see com.nightfire.spi.neustar_soa.adapter.NPACAdapter;
 * @see com.nightfire.spi.neustar_soa.adapter.NPACConstants;
 * @see com.nightfire.spi.neustar_soa.adapter.Session;
 * @see com.nightfire.spi.neustar_soa.utils.TimeZoneUtil;
 * @see java.util.List;
 */
 
/**
 * Revision History
 * ---------------------
 * Rev#        Modified By     Date                Reason
 * -----       -----------     ----------          --------------------------
 *  1           Sreedhar         08/18/2005         Created.
 *  2           Sreedhar         09/02/2005         Incorporated review comments.
 *  3           Sreedhar         10/10/2005         Modified to implement 
 *  												swim_more_data functionality.
 *  
 */

package com.nightfire.spi.neustar_soa.adapter.handler;

import java.util.Date;
import java.util.List;

import com.nightfire.framework.message.MessageException;
import com.nightfire.framework.message.parser.xml.XMLMessageParser;
import com.nightfire.framework.util.Debug;
import com.nightfire.spi.neustar_soa.adapter.NPACAdapter;
import com.nightfire.spi.neustar_soa.adapter.NPACConstants;
import com.nightfire.spi.neustar_soa.adapter.Session;
import com.nightfire.spi.neustar_soa.utils.TimeZoneUtil;

public class ServiceProvidersDataSwimProcessingRecoveryReplyHandler extends
        NotificationHandler {
    /*
     * The status of the reply retrieved from the notification.
     */
    private String status;
    
    /*
     * time we received from NPAC Swim Reply
     */
    private Date npacStopTime;
    
    /*
     * Spid involved in the request    
     */ 
    private String spid;
    
    /*
     *  Revcovery complete time 
     */
    private Date recoveryCompleteTime;
    
    /*
     *  LasNotification time we got from NPAC
     */
    private Date lastNotificationTime;
    
    /*
     * action Id we populated in the reply.
     */
    private String swimActionID;
    
    /*
     * The region/association for which we are recovering.
     */
    private int region;
    
    /*
     * Recovery interval to send the recovery again.
     */
    private long recoveryInterval;
    
    /*
     * List of spids associated with current region
     */
    private List spids;
    
    /*
     * This is the index of the customer SPID in the spids list for
     * which we are currently performing recovery.
     */  
    private int spidIndex;

    /**
     * This is constructor 
     * @param adapter NpacAdapater instance used in the class.
     * @param session session involved. 
     * @param spids  avilable spids for the region.
     * @param spidIndex index of the spid.
     * @param recoveryCompleteTime time to which recovery to be performed.
     * @param lastNotificationTime time we received last notification.
     * @param swimActionID swim action id used .
     * @param region region involved in the request.
     * @param recoveryInterval retray interval.
     */


    public ServiceProvidersDataSwimProcessingRecoveryReplyHandler
                                                (NPACAdapter adapter, 
                                                 Session session, 
                                                 List spids, 
                                                 int spidIndex,
                                                 Date recoveryCompleteTime,
                                                 Date lastNotificationTime,
                                                 String swimActionID, 
                                                 int region,
                                                 long recoveryInterval)

    {
        super(adapter, session);
       
        this.recoveryCompleteTime = recoveryCompleteTime;
        this.lastNotificationTime = lastNotificationTime;
        this.swimActionID = swimActionID;
        this.region = region;
        this.recoveryInterval = recoveryInterval;
        this.spids= spids;
        this.spidIndex = spidIndex;

    }

    /**
     * This is called when a notification is received to set the
     * notification that this handler will work with when its run()
     * method is called. It is assumed that this will get called before
     * the run method.
     * @param parsedNotification XMLMessageParser object received 
     *                            from notificaton.
     */
    
    public int receiveNotification(XMLMessageParser parsedNotification) {
        
        int ack = super.receiveNotification(parsedNotification);

		npacStopTime = null;

        if (ack == NPACConstants.ACK_RESPONSE) {

            try {

                // get the status of the response
                status = notification
                        .getTextValue(NPACConstants.
                                SWIM_PROCESSING_RECOVERY_RESULTS_REPLY_STATUS);
                if(parsedNotification.exists(NPACConstants.
                        SWIM_PROCESSING_RECOVERY_RESULTS_REPLY_STOPTIME))
                {
                String stopTime = notification.getTextValue(NPACConstants.
                        SWIM_PROCESSING_RECOVERY_RESULTS_REPLY_STOPTIME);
                npacStopTime  = TimeZoneUtil.parse(NPACConstants.
                        UTC,NPACConstants.UTC_TIME_FORMAT,stopTime);
                }

            } catch (MessageException mex) {

                Debug
                        .error("Could not get data from new session reply: "
                                + mex);

                ack = NPACConstants.NACK_RESPONSE;

            }

        }

        return ack;

    }
    
    /**
     * This handles the case where a GatewayError has been sent in response
     * to our SwimRequest for service provider data. This simply retries
     *  the Swim request for service prov data.
     *
     * @param error this is the parsed GatewayError.
     */

    protected void handleError(XMLMessageParser error) {

        // create a retry task to retry the recovery request

        adapter.retryServProvSwimRequest(session,
                                         region,
                                         spids,
                                         spidIndex,
                                         recoveryCompleteTime,
                                         lastNotificationTime,
                                         swimActionID, 
                                         spid, 
                                         recoveryInterval);
    }
    
    /**
     * This is called when the timer for this handler has expired before
     * a reply was received.
     */

    public void timeout() {
        adapter.sendServiceProvDataSwimRequest(session, 
                                               region,
                                               spids, 
                                               spidIndex,
                                               recoveryCompleteTime, 
                                               lastNotificationTime, 
                                               swimActionID, 
                                               spid,
                                               recoveryInterval);
    }
    
    /**
     * This gives off the batched reply to Driver chanin for processing 
     *  for the swim
     *  request was successful. If not successful, then this retries
     * the original Swim request for service prov data.
     */

    public void run() {
        if (Debug.isLevelEnabled(Debug.IO_STATUS)) {

            Debug.log(Debug.IO_STATUS,
                    " Swim Processing Service Provider Data status: [" + status
                            + "]");

        }

        // if successful, then update the session
        if (status !=null &&status.equals(NPACConstants.SUCCESS_STATUS)) {
            
            Debug.log(Debug.SYSTEM_CONFIG,"in side run method of service " +
                    "provider swim request::"+npacStopTime);

           // adapter.process(notification);
            if(npacStopTime!= null  )
            {
                
                Date recoveryStartTime = npacStopTime;
               
                adapter.sendDownloadRecoveryRequestServiceProvidersData
                                    (session,
                                     region,
                                     spids,
                                     spidIndex,
                                     null,
                                     recoveryCompleteTime,
                                     recoveryStartTime,
                                     recoveryInterval,
                                     true);
                
            }else
            {
                Debug.log(Debug.SYSTEM_CONFIG,"in side run method of service " +
                        "provider swim request else block ::"
                        +recoveryCompleteTime);
                
                adapter.sendDownloadRecoveryRequestServiceProvidersData
                                                        (session,
                                                         region,
                                                         spids,
                                                         ++spidIndex,
                                                         null,
                                                         recoveryCompleteTime,
                                                         null,
                                                         recoveryInterval,
                                                         false);

                
            }

        } else {

			if(npacStopTime!= null  )
            {
                
                Date recoveryStartTime = npacStopTime;
               
                adapter.sendDownloadRecoveryRequestServiceProvidersData
                                    (session,
                                     region,
                                     spids,
                                     spidIndex,
                                     null,
                                     recoveryCompleteTime,
                                     recoveryStartTime,
                                     recoveryInterval,
                                     true);
			}
			else {

				adapter.retryServProvSwimRequest(session, 
												 region,
												 spids,
												 spidIndex,
												 recoveryCompleteTime, 
												 lastNotificationTime,
												 swimActionID,
												 spid,
												 recoveryInterval);
			}

        }

    }

}
