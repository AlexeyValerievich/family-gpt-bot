package petproject.telegrambot.openai;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


public class OpenAIClient {

    private final String token;
    private final RestTemplate restTemplate;

    public OpenAIClient(String token, RestTemplate restTemplate) {
        this.token = token;
        this.restTemplate = restTemplate;
    }

    public ChatCompletionObject createChatCompletion(
            String message
    ) {
        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        httpHeaders.set("Content-Type", "application/json");

        String request = """
                {
                    "model": "gpt-4o-mini",
                    "messages": [
                      {
                        "role": "developer",
                        "content": "You are a helpful assistant."
                      },
                      {
                        "role": "user",
                        "content": "%s"
                      }
                    ]
                  }
                """.formatted(message);

        HttpEntity<String> httpEntity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<ChatCompletionObject> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                ChatCompletionObject.class);
        return responseEntity.getBody();
    }
}
