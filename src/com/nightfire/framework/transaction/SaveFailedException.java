/**
 * Copyright (c) 1999 Nightfire Software, Inc. All rights reserved.
 * 
 */

package com.nightfire.framework.transaction;

import com.nightfire.framework.util.*;


/**
 * Base class for all state exceptions.
 */
public class SaveFailedException extends FrameworkException
{
	/**
	 * Create a state exception object with the given message.
	 *
	 * @param  msg  Error message associated with exception.
	 */
	public SaveFailedException (String msg)
	{
		super(msg);
	}


	/**
	 * Create a state exception object with the given exception's message.
	 *
	 * @param  e  Exception object used in creation.
	 */
	public SaveFailedException (Exception e)
	{
		super(e);
	}
}
