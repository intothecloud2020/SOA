/**
 * This class contains actions that can be taken against
 * AuditQuery requests.
 *
 * @author Jaganmohan Reddy
 * @version 1.0
 * @Copyright (c) 2003-04 NeuStar, Inc. All rights reserved. The source code
 * provided herein is the exclusive property of NeuStar, Inc. and is considered
 * to be confidential and proprietary to NeuStar.
 * 
 * @see		com.nightfire.spi.neustar_soa.utils.SOAConstants;
 * @see		com.nightfire.spi.neustar_soa.rules.Context;
 * @see		com.nightfire.framework.message.*;
 * @see		com.nightfire.framework.message.generator.xml.XMLMessageGenerator;
 */

/**
	Revision History
	---------------------
	Rev#		Modified By 		Date			Reason
	-----       -----------     	----------		--------------------------
	1			Jaganmohan Reddy	11/23/2004		Created.
												
*/

package com.nightfire.spi.neustar_soa.rules.actions;

import com.nightfire.spi.neustar_soa.utils.SOAConstants;


public class AuditQueryRequest extends AuditAction {
	
	/**
	 * This variable contains the base path for AuditQuery request.
	 */
	public static final String BASE_PATH = SOAConstants.REQUEST_BODY_PATH+"."+
										  SOAConstants.AUDIT_QUERY_REQUEST;

	/**
	 * This variable contains the location of the AuditId location in the 
	 * AuditCancel Request.
	 */
	public static final String AUDITID_LOCATION = BASE_PATH+"."+
											  SOAConstants.AUDITID_NODE;

	/**
	 * This variable contains the location of the RegionId in the 
	 * AuditCancel request.
	 */
	public static final String REGION_LOCATION = BASE_PATH+"."+
												"."+SOAConstants.REGIONID_NODE;
                                            
	/**
	 * This variable contains the location of the AuditName location in the 
	 * AuditCancel Request.
	 */
	public static final String AUDITNAME_LOCATION = BASE_PATH+"."+
												SOAConstants.AUDITNAME_NODE;
											
	/**
	 * This variable contains the location of the SPID location in the 
	 * AuditCancel Request.
	 */
	public static final String AUDIT_SPID_LOCATION = BASE_PATH+"."+
													SOAConstants.AUDIT_SPID_NODE;

	/**
     * Constructor for AuditQuery Request which will 
     * inturn call the super class constructor.
     *
     */
	public AuditQueryRequest() {

		super( 	SOAConstants.AUDIT_QUERY_REQUEST,
				REGION_LOCATION,
				AUDITID_LOCATION,
				AUDITNAME_LOCATION ,
				AUDIT_SPID_LOCATION );

	}
	
}