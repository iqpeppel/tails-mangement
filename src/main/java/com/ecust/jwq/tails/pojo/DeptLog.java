package com.ecust.jwq.tails.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {
    private LocalDateTime   CreateTime;
    private  String   Description;
}
