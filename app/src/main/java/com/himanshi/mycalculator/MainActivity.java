package com.himanshi.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    TextView resultTv , solTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus, buttonMinus ,buttonEquals;
    MaterialButton button1, button2, button3,button4,button5,button6,button7,button8,button9,button0;
    MaterialButton buttonAC,buttonDot;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solTv=findViewById(R.id.sol_tv);

        assignId(buttonC, R.id.button_c );
        assignId(buttonBrackOpen, R.id.button_open);
        assignId(buttonBrackClose, R.id.button_close );
        assignId(buttonDivide, R.id.button_divide );
        assignId(buttonMultiply, R.id.button_multiply );
        assignId(buttonPlus, R.id.button_plus );
        assignId(buttonMinus, R.id.button_minus );
        assignId(buttonEquals, R.id.button_equal );
        assignId(button1, R.id.button_1 );
        assignId(button2, R.id.button_2 );
        assignId(button3, R.id.button_3 );
        assignId(button4, R.id.button_4 );
        assignId(button5, R.id.button_5 );
        assignId(button6, R.id.button_6 );
        assignId(button7, R.id.button_7 );
        assignId(button8, R.id.button_8 );
        assignId(button9, R.id.button_9 );
        assignId(buttonAC, R.id.button_ac );
        assignId(buttonDot, R.id.button_dot );
        assignId(button0, R.id.button_0 );
    }



    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();

        String dataTocalculate = solTv.getText().toString();

        if (buttonText.equals("AC")){
            solTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals('=')){
            solTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataTocalculate= dataTocalculate.substring(0,dataTocalculate.length()-1);
        }else {
            dataTocalculate=dataTocalculate+buttonText;

        }


        solTv.setText(dataTocalculate);
        String finalresult = getresult(dataTocalculate);
        if (!finalresult.equals("Err")){
            resultTv.setText(finalresult);
        }
        
        
    }
    String getresult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable= context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable,data, "Javascript",1,null).toString();
            if (finalresult.endsWith(".0")){
                finalresult = finalresult.replace(" .0", "");
            }

            return finalresult;
        }catch (Exception e){
            return "Err";
        }
    }
}