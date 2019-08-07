package com.example.viewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewpage2;
    Adapter adapter;
    SmartRefreshLayout smartRefreshLayout;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpage2 = findViewById(R.id.page2);
        smartRefreshLayout = findViewById(R.id.refresh);
        smartRefreshLayout.setEnableLoadMore(true);
        adapter = new Adapter();
        viewpage2.setAdapter(adapter);

        for (int i = 0; i < 3; i++) {
            list.add(i + "");
        }
        adapter.setList(list);
        viewpage2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == list.size() - 1) {
                    Toast.makeText(getApplicationContext(), position + "触发loadmore", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    public void addhead(View view) {
        adapter.addHead("我是头");
    }

    public void addfoot(View view) {
        adapter.addFoot("我是尾巴");
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        List<String> list = new ArrayList<>();


        public void setList(List<String> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        public void addHead(String s) {
            list.add(0, s);
            notifyDataSetChanged();
        }

        void addFoot(String s) {
            list.add(s);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bindData(list.get(position) + "");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.num);
        }

        void bindData(String s) {
            textView.setText(s);
        }

    }


}
