package com.codegnan.service;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileImageExtractor {
public byte[] extractImage(MultipartFile file) throws Exception {
String name = file.getOriginalFilename().toLowerCase();
if (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif")) {
return file.getBytes();
}
throw new IllegalArgumentException("Unsupported file type: " + name);
}
public byte[] extractFromUrl(String urlString) throws Exception {
URL url = new URL(urlString);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
InputStream is = conn.getInputStream();
ByteArrayOutputStream os = new ByteArrayOutputStream();
byte[] buffer = new byte[4096];
int bytesRead;
while ((bytesRead = is.read(buffer)) != -1) {
os.write(buffer, 0, bytesRead);
}
is.close();
return os.toByteArray();
}
public String getMimeTypeFromUrl(String urlString) throws Exception {
URL url = new URL(urlString);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("HEAD");
conn.connect();
return conn.getContentType();
}
}