package com.buzznote.websocket_gateway.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SystemMessage {
    @Email(message = "Invalid sender email")
    @NotBlank(message = "Sender unspecified")
    private String from;

    @Email(message = "Invalid sender email")
    @NotBlank(message = "Retriever unspecified")
    private String to;

    @NotBlank(message = "Message should not be empty")
    private String message;
}
