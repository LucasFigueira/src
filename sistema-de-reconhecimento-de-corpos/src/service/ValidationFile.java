package service;

import java.io.File;

import javax.servlet.http.Part;

public class ValidationFile {

	public boolean validarExtensaoArquivo(Part arq1) {
 
		if(!arq1.getName().endsWith(".jpg")) {
			return false;
		}
		
		return true;
	}
	
	
}
