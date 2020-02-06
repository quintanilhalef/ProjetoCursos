<jsp:include page="../util/topo.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="lib/js/jquery.min.js"></script>
<script type="text/javascript" src="lib/js/bootstrap.min.js"></script>
<link href="lib/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="lib/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="lib/css/padrao.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="section section-danger text-justify">
		<div class="container">
			<div class="row text-center">
				<div class="col-md-12 text-center">
					<h1 class="text-center">Lista de clientes</h1>
				</div>
			</div>
		</div>
	</div>
<div class="table-responsive-md">
<table class="table table-striped table-bordered">

  <thead>
  
    <tr>
      		<th scope="col">Codigo curso</th>		<th scope="col">CPF</th>		<th scope="col">Data Inscricão</th>
    </tr>
  </thead>
<tbody>

  <c:forEach items="${requestScope.lista}" var="u">
        
    <tr>  
      <td>${u.id.cdcurso}</td>		<td>${u.id.cpf}</td>		<td>${u.datainscricao}</td>
    </tr>
 		
 </c:forEach>
 
</tbody>
</table>
</div>

	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center corrigir">
					<a class="btn btn-default" href="pagamentos/index.jsp">Voltar</a>
				</div>
			</div>
		</div>
	</div>

	<footer>
		<div class="navbar navbar-fixed-bottom bgred">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 text-center" style="top: 13px; color: #fff;">©
						ABCTreinamentos - Curso de Java 8 para Web</div>
				</div>
			</div>
		</div>
	</footer>
	
</body>
</html>