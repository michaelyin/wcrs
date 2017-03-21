/**
 * 
 */
package net.wyun.wcrs.model;

/**
 * @author Xuecheng
 * when it is mapped to mysql database
 * it is 0--MALE and 1--FEMAL
 *
 */
public enum Gender {
     MALE, FEMALE;
	
	public static Gender gender(int i){
		return i==0?Gender.FEMALE:Gender.MALE;
	 }
}
