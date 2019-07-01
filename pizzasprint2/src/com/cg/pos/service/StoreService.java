package com.cg.pos.service;

import java.util.ArrayList;

import com.cg.pos.entity.StoreDetailsDTO;
import com.cg.pos.exceptions.InValidAlreadyExistingException;
import com.cg.pos.exceptions.InValidOwnerNameException;
import com.cg.pos.exceptions.InValidStoreAddressException;
import com.cg.pos.exceptions.InValidStoreContactException;
import com.cg.pos.exceptions.InValidStoreNameException;
import com.cg.pos.exceptions.StoreExceptions;

public interface StoreService {
	public boolean addStoreDetails(StoreDetailsDTO storeDetailEntity)
			throws InValidStoreNameException, InValidStoreAddressException, InValidStoreContactException,
			InValidOwnerNameException, InValidStoreAddressException, InValidAlreadyExistingException, StoreExceptions;

	public String deleteStoreDetails(String storeName) throws StoreExceptions;

	public String viewStoreDetails(String storeName) throws StoreExceptions;

	public String ModifyStoreName(int storeId, String storeNmae) throws  StoreExceptions;

	public String ModifyStoreContact(int storeId, String storeContact) throws InValidStoreContactException;

	public String ModifyStoreAddress(int storeId, String storeAddress) throws InValidStoreAddressException;

	public String ModifyOwnerName(int storeId, String ownerName) throws InValidOwnerNameException;
	public ArrayList<StoreDetailsDTO> init();

}
