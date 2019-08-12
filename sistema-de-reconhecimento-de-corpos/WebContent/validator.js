var arq1 = document.getElementById("arq1");
 
var arq2 = document.getElementById("arq2");
 
var arq3 = document.getElementById("arq3");
 
var arq4 = document.getElementById("arq4");
 
var arq5 = document.getElementById("arq5");
 
var arq6 = document.getElementById("arq6");

var arq7 = document.getElementById("arq7");

var arq8 = document.getElementById("arq8");

var arq9 = document.getElementById("arq9");
 
var img2 = document.getElementById("imagenzinha2");
var img3 = document.getElementById("imagenzinha3");
var img4 = document.getElementById("imagenzinha4");
var img5 = document.getElementById("imagenzinha5");
var img6 = document.getElementById("imagenzinha6");
var img7 = document.getElementById("imagenzinha7");
var img8 = document.getElementById("imagenzinha8");
var img9 = document.getElementById("imagenzinha9");

arq1.addEventListener("change", function(event){
 
  if(arq1.files.length != 0){
    console.log("ENTROU");
    console.log(arq2.classList);
    img2.classList.remove("uploadimghidden");
    img2.classList.add("uploadimgvisible");
     return;
  } 
 
});

arq2.addEventListener("change", function(event){
	  if(arq2.files.length != 0){
	    console.log("ENTROU");
	    console.log(arq3.classList);
	    img3.classList.remove("uploadimghidden");
	    img3.classList.add("uploadimgvisible");
	     return;
	  } 
	 
});
arq3.addEventListener("change", function(event){
	  if(arq3.files.length != 0){
	    console.log("ENTROU");
	    console.log(arq4.classList);
	    img4.classList.remove("uploadimghidden");
	    img4.classList.add("uploadimgvisible");
	     return;
	  } 
	 
});
arq4.addEventListener("change", function(event){
	  if(arq4.files.length != 0){
	    console.log("ENTROU");
	    console.log(arq5.classList);
	    img5.classList.remove("uploadimghidden");
	    img5.classList.add("uploadimgvisible");
	     return;
	  } 
	 
});

arq5.addEventListener("change", function(event){
	  if(arq5.files.length != 0){
	    console.log("ENTROU");
	    console.log(arq5.classList);
	    img6.classList.remove("uploadimghidden");
	    img6.classList.add("uploadimgvisible");
	     return;
	  } 
	 
});

arq6.addEventListener("change", function(event){
	  if(arq6.files.length != 0){
	    console.log("ENTROU");
	    console.log(arq6.classList);
	    img7.classList.remove("uploadimghidden");
	    img7.classList.add("uploadimgvisible");
	     return;
	  } 
	 
});

arq7.addEventListener("change", function(event){
	  if(arq7.files.length != 0){
	    console.log("ENTROU");
	    console.log(arq7.classList);
	    img8.classList.remove("uploadimghidden");
	    img8.classList.add("uploadimgvisible");
	     return;
	  } 	 
});

arq8.addEventListener("change", function(event){
	  if(arq8.files.length != 0){
	    console.log("ENTROU");
	    console.log(arq8.classList);
	    img9.classList.remove("uploadimghidden");
	    img9.classList.add("uploadimgvisible");
	     return;
	  } 	 
});

var imgPrincipal1 = document.getElementById("img1");
var imgPrincipal2 = document.getElementById("img2");
var imgPrincipal3 = document.getElementById("img3");
var imgPrincipal4 = document.getElementById("img4");
var imgPrincipal5 = document.getElementById("img5");
var imgPrincipal6 = document.getElementById("img6");
var imgPrincipal7 = document.getElementById("img7");
var imgPrincipal8 = document.getElementById("img8");
var imgPrincipal9 = document.getElementById("img9");
var semImg = document.getElementById("noneImg");


semImg.addEventListener("change", function(event){ 
	if(semImg.checked){
		 imgPrincipal1.disabled = true;
		 imgPrincipal2.disabled = true;
		 imgPrincipal3.disabled = true;
		 imgPrincipal4.disabled = true;
		 imgPrincipal5.disabled = true;
		 imgPrincipal6.disabled = true;
		 imgPrincipal7.disabled = true;
		 imgPrincipal8.disabled = true;
		 imgPrincipal9.disabled = true;
		 
		 imgPrincipal2.checked = false;
		 imgPrincipal3.checked = false;
		 imgPrincipal4.checked = false;
		 imgPrincipal5.checked = false;
		 imgPrincipal6.checked = false;
		 imgPrincipal7.checked = false;
		 imgPrincipal8.checked = false;
		 imgPrincipal9.checked = false;
	}else{
		imgPrincipal1.removeAttribute('disabled');  
		imgPrincipal2.removeAttribute('disabled');  
		imgPrincipal3.removeAttribute('disabled');  
		imgPrincipal4.removeAttribute('disabled');  
		imgPrincipal5.removeAttribute('disabled');  
		imgPrincipal6.removeAttribute('disabled');  
		imgPrincipal7.removeAttribute('disabled');  
		imgPrincipal8.removeAttribute('disabled');  
		imgPrincipal9.removeAttribute('disabled');  
	} 
});


imgPrincipal1.addEventListener("change", function(event){ 
 if(imgPrincipal1.checked){ 
	 imgPrincipal2.checked = false;
	 imgPrincipal3.checked = false;
	 imgPrincipal4.checked = false;
	 imgPrincipal5.checked = false;
	 imgPrincipal6.checked = false;
	 imgPrincipal7.checked = false;
	 imgPrincipal8.checked = false;
	 imgPrincipal9.checked = false;
 } 
});

imgPrincipal2.addEventListener("change", function(event){ 
 if(imgPrincipal2.checked){ 
	 imgPrincipal1.checked = false;
	 imgPrincipal3.checked = false;
	 imgPrincipal4.checked = false;
	 imgPrincipal5.checked = false;
	 imgPrincipal6.checked = false;
	 imgPrincipal7.checked = false;
	 imgPrincipal8.checked = false;
	 imgPrincipal9.checked = false;
 } 
}); 

imgPrincipal3.addEventListener("change", function(event){ 

 if(imgPrincipal3.checked){ 
	 imgPrincipal2.checked = false;
	 imgPrincipal1.checked = false;
	 imgPrincipal4.checked = false;
	 imgPrincipal5.checked = false;
	 imgPrincipal6.checked = false;
	 imgPrincipal7.checked = false;
	 imgPrincipal8.checked = false;
	 imgPrincipal9.checked = false;
 } 
 
}); 

imgPrincipal4.addEventListener("change", function(event){ 

	 if(imgPrincipal4.checked){ 
		 imgPrincipal2.checked = false;
		 imgPrincipal1.checked = false;
		 imgPrincipal3.checked = false;
		 imgPrincipal5.checked = false;
		 imgPrincipal6.checked = false;
		 imgPrincipal7.checked = false;
		 imgPrincipal8.checked = false;
		 imgPrincipal9.checked = false;
	 } 
}); 
 
imgPrincipal5.addEventListener("change", function(event){ 

	 if(imgPrincipal5.checked){ 
		 imgPrincipal2.checked = false;
		 imgPrincipal1.checked = false;
		 imgPrincipal3.checked = false;
		 imgPrincipal4.checked = false;
		 imgPrincipal6.checked = false;
		 imgPrincipal7.checked = false;
		 imgPrincipal8.checked = false;
		 imgPrincipal9.checked = false;
	 } 
});  
	
imgPrincipal6.addEventListener("change", function(event){ 

	 if(imgPrincipal6.checked){ 
		 imgPrincipal2.checked = false;
		 imgPrincipal1.checked = false;
		 imgPrincipal3.checked = false;
		 imgPrincipal5.checked = false;
		 imgPrincipal4.checked = false;
		 imgPrincipal7.checked = false;
		 imgPrincipal8.checked = false;
		 imgPrincipal9.checked = false;
	 } 
});

imgPrincipal7.addEventListener("change", function(event){ 

	 if(imgPrincipal7.checked){ 
		 imgPrincipal2.checked = false;
		 imgPrincipal1.checked = false;
		 imgPrincipal3.checked = false;
		 imgPrincipal5.checked = false;
		 imgPrincipal4.checked = false;
		 imgPrincipal6.checked = false;
		 imgPrincipal8.checked = false;
		 imgPrincipal9.checked = false;
	 } 
});

imgPrincipal8.addEventListener("change", function(event){ 

	 if(imgPrincipal8.checked){ 
		 imgPrincipal2.checked = false;
		 imgPrincipal1.checked = false;
		 imgPrincipal3.checked = false;
		 imgPrincipal5.checked = false;
		 imgPrincipal4.checked = false;
		 imgPrincipal7.checked = false;
		 imgPrincipal6.checked = false;
		 imgPrincipal9.checked = false;
	 } 
});

imgPrincipal9.addEventListener("change", function(event){ 

	 if(imgPrincipal9.checked){ 
		 imgPrincipal2.checked = false;
		 imgPrincipal1.checked = false;
		 imgPrincipal3.checked = false;
		 imgPrincipal5.checked = false;
		 imgPrincipal4.checked = false;
		 imgPrincipal7.checked = false;
		 imgPrincipal8.checked = false;
		 imgPrincipal6.checked = false;
	 } 
});

 