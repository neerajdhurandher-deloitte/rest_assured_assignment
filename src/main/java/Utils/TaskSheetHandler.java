package Utils;

import api_responses.Task;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskSheetHandler extends XlsxReader{

    ArrayList<Task> tasksList = new ArrayList<>();


    public TaskSheetHandler(String path) throws IOException {
        super(path);
    }

    public ArrayList<Task> readTaskFile() throws IOException {

        int rowCount = workbookSheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowCount; i++) {

            Row row = workbookSheet.getRow(i);

            int cellCount = row.getLastCellNum();

            Task task = new Task();


            for (int j = 0; j < cellCount; j++) {

                String data = "";
                try {
                    data = row.getCell(j).getStringCellValue();
                }catch (Exception e){

                }

                switch (j){
                    case 0-> task.setComplected(row.getCell(j).getBooleanCellValue());
                    case 1-> task.setId(data);
                    case 2-> task.setDescription(data);
                    case 3-> task.setOwner(data);
                    case 4-> task.setCreatedAt(data);
                    case 5-> task.setUpdatedAt(data);
                    case 6-> task.setV((int)row.getCell(j).getNumericCellValue());
                }
            }

            tasksList.add(task);
        }

        return tasksList;
    }

    public void addTaskData(Task task) {

        int rowNumber = workbookSheet.getPhysicalNumberOfRows();

        Row row = workbookSheet.createRow(rowNumber);

        Object [] taskObjArray = new Object[]{
                task.isComplected(),task.getId(),task.getDescription(),
                task.getOwner(),task.getCreatedAt(),task.getUpdatedAt(),task.getV()
        };

        int cellNumber = 0;

        for (Object obj : taskObjArray) {

            Cell cell = row.createCell(cellNumber++);

            if(obj instanceof String)
                cell.setCellValue((String)obj);
            else if(obj instanceof Integer)
                cell.setCellValue((Integer)obj);
            else if(obj instanceof Boolean)
                cell.setCellValue( (Boolean)obj );
        }

        System.out.println("User details :- " + Arrays.toString(taskObjArray));

        try {
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
            System.out.println("User added in sheet at row " + workbookSheet.getPhysicalNumberOfRows());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
