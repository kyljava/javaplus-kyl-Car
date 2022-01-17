package com.turing.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtils {
	/**
	 * 1.设置本次请求响应的内容类型和编码集（判断不同版本的浏览器）
	 */
public void settings(HttpServletRequest request, HttpServletResponse response, String filename)
			throws UnsupportedEncodingException {
	       /**
	        * 1.在后台对我本次要响应的xxxx.xls按照了UTF-8编码，那么在什么地方对其解码呢，
	        * 我们查看了setHeader()方法之后就会发现第二个参数
	        * 就是必须要进行URLEncoder编码的，但是解码就是在它的实现类种实现的
	        */
	       filename=URLEncoder.encode(filename, "UTF-8"); 
	       response.setHeader("Connection", "close");  //设置http头的短连接
	       response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
	       response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);//// 将此次需要下载的excel文件以附件形式展示出来
	}

/**
 * @desc  2.poi解析excel的时候表格中数据自动适配的问题的加强（解决中文的适配）
 * @param sheet
 * @param size
 */
@SuppressWarnings("unused")
public void setColumnAutoAdapter(HSSFSheet sheet, int size) {
    for (int columnNum = 0; columnNum < size; columnNum++) {
        int columnWidth = sheet.getColumnWidth(columnNum) / 256;
        for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
          HSSFRow   currentRow;
            //当前行未被使用过
            if (sheet.getRow(rowNum) == null) {
                currentRow = sheet.createRow(rowNum);
            } else {
                currentRow = sheet.getRow(rowNum);
            }

            if (currentRow.getCell(columnNum) != null) {
             HSSFCell    currentCell = currentRow.getCell(columnNum);
                if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    int length = currentCell.getStringCellValue().getBytes().length;
                    if (columnWidth < length) {
                        columnWidth = length;
                    }
                }
            }
        }
        sheet.setColumnWidth(columnNum, columnWidth * 256);
    }
}
/**
 * @desc  3.格式化输出
 * @param cell
 * @return
 */
public static String getFormatValue(Cell cell) {
		if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC) 
		{ 
			 int d = (int)cell.getNumericCellValue();
			 return String.valueOf(d);
		}
		else if (cell.getCellType()==Cell.CELL_TYPE_BOOLEAN)
		{
			String.valueOf(cell.getBooleanCellValue());
		}
		return cell.getStringCellValue();
	}	


}
