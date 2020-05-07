package ir.khu.jaobshaar.service.company;

import ir.khu.jaobshaar.component.company.CompanyManager;
import ir.khu.jaobshaar.service.domain.CompanyDomain;
import ir.khu.jaobshaar.service.dto.employer.CompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyManager companyManager;

    public CompanyController(CompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDTO companyDTO) {
        companyManager.addCompany(companyDTO);
        return ResponseEntity.ok(companyDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateCompany(@RequestBody CompanyDTO companyDTO) {
        companyManager.updateCompany(companyDTO);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/list")
    public ResponseEntity<List<CompanyDomain>> getCompanyList() {
        return ResponseEntity.ok(companyManager.getCompanyList());
    }
}
