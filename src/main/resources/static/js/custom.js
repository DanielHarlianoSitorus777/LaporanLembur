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