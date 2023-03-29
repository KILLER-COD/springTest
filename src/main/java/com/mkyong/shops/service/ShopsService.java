package com.mkyong.shops.service;

import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import com.mkyong.shops.dao.ShopDAO;
import com.mkyong.shops.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ShopsService {
    private final ShopDAO shopDAO;
    private final AddressService addressService;
    private final ShopsPersonContactService personContactService;
    private final ShopsPersonDataService personDataService;
    private final ShopsInfoService shopsInfoService;
//    private final ShopContactingInfo contactingInfo;

    public List<Shop> getAllShops() throws SQLException {
        return shopDAO.getAllShops();
    }

    public List<Shop> getAllDeletedShops() throws SQLException {
        return shopDAO.getAllDeletedShops();
    }

    public Shop findByShopsId(int id) throws SQLException {
        return shopDAO.findByShopsId(id);
    }

    public void deleteShops(int shopsId) throws SQLException {
        Shop shop = findByShopsId(shopsId);
        shopsInfoService.deleteShopsInfo(shop.getShopInfoId());
        addressService.deleteAddress(shop.getShopAddressId());
        shopDAO.deleteSoft(shopsId);
    }

    public int addNewShops(ShopAllData shopAllData, Connection conn) {
        Address shopInfoAddress = Address.builder()
                .address(shopAllData.getShopInfoAddress())
                .city(shopAllData.getShopInfoCity())
                .build();
        Address shopAddress = Address.builder()
                .address(shopAllData.getShopAddress())
                .city(shopAllData.getShopCity())
                .build();
        int shopAddressId = addressService.addNewAddress(shopAddress);
        int shopInfoAddressId = addressService.addNewAddress(shopInfoAddress);

        ShopInfo shopInfo = ShopInfo.builder()
                .shopOwner(shopAllData.getShopOwner())
                .hvhh(shopAllData.getHvhh())
                .addressId(shopInfoAddressId)
                .build();
        int newShopsInfoId = shopsInfoService.addNewShopsInfo(shopInfo);


        Shop shop = Shop.builder()
                .shopName(shopAllData.getShopName())
                .shopAddressId(shopAddressId)
                .shopInfoId(newShopsInfoId)
                .createDate(new Date(System.currentTimeMillis()))
                .modifyDate(new Date(System.currentTimeMillis()))
                .build();
        int shopId = shopDAO.insert(shop, conn);
        System.out.println(shopAllData.getPersonPhone().size());
        for (int j = 0; j <= shopAllData.getPersonPhone().size() - 1; j++) {
            ShopPersonData personData = ShopPersonData.builder()
                    .shopId(shopId)
                    .personName(shopAllData.getPersonName()[j])
                    .personEmail(shopAllData.getPersonEmail()[j])
                    .build();
            int newPersonDataId = personDataService.create(personData, null);
            Arrays.stream(shopAllData.getPersonPhone().get(j)).forEach(
                    phoneArray -> {
                        ShopPersonContact personContact = ShopPersonContact.builder()
                                .shopPersonDataId(newPersonDataId)
                                .phone(phoneArray)
                                .build();
                        personContactService.create(personContact, null);
                    }
            );
        }
        return shopId;
    }

    public void changeShops(ShopAllData shopAllData, int shopsId, Connection conn) throws SQLException {
        shopDAO.update(checkedUpdateShops(shopAllData, shopsId), conn);
    }

    public void shopsPrint() {
        try {
            shopDAO.getAllShops().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(int shopsId) {
        Shop retInfo = shopDAO.findByShopsId(shopsId);
        return retInfo != null;
    }

    public Shop checkedUpdateShops(ShopAllData shopAllData, int shopId) throws SQLException {
        //Checked Change Shops name -------------------------------------------------
        Shop shop = findByShopsId(shopId);
        shop.setShopName(shopAllData.getShopName());

        Address shopInfoAddress = Address.builder()
                .address(shopAllData.getShopInfoAddress())
                .city(shopAllData.getShopInfoCity())
                .build();
        Address shopAddress = Address.builder()
                .address(shopAllData.getShopAddress())
                .city(shopAllData.getShopCity())
                .build();
        ShopInfo shopInfo = ShopInfo.builder()
                .shopOwner(shopAllData.getShopOwner())
                .hvhh(shopAllData.getHvhh())
                .build();

        int addressShopsId = shop.getShopAddressId();
        addressService.changeAddress(shopAddress, addressShopsId);

        int addressShopsInfoId = shopsInfoService.findByShopInfoId(shop.getShopInfoId()).getAddressId();
        addressService.changeAddress(shopInfoAddress, addressShopsInfoId);

        shopsInfoService.changeShopsInfo(shopInfo, shop.getShopInfoId(), null);

        testGetPersonDataAndPhoneData(shopId);

        return shop;
    }

    public List<ShopInfo> getAllShopsInfo() throws SQLException {
        return shopsInfoService.getAllShopsInfo();
    }

    public void testGetPersonDataAndPhoneData(int shopId) throws SQLException {
        List<ShopPersonData> personDataList = personDataService.findAllByIdShopId(shopId);
        //Update and Create Person Checking

        //Test Post Person Data Request
        List<ShopPersonData> testPersonDataList = new ArrayList<>();
        testPersonDataList.add(ShopPersonData.builder()
                .id(22)
                .shopId(33)
                .personName("Aramais")
                .personEmail("aram225@mail.ru")
                .build()
        );
        testPersonDataList.add(ShopPersonData.builder()
                .id(0)
                .shopId(33)
                .personName("Aram")
                .personEmail("aram225@mail.ru")
                .build()
        );
        testPersonDataList.add(ShopPersonData.builder()
                .id(0)
                .shopId(33)
                .personName("Garik")
                .personEmail("Garik225@mail.ru")
                .build()
        );

        testPersonDataList.add(ShopPersonData.builder()
                .id(0)
                .shopId(33)
                .personName("Vahag")
                .personEmail("Vahag22@mail.ru")
                .build()
        );

        List<ShopPersonData> deletedPersonDataList = new ArrayList<>(personDataList);
        Set<ShopPersonData> createdPersonDataList = new HashSet<>();


        personDataList.forEach(
                personData -> {
                    testPersonDataList.forEach(
                            testPersonData -> {
                                if (personData.getId() == testPersonData.getId()) {
                                    try {
                                        personDataService.changeShopPersonData(testPersonData, null);
                                        deletedPersonDataList.remove(personData);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    if (testPersonData.getId() == 0) {
                                        createdPersonDataList.add(testPersonData);
                                    }
                                }
                            }
                    );

                }
        );
        //Delete Person Data Checking
        deletedPersonDataList.forEach(
                personData -> {
                    try {
                        personDataService.deleteSoft(personData.getId());
                        personContactService.deleteSoftByShopPersonDataId(personData.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        //Create Person Data Checking
        createdPersonDataList.forEach(
                personData -> {
                    personDataService.create(personData, null);
                }
        );

        //Show Changes List
        List<ShopPersonData> newPersonDataList = personDataService.findAllByIdShopId(shopId);
        newPersonDataList.forEach(System.out::println);
        System.out.println("--------------------------");
        for (int j = 0; j <= newPersonDataList.size() - 1; j++) {
            ShopPersonData personData = ShopPersonData.builder()
                    .id(newPersonDataList.get(j).getId())
                    .shopId(shopId)
                    .personName(newPersonDataList.get(j).getPersonName())
                    .personEmail(newPersonDataList.get(j).getPersonEmail())
                    .build();
            System.out.println(personData);
            List<ShopPersonContact> newPersonContactList = personContactService.findByShopPersonDataId(newPersonDataList.get(j).getId());
            (newPersonContactList).forEach(
                    newPhoneArray -> {
                        ShopPersonContact personContact = ShopPersonContact.builder()
                                .id(newPhoneArray.getId())
                                .shopPersonDataId(newPhoneArray.getShopPersonDataId())
                                .phone(newPhoneArray.getPhone())
                                .build();
                        System.out.println(personContact);
                    }
            );
            System.out.println("------------------");
        }
    }

}
