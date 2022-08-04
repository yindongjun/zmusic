package com.example.zmusic.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDto {

    protected String id;

    protected LocalDateTime createdTime;

    protected LocalDateTime updateTime;
}
