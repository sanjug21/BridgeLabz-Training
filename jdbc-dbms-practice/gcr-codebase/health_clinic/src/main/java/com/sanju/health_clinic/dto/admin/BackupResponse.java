package com.sanju.health_clinic.dto.admin;

import java.util.List;

public record BackupResponse(String backupDirectory, List<String> tables) {
}
