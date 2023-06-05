<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />

    <!-- Bootstrap CSS-->
    <link
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-y3tfxAZXuh4HwSYylfB+J125MxIs6mR5FOHamPBG064zB+AFeWH94NdvaCBm8qnd"
      crossorigin="anonymous"
    />

    <!-- Custom CSS-->
    <link rel="stylesheet" href="/styles.css" />
  </head>

  <body>
    <div class="navbar-collapse collapse inverse" id="navbar-header">
      <div class="container-fluid">
        <div class="about">
          <h4>About</h4>
          <p class="text-muted">
            Add some information about the album below, the author, or any other
            background context. Make it a few sentences long so folks can pick
            up some informative tidbits. Then, link them off to some social
            networking sites or contact information.
          </p>
        </div>
        <div class="social">
          <h4>Contact</h4>
          <ul class="list-unstyled">
            <li><a href="#">Follow on Twitter</a></li>
            <li><a href="#">Like on Facebook</a></li>
            <li><a href="#">Email me</a></li>
          </ul>
        </div>
      </div>
    </div>
    
    

    <section class="jumbotron text-xs-center">
      <div class="container">
        <h1 class="jumbotron-heading mb-3">EXCITING AMUSEMENT <span style="font-weight:bolder;">회원님의 정보수정이 완료</span>되었습니다.</h1>
        <p class="lead text-muted">
          메인페이지로 이동하시려면 하단의 '메인페이지' 버튼을 눌러주세요.
        </p>
        <div>
          <a href="/" class="btn btn-secondary">메인페이지로 이동</a>
        </div>
      </div>
    </section>
  </body>
</html>

