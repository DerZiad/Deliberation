<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<layout:extends name="../index-prof.jsp">
	<layout:put block="responsable" type="REPLACE">
		<li><a href="/" class=""> <i
				class="metismenu-icon pe-7s-graph3"></i> Mes modules
		</a></li>
	</layout:put>
</layout:extends>