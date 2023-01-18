<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <h1>회원 가입 페이지</h1>
        <hr>
        <form action="/join" method="post">
            <input type="text" name="username" placeholder="Enter name" required /> <br>
            <input type="password" name="password" placeholder="Enter password" required /> <br>
            <input type="email" name="email" placeholder="Enter email" required /> <br>
            <button type="submit"> 회원 가입 </button>
        </form>
        <!-- x-www-formurl code 형태로 날아간다. username = " " , ~~~ -->
        <%@ include file="../layout/footer.jsp" %>