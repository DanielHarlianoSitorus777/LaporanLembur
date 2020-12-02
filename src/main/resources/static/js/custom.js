$(document).ready(function() {
    $('tb').DataTable();
} );

function willDeleteEmployee(event) {
    event.preventDefault();
    var url = event.currentTarget.getAttribute("href");
    
    swal({
        title: "Apakah Anda yakin?",
        text: "Data akan dihapus secara permanen.",
        icon: "warning",
        buttons: true,
        dangerMode: true
    }).then(function(willDelete) {
       if(willDelete) {
           console.log("Done");
           window.location.href = url;
       } else {
           console.log("Cancel");
       }
    });
}

function saveNotification() {
    Swal.fire({
    icon: 'success',
    title: 'Telah tersimpan',
    showConfirmButton: false,
    position: 'top-end',
    timer: 150000
})
}

var getUrlParameter = function getUrlParameter(sParam) {

    var sPageURL = window.location.search.substring(1), // mendapatkan parameter tanpa menggunakan ?
            sURLVariables = sPageURL.split('&'), // split parameter yg lebih dari satu
            sParameterName, // var kosong untuk menampung key
            i;                                              // loop

    // result=update_success
    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');       // result, update_success

        if (sParameterName[0] === sParam) {                 // result, dibandingkan dengan input parameter
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]); // ketika null return true, ketika notnull return value
        }
    }
};

if (getUrlParameter("result") === "update_success") {
    Swal.fire({
        icon: 'success',
        title: 'Data Added!',
        showConfirmButton: false,
        timer: 1500
    });
}

if (getUrlParameter("result") === "data_deleted") {
    Swal.fire({
        icon: 'warning',
        title: 'Data Deleted!',
        showConfirmButton: false,
        timer: 1500
    });
}

if (getUrlParameter("result") === "report_confirmed") {
    Swal.fire({
        icon: 'success',
        title: 'Report Confirm Success!',
        showConfirmButton: false,
        timer: 1500
    });
}