package awd.mis.appstore.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPoiTools {
	public static List<String> read(String filePath) {
		List<String> list = new ArrayList<String>();
        try {
            /* 验证文件是否合法 */
            if (!WDWUtil.validateExcel(filePath)) {
                System.out.println("excel文件格式错误！");
            }else{
                /* 判断文件的类型，是2003还是2007 */
                if (WDWUtil.isExcel2007(filePath)) {
                	list = readEXCEL2007(filePath);
                } else if (WDWUtil.isExcel2003(filePath)) {
                	list = readEXCEL(filePath);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // 读取xls文件
    public static List<String> readEXCEL(String file) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));// 创建对Excel工作簿文件的引用
        List<String> list = new ArrayList<String>();
        for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
            if (null != workbook.getSheetAt(numSheets)) {
                HSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
                for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                    if (null != aSheet.getRow(rowNumOfSheet)) {
                    	StringBuilder content = new StringBuilder();
                        HSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
                        for (short cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++) {
                            if (null != aRow.getCell(cellNumOfRow)) {
                                HSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                                if (convertCell(aCell).length() > 0) {
                                    content.append(convertCell(aCell));
                                }
                                if(cellNumOfRow != aRow.getLastCellNum()-1) {
                                	content.append(",");
                                }
                            }
                        }
                        list.add(content.toString());
                    }
                }
            }
        }
        return list;
    }

    // 读取xlsx文件
    public static List<String> readEXCEL2007(String file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        List<String> list = new ArrayList<String>();
        for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
            if (null != workbook.getSheetAt(numSheets)) {
                XSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
                //去掉第一行表头
                for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                    if (null != aSheet.getRow(rowNumOfSheet)) {
                    	StringBuilder content = new StringBuilder();
                        XSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
                        for (short cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++) {
                            if (null != aRow.getCell(cellNumOfRow)) {
                                XSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                                if (convertCell(aCell).length() > 0) {
                                    content.append(convertCell(aCell));
                                }
                                if(cellNumOfRow != aRow.getLastCellNum()-1) {
                                	content.append(",");
                                }
                            }
                        }
                        list.add(content.toString());
                    }
                }
            }
        }
        return list;
    }

    private static String convertCell(Cell cell) {
        NumberFormat formater = NumberFormat.getInstance();
        formater.setGroupingUsed(false);
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = formater.format(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                cellValue = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = Boolean.valueOf(cell.getBooleanCellValue()).toString();
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                cellValue = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                cellValue = "";
        }
        return cellValue.trim();
    }
}
