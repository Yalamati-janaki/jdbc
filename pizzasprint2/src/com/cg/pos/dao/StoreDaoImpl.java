package com.cg.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cg.pos.entity.StoreDetailsDTO;
import com.cg.pos.exceptions.StoreExceptions;
import com.cg.pos.utility.Connect;
import com.cg.pos.utility.ExceptionMessages;
import com.cg.pos.utility.Query;

public class StoreDaoImpl implements StoreDao {

	@Override
	public boolean addStoreDb(StoreDetailsDTO storeDetailsDTO) throws StoreExceptions {

		Connection con = Connect.getConnection();
		String sql = Query.addStore;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, storeDetailsDTO.getStoreName());
			preparedStatement.setString(2, storeDetailsDTO.getStoreAddress());
			preparedStatement.setString(3, storeDetailsDTO.getStoreContact());
			preparedStatement.setString(4, storeDetailsDTO.getOwnerName());
			int resultset = preparedStatement.executeUpdate();
			if(resultset==1) {
				return true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			throw new StoreExceptions(ExceptionMessages.message3);
		}
		return false;

	}

	@Override
	public StoreDetailsDTO viewStoreDb(String storeName) throws StoreExceptions {
		
		Connection con = Connect.getConnection();
		String sql = Query.viewStore;
		StoreDetailsDTO storeDetailsDTO =new StoreDetailsDTO();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,storeName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if(resultSet.getString(2).equals(storeName)) {
					storeDetailsDTO.setStoreId(resultSet.getInt(1));
					storeDetailsDTO.setStoreName(resultSet.getString(2));
					storeDetailsDTO.setStoreAddress(resultSet.getString(3));
					storeDetailsDTO.setStoreContact(resultSet.getLong(4) + "");
					storeDetailsDTO.setOwnerName(resultSet.getString(5));
				}
			}
			preparedStatement.close();
		} catch (SQLException e) {
			throw new StoreExceptions(ExceptionMessages.message3);
		}
		return storeDetailsDTO;
			
	}

	@Override
	public boolean deleteStoreDb(String storeName) throws StoreExceptions {
		Connection con = Connect.getConnection();
		String sql = Query.deleteStore;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,storeName);
			int resultSet = preparedStatement.executeUpdate();
			if(resultSet==1) {
				return true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			throw new StoreExceptions(ExceptionMessages.message4);
		}
		return false;
	}

	@Override
	public boolean modifyStoreNameDb(String storeName,int storeId) throws StoreExceptions {
		Connection con = Connect.getConnection();
		String sql = Query.modifyStoreName;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,storeName);
			preparedStatement.setInt(2, storeId);
			int resultSet = preparedStatement.executeUpdate();
			if(resultSet==1) {
				return true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			throw new StoreExceptions(ExceptionMessages.message4);
		}
		return false;
	}

}
