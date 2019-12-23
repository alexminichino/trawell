package com.trawell.models;

import static org.apache.commons.codec.digest.DigestUtils.*; 

/**
 * @author Milione Vincent
 * class which allows encription via MD5
 */
public class Encoder {
	/**
	 * @author Milione Vincent
	 * The constructor creates an encoder that simply uses a static salt
	 */
	public Encoder () {}

	/**
	 * @author Milione Vincent
	 * @param salt a dinamic salt
	 * The constructor creates an encoder that uses both dinamic salt and static salt
	 */
	public Encoder(String salt) {
		this.salt = salt;
	}

	/**
	 * @author Milione Vincent
	 * The method adds salt to the string that must be encripted
	 * @param encodify string that must be encripted
	 * @return encodify string concatenated with the static salt and a dinamic salt if given
	 */
	private String salt(String encodify) {
		return salt.equals(static_salt) ? salt + encodify : salt + static_salt + encodify;
	}

	/**
	 * @author Milione Vincent
	 * The method encodes the parameter encodify in md5
	 * @param encodify string that must be encripted
	 * @param nof number of times the encription must be done
	 * @return encripted string in md5
	 */
	public String encoding(String encodify, int nof) {

		for (int i = 0; i < nof; i++)
			encodify = i%2==0 ? md5Hex(encodify).toUpperCase() : md5Hex(salt(encodify)).toUpperCase();
		
		return encodify;
    }
    private final static String static_salt = "superkalifragilistic";
    private String salt = static_salt;

}