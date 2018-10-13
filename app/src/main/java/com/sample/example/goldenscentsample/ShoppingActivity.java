package com.sample.example.goldenscentsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> itemNameList, itemNameDisList, itemDiscountList, itemPriceList;
    private HorizontalAdapter horizontalAdapter;

    ExpandableListView expandableListView;

    ListViewAdapter treeViewAdapter;
    public String[] groups = {"Lips", "Face", "Nails"};

    public String[][] child = {{""}, {""}, {""}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        horizontal_recycler_view = (RecyclerView) findViewById(R.id.hr_rv);
        itemNameList = new ArrayList<>();
        itemNameDisList = new ArrayList<>();
        itemDiscountList = new ArrayList<>();
        itemPriceList = new ArrayList<>();

        itemNameList.add("PRADA");
        itemNameList.add("DOLCE");
        itemNameList.add("AIGNER");

        itemNameDisList.add("Candy");
        itemNameDisList.add("The One");
        itemNameDisList.add("N. I");

        itemDiscountList.add("320 SR");
        itemDiscountList.add("310 SR");
        itemDiscountList.add("300 SR");

        itemPriceList.add("356 SR");
        itemPriceList.add("356 SR");
        itemPriceList.add("356 SR");
        horizontalAdapter = new HorizontalAdapter(itemNameList, itemNameDisList, itemDiscountList, itemPriceList);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(ShoppingActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontal_recycler_view.setAdapter(horizontalAdapter);
        treeViewAdapter = new ListViewAdapter(this,
                ListViewAdapter.PaddingLeft >> 1);
        expandableListView = (ExpandableListView) this
                .findViewById(R.id.expandableListView);
        List<ListViewAdapter.TreeNode> treeNode = treeViewAdapter.GetTreeNode();
        for (int i = 0; i < groups.length; i++) {
            ListViewAdapter.TreeNode node = new ListViewAdapter.TreeNode();
            node.parent = groups[i];
            for (int ii = 0; ii < child[i].length; ii++) {
                node.childs.add(child[i][ii]);
            }
            treeNode.add(node);
        }

        treeViewAdapter.UpdateTreeNode(treeNode);
        expandableListView.setAdapter(treeViewAdapter);
        /**
         * If you want to expand first item of List by default then uncomment below code
         */
//        expandableListView.expandGroup(0);
        expandableListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

}
