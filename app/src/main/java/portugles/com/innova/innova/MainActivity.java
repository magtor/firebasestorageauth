package portugles.com.innova.innova;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    private Button btnUpload;
    private StorageReference mstoragerefer;
    //private FirebaseAuth mAuth;
    private static final int Gallery_Intent = 1;
    private ImageView imageViewFire;
    private ProgressDialog mprogressdialog;
    private String sfilePath1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
       // mAuth = FirebaseAuth.getInstance();
        //setSupportActionBar(toolbar);

        btnUpload = (Button) findViewById(R.id.butonUpload);
        imageViewFire = (ImageView) findViewById(R.id.imageViewFirebase) ;

        mprogressdialog = new ProgressDialog(this);
        mstoragerefer = FirebaseStorage.getInstance().getReference();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, sfilePath1, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data){
        super.onActivityResult(requestcode,resultcode,data);
        if((requestcode== Gallery_Intent) && (resultcode==RESULT_OK)){
            mprogressdialog.setTitle("Upload Image");
            mprogressdialog.setMessage("Uploading Image TO FireBase");
            mprogressdialog.setCancelable(false);
            mprogressdialog.show();
            Uri uri = data.getData();
            StorageReference filepath = mstoragerefer.child("fotos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mprogressdialog.dismiss();
                    Uri filePath = taskSnapshot.getDownloadUrl();
                    sfilePath1 = filePath.toString();
                    Glide.with(MainActivity.this)
                            .load(filePath)
                            .fitCenter()
                            .centerCrop()
                            .into(imageViewFire);
                  //  Toast.makeText(MainActivity.this,"Uploading Image",Toast.LENGTH_SHORT).show();
                }
            })
             .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mprogressdialog.dismiss();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }



}
