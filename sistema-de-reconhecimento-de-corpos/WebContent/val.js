 
var visibilidade = false; //Variável que vai manipular o botão Exibir/ocultar
 
function exibir() {
    document.getElementById("dvConteudo").style.display = "block";

}
 
function ocultar() {
    document.getElementById("dvConteudo").style.display = "none";
}
 
function ocultarExibir() { // Quando clicar no botão.
 
    if (visibilidade) {//Se a variável visibilidade for igual a true, então...
        document.getElementById("dvConteudo").style.display = "none";//Ocultamos a div
        visibilidade = false;//alteramos o valor da variável para falso.
    } else {//ou se a variável estiver com o valor false..
        document.getElementById("dvConteudo").style.display = "block";//Exibimos a div..
        visibilidade = true;//Alteramos o valor da variável para true.
       
        var imagem0 = document.getElementById("imagem0");
        var imagem1 = document.getElementById("imagem1");
        var imagem2 = document.getElementById("imagem2");
        var imagem3 = document.getElementById("imagem3");
        var imagem4 = document.getElementById("imagem4");
        var imagem5 = document.getElementById("imagem5");
        var imagem6 = document.getElementById("imagem6");
        var imagem7 = document.getElementById("imagem7");
        var imagem8 = document.getElementById("imagem8");

        console.log(imagem0.src);
        console.log(imagem0);
        if(imagem0.src == "http://localhost:8080/jsf/-"){
        	 document.getElementById("0").style.display = "none";
        }
        if(imagem1.src == "http://localhost:8080/jsf/-"){
       	 document.getElementById("1").style.display = "none";
       }
        if(imagem2.src == "http://localhost:8080/jsf/-"){
       	 document.getElementById("2").style.display = "none";
       }
        
        if(imagem3.src == "http://localhost:8080/jsf/-"){
       	 document.getElementById("3").style.display = "none";
       }
        if(imagem4.src == "http://localhost:8080/jsf/-"){
       	 document.getElementById("4").style.display = "none";
       }
        if(imagem5.src == "http://localhost:8080/jsf/-"){
       	 document.getElementById("5").style.display = "none";
       }
        if(imagem6.src == "http://localhost:8080/jsf/-"){
       	 document.getElementById("6").style.display = "none";
       }
        if(imagem7.src == "http://localhost:8080/jsf/-"){
      	 document.getElementById("7").style.display = "none";
        }
        if(imagem8.src == "http://localhost:8080/jsf/-"){
      	 document.getElementById("8").style.display = "none";
        }
    
    }
}
function alterarOssada(){
	
	
	console.log("HEHEHEHEHEHEHEHEHEH");
	var altura = document.getElementById("altura");
	altura.disabled= false;
	
	var faixaEtaria = document.getElementById("faixaEtaria");
	faixaEtaria.disabled= false;
	
	var peso = document.getElementById("peso");
	peso.disabled= false;
	
	var corPele = document.getElementById("corPele");
	corPele.disabled= false;
	
	var corCabelo = document.getElementById("corCabelo");
	corCabelo.disabled= false;
	
	var tipoCabelo = document.getElementById("tipoCabelo");
	tipoCabelo.disabled= false;
	
	var dataChegada = document.getElementById("dataChegada");
	dataChegada.disabled= false;
	
	var horaChegada = document.getElementById("horaChegada");
	horaChegada.disabled= false;
	
	var descricao   = document.getElementById("descricao");
	descricao.disabled= false;
	
	var tattooRosto = document.getElementById("incisivo");
	tattooRosto.disabled= false;
	
	var cicatrizPe = document.getElementById("tattooPeito");
	cicatrizPe.disabled= false;
	
	var cicatrizPerna = document.getElementById("tattooCostas");
	cicatrizPerna.disabled= false;
	
	var cicatrizBraco = document.getElementById("tattooBraco");
	cicatrizBraco.disabled= false;
	
	var cicatrizCostas = document.getElementById("tattooPerna");
	cicatrizCostas.disabled= false;
	
	var cicatrizPeito = document.getElementById("permanentes");
	cicatrizPeito.disabled= false;
	
	
	
    window.scrollTo(0, 0);
	 
}
function alterarCorpo(){
	
	var sexoAux = document.getElementById("sexo:0");
	sexoAux.disabled= false;
	var sexoAux1 = document.getElementById("sexo:1");
	sexoAux1.disabled= false;
	
	var altura = document.getElementById("altura");
	altura.disabled= false;
	
	var faixaEtaria = document.getElementById("faixaEtaria");
	faixaEtaria.disabled= false;
	
	var peso = document.getElementById("peso");
	peso.disabled= false;
	
	var corPele = document.getElementById("corPele");
	corPele.disabled= false;
	
	var corCabelo = document.getElementById("corCabelo");
	corCabelo.disabled= false;
	
	var tipoCabelo = document.getElementById("tipoCabelo");
	tipoCabelo.disabled= false;
	
	var corOlhos = document.getElementById("corOlhos");
	corOlhos.disabled= false;
	
	var dataChegada = document.getElementById("dataChegada");
	dataChegada.disabled= false;
	
	var horaChegada = document.getElementById("horaChegada");
	horaChegada.disabled= false;
	
	var descricao   = document.getElementById("descricao");
	descricao.disabled= false;
	
	var destino   = document.getElementById("destino");
	destino.disabled= false;
	
	var tattooRosto = document.getElementById("tattooRosto");
	tattooRosto.disabled= false;
	
	var cicatrizPe = document.getElementById("cicatrizPe");
	cicatrizPe.disabled= false;
	
	var cicatrizPerna = document.getElementById("cicatrizPerna");
	cicatrizPerna.disabled= false;
	
	var cicatrizBraco = document.getElementById("cicatrizBraco");
	cicatrizBraco.disabled= false;
	
	var cicatrizCostas = document.getElementById("cicatrizCostas");
	cicatrizCostas.disabled= false;
	
	var cicatrizPeito = document.getElementById("cicatrizPeito");
	cicatrizPeito.disabled= false;
	
	var cicatrizRosto = document.getElementById("cicatrizRosto");
	cicatrizRosto.disabled= false;
	
	var tattooPe = document.getElementById("tattooPe");
	tattooPe.disabled= false;
	
	var tattooPerna = document.getElementById("tattooPerna");
	tattooPerna.disabled= false;
	
	var tattooBraco = document.getElementById("tattooBraco");
	tattooBraco.disabled= false;
	
	var tattooCostas = document.getElementById("tattooCostas");
	tattooCostas.disabled= false;
	
	var tattooPeito = document.getElementById("tattooPeito");
	tattooPeito.disabled= false;
	
	var confirmar = document.getElementById("confirmar");
	confirmar.classList.remove("disp"); 
	
    window.scrollTo(0, 0);
	 
}
var confirmar = document.getElementById("confirmar");

var sexoAux = document.getElementById("sexo:1");
var sexoAux1 = document.getElementById("sexo:0");
var altura = document.getElementById("altura");
var faixaEtaria = document.getElementById("faixaEtaria");
var peso = document.getElementById("peso");
var corPele = document.getElementById("corPele");
var corCabelo = document.getElementById("corCabelo");
var tipoCabelo = document.getElementById("tipoCabelo");
var corOlhos = document.getElementById("corOlhos");
var dataChegada = document.getElementById("dataChegada");
var horaChegada = document.getElementById("horaChegada");
var descricao   = document.getElementById("descricao");
var destino   = document.getElementById("destino");
var tattooRosto = document.getElementById("tattooRosto");
var cicatrizPe = document.getElementById("cicatrizPe");
var cicatrizPerna = document.getElementById("cicatrizPerna");
var cicatrizBraco = document.getElementById("cicatrizBraco");
var cicatrizCostas = document.getElementById("cicatrizCostas");
var cicatrizPeito = document.getElementById("cicatrizPeito");
var cicatrizRosto = document.getElementById("cicatrizRosto");
var tattooPe = document.getElementById("tattooPe");
var tattooPerna = document.getElementById("tattooPerna");
var tattooBraco = document.getElementById("tattooBraco");
var tattooCostas = document.getElementById("tattooCostas");
var tattooPeito = document.getElementById("tattooPeito");
var identificado = document.getElementById("identificado"); 
var latitude =  document.getElementById("latitudeAux"); 
var longitude =  document.getElementById("longitudeAux"); 
var identificar = document.getElementById("simIdent");
var divIdentificar = document.getElementById("divIdentificarCorpo");

var valorInicialAltura = altura.value;
var valorInicialPeso = peso.value;
var valorInicialFaixaEtaria = faixaEtaria.value;
var valorInicialcorPele = corPele.value;
var valorInicialcorCabelo = corCabelo.value;
var valorInicialtipoCabelo = tipoCabelo.value;
var valorInicialcorOlhos = corOlhos.value;
var valorInicialdataChegada = dataChegada.value;
var valorInicialhoraChegada = horaChegada.value;
var valorInicialtattooRosto = tattooRosto.checked; 
var valorInicialDescricao = descricao.value;
var valorInicialDestino = destino.value;
var valorInicialcicatrizPe = cicatrizPe.checked;
var valorInicialcicatrizPerna = cicatrizPerna.checked;
var valorInicialcicatrizBraco = cicatrizBraco.checked;
var valorInicialcicatrizCostas = cicatrizCostas.checked;
var valorInicialcicatrizPeito = cicatrizPeito.checked;
var valorInicialcicatrizRosto = cicatrizRosto.checked;
var valorInicialtattooPe = tattooPe.checked;
var valorInicialtattooPerna = tattooPerna.checked;
var valorInicialtattooBraco = tattooBraco.checked;
var valorInicialtattooCostas = tattooCostas.checked;
var valorInicialtattooPeito= tattooPeito.checked;
 
altura.addEventListener("change", function(event){ 
	confirmar.classList.remove("disp"); 
});

identificar.addEventListener("click", function(event){
	 console.log(identificar);
	divIdentificar.classList.remove("disp");
 
});


faixaEtaria.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

sexoAux.addEventListener("change",function(event){
	confirmar.classList.remove("disp");
});
sexoAux1.addEventListener("change",function(event){
	confirmar.classList.remove("disp");
});


peso.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});


corPele.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

corCabelo.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

tipoCabelo.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

corOlhos.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});


dataChegada.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});


horaChegada.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

descricao.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

destino.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

tattooRosto.addEventListener("change", function(event){
	console.log(tattooRosto.checked);
	confirmar.classList.remove("disp");
 
});


cicatrizPe.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});


cicatrizPerna.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});


cicatrizBraco.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});


cicatrizCostas.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

tattooPe.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});



cicatrizPeito.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

cicatrizRosto.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

tattooPerna.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

tattooBraco.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

tattooCostas.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});

tattooPeito.addEventListener("change", function(event){
	confirmar.classList.remove("disp");
 
});
 
function confirmarAlteracao(){

	var flags = new Array(23);
	for(var i = 0; i < 23; i++){
		flags[i] = 0;
		
	}
	if(valorInicialAltura == altura.value){
		flags[0] = 1;
	}
	if(valorInicialPeso == peso.value){
		flags[1] = 1;
	}
	if(valorInicialFaixaEtaria == faixaEtaria.value){
		flags[2] = 1;
	}
	if(valorInicialcorPele == corPele.value){
		flags[3] = 1;
	}
	if(valorInicialcorOlhos == corOlhos.value){
		flags[4] = 1;
	}
	if(valorInicialdataChegada == dataChegada.value){
		flags[5] = 1;
	}
	if(valorInicialhoraChegada == horaChegada.value){
		flags[6] = 1;
	}
	if(valorInicialtattooRosto == tattooRosto.checked){
		flags[7] = 1;
	}
	if(valorInicialcicatrizPe == cicatrizPe.checked){
		flags[8] = 1;
	}
	if(valorInicialcicatrizPerna == cicatrizPerna.checked){
		flags[9] = 1;
	}
	if(valorInicialcicatrizBraco == cicatrizBraco.checked){
		flags[10] = 1;
	}
	if(valorInicialcicatrizCostas == cicatrizCostas.checked){
		flags[11] = 1;
	}
	if(valorInicialcicatrizPeito == cicatrizPeito.checked){
		flags[12] = 1;
	}
	if(valorInicialcicatrizRosto == cicatrizRosto.checked){
		flags[13] = 1;
	}
	if(valorInicialtattooPe == tattooPe.checked){
		flags[14] = 1;
	}
	if(valorInicialtattooPerna == tattooPerna.checked){
		flags[15] = 1;
	}
	if(valorInicialtattooBraco == tattooBraco.checked){
		flags[16] = 1;
	}
	if(valorInicialtattooCostas == tattooCostas.checked){
		flags[17] = 1;
	}
	if(valorInicialtattooPeito == tattooPeito.checked){
		flags[18] = 1;
	}
	if(valorInicialDescricao == descricao.value){
		flags[19] = 1;
	}
	if(valorInicialDestino == destino.value){
		flags[20] = 1;
	}
	if(valorInicialcorCabelo == corCabelo.value){
		flags[21] = 1;
	}
	if(valorInicialtipoCabelo == tipoCabelo.value){
		flags[22] = 1;
	}
	if(document.getElementById("sexo:1").checked){
		if(document.getElementById("sexoAux").value == document.getElementById("sexo:1").value){
			 flags[23] = 1;
		 }
	}
	if(document.getElementById("sexo:0").checked){
		 if(document.getElementById("sexoAux").value == document.getElementById("sexo:0").value){
			 flags[23] = 1;
		 }
	 }
	 
	 document.getElementById("identificado").value = identificado.value;
	 document.getElementById("latitudeAux").value = latitude.value;
	 document.getElementById("longitudeAux").value = longitude.value;
	 document.getElementById("faixaEtariaAux").value = faixaEtaria.value;
	 document.getElementById("descricaoAux").value = descricao.value;
	 document.getElementById("destinoAux").value = destino.value;
	 document.getElementById("pesoAux").value = peso.value;
	 document.getElementById("corOlhosAux").value = corOlhos.value;
	 document.getElementById("corCabeloAux").value = corCabelo.value;
	 document.getElementById("tipoCabeloAux").value = tipoCabelo.value;
	 document.getElementById("corPeleAux").value = corPele.value;
	 document.getElementById("alturaAux").value = altura.value;
	 document.getElementById("dataChegadaAux").value = dataChegada.value;
	 document.getElementById("horarioChegadaAux").value = horaChegada.value;
	 
	 document.getElementById("tattooRostoAux").value = tattooRosto.checked;
	 document.getElementById("tattooPeAux").value = tattooPe.checked;
	 document.getElementById("tattooPernaAux").value = tattooPerna.checked;
	 document.getElementById("tattooBracoAux").value = tattooBraco.checked;
	 document.getElementById("tattooCostasAux").value = tattooCostas.checked;
	 document.getElementById("tattooPeitoAux").value = tattooPeito.checked;
	 
	 document.getElementById("cicatrizRostoAux").value = cicatrizRosto.checked;
	 document.getElementById("cicatrizPeitoAux").value = cicatrizPeito.checked;
	 document.getElementById("cicatrizCostasAux").value = cicatrizCostas.checked;
	 document.getElementById("cicatrizBracoAux").value = cicatrizBraco.checked;
	 document.getElementById("cicatrizPernaAux").value = cicatrizPerna.checked;
	 document.getElementById("cicatrizPeAux").value = cicatrizPe.checked;
	 
	 if(document.getElementById("sexo:1").checked){
		 document.getElementById("sexoAux").value= document.getElementById("sexo:1").value;
	 }else{
		 document.getElementById("sexoAux").value= document.getElementById("sexo:0").value;
	 }
	 
	 
	var contador = 0;
	for(var i = 0; i < 24; i++){
		contador+= flags[i];
		console.log(contador);
	}
	
	var confirmAlter;
    confirmAlter = document.getElementById("confirmar");
    var atributo = confirmAlter.getAttribute('data-target');
    
    console.log(atributo);
    
/*	if(confirmAlter == null){
		confirmAlter = document.getElementById("confirm-alter");
	}*/
    console.log(contador);
	if(contador == 24){
		confirmAlter.setAttribute('data-target','#errado');
		//confirmAlter.id = "confirm-alter";
		//confirmar.disabled = true;
		//confirmar.classList.add("disp");
	}else{
		//confirmAlter.id = "alo";
		confirmAlter.setAttribute('data-target','#certo');
	}
	
}