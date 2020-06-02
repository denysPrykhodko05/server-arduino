<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
  <a href="/guests">Guests</a><br>
  <c:if test="${not empty user}">
    <a href="/">Home</a>
    <table border="1">
       <caption>Student</caption>
         <tr>
          <th>Photo</th>
          <th>Name</th>
          <th>Surname</th>
          <th>Access</th>
          <th>Room</th>
          <th>Group</th>
          <th>Birth Place</th>
          <th>Status</th>
         </tr>
         <tr>
           <td><img width="128 px" height="128 px" src="${pageContext.request.contextPath}/img/residents/${user.photo}"/></td>
           <td>${user.name}</td>
           <td>${user.surname}</td>
           <td>${user.access}</td>
           <td>${user.room}</td>
           <td>${user.group}</td>
           <td>${user.adressOfResidence}</td>
           <td>${user.position}</td>
         </tr>
    </table>
  </c:if>

  <iframe width="560" height="315" src="https://lideo.tv/embed/14794" frameborder="0" allowfullscreen></iframe>
</body>
</html>
