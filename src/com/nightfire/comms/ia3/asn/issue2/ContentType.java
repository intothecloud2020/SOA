package com.nightfire.comms.ia3.asn.issue2; // machine generated code. DO NOT EDIT

import cryptix.asn1.lang.*;

public class ContentType extends ObjectIdentifier {

   // Constructor(s)
   // -------------------------------------------------------------------------

   /**
    * Constructs a new instance of this type with a blank Name.
    */
   public ContentType() {
      super("", new Tag(Tag.OBJECT_IDENTIFIER));
   }

   /**
    * Constructs a new instance of this type with a designated Name.
    *
    * @param name the designated Name for this new instance.
    */
   public ContentType(String name) {
      super(name, new Tag(Tag.OBJECT_IDENTIFIER));
   }

   /**
    * Constructs a new instance of this type with a designated Name and Tag.
    *
    * @param name the designated Name for this new instance.
    * @param tag the designated tag for this new instance.
    */
   public ContentType(String name, Tag tag) {
      super(name, tag);
   }

   /**
    * Constructs a new instance of this type with a trivial Name and an
    * initial value.
    *
    * @param value the initial value of this instance.
    */
   public ContentType(ObjectIdentifier value) {
      this("", value);
   }

   /**
    * Constructs a new instance of this type with a designated Name and an
    * initial value.
    *
    * @param name the designated Name for this new instance.
    * @param value the initial value of this instance.
    */
   public ContentType(String name, ObjectIdentifier value) {
      this(name, new Tag(Tag.OBJECT_IDENTIFIER), value);
   }

   /**
    * Constructs a new instance of this type given its Name, Tag and initial
    * value.
    *
    * @param name the designated Name for this new instance.
    * @param tag the specific tag for this instance.
    * @param value the initial value for this instance.
    */
   public ContentType(String name, Tag tag, ObjectIdentifier value) {
      super(name, tag, value == null ? null : value.value());
   }

   // Constants and variables
   // -------------------------------------------------------------------------

}

// Generated by the cryptix ASN.1 kit on Sun Mar 09 12:59:14 PST 2003
