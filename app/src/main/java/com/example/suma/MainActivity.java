package com.example.suma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador=0;
        mostrarContador();
        EventoTeclado eTeclado= new EventoTeclado();
        EditText numeroReset = (EditText) findViewById(R.id.numReset);
        numeroReset.setOnEditorActionListener(eTeclado);
    }

/*    @Override
    protected void onSaveInstanceState(@NonNull Bundle estado) {
        estado.putInt("valor", contador);
        super.onSaveInstanceState(estado);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contador = savedInstanceState.getInt("cuenta");
        mostrarContador();
    }Comprobar inicio de sesión en GitHub desde Android Studio a través de la ruta File > Settings > Version Control > GitHub,
     e indicar los datos de la cuenta de usuario creada.
     A continuación pulsar sobre el botón «Test» para comprobar que los datos de conexión son correctos:
*/

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences datos =  (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor= datos.edit();
        miEditor.putInt("valor", contador);
        miEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences datos =  (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(this);
        contador = datos.getInt("valor", 0);
        mostrarContador();
    }

    public void incrementaContador(View view) {
        contador++;
        mostrarContador();
    }

    public void decrementaContador(View vista) {
        contador--;
        CheckBox negativos = (CheckBox) findViewById(R.id.numNeg);
        if ((contador < 0) && !(negativos.isChecked()) ) {
                contador=0;
            }
        mostrarContador();
    }
    class EventoTeclado implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i== EditorInfo.IME_ACTION_DONE){
                reseteaContador(null);
            }
            return false;
        }
    }

    public void reseteaContador(View vista){
        EditText numeroReset = (EditText) findViewById(R.id.numReset);
        contador = Integer.parseInt(numeroReset.getText().toString());
        mostrarContador();
        InputMethodManager teclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        teclado.hideSoftInputFromWindow(numeroReset.getWindowToken(),0);
    }

    public void mostrarContador (){
        TextView textoContador = (TextView) findViewById(R.id.resultado);
        textoContador.setText(String.valueOf(contador));
    }
}