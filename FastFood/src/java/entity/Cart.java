/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.TreeMap;

/**
 *
 * @author Admin
 */
public class Cart {

    private int accountID;
    private TreeMap<Product, Integer> listProduct;

    public Cart() {
        listProduct = new TreeMap<Product, Integer>();
    }

    public Cart(int accountID, TreeMap<Product, Integer> listProduct) {
        this.accountID = accountID;
        this.listProduct = listProduct;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public TreeMap<Product, Integer> getListProduct() {
        return listProduct;
    }

    public void setListProduct(TreeMap<Product, Integer> listProduct) {
        this.listProduct = listProduct;
    }

    public void insetrToCart(Product p, int quatity) {
        boolean check = listProduct.containsKey(p);
        if (check) {
            int quatity_old = listProduct.get(p);
            quatity += quatity_old;
            listProduct.put(p, quatity);
        } else {
            listProduct.put(p, quatity);
        }
    }
}
