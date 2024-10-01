package org.product_delivery_backend.controller;

import org.product_delivery_backend.common.Pair;
import org.product_delivery_backend.entity.FileMetadata;
import org.product_delivery_backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

@Autowired
private FileService fileService;

// http://host:8080/api/files/upload
@PostMapping("/upload")
public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file)
{
        var id = fileService.store(file);

        return ResponseEntity.ok(id.toString());
}

// http://host:8080/api/files/download/{id}
@GetMapping("/download/{id}")
public ResponseEntity<Resource> download(@PathVariable("id") String fileId) throws FileNotFoundException
{
        UUID id = UUID.fromString(fileId);
        var got = fileService.load(id);
        Resource resource = got.getFirst();
        FileMetadata media = got.getSecond();

        HttpHeaders headers = new HttpHeaders();

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + media.getFileName() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
}
//
// http://host:8080/api/files/file/{filename}
@GetMapping("/file/{filename}")
public ResponseEntity<Resource> file(@PathVariable("filename") String filename) throws FileNotFoundException
{
        Pair<Resource, FileMetadata> got = fileService.loadByName(filename);
        Resource resource = got.getFirst();
        FileMetadata media = got.getSecond();

        HttpHeaders headers = new HttpHeaders();

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + media.getFileName() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
}
@GetMapping("/find/{filename}")
public ResponseEntity<FileMetadata> find(@PathVariable("filename") String filename) throws FileNotFoundException
{
        FileMetadata got = fileService.findByFileName(filename).orElseThrow();

        return ResponseEntity.ok(got);
}
@GetMapping("/findall")
public ResponseEntity<List<FileMetadata>> all() throws FileNotFoundException
{
        List<FileMetadata> got = fileService.findAll();

        return ResponseEntity.ok(got);
}
}
