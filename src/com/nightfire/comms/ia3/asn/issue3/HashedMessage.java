package com.nightfire.comms.ia3.asn.issue3; // machine generated code. DO NOT EDIT

import cryptix.asn1.lang.*;

public class HashedMessage extends Sequence {

   // Constructor(s)
   // -------------------------------------------------------------------------

   /**
    * Constructs a new instance of this type with a blank Name.
    */
   public HashedMessage() {
      super("", new Tag(Tag.SEQUENCE));
   }

   /**
    * Constructs a new instance of this type with a designated Name.
    *
    * @param name the designated Name for this new instance.
    */
   public HashedMessage(String name) {
      super(name, new Tag(Tag.SEQUENCE));
   }

   /**
    * Constructs a new instance of this type with a designated Name and Tag.
    *
    * @param name the designated Name for this new instance.
    * @param tag the designated tag for this new instance.
    */
   public HashedMessage(String name, Tag tag) {
      super(name, tag);
   }

   /**
    * Constructs a new instance of this type with a trivial Name and an
    * initial value.
    *
    * @param value the initial value of this instance.
    */
   public HashedMessage(Sequence value) {
      this("", value);
   }

   /**
    * Constructs a new instance of this type with a designated Name and an
    * initial value.
    *
    * @param name the designated Name for this new instance.
    * @param value the initial value of this instance.
    */
   public HashedMessage(String name, Sequence value) {
      this(name, new Tag(Tag.SEQUENCE), value);
   }

   /**
    * Constructs a new instance of this type given its Name, Tag and initial
    * value.
    *
    * @param name the designated Name for this new instance.
    * @param tag the specific tag for this instance.
    * @param value the initial value for this instance.
    */
   public HashedMessage(String name, Tag tag, Sequence value) {
      super(name, tag, value == null ? null : value.value());
   }

   // Constants and variables
   // -------------------------------------------------------------------------


   // Over-loaded implementation of methods defined in superclass
   // -------------------------------------------------------------------------

   protected void initInternal() {
      super.initInternal();

      IType hashedVersion = new Version("hashedVersion", Version.v1999);
      hashedVersion.optional(true);
      components.add(hashedVersion);
      IType hashAlgorithmIdentifier = new AlgorithmIdentifier("hashAlgorithmIdentifier");
      components.add(hashAlgorithmIdentifier);
      IType hashedContent = new BasicMessage("hashedContent");
      components.add(hashedContent);
      IType messageDigest = new OctetString("messageDigest");
      components.add(messageDigest);
   }

   // Accessor methods
   // -------------------------------------------------------------------------

   public Version getHashedVersion() {
      return (Version) components.get(0);
   }

   public void setHashedVersion(Version obj) {
      Version it = getHashedVersion();
      it.value(obj.value());
      components.set(0, it);
   }

   public AlgorithmIdentifier getHashAlgorithmIdentifier() {
      return (AlgorithmIdentifier) components.get(1);
   }

   public void setHashAlgorithmIdentifier(AlgorithmIdentifier obj) {
      AlgorithmIdentifier it = getHashAlgorithmIdentifier();
      it.value(obj.value());
      components.set(1, it);
   }

   public BasicMessage getHashedContent() {
      return (BasicMessage) components.get(2);
   }

   public void setHashedContent(BasicMessage obj) {
      BasicMessage it = getHashedContent();
      it.value(obj.value());
      components.set(2, it);
   }

   public OctetString getMessageDigest() {
      return (OctetString) components.get(3);
   }

   public void setMessageDigest(OctetString obj) {
      OctetString it = getMessageDigest();
      it.value(obj.value());
      components.set(3, it);
   }

}

// Generated by the cryptix ASN.1 kit on Tue Aug 24 11:57:40 PDT 2004
