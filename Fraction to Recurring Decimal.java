/*
	Fraction to Recurring Decimal 

	Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

	If the fractional part is repeating, enclose the repeating part in parentheses.

	For example,
		Given numerator = 1, denominator = 2, return "0.5".
		Given numerator = 2, denominator = 1, return "2".
		Given numerator = 2, denominator = 3, return "0.(6)".

	Tags: HashTable, Math
*/


/*
		soluton:
			1. corner case, numertator(分子) == 0 返回 0， denominator(分母) == 0返回空
			2. 按位异或,作用是判断是否是负数，负数的
			3. 把两个数转为正数，为避免移除，int转为long
			4. 先保存结果的整数部分
			5. 结果的余数部分乘以10
			6. 用hasmap保存结果的余数部分，记录位置，只要后面一出现相同的余数，就意味着开始循环 
			7. 如果没出现则继续除
*/
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)//分子
            return "0";
        if (denominator == 0)//分母
            return "";
        String ans = "";
        
        //假如结果为负数
        if (((numerator < 0) && (denominator > 0)) || ((numerator > 0) && (denominator < 0)) ) //按位异或
            ans += "-";
        //把两个数转为正数，为避免移除，int转为long
        long num = numerator;
        long den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);
        
        //结果的整数部分
        long res = num / den;
        ans += String.valueOf(res);
        
        //if res can be divided,return the result
        //结果的余数部分 乘以10
        long rem = (num % den) * 10;
        if (rem == 0)
            return ans;
        
        
        //结果的小数部分,用map保存每一个余数的位置，只要后面出现一样的余数就取2个余数之间的
        //Using HashMap to store <rem, position of rem>
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        ans += ".";
        while (rem != 0) {
            //如果前面已经出现过余数，那么将会开始循环
            if (map.containsKey(rem)) {
                int beg = map.get(rem);//循环体开始的位置
                String part1 = ans.substring(0, beg);//
                String part2 = ans.substring(beg, ans.length());
                ans = part1 + "(" + part2 + ")";
                return ans;
            }
            
            //继续往下除
            //save the integer part of rem's division
            map.put(rem, ans.length());//rem的位置是ans.length ,所以下一次循环找到相同的时候，rpart1 应该等于 rem的位置 - 1
            res = rem / den;
            ans += res + "";
            rem = (rem % den) * 10;
        }
        return ans;
    }
}