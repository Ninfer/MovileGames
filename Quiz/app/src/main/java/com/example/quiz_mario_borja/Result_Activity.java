package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_mario_borja.adaptadores.ResultadosAdapter;
import com.example.quiz_mario_borja.db.DbHelper;
import com.example.quiz_mario_borja.db.DbQuiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Result_Activity extends AppCompatActivity {

    private Button restartButton, exitButton;
    private RecyclerView resultados;
    private TextView jofrancosText;
    private ArrayList<Object> listaResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        restartButton = findViewById(R.id.restart_button);
        exitButton = findViewById(R.id.exit_button2);
        resultados = findViewById(R.id.RV_Resultados);
        jofrancosText = findViewById(R.id.jofrancosText);
        resultados.setLayoutManager(new LinearLayoutManager(Result_Activity.this));

        Integer result = getIntent().getExtras().getInt("jofrancos");
        jofrancosText.setText(result.toString() + " JOFRANCOS");

        listaResultados = new ArrayList<>();

        ResultadosAdapter adapter = new ResultadosAdapter(mostrarResultados(result));
        resultados.setAdapter(adapter);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result_Activity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Muestra un mensaje de tipo "Toast" con los puntos conseguidos hasta el momento
                // y sale al menú pricipal.
                Intent outIntent = new Intent(Result_Activity.this, Start_Activity.class);
                Toast.makeText(Result_Activity.this,  "¡No olvides volver a jugar pronto!", Toast.LENGTH_LONG).show();
                startActivity(outIntent);
            }
        });
    }

    public ArrayList<Object[]> mostrarResultados(int result){
        DbHelper dbHelper = new DbHelper(Result_Activity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursorResultados = null;

        ArrayList<Object[]> listaResultados = new ArrayList<>();

        cursorResultados = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_RESULT + " ORDER BY puntos DESC", null);

        if(cursorResultados.moveToFirst()){
            do{
                Object resultados [] = new Object[2];
                resultados[0] = cursorResultados.getString(1);
                resultados[1] = cursorResultados.getInt(2);

                listaResultados.add(resultados);
            } while(cursorResultados.moveToNext());
        }

        cursorResultados.close();

        listaResultados = playerFixer(listaResultados, result);

        return listaResultados;
    }

    private ArrayList<Object[]> playerFixer(ArrayList<Object[]> listaResultados, int result) {
        SharedPreferences sp = getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);
        String name = sp.getString("name", "Nombre");

        int id = 0;

        /*
        for(int i = 0; i < listaResultados.size(); i++){
            if((listaResultados.get(i)[0].toString().equals(name)) && ((int) listaResultados.get(i)[1]) <= result){
                listaResultados.get(i)[0] = name;
                listaResultados.get(i)[1] = result;
                flag = true;
            }
        }
        */

        //Ordenar lista por los resultados
        DbQuiz dbQuiz = new DbQuiz(Result_Activity.this);
        dbQuiz.insertPlayer(name, result);

        Object aux [] = new Object[2];
        Object add [] = new Object[2];
        aux [0] = name;
        aux [1] = result;

        //introducir el último elemento en la posición correcta
        int index = 0;
        boolean flag = true;
        for(int i = 0; i < listaResultados.size(); i++){
            add = listaResultados.get(i);
            if(((Integer)add[1] < (Integer) aux[1]) && flag){
                index = i;
                flag = false;
            }
        }
        add = listaResultados.get(0);
        if((index == 0) && ((Integer)add[1] > (Integer) aux[1])){
            listaResultados.add(aux);
        } else{
            listaResultados.add(index, aux);
        }



        return listaResultados;
    }
}