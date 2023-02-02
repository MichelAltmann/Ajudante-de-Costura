package com.example.ajudantedecostura.login;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.ActivityCadastroBinding;
import modelDominio.Costureira;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CadastroActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ActivityCadastroBinding binding;

    Bitmap selectedImageBitmap;
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

        binding.activityCadastroAdicionaImagem.setOnClickListener(v -> {
            imageChooser();
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
                                                            if (binding.activityCadastroAdicionaImagem.getDrawable() != null){
                                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                                selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                                                byte[] imagem = stream.toByteArray();
                                                                if (binding.activityCadastroTxtSenha.getText().toString().equals(binding.activityCadastroTxtConfirmarSenha.getText().toString())){
                                                                    String nome, senha, senhaHash = null, telefone, cpf, email, estado, cidade, rua;
                                                                    nome = binding.activityCadastroTxtNome.getText().toString();
                                                                    senha = binding.activityCadastroTxtSenha.getText().toString();
                                                                    telefone = binding.activityCadastroTxtTelefone.getText().toString();
                                                                    cpf = binding.activityCadastroTxtCpf.getText().toString();
                                                                    email = binding.activityCadastroTxtEmail.getText().toString();

                                                                    Date data = null;
                                                                    try {
                                                                        // transformando senha em código hash
                                                                        //----------------------------------------
                                                                        MessageDigest md = MessageDigest.getInstance("MD5"); // MD5, SHA-1, SHA-256
                                                                        BigInteger senhaCadastrada = new BigInteger(1, md.digest(senha.getBytes()));
                                                                        senhaHash = String.valueOf(senhaCadastrada);
                                                                        //----------------------------------------
                                                                        data = dataFormatada.parse(binding.activityCadastroTxtDataNascimento.getText().toString());
                                                                        Log.i(TAG, "onCreate: " + data);
                                                                    } catch (ParseException | NoSuchAlgorithmException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                    Integer cep = Integer.parseInt(binding.activityCadastroTxtCep.getText().toString());
                                                                    estado = binding.activityCadastroTxtEstado.getText().toString();
                                                                    cidade = binding.activityCadastroTxtCidade.getText().toString();
                                                                    rua = binding.activityCadastroTxtRua.getText().toString();
                                                                    Integer numero = Integer.parseInt(binding.activityCadastroTxtNumero.getText().toString());

                                                                    Costureira costureira = new Costureira(senhaHash, imagem, cpf, nome, email, telefone, data, cep, estado, cidade, rua, numero, 0);

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
                                                                binding.activityCadastroAdicionaImagem.requestFocus();
                                                                Toast.makeText(informacoesApp, "Insira uma foto.", Toast.LENGTH_SHORT).show();
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

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null && data.getData() != null){
                        Uri selectedImageUri = data.getData();
                        try {
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);
                            binding.activityCadastroAdicionaImagem.setImageBitmap(selectedImageBitmap);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
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