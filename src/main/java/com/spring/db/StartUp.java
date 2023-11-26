package com.spring.db;

import com.spring.model.dto.ArchiveFileDto;
import com.spring.model.entity.Export;
import com.spring.repository.ArchiveFileRepository;
import com.spring.repository.ExportRepository;
import com.spring.services.ArchiveFileServices;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartUp implements CommandLineRunner {

    private final ArchiveFileServices archiveFileServices;
    private final ArchiveFileRepository archiveFileRepository;
    private final ExportRepository exportRepository;

    @Override
    public void run(String... args) throws Exception {

        if (archiveFileServices.count() == 0) {

            ArchiveFileDto archiveFile1 = new ArchiveFileDto(1L, "وارد مكتب رئيس الجامعة", (byte) 1);
            ArchiveFileDto archiveFile2 = new ArchiveFileDto(2L, "وارد نائب رئيس الجامعة للدراسات العليا والبحوث", (byte) 1);
            ArchiveFileDto archiveFile3 = new ArchiveFileDto(3L, "وارد نائب رئيس الجامعة لشئون التعليم والطلاب", (byte) 1);
            ArchiveFileDto archiveFile4 = new ArchiveFileDto(4L, "وارد نائب رئيس الجامعة لخدمة المجتمع وتنمية البيئة", (byte) 1);
            ArchiveFileDto archiveFile5 = new ArchiveFileDto(5L, "وارد مكتب أمين عام الجامعة", (byte) 1);
            ArchiveFileDto archiveFile6 = new ArchiveFileDto(6L, "وارد وكيل الكلية لشئون التعليم والطلاب", (byte) 1);
            ArchiveFileDto archiveFile7 = new ArchiveFileDto(7L, "وارد إدارة الطلاب الوافدين", (byte) 1);
            ArchiveFileDto archiveFile8 = new ArchiveFileDto(8L, "وارد الإدارة العامة للدراسات العليا والبحوث", (byte) 1);
            ArchiveFileDto archiveFile9 = new ArchiveFileDto(9L, "وارد شئون اعضاء هيئة التدريس", (byte) 1);
            ArchiveFileDto archiveFile10 = new ArchiveFileDto(10L, "وارد وحدة الجودة", (byte) 1);
            ArchiveFileDto archiveFile11 = new ArchiveFileDto(11L, "وارد وحدة القياس والتقويم", (byte) 1);
            ArchiveFileDto archiveFile12 = new ArchiveFileDto(12L, "وارد الشئون القانونية", (byte) 1);
            ArchiveFileDto archiveFile13 = new ArchiveFileDto(13L, "وارد الوحدة الحسابية والاستحقاقات ", (byte) 1);
            ArchiveFileDto archiveFile14 = new ArchiveFileDto(14L, "وارد الإدارة الهندسية والصيانة", (byte) 1);
            ArchiveFileDto archiveFile15 = new ArchiveFileDto(15L, "وارد الإدارة العامة للعلاقات الثقافية", (byte) 1);
            ArchiveFileDto archiveFile16 = new ArchiveFileDto(16L, "وارد المشتريات والمخازن", (byte) 1);
            ArchiveFileDto archiveFile17 = new ArchiveFileDto(17L, "وارد مركز تقنية الإتصالات", (byte) 1);
            ArchiveFileDto archiveFile18 = new ArchiveFileDto(18L, "وارد كليات وجهات أخرى", (byte) 1);
            ArchiveFileDto archiveFile19 = new ArchiveFileDto(19L, "وارد الأدارة العامة لرعاية الشباب والمكتبات", (byte) 1);
            ArchiveFileDto archiveFile20 = new ArchiveFileDto(20L, "وارد الأدارة العامة للشئون الإدارية", (byte) 1);
            ArchiveFileDto archiveFile21 = new ArchiveFileDto(21L, "وارد قرارات وتعليمات رئيس الجامعة", (byte) 1);
            //imports
            ArchiveFileDto archiveFile22 = new ArchiveFileDto(1L, "صادر مكتب رئيس الجامعة", (byte) 2);
            ArchiveFileDto archiveFile23 = new ArchiveFileDto(2L, "صادر نائب رئيس الجامعة للدراسات العليا والبحوث", (byte) 2);
            ArchiveFileDto archiveFile24 = new ArchiveFileDto(3L, "صادر نائب رئيس الجامعة لشئون التعليم والطلاب", (byte) 2);
            ArchiveFileDto archiveFile25 = new ArchiveFileDto(4L, "صادر نائب رئيس الجامعة لخدمة المجتمع وتنمية البيئة", (byte) 2);
            ArchiveFileDto archiveFile26 = new ArchiveFileDto(5L, "صادر مكتب أمين عام الجامعة", (byte) 2);
            ArchiveFileDto archiveFile27 = new ArchiveFileDto(6L, "صادر وكيل الكلية لشئون التعليم والطلاب", (byte) 2);
            ArchiveFileDto archiveFile28 = new ArchiveFileDto(7L, "صادر إدارة الطلاب الوافدين", (byte) 2);
            ArchiveFileDto archiveFile29 = new ArchiveFileDto(8L, "صادر الإدارة العامة للدراسات العليا والبحوث", (byte) 2);
            ArchiveFileDto archiveFile30 = new ArchiveFileDto(9L, "صادر شئون اعضاء هيئة التدريس", (byte) 2);
            ArchiveFileDto archiveFile31 = new ArchiveFileDto(10L, "صادر وحدة الجودة ", (byte) 2);
            ArchiveFileDto archiveFile32 = new ArchiveFileDto(11L, "صادر وحدة القياس والتقويم", (byte) 2);
            ArchiveFileDto archiveFile33 = new ArchiveFileDto(12L, "صادر الشئون القانونية", (byte) 2);
            ArchiveFileDto archiveFile34 = new ArchiveFileDto(13L, "صادر الوحدة الحسابية والاستحقاقات ", (byte) 2);
            ArchiveFileDto archiveFile35 = new ArchiveFileDto(14L, "صادر الإدارة الهندسية والصيانة", (byte) 2);
            ArchiveFileDto archiveFile36 = new ArchiveFileDto(15L, "صادر الإدارة العامة للعلاقات الثقافية", (byte) 2);
            ArchiveFileDto archiveFile37 = new ArchiveFileDto(16L, "صادر المشتريات والمخازن", (byte) 2);
            ArchiveFileDto archiveFile38 = new ArchiveFileDto(17L, "صادر مركز تقنية الإتصالات", (byte) 2);
            ArchiveFileDto archiveFile39 = new ArchiveFileDto(18L, "صادر كليات وجهات أخرى", (byte) 2);
            ArchiveFileDto archiveFile40 = new ArchiveFileDto(19L, "صادر الأدارة العامة لرعاية الشباب والمكتبات", (byte) 2);
            ArchiveFileDto archiveFile41 = new ArchiveFileDto(20L, "صادرالأدارة العامة للشئون الإدارية", (byte) 2);
            ArchiveFileDto archiveFile42 = new ArchiveFileDto(21L, "صادر قرارات وتعليمات رئيس الجامعة", (byte) 2);
            //others
            ArchiveFileDto archiveFile43 = new ArchiveFileDto(1L, "خاص بحفظ قرارات أمانة المجالس الجامعية", (byte) 3);
            ArchiveFileDto archiveFile44 = new ArchiveFileDto(2L, "خاص بمحاضر مجلس الكلية", (byte) 3);
            ArchiveFileDto archiveFile45 = new ArchiveFileDto(3L, "خاص بمحاضر قسم نظم المعلومات", (byte) 3);
            ArchiveFileDto archiveFile46 = new ArchiveFileDto(4L, "خاص بمحاضر قسم علوم الحاسب", (byte) 3);
            ArchiveFileDto archiveFile47 = new ArchiveFileDto(5L, "خاص بمحاضر قسم تكنولوجيا المعلومات", (byte) 3);
            ArchiveFileDto archiveFile48 = new ArchiveFileDto(6L, "خاص بمحاضر قسم دعم القرار", (byte) 3);
            ArchiveFileDto archiveFile49 = new ArchiveFileDto(7L, "خاص بمحاضر برنامج المعلوماتية الطبية", (byte) 3);
            ArchiveFileDto archiveFile50 = new ArchiveFileDto(8L, "خاص بمحاضر برنامج الذكاء الإصطناعي", (byte) 3);
            ArchiveFileDto archiveFile51 = new ArchiveFileDto(9L, "خاص بمحاضر  إجتماعات البرامج الجديدة", (byte) 3);
            ArchiveFileDto archiveFile52 = new ArchiveFileDto(10L, "خاص بمدير الكلية", (byte) 3);
            ArchiveFileDto archiveFile53 = new ArchiveFileDto(11L, "خاص بقسم شئون العاملين", (byte) 3);
            ArchiveFileDto archiveFile54 = new ArchiveFileDto(12L, "خاص بقسم شئون الطلاب", (byte) 3);
            ArchiveFileDto archiveFile55 = new ArchiveFileDto(13L, "خاص بقسم رعاية الشباب والمكتبة", (byte) 3);
            ArchiveFileDto archiveFile56 = new ArchiveFileDto(14L, "خاص بقسم التخطيط والمتابعة", (byte) 3);
            ArchiveFileDto archiveFile57 = new ArchiveFileDto(15L, "خاص بالدراسات العليا (العامة و المهنية)", (byte) 3);
            ArchiveFileDto archiveFile58 = new ArchiveFileDto(16L, "خاص بالاستشارات ومعامل الكلية", (byte) 3);
            ArchiveFileDto archiveFile59 = new ArchiveFileDto(17L, "خاص بتشكيل مجلس الكلية ومجالس الأقسام واللجان الفنية", (byte) 3);
            ArchiveFileDto archiveFile60 = new ArchiveFileDto(18L, "خاص بالبروتوكولات", (byte) 3);
            ArchiveFileDto archiveFile61 = new ArchiveFileDto(19L, "خاص بالأمن الإداري بالكلية والتربية العسكرية", (byte) 3);
            ArchiveFileDto archiveFile62 = new ArchiveFileDto(20L, "خاص بالرد علي ملاحظات الجهاز المركزي والشكاوي", (byte) 3);
            ArchiveFileDto archiveFile63 = new ArchiveFileDto(21L, "خاص بحفظ المكاتبات الواردة لمكتب العميد", (byte) 3);

            List<ArchiveFileDto> archiveFileDtos = new ArrayList<>();

            archiveFileDtos.add(archiveFile1);
            archiveFileDtos.add(archiveFile2);
            archiveFileDtos.add(archiveFile3);
            archiveFileDtos.add(archiveFile4);
            archiveFileDtos.add(archiveFile5);
            archiveFileDtos.add(archiveFile6);
            archiveFileDtos.add(archiveFile7);
            archiveFileDtos.add(archiveFile8);
            archiveFileDtos.add(archiveFile9);
            archiveFileDtos.add(archiveFile10);
            archiveFileDtos.add(archiveFile11);
            archiveFileDtos.add(archiveFile12);
            archiveFileDtos.add(archiveFile13);
            archiveFileDtos.add(archiveFile14);
            archiveFileDtos.add(archiveFile15);
            archiveFileDtos.add(archiveFile16);
            archiveFileDtos.add(archiveFile17);
            archiveFileDtos.add(archiveFile18);
            archiveFileDtos.add(archiveFile19);

            archiveFileDtos.add(archiveFile20);
            archiveFileDtos.add(archiveFile21);
            archiveFileDtos.add(archiveFile22);
            archiveFileDtos.add(archiveFile23);
            archiveFileDtos.add(archiveFile24);
            archiveFileDtos.add(archiveFile25);
            archiveFileDtos.add(archiveFile26);
            archiveFileDtos.add(archiveFile27);
            archiveFileDtos.add(archiveFile28);
            archiveFileDtos.add(archiveFile29);
            archiveFileDtos.add(archiveFile30);
            archiveFileDtos.add(archiveFile31);
            archiveFileDtos.add(archiveFile32);
            archiveFileDtos.add(archiveFile33);
            archiveFileDtos.add(archiveFile34);
            archiveFileDtos.add(archiveFile35);
            archiveFileDtos.add(archiveFile36);
            archiveFileDtos.add(archiveFile37);
            archiveFileDtos.add(archiveFile38);

            archiveFileDtos.add(archiveFile39);
            archiveFileDtos.add(archiveFile40);
            archiveFileDtos.add(archiveFile41);
            archiveFileDtos.add(archiveFile42);
            archiveFileDtos.add(archiveFile43);
            archiveFileDtos.add(archiveFile44);
            archiveFileDtos.add(archiveFile45);
            archiveFileDtos.add(archiveFile46);
            archiveFileDtos.add(archiveFile47);
            archiveFileDtos.add(archiveFile48);
            archiveFileDtos.add(archiveFile49);
            archiveFileDtos.add(archiveFile50);
            archiveFileDtos.add(archiveFile51);
            archiveFileDtos.add(archiveFile52);
            archiveFileDtos.add(archiveFile53);
            archiveFileDtos.add(archiveFile54);
            archiveFileDtos.add(archiveFile55);
            archiveFileDtos.add(archiveFile56);
            archiveFileDtos.add(archiveFile57);
            archiveFileDtos.add(archiveFile58);
            archiveFileDtos.add(archiveFile59);
            archiveFileDtos.add(archiveFile60);
            archiveFileDtos.add(archiveFile61);
            archiveFileDtos.add(archiveFile62);
            archiveFileDtos.add(archiveFile63);


            archiveFileServices.saveAll(archiveFileDtos);

        }

        if (exportRepository.findAll().isEmpty()) {
            Export export1 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export2 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export3 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export4 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export5 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export6 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export7 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export8 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export9 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export10 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export11 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();
            Export export12 = Export.builder()
                    .date(new Date(123, 8, 19))
                    .numberOfApprove(0L)
                    .receiver("رئيس الجامعة")
                    .summary("بشان عدم التواصل مع الجهات الاجنبية او المنظمات الدولية")
                    .recipientName("أسماء")
                    .urgentNum((short) 4)
                    .urgentDate(new Date(123, 8, 19))
                    .urgentNum((short) 1)
                    .archiveFile(archiveFileRepository.findById((short) 16).get())
                    .build();


            List<Export> exports = new ArrayList<>();
            exports.add(export1);
            exports.add(export2);
            exports.add(export3);
            exports.add(export4);
            exports.add(export5);
            exports.add(export6);
            exports.add(export7);
            exports.add(export8);
            exports.add(export9);
            exports.add(export10);
            exports.add(export11);
            exports.add(export12);
            exportRepository.saveAll(exports);
        }

    }
}
