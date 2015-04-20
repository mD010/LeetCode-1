/*
	Compare Version Numbers
	Compare two version numbers version1 and version2.
	If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

	You may assume that the version strings are non-empty and contain only digits and the . character.
	The . character does not represent a decimal point and is used to separate number sequences.
	For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

	Here is an example of version numbers ordering:
		0.1 < 1.1 < 1.2 < 13.37
*/

//solution1
public class Solution {
    public int compareVersion(String version1, String version2) {
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");

        int length = Math.max(str1.length, str2.length);
        for (int i = 0; i < length; i++) {
        	Integer v1 = i < str1.length ? Integer.parseInt(str1[i]) : 0;
        	Integer v2 = i < str2.length ? Integer.parseInt(str2[i]) : 0;
        	int compare = v1.compareTo(v2);
        	if (compare != 0)
        		return compare;
        }
        return 0;
    }
}

//solution2
public class Solution {
    public int compareVersion(String version1, String version2) {
        String[] strs1 = version1.split("\\.");
        String[] strs2 = version2.split("\\.");
        int maxLen = Math.max(strs1.length, strs2.length);
        int[] nums1 = new int[maxLen];
        int[] nums2 = new int[maxLen];
        for (int i = 0; i < strs1.length; i++) {
            nums1[i] = Integer.parseInt(strs1[i]);
        }
        for (int i = 0; i < strs2.length; i++) {
            nums2[i] = Integer.parseInt(strs2[i]);
        }
        for (int i = 0; i < maxLen; i++) {
            if (nums1[i] > nums2[i]) return 1;
            else if (nums1[i] < nums2[i]) return -1;
        }
        return 0;
    }
}