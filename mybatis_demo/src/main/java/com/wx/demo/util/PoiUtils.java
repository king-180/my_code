package com.wx.demo.util;

import com.wx.demo.entity.Book;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PoiUtils {
    /**
     * 根据集合返回一个http响应体
     *
     * @param allBooks 职称等级列表
     * @return http响应实体
     */
    public static ResponseEntity<byte[]> exportBookExcel(List<Book> allBooks) throws IOException {
        // 创建一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 设置文档属性
        workbook.createInformationProperties();
        // 获取文档的文档摘要信息
        DocumentSummaryInformation sumInfo = workbook.getDocumentSummaryInformation();
        // 设置摘要信息
        sumInfo.setCompany("towards");
        sumInfo.setManager("chet");
        sumInfo.setCategory("user information");
        // 创建一个新的单元格样式并将其添加到工作簿的样式表中。
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 获取与给定格式字符串匹配的格式索引，自动将“文本”转换为excel的格式字符串来表示文本
        short format = HSSFDataFormat.getBuiltinFormat("m/d/yy");
        // 设置表格中的日期格式,设置数据格式(必须是有效格式)
        cellStyle.setDataFormat(format);

        // 为这个HSSFWorkbook创建一个HSSFSheet，将它添加到工作表中
        HSSFSheet sheet = workbook.createSheet("图书信息表");

        // 设置表格列名
        // 在工作表中创建一个新行
        HSSFRow row = sheet.createRow(0);
        // 在行中创建新的单元格,参数为列号
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(9);

        // 为单元格设置一个字符串值
        cell0.setCellValue("编号");
        cell1.setCellValue("书名");
        cell2.setCellValue("大类");
        cell3.setCellValue("小类");
        cell4.setCellValue("ISBN");
        cell5.setCellValue("原价");
        cell6.setCellValue("现价");
        cell7.setCellValue("出版社");
        cell8.setCellValue("出版日期");
        cell9.setCellValue("简介");

        // 循环设置表格中的数据
        for (int i = 0; i < allBooks.size(); i++) {
            // 返回列表中指定位置的元素
            Book book = allBooks.get(i);
            HSSFRow r = sheet.createRow(i + 1);
            HSSFCell c0 = r.createCell(0);
            HSSFCell c1 = r.createCell(1);
            HSSFCell c2 = r.createCell(2);
            HSSFCell c3 = r.createCell(3);
            HSSFCell c4 = r.createCell(4);
            HSSFCell c5 = r.createCell(5);
            HSSFCell c6 = r.createCell(6);
            HSSFCell c7 = r.createCell(7);
            HSSFCell c8 = r.createCell(8);
            HSSFCell c9 = r.createCell(9);

            c0.setCellValue(book.getId());
            c1.setCellValue(book.getBname());
            c2.setCellValue(book.getMainType());
            c3.setCellValue(book.getSubType());
            c4.setCellValue(book.getIsbn());
            c5.setCellValue(book.getOriPrice());
            c6.setCellValue(book.getCurPrice());
            c7.setCellValue(book.getPublisher());

            // 设置日期格式
            c8.setCellStyle(cellStyle);
            c8.setCellValue(book.getPubDate());
            c9.setCellValue(book.getDetail());
        }
        // 创建一个http请求头
        HttpHeaders headers = new HttpHeaders();
        // 设置，参数：1.控制方式-内嵌，2.文件名，在浏览器需转换格式
        headers.setContentDispositionFormData("attachment",
                new String("图书信息表.xls".getBytes("UTF-8"), "iso-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 创建一个字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        // 使用给定的主体、头和状态代码创建一个新的http实体
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.CREATED);
        return responseEntity;
    }

}
