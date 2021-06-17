package com.happy.video.toolbox;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.happy.video.vo.KeyValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class ReadExcelTest {
    @Test
    public void importXlsx() throws FileNotFoundException {
        File f = new File("/Users/haifeng/Desktop/ok/链上数据/波卡浏览器卡槽拍卖/多语言key-6.17.xlsx");
        InputStream inputStream = new FileInputStream(f);

        Workbook workBook = null;
        try {
            workBook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        while (rowIterator.hasNext()) {
            Row next = rowIterator.next();
            String key = next.getCell(0).toString().replace("\"","");
            String value = next.getCell(1).toString().replace("\"","");
            map.put(key,value);
        }
        map.remove("key");
        System.out.println(JSON.toJSONString(map).replace("\\n","{br}"));

    }

}
