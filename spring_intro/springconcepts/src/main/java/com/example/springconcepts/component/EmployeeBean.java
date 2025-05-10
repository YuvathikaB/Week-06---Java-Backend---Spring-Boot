package com.example.springconcepts.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; // For @Autowired
import org.springframework.stereotype.Component;

@Component
public class EmployeeBean {
	private int eId;
    private String ename;
    
    @Autowired 
    private DepartmentBean deptBean;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeBean.class);

    public EmployeeBean() {
        logger.trace("EmployeeBean No-Arg Constructor Called");
    }

    @Autowired
    public EmployeeBean(DepartmentBean deptBean) {
        logger.trace("*** Autowiring by using @Autowire annotation on constructor ***");
        this.deptBean = deptBean;
    }

    public int getEId() {
        return eId;
    }

    public void setEId(int eId) {
        this.eId = eId;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public DepartmentBean getDeptBean() {
        return deptBean;
    }

    
    @Autowired
    public void setDeptBean(DepartmentBean deptBean) {
        logger.trace("*** Autowiring by using @Autowire annotation on Setter ***");
        this.deptBean = deptBean;
    }

    public void showEpoyeeDetails() {
        logger.debug("Employee Id : {}", eId);
        logger.debug("Employee Name : {}", ename);
        deptBean.setDeptName("Information Technology");
        logger.debug("Department : {}", deptBean.getDeptName());
    }

}
