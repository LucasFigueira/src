 
var visibilidade = false; //Variável que vai manipular o botão Exibir/ocultar
var latitude =  document.getElementById("latitudeAux"); 
var longitude =  document.getElementById("longitudeAux"); 
 
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
	
	var sexoAux = document.getElementById("sexo:0");
	sexoAux.disabled= false;
	var sexoAux1 = document.getElementById("sexo:1");
	sexoAux1.disabled= false;
	var estruturaAux = document.getElementById("estrutura:0");
	estruturaAux.disabled= false;
	var estruturaAux1 = document.getElementById("estrutura:1");
	estruturaAux1.disabled= false;
	console.log("HEHEHEHEHEHEHEHEHEH");
	var altura = document.getElementById("altura");
	altura.disabled= false;
	
	var faixaEtaria = document.getElementById("faixaEtaria");
	faixaEtaria.disabled= false;
	
	var peso = document.getElementById("peso");
	peso.disabled= false;
	
	var corPele = document.getElementById("corPele");
	corPele.disabled= false;
	
	
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
	



	
    window.scrollTo(0, 0);
	 
}

var confirmar = document.getElementById("confirmar");
var sexoAux = document.getElementById("sexo:1");
var sexoAux1 = document.getElementById("sexo:0");
var estruturaAux = document.getElementById("estrutura:1");
var estruturaAux1 = document.getElementById("estrutura:0");
var altura = document.getElementById("altura");
var faixaEtaria = document.getElementById("faixaEtaria");
var peso = document.getElementById("peso");
var corPele = document.getElementById("corPele");
var dataChegada = document.getElementById("dataChegada");
var horaChegada = document.getElementById("horaChegada");
var descricao   = document.getElementById("descricao");
var tattooPerna = document.getElementById("tattooPerna");
var tattooBraco = document.getElementById("tattooBraco");
var tattooCostas = document.getElementById("tattooCostas");
var tattooPeito = document.getElementById("tattooPeito");
var incisivo = document.getElementById("incisivo");
var valorInicialAltura = altura.value;
var valorInicialPeso = peso.value;
var valorInicialFaixaEtaria = faixaEtaria.value;
var valorInicialcorPele = corPele.value;
var valorInicialdataChegada = dataChegada.value;
var valorInicialhoraChegada = horaChegada.value; 
var valorInicialDescricao = descricao.value;

var valorInicialtattooPerna = tattooPerna.checked;
var valorInicialtattooBraco = tattooBraco.checked;
var valorInicialtattooCostas = tattooCostas.checked;
var valorInicialtattooPeito = tattooPeito.checked;
var valorInicialincisivo= incisivo.checked;
 	  document.getElementById("latitudeAux").value = latitude.value; 
  document.getElementById("longitudeAux").value = longitude.value; 
 
altura.addEventListener("keypress", function(event){ 
	confirmar.classList.remove("disp"); 
});
faixaEtaria.addEventListener("click", function(event){
	confirmar.classList.remove("disp");
 
});

sexoAux.addEventListener("click",function(event){
	confirmar.classList.remove("disp");
});
sexoAux1.addEventListener("click",function(event){
	confirmar.classList.remove("disp");
});
estruturaAux.addEventListener("click",function(event){
	confirmar.classList.remove("disp");
});
estruturaAux1.addEventListener("click",function(event){
	confirmar.classList.remove("disp");
});
peso.addEventListener("keypress", function(event){
	confirmar.classList.remove("disp");
 
});


corPele.addEventListener("keypress", function(event){
	confirmar.classList.remove("disp");
 
});
dataChegada.addEventListener("keypress", function(event){
	confirmar.classList.remove("disp");
 
});


horaChegada.addEventListener("keypress", function(event){
	confirmar.classList.remove("disp");
 
});

descricao.addEventListener("keypress", function(event){
	confirmar.classList.remove("disp");
 
});

incisivo.addEventListener("click", function(event){
	console.log(incisivo.checked);
	confirmar.classList.remove("disp");
 
});
tattooPerna.addEventListener("click", function(event){
	confirmar.classList.remove("disp");
 
});

tattooBraco.addEventListener("click", function(event){
	confirmar.classList.remove("disp");
 
});

tattooCostas.addEventListener("click", function(event){
	confirmar.classList.remove("disp");
 
});

tattooPeito.addEventListener("click", function(event){
	confirmar.classList.remove("disp");
 
});



 
function confirmarAlteracao(){

	var flags = new Array(20);
	for(var i = 0; i < 20; i++){
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
	
	if(valorInicialdataChegada == dataChegada.value){
		flags[5] = 1;
	}
	if(valorInicialhoraChegada == horaChegada.value){
		flags[6] = 1;
	}
	if(valorInicialincisivo == incisivo.value ){
		flags[7] = 1;
	}
	
	if(valorInicialtattooPerna == tattooPerna.checked){
		flags[9] = 1;
	}
	if(valorInicialtattooBraco == tattooBraco.checked){
		flags[10] = 1;
	}
	if(valorInicialtattooCostas == tattooCostas.checked){
		flags[11] = 1;
	}
	if(valorInicialtattooPeito == tattooPeito.checked){
		flags[12] = 1;
	}
	if(valorInicialDescricao == descricao.value){
		flags[13] = 1;
	}
	 document.getElementById("faixaEtariaAux").value = faixaEtaria.value;
	 document.getElementById("descricaoAux").value = descricao.value;
	 document.getElementById("pesoAux").value = peso.value;
	 document.getElementById("corPeleAux").value = corPele.value;
	 document.getElementById("alturaAux").value = altura.value;
	 document.getElementById("dataChegadaAux").value = dataChegada.value;
	 document.getElementById("horarioChegadaAux").value = horaChegada.value;
	 
	 
	 document.getElementById("tattooPernaAux").value = tattooPerna.checked;
	 document.getElementById("tattooBracoAux").value = tattooBraco.checked;
	 document.getElementById("tattooCostasAux").value = tattooCostas.checked;
	 document.getElementById("tattooPeitoAux").value = tattooPeito.checked;
	 
	 
	 document.getElementById("incisivoAux").value = incisivo.checked;
	 if(document.getElementById("sexo:1").checked){
		 document.getElementById("sexoAux").value= document.getElementById("sexo:1").value;
	 }else{
		 document.getElementById("sexoAux").value= document.getElementById("sexo:0").value;
	 }
	 if(document.getElementById("estrutura:1").checked){
		 document.getElementById("permanentesAux").value= document.getElementById("estrutura:1").value;
	 }else{
		 document.getElementById("permanentesAux").value= document.getElementById("estrutura:0").value;
	 }
	 
	var contador = 0;
	for(var i = 0; i < 20; i++){
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
	if(contador == 20){
		confirmAlter.setAttribute('data-target','#errado');
		//confirmAlter.id = "confirm-alter";
		//confirmar.disabled = true;
		//confirmar.classList.add("disp");
	}else{
		//confirmAlter.id = "alo";
		confirmAlter.setAttribute('data-target','#certo');
	}
	
}