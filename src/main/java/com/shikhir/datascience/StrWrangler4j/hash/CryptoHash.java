package com.shikhir.datascience.StrWrangler4j.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class CryptoHash {
	private static final Logger log = Logger.getLogger(CryptoHash.class.getName());
	private static CryptoHash instance = null;
	public CryptoHash() {

	}
	public static final CryptoHash getInstance() {
		if (instance == null) {
			instance = new CryptoHash();
		}
		return instance;
	}
	
	/** Hashes a string using sha256 to hex
	 * 
	 * @param originalString string to hash
	 * @return hashed value in hex
	 */

	public static String sha256_To_hex(final String originalString) {
		MessageDigest digest=null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			log.log(Level.SEVERE, "Exception: " + e);
		}
		byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
		
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < encodedhash.length; i++) {
		String hex = Integer.toHexString(0xff & encodedhash[i]);
		if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	
	/** Hashes a string using sha1 to hex
	 * 
	 * @param originalString string to hash
	 * @return hashed value in hex
	 */
	public static String sha1_To_hex(final String originalString) {
		MessageDigest digest=null;
		if(originalString==null || originalString.length()==0) {
			throw new IllegalArgumentException("String cannot be null or of size 0");
		}
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			log.log(Level.SEVERE, "Exception: " + e);
		}
		byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
		
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < encodedhash.length; i++) {
		String hex = Integer.toHexString(0xff & encodedhash[i]);
		if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	
	private static String getFileChecksum(MessageDigest digest, File file)
	{
	    //Get file input stream for reading the file content
	    FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.log(Level.SEVERE, "Exception in CryptoHash: " + e);
		}
	     
	    //Create byte array to read data in chunks
	    byte[] byteArray = new byte[1024];
	    int bytesCount = 0;
	      
	    //Read file data and update in message digest
	    try {
			while ((bytesCount = fis.read(byteArray)) != -1) {
			    digest.update(byteArray, 0, bytesCount);
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "Exception in CryptoHash: " + e);
		};
	     
	    //close the stream; We don't need it now.
	    try {
			fis.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Exception in CryptoHash: " + e);
		}
	     
	    //Get the hash's bytes
	    byte[] bytes = digest.digest();
	     
	    //This bytes[] has bytes in decimal format;
	    //Convert it to hexadecimal format
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	     
	    //return complete hash
	   return sb.toString();
	}
	
	/** Returns MD5 checksum of a file
	 * 
	 * @param fileName a string containing the location and name of file
	 * @return hashed value in hex
	 */

	public static String getMD5ChecksumInHex(String fileName) {
		//Create checksum for this file
		File file = new File(fileName);
		return getMD5ChecksumInHex(file);
	}

	/** Returns MD5 checksum of a file
	 * 
	 * @param file a File that needs to be checksum
	 * @return hashed value in hex
	 */

	public static String getMD5ChecksumInHex(File file) {
		//Create checksum for this file
		 
		//Use MD5 algorithm
		MessageDigest md5Digest = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.log(Level.SEVERE, "Exception in CryptoHash:  Error getting checksum of file : "+file.getName());
			return null;
		}
		 
		//Get the checksum
		String checksum = getFileChecksum(md5Digest, file);
		 
		//see checksum
		log.log(Level.FINE, "File: "+file.getName()+" - "+checksum);
		
		return checksum;
	}
	
}
