<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<select name="type" id="exampleSelect" class="form-control" required>

	<option value="TP">TP</option>
	<option value="TD">TD</option>
	<option value="CONTROL">CONTROL</option>
	 <c:if test = "${cmp== '0'}">      
   <option value="EXAM_ORDINAIRE">EXAM ORDINAIRE</option>
</c:if>
<c:if test = "${cmp== '1'}">      
   <option value="EXAM_RATTRAPAGE">EXAM RATTRAPAGE</option>
</c:if> 
	

</select>
