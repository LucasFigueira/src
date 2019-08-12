
	$('#cadastrar1').click(function(){
	var contador = 0;
	var form1 = $('#formCadastro').serializeArray();
	for(i = 0;i < form1.length ;i++){
		if(form1[i].value == ""){
			contador++;
		}
	}
	
	if(contador == 9 && form1.length < 12){
		alert('Preencha pelo menos um campo');
	}else{
		console.log(contador);
		$('#confirm-delete').modal();
	}
});