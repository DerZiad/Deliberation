<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>


<!-- head -->
<jsp:include page="head.jsp"></jsp:include>


</head>


<body>
	<div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
		
		
		<!-- header / search-bar -->
		<jsp:include page="search-bar.jsp"></jsp:include>
		
		
		<div class="app-main">
			
			<!-- nav-bar -->
			<jsp:include page="nav-bar-prof.jsp"></jsp:include>
			
			
			<div class="app-main__outer">
				<div class="app-main__inner">
				
				
					<!-- content -->
					<layout:block name="content"></layout:block>
				
					
				</div>
				<div class="app-wrapper-footer">
					
					
					<!-- footer -->
					<jsp:include page="footer.jsp"></jsp:include>
					
				</div>
			</div>
			
			<!-- scripts -->
			<jsp:include page="scripts.jsp"></jsp:include>
		</div>
	</div>
	<script type="text/javascript" src="/assets/scripts/main.js"></script>
</body>
</html>
