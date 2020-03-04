package id.deuboe.kalkulator;

import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import android.text.Editable;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  Button buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix,
      buttonSeven, buttonEight, buttonNine, buttonClear, buttonBracket, buttonPercent, buttonDot,
      buttonDivision, buttonMultiply, buttonPlus, buttonMinus, buttonEqual;

  ImageButton buttonDelete;

  TextView textOutput;

  EditText textInput;

  String process;

  boolean checkBracket;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    buttonZero = findViewById(R.id.buttonZero);
    buttonZero.setOnClickListener(this);
    buttonOne = findViewById(R.id.buttonOne);
    buttonOne.setOnClickListener(this);
    buttonTwo = findViewById(R.id.buttonTwo);
    buttonTwo.setOnClickListener(this);
    buttonThree = findViewById(R.id.buttonThree);
    buttonThree.setOnClickListener(this);
    buttonFour = findViewById(R.id.buttonFour);
    buttonFour.setOnClickListener(this);
    buttonFive = findViewById(R.id.buttonFive);
    buttonFive.setOnClickListener(this);
    buttonSix = findViewById(R.id.buttonSix);
    buttonSix.setOnClickListener(this);
    buttonSeven = findViewById(R.id.buttonSeven);
    buttonSeven.setOnClickListener(this);
    buttonEight = findViewById(R.id.buttonEight);
    buttonEight.setOnClickListener(this);
    buttonNine = findViewById(R.id.buttonNine);
    buttonNine.setOnClickListener(this);
    buttonClear = findViewById(R.id.buttonClear);
    buttonClear.setOnClickListener(this);
    buttonBracket = findViewById(R.id.buttonBrackets);
    buttonBracket.setOnClickListener(this);
    buttonPercent = findViewById(R.id.buttonPercent);
    buttonPercent.setOnClickListener(this);
    buttonDot = findViewById(R.id.buttonDot);
    buttonDot.setOnClickListener(this);
    buttonDivision = findViewById(R.id.buttonDivision);
    buttonDivision.setOnClickListener(this);
    buttonMultiply = findViewById(R.id.buttonMultiply);
    buttonMultiply.setOnClickListener(this);
    buttonPlus = findViewById(R.id.buttonPlus);
    buttonPlus.setOnClickListener(this);
    buttonMinus = findViewById(R.id.buttonMinus);
    buttonMinus.setOnClickListener(this);
    buttonEqual = findViewById(R.id.buttonEqual);
    buttonEqual.setOnClickListener(this);
    buttonDelete = findViewById(R.id.buttonDelete);
    buttonDelete.setOnClickListener(this);

    textInput = findViewById(R.id.textInput);
    textOutput = findViewById(R.id.textOutput);

    textInput.setRawInputType(InputType.TYPE_NULL);
    textInput.addTextChangedListener(textWatcher);

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.buttonClear:
//        textInput.setText("");
//        textOutput.setText("");
        clear();
        break;
      case R.id.buttonZero:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._0)));
        break;
      case R.id.buttonOne:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._1)));
        break;
      case R.id.buttonTwo:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._2)));
        break;
      case R.id.buttonThree:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._3)));
        break;
      case R.id.buttonFour:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._4)));
        break;
      case R.id.buttonFive:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._5)));
        break;
      case R.id.buttonSix:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._6)));
        break;
      case R.id.buttonSeven:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._7)));
        break;
      case R.id.buttonEight:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._8)));
        break;
      case R.id.buttonNine:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string._9)));
        break;
      case R.id.buttonDivision:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string.division)));
        break;
      case R.id.buttonMultiply:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string.multiply)));
        break;
      case R.id.buttonPlus:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string.plus)));
        break;
      case R.id.buttonMinus:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string.minus)));
        break;
      case R.id.buttonPercent:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string.percent)));
        break;
      case R.id.buttonDot:
        process = textInput.getText().toString();
        textInput.setText(String.format("%s%s", process, getText(R.string.dot)));
        break;
      case R.id.buttonBrackets:
        if (checkBracket) {
          process = textInput.getText().toString();
          textInput.setText(String.format("%s%s", process, getText(R.string.close_bracket)));
          checkBracket = false;
        } else {
          process = textInput.getText().toString();
          textInput.setText(String.format("%s%s", process, getText(R.string.open_bracket)));
          checkBracket = true;
        }
        break;
      case R.id.buttonEqual:
        process = textInput.getText().toString();
        calculate(true);
        break;
      case R.id.buttonDelete:
        delete();
        break;
    }
  }

  private void clear() {
    textInput.setText("");
    textOutput.setText("");
  }

  private void delete() {
    if (isEmpty() > 1) {
      this.textInput.getText().delete(getInput().length() -1, getInput().length());
    } else if (isEmpty() == 1){
      clear();
    }
  }

  private String getInput() {
    return this.textInput.getText().toString();
  }

  private int isEmpty() {
    return getInput().length();
  }

  private void calculate(boolean isClick) {
    process = textInput.getText().toString();
    process = process.replaceAll("×", "*");
    process = process.replaceAll("%", "/100");
    process = process.replaceAll("÷", "/");

    Context rhino = Context.enter();

    rhino.setOptimizationLevel(-1);
    String finalResult;
    try {
      ScriptableObject scriptableObject = rhino.initStandardObjects();
      finalResult = rhino.evaluateString(scriptableObject, process, "javascript", 1, null).toString();
    } catch (Exception e) {
      finalResult = getString(R.string._0);
    }

    if (isClick) {
      textInput.setText(finalResult);
      textOutput.setText("");
    } else {
      textOutput.setText(finalResult);
    }
  }

  TextWatcher textWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      calculate(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
  };

}
