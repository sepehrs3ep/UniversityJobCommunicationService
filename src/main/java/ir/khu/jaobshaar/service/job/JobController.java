package ir.khu.jaobshaar.service.job;


import ir.khu.jaobshaar.component.job.JobManager;
import ir.khu.jaobshaar.service.domain.JobDomain;
import ir.khu.jaobshaar.service.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobManager jobManager;

    @PostMapping
    public ResponseEntity<?> addJob(@RequestBody JobDTO jobDTO) {
        // TODO: 1/13/2020 test 
        jobManager.addJob(jobDTO);
        return ResponseEntity.ok(jobDTO);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<JobDomain>> getEmployeeJobs() {
        // TODO: 1/13/2020 Hoy Fateme ! - > Get Filter Request Param Using Predicate
        return ResponseEntity.ok(jobManager.getEmployeeJobs());
    }

    @GetMapping("/employer")
    public ResponseEntity<List<JobDomain>> getEmployerJobs() {
        return ResponseEntity.ok(jobManager.getEmployerJobs());
    }

}
