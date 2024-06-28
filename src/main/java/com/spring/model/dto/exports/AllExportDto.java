package com.spring.model.dto.exports;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllExportDto {


    private Integer id;

    private Integer no;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Byte numberOfAttachments;

    private Byte numberOfImages;

    private String createdBy;

    private List<String> paths;


    private String receiver;

    private String summary;

    private String recipientName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recipientDate;

    private Integer urgentNum;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate;


    private Integer responseNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate;


    private Byte typeNumber;
    private Short num;


}
