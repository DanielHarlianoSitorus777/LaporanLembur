/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.controllers;

import com.example.LaporanLembur.daoimpl.AdminDaoImpl;
import com.example.LaporanLembur.daoimpl.EmployeeDaoImpl;
import com.example.LaporanLembur.entities.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    // HOME
    @GetMapping("")
    public String index(Model model) throws ParseException {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = user.getId();

        String view = null;

        model.addAttribute("employee", employeeRepository.getOne(id));
        model.addAttribute("department", employeeRepository.getOne(id).getDepartment().getId());
        System.out.println(employeeRepository.getOne(id).getDepartment().getId());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        SimpleDateFormat overtimeFormat = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
        Date date = new Date();

//        System.out.println("Date Formating : " + formatter.format(date));
//        System.out.println("Time Formating : " + timeFormat.format(1606730000));
        model.addAttribute("form", new Overtime());
        model.addAttribute("date", formatter.format(date));
        model.addAttribute("time", timeFormat.format(new Date().getTime()));

        System.out.println("Day : " + date.getDay());
        int overtime = policyRepository.getOne(2).getTime() / 3600;
        float hourSpent = 0;

        try {
            if (employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(id)) == null) {
                hourSpent = 0;
            } else if (employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(id)) != null) {
                hourSpent = Float.parseFloat(employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(id)).get(0));
            }
        } catch (Exception e) {
            System.out.println("Spent Hours Exception : " + e);
        }

        // Check New Month
        if ((date.getDay() - 1) == 1) {
            model.addAttribute("overtime", overtime);
        }
        model.addAttribute("overtime", overtime - hourSpent);

        int totalReport = 0;

        try {
            if (employeeDaoImpl.getCurrentMonthTotalReport(employeeRepository.getOne(id)) == null) {
                totalReport = 0;
            } else if (employeeDaoImpl.getCurrentMonthTotalReport(employeeRepository.getOne(id)) != null) {
                totalReport = Integer.parseInt(employeeDaoImpl.getCurrentMonthTotalReport(employeeRepository.getOne(id)));
            }
        } catch (Exception e) {
            System.out.println("Total Report Exception : " + e);
        }

        model.addAttribute("totalreport", totalReport);

        if (null != employeeRepository.getOne(id).getTitle().getTitle()) {
            switch (employeeRepository.getOne(id).getTitle().getTitle()) {
                case "Manager":
                    if (employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).size() > 0 && employeeDaoImpl.getDepartmentLatestReport(employeeRepository.getOne(id).getDepartment()).size() > 0) {
                        model.addAttribute("latestPersonalReport", employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).get(0));
                        model.addAttribute("departmentLatestReport", overtimeRepository.getLatestDepartmentReport(employeeRepository.getOne(id).getDepartment()));
                    }
                    return view = "dashboardmanager";
                case "Karyawan":
                    if (employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).size() > 0) {
                        model.addAttribute("latestPersonalReport", employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).get(0));
                    } else {
                        model.addAttribute("latestPersonalReport", new Overtime());
                    }
                    System.out.println("Size : " + employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).size());
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
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = user.getId();

        String view = null;

        model.addAttribute("employee", employeeRepository.getOne(id));
        model.addAttribute("department", employeeRepository.getOne(id).getDepartment().getId());
        System.out.println(employeeRepository.getOne(id).getDepartment().getId());

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
            if (employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(id)) == null) {
                hourSpent = 0;
            } else if (employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(id)) != null) {
                hourSpent = Float.parseFloat(employeeDaoImpl.getCurrentMonthTotalOvertime(employeeRepository.getOne(id)).get(0));
            }
        } catch (Exception e) {
            System.out.println("Spent Hours Exception : " + e);
        }

        // Check New Month
        if ((date.getDay() - 1) == 1) {
            model.addAttribute("overtime", overtime);
        }
        model.addAttribute("overtime", overtime - hourSpent);

        int totalReport = 0;

        try {
            if (employeeDaoImpl.getCurrentMonthTotalReport(employeeRepository.getOne(id)) == null) {
                totalReport = 0;
            } else if (employeeDaoImpl.getCurrentMonthTotalReport(employeeRepository.getOne(id)) != null) {
                totalReport = Integer.parseInt(employeeDaoImpl.getCurrentMonthTotalReport(employeeRepository.getOne(id)));
            }
        } catch (Exception e) {
            System.out.println("Total Report Exception : " + e);
        }

        model.addAttribute("totalreport", totalReport);

        if (null != employeeRepository.getOne(id).getTitle().getTitle()) {
            switch (employeeRepository.getOne(id).getTitle().getTitle()) {
                case "Manager":
                    if (employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).size() > 0 && employeeDaoImpl.getDepartmentLatestReport(employeeRepository.getOne(id).getDepartment()).size() > 0) {
                        model.addAttribute("latestPersonalReport", employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).get(0));
                        model.addAttribute("departmentLatestReport", overtimeRepository.getLatestDepartmentReport(employeeRepository.getOne(id).getDepartment()));
                    }
                    return view = "dashboardmanager";
                case "Karyawan":
                    if (employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).size() > 0) {
                        model.addAttribute("latestPersonalReport", employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).get(0));
                    } else {
                        model.addAttribute("latestPersonalReport", new Overtime());
                    }
                    System.out.println("Size : " + employeeDaoImpl.getEmployeeLatestReport(employeeRepository.getOne(id)).size());
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
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = user.getId();

        model.addAttribute("history", employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(id)));
        model.addAttribute("report", employeeRepository.getOne(id));
        System.out.println("Id Personal : " + employeeRepository.getOne(id).getTitle());

        System.out.println("Personal : " + employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(id)));
        return "reportpribadi";
    }

    @GetMapping("/personal/{id}")
    public String personalReportDetail(Model model, @PathVariable("id") int id) {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int uid = user.getId();
        
        model.addAttribute("history", employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(uid)));
        model.addAttribute("report", overtimeRepository.getOne(id));
        model.addAttribute("user", employeeRepository.getOne(uid));
        return "reportpribadimodal";
    }

    @GetMapping("/department")
    public String departmentReport(Model model) {
        
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int uid = user.getId();
        
        model.addAttribute("history", overtimeRepository.findByDepartment(departmentRepository.getOne(employeeRepository.getOne(uid).getDepartment().getId())));
        System.out.println("Dept : " + employeeDaoImpl.getReportbyDepartment(departmentRepository.getOne(employeeRepository.getOne(uid).getDepartment().getId())));

        return "reportdivisi";
    }

    @GetMapping("/department/{id}")
    public String departmentReportDetail(Model model, @PathVariable("id") int id) {
        
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int uid = user.getId();
        
        model.addAttribute("history", overtimeRepository.findByDepartment(departmentRepository.getOne(employeeRepository.getOne(uid).getDepartment().getId())));
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

    // POST
    @PostMapping("/createreport")
    public String createReport(Overtime overtime, @RequestParam("tempStartTime") String tempStartTime, @RequestParam("tempEndTime") String tempEndTime) throws MessagingException, ParseException {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = user.getId();
        
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tempTime = overtime.getSubmitDate() + " " + tempStartTime + ":00";
        String tempTimeEnd = overtime.getSubmitDate() + " " + tempEndTime + ":00";
        Date date = timeFormat.parse(tempTime);
        Date dateEnd = timeFormat.parse(tempTimeEnd);
        
        System.out.println("hasil : " + date);
        overtime.setStartTime(date);
        overtime.setEndTime(dateEnd);
        overtime.setReorder(1);
        overtimeRepository.save(overtime);
        emailService.sendManagerNotif(employeeRepository.getOne(id).getManager().getEmail(), employeeRepository.getOne(id).getName());
        return "redirect:/home/?result=update_success";
    }

    @GetMapping("/confirmReport/{id}/{status}/{reorder}")
    public String confirmReport(@PathVariable("id") int id, @PathVariable("status") String status, @PathVariable("reorder") int reorder) throws MessagingException {
        overtimeRepository.confirmReport(id, status, reorder);
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

    @PostMapping("/savepolicy")
    public String savePolicy(Policy policy) {
        adminDaoImpl.savePolicy(policy);
        return "redirect:/policy/?result=update_success";
    }

    @PostMapping("/forgotpassword/sendemail")
    public String sendForgotPassword(EmailInput email, Model model) throws MessagingException {
        employeeDaoImpl.getEmployeeByEmail(email.getEmail()).getEmail();
        emailService.sendForgotPassNotif(employeeDaoImpl.getEmployeeByEmail(email.getEmail()).getEmail(), employeeDaoImpl.getEmployeeByEmail(email.getEmail()).getPassword());
        System.out.println("Forgot Pass : " + employeeDaoImpl.getEmployeeByEmail(email.getEmail()).getEmail() + employeeDaoImpl.getEmployeeByEmail(email.getEmail()).getPassword());
        return "forgotpassword";
    }

    // DELETE
    @RequestMapping(value = "/deleteemployee/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", adminDaoImpl.getAllEmployee());
        employeeRepository.deleteById(id);
        return "redirect:/employee/?result=data_deleted";
    }

    @RequestMapping(value = "/deletepolicy/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deletePolicy(@PathVariable("id") int id, Model model) {
        model.addAttribute("input", policyRepository.getOne(id));
        model.addAttribute("policy", policyRepository.findAll());
        policyRepository.deleteById(id);
        return "redirect:/policy/?result=data_deleted";
    }
}
