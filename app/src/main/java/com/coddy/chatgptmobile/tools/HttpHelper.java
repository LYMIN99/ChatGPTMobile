package com.coddy.chatgptmobile.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.coddy.chatgptmobile.model.ChatResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHelper {
    public void sendPostChatRequest(String requestText,OnSendChatCallBack onSendChatCallBack){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"model\": \"gpt-3.5-turbo\",\r\n    \"messages\": [\r\n{\r\n\"role\": \"user\",\r\n\"content\": \""+requestText+"\"\r\n}\r\n    ]\r\n}");
        Request request = new Request.Builder()
                .url("https://openai80.p.rapidapi.com/chat/completions")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("X-RapidAPI-Key", "7addc88e78msh3abe92f7df29b7ap178a83jsn1bbbcfce6cff")
                .addHeader("X-RapidAPI-Host", "openai80.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    // Update UI or perform further actions with the response data
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Log.d("TAG", "onClick: Response " + responseData);
                        Gson gson = new Gson();
                        ChatResponse chatResponse = gson.fromJson(responseData, ChatResponse.class);
                        if (chatResponse != null && chatResponse.getChoices() != null && chatResponse.getChoices().size() > 0) {
                            String messageContent = chatResponse.getChoices().get(0).getMessage().getContent();
                            System.out.println("Message content: " + messageContent);
                            onSendChatCallBack.onSuccess(messageContent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
                onSendChatCallBack.onError(e.getMessage());
            }
        });
    }

    public interface OnSendChatCallBack{
        void onSuccess(String text);
        void onError(String text);
    }
}

