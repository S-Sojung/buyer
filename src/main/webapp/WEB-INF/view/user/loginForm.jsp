<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <h1>로그인 페이지</h1>
        <hr>
        <form action="/login" method="post"><!--템플릿 언어가 메모리의 어디까지 접근 가능한지 공부해야한다 ,cookie.key.value cookie.remember.value-->
            <input type="text" name="username" value ="${cookie.remember.value}" placeholder="Enter name" required /> <br/>
            <input type="password" name="password" placeholder="Enter password" required /> <br/>
            유저네임을 기억할까요? <input type="checkbox" name="remember" /> <br/>
            <button type="submit"> 로그인 </button>
        </form>
        <!-- x-www-formurl code 형태로 날아간다. username = " " , ~~~ -->
        <%@ include file="../layout/footer.jsp" %>