package ir.khu.jaobshaar.service.resume;

import ir.khu.jaobshaar.component.resume.ResumeManager;
import ir.khu.jaobshaar.service.dto.ResumeDTO;
import ir.khu.jaobshaar.utils.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeManager resumeManager;
    private final FileStorageService fileStorageService;

    public ResumeController(ResumeManager resumeManager, FileStorageService fileStorageService
    ) {
        this.resumeManager = resumeManager;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/url")
    public ResponseEntity<?> addResume(@RequestBody ResumeDTO resumeDTO) {
        return ResponseEntity.ok(resumeManager.addResume(resumeDTO));
    }

    @PutMapping("/url")
    public ResponseEntity<?> updateResume(@RequestBody ResumeDTO resumeDTO) {
        return ResponseEntity.ok(resumeManager.updateResume(resumeDTO));
    }

    @PostMapping("/file")
    public ResponseEntity<?> addResume(MultipartFile file) {
        resumeManager.addResume(fileStorageService.storeFile(file));
        return ResponseEntity.ok("add successfully");
    }

    @PutMapping("/file")
    public ResponseEntity<?> updateResume(MultipartFile file) {
        resumeManager.updateResume(fileStorageService.replaceFile(file, resumeManager.getResumeName()));
        return ResponseEntity.ok("update successfully");
    }

    @GetMapping(produces = {"application/pdf"})
    public ResponseEntity<Resource> getResume() {
        return ResponseEntity.ok().body(fileStorageService.loadFileAsResource(resumeManager.getResumeName()));
    }

    @GetMapping(value = "/employer", produces = {"application/pdf"})
    public ResponseEntity<Resource> getResume(long id) {
        return ResponseEntity.ok().body(fileStorageService.loadFileAsResource(resumeManager.getResumeName(id)));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(boolean isFileResume){
        resumeManager.deleteResume(isFileResume);
        return ResponseEntity.ok().build();
    }
}
