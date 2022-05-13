package com.example.demo.emp.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.emp.service.EmpVO;

@Repository
public class EmpDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    public EmpVO getEmp(EmpVO empVO) {
        return mybatis.selectOne("com.example.demo.emp.EmpDAO.getEmp", empVO);
    }

    public List<EmpVO> getEmpList(EmpVO empVO) {
        return mybatis.selectList("com.example.demo.emp.EmpDAO.getEmpList", empVO);
    }

}
