package com.example.ajudantedecostura.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.ActivityCadastroBinding;
import modelDominio.Costureira;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CadastroActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ActivityCadastroBinding binding;
    InformacoesApp informacoesApp;
    String msgRecebida;
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.activityCadastroBtnCancelar.setOnClickListener(view -> {
            finish();
        });

        informacoesApp = (InformacoesApp) getApplicationContext();

        binding.activityCadastroTxtDataNascimento.setInputType(InputType.TYPE_NULL);

        binding.activityCadastroTxtDataNascimento.setOnClickListener(v -> {
            // date picker dialog
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(), "Date dialog");
        });

        binding.activityCadastroBtnCadastrar.setOnClickListener(v -> {
            if (!binding.activityCadastroTxtNome.getText().toString().equals("")){
                if (!binding.activityCadastroTxtSenha.getText().toString().equals("")) {
                    if (!binding.activityCadastroTxtConfirmarSenha.getText().toString().equals("")){
                        if (!binding.activityCadastroTxtTelefone.getText().toString().equals("")){
                            if (!binding.activityCadastroTxtCpf.getText().toString().equals("")){
                                if (!binding.activityCadastroTxtEmail.getText().toString().equals("")){
                                    if (!binding.activityCadastroTxtDataNascimento.getText().toString().equals("")){
                                        if (!binding.activityCadastroTxtCep.getText().toString().equals("")){
                                            if (!binding.activityCadastroTxtEstado.getText().toString().equals("")){
                                                if (!binding.activityCadastroTxtCidade.getText().toString().equals("")){
                                                    if (!binding.activityCadastroTxtRua.getText().toString().equals("")){
                                                        if (!binding.activityCadastroTxtNumero.getText().toString().equals("")) {
                                                            if (binding.activityCadastroTxtSenha.getText().toString().equals(binding.activityCadastroTxtConfirmarSenha.getText().toString())){
                                                                String nome, senha, telefone, cpf, email, estado, cidade, rua;
                                                                nome = binding.activityCadastroTxtNome.getText().toString();
                                                                senha = binding.activityCadastroTxtSenha.getText().toString();
                                                                telefone = binding.activityCadastroTxtTelefone.getText().toString();
                                                                cpf = binding.activityCadastroTxtCpf.getText().toString();
                                                                email = binding.activityCadastroTxtEmail.getText().toString();

                                                                Date data = null;
                                                                try {
                                                                    data = dataFormatada.parse(binding.activityCadastroTxtDataNascimento.getText().toString());
                                                                    Log.i(TAG, "onCreate: " + data);
                                                                } catch (ParseException e) {
                                                                    e.printStackTrace();
                                                                }

                                                                Integer cep = Integer.parseInt(binding.activityCadastroTxtCep.getText().toString());
                                                                estado = binding.activityCadastroTxtEstado.getText().toString();
                                                                cidade = binding.activityCadastroTxtCidade.getText().toString();
                                                                rua = binding.activityCadastroTxtRua.getText().toString();
                                                                Integer numero = Integer.parseInt(binding.activityCadastroTxtNumero.getText().toString());
                                                                Costureira costureira = new Costureira(senha, null, cpf, nome, email, telefone, data, cep, estado, cidade, rua, numero, 0);

                                                                Thread thread = new Thread((Runnable) () -> {

                                                                    ConexaoSocketController conexaoController = new ConexaoSocketController(informacoesApp);
                                                                    msgRecebida = conexaoController.cadastraCostureira(costureira);

                                                                    runOnUiThread((Runnable) () -> {
                                                                        Toast.makeText(informacoesApp, msgRecebida, Toast.LENGTH_SHORT).show();
                                                                    });

                                                                });
                                                                thread.start();

                                                            } else {
                                                                binding.activityCadastroTxtConfirmarSenha.requestFocus();
                                                                binding.activityCadastroTxtConfirmarSenha.setError("Erro: Senhas não coincidem");
                                                            }
                                                        } else {
                                                            binding.activityCadastroTxtNumero.requestFocus();
                                                            binding.activityCadastroTxtNumero.setError("Erro: Campo obrigatório");
                                                        }
                                                    } else {
                                                        binding.activityCadastroTxtRua.requestFocus();
                                                        binding.activityCadastroTxtRua.setError("Erro: Campo obrigatório");
                                                    }
                                                } else {
                                                    binding.activityCadastroTxtCidade.requestFocus();
                                                    binding.activityCadastroTxtCidade.setError("Erro: Campo obrigatório");
                                                }
                                            } else {
                                                binding.activityCadastroTxtEstado.requestFocus();
                                                binding.activityCadastroTxtEstado.setError("Erro: Campo obrigatório");
                                            }
                                        } else {
                                            binding.activityCadastroTxtCep.requestFocus();
                                            binding.activityCadastroTxtCep.setError("Erro: Campo obrigatório");
                                        }
                                    } else {
                                        binding.activityCadastroTxtDataNascimento.requestFocus();
                                        binding.activityCadastroTxtDataNascimento.setError("Erro: Campo obrigatório");
                                    }
                                } else {
                                    binding.activityCadastroTxtEmail.requestFocus();
                                    binding.activityCadastroTxtEmail.setError("Erro: Campo obrigatório");
                                }
                            } else {
                                binding.activityCadastroTxtCpf.requestFocus();
                                binding.activityCadastroTxtCpf.setError("Erro: Campo obrigatório");
                            }
                        } else {
                            binding.activityCadastroTxtTelefone.requestFocus();
                            binding.activityCadastroTxtTelefone.setError("Erro: Campo obrigatório");
                        }
                    } else {
                        binding.activityCadastroTxtConfirmarSenha.requestFocus();
                        binding.activityCadastroTxtConfirmarSenha.setError("Erro: Campo obrigatório");
                    }
                } else {
                    binding.activityCadastroTxtSenha.requestFocus();
                    binding.activityCadastroTxtSenha.setError("Erro: Campo obrigatório");
                }
            } else {
                binding.activityCadastroTxtNome.requestFocus();
                binding.activityCadastroTxtNome.setError("Erro: Campo obrigatório");

            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        Calendar c = new GregorianCalendar(ano, mes, dia);
        Log.d("VVV", dataFormatada.format(c.getTime()));
        binding.activityCadastroTxtDataNascimento.setText(dataFormatada.format(c.getTime()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}