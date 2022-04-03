package api_responses;

import java.util.HashMap;
import java.util.Map;

public class Register_user_response {
    private User user;
    private String token;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Register_user_response(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public Register_user_response() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
