package Utils;

import api_responses.Register_user_response;
import api_responses.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserSheetHandler extends XlsxReader{

    ArrayList<User> userList = new ArrayList<>();


    public UserSheetHandler(String path) throws IOException {
        super(path);
    }

    public ArrayList<User> readUserFile() throws IOException {

        int rowCount = workbookSheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowCount; i++) {

            Row row = workbookSheet.getRow(i);

            int cellCount = row.getLastCellNum();

            User user = new User();


            for (int j = 0; j < cellCount; j++) {

                String data = "";
                try {
                    data = row.getCell(j).getStringCellValue();
                }catch (Exception e){

                }

                switch (j){
                    case 0-> user.setAge((int)row.getCell(j).getNumericCellValue());
                    case 1-> user.setId(data);
                    case 2-> user.setName(data);
                    case 3-> user.setEmail(data);
                    case 4-> user.setCreatedAt(data);
                    case 5-> user.setUpdatedAt(data);
                    case 6-> user.setV((int)row.getCell(j).getNumericCellValue());
                    case 7-> user.setToken(data);
                    case 8-> user.setPassword(data);
                }
            }

            userList.add(user);
        }

        return userResponsesList;
    }

    public void addUserData(User user) {



        int rowNumber = workbookSheet.getPhysicalNumberOfRows();

        Row row = workbookSheet.createRow(rowNumber);

        Object [] userObjArray = new Object[]{
                user.getAge(),user.getId(),user.getName(),user.getEmail(),
                user.getCreatedAt(),user.getUpdatedAt(),user.getV(),user.getToken(),user.getPassword()
        };

        int cellNumber = 0;

        for (Object obj : userObjArray) {

            Cell cell = row.createCell(cellNumber++);

            if(obj instanceof String)
                cell.setCellValue((String)obj);
            else if(obj instanceof Integer)
                cell.setCellValue((Integer)obj);
        }

        System.out.println("User details :- " + Arrays.toString(userObjArray));

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
