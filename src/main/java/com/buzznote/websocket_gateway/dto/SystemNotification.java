package com.buzznote.websocket_gateway.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SystemNotification {
    @NotBlank(message = "Message cannot be blank")
    @Size(message = "Message can contain maximum of 100 characters")
    private String message;
}
