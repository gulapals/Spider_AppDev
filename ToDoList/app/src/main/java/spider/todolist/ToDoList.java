package spider.todolist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import static spider.todolist.R.id.listView;

public class ToDoList extends AppCompatActivity {

    private ListView List;
    private EditText Edit;
    private Button AddBtn;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        List = (ListView) findViewById(listView);
        Edit = (EditText) findViewById(R.id.item_Text);
        AddBtn = (Button) findViewById(R.id.add_but);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        List.setAdapter(adapter);

        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Your Task");
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.popup_layout, null);
        dialog.setContentView(v);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = Edit.getText().toString();
                adapter.add(item);
                adapter.notifyDataSetChanged();
                Edit.setText("");
            }
        });

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                /*LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_layout,null); //Separate*/
                String itemName = List.getItemAtPosition(position).toString();
                TextView textView = (TextView) dialog.findViewById(R.id.popup);
                String text = textView.getText().toString();
                textView.setText("Task Description:" + itemName);
                dialog.show();

                Button dialogButton = (Button) dialog.findViewById(R.id.close);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
//        List.setOnItemClickListener(
//                new AdapterView.OnItemClickListener()
//                {
//                    @Override
//                    public void onItemClick(AdapterView<?> arg0, View view,int position, long arg3) {
//                        String itemName = List.getItemAtPosition(position).toString();
//                        TextView textView = (TextView) view.findViewById(R.id.popup);
//                        String text = textView.getText().toString();
//                        Toast.makeText(getApplicationContext(), "Selected item: " + text + " - " + position, Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//        );
    }
}