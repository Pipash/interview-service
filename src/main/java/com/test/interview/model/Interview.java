package com.test.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interviews")
public class Interview extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String applicantName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String applicantEmail;

    private LocalDateTime interviewTime;

    public Interview(String applicantName, String applicantEmail, LocalDateTime interviewTime) {
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.interviewTime = interviewTime;
    }
}
