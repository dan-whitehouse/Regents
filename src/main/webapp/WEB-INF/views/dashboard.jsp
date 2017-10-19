<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <jsp:include page="fragments/header.jsp" />
    <!-- page content -->
    <div class="right_col" role="main">
        <!-- top tiles -->
        <div class="row tile_count">
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-user"></i> Total Users</span>
                <div class="count">${userCount}</div>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-university"></i> Total Districts</span>
                <div class="count">${districtCount}</div>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-graduation-cap"></i> Total Schools</span>
                <div class="count">${schoolCount}</div>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-truck"></i> Total Orders</span>
                <div class="count">${orderCount}</div>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-calendar"></i> Orders This Period</span>
                <div class="count">${activeOrderCount}</div>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-calendar-o"></i> Not Administering This Period</span>
                <div class="count">${activeNotAdministering}</div>
            </div>
        </div>
        <!-- /top tiles -->
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12">
                <div class="x_panel tile fixed_height_320">
                    <div class="x_title">
                        <h2>Orders</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <h4>Number of Districts Ordering Over Time</h4>
                        <div class="widget_summary">
                            <div class="w_left w_25">
                                <span>0.1.5.2</span>
                            </div>
                            <div class="w_center w_55">
                                <div class="progress">
                                    <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 66%;">
                                        <span class="sr-only">60% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <div class="w_right w_20">
                                <span>123k</span>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="widget_summary">
                            <div class="w_left w_25">
                                <span>0.1.5.3</span>
                            </div>
                            <div class="w_center w_55">
                                <div class="progress">
                                    <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 45%;">
                                        <span class="sr-only">60% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <div class="w_right w_20">
                                <span>53k</span>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="widget_summary">
                            <div class="w_left w_25">
                                <span>0.1.5.4</span>
                            </div>
                            <div class="w_center w_55">
                                <div class="progress">
                                    <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 25%;">
                                        <span class="sr-only">60% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <div class="w_right w_20">
                                <span>23k</span>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="widget_summary">
                            <div class="w_left w_25">
                                <span>0.1.5.5</span>
                            </div>
                            <div class="w_center w_55">
                                <div class="progress">
                                    <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 5%;">
                                        <span class="sr-only">60% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <div class="w_right w_20">
                                <span>3k</span>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="widget_summary">
                            <div class="w_left w_25">
                                <span>0.1.5.6</span>
                            </div>
                            <div class="w_center w_55">
                                <div class="progress">
                                    <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 2%;">
                                        <span class="sr-only">60% Complete</span>
                                    </div>
                                </div>
                            </div>
                            <div class="w_right w_20">
                                <span>1k</span>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12">
                <div class="x_panel tile fixed_height_320 overflow_hidden">
                    <div class="x_title">
                        <h2>Active Order Period</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <table class="" style="width:100%">
                            <tr>
                                <th style="width:37%;">
                                    <p></p>
                                </th>
                                <th>
                                    <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                                        <p class="">Status</p>
                                    </div>
                                    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                        <p class="">Percentage</p>
                                    </div>
                                </th>
                            </tr>
                            <tr>
                                <td>
                                    <canvas class="canvasDoughnut" height="140" width="140" style="margin: 15px 10px 10px 0"></canvas>
                                </td>
                                <td>
                                    <table class="tile_info">
                                        <tr>
                                            <td>
                                                <p>
                                                    <i class="fa fa-square blue"></i>
                                                    <span style="text-decoration: underline;">
                                                        <a href="<c:url value='/admin/dashboard/report/aop/ordered' />">Ordered</a>
                                                    </span>
                                                </p>
                                            </td>
                                            <td>${activeOrderCountPercent}</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <i class="fa fa-square green"></i>
                                                    <span style="text-decoration: underline;">
                                                        <a href="<c:url value='/admin/dashboard/report/aop/na' />">Not Administering</a>
                                                    </span>
                                                </p>
                                            </td>
                                            <td>${activeNotAdministeringPercent}</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p>
                                                    <i class="fa fa-square purple"></i>
                                                     <span style="text-decoration: underline;">
                                                        <a href="<c:url value='/admin/dashboard/report/aop/undecided' />">Undecided</a>
                                                    </span>
                                                </p>
                                            </td>
                                            <td>${activeUndecidedPercent}</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 col-xs-12 widget_tally_box">
                <div class="x_panel tile fixed_height_320 overflow_hidden">
                    <div class="x_title">
                        <h2>Active Orders By Day</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div id="graph_bar" style="width:100%; height:200px;"></div>
                    </div>
                </div>
            </div>
        </div>

            <div class="clearfix"></div>
        </div>

    <!-- /page content -->
    <script>
        function init_chart_doughnut()
        {
            if( typeof (Chart) === 'undefined'){ return; }
            console.log('init_chart_doughnut');
            if ($('.canvasDoughnut').length)
            {
                var chart_doughnut_settings =
                {
                    type: 'doughnut',
                    tooltipFillColor: "rgba(51, 51, 51, 0.55)",
                    data:
                    {
                        labels: [
                            "Ordered",
                            "Not Administering",
                            "Undecided"
                        ],
                        datasets: [{
                            data: [${activeOrdersUniqueDistrictCount}, ${activeNotAdministering}, ${activeUndecided}],
                            backgroundColor: [
                                "#BDC3C7",
                                "#E74C3C",
                                "#9B59B6"
                            ],
                            hoverBackgroundColor: [
                                "#CFD4D8",
                                "#E95E4F",
                                "#B370CF"
                            ]
                        }]
                    },
                    options: {
                        legend: false,
                        responsive: false
                    }
                }

                $('.canvasDoughnut').each(function(){

                    var chart_element = $(this);
                    var chart_doughnut = new Chart( chart_element, chart_doughnut_settings);

                });
            }
        }
    </script>

    <script>
        function init_morris_charts()
        {
            if( typeof (Morris) === 'undefined'){ return; }
            console.log('init_morris_charts');

            if ($('#graph_bar').length)
            {
                Morris.Bar({
                  element: 'graph_bar',
                  data: [
                    <c:forEach items="${orders}" var="order" varStatus="status">
                        {orderDate: '${order.key}', orderAmount: ${order.value}},
                    </c:forEach>
                  ],
                  xkey: 'orderDate',
                  ykeys: ['orderAmount'],
                  labels: ['Orders: '],
                  barRatio: 0.4,
                  barColors: ['#1ABB9C'],
                  xLabelAngle: 35,
                  hideHover: 'auto',
                  resize: true
                });

            }
        }
    </script>

    <script>
        function loadCharts()
        {
            init_chart_doughnut();
            init_morris_charts();
        }
        window.onload = loadCharts;
    </script>

    <jsp:include page="fragments/footer.jsp" />
</html>