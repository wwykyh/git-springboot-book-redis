<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>关于我们：</div>
        
        	<div class="feat_prod_box_details">
            <p class="details" style="font-family:STXingkai; font-size:13px;color:#6F605A"">
            <img src="images/about.gif" alt="" title="" class="right" /> 
            我们爱书店<br />
	书店带给我们什么？<br />
	不仅是排排架上泛着墨香的书<br />
	和度过宁静时光的空间<br />
	它还可以带给你诗与远方<br />
            我要去散步，置猫于不顾<br/>
	一切与寒冷苦苦搏斗的行径都可以暂时停止。<br/>
	今夜月相娥眉，草木悄然萌动。<br/>
	热点新闻焦灼得不合时宜，我要去散步，置猫于不顾。<br/>
	在计划阅读时同步计划出游，绕室或环球，<br/>
	我不精打细算于开销，却在意与谁交换行纪。<br/>
	假如逢着你，不感谢天意，感谢编剧。<br/>
	你会在购买孤独之后写差评吗<br/>
	把舌头伸出来，空气湿度超过百分之八十，<br/>
	你可以直接品尝，无需盐罐。<br/>
	你会在购买孤独之后写差评吗，<br/>
	谁会想退货这种好东西，特别是临近周末。<br/>
	在水果店财务自由的前提下，<br/>
	优先购买云南枇杷，它比其他地区的更熟，更大。<br/>
	汁水在牙齿间轻轻漱出甜香，一整碗一个人都能吃下。<br/>
	春夜喜雨，《书淫艳异录》翻到第二十八页，<br/>
	嗅嗅鼻子，你继续读。<br/>
	这些非常王家卫的文字竟然能用在“近期活动预告”的最开头，且毫无违和感。沉淀些许心事，又调皮得可爱。若是想要引起兴趣，只能摊手说句，你成功了。<br/>
            </p>
            
            
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