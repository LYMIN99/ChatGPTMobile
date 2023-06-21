package com.coddy.chatgptmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.coddy.chatgptmobile.adapter.ChatsAdapter;
import com.coddy.chatgptmobile.model.Chats;
import com.coddy.chatgptmobile.tools.HttpHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvAskMe;
    private EditText edChat;
    private ImageButton btnSend;
    private RecyclerView recyclerView;
    private ChatsAdapter adapter;
    private List<Chats> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAskMe = findViewById(R.id.tv_ask);
        edChat = findViewById(R.id.ed_ask);
        btnSend = findViewById(R.id.btn_send);
        recyclerView = findViewById(R.id.recycler_view);

        //Setup RecyclerView

        setUpList();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendChat(edChat.getText().toString());
            }
        });
    }

    private void sendChat(String message) {
        if (message.isEmpty()){
            edChat.setError("Please enter something");
        } else {
            list.add(new Chats(message,"RIGHT"));
            edChat.setText("");
            adapter.setList(list);
            tvAskMe.setVisibility(View.GONE);
            new HttpHelper().sendPostChatRequest(message, new HttpHelper.OnSendChatCallBack() {
                @Override
                public void onSuccess(String text) {
                    list.add(new Chats(text,"LEFT"));
                    adapter.setList(list);
                }

                @Override
                public void onError(String text) {

                }
            });
        }
    }

    private void setUpList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatsAdapter();
        list = new ArrayList<>();
        recyclerView.setAdapter(adapter);// We need an Adapter
    }
}