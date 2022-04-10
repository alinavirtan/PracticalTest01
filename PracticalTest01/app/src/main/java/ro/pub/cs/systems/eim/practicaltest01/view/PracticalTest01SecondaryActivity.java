package ro.pub.cs.systems.eim.practicaltest01.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ro.pub.cs.systems.eim.practicaltest01.R;
import ro.pub.cs.systems.eim.practicaltest01.general.Constants;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {
    private Button okBtn;
    private Button cancelBtn;
    private TextView countClicksTextView;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.ok_btn:
                    setResult(RESULT_OK, null);
                    break;

                case R.id.cancel_btn:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        okBtn = (Button) findViewById(R.id.ok_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        countClicksTextView = (TextView) findViewById(R.id.total_counter);

        okBtn.setOnClickListener(buttonClickListener);
        cancelBtn.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER_OF_CLICKS)) {
            int numberOfClicks = intent.getExtras().getInt(Constants.NUMBER_OF_CLICKS, -1);
            countClicksTextView.setText(String.valueOf(numberOfClicks));
        }
    }

}
