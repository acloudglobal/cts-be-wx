package com.acloudglobal.ctsbewx.dto;

import lombok.Data;

/**
 * 异常返回DTO
 *
 * @author yupl@acloudchina.com
 * @date 2019-12-12 9:46 上午
 * @since V2.0.0
 */
@Data
public class ErrorDTO {
    /**
     * 错误信息
     */
    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
               "message='" + message + '\'' +
               '}';
    }
}
