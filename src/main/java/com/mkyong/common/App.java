package com.mkyong.common;

import com.mkyong.address.dao.AddressDAO;
import com.mkyong.address.model.Address;
import com.mkyong.address.service.AddressService;
import com.mkyong.goods.dao.GoodsDAO;
import com.mkyong.goods.model.Goods;
import com.mkyong.goods.service.GoodsService;
import com.mkyong.orders.dao.OrdersDAO;
import com.mkyong.orders.dao.OrdersGoodsDAO;
import com.mkyong.product.dao.ProductDAO;
import com.mkyong.product.model.Product;
import com.mkyong.product.service.ProductService;
import com.mkyong.shops.dao.ShopsDAO;
import com.mkyong.shops.dao.ShopsInfoDAO;
import com.mkyong.shops.model.Shops;
import com.mkyong.shops.model.ShopsInfo;
import com.mkyong.shops.service.ShopsInfoService;
import com.mkyong.shops.service.ShopsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {
    private DataSource dataSource;

    public static void main( String[] args ) throws SQLException, InterruptedException {
        new App().start();
    }
    public void start() throws SQLException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        AddressDAO addressDAO = (AddressDAO) context.getBean("addressDAO");
        this.dataSource = (DataSource) context.getBean("dataSource");
    //  Address All DAO methods
    //  Add New Address                                      | addressDAO.insert();
    //  Delete Address Hard in DB                            | addressDAO.deleteHard(addressId);
    //  Delete Address Soft in List                          | addressDAO.deleteSoft(addressId);
    //  Get All Address List                                 | addressDAO.getAllAddress();
    //  Get All Deleted Address List                         | addressDAO.getAllDeletedAddress();
    //  Find Address By Id                                   | addressDAO.findByAddressId(addressId);
    //  Get Shop Count By City                               | addressDAO.findCountCity();
    //  Get Shop Count By City Set Min Shop Count            | addressDAO.findCountCity(minCountShopInCity);
    //  Update Address By Id All                             | addressDAO.update(address,addressId);
    //  Update Address By Id (Address Name or City Name)     | addressDAO.update(addressName,cityName,addressId);
    //------------------------------------------------------------------------------------------------------------------------------
        ShopsDAO shopsDAO = (ShopsDAO) context.getBean("shopsDAO");
    //  Shops All DAO methods
    //  Add New Shop                                                     | shopsDAO.insert();
    //  Delete Shop Hard in DB                                           | shopsDAO.deleteHard(shopsId);
    //  Delete Shop Soft in List                                         | shopsDAO.deleteSoft(shopsId);
    //  Get All Shop List                                                | shopsDAO.getAllShops();
    //  Get All Deleted Shop List                                        | shopsDAO.getAllDeletedShops();
    //  Find Shop By Id                                                  | shopsDAO.findByShopsId(shopsId);
    //  Update Shop By Id All                                            | shopsDAO.update(Shops,shopsId);
    //  Update Shop By Id (Shop Name or Shop Address or Shop Info)       | shopsDAO.update(shopName,shopAddressId,shopInfoId,shopsId);
    //------------------------------------------------------------------------------------------------------------------------------
        ShopsInfoDAO shopsInfoDAO = (ShopsInfoDAO) context.getBean("shopsInfoDAO");
    //  Shops Info All DAO methods
    //  Add New Shop Info                                                | shopsInfoDAO.insert();
    //  Delete Shop Info Hard in DB                                      | shopsInfoDAO.deleteHard(shopsInfoId);
    //  Delete Shop Info Soft in List                                    | shopsInfoDAO.deleteSoft(shopsInfoId);
    //  Get All Shop Info List                                           | shopsInfoDAO.getAllShopsInfo();
    //  Get All Deleted Shop Info List                                   | shopsInfoDAO.getAllDeletedShopsInfo();
    //  Find Shop Info By Id                                             | shopsInfoDAO.findByShopsInfoId(shopsInfoId);
    //  Update Shop Info By Id All                                       | shopsInfoDAO.update(ShopsInfo,shopsInfoId);
    //  Update Shop Info By Id (Shop Owner or HVHH or Shop Address)      | shopsInfoDAO.update(shopsOwner,hvhh,addressId,shopsInfoId);
    //------------------------------------------------------------------------------------------------------------------------------
        GoodsDAO goodsDAO = (GoodsDAO) context.getBean("goodsDAO");
    //  Goods All DAO methods
    //  Add New Goods                                                                         | goodsDAO.insert();
    //  Delete Goods Hard in DB                                                               | goodsDAO.deleteHard(goodsId);
    //  Delete Goods Soft in List                                                             | goodsDAO.deleteSoft(goodsId);
    //  Get All Goods List                                                                    | goodsDAO.getAllGoods();
    //  Get All Deleted Goods List                                                            | goodsDAO.getAllDeletedGoods();
    //  Find Goods By Id                                                                      | goodsDAO.findByGoodsId(goodsId);
    //  Update Goods By Id All                                                                | goodsDAO.update(Goods,goodsId);
    //  Update Goods By Id (Goods name or Goods Type or Goods Price Or Goods Product Type)//  | goodsDAO.update(shopsOwner,hvhh,addressId,shopsInfoId);
    //------------------------------------------------------------------------------------------------------------------------------
        OrdersDAO ordersDAO = (OrdersDAO) context.getBean("ordersDAO");
    //  Orders All DAO methods
    //  Add New Orders                                  | ordersDAO.insert();
    //  Delete Orders Hard in DB                        | ordersDAO.deleteHard(ordersId);
    //  Delete Orders Soft in List                      | ordersDAO.deleteSoft(ordersId);
    //  Get All Orders List                             | ordersDAO.getAllOrders();
    //  Get All Deleted Orders List                     | ordersDAO.getAllDeletedOrders();
    //  Find Orders By Id                               | ordersDAO.findByOrdersId(ordersId);
    //  Update Orders By Id All                         | ordersDAO.update(Orders,ordersId);
    //  Update Orders By Id (Orders Shop id)//          | ordersDAO.update(shopsId);
    //------------------------------------------------------------------------------------------------------------------------------
        OrdersGoodsDAO ordersGoodsDAO = (OrdersGoodsDAO) context.getBean("ordersGoodsDAO");
    //  Orders Goods All DAO methods
    //  Add New Orders Goods                                                  | ordersGoodsDAO.insert();
    //  Delete Orders Goods Hard in DB                                        | ordersGoodsDAO.deleteHard(ordersGoodsId);
    //  Delete Orders Goods Soft in List                                      | ordersGoodsDAO.deleteSoft(ordersGoodsId);
    //  Get All Orders Goods List                                             | ordersGoodsDAO.getAllOrdersGoods();
    //  Get All Deleted Orders Goods List                                     | ordersGoodsDAO.getAllDeletedOrdersGoods();
    //  Find Orders Goods By Id                                               | ordersGoodsDAO.findByOrdersGoodsId(ordersGoodsId);
    //  Update Orders Goods By Id All                                         | ordersGoodsDAO.update(OrdersGoods,ordersGoodsId);
    //  Update Orders Goods By Id (Orders Id or Goods Id or Goods Count )//   | ordersGoodsDAO.update(ordersId,goodsId,goodsCount,ordersGoodsId);
    //------------------------------------------------------------------------------------------------------------------------------
        ProductDAO productDAO = (ProductDAO) context.getBean("productDAO");
    //  Product All DAO methods
    //  Add New Product                                             | productDAO.insert();
    //  Delete Product Hard in DB                                   | productDAO.deleteHard(productId);
    //  Delete Product Soft in List                                 | productDAO.deleteSoft(productId);
    //  Get All Product List                                        | productDAO.getAllProduct();
    //  Get All Deleted Product List                                | productDAO.getAllDeletedProduct();
    //  Find Product By Id                                          | productDAO.findByProductId(productId);
    //  Update Product By Id All                                    | productDAO.update(Product,productId);
    //  Update Product By Id (Product Name or Product Type )//      | productDAO.update(productName,productType,productId);
    //------------------------------------------------------------------------------------------------------------------------------

        System.out.println("---------------------All-Address------------------------");

        // Get All Data in Address DB
        AddressService addressService = new AddressService();

        ArrayList<Address> arrayList = addressService.getAllAddress(addressDAO);
        for (Address address : arrayList ) {
            System.out.println(address);
        }
        System.out.println("---------------------All-Shops Info------------------------");

        // Get All Data in Shops Info DB
        ShopsInfoService shopsInfoService = new ShopsInfoService();

        ArrayList<ShopsInfo> allShopsInfo = shopsInfoService.getAllShopsInfo(shopsInfoDAO);
        for (ShopsInfo shopsInfo : allShopsInfo ) {
            System.out.println(shopsInfo);
        }
        System.out.println("-------------------All-Shops--------------------------");

        // Get All Data in Shops DB
        ShopsService shopsService = new ShopsService();

        ArrayList<Shops> allShops = shopsService.getAllShops(shopsDAO);
        for (Shops shops : allShops ) {
            System.out.println(shops);
        }
        System.out.println("---------------------All-Goods------------------------");

        // Get All Data in Goods DB
        GoodsService goodsService = new GoodsService();

        ArrayList<Goods> allGoods = goodsService.getAllGoods(goodsDAO);
        for (Goods goods : allGoods ) {
            System.out.println(goods);
        }
        System.out.println("-------------------All-Product--------------------------");

        // Get All Data in Goods DB
        ProductService productService = new ProductService();

        ArrayList<Product> allProduct = productService.getAllProduct(productDAO);
        for (Product product : allProduct ) {
            System.out.println(product);
        }
        Connection conn;
        conn = dataSource.getConnection();
        conn.setAutoCommit(false);

            int addressIdShop = addressService.addNewAddress(addressDAO,conn);
            System.out.println(addressIdShop);
            int addressIdShopInfo = addressService.addNewAddress(addressDAO,conn);
            System.out.println(addressIdShopInfo);
            int shopInfoId = shopsInfoService.addNewShopsInfo(shopsInfoDAO,conn,addressIdShopInfo);
            System.out.println(shopInfoId);
            int shopsAdd = shopsService.addNewShops(shopsDAO,conn,addressIdShop,shopInfoId);

        if (shopsAdd > 0) {
            conn.commit();
        } else {
            conn.rollback();
        }


    }

}

