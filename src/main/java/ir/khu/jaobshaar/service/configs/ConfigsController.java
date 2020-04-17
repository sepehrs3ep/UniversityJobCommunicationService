package ir.khu.jaobshaar.service.configs;

import ir.khu.jaobshaar.component.configs.ConfigsManager;
import ir.khu.jaobshaar.service.domain.ErrorCodesDomain;
import ir.khu.jaobshaar.service.dto.ErrorCodesTranslatorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/configs")
public class ConfigsController {

    private final ConfigsManager configsManager;

    public ConfigsController(ConfigsManager configsManager) {
        this.configsManager = configsManager;
    }

    @GetMapping("/error-codes-translator")
    public ResponseEntity<ErrorCodesDomain> getErrorCodesTranslator(@RequestBody ErrorCodesTranslatorDTO errorCodesTranslatorDTO) {
        return ResponseEntity.ok(configsManager.getErrorCodesTranslator(errorCodesTranslatorDTO));
    }

}