package Utils;

import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.ArrayList;

public class NewTaskSheetHandler extends XlsxReader{

    ArrayList<String> newTasksList = new ArrayList<>();

    public NewTaskSheetHandler(String path) throws IOException {
        super(path);
    }

    public ArrayList<String> readNewTaskFile(){

        int rowCount = workbookSheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowCount; i++) {

            Row row = workbookSheet.getRow(i);

            int cellCount = row.getLastCellNum();

            for (int j = 0; j < cellCount; j++) {

                String data = row.getCell(j).getStringCellValue();

                newTasksList.add(data);
            }
        }

        return newTasksList;
    }


}
