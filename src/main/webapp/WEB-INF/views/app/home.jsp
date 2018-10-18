<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../fragments/header.jsp" />
<jsp:include page="../fragments/nav.jsp" />
<!-- page content -->
<div class="right_col" role="main">
   <div class="">
      <div class="page-title">
         <div class="title_left">
            <!--                 <h3>NERIC Regents Order Form</h3> -->
         </div>
      </div>
      <div class="clearfix"></div>
      <div class="row">
         <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
               <div class="x_title">
                  <h2>NERIC Regents Order Form</h2>
                  <div class="clearfix"></div>
               </div>
               <div class="x_content">
                  <div class="col-md-8 col-lg-8 col-sm-7">
                     <div class="bs-example" data-example-id="simple-jumbotron">
                        <div class="jumbotron">
                          ${home_message}
                        </div>
                     </div>
                  </div>
                  <div class="col-md-4 col-lg-4 col-sm-5">
                     <ul class="list-unstyled msg_list">
                        <li>
                           <a>
                           <span>
                           <span>Anne Marie Bertram</span>
                           </span>
                           <span class="message">
                           annemarie.bertram@neric.org
                           </span>
                           </a>
                        </li>
                        <li>
                           <a>
                           <span>
                           <span>Matthew Coleman</span>
                           </span>
                           <span class="message">
                           matthew.coleman@neric.org
                           </span>
                           </a>
                        </li>
                        <li>
                           <a>
                           <span>
                           <span>Andrea Vamvas</span>
                           </span>
                           <span class="message">
                           andrea.vamvas@neric.org
                           </span>
                           </a>
                        </li>
                        <li>
                           <a>
                           <span>
                           <span>Testing and Evaluation Services</span>
                           </span>
                           <span class="message">
                           Phone: (518) 862-5314<br />
                           Fax: (518) 862-5396<br />
                           testing@neric.org
                           </span>
                           </a>
                        </li>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>
<!-- /page content -->
<jsp:include page="../fragments/footer.jsp" />
<jsp:include page="../fragments/scripts.jsp" />
<jsp:include page="../fragments/close.jsp" />