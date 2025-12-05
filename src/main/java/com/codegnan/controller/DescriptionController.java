package com.codegnan.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.codegnan.service.FileImageExtractor;
import com.codegnan.service.DescriptionService;
@RestController
@RequestMapping("/api/describe")
@CrossOrigin
public class DescriptionController {
private final FileImageExtractor extractor;
private final DescriptionService descriptionService;
public DescriptionController(FileImageExtractor extractor, DescriptionService descriptionService) {
this.extractor = extractor;
this.descriptionService = descriptionService;
}
@PostMapping("/file")
public ResponseEntity describeFile(@RequestParam("file") MultipartFile file) {
try {
byte[] imageBytes = extractor.extractImage(file);
if (imageBytes.length == 0) {
return ResponseEntity.badRequest().body("File contains no image data.");
}
String mimeType = file.getContentType();
String description = descriptionService.describe(imageBytes, mimeType);
return ResponseEntity.ok(description);
} catch (Exception e) {
return ResponseEntity.internalServerError()
.body("Error: " + e.getMessage());
}
}
@PostMapping("/url")
public ResponseEntity describeUrl(@RequestBody String url) {
try {
String mimeType = extractor.getMimeTypeFromUrl(url);
byte[] imageBytes = extractor.extractFromUrl(url);
if (imageBytes.length == 0) {
	return ResponseEntity.badRequest().body("URL contains no image data.");
	}
	String description = descriptionService.describe(imageBytes, mimeType);
	return ResponseEntity.ok(description);
	} catch (Exception e) {
	return ResponseEntity.internalServerError()
	.body("Error: " + e.getMessage());
	}
	}
	}