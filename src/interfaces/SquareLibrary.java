package interfaces;

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
	
//	String[] vals = {"A", "A", "B", "C", "D", "E", "F", "G", "H"};
//    for (int n = 1, j = 0; n <= 8; n++, j++ )
//       for (int i = 1, tempJ = j; i <= 8; i++, tempJ+=16){
//           System.out.println("public static final String "+vals[n] + i + "_STRING"
//           + " = \""+vals[n].toLowerCase()+i+"\";");
//       }

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

}
