<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title">Board List</h3>
				</div>
				<div class='box-body'>
					<select name="searchType">
						<option value="n" <c:out value="${cri.searchType == null?'selected':''}"/>>
							카테고리 선택</option>
						<option value="t" <c:out value="${cri.searchType eq 't'?'selected':''}"/>>
							제목</option>
						<option value="c" <c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							내용</option>
						<option value="w" <c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
							작성자</option>
						<option value="tc" <c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
							제목or내용</option>
						<option value="cw" <c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
							내용or작성자</option>
						<option value="tcw" <c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
							제목or내용or작성자</option>
					</select> 
					<input type="text" name='keyword' id="keywordInput" value='${cri.keyword }'>
					<button id='searchBtn'>Search</button>
					<button id='newBtn'>New Board</button>
				</div>
			</div>
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th style="width: 40px">VIEWCNT</th>
						</tr>

						<c:forEach items="${list }" var="boardVO">
							<tr>
								<td>${boardVO.bno }</td>
								<td><a href='/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${boardVO.bno}'>
									${boardVO.title }<strong>[ ${boardVO.replycnt } ]</strong></a></td>
								<td>${boardVO.writer }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate }"/></td>
								<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
							</tr>
						</c:forEach>

					</table>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}"> <!-- 이전 페이지로 가는 링크가 있는 지 판단 -->
								<li><a href="list${pageMaker.makeSearch(pageMaker.startPage-1)}">&laquo;</a></li>
							</c:if>
 							<!-- pageMaker.cri.page를 이용해서 getCriteria(), getPage()를 호출하는 형태 -->
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
								<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a href="list${pageMaker.makeSearch(pageMaker.endPage +1)}">&raquo;</a></li>
							</c:if>

						</ul>
					</div>
				</div> 
				<!-- /.box-footer-->
			</div>
		</div><!--/.col (left) -->

	</div><!-- /.row -->
	
	<script type="text/javascript">
		$(document).ready(function(event){
			$('#searchBtn').on("click", function(event){
				self.location = "list" + '${pageMaker.makeQuery(1)}'
									   + "&searchType=" +$("select option:selected").val()
									   + "&keyword=" + encodeURIComponent($('#keywordInput').val());
			});
			$('#newBtn').on("click", function(evt){
				self.location = "register";
			});
		});	
		
		var result = '${msg}';

		if (result == 'SUCCESS') {
			alert("처리가 완료되었습니다.");
		}
	
	</script>
	
</section><!-- /.content -->


<%@ include file="/WEB-INF/views/include/footer.jsp" %>