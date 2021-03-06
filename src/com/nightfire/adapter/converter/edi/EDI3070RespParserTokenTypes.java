// $ANTLR 2.7.1: "expandededi3070Resp.g" -> "EDI3070RespParser.java"$

  package com.nightfire.adapter.converter.edi;

  import java.io.*;
  import java.util.Vector;
  import java.util.Hashtable;
 
public interface EDI3070RespParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int BAD_TRANSACTION = 4;
	int ENVELOPE = 5;
	int FUNCGROUP = 6;
	int TRANSACTION = 7;
	int CONTAINER = 8;
	int SEGMENT = 9;
	int LOOP = 10;
	int COMPELEM = 11;
	int ELEMGROUP = 12;
	int NULL = 13;
	int LITERAL_ST = 14;
	int SEP = 15;
	// "855" = 16
	// "865" = 17
	// "997" = 18
	// "AK1" = 19
	int SEGTERM = 20;
	// "AK2" = 21
	// "AK3" = 22
	// "AK4" = 23
	// "AK5" = 24
	// "AK9" = 25
	int LITERAL_ACK = 26;
	int LITERAL_ADV = 27;
	int LITERAL_AMT = 28;
	int LITERAL_BCA = 29;
	int LITERAL_BAK = 30;
	int LITERAL_CSH = 31;
	int LITERAL_CTB = 32;
	int LITERAL_CTP = 33;
	int LITERAL_CTT = 34;
	int LITERAL_CUR = 35;
	int LITERAL_DIS = 36;
	int LITERAL_DTM = 37;
	int LITERAL_FOB = 38;
	// "G53" = 39
	int LITERAL_GE = 40;
	int LITERAL_GS = 41;
	int LITERAL_IEA = 42;
	int LITERAL_INC = 43;
	int LITERAL_ISA = 44;
	// "IT8" = 45
	int LITERAL_ITD = 46;
	int LITERAL_LDT = 47;
	int LITERAL_LIN = 48;
	int LITERAL_LM = 49;
	int LITERAL_LQ = 50;
	int LITERAL_MAN = 51;
	int LITERAL_MEA = 52;
	int LITERAL_MSG = 53;
	int LITERAL_MTX = 54;
	// "N1" = 55
	// "N2" = 56
	// "N3" = 57
	// "N4" = 58
	// "N9" = 59
	// "NX2" = 60
	int LITERAL_PAM = 61;
	int LITERAL_PCT = 62;
	int LITERAL_PD = 63;
	int LITERAL_PDD = 64;
	int LITERAL_PER = 65;
	int LITERAL_PID = 66;
	int LITERAL_PKG = 67;
	// "PO1" = 68
	// "PO3" = 69
	// "PO4" = 70
	int LITERAL_POC = 71;
	int LITERAL_PWK = 72;
	int LITERAL_QTY = 73;
	int LITERAL_REF = 74;
	int LITERAL_SAC = 75;
	int LITERAL_SCH = 76;
	int LITERAL_SDQ = 77;
	int LITERAL_SE = 78;
	int LITERAL_SI = 79;
	int LITERAL_SLN = 80;
	int LITERAL_SPI = 81;
	int LITERAL_TAX = 82;
	// "TD1" = 83
	// "TD3" = 84
	// "TD4" = 85
	// "TD5" = 86
	int LITERAL_TXI = 87;
	int ELEM = 88;
	int ELSEP = 89;
	int LITERAL_RQ = 90;
	int LITERAL_EFI = 91;
	// "IN2" = 92
	// "PO2" = 93
}
