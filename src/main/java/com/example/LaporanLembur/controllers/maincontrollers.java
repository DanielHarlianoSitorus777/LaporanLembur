/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.controllers;

import com.example.LaporanLembur.daoimpl.AdminDaoImpl;
import com.example.LaporanLembur.daoimpl.EmployeeDaoImpl;
import com.example.LaporanLembur.entities.Employee;
import com.example.LaporanLembur.entities.Overtime;
import com.example.LaporanLembur.entities.TempValue;
import com.example.LaporanLembur.repositories.DepartmentRepository;
import com.example.LaporanLembur.repositories.EmployeeRepository;
import com.example.LaporanLembur.repositories.OvertimeRepository;
import com.example.LaporanLembur.repositories.PolicyRepository;
import com.example.LaporanLembur.repositories.TitleRepository;
import com.example.LaporanLembur.services.EmployeeService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ardian
 */
@Controller
public class maincontrollers {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    TitleRepository titleRepository;

    // LOGIN
    @GetMapping("/login_error")
    public String loginError() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
//        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        Date date = new Date();
//        System.out.println(new Timestamp(employeeDaoImpl.getReportbyDepartment(departmentRepository.getOne(1)).get(0).getTotalTime().getTime()));
//        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/YYYY");
//        System.out.println("history : " + ts);
        return "login";
    }

    // HOME
    @GetMapping("")
    public String index(Model model) {
        String view = null;

        model.addAttribute("employee", employeeRepository.getOne(TempValue.id));
        model.addAttribute("department", employeeRepository.getOne(TempValue.id).getDepartment().getId());
        System.out.println(employeeRepository.getOne(TempValue.id).getDepartment().getId());

        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();

        model.addAttribute("form", new Overtime());

        if (null != TempValue.role) {
            switch (TempValue.role) {
                case "Manager":
                    return view = "dashboardmanager";
                case "Karyawan":
                    return view = "dashboardemployee";
                case "Admin":
                    return view = "dashboardadmin";
                default:
                    break;
            }
        }
        return view;
    }

    @GetMapping("/home")
    public String home(Model model) {
        String view = null;

        model.addAttribute("employee", employeeRepository.getOne(TempValue.id));

        model.addAttribute("form", new Overtime());

        if (null != TempValue.role) {
            switch (TempValue.role) {
                case "Manager":
                    return view = "dashboardmanager";
                case "Karyawan":
                    return view = "dashboardemployee";
                case "Admin":
                    return view = "dashboardadmin";
                default:
                    break;
            }
        }
        return view;
    }

    @Autowired
    EmployeeDaoImpl employeeDaoImpl;

    @Autowired
    OvertimeRepository overtimeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PolicyRepository policyRepository;

    @GetMapping("/personal")
    public String personalReport(Model model) {
        model.addAttribute("history", employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(TempValue.id)));
        model.addAttribute("report", overtimeRepository.getOne(1));
        return "reportpribadi";
    }
    
    @GetMapping("/personal/{id}")
    public String personalReportDetail(Model model, @PathVariable("id") int id) {
        model.addAttribute("history", employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(TempValue.id)));
        model.addAttribute("report", overtimeRepository.getOne(id));
        return "reportpribadimodal";
    }

    @GetMapping("/department")
    public String departmentReport(Model model) {
        model.addAttribute("history", employeeDaoImpl.getReportbyDepartment(departmentRepository.getOne(TempValue.deptId)));
        
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
//        System.out.println("history : " + formatter.format("1606659535"));
        return "reportdivisi";
    }

    @Autowired
    AdminDaoImpl adminDaoImpl;

    @GetMapping("/employee")
    public String employeeList(Model model) {
        model.addAttribute("employee", adminDaoImpl.getAllEmployee());
        return "employeelist";
    }

    @GetMapping("/employee/{id}")
    public String employeeList(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeRepository.findById(id).get());
        model.addAttribute("department", departmentRepository.findAll());
        model.addAttribute("title", titleRepository.findAll());
        return "updatekaryawan";
    }

    @GetMapping("/addemployee")
    public String employeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("department", departmentRepository.findAll());
        model.addAttribute("title", titleRepository.findAll());
        return "updatekaryawan";
    }

    @GetMapping("/policy")
    public String policy() {
        return "kebijakan";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    // POST
    @PostMapping("/createreport")
    public String createReport(Overtime overtime) {
        overtimeRepository.save(overtime);
        return "redirect:/";
    }

    @PostMapping("/saveemployee")
    public String addEmployee(Employee employee, Model model) {
        model.addAttribute("employee", adminDaoImpl.getAllEmployee());
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    // DELETE
    @RequestMapping(value = "/deleteemployee/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", adminDaoImpl.getAllEmployee());
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }
}
