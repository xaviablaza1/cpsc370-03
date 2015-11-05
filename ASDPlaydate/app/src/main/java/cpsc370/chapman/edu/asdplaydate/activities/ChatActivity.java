package cpsc370.chapman.edu.asdplaydate.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import cpsc370.chapman.edu.asdplaydate.R;
import cpsc370.chapman.edu.asdplaydate.adapters.ChatMessageAdapter;
import cpsc370.chapman.edu.asdplaydate.models.ChatMessage;

public class ChatActivity extends AppCompatActivity
{
    ListView lv_displayMessages;
    FloatingActionButton fab_sendMessage;
    ChatMessageAdapter messageAdapter;
    EditText et_message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        fab_sendMessage = (FloatingActionButton) findViewById(R.id.fab_sendMessage);
        lv_displayMessages = (ListView) findViewById(R.id.lv_displayMessages);
        et_message = (EditText) findViewById(R.id.et_message);

        messageAdapter = new ChatMessageAdapter(this, null);


        //for testing only
        ChatMessage message1 = new ChatMessage();
        message1.setDateTime("11/4/15 7:15pm");
        message1.setIsMe(false);
        message1.setMessageText("Hello!");
        message1.setMessageID(121);
        message1.setUserID(1235);
        displayMessage(message1);

        ChatMessage message2 = new ChatMessage();
        message2.setDateTime("11/4/15 7:16pm");
        message2.setIsMe(false);
        message2.setMessageText("How are you?");
        message2.setMessageID(122);
        message2.setUserID(1235);
        displayMessage(message2);

        lv_displayMessages.setAdapter(messageAdapter);

        fab_sendMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Snackbar.make(view, "Message Sent", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                ChatMessage message = new ChatMessage();

                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();

                message.setDateTime(Integer.toString(today.month + 1) + "/" + Integer.toString(today.monthDay) + "/" + Integer.toString(today.year) + " " + Integer.toString(today.hour) + ":" + Integer.toString(today.minute));
                message.setIsMe(true);
                message.setMessageText(String.valueOf(et_message.getText()));
                message.setMessageID(123);
                message.setUserID(1234);
                displayMessage(message);

                et_message.setText("");
            }
        });
    }

    public void displayMessage(ChatMessage message)
    {
        messageAdapter.add(message);
        messageAdapter.notifyDataSetChanged();
    }

}