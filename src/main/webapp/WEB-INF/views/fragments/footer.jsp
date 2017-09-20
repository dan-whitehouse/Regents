<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- footer content -->
        <footer>
          <div class="pull-right">
<!--             Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a> -->
			<p style="font-size:13px;"> NERIC - Northeaster Regional Information Center</p>
			<p style="font-size:10px;">900 Watervliet-Shaker Road | Albany, New York 12205</p>
<!-- 			<p style="font-size:10px;">Tel: 518.862.5300 | Fax: 518.862.5378</p> -->
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>
</body>
</html>
    <!-- jQuery -->
    <script src="<c:url value='/resources/vendors/jquery/dist/jquery.min.js' />"></script>
    
    <!-- Bootstrap -->    
    <script src="<c:url value='/resources/vendors/bootstrap/dist/js/bootstrap.min.js' />"></script>
    
    <!-- FastClick -->    
    <script src="<c:url value='/resources/vendors/fastclick/lib/fastclick.js' />"></script>
    
    <!-- NProgress -->   
    <script src="<c:url value='/resources/vendors/nprogress/nprogress.js' />"></script>
    
    <!-- jQuery Smart Wizard -->   
    <script src="<c:url value='/resources/vendors/jQuery-Smart-Wizard/js/jquery.smartWizard.js' />"></script>

    <!-- validator -->
    <script src="<c:url value='/resources/vendors/validator/validator.js' />"></script>
    
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
    <script src="<c:url value='/resources/vendors/datatables.net-scroller/js/datatables.scroller.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/jszip/dist/jszip.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/pdfmake/build/pdfmake.min.js' />"></script>
    <script src="<c:url value='/resources/vendors/pdfmake/build/vfs_fonts.js' />"></script>
       
    <script src="<c:url value='/resources/vendors/iCheck/icheck.min.js'/>"></script>
    
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
            $(this).remove();
        })
        
        $('#activate-step-3').on('click', function(e) {
            $('ul.setup-panel li:eq(2)').removeClass('disabled');
            $('ul.setup-panel li a[href="#step-3"]').trigger('click');
            $(this).remove();
        })
        
        $('#activate-step-4').on('click', function(e) {
            $('ul.setup-panel li:eq(3)').removeClass('disabled');
            $('ul.setup-panel li a[href="#step-4"]').trigger('click');
            $(this).remove();
        })
        
       /*  $('#activate-step-3').on('click', function(e) {
            $('ul.setup-panel li:eq(2)').removeClass('disabled');
            $('ul.setup-panel li a[href="#step-3"]').trigger('click');
            $(this).remove();
        }) */
        
        $('#activate-step-5').on('click', function(e) {
            $('ul.setup-panel li:eq(4)').removeClass('disabled');
            $('ul.setup-panel li a[href="#step-5"]').trigger('click');
            $(this).remove();
        })
        
        $('#activate-step-6').on('click', function(e) {
            $('ul.setup-panel li:eq(5)').removeClass('disabled');
            $('ul.setup-panel li a[href="#step-6"]').trigger('click');
            $(this).remove();
        })
        
        $('#activate-step-7').on('click', function(e) {
            $('ul.setup-panel li:eq(6)').removeClass('disabled');
            $('ul.setup-panel li a[href="#step-7"]').trigger('click');
            $(this).remove();
        })
    });


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
    
     <!-- validator -->
    <script>
      // initialize the validator function
      validator.message.date = 'not a real date';

      // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
      $('form')
        .on('blur', 'input[required], input.optional, select.required', validator.checkField)
        .on('change', 'select.required', validator.checkField)
        .on('keypress', 'input[required][pattern]', validator.keypress);

      $('.multi.required').on('keyup blur', 'input', function() {
        validator.checkField.apply($(this).siblings().last()[0]);
      });

      $('form').submit(function(e) {
        e.preventDefault();
        var submit = true;

        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
          submit = false;
        }

        if (submit)
          this.submit();

        return false;
      });
    </script>
    <!-- /validator -->
    
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