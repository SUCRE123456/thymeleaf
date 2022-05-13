package com.example.demo.emp.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.emp.service.EmpService;
import com.example.demo.emp.service.EmpVO;

@Controller
public class EmpController {

    @Autowired
    EmpService empService;

    @RequestMapping("test")
    public String test(Model model, EmpVO empVO) {
        return "test";
    }
    
    // 단건조회   getEmp?employeeId=100
    @RequestMapping("getEmp/{employeeId}") // getEmp?employeeId=aaaa
    public String getEmp(@PathVariable String employeeId
                        , Model model, EmpVO empVO) {
        empVO.setEmployeeId(employeeId);
        model.addAttribute("emp", empService.getEmp(empVO));
        return "emp/getEmp";
    }

    // 목록조회
    @RequestMapping("empList")
    public String empList(Model model, EmpVO empVO) {
        model.addAttribute("empList", empService.getEmpList(empVO));
        return "emp/getEmpList";
    }
    
    // 수정폼
    @RequestMapping("updateFormEmp")
    public String updateFormEmp(Model model, 
    		EmpVO empVO,
    		BindingResult bindingResult ) {  	
        model.addAttribute("empVO", empService.getEmp(empVO));
        return "emp/updateEmp";
    }

    // 등록폼
    @RequestMapping("insertFormEmp")
    public String insertFormEmp(EmpVO vo, BindingResult bindingResult ) {
        return "emp/insertEmp";
    }

    // 등록처리
    @RequestMapping("insertEmp")
    public String insertEmp(@ModelAttribute("evo") EmpVO vo, // 1. 커맨트 객체
            Model model, @RequestParam String firstName, // 2. String
            HttpServletRequest request,
                                                         // ln=request.getParameter("firstName")
            @RequestParam(required = false, defaultValue = "kim", value = "lastName") String ln,
            @RequestParam Map map // 3. 모든 파라미터 맵으로 받기
    ) throws IOException {
        //업로드 경로
        String path = request.getSession().getServletContext().getRealPath("resources/download");
        System.out.println(path);
        // 첨부파일 처리 
        MultipartFile file = vo.getUploadFile();
        String filename = file.getOriginalFilename();
        if (file != null && file.getSize() > 0) {
            file.transferTo(new File("d:/upload", filename));
            vo.setProfile(filename);
        }

        // 서비스호출
        empService.empInsert(vo);

        return "redirect:empList";
    }
}


