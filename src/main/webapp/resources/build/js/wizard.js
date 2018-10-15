/* Puts the wizard in a tab format and allows stepping through the tabs */
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
});


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
					  			
			var period = document.getElementById("hiddenPeriod").value;
			if(period === 'June') {
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
			}
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


/** Validation For Each Step **/
function validateStep2() {
	var i, count = 0, checkedCount = 0, validCount = 0, uncheckedCount = 0;
	$('input[id^="selectedExams"][id$="selected1"]').each(function() { count++ }); //Count Number Of Exams
	
	for(i = 0; i <= count-1; i++) {
		if(document.getElementById('selectedExams' + i + '.selected1').checked) {	
			var answerSheetAmount = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").value;
			var studentsPerCSV = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").value;

			var period = document.getElementById("hiddenPeriod").value;

			  if(period === 'June') {
				  var examAmount = document.getElementById("selectedExams" + i + ".orderExam.examAmount").value;
				  
				 	 if(!isEmpty(examAmount) || !isEmpty(answerSheetAmount) || !isEmpty(studentsPerCSV)) {
				 		if(isEmpty(examAmount)) {
				 			document.getElementById("selectedExams" + i + ".orderExam.examAmount").removeAttribute("required");
			  			}
			  			if(isEmpty(answerSheetAmount)) {
			  				document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").removeAttribute("required")
			  			}
			  			if(isEmpty(studentsPerCSV)) {
			  				document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").removeAttribute("required");
			  			}
				 		validCount++;
			 		 }
				 	 else {
			  			document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").setAttribute("required", "required");
			  			document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").setAttribute("required", "required");
			  			document.getElementById("selectedExams" + i + ".orderExam.examAmount").setAttribute("required", "required");
					}
			  }
			  else {
				  if(!isEmpty(answerSheetAmount) || !isEmpty(studentsPerCSV)) {
			  			
			  			if(isEmpty(answerSheetAmount)) {
			  				document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").removeAttribute("required");
			  			}
			  			if(isEmpty(studentsPerCSV)) {
			  				document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").removeAttribute("required");
			  			}
			  			validCount++;
			 		}
			  		else {
			  			document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").setAttribute("required", "required");
			  			document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").setAttribute("required", "required");
					}
			  }
			
			checkedCount++;
		}
		else {
			var answerSheetAmount = document.getElementById("selectedExams" + i + ".orderExam.answerSheetAmount").value;
			var studentsPerCSV = document.getElementById("selectedExams" + i + ".orderExam.studentsPerCSV").value;

		  	if(hiddenPeriod === 'June') {
		  		var examAmount = document.getElementById("selectedExams" + i + ".orderExam.examAmount").value;
		 	 	 if(!isEmpty(examAmount) || !isEmpty(answerSheetAmount) || !isEmpty(studentsPerCSV)) {
		 	 		uncheckedCount++; 
		 	 	 }
		  	}
		  	else {
		  		if(!isEmpty(answerSheetAmount) || !isEmpty(studentsPerCSV)) {
		 	 		uncheckedCount++; 
		 	 	 }
		  	}
		}
	}
	
	if(uncheckedCount > 0) return false;
    var isValid = (checkedCount == validCount);
	return isValid;
} 


/** Checks to make sure all required Non-Secure Documents information has been entered. 
 *  Ie: If a check box is selected, the quantity text field must have a value.
**/
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


/** Checks to see if the radio button has been chosen. **/
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
	
/** Checks to make sure all required Contact information has been entered. **/
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


/** Utility Functions **/
function isEmpty(str) {
    return (!str || 0 === str.length);
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

function isValidEmail(email) {
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(String(email).toLowerCase());
}