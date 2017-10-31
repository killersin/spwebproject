<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<body>
	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">BNO</label>
				<input type="text" name='bno' class="form-control" value="${boardVO.bno}" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">제목</label>
				<input type="text" name='title' class="form-control" value="${boardVO.title }" >
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">내용</label>
				<textarea class="form-control" name="content" rows="3" >${boardVO.content }</textarea>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">작성자</label>
				<input type="text" name="writer" class="form-control" value="${boardVO.writer }" >
			</div>
		</div>
	</form>
	
	<div class="box-footer">
		<button type="submit" class="btn btn-primary">수정완료</button>
		<button type="submit" class="btn btn-warning">취소</button>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[role='form']");
			console.log(formObj);
			
			$(".btn-warning").on("click", function(){
				self.location = "/board/listAll";
			});
			$(".btn-primary").on("click", function(){
				formObj.submit();
			})
		});
	</script>
</body>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>