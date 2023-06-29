package kz.talipovsn.quadratic;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Локальные переменные для доступа к компонентам окна
    private EditText editText_a, editText_b, editText_x;
    private TextView textView_y;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация переменных доступа к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_x = findViewById(R.id.editText_x);
        textView_y = findViewById(R.id.textView_y);
        button = findViewById(R.id.button);

        View.OnKeyListener myKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (editText_a.getText().toString().trim().equals("") ||
                        editText_b.getText().toString().trim().equals("")||
                        editText_x.getText().toString().trim().equals("")) {
                    button.setEnabled(false); // Выключаем доступность нажатия у кнопки
                } else {
                    button.setEnabled(true); // Включаем доступность нажатия у кнопки
                }
                return false;
            }
        };

        button.setEnabled(false); // Выключаем доступность нажатия у кнопки
        editText_a.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_b.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_x.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий


        // Проверка на переворот экрана и восстановление нужных значений в компонентах
        if (savedInstanceState != null) {
            textView_y.setText(savedInstanceState.getString("y"));
            button.setEnabled(savedInstanceState.getBoolean("b"));

        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Сохранение нужных нам значений компонент при перевороте экрана
        outState.putString("y", textView_y.getText().toString());
        outState.putBoolean("b", button.isEnabled());

    }

    // МЕТОД ДЛЯ КНОПКИ РАСЧЕТА
    @SuppressLint("DefaultLocale")
    public void onCalc(View v) {

        // Локальные переменные
        double a, b, x, y;

        try {
            // Считывание введенных входных значений в переменные
            a = Double.parseDouble(editText_a.getText().toString());
            b = Double.parseDouble(editText_b.getText().toString());
            x = Double.parseDouble(editText_x.getText().toString());
        } catch (Exception e) {
            // Выдача всплывающего сообщения на экран об ошибке
            Toast.makeText(MainActivity.this, "Неверные входные данные!",
                    Toast.LENGTH_LONG).show();
            return;
        }


        // Основной алгоритм
        if (x<7){
            y = Math.pow(x,2)+Math.pow(a,2)+Math.pow(b,2);
        }
        else {
            y = (a+b)/(Math.pow(x,3)*Math.pow(a+b,2));
        }
        try{

            textView_y.setText(String.format("y = %.3f", y));

        } catch (Exception e) {
            String err = "Нет решения!";
            textView_y.setText(err);

        }

    }

}
