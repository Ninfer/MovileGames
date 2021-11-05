package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class Options_Activity extends AppCompatActivity {

    private Button backButton;
    private Switch swichSound;
    private RadioGroup radioGruop;
    private EditText nameText;
    public int lvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        backButton = findViewById(R.id.back_button);
        radioGruop = findViewById(R.id.Radio_Group);
        nameText = findViewById(R.id.text_name_op);
        swichSound = findViewById(R.id.switch_sound);

        //Selección de modo de juego
        radioGruop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton_facil){
                    Toast.makeText(Options_Activity.this, "¡Modo Fácil Activado!",Toast.LENGTH_LONG).show();
                    lvl = 5;
                } else if(checkedId == R.id.radioButton_medio){
                    Toast.makeText(Options_Activity.this, "¡Modo Normal Activado!",Toast.LENGTH_LONG).show();
                    lvl = 10;
                } else if(checkedId == R.id.radioButton_dificil){
                    Toast.makeText(Options_Activity.this, "¡Modo Dificil Activado!",Toast.LENGTH_LONG).show();
                    lvl = 15;
                }
            }
        });

        cargarPreferencias();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Options_Activity.this, Start_Activity.class);
                guardarPreferencias();
                startActivity(intent);
            }
        });

    }

    //Guardar las preferencias del menú de opciones
    public void guardarPreferencias(){
        SharedPreferences sp = getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        if(nameText.getText().toString().equals("Anónimo")){
            Toast.makeText(Options_Activity.this, "Nombre Anónimo de usuario utilizado!",Toast.LENGTH_LONG).show();
            nameText.setText("R4nD0m_3");
        }

        editor.putString("name", nameText.getText().toString());
        editor.putInt("dificult", radioGruop.getCheckedRadioButtonId());
        editor.putInt("lvl", lvl);
        editor.putBoolean("sound", swichSound.isChecked());

        editor.commit();
    }

    public void cargarPreferencias(){
        SharedPreferences sp = getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);

        String name = sp.getString("name", "Anónimo");
        int dificult = sp.getInt("dificult", 2131231156);
        boolean sound = sp.getBoolean("sound", true);

        nameText.setText(name);
        radioGruop.check(dificult);
        swichSound.setChecked(sound);
    }
}