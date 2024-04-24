package com.spring.model.dto.incomesigns;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IncomingSignsDto {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date;

    private String sender;

    private String via;

    private String signInformer;
    private String signInformerSelf;
    private String signInformerPhone;


    private String summary;

    private String signSignature;
    private String signSelf;


    private String signRecipientName;
    private String signRecipientSelf;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signRecipientDate;

    private String signExcutedName;
    private String signExcutedSelf;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date SignExecutionDate;

    private String depend;
}
