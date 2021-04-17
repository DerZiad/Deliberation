<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<layout:extends name="layout-resp.jsp">
	<layout:put block="content" type="REPLACE">

		<div class="main-card mb-3 card">
			<div class="card-body">
				<h5 class="card-title">Liste des étudiants</h5>
				<form method="get" action="/delib/XCLModule">
				<input name="module" id="module" value="${module.id_module}"
							type="hidden" class="form-control" readonly>
				<button class="mb-2 mr-2 btn btn-success" >Liste Deliberation</button>
				</form>
					<div class="form-row">
							
						<div class="col-md-3">
							<div class="position-relative form-group">
								<label for="city" class="">Chercher</label> <input type="text" onkeyup="cne()" id="myInput"
								 class="form-control">
							</div>
						</div>
					</div>
					
				<table class="mb-0 table table-hover" id="myTable">
					<thead>
						<tr>
						<th onclick="cneTr()">cne</th>
							<th class="th-sm" onclick="nomTr()">Nom</th>
							<th class="th-sm" onclick="preTr()">Prénom</th>
							<th class="th-sm" onclick="sortTable()">Moyenne</th>
							<th class="th-sm">validation</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${etudiants}">
							<tr >
								<td><a style="color: black">${i.inscription_pedagogique.etudiant.cne}</a></td>
								<td><a style="color: black">${i.inscription_pedagogique.etudiant.last_name_fr}</a></td>
								<td><a style="color: black">${i.inscription_pedagogique.etudiant.first_name_fr}</a></td>
								
								<td><a style="color: black">${i.note_module}</a></td>
								
								<c:if test="${i.validation == 0}">  
								<td><a style="color: black"><button disabled class="mb-2 mr-2 btn btn-danger disabled">Non validée
                                        </button></a></td>
                                 </c:if>
                                 <c:if test="${i.validation == 1}">  
								<td><a style="color: black"><button disabled class="mb-2 mr-2 btn btn-success disabled">Validé Ordinnaire
                                        </button></a></td>
                                 </c:if>
                                 
                                 <c:if test="${i.validation == 2}">  
								<td><a style="color: black"><button disabled class="mb-2 mr-2 btn btn-success disabled">Validé Rattrapage
                                        </button></a></td>
                                 </c:if>
                                 <c:if test="${i.validation == null}">  
								<td><a style="color: black"><button disabled class="mb-2 mr-2 btn btn-danger disabled">En cours
                                        </button></a></td>
                                 </c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
							<script>
				var pret=1;
function preTr() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("myTable");
  switching = true;
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("td")[2];
      y = rows[i + 1].getElementsByTagName("td")[2];
      //check if the two rows should switch place:
    	  if(pret==1){
    		 if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
        //if so, mark as a switch and break the loop:
        shouldSwitch = true;
        break;
      } 
    	  }
      
      else {
    	  if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
    	        //if so, mark as a switch and break the loop:
    	        shouldSwitch = true;
    	        break;
    	      }
      }
    	  
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
  if (pret==1)pret=0;
  else pret=1;
}
</script>


	
							<script>
				var nomt=1;
function nomTr() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("myTable");
  switching = true;
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("td")[1];
      y = rows[i + 1].getElementsByTagName("td")[1];
      //check if the two rows should switch place:
    	  if(nomt==1){
    		 if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
        //if so, mark as a switch and break the loop:
        shouldSwitch = true;
        break;
      } 
    	  }
      
      else {
    	  if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
    	        //if so, mark as a switch and break the loop:
    	        shouldSwitch = true;
    	        break;
    	      }
      }
    	  
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
  if (nomt==1)nomt=0;
  else nomt=1;
}
</script>
	
		
						<script>
				var cnet=1;
function cneTr() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("myTable");
  switching = true;
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("td")[0];
      y = rows[i + 1].getElementsByTagName("td")[0];
      //check if the two rows should switch place:
    	  if(cnet==1){
    		 if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
        //if so, mark as a switch and break the loop:
        shouldSwitch = true;
        break;
      } 
    	  }
      
      else {
    	  if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
    	        //if so, mark as a switch and break the loop:
    	        shouldSwitch = true;
    	        break;
    	      }
      }
    	  
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
  if (cnet==1)cnet=0;
  else cnet=1;
}
</script>
		
				<script>
				var id=1;
function sortTable() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("myTable");
  switching = true;
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("td")[3];
      y = rows[i + 1].getElementsByTagName("td")[3];
      //check if the two rows should switch place:
    	  if(id==1){
    		 if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
        //if so, mark as a switch and break the loop:
        shouldSwitch = true;
        break;
      } 
    	  }
      
      else {
    	  if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
    	        //if so, mark as a switch and break the loop:
    	        shouldSwitch = true;
    	        break;
    	      }
      }
    	  
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
  if (id==1)id=0;
  else id=1;
}
</script>
<script>
function cne() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td0 = tr[i].getElementsByTagName("td")[0];
    td1 = tr[i].getElementsByTagName("td")[1];
    td2 = tr[i].getElementsByTagName("td")[2];
    if (td0 || td1 || td2) {
      txtValue0 = td0.textContent || td0.innerText;
      txtValue1 = td1.textContent || td1.innerText;
      txtValue2 = td2.textContent || td2.innerText;
      if ((txtValue0.toUpperCase().indexOf(filter) > -1) ||(txtValue1.toUpperCase().indexOf(filter) > -1)
    		  || (txtValue2.toUpperCase().indexOf(filter) > -1)) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }   
    
  }
  
  sortTable();
}
sortTable();
</script>

		
	</layout:put>
</layout:extends>