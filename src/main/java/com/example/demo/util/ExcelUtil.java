package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.Iterator;
import java.util.List;

/**
 * 操作Excel表格的功能类
 *
 * @author Ice
 */
public class ExcelUtil {
    /**
     * @param titles   标题行
     * @param keyList  json的key
     * @param dataList list数据
     * @return SXSSFWorkbook
     */
    public static SXSSFWorkbook createExcel(String[] titles, String[] keyList, List<T> dataList) {
        JSONArray dataArray = JSONArray.parseArray(JSON.toJSONString(dataList));
        return createExcel(titles, keyList, dataArray);
    }


    /**
     * @param titles    标题行
     * @param keyList   json的key
     * @param dataArray json array数据
     * @return SXSSFWorkbook
     */
    public static SXSSFWorkbook createExcel(String[] titles, String[] keyList, JSONArray dataArray) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        if (titles.length > 0) {
            Row rowTitle = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                Cell cell = rowTitle.createCell(i);
                cell.setCellValue(titles[i]);
            }
        }
        Iterator<Object> it = dataArray.iterator();
        int j = 1;
        CellStyle cellStyle = workbook.createCellStyle();
        while (it.hasNext()) {
            JSONObject dataJson = (JSONObject) it.next();
            Row tmpRow = sheet.createRow(j);
            for (int i = 0; i < keyList.length; i++) {
                Cell cell = tmpRow.createCell(i);
                String key = keyList[i];
                String param = dataJson.getString(key);
                String lowerCaseKey = key.toLowerCase();
                if (lowerCaseKey.contains("amount") || lowerCaseKey.contains("avg")) {
                    param = "$" + param;
                }
                cell.setCellValue(param);
                cell.setCellStyle(cellStyle);
            }
            j++;
        }
        return workbook;
    }

}
