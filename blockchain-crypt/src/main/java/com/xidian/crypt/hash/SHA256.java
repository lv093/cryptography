package com.xidian.crypt.hash;

public class SHA256 {

    private String output;

    private Integer repeatNum;

    private String H0 = "6a09e667";
    private String H1 = "bb67ae85";
    private String H2 = "3c6ef372";
    private String H3 = "a54ff53a";
    private String H4 = "510e527f";
    private String H5 = "9b05688c";
    private String H6 = "1f83d9ab";
    private String H7 = "5be0cd19";
    private String A, B, C, D, E, F, G, H;

    private String[] k = new String[64];
    private final String[] K = {
            "428a2f98", "71374491", "b5c0fbcf", "e9b5dba5", "3956c25b", "59f111f1", "923f82a4", "ab1c5ed5",
            "d807aa98", "12835b01", "243185be", "550c7dc3", "72be5d74", "80deb1fe", "9bdc06a7", "c19bf174",
            "e49b69c1", "efbe4786", "0fc19dc6", "240ca1cc", "2de92c6f", "4a7484aa", "5cb0a9dc", "76f988da",
            "983e5152", "a831c66d", "b00327c8", "bf597fc7", "c6e00bf3", "d5a79147", "06ca6351", "14292967",
            "27b70a85", "2e1b2138", "4d2c6dfc", "53380d13", "650a7354", "766a0abb", "81c2c92e", "92722c85",
            "a2bfe8a1", "a81a664b", "c24b8b70", "c76c51a3", "d192e819", "d6990624", "f40e3585", "106aa070",
            "19a4c116", "1e376c08", "2748774c", "34b0bcb5", "391c0cb3", "4ed8aa4a", "5b9cca4f", "682e6ff3",
            "748f82ee", "78a5636f", "84c87814", "8cc70208", "90befffa", "a4506ceb", "bef9a3f7", "c67178f2"
    };

    private String[] w = new String[80];


    public SHA256() {

    }


    public String getHash() {
        output = H0 + H1 + H2 + H3 + H4 + H5 + H6 + H7;
        return output.toUpperCase();
    }

    /**
     * 对输入的字符串进行SHA256处理
     *
     * @param input
     */
    public SHA256(String input) {
        for (int i = 0; i < 64; i++) {
            k[i] = this.hexToBi(K[i]);
        }

        String binInput = this.stringToBinary(input);
        final Integer inputLength = binInput.length();

        if (inputLength < 448) {
            repeatNum = 1;
        } else if (inputLength >= 448 && inputLength <= 512) {
            repeatNum = 2;
        } else {
            if (inputLength % 512 < 448) {
                repeatNum = inputLength / 512 + 1;
            } else if (inputLength % 512 >= 448 && inputLength % 512 <= 512) {
                repeatNum = inputLength / 512 + 2;
            }
        }

        char[] inputChars = new char[512 * repeatNum];

        for (int i = 0; i < inputLength; i++) {
            inputChars[i] = binInput.charAt(i);
        }

        String lenthStr = Integer.toBinaryString(inputLength);

        //填充
        inputChars[inputLength] = 1;
        for (int i = inputLength + 1; i < repeatNum * 512 - lenthStr.length(); i++) {
            inputChars[i] = 0;
        }
        for (int i = 512 * repeatNum - lenthStr.length(); i < 512 * repeatNum; i++) {
            inputChars[i] = lenthStr.charAt(i - 512 * repeatNum + lenthStr.length());
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 512 * repeatNum; i++) {
            builder.append(inputChars[i]);
        }
        String filledStr = builder.toString();

        //进行逻辑计算
        for (int i = 0; i < repeatNum; i++) {
            String splitStr = filledStr.substring(i*512, (i+1)*512);

            for (int j = 0; j < 16; j++) {
                w[j] = splitStr.substring(j*32, (j+1)*32);
            }
            for (int j = 16; j < 64; j++) {
                w[j] = xorAdd(xorAdd(smallSigmaOne(w[j - 2]), w[j - 7]), xorAdd(smallSigmaZero(w[j - 15]), w[j - 16]));
            }

            A = hexToBi(H0);
            B = hexToBi(H1);
            C = hexToBi(H2);
            D = hexToBi(H3);
            E = hexToBi(H4);
            F = hexToBi(H5);
            G = hexToBi(H6);
            H = hexToBi(H7);
            SHA_256(A, B, C, D, E, F, G, H);
        }
    }


    private void SHA_256(String A, String B, String C, String D, String E, String F, String G, String H) {
        String temp1 = new String();
        String temp2 = new String();

        for (int i = 0; i < 64; i++) {
            temp1 = T1(H, E, ch(E, F, G), w[i], k[i]);
            temp2 = Add(temp1, T2(A, maj(A, B, C)));
            H = G;
            G = F;
            F = E;
            E = Add(D, temp1);
            D = C;
            C = B;
            B = A;
            A = temp2;
        }

        H0 = biToHex(Add(A, hexToBi(H0)));
        H1 = biToHex(Add(B, hexToBi(H1)));
        H2 = biToHex(Add(C, hexToBi(H2)));
        H3 = biToHex(Add(D, hexToBi(H3)));
        H4 = biToHex(Add(E, hexToBi(H4)));
        H5 = biToHex(Add(F, hexToBi(H5)));
        H6 = biToHex(Add(G, hexToBi(H6)));
        H7 = biToHex(Add(H, hexToBi(H7)));

    }



    public String biToHex(String str) {
        int temp = 0;
        StringBuffer st = new StringBuffer();

        for (int i = 0; i < str.length() / 4; i++) {
            temp = Integer.valueOf(str.substring(i * 4, (i + 1) * 4), 2);
            st = st.append(Integer.toHexString(temp));
        }
        return st.toString();
    }

    public String hexToBi(String str) {
        String temp = "";
        String st = "";

        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '0':
                    st = "0000";
                    break;
                case '1':
                    st = "0001";
                    break;
                case '2':
                    st = "0010";
                    break;
                case '3':
                    st = "0011";
                    break;
                case '4':
                    st = "0100";
                    break;
                case '5':
                    st = "0101";
                    break;
                case '6':
                    st = "0110";
                    break;
                case '7':
                    st = "0111";
                    break;
                case '8':
                    st = "1000";
                    break;
                case '9':
                    st = "1001";
                    break;
                case 'a':
                    st = "1010";
                    break;
                case 'b':
                    st = "1011";
                    break;
                case 'c':
                    st = "1100";
                    break;
                case 'd':
                    st = "1101";
                    break;
                case 'e':
                    st = "1110";
                    break;
                case 'f':
                    st = "1111";
                    break;
            }
            temp = temp + st;
        }
        return temp.toString();
    }

    private String stringToBinary(String str) {
        StringBuffer str2 = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            str2 = str2.append(fillZero(Integer.toBinaryString(Integer.valueOf(str.charAt(i))), 8));
        }
        return str2.toString();
    }

    private String fillZero(String str, int n) {
        String str2 = new String();
        StringBuffer str1 = new StringBuffer();

        if (str.length() < n)
            for (int i = 0; i < n - str.length(); i++) {
                str2 = str1.append('0').toString();
            }
        return str2 + str;
    }

    //ADD
    private String xorAdd(String str1, String str2) {
        char[] cArray = new char[32];
        int flag = 0;
        for (int i = str1.length() - 1; i >= 0; i--) {
            cArray[i] = (char) (((str1.charAt(i) - '0') + ((str2.charAt(i) - '0')) + flag) % 2 + '0');
            if (((str1.charAt(i) - '0') + (str2.charAt(i) - '0') + flag) >= 2)
                flag = 1;
            else
                flag = 0;
        }
        return new String(cArray);
    }


    public String ch(String str1, String str2, String str3) {
        return bit_df_or(and(str1, str2), and(not(str1), str3));
    }

    public String maj(String str1, String str2, String str3) {
        return bit_df_or(bit_df_or(and(str1, str2), and(str1, str3)), and(str2, str3));
    }


    //AND
    public String and(String str1, String str2) {
        String str = new String();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == '0' || str2.charAt(i) == '0')
                str = s.append('0').toString();
            else
                str = s.append('1').toString();
        }
        return str;
    }

    //OR
    public String bit_or(String str1, String str2) {
        String str = new String();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == '1' || str2.charAt(i) == '1')
                str = s.append('1').toString();
            else
                str = s.append('0').toString();
        }
        return str;
    }

    //NOT
    public String not(String str1) {
        String str = new String();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == '0')
                str = s.append('1').toString();
            else
                str = s.append('0').toString();
        }
        return str;
    }


    public String smallSigmaZero(String str1) {
        return bit_df_or(bit_df_or(rotr(str1, 7), rotr(str1, 18)), shr(str1, 3));
    }

    public String smallSigmaOne(String str1) {
        return bit_df_or(bit_df_or(rotr(str1, 17), rotr(str1, 19)), shr(str1, 10));
    }


    public String bigSigmaZero(String str1) {
        return bit_df_or(bit_df_or(rotr(str1, 2), rotr(str1, 13)), rotr(str1, 22));
    }

    public String bigSigmaOne(String str1) {
        return bit_df_or(bit_df_or(rotr(str1, 6), rotr(str1, 11)), rotr(str1, 25));
    }


    //different OR
    public String bit_df_or(String str1, String str2) {
        String str = new String();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i))
                str = s.append('0').toString();
            else
                str = s.append('1').toString();
        }
        return str;
    }

    //same OR
    public String bit_sa_or(String str1, String str2) {
        String str = new String();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i))
                str = s.append('1').toString();
            else
                str = s.append('0').toString();
        }
        return str;
    }

    //rotate left n bits
    public String rotl(String str, int n) {
        return str.substring(0, n) + str.substring(n);
    }

    //rotate right n bits
    public String rotr(String str, int n) {
        return str.substring(str.length() - n) + str.substring(0, str.length() - n);
    }


    //right shift n bits
    public String shr(String str, int n) {
        char[] fillZero = new char[n];
        for (int i = 0; i < fillZero.length; i++)
            fillZero[i] = '0';
        String str1 = str.substring(0, str.length() - n);
        return new String(fillZero) + str1;
    }

    //ADD
    public String Add(String str1, String str2) {
        char[] cArray = new char[32];
        int flag = 0;
        for (int i = str1.length() - 1; i >= 0; i--) {
            cArray[i] = (char) (((str1.charAt(i) - '0') + ((str2.charAt(i) - '0')) + flag) % 2 + '0');
            if (((str1.charAt(i) - '0') + (str2.charAt(i) - '0') + flag) >= 2)
                flag = 1;
            else
                flag = 0;
        }
        return new String(cArray);
    }

    //find T1
    public String T1(String str_h, String str_e, String str_ch, String str_w, String str_k) {
        return Add(Add(Add(str_h, bigSigmaOne(str_e)), Add(str_ch, str_w)), str_k);
    }

    //find T2
    public String T2(String str_a, String str_maj) {
        return Add(bigSigmaZero(str_a), str_maj);
    }


    public static void main(String[] args) {
        System.out.println(new SHA256("hello").getHash());
    }
}
