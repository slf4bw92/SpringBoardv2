<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- Header -->
	<%@ include file="/WEB-INF/views/includes/header.jsp" %>
		
		<!-- Content Title  -->
        <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">게시판 /</span> 게시글 등록</h4>
        
              
              <div class="row">
                <div class="col-xl">
                  <div class="card mb-4">
                    <div class="card-header d-flex justify-content-between align-items-center">
                      <h5 class="mb-0">Register</h5>
                      <small class="text-muted float-end">Default label</small>
                    </div>
                    <div class="card-body">
                      <form action="/boards/add" method="post">
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-fullname">제목</label>
                          <input type="text" class="form-control" id="basic-default-fullname" placeholder="제목을 입력하세요" name="title"/>
                        </div>
                        <div class="mb-3">
                          <label class="form-label" for="basic-default-company">작성자</label>
                          <input type="text" class="form-control" id="basic-default-company" placeholder="작성자를 입력하세요" name="writer"/>
                        </div>

                        <div class="mb-3">
                          <label class="form-label" for="basic-default-message">내용</label>
                          <textarea id="basic-default-message" class="form-control" placeholder="내용을 입력하세요" rows="5" name="content"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">등록</button>
                        <button type="reset" class="btn btn-secondary">초기화</button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
			  <!-- /.row -->
	
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/includes/footer.jsp" %>