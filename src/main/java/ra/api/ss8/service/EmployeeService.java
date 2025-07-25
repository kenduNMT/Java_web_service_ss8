package ra.api.ss8.service;

import ra.api.ss8.exception.NotFoundException;
import ra.api.ss8.model.dto.response.DataResponse;
import ra.api.ss8.model.entity.Employee;
import ra.api.ss8.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public DataResponse<List<Employee>> getAllEmployees() {
        return DataResponse.<List<Employee>>builder()
                .key("employees")
                .data(employeeRepository.findAll())
                .build();
    }
    @Transactional
    public void deleteEmployee(Long id) throws NotFoundException{
        if (!employeeRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy nhân viên với ID: " + id);
        }
        employeeRepository.deleteById(id);
    }
    public DataResponse<Employee> getEmployeeById(Long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhân viên với ID: " + id));
        return DataResponse.<Employee>builder()
                .key("employee")
                .data(employee)
                .build();
    }
    @Transactional
    public DataResponse<Employee> createEmployee(Employee employee) {
        return DataResponse.<Employee>builder()
                .key("employee")
                .data(employeeRepository.save(employee))
                .build();
    }
    @Transactional
    public DataResponse<Employee> updateEmployee(Long id, Employee employee) throws NotFoundException {
        if (!employeeRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy nhân viên với ID: " + id);
        }
        employee.setId(id);
        return DataResponse.<Employee>builder()
                .key("employee")
                .data(employeeRepository.save(employee))
                .build();
    }

}