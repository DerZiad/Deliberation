<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<link rel="stylesheet" href="/font-awesome-4.7.0/css/font-awesome.min.css">
<head>


<!-- head -->
<jsp:include page="head.jsp"></jsp:include>


</head>
<style>

</style>

<body>
	<div class="app-container app-theme-white body-tabs-shadow">
		
				
		
		<div class="app-main">
			
					
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
		</div>
	</div>
	<script type="text/javascript" src="/assets/scripts/main.js"></script>
</body>
</html>
