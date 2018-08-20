<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>Book Store</title>

</head>
<body>

<table style="width:100%;border:1px white solid">  
    <tr bgcolor="#4F81BD"style="color: #fff;">  
 		<td>姓名</td>
 		<td>图片</td>
 		<td>书名</td>
 		<td>数量</td>
 		<td>总价</td>
<!--  		<td width="600px">留言内容</td> -->
 		<td>日期</td>
 		<td>操作</td>
    </tr>  
	<c:forEach items="${order}" var="orders">
	<tr bgcolor="#4F81BD"style="color: #fff;">  
		<td>${orders.users.userName }</td>
		<td><a href="details"><img height="60px" width="40px" src="bookImage/${orders.book.bookImages }" alt="" title="" border="0" class="cart_thumb" /></a></td>
        <td>${orders.book.bookName}</td>
        <td>${orders.bookCount}</td>
        <td>${orders.bookPrice}</td>
        <td>${orders.orderTime}</td>
 		<td><a onClick="return confirm('确定删除吗?');" href="/Book/deleteOrder?orderid=${orders.orderId }" class="checkout">删除</a></td>
 	</tr>
	</c:forEach>
</table>  
</body>
</html>