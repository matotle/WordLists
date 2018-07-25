<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>

<form action="test1">
    <input type="text" name="words" value="">
    <button type="submit" value="submit">GetWords</button>
</form>

	<div>

		<h3>Result</h3>
		 ${list}
	</div>


</body>
</html>
