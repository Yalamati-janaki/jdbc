package com.cg.pos.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.cg.pos.dao.StoreDao;
import com.cg.pos.dao.StoreDaoImpl;
import com.cg.pos.entity.StoreDetailsDTO;
import com.cg.pos.exceptions.InValidAlreadyExistingException;
import com.cg.pos.exceptions.InValidOwnerNameException;
import com.cg.pos.exceptions.InValidStoreAddressException;
import com.cg.pos.exceptions.InValidStoreContactException;
import com.cg.pos.exceptions.InValidStoreIdException;
import com.cg.pos.exceptions.InValidStoreNameException;
import com.cg.pos.exceptions.StoreExceptions;
import com.cg.pos.service.StoreService;
import com.cg.pos.staticdb.StoreDetailsStaticDb;
import com.cg.pos.utility.ExceptionMessages;
import com.cg.pos.utility.ValidateStoreDetails;

/**
 * class for adding, deleting, viewing, modifying the store details.
 * 
 * @author trainee
 *
 */
public class StoreServiceImpl implements StoreService {

	StoreDetailsDTO storeDetailEntity = new StoreDetailsDTO();
	ArrayList<StoreDetailsDTO> list1;

	/**
	 * method for adding store details to list.
	 * @throws StoreExceptions 
	 */
	@Override
	public boolean addStoreDetails(StoreDetailsDTO storeDetailEntity)
			throws InValidStoreNameException, InValidStoreAddressException, InValidStoreContactException,
			InValidOwnerNameException, InValidAlreadyExistingException, StoreExceptions {
		
		ValidateStoreDetails validateStoreDetails = new ValidateStoreDetails();
		String storeName = storeDetailEntity.getStoreName();
		boolean validatedStorename = validateStoreDetails.isValidStoreName(storeName);

		String storeContact = storeDetailEntity.getStoreContact();
		boolean validatedStoreContact = validateStoreDetails.isValidStoreContact(storeContact);

		String storeAddress = storeDetailEntity.getStoreAddress();
		boolean validatedStoreAddress = validateStoreDetails.isValidStoreAddress(storeAddress);

		String ownerName = storeDetailEntity.getOwnerName();
		boolean validatedOwnerName = validateStoreDetails.isValidOwnerName(ownerName);
		boolean addedDetails = false;
		if (validatedStorename && validatedStoreContact && validatedStoreAddress && validatedOwnerName) {
//			if (isValidDbCheck(storeName, storeContact)) {
//				StoreDetailsStaticDb.addDetails(storeDetailEntity);
//			}
			StoreDao storeDao=new StoreDaoImpl();
			addedDetails=storeDao.addStoreDb(storeDetailEntity);
			if(!addedDetails) {
				throw new StoreExceptions(ExceptionMessages.message3);
			}
		}
		return addedDetails;
	}

	/**
	 * method for validating whether the store name and contact exists in database.
	 * 
	 * @param storeName
	 * @param storeContact
	 * @return boolean
	 * @throws InValidAlreadyExistingException
	 * @throws InValidStoreNameException 
	 * @throws InValidStoreContactException 
	 * @throws StoreExceptions 
	 */
	public boolean isValidDbCheck(String storeName, String storeContact) throws InValidAlreadyExistingException, InValidStoreNameException, InValidStoreContactException, StoreExceptions {
		ArrayList<StoreDetailsDTO> list = StoreDetailsStaticDb.getList();
		Iterator<StoreDetailsDTO> i = list.iterator();
		ValidateStoreDetails validateStoreDetails=new ValidateStoreDetails();
		validateStoreDetails.isValidStoreName(storeName);
		validateStoreDetails.isValidStoreContact(storeContact);
		while (i.hasNext()) {
			StoreDetailsDTO store = (StoreDetailsDTO) i.next();
			String dbName = store.getStoreName();
			String dbcontact = store.getStoreContact();
			if (storeName.equals(dbName)) {
				throw new InValidAlreadyExistingException(" Enter New Details because store name alreday existing");
			}
			if (storeContact.equals(dbcontact)) {
				throw new InValidAlreadyExistingException(" Enter New Details because store contact alreday existing");
			}
		}
		return true;

	}

	/**
	 * Method for deleting store details from list.
	 * @throws InValidStoreNameException 
	 * @throws StoreExceptions 
	 */
	@Override
	public String deleteStoreDetails(String storeName) throws StoreExceptions {

		ValidateStoreDetails validateStoreDetails=new ValidateStoreDetails();
		validateStoreDetails.isValidStoreName(storeName);
//		Iterator<StoreDetailsDTO> i = list1.iterator();
//		while (i.hasNext()) {
//			StoreDetailsDTO store = (StoreDetailsDTO) i.next();
//			String dbName = store.getStoreName();
//			if (storeName.equals(dbName)) {
//				i.remove();
//			}
//		}
		StoreDao storeDao=new StoreDaoImpl();
		boolean addedDetails = storeDao.deleteStoreDb(storeName);
		if(!addedDetails) {
			throw new StoreExceptions(ExceptionMessages.message4);
		}
		return addedDetails + " is deleted ";
	}

	/**
	 * method for viewing store details.
	 * @throws InValidStoreNameException 
	 * @throws StoreExceptions 
	 * @throws SQLException 
	 */
	@Override
	public String viewStoreDetails(String storeName) throws StoreExceptions {

//		list1 = StoreDetailsStaticDb.getList();
		ValidateStoreDetails validateStoreDetails=new ValidateStoreDetails();
		validateStoreDetails.isValidStoreName(storeName);
		
//		Iterator<StoreDetailsDTO> i = list1.iterator();
//		while (i.hasNext()) {
//			StoreDetailsDTO store = (StoreDetailsDTO) i.next();
//			String dbName = store.getStoreName();
//			if (storeName.equals(dbName)) {
//
//				return "Selected Store Details : [ store Name " + store.getStoreName() + ", StoreId :"
//						+ store.getStoreId() + ", Contact :" + store.getStoreContact() + ", Address :"
//						+ store.getStoreAddress() + ", Owner name :" + store.getOwnerName() + " ]";
//			}
//		}
		StoreDao storeDao=new StoreDaoImpl();
		StoreDetailsDTO addedDetails = storeDao.viewStoreDb(storeName);
		if(addedDetails.getStoreName()==null) {
			throw new StoreExceptions(ExceptionMessages.message2);
		}
		return "" + addedDetails;
	}

	/**
	 * method for modifying store name.
	 * @throws StoreExceptions 
	 */
	@Override
	public String ModifyStoreName(int storeId, String storeName) throws StoreExceptions {

//		list1 = StoreDetailsStaticDb.getList();
		ValidateStoreDetails validateStoreDetails=new ValidateStoreDetails();
		validateStoreDetails.isValidStoreName(storeName);
//		Iterator<StoreDetailsDTO> i = list1.iterator();
//		while (i.hasNext()) {
//			StoreDetailsDTO store = (StoreDetailsDTO) i.next();
//			int dbId = store.getStoreId();
//			if (storeId == dbId) {
//				store.setStoreName(storeName);
//				return "For " + dbId + " store name" + storeName + " is set ";
//			}
//		}
		StoreDao storeDao=new StoreDaoImpl();
		boolean addedDetails = storeDao.modifyStoreNameDb(storeName,storeId);
		if(!addedDetails) {
			throw new StoreExceptions(ExceptionMessages.message2);
		}
		return storeName;
	}

	/**
	 * method for modifying store contact.
	 */
	@Override
	public String ModifyStoreContact(int storeId, String storeContact) throws InValidStoreContactException {

		list1 = StoreDetailsStaticDb.getList();
		ValidateStoreDetails validateStoreDetails=new ValidateStoreDetails();
		validateStoreDetails.isValidStoreContact(storeContact);
		Iterator<StoreDetailsDTO> i = list1.iterator();
		while (i.hasNext()) {
			StoreDetailsDTO store = (StoreDetailsDTO) i.next();
			int dbId = store.getStoreId();
			if (storeId == dbId) {
				store.setStoreContact(storeContact);
				return "For " + dbId + " store name " + storeContact + " is set ";
			}
		}
		return "entered valid";
	}

	/**
	 * method for modifying store address.
	 */
	@Override
	public String ModifyStoreAddress(int storeId, String storeAddress) throws InValidStoreAddressException {

		list1 = StoreDetailsStaticDb.getList();
		ValidateStoreDetails validateStoreDetails=new ValidateStoreDetails();
		validateStoreDetails.isValidStoreAddress(storeAddress);
		Iterator<StoreDetailsDTO> i = list1.iterator();
		while (i.hasNext()) {
			StoreDetailsDTO store = (StoreDetailsDTO) i.next();
			int dbId = store.getStoreId();
			if (storeId == dbId) {
				store.setStoreContact(storeAddress);
				return "For " + dbId + " store name" + storeAddress + " is set ";
			}
		}
		return "Entered valid";
	}

	/**
	 * method for modifying owner name.
	 */
	@Override
	public String ModifyOwnerName(int storeId, String ownerName) throws InValidOwnerNameException {

		list1 = StoreDetailsStaticDb.getList();
		ValidateStoreDetails validateStoreDetails=new ValidateStoreDetails();
		validateStoreDetails.isValidOwnerName(ownerName);
		Iterator<StoreDetailsDTO> i = list1.iterator();
		while (i.hasNext()) {
			StoreDetailsDTO store = (StoreDetailsDTO) i.next();
			int dbId = store.getStoreId();
			if (storeId == dbId) {
				store.setStoreContact(ownerName);
				return "For " + dbId + " store name" + ownerName + " is set ";
			}
		}
		return "Entered valid";
	}

	/**
	 * method for validating the store id.
	 * 
	 * @param storeId
	 * @return boolean
	 * @throws InValidStoreIdException
	 */
//	public boolean isValidStoreId(int storeId) throws InValidStoreIdException {
//		String id = Integer.toString(storeId);
//		if (!id.matches("[0-9]{5}")) {
//			throw new InValidStoreIdException("Enter only numbers");
//		} else {
//			ArrayList<StoreDetailsDTO> list;
//			list = StoreDetailsStaticDb.getList();
//			Iterator<StoreDetailsDTO> i = list.iterator();
//			while (i.hasNext()) {
//				StoreDetailsDTO store = (StoreDetailsDTO) i.next();
//				if (storeId == store.getStoreId()) {
//					return true;
//				}
//			}
//			return false;
//		}
//	}

	@Override
	public ArrayList<StoreDetailsDTO> init() {
		StoreDetailsStaticDb storeDetailsStaticDb=new StoreDetailsStaticDb();
		storeDetailsStaticDb.init();
		return StoreDetailsStaticDb.getList();
		
		
	}

}
