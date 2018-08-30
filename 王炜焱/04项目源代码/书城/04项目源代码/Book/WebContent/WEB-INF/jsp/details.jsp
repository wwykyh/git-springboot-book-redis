<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="jsp/style.css" />
	<link rel="stylesheet" href="jsp/lightbox.css" type="text/css" media="screen" />
	
	<script src="js/prototype.js" type="text/javascript"></script>
	<script src="js/scriptaculous.js?load=effects" type="text/javascript"></script>
	<script src="js/lightbox.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/java.js"></script>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wrap">

       <div class="header">
       		<div class="logo"><a href="index"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>            
        <div id="menu">
            <ul>
					<li class="selected"><a href="index">主页</a></li>
					<li><a href="about">关于我们</a></li>
					<li><a href="category">书城</a></li>
					<li><a href="specials">特价书</a></li>
					<%-- <c:if test="${sessionScope.username!=null }">
					<!-- <li><a href="myaccount"></a></li> --></c:if> --%>
					<c:if test="${sessionScope.username==null }">
					<li><a href="myaccount">登录</a></li></c:if>
					<c:if test="${sessionScope.username==null }">
					<li><a href="register">注册</a></li></c:if>
					
					
					<c:if test="${sessionScope.username!=null }">
					 <li><a href="edit">${sessionScope.username}</a></li></c:if>
					 <c:if test="${sessionScope.username!=null }">
					<li><a href="order">我的订单</a></li></c:if>
					 
					 
					<li><a href="contact">联系我们</a></li>
					<c:if test="${sessionScope.username!=null }">
					<li><a href="exit">退出</a></li></c:if>
				</ul>
        </div>     
            
            
       </div> 
       
    
       <div class="center_content">
       	<div class="left_content">
        	<div class="crumb_nav">
            <a href="index">主页</a> &gt;&gt; 书本详情
            </div>
             
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>${book.bookName}</div>
        
        	<div class="feat_prod_box_details">
            
            	<div class="prod_img"><a href="details"><img height="100px" width="60" src="bookImage/${book.bookImages}" alt="" title="" border="0" /></a>
                <br /><br />
                <a href="bookImage/${book.bookImages}" rel="lightbox"><img src="images/zoom.gif" alt="" title="" border="0" /></a>
                </div>
                
                <div class="prod_det_box">
                	<div class="box_top"></div>
                    <div class="box_center">
                    <div class="prod_title">介绍</div>
                    <p class="details">
                 		    作者：${book.bookAuthor}<br/>
                    	出版社：${book.bookEdition }<br/>
                    	出版时间：${book.issuanceDate }<br/>
                    	价格：${book.bookPrice}<br/>
                    	折扣：${book.bookDiscount}<br/>
                                   </p>
                    <div class="price"><strong>折后价:</strong> <span class="red"><fmt:formatNumber type="number" value="${book.bookPrice*book.bookDiscount }" pattern="0.00" maxFractionDigits="2"/><br/></span></div>
                    <!--  <div class="price"><strong>颜色:</strong> 
                    <span class="colors"><img src="images/color1.gif" alt="" title="" border="0" /></span>
                    <span class="colors"><img src="images/color2.gif" alt="" title="" border="0" /></span>
                    <span class="colors"><img src="images/color3.gif" alt="" title="" border="0" /></span>                    </div>-->
                    <%String user = (String)session.getAttribute("username");
                    	if(user!=null){%>
					<a onClick="return confirm('确定添加至购物车吗?');" href="/Book/addcar?bookid=${book.bookId}&userid=${sessionScope.userid }" class="more"><img src="images/order_now.gif" alt="" title="" border="0" /></a>
					<%} else{ %>
				<a href="/Book/myaccount" class="more" onclick="javascript:fun()"><img src="images/order_now.gif" alt="" title="" border="0" /></a>
					<%} %>
                    <div class="clear"></div>
                    </div> 
                    
                    <div class="box_bottom"></div>
                </div>    
            <div class="clear"></div>
            </div>	
            
              
            <div id="demo" class="demolayout">

                <ul id="demo-nav" class="demolayout">
                <li><a class="active" href="#tab1">详细介绍</a></li>
                <li><a class="" href="#tab2">相关书籍</a></li>
         
                </ul>
    
            <div class="tabs-container">
            
                    <div style="display: block;" class="tab" id="tab1">
                                        <p class="more_details">${book.bookIntroduce }</p>
                                                    
                    </div>	
                    
                            <div style="display: none;" class="tab" id="tab2">
                             <c:forEach items="${booktype }" var="bk">
                    <div class="new_prod_box">
                        <a href="details">${bk.bookName}</a>
                        <div class="new_prod_bg">
                        <a href="details?cz=${bk.bookId}"><img height="100px" width="60" src="bookImage/${bk.bookImages}" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    </c:forEach>
             <!--        <div class="new_prod_box">
                        <a href="details">product name</a>
                        <div class="new_prod_bg">
                        <a href="details"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>   -->                  
                    
                 <!--    <div class="new_prod_box">
                        <a href="details">product name</a>
                        <div class="new_prod_bg">
                        <a href="details"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>  -->   

                <!--     <div class="new_prod_box">
                        <a href="details">product name</a>
                        <div class="new_prod_bg">
                        <a href="details"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                     -->
                  <!--   <div class="new_prod_box">
                        <a href="details">product name</a>
                        <div class="new_prod_bg">
                        <a href="details"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>    -->                 
                    
              <!--       <div class="new_prod_box">
                        <a href="details">product name</a>
                        <div class="new_prod_bg">
                        <a href="details"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>  --> 


                   
                    <div class="clear"></div>
                            </div>	
            
            </div>


			</div>
            

            
        <div class="clear"></div>
        </div><!--end of left content-->
        
        <div class="right_content">
        	<form action="search" method="get">
					<input type="text" name="search" placeholder="垵书名查询" required=""/>					
					<input type="submit" value="搜索"/>
					</form>
                
                
              <div class="cart">
                  <div class="title"><span class="title_icon"><img src="images/cart.gif" alt="" title="" /></span>购物车</div>
                  <div class="home_cart_content">
                  ${number}本书 | <span class="red">总价: ${Total}</span>
                  </div>
                  <a href="cart" class="view_cart">展示</a>
              
              </div>
                       
            	
        
        
             <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>关于我们的商店</div> 
             <div class="about">
             <p>
             <img src="images/about.gif" alt="" title="" class="right" />
             		我们爱书店<br/>
					书店带给我们什么？<br/>
					不仅是排排架上泛着墨香的书<br/>
					和度过宁静时光的空间<br/>
					它还可以带给你诗与远方<br/>
             </p>
             
             </div>
             
             <div class="right_box">
             
             	<div class="title"><span class="title_icon"><img src="images/bullet4.gif" alt="" title="" /></span>促销活动</div> 
                    <c:forEach items="${bookcount }" var="count">
					<div class="new_prod_box">
						<a href="details?cz=${count.bookId}">${count.bookName }</a>
						<div class="new_prod_bg">
							<span class="new_icon"><img src="images/promo_icon.gif"
								alt="" title="" /></span> <a href="details?cz=${count.bookId}"><img
								height="100px" width="60" src="bookImage/${count.bookImages}" alt="" title="" class="thumb" border="0" /></a>
						</div>
					</div>
					</c:forEach> 
             
             </div>
             
             <div class="right_box">
             
             	<div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>类别</div> 
                
                 <ul class="list">
                <li><a href="categoryType?type=教育">教育</a></li>
                <li><a href="categoryType?type=小说">小说</a></li>
                <li><a href="categoryType?type=文艺">文艺</a></li>
                <li><a href="categoryType?type=童书">童书</a></li>
                <li><a href="categoryType?type=人文社科">人文社科</a></li>
                <li><a href="categoryType?type=经管">经管</a></li>
                <li><a href="categoryType?type=成功/励志">成功/励志</a></li>
                <li><a href="categoryType?type=生活">生活</a></li>
                <li><a href="categoryType?type=科技">科技</a></li>                                             
                </ul>
                
             	<div class="title"><span class="title_icon"><img src="images/bullet6.gif" alt="" title="" /></span>合作伙伴</div> 
                
                <ul class="list">
                <li><a href="https://www.amazon.cn/">亚马逊</a></li>
                <li><a href="http://www.dangdang.com">当当网</a></li>
                <li><a href="http://www.taobao.com">淘宝</a></li>
                <li><a href="https://www.jd.com">京东</a></li>
                <li><a href="https://www.suning.com">苏宁</a></li>
                <li><a href="https://www.gome.com.cn">国美</a></li>
                <li><a href="https://www.pinduoduo.com">拼多多</a></li>
                <li><a href="https://www.baidu.com">百度</a></li>                              
                </ul>      
             
             </div>         
             
        
        </div><!--end of right content-->
        
       
       
       <div class="clear"></div>
       </div><!--end of center content-->
       
              
       <div class="footer">
       	<div class="left_footer"><img src="images/footer_logo.gif" alt="" title="" /><br /> <a href="http://www.cssmoban.com/" title="free templates">cssmoban</a></div>
        <div class="right_footer">
        <a href="#">home</a>
        <a href="#">about us</a>
        <a href="#">services</a>
        <a href="#">privacy policy</a>
        <a href="#">contact us</a>
       
        </div>
        
       
       </div>
    

</div>

</body>
<script type="text/javascript">

var tabber1 = new Yetii({
id: 'demo'
});

</script>
</html>