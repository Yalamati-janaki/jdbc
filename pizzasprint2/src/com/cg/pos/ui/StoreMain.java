package com.cg.pos.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import com.cg.pos.entity.StoreDetailsDTO;
import com.cg.pos.exceptions.InValidStoreIdException;
import com.cg.pos.service.StoreService;
import com.cg.pos.service.impl.StoreServiceImpl;
import com.cg.pos.staticdb.StoreDetailsStaticDb;

public class StoreMain {

	public static void main(String[] args) {
	
		Scanner s = new Scanner(System.in);
		String choice;
		StoreDetailsDTO storeDetailEntity = new StoreDetailsDTO();
		StoreService storeService = new StoreServiceImpl();
		storeService.init();
		
		do {
			System.out.println("1. ADDING Store Details");
			System.out.println("2. VIEW Store Details");
			System.out.println("3. MODIFY Store Details");
			System.out.println("4. DELETE Store Details");
			System.out.println("5. EXIT");
			System.out.println(" Enter your Choice");
			choice = s.next();
			try {
				switch (choice) {
				case "1":
					System.out.println("Enter Store Name");
					String storeName = s.next();
					System.out.println("Enter Store Address");
					String storeAddress = s.next();
					System.out.println("Enter Store Contact");
					String storeContact = s.next();
					System.out.println("Enter Owner Name");
					String ownerName = s.next();

					storeDetailEntity.setStoreName(storeName);
					storeDetailEntity.setStoreAddress(storeAddress);
					storeDetailEntity.setStoreContact(storeContact);
					storeDetailEntity.setOwnerName(ownerName);
					storeService.addStoreDetails(storeDetailEntity);
					System.out.println(storeName+" is added");
					break;
				case "2":
					System.out.println("Enter the Store Name to view its details");
					String storeNameView = s.next();
					String msg = storeService.viewStoreDetails(storeNameView);
					System.out.println(msg);
					break;
				case "3":
					StoreServiceImpl storeServiceImpl=new StoreServiceImpl();
//					System.out.println("Select ID to which you want to modify the details");
//					System.out.println(StoreDetailsStaticDb.getList());
					System.out.println("enter store ID you want to delete");
					int selectedId = s.nextInt();
//					if (!storeServiceImpl.isValidStoreId(selectedId)) {
//						throw new InValidStoreIdException("Select Proper Id");
//					}
					do {
						System.out.println("1. Modify Store NAME");
						System.out.println("2. Modify Store CONTACT");
						System.out.println("3. Modify Store Address");
						System.out.println("4. Modify OWNER NAME");
						System.out.println("Enter your Choice");
						choice = s.next();
						switch (choice) {
						case "1":
							System.out.println("Enter New Store Name ");
							String newStoreName = s.next();
							String setStoreName = storeService.ModifyStoreName(selectedId, newStoreName);
							System.out.println(setStoreName+" is set to store id "+selectedId);
							break;
						case "2":
							System.out.println("Enter New Store Contact Number");
							String newStoreContact = s.next();
							String setStoreContact = storeService.ModifyStoreContact(selectedId, newStoreContact);
							System.out.println("Your Modified store Contact is : " + setStoreContact);
							break;
						case "3":
							System.out.println("Enter New Store Address");
							String newStoreAddress = s.next();
							String setStoreAddress = storeService.ModifyStoreAddress(selectedId, newStoreAddress);
							System.out.println("Your Modified store Address is : " + setStoreAddress);
							break;
						case "4":
							System.out.println("Enter New Owner Name");
							String newOwnerName = s.next();
							String setOwnerName = storeService.ModifyOwnerName(selectedId, newOwnerName);
							System.out.println("Your Modified Owner Name :" + setOwnerName);
							break;
						default:
							System.err.println("Enter valid number");
							System.out.println("1. Modify Store NAME");
							System.out.println("2. Modify Store CONTACT");
							System.out.println("3. Modify Store Address");
							System.out.println("4. Modify OWNER NAME");
							System.out.println("Enter your Choice");
							choice = s.next();
							break;
						}
						System.out.println("Do you want to Continue your modifications");
						System.out.println(" Enter yes or no");
						choice = s.next();
						if (choice.equals("yes")) {
							choice = "";
						}else {
							choice="0";
						}
					} while (choice != "0");
					break;
				case "4":
					System.out.println("Select NAME of Store you want to delete");
//					System.out.println(StoreDetailsStaticDb.getList());
					String selectedName = s.next();
					String setStoreName = storeService.deleteStoreDetails(selectedName);

					System.out.println(setStoreName);
					break;
				case "5":
					System.out.println("exit");
					System.exit(0);
					break;
				default:
					System.err.println("Enter PROPER Number");
				}

			} catch (Exception e) {
				System.err.println("Error :" + e.getMessage());
			}
			System.out.println("Do you want to Continue");
			System.out.println("yes or no");
			String continueNumber = s.next();
			if (continueNumber.equals("yes")) {
				choice="";
			} else
			{
				System.out.println("EXIT");
				System.exit(0);
			}
		} while (choice != "0");
		s.close();
	}

}
