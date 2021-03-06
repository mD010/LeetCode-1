/*
	ZigZag Conversion
	The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
	
	P   A   H   N
	A P L S I I G
	Y   I   R
	And then read line by line: "PAHNAPLSIIGYIR"
	Write the code that will take a string and make this conversion given a number of rows:

	string convert(string text, int nRows);
	convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

/*
	PAYPALISHIRING是输入
    
    输出
	P   A   H   N
	A P L S I I G
	Y   I   R 
	输出是按照上面这个zigzag排列之后每行相加的字符串 PAHNAPLSIIGYIR
*/
public class Solution {
    public String convert(String s, int numRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuffer();
        }
        
        int i = 0;
        while (i < len) {
        	//这个for循环是用来储存满列的数
            for (int index = 0; index < numRows && i < len; index++) {
                sb[index].append(c[i++]);
            }
            //这个for循环是用来储存不满列的数
            for (int index = numRows - 2; index >= 1 && i < len; index--) {
                sb[index].append(c[i++]);
            }
        }
        
        for (int index = 1; index < sb.length; index++) {
            sb[0].append(sb[index]);
        }
        return sb[0].toString();
    }
}