<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Shoppy Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!--js-->
<script src="js/jquery-2.1.1.min.js"></script> 
<!--icons-css-->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!--Google Fonts-->
<link href='https://fonts.googleapis.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
<!--static chart-->
<script src="js/Chart.min.js"></script>
<!--//charts-->
<!-- geo chart -->
    <script src="http://cdn.jsdelivr.net/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <script>window.modernizr || document.write('<script src="lib/modernizr/modernizr-custom.js"><\/script>')</script>
    <!--<script src="lib/html5shiv/html5shiv.js"></script>-->
     <!-- Chartinator  -->
    <script src="js/chartinator.js" ></script>
    <script type="text/javascript">
        jQuery(function ($) {

            var chart3 = $('#geoChart').chartinator({
                tableSel: '.geoChart',

                columns: [{role: 'tooltip', type: 'string'}],
         
                colIndexes: [2],
             
                rows: [
                    ['China - 2015'],
                    ['Colombia - 2015'],
                    ['France - 2015'],
                    ['Italy - 2015'],
                    ['Japan - 2015'],
                    ['Kazakhstan - 2015'],
                    ['Mexico - 2015'],
                    ['Poland - 2015'],
                    ['Russia - 2015'],
                    ['Spain - 2015'],
                    ['Tanzania - 2015'],
                    ['Turkey - 2015']],
              
                ignoreCol: [2],
              
                chartType: 'GeoChart',
              
                chartAspectRatio: 1.5,
             
                chartZoom: 1.75,
             
                chartOffset: [-12,0],
             
                chartOptions: {
                  
                    width: null,
                 
                    backgroundColor: '#fff',
                 
                    datalessRegionColor: '#F5F5F5',
               
                    region: 'world',
                  
                    resolution: 'countries',
                 
                    legend: 'none',

                    colorAxis: {
                       
                        colors: ['#679CCA', '#337AB7']
                    },
                    tooltip: {
                     
                        trigger: 'focus',

                        isHtml: true
                    }
                }

               
            });                       
        });
    </script>
<!--geo chart-->

<!--skycons-icons-->
<script src="js/skycons.js"></script>
<!--//skycons-icons-->
</head>
<body>	
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-container">	
   <div class="left-content">
	   <div class="mother-grid-inner">
            <!--header start here-->
				<div class="header-main">
					<div class="header-left">
							<div class="logo-name">
									 <a href="index.html"> <h1>Shoppy</h1> 
									<!--<img id="logo" src="" alt="Logo"/>--> 
								  </a> 								
							</div>
							<!--search-box-->
								<!-- <div class="search-box">
									<form method="post" action="adminsearch">
									<select name="type">
									<option value="11">11</option>
									</select><br>
										<input type="text" placeholder="Search..." required="" name="search">	
										<input type="submit" value="">					
									</form>
								</div>//end-search-box -->
							<div class="clearfix"> </div>
						 </div>
						 <div class="header-right">
							<div class="profile_details_left"><!--notifications of menu start -->
								<ul class="nofitications-dropdown">
									<li class="dropdown head-dpdn">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-envelope"></i><span class="badge">3</span></a>
										<ul class="dropdown-menu">
											<li>
												<div class="notification_header">
													<h3>You have 3 new messages</h3>
												</div>
											</li>
											<li><a href="#">
											   <div class="user_img"><img src="images/p4.png" alt=""></div>
											   <div class="notification_desc">
												<p>Lorem ipsum dolor</p>
												<p><span>1 hour ago</span></p>
												</div>
											   <div class="clearfix"></div>	
											</a></li>
											<li class="odd"><a href="#">
												<div class="user_img"><img src="images/p2.png" alt=""></div>
											   <div class="notification_desc">
												<p>Lorem ipsum dolor </p>
												<p><span>1 hour ago</span></p>
												</div>
											  <div class="clearfix"></div>	
											</a></li>
											<li><a href="#">
											   <div class="user_img"><img src="images/p3.png" alt=""></div>
											   <div class="notification_desc">
												<p>Lorem ipsum dolor</p>
												<p><span>1 hour ago</span></p>
												</div>
											   <div class="clearfix"></div>	
											</a></li>
											<li>
												<div class="notification_bottom">
													<a href="#">See all messages</a>
												</div> 
											</li>
										</ul>
									</li>
									<li class="dropdown head-dpdn">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-bell"></i><span class="badge blue">3</span></a>
										<ul class="dropdown-menu">
											<li>
												<div class="notification_header">
													<h3>You have 3 new notification</h3>
												</div>
											</li>
											<li><a href="#">
												<div class="user_img"><img src="images/p5.png" alt=""></div>
											   <div class="notification_desc">
												<p>Lorem ipsum dolor</p>
												<p><span>1 hour ago</span></p>
												</div>
											  <div class="clearfix"></div>	
											 </a></li>
											 <li class="odd"><a href="#">
												<div class="user_img"><img src="images/p6.png" alt=""></div>
											   <div class="notification_desc">
												<p>Lorem ipsum dolor</p>
												<p><span>1 hour ago</span></p>
												</div>
											   <div class="clearfix"></div>	
											 </a></li>
											 <li><a href="#">
												<div class="user_img"><img src="images/p7.png" alt=""></div>
											   <div class="notification_desc">
												<p>Lorem ipsum dolor</p>
												<p><span>1 hour ago</span></p>
												</div>
											   <div class="clearfix"></div>	
											 </a></li>
											 <li>
												<div class="notification_bottom">
													<a href="#">See all notifications</a>
												</div> 
											</li>
										</ul>
									</li>	
									<li class="dropdown head-dpdn">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-tasks"></i><span class="badge blue1">9</span></a>
										<ul class="dropdown-menu">
											<li>
												<div class="notification_header">
													<h3>You have 8 pending task</h3>
												</div>
											</li>
											<li><a href="#">
												<div class="task-info">
													<span class="task-desc">Database update</span><span class="percentage">40%</span>
													<div class="clearfix"></div>	
												</div>
												<div class="progress progress-striped active">
													<div class="bar yellow" style="width:40%;"></div>
												</div>
											</a></li>
											<li><a href="#">
												<div class="task-info">
													<span class="task-desc">Dashboard done</span><span class="percentage">90%</span>
												   <div class="clearfix"></div>	
												</div>
												<div class="progress progress-striped active">
													 <div class="bar green" style="width:90%;"></div>
												</div>
											</a></li>
											<li><a href="#">
												<div class="task-info">
													<span class="task-desc">Mobile App</span><span class="percentage">33%</span>
													<div class="clearfix"></div>	
												</div>
											   <div class="progress progress-striped active">
													 <div class="bar red" style="width: 33%;"></div>
												</div>
											</a></li>
											<li><a href="#">
												<div class="task-info">
													<span class="task-desc">Issues fixed</span><span class="percentage">80%</span>
												   <div class="clearfix"></div>	
												</div>
												<div class="progress progress-striped active">
													 <div class="bar  blue" style="width: 80%;"></div>
												</div>
											</a></li>
											<li>
												<div class="notification_bottom">
													<a href="#">See all pending tasks</a>
												</div> 
											</li>
										</ul>
									</li>	
								</ul>
								<div class="clearfix"> </div>
							</div>
							<!--notification menu end -->
							<div class="profile_details">		
								<ul>
									<li class="dropdown profile_details_drop">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
											<div class="profile_img">	
												<span class="prfil-img"><img src="images/p1.png" alt=""> </span> 
												<div class="user-name">
													<p>Malorum</p>
													<span>Administrator</span>
												</div>
												<i class="fa fa-angle-down lnr"></i>
												<i class="fa fa-angle-up lnr"></i>
												<div class="clearfix"></div>	
											</div>	
										</a>
										<ul class="dropdown-menu drp-mnu">
											<!-- <li> <a href="#"><i class="fa fa-cog"></i> Settings</a> </li> 
											<li> <a href="#"><i class="fa fa-user"></i> Profile</a> </li>  -->
											<li> <a href="adminExit"><i class="fa fa-sign-out"></i>登出</a> </li>
										</ul>
									</li>
								</ul>
							</div>
							<div class="clearfix"> </div>				
						</div>
				     <div class="clearfix"> </div>	
				</div>
<!--heder end here-->
<!-- script-for sticky-nav -->
		<script>
		$(document).ready(function() {
			 var navoffeset=$(".header-main").offset().top;
			 $(window).scroll(function(){
				var scrollpos=$(window).scrollTop(); 
				if(scrollpos >=navoffeset){
					$(".header-main").addClass("fixed");
				}else{
					$(".header-main").removeClass("fixed");
				}
			 });
			 
		});
		</script>
		<!-- /script-for sticky-nav -->
<!--inner block start here-->
<div class="inner-block">
<!--market updates updates-->
	
	
	<form class="form-horizontal" role="form" action="doadd" method="post" enctype="multipart/form-data">
	<%-- <input type="hidden" name="bookId" value="${ book.bookId}"> --%>
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label" >书名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name ="bookName" id="bookName" placeholder="书名">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">书本类型</label>
    <div class="col-sm-10">
    
    <select name="booktypeId" id="bookTypename">
    <c:forEach items="${types }" var="type">
    <option value=${type.typeId }>${type.typeName }</option>
    </c:forEach>
    </select>
    
     <!--  <input type="text" class="form-control" name="booktypeId"id="bookTypename" placeholder="书本类型"> -->
    </div>
  </div>
  
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">作者</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="bookAuthor"id="bookAuthor"  placeholder="作者">
    </div>
    </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">出版社</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="bookEdition"id="bookEdition" placeholder="出版社">
    </div>
    </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">价格</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="bookPrice"id="bookPrice" placeholder="价格">
    </div>
    </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">库存</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="bookAmount"id="bookAmount" placeholder="库存">
    </div>
    </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">详细信息</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="bookIntroduce"id="bookIntroduce" placeholder="相信信息">
    </div>
    </div>
    
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">出版日期</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="issuanceDate" id="issuanceDate" placeholder="出版日期（年-月-日）" >
    </div>
    </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">封面</label>
    <div class="col-sm-10">
      <input type="file"  name="file" id="file" >
    
 <%-- <img height="100px" width="60"
								src="bookImage/${book.bookImages}" alt="" title="" border="0" /> --%>
 </div>
    </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">特色书籍</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="bookSpecil" id="bookSpecil"  placeholder="0不是特色书籍，1是特色书籍">
    </div>
    </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">折扣</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="bookDiscount"id="bookDiscount" placeholder="折扣" >
    </div>
    
  </div>
 <!--  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox">请记住我
        </label>
      </div>
    </div>
  </div> -->
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">增加</button>
    </div>
  </div>
</form>
	
<!--climate end here-->
</div>

<!--inner block end here-->
<!--copy rights start here-->
<div class="copyrights">
</div>	
<!--COPY rights end here-->
</div>
</div>
<!--slider menu-->
    <div class="sidebar-menu">
		  	<div class="logo"> <a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> <a href="#"> <span id="logo" ></span> 
			     <!--  <img id="logo" src="" alt="Logo"/>  -->
			  </a> </div>		  
		    <div class="menu">
		      <ul id="menu" >
		        <li id="menu-home" ><a href="index"><i class="fa fa-tachometer"></i><span>主页</span></a></li>
		        
		       
		     <!--     
		        <li id="menu-academico" ><a href="#"><i class="fa fa-file-text"></i><span>登陆</span><span class="fa fa-angle-right" style="float: right"></span></a>
		          <ul id="menu-academico-sub" >
		          	 <li id="menu-academico-boletim" ><a href="login">登陆</a></li>
		            <li id="menu-academico-avaliacoes" ><a href="signup.html">注册</a></li>		           
		          </ul>
		        </li> -->
		         <li><a href="liuyan"><i class="fa fa-bar-chart"></i><span>留言管理</span></a></li>
		        <!-- <li><a href="addtype"><i class="fa fa-bar-chart"></i><span>新增类型</span></a></li> -->
		        
		         <li><a href="#"><i class="fa fa-shopping-cart"></i><span>书籍管理</span><span class="fa fa-angle-right" style="float: right"></span></a>
		         	<ul id="menu-academico-sub" >
			            <li id="menu-academico-avaliacoes" ><a href="index">书籍</a></li>
			            <li id="menu-academico-boletim" ><a href="add">新增书籍</a></li>
			             
		             </ul>
		         </li>
		         
		          <li><a href="#"><i class="fa fa fa-book nav_icon"></i><span>类型管理</span><span class="fa fa-angle-right" style="float: right"></span></a>
		         	<ul id="menu-academico-sub" >
			            <li id="menu-academico-avaliacoes" ><a href="type">类型</a></li>
			        
			              <li id="menu-academico-boletim" ><a href="addtype">新增类型</a></li>
		             </ul>
		         </li>
		      </ul>
		    </div>
	 </div>
	<div class="clearfix"> </div>
</div>
<!--slide bar menu end here-->
<script>
var toggle = true;
            
$(".sidebar-icon").click(function() {                
  if (toggle)
  {
    $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
    $("#menu span").css({"position":"absolute"});
  }
  else
  {
    $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
    setTimeout(function() {
      $("#menu span").css({"position":"relative"});
    }, 400);
  }               
                toggle = !toggle;
            });
</script>
<!--scrolling js-->
		<script src="js/jquery.nicescroll.js"></script>
		<script src="js/scripts.js"></script>
		<!--//scrolling js-->
<script src="js/bootstrap.js"> </script>
<!-- mother grid end here-->
</body>
</html>                     