		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<!-- footer content -->
        <footer>
       	<div class="clearfix"></div>
          <div class="pull-right">
			<p class="right_orgInfo" style="font-size:13px;">Northeastern Regional Information Center<br />
				<span class="right_orgInfo" style="font-size:10px;">900 Watervliet-Shaker Road | Albany, New York 12205</span>
				<span class="right_version" style="font-size:10px;">NERIC Regents Order Form v1.1.0</span>
			</p>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="<c:url value='/resources/vendors/jquery/dist/jquery.min.js' />"></script>
    
    <!-- Bootstrap -->    
    <script src="<c:url value='/resources/vendors/bootstrap/dist/js/bootstrap.min.js' />"></script>

    <!-- FastClick -->    
    <script src="<c:url value='/resources/vendors/fastclick/lib/fastclick.js' />"></script>
    
    <!-- NProgress -->   
    <!--<script src="<c:url value='/resources/vendors/nprogress/nprogress.js' />"></script>-->

    <!-- jQuery Smart Wizard -->   
    <script src="<c:url value='/resources/vendors/jQuery-Smart-Wizard/js/jquery.smartWizard.js' />"></script>

    <!-- validator -->
    <script src="<c:url value='/resources/build/js/validator.min.js' />"></script>

    
    <!-- Datatables -->
    <script src="<c:url value='/resources/vendors/datatables.net/js/jquery.dataTables.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-buttons/js/dataTables.buttons.min.js' />"></script>
	<script src="<c:url value='/resources/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-buttons/js/buttons.flash.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-buttons/js/buttons.html5.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-buttons/js/buttons.print.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-responsive/js/dataTables.responsive.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js' />"></script>
    <script src="<c:url value='/resources/vendors/datatables.net-scroller/js/dataTables.scroller.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/jszip/dist/jszip.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/pdfmake/build/pdfmake.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/pdfmake/build/vfs_fonts.js' />"></script>
       
    <script src="<c:url value='/resources/vendors/iCheck/icheck.min.js'/>"></script>

     <!-- Chart -->
    <script src="<c:url value='/resources/vendors/Chart.js/dist/Chart.min.js' />"></script>

    <script src="<c:url value='/resources/vendors/Flot/jquery.flot.js' />"></script>
    <script src="<c:url value='/resources/vendors/Flot/jquery.flot.pie.js' />"></script>
    <script src="<c:url value='/resources/vendors/Flot/jquery.flot.time.js' />"></script>
    <script src="<c:url value='/resources/vendors/Flot/jquery.flot.stack.js' />"></script>
    <script src="<c:url value='/resources/vendors/Flot/jquery.flot.resize.js' />"></script>

    <script src="<c:url value='/resources/vendors/raphael/raphael.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/morris.js/morris.min.js' />"></script>


    <!-- Dropzone.js -->
    <script src="<c:url value='/resources/vendors/dropzone/dist/min/dropzone.min.js' />"></script>

     <!-- Custom Theme Scripts -->
    <script src="<c:url value='/resources/build/js/custom.min.js' />"></script>
    
   
    
    <script>
 	// Activate Next Step
    $(document).ready(function() {
        
        var navListItems = $('ul.setup-panel li a'),
            allWells = $('.setup-content');

        allWells.hide();

        navListItems.click(function(e)
        {
            e.preventDefault();
            var $target = $($(this).attr('href')),
                $item = $(this).closest('li');
            
            if (!$item.hasClass('disabled')) {
                navListItems.closest('li').removeClass('active');
                $item.addClass('active');
                allWells.hide();
                $target.show();
            }
        });
        
        $('ul.setup-panel li.active a').trigger('click');
        
        // DEMO ONLY //
        $('#activate-step-2').on('click', function(e) {
            $('ul.setup-panel li:eq(1)').removeClass('disabled');
            $('ul.setup-panel li a[href="#step-2"]').trigger('click');
            //$(this).remove();
        })
        
         $('#deactivate-step-2').on('click', function(e) {
            $('ul.setup-panel li:eq(1)').removeClass('active');
            $('ul.setup-panel li a[href="#step-1"]').trigger('click');
            //$(this).remove();
        })
        
        $('#activate-step-3').on('click', function(e) {	
        	if(!validateStep2()) {
        		$('#xForm2').validator('update');
				$('#xForm2').validator('validate');
			}
        	else {
				$('ul.setup-panel li:eq(2)').removeClass('disabled');
                $('ul.setup-panel li a[href="#step-3"]').trigger('click');
                
                $('#xForm2').validator('destroy');
                $('#xForm2').validator('update');
        	}
        	
        	var i, count = 0;
			$('input[id^="selectedExams"][id$="selected1"]').each(function() { count++ }); //Count Number Of Exams
        	for(i = 0; i <= count-1; i++) {
				if(!document.getElementById('selectedExams' + i + '.selected1').checked) {		
					/* Remove Validation on fields which are not selected */
					var answerSheetAmount = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").value;
					document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").removeAttribute("required");
					var answerSheetAmountParent = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").parentNode;
					var answerSheetAmountSpan = answerSheetAmountParent.getElementsByTagName("span")[0];
					$(answerSheetAmountParent).removeClass('has-success');
					$(answerSheetAmountSpan).removeClass('glyphicon-ok');
					if(!isEmpty(answerSheetAmount)) {
						$(answerSheetAmountParent).addClass('has-error');
						$(answerSheetAmountParent).addClass('has-danger');
						$(answerSheetAmountSpan).addClass('glyphicon-remove');
					}
										
					var studentsPerCSV = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").value;
					document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").removeAttribute("required");
		  			var studentsPerCSVParent = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").parentNode;
		  			var studentsPerCSVSpan = studentsPerCSVParent.getElementsByTagName("span")[0];
					$(studentsPerCSVParent).removeClass('has-success');
					$(studentsPerCSVSpan).removeClass('glyphicon-ok');
					if(!isEmpty(studentsPerCSV)) {
						$(studentsPerCSVParent).addClass('has-error');
						$(studentsPerCSVParent).addClass('has-danger');
						$(studentsPerCSVSpan).addClass('glyphicon-remove');
					}
							  			
		  			<c:if test="${orderForm.period eq 'June'}">
		  				var examAmount = document.getElementById("selectedExams" + i + ".orderExam.examAmount").value;
		  				document.getElementById("selectedExams" + i + ".orderExam.examAmount").removeAttribute("required");
		  				var examAmountParent = document.getElementById("selectedExams" + i + ".orderExam.examAmount").parentNode;
			  			var examAmountSpan = examAmountParent.getElementsByTagName("span")[0];
						$(examAmountParent).removeClass('has-success');
						$(examAmountSpan).removeClass('glyphicon-ok');
						if(!isEmpty(examAmount)) {
							$(examAmountParent).addClass('has-error');
							$(examAmountParent).addClass('has-danger');
							$(examAmountSpan).addClass('glyphicon-remove');
						}
	  				</c:if>
				}
			}
        })
        
         $('#deactivate-step-3').on('click', function(e) {
            $('ul.setup-panel li:eq(2)').removeClass('active');
            $('ul.setup-panel li a[href="#step-2"]').trigger('click');
            //$(this).remove();
        })
        
        $('#activate-step-4').on('click', function(e) {
            $('ul.setup-panel li:eq(3)').removeClass('disabled');
            $('ul.setup-panel li a[href="#step-4"]').trigger('click');
            //$(this).remove();
        })
        
         $('#deactivate-step-4').on('click', function(e) {
            $('ul.setup-panel li:eq(3)').removeClass('active');
            $('ul.setup-panel li a[href="#step-3"]').trigger('click');
        })

        
        $('#activate-step-5').on('click', function(e) {	
        	if(!validateStep4()) {
                $('#xForm2').validator('update');
				$('#xForm2').validator('validate');
			}
        	else {
        		 $('ul.setup-panel li:eq(4)').removeClass('disabled');
                 $('ul.setup-panel li a[href="#step-5"]').trigger('click');
                
                $('#xForm2').validator('destroy');
                $('#xForm2').validator('update');
        	}
        	
        	
        	var i, count = 0, checkedCount = 0, validCount = 0;
			$('input[id^="selectedDocuments"][id$="selected1"]').each(function() { count++ }); //Count Number Of Documents
			for(i = 0; i <= count-1; i++) {
				if(!document.getElementById('selectedDocuments' + i + '.selected1').checked) {
					document.getElementById("selectedDocuments" + i + ".orderDocument.documentAmount").removeAttribute("required");
					var documentAmount = document.getElementById("selectedDocuments" + i + ".orderDocument.documentAmount").value;
					var documentAmountParent = document.getElementById("selectedDocuments" + i + ".orderDocument.documentAmount").parentNode;
					var documentAmountSpan = documentAmountParent.getElementsByTagName("span")[0];
					$(documentAmountParent).removeClass('has-success');
					$(documentAmountSpan).removeClass('glyphicon-ok');
					
					if(!isEmpty(documentAmount)) {
						$(documentAmountParent).addClass('has-error');
						$(documentAmountParent).addClass('has-danger');
						$(documentAmountSpan).addClass('glyphicon-remove');
					}
				}
			}
        })
        
         $('#deactivate-step-5').on('click', function(e) {
            $('ul.setup-panel li:eq(4)').removeClass('active');
            $('ul.setup-panel li a[href="#step-4"]').trigger('click');
        })
        
        $('#activate-step-6').on('click', function(e) {
        	if(!validateStep5()) {
        		$('#xForm2').validator('update');
				$('#xForm2').validator('validate');
			}
        	else {
        		$('ul.setup-panel li:eq(5)').removeClass('disabled');
                $('ul.setup-panel li a[href="#step-6"]').trigger('click');
                
                $('#xForm2').validator('destroy');
                $('#xForm2').validator('update');
        	}
        })
        
         $('#deactivate-step-6').on('click', function(e) {
            $('ul.setup-panel li:eq(5)').removeClass('active');
            $('ul.setup-panel li a[href="#step-5"]').trigger('click');
            //$(this).remove();
        })
        
        $('#activate-step-7').on('click', function(e) {
        	if(!validateStep6()) {
        		$('#xForm2').validator('update');
				$('#xForm2').validator('validate');
			}
        	else {
        		 $('ul.setup-panel li:eq(6)').removeClass('disabled');
                 $('ul.setup-panel li a[href="#step-7"]').trigger('click');
                
                $('#xForm2').validator('destroy');
                $('#xForm2').validator('update');
        	}
        })
        
         $('#deactivate-step-7').on('click', function(e) {
            $('ul.setup-panel li:eq(6)').removeClass('active');
            $('ul.setup-panel li a[href="#step-6"]').trigger('click');
            //$(this).remove();
        })
    });

 
 	//TEST
 	function isEmpty(str) {
		    return (!str || 0 === str.length);
		}
		
		function isValidEmail(email) {
		    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		    return regex.test(String(email).toLowerCase());
		}
	
		function validateStep2() {
			var i, count = 0, checkedCount = 0, validCount = 0, uncheckedCount = 0;
			$('input[id^="selectedExams"][id$="selected1"]').each(function() { count++ }); //Count Number Of Exams
			
			for(i = 0; i <= count-1; i++) {
				if(document.getElementById('selectedExams' + i + '.selected1').checked) {	
					var answerSheetAmount = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").value;
					var studentsPerCSV = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").value;

					<c:choose> 
					  <c:when test="${orderForm.period eq 'June'}">
					 	 var examAmount = document.getElementById("selectedExams" + i + ".orderExam.examAmount").value;
					 	 
					 	 if(!isEmpty(examAmount) || !isEmpty(answerSheetAmount) || !isEmpty(studentsPerCSV)) {
					 		 
					 		if(isEmpty(examAmount)) {
					 			document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").removeAttribute("required");
				  				document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").removeAttribute("required");
				  			}
				  			if(isEmpty(answerSheetAmount)) {
				  				document.getElementById("selectedExams" + i + ".orderExam.examAmount").removeAttribute("required");
				  				document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").removeAttribute("required");
				  			}
				  			if(isEmpty(studentsPerCSV)) {
				  				document.getElementById("selectedExams" + i + ".orderExam.examAmount").removeAttribute("required");
				  				document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").removeAttribute("required");
				  			}
					 		validCount++;
				 		 }
					 	 else {
				  			document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").setAttribute("required", "required");
				  			document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").setAttribute("required", "required");
				  			document.getElementById("selectedExams" + i + ".orderExam.examAmount").setAttribute("required", "required");
						}
					  </c:when>
					  <c:otherwise>
				  		if(!isEmpty(answerSheetAmount) || !isEmpty(studentsPerCSV)) {
				  			
				  			if(!isEmpty(answerSheetAmount)) {
				  				document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").removeAttribute("required");
				  			}
				  			if(!isEmpty(studentsPerCSV)) {
				  				document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").removeAttribute("required");
				  			}
				  			
				  			validCount++;
				 		}
 				  		else {
				  			document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").setAttribute("required", "required");
				  			document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").setAttribute("required", "required");
						}
					  </c:otherwise>
					</c:choose>
					
					checkedCount++;
				}
				else {
					/* document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").removeAttribute("required");
		  			document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").removeAttribute("required");
		  			<c:if test="${orderForm.period eq 'June'}">
		  				document.getElementById("selectedExams" + i + ".orderExam.examAmount").removeAttribute("required");
	  				</c:if> */

	  				var answerSheetAmount = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").value;
					var studentsPerCSV = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").value;

					<c:choose> 
					  	<c:when test="${orderForm.period eq 'June'}">
					 	 	var examAmount = document.getElementById("selectedExams" + i + ".orderExam.examAmount").value;
					 	 	 if(!isEmpty(examAmount) || !isEmpty(answerSheetAmount) || !isEmpty(studentsPerCSV)) {
					 	 		uncheckedCount++; 
					 	 	 }
				 	 	</c:when>
					  	<c:otherwise>
						  	if(!isEmpty(answerSheetAmount) || !isEmpty(studentsPerCSV)) {
					 	 		uncheckedCount++; 
					 	 	 }
						</c:otherwise>
					</c:choose>
				}
			}
			
			if(uncheckedCount > 0) return false;
		    var isValid = (checkedCount == validCount);
			return isValid;
		} 
		
		
		
		function hasClass(ele,cls) {
		    return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
		}

		function removeClass(ele,cls) {
		        if (hasClass(ele,cls)) {
		            var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');
		            ele.className=ele.className.replace(reg,' ');
		        }
		    }
		
		function validateStep4() {
			var i, count = 0, checkedCount = 0, validCount = 0, uncheckedCount = 0;
			$('input[id^="selectedDocuments"][id$="selected1"]').each(function() { count++ }); //Count Number Of Documents

			for(i = 0; i <= count-1; i++) {
				if(document.getElementById('selectedDocuments' + i + '.selected1').checked) {
					checkedCount++;
					var docAmount = document.getElementById("selectedDocuments" + i + ".orderDocument.documentAmount").value;
					if(!isEmpty(docAmount)) {
						validCount++;
			 		}
					else {
						document.getElementById("selectedDocuments" + i + ".orderDocument.documentAmount").setAttribute("required", "required");
					}
				}
				else {
					//document.getElementById("selectedDocuments" + i + ".orderDocument.documentAmount").removeAttribute("required");
					var docAmount = document.getElementById("selectedDocuments" + i + ".orderDocument.documentAmount").value;
					if(!isEmpty(docAmount)) {
						uncheckedCount++; 
					}
				}
			}
			if(uncheckedCount > 0) return false;
			var isValid = (checkedCount == validCount);
			return isValid;
		} 
		
		function validateStep5() {
			var selectedOptionScanIndex = getRadioButtonIndex('selectedOptionScan');
			var selectedOptionScan = '';
			if(selectedOptionScanIndex != null) {
				selectedOptionScan = document.querySelector('label[for="selectedOptionScan' + selectedOptionScanIndex + '"]').textContent;
			}
			
			var isValid = !isEmpty(selectedOptionScan);
			//alert(isValid);
			return isValid;
		} 
		
		function validateStep6() {
			var firstName = document.getElementById("orderContact.firstName").value;
			var lastName = document.getElementById("orderContact.lastName").value;
			var title = document.getElementById("orderContact.title").value;
			var email = document.getElementById("orderContact.email").value;
			var phone = document.getElementById("orderContact.phone").value;
			
			var isValid = (!isEmpty(firstName) && !isEmpty(lastName) && !isEmpty(title) && isValidEmail(email) && !isEmpty(phone));
			//alert(isValid);
			return isValid;
		} 
 	
 	//END TEST
 
 

    // Add , Dlelete row dynamically

         $(document).ready(function(){
          var i=1;
         $("#add_row").click(function(){
          $('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='name"+i+"' type='text' placeholder='Name' class='form-control input-md'  /> </td><td><input  name='sur"+i+"' type='text' placeholder='Surname'  class='form-control input-md'></td><td><input  name='email"+i+"' type='text' placeholder='Email'  class='form-control input-md'></td><td><select type='text' name='gender"+i+"' class='form-control'><option name='male"+i+"' value='male'>Male</option><option name='Female"+i+"' value='Female'>Female</option><option name='3rdgen"+i+"' value='none'>None</option></select></td>");

          $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
          i++; 
      });
         $("#delete_row").click(function(){
        	 if(i>1){
    		 $("#addr"+(i-1)).html('');
    		 i--;
    		 }
    	 });

    });


    </script>
    
    
    
    <!-- jQuery Smart Wizard -->
    <script>
//       $(document).ready(function() {
//         $('#wizard').smartWizard({
//         	hideButtonsOnDisabled: true,
//         	labelFinish: 'Submit',
//         	onFinish: onFinishCallback
//         });

//         $('#wizard_horizontal').smartWizard({
//           transitionEffect: 'slide'
//         });
        
//         function onFinishCallback(){
//         	$('#form').submit();
          
//             alert('O.M.G. YOU SUBMITTED!');
//         } 

//         $('.buttonNext').addClass('btn btn-success');
//         $('.buttonPrevious').addClass('btn btn-primary');
//         $('.buttonFinish').addClass('btn btn-success');

//       });
    </script>
    <!-- /jQuery Smart Wizard -->

    <!-- Datatables -->
    <script>
      $(document).ready(function() {
        var handleDataTableButtons = function() {
          if ($("#datatable-buttons").length) {
            $("#datatable-buttons").DataTable({
              dom: "Bfrtip",
              buttons: [
                {
                  extend: "copy",
                  className: "btn-sm"
                },
                {
                  extend: "csv",
                  className: "btn-sm"
                },
                {
                  extend: "excel",
                  className: "btn-sm"
                },
                {
                  extend: "pdfHtml5",
                  className: "btn-sm"
                },
                {
                  extend: "print",
                  className: "btn-sm"
                },
              ],
              responsive: true
            });
          }
        };

        TableManageButtons = function() {
          "use strict";
          return {
            init: function() {
              handleDataTableButtons();
            }
          };
        }();

        $('#datatable').dataTable();

        $('#datatable-keytable').DataTable({
          keys: true
        });

        $('#datatable-responsive').DataTable();

        $('#datatable-scroller').DataTable({
          ajax: "js/datatables/json/scroller-demo.json",
          deferRender: true,
          scrollY: 380,
          scrollCollapse: true,
          scroller: true
        });

        $('#datatable-fixed-header').DataTable({
          fixedHeader: true
        });

        var $datatable = $('#datatable-checkbox');

        $datatable.dataTable({
          'order': [[ 1, 'asc' ]],
          'columnDefs': [
            { orderable: false, targets: [0] }
          ]
        });
        $datatable.on('draw.dt', function() {
          $('input').iCheck({
            checkboxClass: 'icheckbox_flat-green'
          });
        });

        TableManageButtons.init();
      });
    </script>
    <!-- /Datatables -->
    
   
    
  </body>
</html>