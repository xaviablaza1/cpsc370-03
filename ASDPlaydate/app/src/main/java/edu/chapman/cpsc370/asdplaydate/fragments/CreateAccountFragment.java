package edu.chapman.cpsc370.asdplaydate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.chapman.cpsc370.asdplaydate.R;
import edu.chapman.cpsc370.asdplaydate.activities.AccountActivity;
import edu.chapman.cpsc370.asdplaydate.activities.ChatActivity;
import edu.chapman.cpsc370.asdplaydate.activities.ProfileActivity;

/**
 * Created by TheHollowManV on 11/4/2015.
 */

public class CreateAccountFragment extends Fragment
{
    EditText email;
    EditText pass;
    EditText passCom;
    TextView link;
    Button createAccountBtn;

    public static final String ACCOUNT_EMAIL = "account_email";
    public static final String ACCOUNT_PASSWORD = "account_password";

    public CreateAccountFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);
        email = (EditText)rootView.findViewById(R.id.editText);
        pass = (EditText)rootView.findViewById(R.id.editText2);
        passCom= (EditText)rootView.findViewById(R.id.editText3);
        link = (TextView)rootView.findViewById(R.id.textView);
        createAccountBtn = (Button)rootView.findViewById(R.id.button);

        createAccountBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Check if email field is empty
                String emailAddress = email.getText().toString().trim();
                if(emailAddress.equals("") || !emailAddress.contains("@") || emailAddress == null)
                {
                    Toast.makeText(getActivity(), getString(R.string.invalid_email), Toast.LENGTH_LONG).show();
                }
                // Check if password matches password confirmation
                else if(!pass.getText().toString().equals(passCom.getText().toString())
                        || pass.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getActivity(), getString(R.string.incorrect_passwords), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    intent.putExtra(ACCOUNT_EMAIL, email.getText().toString());
                    intent.putExtra(ACCOUNT_PASSWORD, pass.getText().toString());

                    getActivity().startActivity(intent);
                }
            }
        });

        link.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((AccountActivity)getActivity()).loadLogin();
            }
        });
        return rootView;
    }

}
