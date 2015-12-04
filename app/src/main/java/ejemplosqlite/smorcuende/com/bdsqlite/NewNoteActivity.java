package ejemplosqlite.smorcuende.com.bdsqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends Activity{

    private Button btnAdd;
    private EditText txtText;
    private NoteDataSource sourceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_new_note);

        sourceData = new NoteDataSource(this);
        sourceData.open();

        // My lable's elements
        txtText = (EditText) findViewById(R.id.txtText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String noteText = txtText.getText().toString();

                if (noteText.length() != 0) {
                    sourceData.addNote(noteText);
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Empty text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
