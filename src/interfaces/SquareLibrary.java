package interfaces;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface SquareLibrary {

	//@formatter:off
	/**
	 * 0x88 board
	 * 
	 *  112 113 114 115 116 117 118 119 | 120 121 122 123 124 125 126 127
	     96  97  98  99 100 101 102 103 | 104 105 106 107 108 109 110 111
	     80  81  82  83  84  85  86  87 |  88  89  90  91  92  93  94  95
	     64  65  66  67  68  69  70  71 |  72  73  74  75  76  77  78  79
	     48  49  50  51  52  53  54  55 |  56  57  58  59  60  61  62  63
	     32  33  34  35  36  37  38  39 |  40  41  42  43  44  45  46  47
	     16  17  18  19  20  21  22  23 |  24  25  26  27  28  29  30  31
	      0   1   2   3   4   5   6   7 |   8   9  10  11  12  13  14  15
	  */
	//@formatter:on

	// String[] vals = {"", "A", "B", "C", "D", "E", "F", "G", "H"};
	// for (int n = 1, j = 0; n <= 8; n++, j++ )
	// for (int i = 1, tempJ = j; i <= 8; i++, tempJ+=16){
	// System.out.println("public static final int "+vals[n] + i +
	// " = "+tempJ+";");
	// }

	public static final int A1 = 0;
	public static final int A2 = 16;
	public static final int A3 = 32;
	public static final int A4 = 48;
	public static final int A5 = 64;
	public static final int A6 = 80;
	public static final int A7 = 96;
	public static final int A8 = 112;
	public static final int B1 = 1;
	public static final int B2 = 17;
	public static final int B3 = 33;
	public static final int B4 = 49;
	public static final int B5 = 65;
	public static final int B6 = 81;
	public static final int B7 = 97;
	public static final int B8 = 113;
	public static final int C1 = 2;
	public static final int C2 = 18;
	public static final int C3 = 34;
	public static final int C4 = 50;
	public static final int C5 = 66;
	public static final int C6 = 82;
	public static final int C7 = 98;
	public static final int C8 = 114;
	public static final int D1 = 3;
	public static final int D2 = 19;
	public static final int D3 = 35;
	public static final int D4 = 51;
	public static final int D5 = 67;
	public static final int D6 = 83;
	public static final int D7 = 99;
	public static final int D8 = 115;
	public static final int E1 = 4;
	public static final int E2 = 20;
	public static final int E3 = 36;
	public static final int E4 = 52;
	public static final int E5 = 68;
	public static final int E6 = 84;
	public static final int E7 = 100;
	public static final int E8 = 116;
	public static final int F1 = 5;
	public static final int F2 = 21;
	public static final int F3 = 37;
	public static final int F4 = 53;
	public static final int F5 = 69;
	public static final int F6 = 85;
	public static final int F7 = 101;
	public static final int F8 = 117;
	public static final int G1 = 6;
	public static final int G2 = 22;
	public static final int G3 = 38;
	public static final int G4 = 54;
	public static final int G5 = 70;
	public static final int G6 = 86;
	public static final int G7 = 102;
	public static final int G8 = 118;
	public static final int H1 = 7;
	public static final int H2 = 23;
	public static final int H3 = 39;
	public static final int H4 = 55;
	public static final int H5 = 71;
	public static final int H6 = 87;
	public static final int H7 = 103;
	public static final int H8 = 119;

	// String[] vals = {"A", "A", "B", "C", "D", "E", "F", "G", "H"};
	// for (int n = 1, j = 0; n <= 8; n++, j++ )
	// for (int i = 1, tempJ = j; i <= 8; i++, tempJ+=16){
	// System.out.println("public static final String "+vals[n] + i + "_STRING"
	// + " = \""+vals[n].toLowerCase()+i+"\";");
	// }

	public static final String A1_STRING = "a1";
	public static final String A2_STRING = "a2";
	public static final String A3_STRING = "a3";
	public static final String A4_STRING = "a4";
	public static final String A5_STRING = "a5";
	public static final String A6_STRING = "a6";
	public static final String A7_STRING = "a7";
	public static final String A8_STRING = "a8";
	public static final String B1_STRING = "b1";
	public static final String B2_STRING = "b2";
	public static final String B3_STRING = "b3";
	public static final String B4_STRING = "b4";
	public static final String B5_STRING = "b5";
	public static final String B6_STRING = "b6";
	public static final String B7_STRING = "b7";
	public static final String B8_STRING = "b8";
	public static final String C1_STRING = "c1";
	public static final String C2_STRING = "c2";
	public static final String C3_STRING = "c3";
	public static final String C4_STRING = "c4";
	public static final String C5_STRING = "c5";
	public static final String C6_STRING = "c6";
	public static final String C7_STRING = "c7";
	public static final String C8_STRING = "c8";
	public static final String D1_STRING = "d1";
	public static final String D2_STRING = "d2";
	public static final String D3_STRING = "d3";
	public static final String D4_STRING = "d4";
	public static final String D5_STRING = "d5";
	public static final String D6_STRING = "d6";
	public static final String D7_STRING = "d7";
	public static final String D8_STRING = "d8";
	public static final String E1_STRING = "e1";
	public static final String E2_STRING = "e2";
	public static final String E3_STRING = "e3";
	public static final String E4_STRING = "e4";
	public static final String E5_STRING = "e5";
	public static final String E6_STRING = "e6";
	public static final String E7_STRING = "e7";
	public static final String E8_STRING = "e8";
	public static final String F1_STRING = "f1";
	public static final String F2_STRING = "f2";
	public static final String F3_STRING = "f3";
	public static final String F4_STRING = "f4";
	public static final String F5_STRING = "f5";
	public static final String F6_STRING = "f6";
	public static final String F7_STRING = "f7";
	public static final String F8_STRING = "f8";
	public static final String G1_STRING = "g1";
	public static final String G2_STRING = "g2";
	public static final String G3_STRING = "g3";
	public static final String G4_STRING = "g4";
	public static final String G5_STRING = "g5";
	public static final String G6_STRING = "g6";
	public static final String G7_STRING = "g7";
	public static final String G8_STRING = "g8";
	public static final String H1_STRING = "h1";
	public static final String H2_STRING = "h2";
	public static final String H3_STRING = "h3";
	public static final String H4_STRING = "h4";
	public static final String H5_STRING = "h5";
	public static final String H6_STRING = "h6";
	public static final String H7_STRING = "h7";
	public static final String H8_STRING = "h8";

	public static final Map<Integer, String> intToStringMap = Collections
			.unmodifiableMap(new HashMap<Integer, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(A1, A1_STRING);
					put(A2, A2_STRING);
					put(A3, A3_STRING);
					put(A4, A4_STRING);
					put(A5, A5_STRING);
					put(A6, A6_STRING);
					put(A7, A7_STRING);
					put(A8, A8_STRING);
					put(B1, B1_STRING);
					put(B2, B2_STRING);
					put(B3, B3_STRING);
					put(B4, B4_STRING);
					put(B5, B5_STRING);
					put(B6, B6_STRING);
					put(B7, B7_STRING);
					put(B8, B8_STRING);
					put(C1, C1_STRING);
					put(C2, C2_STRING);
					put(C3, C3_STRING);
					put(C4, C4_STRING);
					put(C5, C5_STRING);
					put(C6, C6_STRING);
					put(C7, C7_STRING);
					put(C8, C8_STRING);
					put(D1, D1_STRING);
					put(D2, D2_STRING);
					put(D3, D3_STRING);
					put(D4, D4_STRING);
					put(D5, D5_STRING);
					put(D6, D6_STRING);
					put(D7, D7_STRING);
					put(D8, D8_STRING);
					put(E1, E1_STRING);
					put(E2, E2_STRING);
					put(E3, E3_STRING);
					put(E4, E4_STRING);
					put(E5, E5_STRING);
					put(E6, E6_STRING);
					put(E7, E7_STRING);
					put(E8, E8_STRING);
					put(F1, F1_STRING);
					put(F2, F2_STRING);
					put(F3, F3_STRING);
					put(F4, F4_STRING);
					put(F5, F5_STRING);
					put(F6, F6_STRING);
					put(F7, F7_STRING);
					put(F8, F8_STRING);
					put(G1, G1_STRING);
					put(G2, G2_STRING);
					put(G3, G3_STRING);
					put(G4, G4_STRING);
					put(G5, G5_STRING);
					put(G6, G6_STRING);
					put(G7, G7_STRING);
					put(G8, G8_STRING);
					put(H1, H1_STRING);
					put(H2, H2_STRING);
					put(H3, H3_STRING);
					put(H4, H4_STRING);
					put(H5, H5_STRING);
					put(H6, H6_STRING);
					put(H7, H7_STRING);
					put(H8, H8_STRING);
				}
			});
	public static final Map<String, Integer> stringToIntMap = Collections
			.unmodifiableMap(new HashMap<String, Integer>() {
				private static final long serialVersionUID = 1L;
				{
					put(A1_STRING, A1);
					put(A2_STRING, A2);
					put(A3_STRING, A3);
					put(A4_STRING, A4);
					put(A5_STRING, A5);
					put(A6_STRING, A6);
					put(A7_STRING, A7);
					put(A8_STRING, A8);
					put(B1_STRING, B1);
					put(B2_STRING, B2);
					put(B3_STRING, B3);
					put(B4_STRING, B4);
					put(B5_STRING, B5);
					put(B6_STRING, B6);
					put(B7_STRING, B7);
					put(B8_STRING, B8);
					put(C1_STRING, C1);
					put(C2_STRING, C2);
					put(C3_STRING, C3);
					put(C4_STRING, C4);
					put(C5_STRING, C5);
					put(C6_STRING, C6);
					put(C7_STRING, C7);
					put(C8_STRING, C8);
					put(D1_STRING, D1);
					put(D2_STRING, D2);
					put(D3_STRING, D3);
					put(D4_STRING, D4);
					put(D5_STRING, D5);
					put(D6_STRING, D6);
					put(D7_STRING, D7);
					put(D8_STRING, D8);
					put(E1_STRING, E1);
					put(E2_STRING, E2);
					put(E3_STRING, E3);
					put(E4_STRING, E4);
					put(E5_STRING, E5);
					put(E6_STRING, E6);
					put(E7_STRING, E7);
					put(E8_STRING, E8);
					put(F1_STRING, F1);
					put(F2_STRING, F2);
					put(F3_STRING, F3);
					put(F4_STRING, F4);
					put(F5_STRING, F5);
					put(F6_STRING, F6);
					put(F7_STRING, F7);
					put(F8_STRING, F8);
					put(G1_STRING, G1);
					put(G2_STRING, G2);
					put(G3_STRING, G3);
					put(G4_STRING, G4);
					put(G5_STRING, G5);
					put(G6_STRING, G6);
					put(G7_STRING, G7);
					put(G8_STRING, G8);
					put(H1_STRING, H1);
					put(H2_STRING, H2);
					put(H3_STRING, H3);
					put(H4_STRING, H4);
					put(H5_STRING, H5);
					put(H6_STRING, H6);
					put(H7_STRING, H7);
					put(H8_STRING, H8);
				}
			});

}
