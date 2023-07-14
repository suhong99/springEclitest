<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<script type="text/javascript" src="jquery/jquery.twbsPagination.min.js"></script>

<!-- https://github.com/josecebe/twbs-pagination -->
</head>
<body>

<div class="container">
<nav aria-label="Page navigation">
<ul class="pagination" id="pagination"></ul>
</nav>
</div>

<script type="text/javascript">
let totalCount = 26; // 글의 총수
let pageNumber = 1; // 현재 페이지

let pageBbs = totalCount / 10;
if((totalCount % 10) > 0){
pageBbs = pageBbs + 1;
}

$("#pagination").twbsPagination({
startPage:1,
totalPages:pageBbs,
visiblePages:10,
first:'<span srid-hidden="true">«</span>', // 처음 페이지로 이동
prev:"이전",
next:"다음",
last:'<span srid-hidden="true">»</span>'
});

</script>

</body>
</html>