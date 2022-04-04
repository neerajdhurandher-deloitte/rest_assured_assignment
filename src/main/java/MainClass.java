import Utils.UserSheetHandler;
import api_responses.User;
import Utils.XlsxReader;
import api_responses.Register_user_response;
import com.aventstack.extentreports.Status;

import java.io.IOException;
import java.util.ArrayList;

public class MainClass {

    public static void main(String[] args) throws IOException {
        UserSheetHandler userSheetHandler;
        ArrayList<User> userList = new ArrayList<>();

        try {
            // get all users from xlsx sheet
            userSheetHandler = new UserSheetHandler("src/main/java/Utils/UserDetails.xlsx");
            userList = userSheetHandler.readUserFile();
            System.out.println(userList.get(0).getName());

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
