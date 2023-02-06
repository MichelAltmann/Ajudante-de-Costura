package com.example.ajudantedecostura.login;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CadastroActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ActivityCadastroBinding binding;

    Bitmap selectedImageBitmap;
    InformacoesApp informacoesApp;
    String msgRecebida;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    public static final String CAMERA = "android.permission.CAMERA";
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
            if(checkAndRequestPermissions(this)){

            }
            imageChooser();
        });

        informacoesApp = (InformacoesApp) getApplicationContext();
        EditText txtNome = binding.activityCadastroTxtNome;
        EditText txtSenha = binding.activityCadastroTxtSenha;
        EditText txtConfirmaSenha = binding.activityCadastroTxtConfirmarSenha;
        EditText txtTelefone = binding.activityCadastroTxtTelefone;
        EditText txtCpf = binding.activityCadastroTxtCpf;
        EditText txtEmail = binding.activityCadastroTxtEmail;
        EditText txtDataNascimento = binding.activityCadastroTxtDataNascimento;
        EditText txtCep = binding.activityCadastroTxtCep;
        EditText txtEstado = binding.activityCadastroTxtEstado;
        EditText txtCidade = binding.activityCadastroTxtCidade;
        EditText txtRua = binding.activityCadastroTxtRua;
        EditText txtNumero = binding.activityCadastroTxtNumero;
        ImageView addImagem = binding.activityCadastroAdicionaImagem;

        binding.activityCadastroTxtDataNascimento.setInputType(InputType.TYPE_NULL);

        binding.activityCadastroTxtDataNascimento.setOnClickListener(v -> {
            // date picker dialog
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(), "Date dialog");
        });

        binding.activityCadastroBtnCadastrar.setOnClickListener(v -> {
            if (!txtNome.getText().toString().equals("")){
                if (!txtSenha.getText().toString().equals("")) {
                    if (!txtConfirmaSenha.getText().toString().equals("")){
                        if (!txtTelefone.getText().toString().equals("")){
                            if (!txtCpf.getText().toString().equals("")){
                                if (!txtEmail.getText().toString().equals("")){
                                    if (!txtDataNascimento.getText().toString().equals("")){
                                        if (!txtCep.getText().toString().equals("")){
                                            if (!txtEstado.getText().toString().equals("")){
                                                if (!txtCidade.getText().toString().equals("")){
                                                    if (!txtRua.getText().toString().equals("")){
                                                        if (!txtNumero.getText().toString().equals("")) {
                                                            if (addImagem.getDrawable() != null){
                                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                                selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                                                byte[] imagem = stream.toByteArray();
                                                                if (txtSenha.getText().toString().equals(txtConfirmaSenha.getText().toString())){
                                                                    String nome, senha, senhaHash = null, telefone, cpf, email, estado, cidade, rua;
                                                                    nome = txtNome.getText().toString();
                                                                    senha = txtSenha.getText().toString();
                                                                    telefone = txtTelefone.getText().toString();
                                                                    cpf = txtCpf.getText().toString();
                                                                    email = txtEmail.getText().toString();

                                                                    Date data = null;
                                                                    try {
                                                                        // transformando senha em código hash
                                                                        //----------------------------------------
                                                                        MessageDigest md = MessageDigest.getInstance("MD5"); // MD5, SHA-1, SHA-256
                                                                        BigInteger senhaCadastrada = new BigInteger(1, md.digest(senha.getBytes()));
                                                                        senhaHash = String.valueOf(senhaCadastrada);
                                                                        //----------------------------------------
                                                                        data = dataFormatada.parse(txtDataNascimento.getText().toString());
                                                                        Log.i(TAG, "onCreate: " + data);
                                                                    } catch (ParseException | NoSuchAlgorithmException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                    Integer cep = Integer.parseInt(txtCep.getText().toString());
                                                                    estado = txtEstado.getText().toString();
                                                                    cidade = txtCidade.getText().toString();
                                                                    rua = txtRua.getText().toString();
                                                                    Integer numero = Integer.parseInt(txtNumero.getText().toString());

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
                                                                    txtConfirmaSenha.requestFocus();
                                                                    txtConfirmaSenha.setError("Erro: Senhas não coincidem");
                                                                }
                                                            } else {
                                                                addImagem.requestFocus();
                                                                Toast.makeText(informacoesApp, "Insira uma foto.", Toast.LENGTH_SHORT).show();
                                                            }

                                                        } else {
                                                            txtNumero.requestFocus();
                                                            txtNumero.setError("Erro: Campo obrigatório");
                                                        }
                                                    } else {
                                                        txtRua.requestFocus();
                                                        txtRua.setError("Erro: Campo obrigatório");
                                                    }
                                                } else {
                                                    txtCidade.requestFocus();
                                                    txtCidade.setError("Erro: Campo obrigatório");
                                                }
                                            } else {
                                                txtEstado.requestFocus();
                                                txtEstado.setError("Erro: Campo obrigatório");
                                            }
                                        } else {
                                            txtCep.requestFocus();
                                            txtCep.setError("Erro: Campo obrigatório");
                                        }
                                    } else {
                                        txtDataNascimento.requestFocus();
                                        txtDataNascimento.setError("Erro: Campo obrigatório");
                                    }
                                } else {
                                    txtEmail.requestFocus();
                                    txtEmail.setError("Erro: Campo obrigatório");
                                }
                            } else {
                                txtCpf.requestFocus();
                                txtCpf.setError("Erro: Campo obrigatório");
                            }
                        } else {
                            txtTelefone.requestFocus();
                            txtTelefone.setError("Erro: Campo obrigatório");
                        }
                    } else {
                        txtConfirmaSenha.requestFocus();
                        txtConfirmaSenha.setError("Erro: Campo obrigatório");
                    }
                } else {
                    txtSenha.requestFocus();
                    txtSenha.setError("Erro: Campo obrigatório");
                }
            } else {
                txtNome.requestFocus();
                txtNome.setError("Erro: Campo obrigatório");

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

    public static boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
                WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
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