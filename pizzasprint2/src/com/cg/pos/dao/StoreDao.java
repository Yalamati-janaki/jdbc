package com.cg.pos.dao;

import com.cg.pos.entity.StoreDetailsDTO;
import com.cg.pos.exceptions.StoreExceptions;

public interface StoreDao {

	boolean addStoreDb(StoreDetailsDTO storeDetailEntity) throws StoreExceptions;

	StoreDetailsDTO viewStoreDb(String storeName) throws StoreExceptions ;

	boolean deleteStoreDb(String storeName) throws StoreExceptions;

	boolean modifyStoreNameDb(String storeName, int storeId) throws StoreExceptions;

//	String checkDb(String storeName) throws StoreExceptions;

}
