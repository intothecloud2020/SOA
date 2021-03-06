/*
 * Copyright(c) 2002 NightFire Software, Inc.
 * All rights reserved.
 */

package com.nightfire.webgui.manager.svcmeta;

// jdk imports
import java.util.*;

// third-party imports
import org.w3c.dom.*;

// nightfire imports
import com.nightfire.framework.message.util.xml.DOMWriter;
import com.nightfire.framework.message.util.xml.ParsedXPath;
import com.nightfire.framework.util.Debug;
import com.nightfire.framework.util.FrameworkException;
import com.nightfire.framework.util.StringUtils;
import com.nightfire.webgui.core.meta.*;
import com.nightfire.webgui.core.svcmeta.*;
import com.nightfire.webgui.manager.svcmeta.*;
import com.nightfire.framework.debug.*;
import java.awt.Dimension;



/**
 * 
 */
public class ModifierInfo
{

      /** Fields to display in the summary line for the component */
    private List summaryFields = new ArrayList();
    

 /** Read-only view of summaryFields */
    private List ro_summaryFields;


   /** Action definitions available on this component */
    private List actionDefs = new ArrayList();

    /** Read-only view of actionDefs */
    private List ro_actionDefs;

  
    private ComponentDef compDef;
    private BuildContext buildCtx;
    
    
    private DebugLogger log;
    
    

    /**
     * Constructor
     */
    public ModifierInfo(ComponentDef compDef, BuildContext buildCtx)
    {
        this.compDef = compDef;
        ro_summaryFields       = Collections.unmodifiableList(summaryFields);
        ro_actionDefs          = Collections.unmodifiableList(actionDefs);
        this.buildCtx = buildCtx;
        log = DebugLogger.getLoggerLastApp(getClass() );
     
    }

    /**
     * Returns a list of the fields to display in the summary line for
     * the component.  The items in the list are instances of {@link Field}.
     */
    public List getSummaryFields()
    {
        return ro_summaryFields;
    }

    /**
     * Returns a list of the actions available on components of this type.
     * The items in the list are instances of {@link ActionDef}.
     */
    public List getActionDefs()
    {
        return ro_actionDefs;
    }
    

 

    /**
     * Reads this action definition from a node in an XML document
     *
     * @param ctx      The node to read from
     * @param buildCtx The BuildContext
     *
     * @exception FrameworkException Thrown if the definition cannot be
     *                               loaded.
     */
    public void readFromXML(Node ctx, BuildContext buildCtx)
        throws FrameworkException
    {
  

        List temp = buildCtx.xpaths.summaryFieldsPath.getNodeList(ctx);
        if (temp.size() > 0)
        {
            // summaryFields
            loadFields(buildCtx.xpaths.summaryFieldsPath, ctx, buildCtx, summaryFields);           

        } else {
            summaryFields = compDef.getSummaryFields();
            ro_summaryFields       = Collections.unmodifiableList(summaryFields);
        }
        

       // actionDefs
        Iterator iter = buildCtx.xpaths.allowableActionsPath.getNodeList(ctx)
            .iterator();
        while (iter.hasNext())
        {
            Node n = (Node)iter.next();
            String name = n.getNodeValue();
            ActionDef def = buildCtx.getAction(name);
            if (def == null)
                log.error(
                          "Could not locate action with name [" + name
                          + "], referenced at [" + buildCtx.toXPath(ctx)
                          + "].");
            else
                actionDefs.add(def);
        }

    
    
    }

   /**
     * Loads a set of field definitions
     *
     * @param path     The path to load from
     * @param ctx      The Node path is relative to
     * @param buildCtx The build context
     * @param dest     The list to place loaded fields in
     */
    private void loadFields(ParsedXPath path, Node ctx,
                            BuildContext buildCtx, List dest)
        throws FrameworkException
    {
        Iterator iter = path.getNodeList(ctx).iterator();
        while (iter.hasNext())
        {
            Node n = (Node)iter.next();
            String name = n.getNodeValue();
            MessagePart fld = buildCtx.getMessagePart(name);
            if (fld == null)
                log.error(
                          "Could not locate field with name [" + name
                          + "], referenced at [" + buildCtx.toXPath(ctx)
                          + "].");
            else {
              log.debug("ModifierInfo[" + compDef.getID() +"]: Loading field [" + fld.getID() +"]");
              dest.add(fld);
            }
            
        }
    }
    
    /**
     * Returns a particular action from the actions available on components of this type.
     * @param String id
     * @return ActionDef
     */
    public ActionDef getActionDef( String id )
    {
        Iterator iter = ro_actionDefs.iterator();

        ActionDef action = null;

        while ( iter.hasNext() )
        {
            ActionDef temp = (ActionDef) iter.next() ;
            String actionName = temp.getActionName();
            int idSuffix = actionName.indexOf("#");
            
            if (idSuffix > -1)
                actionName = actionName.substring(0, idSuffix);
            
            if ( actionName.equals( id ) )
            {
                action = temp;
                break;
            }
        }

        if ( action == null )
        {
            log.warn("getActionDef(String): No ActionDef found for id [" + id + "].");
        }

        return action;
    }
}
