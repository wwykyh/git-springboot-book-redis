<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="jsp/style.css" />
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>我的账户</div>
        
        	<div class="feat_prod_box_details">
            <p class="details" style="font-family:STXingkai; font-size:13px;color:#6F605A"">
             当你老了，头发白了，睡思昏沉，<br />

	炉火旁打盹，请取下这部诗歌，<br />

	慢慢读，回想你过去眼神的柔和，<br />

	回想它们昔日浓重的阴影，<br />

	多少人爱你青春欢畅的时辰，<br />

	爱慕你的美丽，假意和真心，<br />

	只有一个人爱你朝圣者的灵魂，<br />

	爱你衰老了的脸上痛苦的皱纹，<br />

	垂下头来，在红火闪耀的炉子旁，<br />

	凄然地轻轻诉说那爱情的消逝，<br />

	在头顶上的山上它缓缓地踱着步子，<br />

	在一群星星中间隐藏着脸庞。<br />
            </p>
            
              
              	<div class="contact_form">
                <div class="form_subtitle">登陆</div>
                <div>${tip} </div>
                 <form name="log" action="dologin" method="post">          
                    <div class="form_row">
                    <label class="contact"><strong>Username:</strong></label>
                    <input type="text" class="contact_input" name="username" value="${username1 }"/>
                    </div>  


                    <div class="form_row">
                    <label class="contact"><strong>Password:</strong></label>
                    <input type="password" class="contact_input" name="password" />
                    </div>                     

                    <div class="form_row">
                        <div class="terms">
                        <input type="checkbox" name="remember" />
                   			    记住我的账户
                        </div>
                    </div> 

                    
                    <div class="form_row">
                    <input type="submit" class="register" value="login" />
                    </div>   
                    
                  </form>     
                    
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
              <c:if test="${sessionScope.username!=null }">
					
                  <div class="title"><span class="title_icon"><img src="images/cart.gif" alt="" title="" /></span>购物车</div>
                  <div class="home_cart_content">
                  ${number}本书 | <span class="red">总价: ${Total}</span>
                  </div>
                  <a href="cart" class="view_cart">展示</a>
              </c:if>
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
</html>