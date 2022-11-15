var globBtnId = '';
var globStudentId = 0;
var globPaymentId = 0;
$(function () {
    $('body').layout({applyDemoStyles: true});
    $('.ui-layout-center,.ui-layout-east,.ui-layout-north,.ui-layout-south,.ui-layout-west').css("background-color", "aqua");

    $('.btnDesign').click(function () {
        var btnId = $(this).attr('id');
        globBtnId = btnId;
        $('#keywordId').val('');
    })
    $('#keywordId').keyup(function () {
        var keyword = $(this).val();
        switch (globBtnId) {
            case "studentDataBtnId":
                searchStudentData(keyword);
                break;
            case "teacherDataBtnId":
                break;
            case"lessonBtnId":
                break;
            case"paymentBtnId":
                searchPaymentData(keyword);
                break;
            default:
                alert("Please select menu!");
        }
    })
    $('#newBtnId').click(function () {
        switch (globBtnId) {
            case "studentDataBtnId":
                $('#newStudentDialogId').load('views/newStudent.jsp', function () {
                    $(this).dialog("open");
                });
                break;
            case "teacherDataBtnId":
                $('#newTeacherDialogId').dialog("open");
                break;
            case"lessonBtnId":
                alert("New lesson!")
                break;
            case"paymentBtnId":
                $('#newPaymentDialogId').load('cs?action=newPayment', function () {
                    $(this).dialog("open");
                    // getStudentCombo();
                    // getTeacherCombo();
                    // getLessonCombo();
                })
                break;
            default:
                alert("Please select menu!");
        }
    })


    $('#newStudentDialogId').dialog({
        title: 'New Student',
        autoOpen: false,
        height: 400,
        width: 400,
        modal: true,
        buttons:
            {
                "Save": function () {
                    addStudent();
                    $(this).dialog('close');
                },
                "Close": function () {
                    $(this).dialog('close');

                }
            }
    })
    $('#newTeacherDialogId').dialog({
        title: 'New Teacher',
        autoOpen: false,
        height: 400,
        width: 400,
        modal: true,
        buttons:
            {
                "Save": function () {
                    addStudent();
                    $(this).dialog('close');
                },
                "Close": function () {
                    $(this).dialog('close');

                }
            }
    })
    $('#newPaymentDialogId').dialog({
        title: 'New Payment',
        autoOpen: false,
        height: 350,
        width: 400,
        modal: true,
        buttons:
            {
                "Save": function () {
                    addPayment();
                    $(this).dialog('close');
                },
                "Close": function () {
                    $(this).dialog('close');

                }
            }
    })

    $('#studentDataBtnId').click(function () {
        getStudentList();
    })
    $('#paymentBtnId').click(function () {
        getPaymentList();
    })


    function addStudent() {
        var name = $('#sName').val();
        var surname = $('#sSurname').val();
        var address = $('#sAddress').val();
        var dob = $('#dobId').val();
        var phone = $('#phoneId').val();
        var email = $('#emailId').val();
        if (name.trim().length == 0 || surname.trim().length == 0) {
            alert("Please fill in!")
            return;
        }
        var data = {
            name: name,
            surname: surname,
            address: address,
            dob: dob,
            phone: phone,
            email: email
        };
        $.ajax({
            url: 'cs?action=addStudent',
            type: 'POST',
            data: data,
            dataType: 'text',
            success: function (response) {
                if (response == 'success') {
                    alert("Student has been succesfully added!");
                    getStudentList();
                } else {
                    alert("Problem! Student has not  been succesfully added!");
                }
            }
        })
    }


    function addPayment() {
        var studentCombo = $('#studentComboId').val();
        var teacherCombo = $('#teacherComboId').val();
        var lessonCombo = $('#lessonComboId').val();
        var amount = $('#amountId').val();
        alert(studentCombo + " " + teacherCombo + " " + lessonCombo + " " + amount);
        var data = {
            studentCombo: studentCombo,
            teacherCombo: teacherCombo,
            lessonCombo: lessonCombo,
            amount: amount
        }
        $.ajax({
            url: 'cs?action=addPayment',
            type: 'POST',
            data: data,
            dataType: 'text',
            success: function (response) {
                if (response == 'success') {
                    alert("Payment has been succesfully added!");
                    getPaymentList();
                    $('#newPaymentDialogId').dialog('close');
                } else {
                    alert("Problem! Payment has not  been succesfully added!");
                }
            }
        })
    }

    $('#searchBtnId').click(function () {
        var keyword = $('#keywordId').val();
        switch (globBtnId) {
            case "studentDataBtnId":
                searchStudentData(keyword);
                break;
            case "teacherDataBtnId":
                break;
            case"lessonBtnId":
                break;
            case"paymentBtnId":
                searchPaymentData(keyword);
                break;
            default:
                alert("Please select menu!");
        }
    })

})

function editStudent(studentId) {
    globStudentId = studentId;
    $.ajax({
        url: 'cs?action=editStudent',
        type: 'GET',
        data: 'studentId=' + studentId,
        dataType: 'html',
        success: function (data) {
            $('#editStudentDialogId').html(data);
            $('#editStudentDialogId').dialog('open');
        }
    });
    $('#editStudentDialogId').dialog({
        title: 'Update Student',
        autoOpen: false,
        height: 350,
        width: 400,
        modal: true,
        buttons:
            {
                "Update": function () {
                    updateStudent();
                    $(this).dialog('close');
                },
                "Close": function () {
                    $(this).dialog('close');

                }
            }
    })
}

function updateStudent() {
    var name = $('#sName1').val();
    var surname = $('#sSurname1').val();
    var address = $('#sAddress1').val();
    var dob = $('#dobId1').val();
    var phone = $('#phoneId1').val();
    var email = $('#emailId1').val();
    if (name.trim().length == 0 || surname.trim().length == 0) {
        alert("Please fill in!")
        return;
    }
    var data = {
        name: name,
        surname: surname,
        address: address,
        dob: dob,
        phone: phone,
        email: email,
        studentId: globStudentId
    };
    $.ajax({
        url: 'cs?action=updateStudent',
        type: 'POST',
        data: data,
        dataType: 'TEXT',
        success: function (response) {
            if (response == 'success') {
                alert("Student has been succesfully updated!");
                getStudentList();
            } else {
                alert("Problem! Student has not  been succesfully updated!");
            }
        }
    })
}

function deleteStudent(studentId, studentFullName) {
    var isDelete = confirm('Are you sure?')
    if (!isDelete) {
        alert(studentFullName + ' has not been deleted succesfully!');
        return;
    }
    $.ajax({
        url: 'cs?action=deleteStudent',
        type: 'POST',
        data: 'studentId=' + studentId,
        dataType: 'text',
        success: function (data) {
            if (data == 'success') {
                alert("Student has been succesfully deleted!");
                getStudentList();
            } else {
                alert("Problem! Student has not  been succesfully deleted!!");
            }
        }
    })
}

function getStudentList() {
    $.ajax({
        type: 'post',
        url: 'cs?action=getStudentListAjax',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);

        },
        complete: function () {

        },
        error: function () {

        }
    })
}

function editPayment(paymentId) {
    globPaymentId = paymentId;
    $.ajax({
        url: 'cs?action=editPayment',
        type: 'Get',
        data: "paymentId=" + paymentId,
        dataType: 'html',
        success: function (response) {
            $('#updatePaymentDialogId').html(response);
            $('#updatePaymentDialogId').dialog('open');

        },
        error: function (err) {
            console.log(err)
        }

    });


    $('#updatePaymentDialogId').dialog({
        title: 'Update Payment',
        autoOpen: false,
        height: 400,
        width: 400,
        modal: true,
        buttons:
            {
                "Update": function () {
                    updatePayment();
                    $(this).dialog('close');
                },
                "Close": function () {
                    $(this).dialog('close');

                }
            }
    })
}

function updatePayment() {
    var studentCombo = $('#studentComboId1').val();
    var teacherCombo = $('#teacherComboId1').val();
    var lessonCombo = $('#lessonComboId1').val();
    var amount = $('#amountId1').val();
    var data = {
        studentCombo: studentCombo,
        teacherCombo: teacherCombo,
        lessonCombo: lessonCombo,
        amount: amount,
        paymentId: globPaymentId
    }
    $.ajax({
        url: 'cs?action=updatePayment',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Payment has been succesfully updated!");
                getPaymentList();
                $('#newPaymentDialogId').dialog('close');
            } else {
                alert("Problem! Payment has not  been succesfully updated!");
            }
        }
    });
}

function getPaymentList() {
    $.ajax({
        url: 'cs?action=getPaymentList',
        type: 'GET',
        dataType: 'HTML',
        success: function (data) {
            $('.ui-layout-center').html(data);

        }

    })

}

function deletePayment(paymentId) {

}

function searchStudentData(keyword) {
    $.ajax({
        url: 'cs?action=searchStudentData',
        type: 'GET',
        data: 'keyword=' + keyword,
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        }

    });
}

function searchPaymentData(keyword) {
    $.ajax({
        url: 'cs?action=searchPaymentData',
        type: 'GET',
        data: 'keyword=' + keyword,
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        }

    });
}

function advancedSearchPaymentData() {
    var advLessonCombo = $('#advLessonComboId').val();
    var advTeacherCombo = $('#advTeacherComboId').val();
    var minAmount = $('#minAmountId').val();
        var maxAmount=$('#maxAmountId').val();
        var beginDate=$('#beginDateId').val();
        var endDate=$('#endDateId').val();
        var data={
            advLessonCombo:advLessonCombo,
            advTeacherCombo:advTeacherCombo,
            minAmount:minAmount,
            maxAmount:maxAmount,
            beginDate:beginDate,
            endDate:endDate

        };

    $.ajax({
        url: 'cs?action=advancedSearchPaymentData',
        type: 'GET',
        data: data,
        dataType: 'html',
        success: function (response) {
            $('#paymentTableDivId').html(response);
        }

    });
}