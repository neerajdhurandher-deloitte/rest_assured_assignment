package mainAssignment.TaskAction;

import api_responses.Task;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TaskCreator {
    private Response response;

    public TaskCreator(Response response) {
        this.response = response;
    }

    public Task getTask(){

        Task task = new Task();

        JsonPath jsonPath = response.body().jsonPath();

        task.setId(jsonPath.getString("data._id"));
        task.setComplected(jsonPath.getBoolean("data.completed"));
        task.setDescription(jsonPath.getString("data.description"));
        task.setOwner(jsonPath.getString("data.owner"));
        task.setCreatedAt(jsonPath.getString("data.createdAt"));
        task.setUpdatedAt(jsonPath.getString("data.updatedAt"));


        return task;
    }
}
