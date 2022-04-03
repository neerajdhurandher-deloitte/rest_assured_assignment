package Utils;

import api_responses.Task;
import api_responses.User;
import api_responses.Register_user_response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class XlsxReader {
     String filePath;
     ArrayList<User> userResponsesList = new ArrayList<>();
     File file;
     FileInputStream inputStream;
     FileOutputStream outputStream;
     XSSFWorkbook workbook;
     XSSFSheet workbookSheet;

    public XlsxReader(String path) throws IOException {

        filePath = path;

        file = new File(filePath);

        inputStream = new FileInputStream(file);

        workbook = new XSSFWorkbook(inputStream);

        workbookSheet = workbook.getSheet("sheet1");

    }

}
