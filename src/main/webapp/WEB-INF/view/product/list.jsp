<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!--중복되더라도 jsp 페이지를 인식하기 위함--> 
<!-- 내 폴더 한 칸 위로 ../ -->
<%@ include file ="../layout/header.jsp"%>
    <h1> 상품 목록 페이지 </h1>
    <hr />
    <table border="1">
        <tr>
            <th>번호</th>
            <th>상품명</th>
            <th>가격</th>
            <th>재고</th>
            <th>등록일</th>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.id}</td> <!--톰캣이 알아서 스트링을 해석함. -->
                <td> <a href="/product/${product.id}">${product.name}</a></td>
                <td>${product.price} 원</td>
                <td>${product.qty} 개</td>
                <td>${product.createdAt}</td>
            </tr>
        </c:forEach>
        
    </table>
<%@ include file ="../layout/footer.jsp"%>