<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<table border="1">
<caption>Гости</caption>
 <tr>
   <th>Имя</th>
   <th>Фамилия</th>
   <th>Комната</th>
   <th>Время прихода</th>
   <th>Удалить гостя</th>
 </tr>

 <c:forEach items="${guestList}" var="guest">
   <tr>
     <td>${guest.name}</td>
     <td>${guest.surname}</td>
     <td>${guest.room}</td>
     <td>${guest.visitTime}</td>
     <td>
     <form name="deleteGuest" action="/deleteGuest">
         <input type="hidden" name="id" value="${guest.id}">
         <input type="submit" value="OK">
     </form></td>
   </tr>
 </c:forEach>
</table>

  <form name="addGuest" method="post" action="/addGuest">
      Имя: <input type="text" name="name"/>
      Фамилия: <input type="text" name="surname"/>
      К кому: <input type="text" name="owner"/>
      <input type="submit" value="Ok"/>
  </form>
</body>
</html>