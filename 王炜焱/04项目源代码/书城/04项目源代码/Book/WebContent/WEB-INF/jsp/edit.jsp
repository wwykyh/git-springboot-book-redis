<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="jsp/style.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<style type="text/css">
span {
	line-height: 30px;
}
</style>

<script type="text/javascript">

function showError(){
var obj = document.getElementById("tip");
//alert(obj.value=="购买失败,库存不足");
if(obj!=null){
	alert(obj.value);
}

} 


</script>

<script type="text/javascript">
			/*用户名的验证 4-15位，字母开头，字母数字组合*/
			function checkUserName() {
				//得到用户输入的用户名
				var uname = document.getElementById("username").value;
				//按照规则来校验
				var umg = document.getElementById("usernamemsg");

				//4-15位，字母开头，字母数字组合
				//正则表达式
				var reg_user = /^[a-zA-Z][a-zA-Z0-9]{3,14}$/;
				//验证 test
				//匹配
				var flag = reg_user.test(uname);

				if(flag) {
					//判断一下组合,uname是否包含数字
					var reg_user_number = /[0-9]/; // /\d/
					//使用test匹配
					if(reg_user_number.test(uname)) {
						umg.innerHTML = "";
						return true;
					} else {
						//格式错误.没有包含数字
						umg.innerHTML = "格式错误.没有包含数字";
						return false;
					}
				} else {
					umg.innerHTML = "用户名不能为空,且必须字母开头，4-15位,字母数字组合"
					return false;
				}

			}
			//密码验证  字母数字组合,6-20位
			function checkPwd() {
				//得到用户输入的用户名
				var pwd = document.getElementById("pwd").value;
				//按照规则来校验
				var pwdmsg = document.getElementById("pwdmsg");

				//字母数字组合,6-20位
				//正则表达式
				var reg_pwd = /^[a-zA-Z0-9]{6,20}$/;
				//验证 test
				//匹配
				var flag = reg_pwd.test(pwd);
				
				if(flag) {
					//判断一下组合,uname是否包含数字
					var reg_pwd_number = /\d/; // 
					//使用test匹配
					if(reg_pwd_number.test(pwd)) {
						var reg_pwd_number2 = /[a-zA-Z]/; // 
						if(reg_pwd_number2.test(pwd)) {
							pwdmsg.innerHTML = "";
							return true;
						} else {
							//格式错误.没有包含数字
							pwdmsg.innerHTML = "格式错误.没有包含字母";
							return false;
						}
						
					} else {
						//格式错误.没有包含数字
						pwdmsg.innerHTML = "格式错误.没有包含数字";
						return false;
					}

				} else {
					pwdmsg.innerHTML = "格式错误.没有包含字母";
					return false;
				}

			}
			//验证是否重复密码
			function checkcpwd () {
					var pwd = document.getElementById("pwd").value;
					var cpwd = document.getElementById("cpwd").value;
					var cpwdmsg = document.getElementById("cpwdmsg");
					if (pwd==cpwd) {
						cpwdmsg.innerHTML="<font color='green'>√</font>"
						return true;
					} else{
						cpwdmsg.innerHTML="两次输入密码不一致"
						return false;
					}
			}
			
			
			function checkoPwd () {
				var oldpwd = document.getElementById("oldpwd").value;
				var opwd = document.getElementById("opwd").value;
				var opwdmsg = document.getElementById("opwdmsg");
				if (opwd==oldpwd) {
					opwdmsg.innerHTML="<font color='green'>√</font>"
					return true;
				} else{
					opwdmsg.innerHTML="密码错误"
					return false;
				}
		}

			function checkemail() {
				//得到用户输入的用户名
				var email = document.getElementById("email").value;
				//按照规则来校验
				var emailmsg = document.getElementById("emailmsg");

				//字母数字组合,6-20位
				//正则表达式
				var reg_email =/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
				//验证 test
				//匹配
				var flag = reg_email.test(email);

				if(flag) {
							emailmsg.innerHTML = "";
							return true;
						} 
				else {
							//格式错误.没有包含数字
							emailmsg.innerHTML = "邮箱格式错误";
							return false;
						}
						

			}
			
			function checkephone() {
				//得到用户输入的用户名
				var tel = document.getElementById("tel").value;
				//按照规则来校验
				var telmsg = document.getElementById("telmsg");

				//字母数字组合,6-20位
				//正则表达式
				var reg_tel =/^[1][3,4,5,7,8][0-9]{9}$/;
				//验证 test
				//匹配
				var flag = reg_tel.test(tel);

				if(flag) {
							telmsg.innerHTML = "";
							return true;
						} 
				else {
							//格式错误.没有包含数字
							telmsg.innerHTML = "手机格式错误";
							return false;
						}
						

			}
			
			function checkAll () {
					if (checkUserName() &&checkPwd() &&checkcpwd () && checkemail() && checkephone()) {
								return true;
							} else{
								return false;
							}
						}
		</script>


</head>
<body onload="showError()">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	HttpSession se = request.getSession();
//String tip =(String) se.getAttribute("tip");
	
%>
<%if(se.getAttribute("tip")!=null){ %>
<input type="hidden" id="tip" value =<%=(String)se.getAttribute("tip") %>>
<%session.removeAttribute("tip");} %>
	<div id="wrap">

		<div class="header">
			<div class="logo">
				<a href="index"><img src="images/logo.gif" alt="" title=""
					border="0" /></a>
			</div>
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
				<div class="title">
					<span class="title_icon"><img src="images/bullet1.gif"
						alt="" title="" /></span>注册
				</div>

				<div class="feat_prod_box_details">
					<p class="details"
						style="font-family: STXingkai; font-size: 13px; color: #6F605A"">
						假如爱情可以解释、誓言可以修改<br /> 假如你我的相遇，可以重新安排<br /> 那么，生活就会比较容易<br />

						假如，有一天我终于能将你忘记<br /> 然而，这不是随便传说的故事<br /> 也不是明天才要上演的戏剧<br />

						我无法找出原稿然后将你一笔抹去<br />
					</p>

					<div class="contact_form">
						<div class="container">
							<div class="row">

								<div class="form_subtitle">修改资料</div>
								<form name="register" action="doedit" method="post"
									onsubmit="return checkAll()">
									<input type="hidden" name="userId" value="${User.userId }"/>

									<div class="form_row">
										<div class="form-group">
											<label class="contact"><strong>Username:</strong></label>
											<div class="col-md-6">
												<input type="text" class="contact_input form-control"
													id="username" name="userName" onblur="checkUserName()" value="${ User.userName}"/>
											</div>
											<div class="col-md-4">
												<span id="usernamemsg">*4-15位，字母开头，字母数字组合,eg: pig001</span>

											</div>
										</div>
									</div>
									
									<div class="form_row">
										<div class="form-group">
											<label class="contact"><strong>Oldpassword:</strong></label>
											<div class="col-md-6">
											<input type="hidden" id="oldpwd" value="${User.password }"/>

												<input type="password" class="contact_input form-control"
													name="oldpassword" id="opwd" onblur="checkoPwd()" />
											</div>
											<div class="col-md-4">
												<span id="opwdmsg"></span>

											</div>
										</div>
									</div>


									<div class="form_row">
										<div class="form-group">
											<label class="contact"><strong>Newpassword:</strong></label>
											<div class="col-md-6">

												<input type="password" class="contact_input form-control"
													name="password" id="pwd" onblur="checkPwd()" />
											</div>
											<div class="col-md-4">
												<span id="pwdmsg">字母数字组合,6-20位</span>

											</div>
										</div>
									</div>
									
									<div class="form_row">
										<div class="form-group">
											<label class="contact"><strong>Cpassword:</strong></label>
											<div class="col-md-6">

												<input type="password" class="contact_input form-control"
													name="cpassword" id="cpwd" onblur="checkcpwd()" />
											</div>
											<div class="col-md-4">
												<span id="cpwdmsg"></span>

											</div>
										</div>
									</div>



									<div class="form_row">
										<div class="form-group">

											<label class="contact"><strong>Email:</strong></label>
											<div class="col-md-6">
												<input type="text" class="contact_input form-control"
													id="email" onblur="checkemail()" name="userEmail" value="${User.userEmail }"/>
											</div>
											<div class="col-md-4">
												<span id="emailmsg">不能为空,满足邮箱格式 user@xx.com</span>

											</div>
										</div>
									</div>


									<div class="form_row">
										<div class="form-group">
											<label class="contact"><strong>Phone:</strong></label>
											<div class="col-md-6">
												<input type="text" class="contact_input form-control"
													id="tel" onblur="checkephone()" name="userPhone" value="${ User.userPhone}" />
											</div>
											<div class="col-md-4">
												<span id="telmsg">不能为空,必须为11位</span>

											</div>
										</div>
									</div>

									<!--    <div class="form_row">
                    <label class="contact"><strong>Company:</strong></label>
                    <input type="text" class="contact_input" />
                    </div> -->




									<div class="form_row">
										<label class="contact"><strong>Adrres:</strong></label> <input
											type="text" class="contact_input" name="userAdress"  value="${ User.userAdress}"/>
									</div>

									<div class="form_row">
										<div class="terms">
											<input type="checkbox" name="terms" /> I agree to the <a
												href="#">terms &amp; conditions</a>
										</div>
									</div>



									<div class="foem_row">
										<%@ page import="java.util.*"%>
										<!-- //获取系统时间必须导入的 -->
										<%@ page import="java.text.*"%>
										<!-- //获取系统时间必须导入的 -->
										<%
											SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
											String date = format.format(new Date());
										%>
										<input type="hidden" name="userDate" value="<%=date%>" />

									</div>
									<div class="form_row text-center">

										<input type="submit" class="register btn btn-group-lg btn-lg"
											value="update" />


									</div>
								</form>
							</div>
						</div>
					</div>


				</div>


				<div class="clear"></div>
			</div>
			<!--end of left content-->

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
		</div>
		<!--end of center content-->


		<div class="footer">
			<div class="left_footer">
				<img src="images/footer_logo.gif" alt="" title="" /><br /> <a
					href="http://www.cssmoban.com/" title="free templates">cssmoban</a>
			</div>
			<div class="right_footer">
				<a href="#">home</a> <a href="#">about us</a> <a href="#">services</a>
				<a href="#">privacy policy</a> <a href="#">contact us</a>

			</div>


		</div>


	</div>

</body>
</html>