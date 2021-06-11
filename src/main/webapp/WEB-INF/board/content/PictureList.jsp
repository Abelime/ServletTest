<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Content List</title>
	<!-- root Route -->
	<c:set var="root" value="${pageContext.request.contextPath}" />
 <!-- Bootstrap core CSS -->
	<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${root}/css/board/dashboard.css" rel="stylesheet">
    
    <!-- Bootstrap js-->
     <script src="${root}/js/bootstrap/bootstrap.bundle.min.js" defer></script>
    
    <!-- icon js -->
      <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous" defer></script>
   
    <!-- Dashboard js -->
      <script src="${root}/js/board/dashboard.js" defer></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/board/etc/header.jsp"></jsp:include>
	<!-- sidebar -->
	<jsp:include page="/WEB-INF/board/etc/sidebar.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<c:forEach var="list" items="${list}">
			<div class="col-3">
				<img  src="${root}${list.path}">
			</div>		
			</c:forEach>
		</div>
	</div>
</body>
</html>