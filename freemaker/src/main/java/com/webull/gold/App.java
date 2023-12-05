package com.webull.gold;

import com.webull.gold.model.NoteEntity;
import com.webull.gold.model.NoteVO;
import com.webull.gold.util.PDFHelper;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class App {

    public static void main(String[] args) throws IOException {
        final NoteEntity noteEntity = new NoteEntity();
        noteEntity.setSymbol("CBA");
        noteEntity.setBrokerageGst(BigDecimal.valueOf(11.11));
        noteEntity.setExpiryDate("12/12/2023");
        noteEntity.setStrikePrice(BigDecimal.valueOf(11.11));
        noteEntity.setPrincipalTransaction("1111111");

        final NoteVO noteVO = new NoteVO();
        noteVO.setCity("Beijing");
        noteVO.setCountry("China");
        noteVO.setAccountName("XXXXXXX");
        noteVO.setAccountType("Individual");
        noteVO.setAddress1("Peking University");
        noteVO.setAddress2("2st");
        noteVO.setAddress3("1");
        noteVO.setAddress4("4");
        noteVO.setEmail("xxxxxx@lihai.com");
        noteVO.setDateOfIssuance("2012-12-12");
        noteVO.setState("Y");
        noteVO.setStatus("Y");
        noteVO.setList(Lists.newArrayList(noteEntity));

        final byte[] bytes = PDFHelper.buildContractNotePdf(noteVO);
        File file = new File("example.pdf");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();

        System.out.println(file.getAbsolutePath());
    }
}
