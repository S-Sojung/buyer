<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="../layout/header.jsp"%>

    <h1> 구매목록 페이지 </h1>
    <hr />
    <table border="1">
        <tr>
            <th>번호</th>
            <th>산 사람 </th>
            <th>물건 </th>
            <th>재고</th>
        </tr>
        <c:forEach items="${purchaseList}" var="purchase">
            <tr>
                <td>${purchase.id}</td> <!--톰캣이 알아서 스트링을 해석함. -->
                <td>${purchase.user_id}</td>
                <td>${purchase.product_id}</td>
                <td>${purchase.createdAt}</td>
            </tr>
        </c:forEach>
        
    </table>
<%@ include file ="../layout/footer.jsp"%>