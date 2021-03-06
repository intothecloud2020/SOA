/* PGPDecrypt class
 * This adapter class calls C library function for decrypting binary string.
 */
package com.nightfire.adapter.messageprocessor.pgp;

import com.nightfire.common.*;
import com.nightfire.spi.common.driver.*;
import com.nightfire.framework.util.*;
import com.nightfire.framework.db.*;
import com.nightfire.framework.message.*;
import java.util.StringTokenizer;
import java.io.*;

/**
 * PGPDecrypt class.
 * extends PGPBase .
 */
public class PGPDecrypt extends PGPBase {
  //Privat key pass code needed to identify the correct private key.
  private final static String PRIVATE_KEY_PASSCODE_PROP = "PRIVATE_KEY_PASSCODE";

  private String privateKeyPasscode   = "";

  public PGPDecrypt() {
  }

  /**
   * This method loads properties in virtual machine.
   * @param key - Key value of persistentproperty.
   * @param type - Type value of persistentproperty.
   * @exception - ProcessingException - this exception is thrown by super class
   *              for any problem with initialization .
   */
  public void initialize ( String key, String type ) throws ProcessingException
  {
    Debug.log( this, Debug.DB_STATUS, "Loading properties for PGP Encrypt module.");
    super.initialize(key, type);

    privateKeyPasscode  = (String)adapterProperties.get(PRIVATE_KEY_PASSCODE_PROP);

  if(!(StringUtils.hasValue(privateKeyPasscode)))
    {
       Debug.log( this, Debug.ALL_ERRORS, "ERROR: PGPDecrypt: private passphrase in PGP properties is missing.");
       throw new ProcessingException("ERROR: PGPDecrypt: private passphrase in PGP properties is missing.");
    }

    Debug.log( this, Debug.DB_STATUS, "Loaded properties for PGP decrypt.");
  }

  /**
   * This method is called from driver.
   * It calls decrypt method of PGPBase class, which inturn calls native_decrypt function.
   * @param context - MessageProcessorContext.
   * @param type - input - contains encrypted data in the form of byte array.
   * @return NVPair[] - this array contains only one instance of NVPair.
   *                    name - value of NEXT_PROCESSOR_NAME; value - decrypted string.
   * @exception - ProcessingException is thrown from C routines for any error.
   */
  public NVPair[] execute ( MessageProcessorContext context, Object input ) throws MessageException, ProcessingException
  {
    Debug.log( this, Debug.BENCHMARK, "PGPDecrypt: Starting decryption process");
    if(input == null)
    {
      return null;
    }

    byte[] inputString ;
    byte[] decryptedString ;

    try
    {
      inputString = (byte[])input;
      if(inputString.length <= 0 )
      {
        throw new ProcessingException("ERROR: PGPDecrypt: Input array to process method does not contain any elements.");
      }
    }
    catch(ClassCastException exp)
    {
      throw new ProcessingException("ERROR: PGPDecrypt: Input to process method is not a valid byte array.");
    }

    try
    {
      decryptedString = decrypt(inputString,privateKeyPasscode, ringDirectory+"/");
    }
    catch(Exception exp)
    {
      throw new ProcessingException("ERROR: PGPDecrypt: Error in decryption: " + exp.getMessage());
    }

    Debug.log( this, Debug.BENCHMARK, "PGPDecrypt: Decryption done");

    String str = new String(decryptedString);

    return formatNVPair(str);
  }

  public static void main(String[] args)
  {
    if(args.length != 7)
    {
       System.out.println("usage: java PGPEncrypt dtabaseurl dbuser password propertyKey propertyType sourceFile");
       return;
    }
    try
    {
       DBInterface.initialize(args[0],args[1],args[2]);
    }
    catch(Exception exp)
    {
      String e = exp.toString();
      e = exp.getMessage();
      exp.printStackTrace();
    }

    PGPDecrypt decryption = new PGPDecrypt();
    try
    {
      decryption.initialize(args[3],args[4]);
      NVPair[] dpair = decryption.execute(null,FileUtils.readBinaryFile(args[5]));
      String result = ((String)dpair[0].value);

      FileUtils.writeFile(args[6],result);

      System.out.println("Press Enter to quit." );
      System.in.read();
    }
    catch(Exception exp)
    {
       exp.printStackTrace();
    }
  }


}
