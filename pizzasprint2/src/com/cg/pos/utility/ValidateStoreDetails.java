package com.cg.pos.utility;

import com.cg.pos.exceptions.InValidOwnerNameException;
import com.cg.pos.exceptions.InValidStoreAddressException;
import com.cg.pos.exceptions.InValidStoreContactException;
import com.cg.pos.exceptions.InValidStoreNameException;
import com.cg.pos.exceptions.StoreExceptions;

/**
 * class for validating the store details.
 * 
 * @author trainee
 *
 */
public class ValidateStoreDetails {
	/**
	 * method for validating the store name.
	 * 
	 * @param storeName
	 * @return boolean
	 * @throws InValidStoreNameException
	 * @throws StoreExceptions 
	 */
	public boolean isValidStoreName(String storeName) throws StoreExceptions {

		if (!storeName.matches("^[a-zA-Z]*$") || storeName.isEmpty()) {
			throw new StoreExceptions(ExceptionMessages.message1);
		}

		return true;

	}

	/**
	 * method for validating the store address.
	 * 
	 * @param storeAddress
	 * @return boolean
	 * @throws InValidStoreAddressException
	 */
	public boolean isValidStoreAddress(String storeAddress) throws InValidStoreAddressException {

		if (!storeAddress.matches("^[\\\\$#\\\\+{}:\\\\?\\\\.,~@\\\"a-zA-Z0-9 ]+$") || storeAddress.isEmpty()) {
			throw new InValidStoreAddressException("Enter Valid Store Address ");
		}
		return true;

	}

	/**
	 * method for validating the store contact.
	 * 
	 * @param storeContact
	 * @return boolean
	 * @throws InValidStoreContactException
	 */
	public boolean isValidStoreContact(String storeContact) throws InValidStoreContactException {
		if (!storeContact.matches("[1-9][0-9]{9}") || storeContact.isEmpty()) {
			throw new InValidStoreContactException("enter valid contact number with only numbers");
		}

		return true;
	}

	/**
	 * method for validating owner name.
	 * 
	 * @param ownerName
	 * @return boolean
	 * @throws InValidOwnerNameException
	 */
	public boolean isValidOwnerName(String ownerName) throws InValidOwnerNameException {

		if (!(ownerName.matches("^[a-zA-Z]*$")) || ownerName.isEmpty()) {
			throw new InValidOwnerNameException("enter valid name");
		}
		return true;
	}

	


}
