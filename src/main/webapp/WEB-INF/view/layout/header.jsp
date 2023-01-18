<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>구매서버</title>
        </head>

        <body>
            <ul>
            <!--if문 : 조건이 맞으면 when 아니면 otherwise-->
            <c:choose>
               <c:when test="${principal == null}">
                    <li><a href="/">홈</a></li>
                    <!-- 인증에 관련된 것들은 앞에 entity 이름을 붙히지 않는다. -->
                    <!-- 회원 정보 보기 같은 인증이 필요한 경우 세션값을 검사해서 들어갈 수 있도록 해준다
                    이걸 컨트롤러에서 막으려면 메소드 마다 막아야하는데... 
                    -->
                    <li><a href="/loginForm">로그인</a></li>
                    <li><a href="/joinForm">회원가입</a></li>
               </c:when>
               
               <c:otherwise>
                    <li><a href="/">홈</a></li>
                    <li><a href="/purchase">구매목록</a></li> <!--내가 구매한게 걸려야함 나중에 다시 설정 -->
                    <li><a href="/logout">로그아웃</a></li>
               </c:otherwise>
            </c:choose>

                
                
            </ul>