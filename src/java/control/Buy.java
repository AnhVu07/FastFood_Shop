/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.Dao;
import entity.Item;
import entity.Order;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Buy", urlPatterns = {"/buy"})
public class Buy extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Dao dao = new Dao();
        int quatity = 1;
        int id;
        HttpSession session = request.getSession();
        if (request.getParameter("pid") != null) {
            id = Integer.parseInt(request.getParameter("pid"));
            Product pro = dao.getPDetail(id);
            if (pro != null) {
                if (request.getParameter("quatity") != null) {
                    quatity = Integer.parseInt(request.getParameter("quatity"));
                }
                if (session.getAttribute("order") == null) {
                    Order order = new Order();
                    Item item = new Item();
                    List<Item> listItem = new ArrayList<>();
                    item.setQuatity(quatity);
                    item.setProduct(pro);
                    item.setPrice((long) pro.getPrice());
                    listItem.add(item);
                    order.setItems(listItem);
                    session.setAttribute("order", order);
                    int i = listItem.size();
                    session.setAttribute("number", i);
                } else {
                    Order order = (Order) session.getAttribute("order");
                    List<Item> listItem = order.getItems();
                    boolean check = false;
                    for (Item item : listItem) {
                        if (item.getProduct().getId() == pro.getId()) {
                            item.setQuatity(item.getQuatity() + quatity);
                            check = true;
                        }
                    }

                    if (check == false) {
                        Item item = new Item();
                        item.setQuatity(quatity);
                        item.setProduct(pro);
                        item.setPrice((long) pro.getPrice());
                        listItem.add(item);
                    }
                    session.setAttribute("order", order);
                    int i = listItem.size();
                    session.setAttribute("number", i);
                    double count = 0;
                    for (Item item : listItem) {
                        count += item.getPrice() * item.getQuatity();
                         session.setAttribute("total", count);
                    }
                   
                }
            }
            session.setMaxInactiveInterval(360);
        }
        request.getRequestDispatcher("Order.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
