package ir.khu.jaobshaar.component.user;

import ir.khu.jaobshaar.entity.model.Employee;
import ir.khu.jaobshaar.entity.model.User;
import ir.khu.jaobshaar.repository.EmployeeRepository;
import ir.khu.jaobshaar.service.dto.user.UserDTO;
import ir.khu.jaobshaar.utils.ValidationUtils;
import ir.khu.jaobshaar.utils.validation.ErrorCodes;
import ir.khu.jaobshaar.utils.validation.ResponseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeManager {

    private EmployeeRepository employeeRepository;

    private PasswordEncoder bcryptEncoder;

    public EmployeeManager(EmployeeRepository employeeRepository, PasswordEncoder bcryptEncoder) {
        this.employeeRepository = employeeRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public void login(final UserDTO userDTO) {
        ValidationUtils.validateUser(userDTO);

        final Employee employee = employeeRepository.findByUsername(userDTO.getUsername());

        if (employee == null) {
            throw ResponseException.newResponseException(
                    ErrorCodes.ERROR_CODE_USER_NOT_FOUND, " ERROR_CODE_USER_NOT_FOUND "
            );
        }
    }

    public void register(final UserDTO userDTO) {
        ValidationUtils.validateUser(userDTO);

        if (ValidationUtils.isInvalidEmailAddress(userDTO.getEmail())) {
            throw ResponseException.newResponseException(
                    ErrorCodes.ERROR_CODE_INVALID_EMAIL, " Invalid Email"
            );
        }

        final Employee existEmployee = employeeRepository.findByUsername(userDTO.getUsername());

        if (existEmployee != null) {
            throw ResponseException.newResponseException(
                    ErrorCodes.ERROR_CODE_USER_ALREADY_EXIST, " ERROR_CODE_USER_ALREADY_EXIST "
            );
        }

        final Employee employee = new Employee();
        employee.setUsername(userDTO.getUsername());
        employee.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        employee.setEmail(userDTO.getEmail());
        employee.setRole(User.USER_ROLE_EMPLOYEE);

        employeeRepository.save(employee);
    }

}
