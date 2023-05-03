/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DB_Connect;
import entity.Account;
import entity.Category;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Dao {

    List<Product> listP = new ArrayList<Product>();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getListP() {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from product";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product e = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getDouble("price"), rs.getString("title"), rs.getString("description"));
                listP.add(e);
            }
        } catch (Exception e) {

        }
        return listP;

    }

    public List<Category> getListC() {
        List<Category> listC = new ArrayList<>();
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from category";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"));
                listC.add(c);
            }
        } catch (Exception e) {

        }
        return listC;

    }

    public Product getLastP() {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select top 1 * from product order by id desc";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getDouble("price"), rs.getString("title"), rs.getString("description"));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public List<Product> getListPByCategory(String cateID) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from product where cateID = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, cateID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product e = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getDouble("price"), rs.getString("title"), rs.getString("description"));
                listP.add(e);
            }
        } catch (Exception e) {

        }
        return listP;

    }

    public List<Product> getListPByIDSell(int sid) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from product where sell_ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, sid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product e = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getDouble("price"), rs.getString("title"), rs.getString("description"));
                listP.add(e);
            }
        } catch (Exception e) {

        }
        return listP;

    }

    public Product getPDetail(int id) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from product where id =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getDouble("price"), rs.getString("title"), rs.getString("description"));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public List<Product> getListPByFind(String search) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from product where name like ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product e = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("image"), rs.getDouble("price"), rs.getString("title"), rs.getString("description"));
                listP.add(e);
            }
        } catch (Exception e) {

        }
        return listP;

    }

    public Account getAccount(String user, String pass) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from Account where [user] =? and pass=?";
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("uID"), rs.getString("user"), rs.getString("pass"), rs.getInt("isSell"), rs.getInt("isAdmin"));

            }
        } catch (Exception e) {

        }
        return null;
    }

    public Account getUser(String user) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from Account where [user] =? ";
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("uID"), rs.getString("user"), rs.getString("pass"), rs.getInt("isSell"), rs.getInt("isAdmin"));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void Register(String user, String pass) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "insert into Account values(?,?,0,0)";
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void insertAcc(String user, String pass, int isSell, int isAdmin) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "insert into Account([user],pass,isSell,isAdmin) values(?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, isSell);
            ps.setInt(4, isAdmin);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<Account> getAllAcc() {
        List<Account> list = new ArrayList<>();
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from Account";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account e = new Account(rs.getInt("uID"), rs.getString("user"), rs.getString("pass"), rs.getInt("isSell"), rs.getInt("isAdmin"));
                list.add(e);
            }
        } catch (Exception e) {

        }
        return list;

    }

    public void EditAcc(String user, String pass, int isSell, int isAdmin, int id) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "update Account set [user] =?, pass =?, isSell=?, isAdmin=? where [uID] = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, isSell);
            ps.setInt(4, isAdmin);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public Account getAccById(int id) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "select * from Account where [uID] = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("uID"), rs.getString("user"), rs.getString("pass"), rs.getInt("isSell"), rs.getInt("isAdmin"));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public void DeleteAcc(int id) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "delete from Account where [uID] =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void DeleteProduct(String id) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "delete from product where id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void InsertProduct(String name, String image, String price, String title, String description, String category, int sellID) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "insert into product(name,image,price,title,description,cateID,sell_ID) values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setInt(7, sellID);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void EditProduct(String name, String image, String price, String title, String description, String category, String id) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "update product set [name]=?,[image]=?,price=?,title=?,[description]=?,cateID=? where id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void InsertProductInCart(int accountID, String productID, int amount) {
        try {
            con = new DB_Connect().getConnecttion();
            String query = "insert into Cart(AccountID,ProductID,Amount) values(?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setString(2, productID);
            ps.setInt(3, amount);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

}
