package com.spring.model.dto.exports;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExportDtoPut {

    private Long id;
    private String receiver; //الجهة الصادر منها الخطاب
    private String summary;//ملخص الخطاب
    private String recipientName;// المستلم



}
