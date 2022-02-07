package com.challenge.apirest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "request_history")
public class RequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String endpoint;

    @NotBlank
    private String method;

    @Nullable
    private String body;

    @NotNull
    private Date date;

    public RequestHistory(String endpoint, String method, String body){
        this.endpoint = endpoint;
        this.method = method;
        this.body = body;
        this.date = new Date();
    }

}
