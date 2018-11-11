package comp.android.e;

import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class filebrowser extends ListActivity {
    private Button cancelbutton;
    public String dbPath;
    public String defaultName = "請輸入檔名";
    public File file;
    public FileInputStream inFile;
    private List<String> items = null;
    private ArrayList<String> itemsDir = new ArrayList();
    private ArrayList<String> itemsFile = new ArrayList();
    private TextView mPath;
    public EditText myDialogEditText;
    public FileOutputStream outFile;
    private List<String> paths = null;
    private ArrayList<String> pathsDir = new ArrayList();
    private ArrayList<String> pathsFile = new ArrayList();
    private String rootPath = "/";
    public String saveFileName;

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
        this.file = new File(this.rootPath);
        this.dbPath = getIntent().getExtras().getString("dbPath");
        this.cancelbutton = (Button) findViewById(R.id.cancel);
        this.cancelbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                filebrowser.this.finish();
            }
        });
        ((Button) findViewById(R.id.savefilebutton)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                File tmpFile = new File(filebrowser.this.dbPath);
                if (filebrowser.this.file.canWrite()) {
                    filebrowser.this.fileHandle(tmpFile);
                } else {
                    Toast.makeText(filebrowser.this, "您沒有權限存取此檔案哦！", 0).show();
                }
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

    protected void onListItemClick(ListView l, View v, int position, long id) {
        this.file = new File((String) this.paths.get(position));
        if (this.file.isDirectory() && this.file.canRead()) {
            getFileDir((String) this.paths.get(position));
            return;
        }
        this.defaultName = this.file.getName().split(".Dro")[0];
        this.file = new File(this.file.getParent());
        Toast.makeText(this, "您沒有權限存取此檔案哦！", 0).show();
    }

    private void fileHandle(File tmpFile) {
        try {
            this.inFile = new FileInputStream(tmpFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        View textEntryView = getLayoutInflater().inflate(R.layout.save_dialog, null);
        ((TextView) textEntryView.findViewById(R.id.sTextViewTitle)).setText(this.file.getPath());
        Builder rbuilder = new Builder(this);
        rbuilder.setView(textEntryView);
        this.myDialogEditText = (EditText) textEntryView.findViewById(R.id.myDialogEditText);
        this.myDialogEditText.setText(this.defaultName);
        rbuilder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                filebrowser.this.saveFileName = new StringBuilder(String.valueOf(filebrowser.this.file.getPath())).append(File.separator).append(filebrowser.this.myDialogEditText.getText().toString()).append(".Dro易d").toString();
                if (new File(filebrowser.this.saveFileName).exists()) {
                    new File(filebrowser.this.saveFileName).delete();
                    try {
                        filebrowser.this.outFile = new FileOutputStream(filebrowser.this.saveFileName);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    new Builder(filebrowser.this).setTitle("注意").setMessage("此檔名已存在！是否覆蓋？").setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            byte[] buf = new byte[1024];
                            while (true) {
                                try {
                                    int i = filebrowser.this.inFile.read(buf);
                                    if (i == -1) {
                                        break;
                                    }
                                    filebrowser.this.outFile.write(buf, 0, i);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            filebrowser.this.getFileDir(filebrowser.this.file.getPath());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
                } else {
                    try {
                        filebrowser.this.outFile = new FileOutputStream(filebrowser.this.saveFileName);
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    byte[] buf = new byte[1024];
                    while (true) {
                        i = filebrowser.this.inFile.read(buf);
                        if (i == -1) {
                            break;
                        }
                        try {
                            filebrowser.this.outFile.write(buf, 0, i);
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    filebrowser.this.getFileDir(filebrowser.this.file.getPath());
                }
                try {
                    filebrowser.this.inFile.close();
                    filebrowser.this.outFile.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
        }).create().show();
    }
}
