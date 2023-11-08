package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating reporting structure with employee id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);

        reportingStructure.setNumberOfReports(calculateNumberOfReports(employee));

        return reportingStructure;
    }

    private Integer calculateNumberOfReports(Employee employee){
        return calculateNumberOfReportsHelper(employee) - 1;
    }

    private Integer calculateNumberOfReportsHelper(Employee employee) {
        Employee retrievedEmployee = employeeRepository.findByEmployeeId(employee.getEmployeeId());
        List<Employee> directReports = retrievedEmployee.getDirectReports();
        if(directReports != null && !directReports.isEmpty()){
            int numberOfReportsAndSelfSum = 0;
            for(Employee directReport : directReports){
                numberOfReportsAndSelfSum += calculateNumberOfReportsHelper(directReport);
            }
            return numberOfReportsAndSelfSum + 1;
        } else {
            return 1;
        }
    }


}
