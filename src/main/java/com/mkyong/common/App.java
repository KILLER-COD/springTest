package com.mkyong.common;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.product.dao.ProductDAO;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.dao.ShopsInfoDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;

public class App {

    public static void main( String[] args ) throws SQLException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");
    //  Address All DAO methods
    //  Add New Address                                      | addressDAO.insert();
    //  Delete Address Hard in DB                            | addressDAO.deleteHard(addressID);
    //  Delete Address Soft in List                          | addressDAO.deleteSoft(addressID);
    //  Get All Address List                                 | addressDAO.getAllAddress();
    //  Get All Deleted Address List                         | addressDAO.getAllDeletedAddress();
    //  Find Address By ID                                   | addressDAO.findByAddressId(addressId);
    //  Get Shop Count By City                               | addressDAO.findCountCity();
    //  Get Shop Count By City Set Min Shop Count            | addressDAO.findCountCity(minCountShopInCity);
    //  Update Address By ID All                             | addressDAO.update(address,addressId);
    //  Update Address By ID (Address Name or City Name)     | addressDAO.update(addressName,cityName,addressId);
    //------------------------------------------------------------------------------------------------------------------------------
        ShopsDAO shopsDAO = (ShopsDAO) context.getBean("shopsDAO");
    //  Shops All DAO methods
    //  Add New Shop                                                | addressDAO.insert();
    //  Delete Shop Hard in DB                                      | addressDAO.deleteHard(shopsID);
    //  Delete Shop Soft in List                                    | addressDAO.deleteHard(shopsID);
    //  Get All Shop List                                           | addressDAO.getAllAddress();
    //  Get All Deleted Shop List                                   | addressDAO.getAllDeletedAddress();
    //  Find Shop By ID                                             | addressDAO.findByAddressId(shopsID);
    //  Update Shop By ID All                                       | addressDAO.update(Address,shopsID);
    //  Update Shop By ID (Shop Name or Shop Address or Shop Info)  | addressDAO.update(shopName,shopAddressId,shopInfoId,shopsID);
    //------------------------------------------------------------------------------------------------------------------------------
        ShopsInfoDAO shopsInfoDAO = (ShopsInfoDAO) context.getBean("shopsInfoDAO");


        GoodsDAO goodsDAO = (GoodsDAO) context.getBean("goodsDAO");
        OrdersDAO ordersDAO = (OrdersDAO) context.getBean("ordersDAO");
        OrdersGoodsDAO ordersGoodsDAO = (OrdersGoodsDAO) context.getBean("ordersGoodsDAO");
        ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");


        AddressService addressService = new AddressService();

        ArrayList<Address> arrayList = addressService.getAllAddress(addressDAO);

        for (Address address : arrayList ) {
            System.out.println(address);
        }

//        addressService.countShopCity(addressDAO);

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Set minimum count shop in city");
//        Integer minCountShop = scanner.nextInt();

//        ArrayList<AddressCountByCity> addressCountByCitie = addressDAO.findCountCity();
//        ArrayList<AddressCountByCity> addressCountByCitie1 = addressDAO.findCountCity(minCountShop);

//        for (AddressCountByCity a :
//                addressCountByCitie1) {
//            System.out.println("count = " + a.getCount() +" | " + "city = " + a.getCity() );
//        }

//        System.out.println("----------------");
//        addressService.changeAddress(addressDAO);

//        addressService.addNewAddress(addressDAO);
//        System.out.println("----------------");
//        ArrayList<Address> arrayList0 = addressService.getAllAddress(addressDAO);
//
//        for (Address address : arrayList0 ) {
//            System.out.println(address);
//        }
//        System.out.println("----------------");

//        ArrayList<Address> arrayList1 = addressService.getAllAddress(addressDAO);

//        for (Address address : arrayList1 ) {
//            System.out.println(address);
//        }
//        System.out.println("----------------");
//        addressService.deleteAddress(addressDAO,14);
//        System.out.println("----------------");
//        ArrayList<Address> arrayList3 = addressService.getAllAddress(addressDAO);
//
//        for (Address address : arrayList3 ) {
//            System.out.println(address);
//        }

    }



}

