package com.msa.banking.common.notification;

import com.msa.banking.common.base.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationRequestDto {

    private UUID userId;
    private String slackId;
    private UserRole role;
    private NotiType type;
    private String message;

}