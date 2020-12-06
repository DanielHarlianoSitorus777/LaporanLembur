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
import com.example.LaporanLembur.entities.Policy;
import com.example.LaporanLembur.entities.TempValue;
import com.example.LaporanLembur.repositories.DepartmentRepository;
import com.example.LaporanLembur.repositories.EmployeeRepository;
import com.example.LaporanLembur.repositories.OvertimeRepository;
import com.example.LaporanLembur.repositories.PolicyRepository;
import com.example.LaporanLembur.repositories.TitleRepository;
import com.example.LaporanLembur.services.EmailService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
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
    TitleRepository titleRepository;

    @Autowired
    OvertimeRepository overtimeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    EmployeeDaoImpl employeeDaoImpl;

    @Autowired
    AdminDaoImpl adminDaoImpl;

    @Autowired
    EmailService emailService;

    // LOGIN
    @GetMapping("/login_error")
    public String loginError() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // HOME
    @GetMapping("")
    public String index(Model model) throws ParseException {
        String view = null;

        model.addAttribute("employee", employeeRepository.getOne(TempValue.id));
        model.addAttribute("department", employeeRepository.getOne(TempValue.id).getDepartment().getId());
        System.out.println(employeeRepository.getOne(TempValue.id).getDepartment().getId());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        SimpleDateFormat overtimeFormat = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
        Date date = new Date();

        System.out.println("Date Formating : " + formatter.format(date));
        System.out.println("Time Formating : " + timeFormat.format(1606730000));
        model.addAttribute("form", new Overtime());
        model.addAttribute("date", formatter.format(date));
        model.addAttribute("time", timeFormat.format(new Date().getTime()));

        System.out.println("Day : " + date.getDay());
        int overtime = policyRepository.getOne(2).getTime() / 3600;
        float hourSpent = 0;

        try {
            if (employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(TempValue.id)) == null) {
                hourSpent = 0;
            } else if (employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(TempValue.id)) != null) {
                hourSpent = Float.parseFloat(employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(TempValue.id)).get(0));
            }
        } catch (Exception e) {
            System.out.println("Spent Hours Exception : " + e);
        }

        // Check New Month
        if ((date.getDay() - 1) == 1) {
            model.addAttribute("overtime", overtime);
        }
        model.addAttribute("overtime", overtime - hourSpent);

        if (null != TempValue.role) {
            switch (TempValue.role) {
                case "Manager":
                    if (employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).size() > 0 && employeeDaoImpl.getDepartmentLatestReport(employeeRepository.getOne(TempValue.id).getDepartment()).size() > 0) {
                        model.addAttribute("latestPersonalReport", employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).get(0));
                        model.addAttribute("departmentLatestReport", employeeDaoImpl.getDepartmentLatestReport(employeeRepository.getOne(TempValue.id).getDepartment()).get(0));
                    }
                    return view = "dashboardmanager";
                case "Karyawan":
                    if (employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).size() > 0) {
                        model.addAttribute("latestPersonalReport", employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).get(0));
                    } else {
                        model.addAttribute("latestPersonalReport", new Overtime());
                    }
                    System.out.println("Size : " + employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).size());
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
        model.addAttribute("department", employeeRepository.getOne(TempValue.id).getDepartment().getId());
        System.out.println(employeeRepository.getOne(TempValue.id).getDepartment().getId());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        Date date = new Date();
        System.out.println("Date Formating : " + formatter.format(date));

        model.addAttribute("form", new Overtime());
        model.addAttribute("date", formatter.format(date));
        model.addAttribute("time", timeFormat.format(new Date().getTime()));
        System.out.println("Day : " + date.getDay());
        int overtime = policyRepository.getOne(2).getTime() / 3600;
        float hourSpent = 0;

        try {
            if (employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(TempValue.id)) == null) {
                hourSpent = 0;
            } else if (employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(TempValue.id)) != null) {
                hourSpent = Float.parseFloat(employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(TempValue.id)).get(0));
            }
        } catch (Exception e) {
            System.out.println("Spent Hours Exception : " + e);
        }

        // Check New Month
        if ((date.getDay() - 1) == 1) {
            model.addAttribute("overtime", overtime);
        }
        model.addAttribute("overtime", overtime - hourSpent);

        if (null != TempValue.role) {
            switch (TempValue.role) {
                case "Manager":
                    if (employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).size() > 0 && employeeDaoImpl.getDepartmentLatestReport(employeeRepository.getOne(TempValue.id).getDepartment()).size() > 0) {
                        model.addAttribute("latestPersonalReport", employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).get(0));
                        model.addAttribute("departmentLatestReport", employeeDaoImpl.getDepartmentLatestReport(employeeRepository.getOne(TempValue.id).getDepartment()).get(0));
                    }
                    return view = "dashboardmanager";
                case "Karyawan":
                    if (employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).size() > 0) {
                        model.addAttribute("latestPersonalReport", employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).get(0));
                    } else {
                        model.addAttribute("latestPersonalReport", new Overtime());
                    }
                    System.out.println("Size : " + employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(TempValue.id)).size());
                    return view = "dashboardemployee";
                case "Admin":
                    return view = "dashboardadmin";
                default:
                    break;
            }
        }
        return view;
    }

    @GetMapping("/personal")
    public String personalReport(Model model) {
        model.addAttribute("history", employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(TempValue.id)));
        model.addAttribute("report", employeeRepository.getOne(TempValue.id));
        System.out.println("Id Personal : " + employeeRepository.getOne(TempValue.id).getTitle());

        System.out.println("Personal : " + employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(TempValue.id)));
        return "reportpribadi";
    }

    @GetMapping("/personal/{id}")
    public String personalReportDetail(Model model, @PathVariable("id") int id) {
        model.addAttribute("history", employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(TempValue.id)));
        model.addAttribute("report", overtimeRepository.getOne(id));
        model.addAttribute("user", employeeRepository.getOne(TempValue.id));
        return "reportpribadimodal";
    }

    @GetMapping("/department")
    public String departmentReport(Model model) {
        model.addAttribute("history", employeeDaoImpl.getReportbyDepartment(departmentRepository.getOne(TempValue.deptId)));
        System.out.println("Dept : " + employeeDaoImpl.getReportbyDepartment(departmentRepository.getOne(TempValue.deptId)));

        return "reportdivisi";
    }

    @GetMapping("/department/{id}")
    public String departmentReportDetail(Model model, @PathVariable("id") int id) {
        model.addAttribute("history", employeeDaoImpl.getReportbyDepartment(departmentRepository.getOne(TempValue.deptId)));
        model.addAttribute("report", overtimeRepository.getOne(id));

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        System.out.println("Time : " + timeFormat.format(overtimeRepository.getOne(id).getStartTime()));

        model.addAttribute("startTime", timeFormat.format(overtimeRepository.getOne(id).getStartTime()));
        model.addAttribute("endTime", timeFormat.format(overtimeRepository.getOne(id).getEndTime()));

        return "reportdivisimodal";
    }

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
    public String policy(Model model) {
        model.addAttribute("input", new Policy());
        model.addAttribute("policy", policyRepository.findAll());
        return "kebijakan";
    }

    @GetMapping("/policy/{id}")
    public String getPolicyId(Model model, @PathVariable("id") int id) {
        model.addAttribute("input", policyRepository.getOne(id));
        model.addAttribute("policy", policyRepository.findAll());
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
    public String createReport(Overtime overtime) throws MessagingException {
        overtimeRepository.save(overtime);
        emailService.sendManagerNotif(employeeRepository.getOne(TempValue.id).getManager().getEmail(), employeeRepository.getOne(TempValue.id).getName());
        return "redirect:/home/?result=update_success";
    }

    @GetMapping("/confirmReport/{id}/{status}")
    public String confirmReport(@PathVariable("id") int id, @PathVariable("status") String status) {
        overtimeRepository.confirmReport(id, status);
        System.out.println("Overtime : " + overtimeRepository.getOne(id).getEmployee().getEmail());
        emailService.sendEmployeeConfirmationNotif(overtimeRepository.getOne(id).getEmployee().getEmail(), status);
        return "redirect:/department/?result=report_confirmed";
    }

    @PostMapping("/createnote/{id}")
    public String addManagerNotes(Overtime overtime, @PathVariable("id") int id) throws MessagingException {
        overtimeRepository.save(overtime);
        System.out.println("Overtime : " + overtimeRepository.getOne(id).getEmployee().getEmail());
        emailService.sendEmployeeNoteNotif(overtimeRepository.getOne(id).getEmployee().getEmail(), overtimeRepository.getOne(id).getManagerNotes());
        return "redirect:/home/?result=update_success";
    }

    @PostMapping("/saveemployee")
    public String addEmployee(Employee employee, Model model) throws MessagingException {
        model.addAttribute("employee", adminDaoImpl.getAllEmployee());
        employeeRepository.save(employee);
        System.out.println("Email : " + employee.getEmail());
        emailService.sendEmailNotif(employee.getEmail());
        return "redirect:/employee/?result=update_success";
    }

    // DELETE
    @RequestMapping(value = "/deleteemployee/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", adminDaoImpl.getAllEmployee());
        employeeRepository.deleteById(id);
        return "redirect:/employee/?result=data_deleted";
    }
}
