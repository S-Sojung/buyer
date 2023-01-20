<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <h1> 상품 정보 페이지 </h1>
        <hr />
        <table border="1">
            <tr>
                <th>번호</th>
                <th>상품명</th>
                <th>가격</th>
                <th>재고</th>
                <th>등록일</th>
            </tr>
            <tr>
                <td>${product.id}</td> <!--톰캣이 알아서 스트링을 해석함. -->
                <td>${product.name}</td>
                <td>${product.price} 원</td>
                <td>${product.qty} 개</td>
                <td>${product.createdAtToString}</td>
            </tr>
        </table>

        <br>

        <c:if test="${principal != null}">
            <!-- <form action="/product/${product.id}/buy" method="post"> -->
            <form action="/purchase/insert" method="post">
                <!-- <input type="number" name="count" min="1" max="${product.qty}"> -->
                <input type = "hidden" name="productId" value="${product.id}" /> 
                <select name="count">
                    <c:forEach begin = "1" end="${product.qty}" var="num">
                        <option value="${num}">${num}</option>
                    </c:forEach>
                </select>
                <button type="submit">구매 하기</button>
            </form>
        </c:if>
        <%@ include file="../layout/footer.jsp" %>