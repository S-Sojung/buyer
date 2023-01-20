<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <h1> 구매목록 페이지 </h1>
        <hr />
        <table border="1">
            <tr>
                <th>번호</th>
                <th>유저이름</th>
                <th>상품명</th>
                <th>구매갯수</th>
                <th></th>
            </tr>
            <c:forEach items="${purchaseList}" var="purchase">
                <tr>
                    <td>${purchase.id}</td> <!--톰캣이 알아서 스트링을 해석함. -->
                    <td>${purchase.username}</td>
                    <td>${purchase.name}</td>
                    <td>${purchase.count}</td>
                    <td>
                        <form action="/purchase/${purchase.id}/delete" method="post">
                            <!--구매 히스토리 삭제, 재고 플러스해야함. 트랜잭션 타야함.-->
                            <button type="submit">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <%@ include file="../layout/footer.jsp" %>