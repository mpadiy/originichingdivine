package comp.android.e;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class dbSelect extends ListActivity {
    public Bundle bundle;
    private Button cancelbutton;
    private String dbPath;
    public File file;
    public Intent intent;
    private List<String> items = null;
    private ArrayList<String> itemsDir = new ArrayList();
    private ArrayList<String> itemsFile = new ArrayList();
    private TextView mPath;
    public EditText myDialogEditText;
    private List<String> paths = null;
    private ArrayList<String> pathsDir = new ArrayList();
    private ArrayList<String> pathsFile = new ArrayList();
    private int rOrw_file_flag = 0;
    private String rootPath = File.separator;
    public String saveFileName;
    private Button savebutton;

    public class Column0Comparator implements Comparator<String[]> {
        public int compare(String[] array, String[] anotherArray) {
            return array[0].compareTo(anotherArray[0]);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filebrowser);
        this.mPath = (TextView) findViewById(R.id.mPath);
        getFileDir(this.rootPath);
        this.intent = getIntent();
        this.bundle = this.intent.getExtras();
        this.dbPath = this.bundle.getString("dbPath");
        this.cancelbutton = (Button) findViewById(R.id.cancel);
        this.cancelbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                System.exit(0);
            }
        });
        this.savebutton = (Button) findViewById(R.id.savefilebutton);
        this.savebutton.setText("確定所選檔案為：");
        this.savebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (dbSelect.this.rOrw_file_flag == 1) {
                    dbSelect.this.dbPath = new StringBuilder(String.valueOf(dbSelect.this.file.getParentFile().getPath())).append(File.separator).append(dbSelect.this.file.getName()).toString();
                    dbSelect.this.bundle.putString("dbPath", dbSelect.this.dbPath);
                    dbSelect.this.intent.putExtras(dbSelect.this.bundle);
                    dbSelect.this.setResult(-1, dbSelect.this.intent);
                    dbSelect.this.finish();
                    return;
                }
                Toast.makeText(dbSelect.this, dbSelect.this.getResources().getString(R.string.err_file), 1).show();
            }
        });
    }

    private void getFileDir(String filePath) {
        int i;
        this.mPath.setText(filePath);
        this.items = new ArrayList();
        this.paths = new ArrayList();
        this.itemsDir.clear();
        this.itemsFile.clear();
        this.pathsDir.clear();
        this.pathsFile.clear();
        File f = new File(filePath);
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                this.itemsDir.add(file.getName());
                this.pathsDir.add(file.getPath());
            } else {
                this.itemsFile.add(file.getName());
                this.pathsFile.add(file.getPath());
            }
        }
        ArrayList<String[]> arrayListDirForSort = new ArrayList();
        for (i = 0; i < this.itemsDir.size(); i++) {
            arrayListDirForSort.add(new String[]{(String) this.itemsDir.get(i), (String) this.pathsDir.get(i)});
        }
        Collections.sort(arrayListDirForSort, new Column0Comparator());
        ArrayList<String[]> arrayListFileForSort = new ArrayList();
        for (i = 0; i < this.itemsFile.size(); i++) {
            arrayListFileForSort.add(new String[]{(String) this.itemsFile.get(i), (String) this.pathsFile.get(i)});
        }
        Collections.sort(arrayListFileForSort, new Column0Comparator());
        if (!filePath.equals(this.rootPath)) {
            this.items.add("b1");
            this.paths.add(this.rootPath);
            this.items.add("b2");
            this.paths.add(f.getParent());
        }
        for (i = 0; i < this.itemsDir.size(); i++) {
            this.items.add(((String[]) arrayListDirForSort.get(i))[0]);
            this.paths.add(((String[]) arrayListDirForSort.get(i))[1]);
        }
        for (i = 0; i < this.itemsFile.size(); i++) {
            this.items.add(((String[]) arrayListFileForSort.get(i))[0]);
            this.paths.add(((String[]) arrayListFileForSort.get(i))[1]);
        }
        setListAdapter(new MyAdapter(this, this.items, this.paths));
    }

    public void onBackPressed() {
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        v.setBackgroundColor(getTitleColor());
        this.file = new File((String) this.paths.get(position));
        if (this.file.isDirectory()) {
            if (this.file.canRead()) {
                try {
                    getFileDir((String) this.paths.get(position));
                } catch (Exception e) {
                }
            } else if (!this.file.canRead()) {
                Toast.makeText(this, getResources().getString(R.string.err_folder), 1).show();
            }
        } else if (!this.file.isDirectory()) {
            if (this.file.canRead()) {
                try {
                    String fName = this.file.getName();
                    String[] end = fName.split("\\.");
                    this.savebutton.setText(end[0]);
                    if (end[1].equals("Dro易d")) {
                        this.dbPath = new StringBuilder(String.valueOf(this.file.getParentFile().getPath())).append(File.separator).append(fName).toString();
                        this.savebutton.setText("確定所選檔案為：\n" + this.dbPath);
                        this.rOrw_file_flag = 1;
                        return;
                    }
                    Toast.makeText(this, getResources().getString(R.string.err_file), 1).show();
                    this.rOrw_file_flag = 0;
                } catch (Exception e2) {
                }
            } else if (!this.file.canRead()) {
                Toast.makeText(this, getResources().getString(R.string.err_file), 1).show();
            }
        }
    }
}
