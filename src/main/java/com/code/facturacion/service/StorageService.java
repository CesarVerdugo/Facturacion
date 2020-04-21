package com.code.facturacion.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.print.DocFlavor.INPUT_STREAM;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.code.facturacion.configuration.ApplicationProperties;
import com.code.facturacion.exception.StorageException;

@Service
public class StorageService {

	private final Path baseFolder ;

	
	public StorageService(ApplicationProperties properties) {
		this.baseFolder = Paths.get(properties.getCDN().getBaseFolder());
	}
	
	@PostConstruct
	public void init(){
		try {
			Files.createDirectories(baseFolder);
		} catch (IOException e) {
			throw new StorageException("no se pudo crear la carpeta en la ruta especificada", e);
		}
	}
	
	public String store(MultipartFile file) {
		//String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		String newName = UUID.randomUUID().toString().concat("." + extension);
		
		try {
			if (file.isEmpty()) {
				throw new StorageException("fallo al tratar de guardar archivo vacio" + file.getOriginalFilename());
			}
			
			try(InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, baseFolder.resolve(newName), StandardCopyOption.REPLACE_EXISTING);
			}
			
		} catch (IOException e) {
			throw new StorageException("fallo al tratar de guardar archivo",e);
		}
		
		return newName;
	}
	
	public Resource loadAsResource(String fileName) {
		
		try {
			Path fileUrl = baseFolder.resolve(fileName);
			Resource resource = new UrlResource(fileUrl.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				throw new StorageException("no se pudo leer el archivo :" + fileName);
			}
		} catch (MalformedURLException e) {
			throw new StorageException("no se pudo leer el archivo :" + fileName , e);
		}
	}
	
	public Path loadPath(String fileName) {
		return baseFolder.resolve(fileName);
	}
}
