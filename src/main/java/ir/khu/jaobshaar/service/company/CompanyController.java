package ir.khu.jaobshaar.service.company;

import ir.khu.jaobshaar.component.company.CompanyManager;
import ir.khu.jaobshaar.service.domain.CompanyDomain;
import ir.khu.jaobshaar.service.dto.employer.CompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CompanyController {

    private final CompanyManager companyManager;

    public CompanyController(CompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    @PostMapping("/company")
    public ResponseEntity<?> addCompany(@RequestBody CompanyDTO companyDTO) {
        companyManager.addCompany(companyDTO);
        return ResponseEntity.ok(companyDTO);
    }

    @GetMapping
    public ResponseEntity<CompanyDomain> getCompany(){
        return ResponseEntity.ok(companyManager.getCurrentUserCompany());
    }

    @PutMapping
    public ResponseEntity<CompanyDomain> update(@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.ok(companyManager.update(companyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        companyManager.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
