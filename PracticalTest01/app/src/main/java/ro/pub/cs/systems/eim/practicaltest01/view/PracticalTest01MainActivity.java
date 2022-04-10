package ro.pub.cs.systems.eim.practicaltest01.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01.R;
import ro.pub.cs.systems.eim.practicaltest01.general.Constants;

public class PracticalTest01MainActivity extends AppCompatActivity {
    private Button pressMeBtn;
    private Button pressMeTooBtn;
    private EditText leftContor;
    private EditText rightContor;
    private Button navigateBtn;
    private ButtonClickListener ButtonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.press_me_btn:
                    int leftClicks = Integer.valueOf(leftContor.getText().toString());
                    leftContor.setText(String.valueOf(leftClicks + 1));
                    break;
                case R.id.press_me_too_btn:
                    int rightClicks = Integer.valueOf(rightContor.getText().toString());
                    rightContor.setText(String.valueOf(rightClicks + 1));
                    break;
                case R.id.navigate_btn:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    int nrOfClicks = Integer.valueOf(leftContor.getText().toString()) + Integer.valueOf(rightContor.getText().toString());
                    intent.putExtra(Constants.NUMBER_OF_CLICKS, nrOfClicks);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        pressMeBtn = (Button) findViewById(R.id.press_me_btn);
        pressMeTooBtn = (Button) findViewById(R.id.press_me_too_btn);
        leftContor = (EditText) findViewById(R.id.left_contor);
        rightContor = (EditText) findViewById(R.id.right_contor);
        navigateBtn = (Button) findViewById(R.id.navigate_btn);

        pressMeBtn.setOnClickListener(ButtonClickListener);
        pressMeTooBtn.setOnClickListener(ButtonClickListener);
        navigateBtn.setOnClickListener(ButtonClickListener);

        // posibil sa nu mai fie nevoie, testez
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
                leftContor.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
            } else {
                leftContor.setText(String.valueOf(0));
            }

            if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
                rightContor.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
            } else {
                rightContor.setText(String.valueOf(0));
            }
        } else {
            leftContor.setText(String.valueOf(0));
            rightContor.setText(String.valueOf(0));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.LEFT_COUNT, leftContor.getText().toString());
        savedInstanceState.putString(Constants.RIGHT_COUNT, rightContor.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
            leftContor.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
        } else {
            leftContor.setText(String.valueOf(0));
        }

        if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
            rightContor.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
        } else {
            rightContor.setText(String.valueOf(0));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "Secondary activity returned with code " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}