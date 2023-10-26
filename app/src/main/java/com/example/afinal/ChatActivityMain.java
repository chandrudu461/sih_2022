package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ChatActivityMain extends AppCompatActivity {
     String sender_uid,reciever_uid;
     String message;
     EditText edt_text;
     Button sendmsg;
     ChattingPostModel chatmodel=new ChattingPostModel();
//    DatabaseReference chatReference = FirebaseDatabase.getInstance().getReference().child("chats");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);
        sender_uid="WYxZeKlCuqY2bjc1zQ2UWEUnZf33";
        reciever_uid="Y0QhVpcK0TP3WdHuR78fveAQE362";
//        sender_uid=getIntent().getDataString("senderUid");
//        reciever_uid=getIntent().getDataString("recieverUid");
        edt_text=findViewById(R.id.edtText_EnterMessage);
        sendmsg=findViewById(R.id.submitSendMessage);
        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message=edt_text.getText().toString();
                if(message.equals("")){
                    Toast.makeText(ChatActivityMain.this, "enter message to send", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference putchat=FirebaseDatabase.getInstance().getReference("chats").child(sender_uid).child("sendingRoom");
                    chatmodel.setMessage(message);
                    chatmodel.setType("sent");
                    chatmodel.setDate_time(String.valueOf(new Date().getTime()));
                    chatmodel.setUid(sender_uid);
                    putchat.push().setValue(chatmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            DatabaseReference ref=FirebaseDatabase.getInstance().getReference("chats").child(reciever_uid).child("recieverRoom");
                            chatmodel.setType("recieved");
                            ref.push().setValue(chatmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }
                    });
                }
            }
        });

    }
}