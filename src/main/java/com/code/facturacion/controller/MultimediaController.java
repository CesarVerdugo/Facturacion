package com.code.facturacion.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.code.facturacion.entity.Multimedia;
import com.code.facturacion.entity.Product;
import com.code.facturacion.exception.ResourceNotFoundException;
import com.code.facturacion.exception.StorageException;
import com.code.facturacion.repository.MultimediaRepository;
import com.code.facturacion.repository.ProductRepository;
import com.code.facturacion.service.StorageService;

@RestController
//@RequestMapping("/multimedias")
public class MultimediaController  {

	@Autowired
	private MultimediaRepository multimediaRepository;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Multimedia> getMultimedias(){
		return multimediaRepository.findAll();
	}
	
	@PostMapping("/products/{id}/images")
	public ResponseEntity<Multimedia> createMultimedia(@RequestParam("file") MultipartFile file, @PathVariable("id") long id) {
		
		Product producto = productRepository.findById(id)
			.orElseThrow(()->new ResourceNotFoundException("producto"));
		
		String name = storageService.store(file);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/multimedia/{filename}")
				.buildAndExpand(name).toUri();
		
		Multimedia multimedia = new Multimedia();
		multimedia.setName(name);
		multimedia.setSize(file.getSize());
		multimedia.setType(file.getContentType());
		multimedia.setEliminated(false);
		multimedia.setUrl(location.toString());
		multimedia.setProduct(producto);
		
		return ResponseEntity.created(location).body(multimediaRepository.save(multimedia));
	}
	
	@GetMapping("/multimedia/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getMultimedia(@PathVariable("filename") String filename) {
		
		Resource resource = storageService.loadAsResource(filename);
		MediaType mediaType = MediaTypeFactory.getMediaType(resource).orElseThrow(()->new StorageException("no se pudo hallar el tipo de archivo"));
		
		return ResponseEntity.ok().contentType(mediaType).body(resource);
	}
	
	@GetMapping("/multimedia/download/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> downloadMultimedia(@PathVariable("filename") String filename) {
		
		Resource resource = storageService.loadAsResource(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
		
	}
}
