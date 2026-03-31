package com.sanju.health_clinic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanju.health_clinic.dto.admin.AuditLogResponse;
import com.sanju.health_clinic.dto.admin.BackupResponse;
import com.sanju.health_clinic.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/backup")
    public BackupResponse backup() throws Exception {
        return adminService.backupDatabase();
    }

    @GetMapping("/audit-logs")
    public List<AuditLogResponse> auditLogs(@RequestParam(required = false) String table) {
        return adminService.auditLogs(table);
    }
}
