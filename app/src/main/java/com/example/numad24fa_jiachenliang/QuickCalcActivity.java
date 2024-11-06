package com.example.numad24fa_jiachenliang;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuickCalcActivity extends AppCompatActivity {

    private TextView calcDisplay;
    private StringBuilder currentExpression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quic_calc);

        calcDisplay = findViewById(R.id.calc_display);

        // Set up listeners for number buttons
        findViewById(R.id.button_0).setOnClickListener(v -> appendToDisplay("0"));
        findViewById(R.id.button_1).setOnClickListener(v -> appendToDisplay("1"));
        findViewById(R.id.button_2).setOnClickListener(v -> appendToDisplay("2"));
        findViewById(R.id.button_3).setOnClickListener(v -> appendToDisplay("3"));
        findViewById(R.id.button_4).setOnClickListener(v -> appendToDisplay("4"));
        findViewById(R.id.button_5).setOnClickListener(v -> appendToDisplay("5"));
        findViewById(R.id.button_6).setOnClickListener(v -> appendToDisplay("6"));
        findViewById(R.id.button_7).setOnClickListener(v -> appendToDisplay("7"));
        findViewById(R.id.button_8).setOnClickListener(v -> appendToDisplay("8"));
        findViewById(R.id.button_9).setOnClickListener(v -> appendToDisplay("9"));

        // Set up listeners for operator buttons
        findViewById(R.id.button_plus).setOnClickListener(v -> appendToDisplay("+"));
        findViewById(R.id.button_minus).setOnClickListener(v -> appendToDisplay("-"));

        // Set up backspace button 'x'
        findViewById(R.id.button_clear).setOnClickListener(v -> removeLastCharacter());

        // Set up equals button '='
        findViewById(R.id.button_equals).setOnClickListener(v -> evaluateExpression());
    }

    private void appendToDisplay(String value) {
        currentExpression.append(value);
        calcDisplay.setText(currentExpression.toString());
    }

    private void removeLastCharacter() {
        if (currentExpression.length() > 0) {
            currentExpression.deleteCharAt(currentExpression.length() - 1);
            calcDisplay.setText(currentExpression.toString());
        }
    }

    private void evaluateExpression() {
        try {
            // Basic expression evaluation for + and - operations only
            double result = eval(currentExpression.toString());
            calcDisplay.setText(String.valueOf(result));
            currentExpression.setLength(0); // Reset expression after evaluation
        } catch (Exception e) {
            calcDisplay.setText("Error");
        }
    }

    // Basic evaluation method for expressions containing + and -
    private double eval(String expression) {
        // Split the expression by "+" or "-" to handle simple arithmetic
        if (expression.contains("+")) {
            String[] parts = expression.split("\\+");
            return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
        } else if (expression.contains("-")) {
            String[] parts = expression.split("-");
            return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
        } else {
            // If no operator is found, return the number as it is
            return Double.parseDouble(expression);
        }
    }
}
