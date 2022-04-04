package mainAssignment.UserAction;

import api_responses.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserCreator {

    private Response response;

    public UserCreator(Response response) {
        this.response = response;
    }

    public User getUser(){

        User user = new User();

        JsonPath jsonPath = response.body().jsonPath();

        user.setToken(jsonPath.getString("token"));
        user.setId(jsonPath.getString("user._id"));
        user.setName(jsonPath.getString("user.name"));
        user.setEmail(jsonPath.getString("user.email"));
        user.setCreatedAt(jsonPath.getString("user.createdAt"));
        user.setUpdatedAt(jsonPath.getString("user.updatedAt"));
        user.setAge(jsonPath.getInt("user.age"));
        user.setV(jsonPath.getInt("user.__v"));

        return user;

    }
}
