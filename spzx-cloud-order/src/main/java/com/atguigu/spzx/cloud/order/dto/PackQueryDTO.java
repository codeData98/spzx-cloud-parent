/**
 * @ClassName PackQueryDTO
 * @Description TODO
 * @Author apple
 * @Date 2026/11/25 12:12
 */
package com.atguigu.spzx.cloud.order.dto;

import lombok.Data;

@Data
public class PackQueryDTO {
    private Long orgId;
    private String startTime;
    private String endTime;
    private Integer pageIndex;
    private Integer pageSize;
}
