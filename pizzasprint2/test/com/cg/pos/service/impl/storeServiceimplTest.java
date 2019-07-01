package com.cg.pos.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.cg.pos.exceptions.InValidStoreContactException;
import com.cg.pos.exceptions.InValidStoreNameException;
import com.cg.pos.exceptions.StoreExceptions;
import com.cg.pos.service.StoreService;

public class storeServiceimplTest {

	private StoreService storeService;

	@Before
	public void setUp() throws Exception {
		storeService = new StoreServiceImpl();
	}

	@Test(expected = InValidStoreNameException.class)
	public void testStoreNameHasNumber() throws StoreExceptions {
		String actual = storeService.deleteStoreDetails("67576");
		assertEquals("Enter Valid Store Name in alphabets within the length of 15 ", actual);
	}

	@Test(expected = InValidStoreNameException.class)
	public void testStoreNameHasSplChar() throws StoreExceptions {
		String actual = storeService.deleteStoreDetails("@#$%^&");
		assertEquals("Enter Valid Store Name in alphabets within the length of 15 ", actual);
	}

	@Test(expected = InValidStoreContactException.class)
	public void testStoreContactHasAlphabet() throws InValidStoreNameException, InValidStoreContactException {
		String actual = storeService.ModifyStoreContact(10101, "dfhgj");
		assertEquals("enter valid contact number with only numbers", actual);
	}

	@Test(expected = InValidStoreContactException.class)
	public void testStoreContactHasSplChar() throws InValidStoreNameException, InValidStoreContactException {
		String actual = storeService.ModifyStoreContact(10101, "#$%^&");
		assertEquals("enter valid contact number with only numbers", actual);
	}

	@Test(expected = InValidStoreContactException.class)
	public void testStore() throws InValidStoreNameException, InValidStoreContactException {
		String actual = storeService.ModifyStoreContact(10101, "#$%^&");
		assertEquals("enter valid contact number with only numbers", actual);
	}

}
