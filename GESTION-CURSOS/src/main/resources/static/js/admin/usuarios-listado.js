
$('#modal-delete').on('show.bs.modal', function (event) {
  
	var button = $(event.relatedTarget) // Button that triggered the modal
	var id = button.data('id')
	var usuario = button.data('usuario')
  
	var modal = $(this)
	
	modal.find('.modal-body p').html(
		'Esta seguro que desea eliminar permanentemente al usuario: <br><strong>[' + id + '] ' + usuario + '</strong>?');
		
	modal.find('.btn-delete-confirm').click(function() {
		console.log("/admin/usuarios/" + id);
		$.ajax({
			url: "/admin/usuarios/" + id,
			type: 'DELETE',
			success: function( result ) {
				button.parent().parent().parent().remove();
	    		$('#modal-delete').modal('toggle')
			}
		});
	});
	
});
