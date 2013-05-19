package com.nightfire.comms.ia3.asn.issue2; // machine generated code. DO NOT EDIT

import cryptix.asn1.lang.*;

public class Enhancements extends Choice {

   // Constructor(s)
   // -------------------------------------------------------------------------

   /**
    * Constructs a new instance of this type with a blank Name.
    */
   public Enhancements() {
      super("", null);
   }

   /**
    * Constructs a new instance of this type with a designated Name.
    *
    * @param name the designated Name for this new instance.
    */
   public Enhancements(String name) {
      super(name, null);
   }

   /**
    * Constructs a new instance of this type with a designated Name and Tag.
    *
    * @param name the designated Name for this new instance.
    * @param tag the designated tag for this new instance.
    */
   public Enhancements(String name, Tag tag) {
      super(name, tag);
   }

   /**
    * Constructs a new instance of this type with a trivial Name and an
    * initial value.
    *
    * @param value the initial value of this instance.
    */
   public Enhancements(Choice value) {
      this("", value);
   }

   /**
    * Constructs a new instance of this type with a designated Name and an
    * initial value.
    *
    * @param name the designated Name for this new instance.
    * @param value the initial value of this instance.
    */
   public Enhancements(String name, Choice value) {
      this(name, null, value);
   }

   /**
    * Constructs a new instance of this type given its Name, Tag and initial
    * value.
    *
    * @param name the designated Name for this new instance.
    * @param tag the specific tag for this instance.
    * @param value the initial value for this instance.
    */
   public Enhancements(String name, Tag tag, Choice value) {
      super(name, tag, value == null ? null : value.value());
   }

   // Constants and variables
   // -------------------------------------------------------------------------


   // Over-loaded implementation of methods defined in superclass
   // -------------------------------------------------------------------------

   protected void initInternal() {
      super.initInternal();

      IType withDigest = new WithDigest("withDigest", new Tag(Tag.CONTEXT, 1, Module.EXPLICIT_TAGGING));
      components.add(withDigest);
      IType withDigSig = new WithDigSig("withDigSig", new Tag(Tag.CONTEXT, 2, Module.EXPLICIT_TAGGING));
      components.add(withDigSig);
   }

   // Accessor methods
   // -------------------------------------------------------------------------

   public WithDigest getWithDigest() {
      return (WithDigest) components.get(0);
   }

   public void setWithDigest(WithDigest obj) {
      WithDigest it = getWithDigest();
      it.value(obj.value());
      components.set(0, it);
   }

   public WithDigSig getWithDigSig() {
      return (WithDigSig) components.get(1);
   }

   public void setWithDigSig(WithDigSig obj) {
      WithDigSig it = getWithDigSig();
      it.value(obj.value());
      components.set(1, it);
   }

   // CHOICE-specific convenience methods
   // -------------------------------------------------------------------------

   /**
    * Returns true iff this CHOICE instance has been decoded, and its (only)
    * concrete alternative is the designated one. False otherwise.
    *
    * @return true iff this CHOICE instance has been decoded, and its (only)
    * concrete alternative is the designated one. False otherwise.
    */
   public boolean isWithDigest() {
      return !getWithDigest().isBlank();
   }
   /**
    * Returns true iff this CHOICE instance has been decoded, and its (only)
    * concrete alternative is the designated one. False otherwise.
    *
    * @return true iff this CHOICE instance has been decoded, and its (only)
    * concrete alternative is the designated one. False otherwise.
    */
   public boolean isWithDigSig() {
      return !getWithDigSig().isBlank();
   }
}

// Generated by the cryptix ASN.1 kit on Sun Mar 09 12:59:13 PST 2003
