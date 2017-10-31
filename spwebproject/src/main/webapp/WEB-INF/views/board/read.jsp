<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<body>
	<form role="form" method="post">
		<input type='hidden' name='bno' value="${boardVO.bno }">
	</form>
	
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">제목</label>
			<input type="text" name='title' class="form-control" value="${boardVO.title }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">내용</label>
			<textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content }</textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">작성자</label>
			<input type="text" name="writer" class="form-control" value="${boardVO.writer }" readonly="readonly">
		</div>
	</div> <!-- /.box-body -->
	
	<div class="box-footer">
		<button type="submit" class="btn btn-warning">MODIFY</button>
		<button type="submit" class="btn btn-danger">REMOVE</button>
		<button type="submit" class="btn btn-primary">LIST ALL</button>
	</div>
	<!-- include libraries(jQuery) -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script> 
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[role='form']");
			console.log(formObj);
			//수정
			$(".btn-warning").on("click", function(){
				formObj.attr("action", "/board/modify");
				formObj.attr("method", "get");
				formObj.submit();
			});
			//삭제
			$(".btn-danger").on("click", function(){
				formObj.attr("action", "/board/remove");
				formObj.submit();
			});
			//리스트 조회
			$(".btn-primary").on("click", function(){
				self.location = "/board/listAll";
			});
		});	
	</script>
</body>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>