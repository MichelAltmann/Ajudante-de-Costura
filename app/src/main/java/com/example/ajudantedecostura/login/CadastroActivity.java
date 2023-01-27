package com.example.ajudantedecostura.login;

import static android.app.DatePickerDialog.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ajudantedecostura.R;
import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.ActivityCadastroBinding;
import com.example.ajudantedecostura.modelDominio.Costureira;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    InformacoesApp informacoesApp;
    String msgRecebida;
    DatePicker picker;
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
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog


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
                                                                Date data =  (Date) binding.activityCadastroTxtDataNascimento.getText();
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
}