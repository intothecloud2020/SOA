/**
* Copyright (c) 2001-2002 NightFire Software, Inc. All rights reserved.
*/
package com.nightfire.adapter.converter;

import com.nightfire.adapter.converter.meta.*;

import com.nightfire.framework.util.Debug;
import com.nightfire.framework.util.FileUtils;
import com.nightfire.framework.util.FrameworkException;
import com.nightfire.framework.message.parser.xml.XMLMessageParser;

import org.w3c.dom.Document;

import com.nightfire.framework.message.util.xml.CachingXPathAccessor;

/**
* This converter takes XML input and uses meta data "Field"
* objects to write the output in the proper format.
*/
public class XMLToFieldConverter extends XMLToStringConverter{

   /**
   * The input XML for this converter is passed to the rootField to
   * be written to the proper output format.    
   * A field is an object that describes a particular section of output.
   * The field may have sub-fields that describe subsections and fields
   * of the output and where those fields live in the XML input.
   */
   private Field rootField;

   /**
   * Constructs this Converter by reading field definitions from the named file
   * and then using these field definitions to convert the input from XML to
   * the output format.
   *
   * @param metaFileName the path to the meta file which defines the root field
   *                     (and its child fields) which describes the
   *                     output to be generated by this converter. The
   *                     meta file is loaded and cached using the FieldFactory. 
   */
   public XMLToFieldConverter(String metaFileName) throws FrameworkException{

      this( FieldFactory.getMap( metaFileName ) );

   }

   /**
   * Constructs this Converter taking a Field instance that will
   * be used to convert the input from XML to the output format.
   *
   * @param rootField the path to the meta file which defines the root field
   *                  (and its child fields) which describes the
   *                  output to be generated by this converter.
   */
   public XMLToFieldConverter(Field rootField){

      this.rootField = rootField;

   }


   /**
   * This method takes an XML Document and converts it to a String output
   * using the <code>rootField</code> (and its subfields) to create the
   * correct output format.
   * This defines XMLToStringConverter.convertToString().
   *
   * @param input an XML Document object to be converted.
   * @return the String result of the conversion.
   * @throws ConverterException if the input Document can not be accessed
   *                            using an XPathAccessor.
   */
   public String convertToString(Document input) throws ConverterException{

      StringBuffer result = new StringBuffer();

      try{

         // use the root field and its subfields to write the
         // converted output to the result buffer.
         rootField.write(new FieldContext(),
                         new CachingXPathAccessor(input),
                         result);

      }
      catch(FrameworkException fex){

         throw new ConverterException(fex);

      }
      return result.toString();

   }

   /**
   * This takes the name of meta file containing Field definitions
   * and the name of an XML input file to be converted,
   * and uses the loaded Field definitions to convert the XML input.
   */
   public static void main(String[] args){

      String metaFile  = null;
      String inputFile = null;

      String usage = "usage: java "+XMLToFieldConverter.class.getName()+
                     " [-verbose|-verboseAll] <meta file> <input xml file>";

      for(int i = 0; i < args.length; i++){

         if( args[i].equals("-verbose") ){

            Debug.enable(Debug.NORMAL_STATUS);
            Debug.enable(Debug.ALL_ERRORS);
            Debug.enable(Debug.ALL_WARNINGS);
            Debug.enable(Debug.MAPPING_LIFECYCLE);
            Debug.enable(Debug.MAPPING_STATUS);
            Debug.enable(Debug.MAPPING_BASE);                                                

         }
         else if( args[i].equals("-verboseAll") ){

            Debug.enableAll();

         }
         else if(metaFile == null){

            metaFile = args[i];

         }
         else if(inputFile == null){

            inputFile = args[i];

         }

      }

      if(metaFile == null || inputFile == null){

         System.err.println(usage);
         System.exit(1);      

      }

      try{


         XMLToFieldConverter test = new XMLToFieldConverter(args[0]);

         String input = FileUtils.readFile(args[1]);

         XMLMessageParser parser = new XMLMessageParser(input);

         String result = test.convertToString(parser.getDocument());

         System.out.println(result);

      }
      catch(Exception ex){

         ex.printStackTrace();

      }

   }

}