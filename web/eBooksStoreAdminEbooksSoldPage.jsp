<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="WEB-INF/tlds/astiro.tld" prefix="astiro" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sold Ebooks</title>
        <link rel="stylesheet" type="text/css" href=".\\css\\ebookstore.css">
    </head>
    <body>
         <c:choose>
                <c:when test="${validUser == true}"> 
                    <%@ include file="./utils/eBooksStoreMenu.jsp" %>
             
        
                    <%-- Master view --%>
                        <form action="${pageContext.request.contextPath}/eBooksStoreAdminSoldEbooksServlet" method="POST">
                        <sql:setDataSource 
                        var="snapshot" 
                        driver="org.apache.derby.jdbc.ClientDriver40"
                        url="jdbc:derby://localhost:1527/ebooksstoreMihnea;create=true;"
                        user="test"  
                        password="test"/>
                    <sql:query dataSource="${snapshot}" var="result">
                            SELECT ID, ISNBn_EBBOK, status FROM EBOOKS.ORDERS
                        </sql:query>
                        <table border="1" width="100%">
                            <tr>
                            <td width="4%" class="thc"> select </td>   
                            <td width="12%" class="thc"> ID </td>  
                            <td width="12%" class="thc">ISBN</td>
                            <td width="12%" class="thc">status</td>
                           
                        </table>    
                        <table border="1" width="100%">  
                            </tr>
                            <c:forEach var="row" varStatus="loop" items="${result.rows}">
                            <tr>
                                <td width="4%" class="tdc"><input type="checkbox" name="admin_orders_checkbox" value="${row.id}"></td>
                                <td width="12%" class="tdc"><c:out value="${row.id}"/></td>
                                <td width="12%" class="tdc"><c:out value="${row.isnbn_ebbok}"/></td>
                                <td width="12%" class="tdc"><c:out value="${row.status}"/></td>
                                
                            </tr>
                            </c:forEach>
                            
                            
                        </table>
                            <table border="1" width="100%"> 
                                    <tr>
                                         
                                        <td >ID: </td><td><input class = "inputlarge" type="text" name="admin_sold_ebooks_new_id"></input></td>
                                        <td >ISBN: </td><td><input class = "inputlarge" type="text" name="admin_sold_ebooks_new_isbn"></input></td>
                                        <td >Status: </td><td><input class=" inputlarge" type =" text" name="admin_sold_ebooks_new_status"</td>
                                        <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_sold_ebooks_new" value="New"></td>,</tr>
                                    <tr> <td></td><td></td><td></td><td></td><td></td><td></td>
                                        <td class="tdc"><input type="submit" class="ebooksstorebutton" name="admin_sold_ebooks_delete" value="Delete"></td> 
                                       
                                    </tr>     
                            </table>
                        </form>
              </c:when>
        
        
        
            <c:otherwise>
                <c:redirect url="./Index.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>
                
        
    </body>
</html>
