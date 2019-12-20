package com.trawell.models;

import static org.apache.commons.codec.digest.DigestUtils.*; 


public class Encoder {

	public Encoder(String salt) {
		this.salt = salt;
	}

	private String salt(String encodify) {
		return salt.equals(static_salt) ? salt : salt + static_salt + encodify;
	}

	public String encoding(String encodify, int nof) {

		for (int i = 0; i < nof; i++)
			encodify = i%2==0 ? md5Hex(encodify).toUpperCase() : md5Hex(salt(encodify)).toUpperCase();
		
		return encodify;
    }
    private final static String static_salt = "superkalifragilistic";
    private String salt = static_salt;

}