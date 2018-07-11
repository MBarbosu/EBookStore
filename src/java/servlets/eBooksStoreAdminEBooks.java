package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class eBooksStoreAdminEBooks extends HttpServlet {

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
            // declare specific DB info
            String user = "test" ;
            String password = "test";
            String url = "jdbc:derby://localhost:1527/ebooksStoreMihnea;create=true;";
            String driver = "org.apache.derby.jdbc.ClientDriver";  
            // check push on Insert button
            if (request.getParameter("admin_ebooks_insert") != null) { // insert values from fields
                // set connection paramters to the DB
                // read values from page fields
                String isbn = request.getParameter("admin_ebooks_isbn");
                String denumire = request.getParameter("admin_ebooks_denumire");
                String price= request.getParameter("admin_ebooks_price");
                String pages= request.getParameter("admin_ebooks_pages");
                String id_type= request.getParameter("admin_ebooks_id_type");
                String id_genre= request.getParameter("admin_ebooks_id_genres");
                String id_quality= request.getParameter("admin_ebooks_id_paper_qualities");
                
                // declare specific DBMS operationsvariables
                ResultSet resultSet = null;
                Statement statement = null;
                Connection connection = null;
                PreparedStatement pstmnt = null;
                try
                { 
                    //check driver and create connection
                    Class driverClass = Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password);
                    String DML = "INSERT INTO EBOOKS.EBOOKS VALUES (?, ?, ?, ?, ?, ?, ?)";
                  pstmnt = connection.prepareStatement(DML);
                    
                  pstmnt.setString(1, isbn);
                                pstmnt.setString(2, denumire);
                                pstmnt.setObject(3, id_type);
                                 pstmnt.setObject(4, id_quality);
                               pstmnt.setObject(5, pages);
                                 pstmnt.setObject(6, id_genre);
                                pstmnt.setObject(7, price);
                    
                   pstmnt.execute();//
                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    // display a message for NOT OK
                    Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    if (resultSet != null)
                    {
                        try
                        {
                            resultSet.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (statement != null)
                    {
                        try
                        {
                            statement.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (connection != null)
                    {
                        try
                        {
                            connection.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
                }
            }  // check push on Update button
            else if (request.getParameter("admin_ebooks_update") != null){ // update
                // declare specific variables
                ResultSet resultSet = null;
                Statement statement = null;
                PreparedStatement pstmnt = null;
                Connection connection = null;
                try
                { 
                    //check driver and create connection
                    Class driverClass = Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password);
                    // identify selected checkbox and for each execute the update operation
                    
                    String[] selectedCheckboxes = request.getParameterValues("admin_ebooks_checkbox");
                    
                    String isbn = request.getParameter("admin_ebooks_isbn");
                String denumire = request.getParameter("admin_ebooks_denumire");
                String price= request.getParameter("admin_ebooks_price");
                String pages= request.getParameter("admin_ebooks_pages");
                String id_type= request.getParameter("admin_ebooks_id_type");
                String id_genre= request.getParameter("admin_ebooks_id_genres");
                String id_quality= request.getParameter("admin_ebooks_id_paper_qualities");
                
                   
                  
                        // operate updates for all selected rows
                        connection.setAutoCommit(false);
                        for(String s : selectedCheckboxes){
                                                 
                                                                        
                            {
                                 
                                  String DML = "UPDATE EBOOKS.EBOOKS SET DENUMIRE = ?, ID_TYPE = ?, ID_QUALITY = ?, PAGES = ?, ID_GENRE = ?, PRET = ? WHERE ISBN=?";
                                  pstmnt = connection.prepareStatement(DML);
                    
                                pstmnt.setString(1, denumire);
                                pstmnt.setObject(2, id_type);
                                pstmnt.setObject(3, id_quality);
                                pstmnt.setObject(4, pages);
                                pstmnt.setObject(5, id_genre);
                                pstmnt.setObject(6, price);
                                pstmnt.setString(7, s);
                                
                                 pstmnt.execute();
                            }
                            connection.commit();
                            connection.setAutoCommit(true);
                            
                         
                                     
                        }
                }
    
                catch (ClassNotFoundException | SQLException ex)
                {
                    // display a message for NOT OK
                    Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);

                }
                finally
                {
                    if (resultSet != null)
                    {
                        try
                        {
                            resultSet.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }	
                    if (connection != null)
                    {
                        try
                        {
                            connection.close();
                        }
                        catch (SQLException ex){Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);}
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
                }
            } // check push on Delete button
            else if (request.getParameter("admin_ebooks_delete") != null) { // delete 
                // declare specific variables
                ResultSet resultSet = null;
                PreparedStatement pstmnt = null;
                Connection connection = null;
                try
                {
                    //check driver and create connection
                    Class driverClass = Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password);
                    // identify selected checkbox and for each execute the delete operation
                    String[] selectedCheckboxes = request.getParameterValues("admin_ebooks_checkbox");
                    // more critical DB operations should be made into a transaction
                    connection.setAutoCommit(false);
                    for(String s : selectedCheckboxes){
                        // realize delete of all selected rows
                        String id = s;
                        String DML = "DELETE FROM EBOOKS.EBOOKS WHERE ISBN=?";
                        pstmnt = connection.prepareStatement(DML);
                        pstmnt.setString(1, id);
                        pstmnt.execute();
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                }
                catch (ClassNotFoundException | SQLException ex)
                {
                    Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    if (connection != null){
                        try {
                            connection.rollback();
                        } catch (SQLException ex1) {
                            Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }              
                finally
                {
                    if (resultSet != null)
                    {
                        try
                        {
                            resultSet.close();
                        }
                        catch (SQLException ex){
                            Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){
                            Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (pstmnt != null)
                    {
                        try
                        {
                            pstmnt.close();
                        }
                        catch (SQLException ex){
                            Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (connection != null){
                        try
                        {
                            connection.setAutoCommit(true);
                        }
                        catch (SQLException ex){                          
                            Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        finally{
                            try {
                                connection.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(eBooksStoreAdminUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    // redirect page to its JSP as view
                    request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
                }
            } // check push on Cancel button
            else if (request.getParameter("admin_users_cancel") != null){ // cancel
                request.getRequestDispatcher("./eBooksStoreMainPage.jsp").forward(request, response);
            } // check push on admin user roles button
            else if (request.getParameter("admin_userroles_open") != null) { // insert values from fields
                request.getRequestDispatcher("./eBooksStoreAdminUserRolesPage.jsp").forward(request, response);
            } // check push on admin customers button           
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
        return "Servlet serves eBooksSoreAdminEBooks.JSP page";
    }// </editor-fold>

}
