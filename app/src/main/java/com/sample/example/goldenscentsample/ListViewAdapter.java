package com.sample.example.goldenscentsample;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sweety on 12-Oct-18.
 */

public class ListViewAdapter extends BaseExpandableListAdapter implements AdapterView.OnItemClickListener {
    public static final int ItemHeight = 148;
    public static final int PaddingLeft = 45;
    private int myPaddingLeft = 0;
    private MyGridView toolbarGrid;
    private String menu_toolbar_name_array[] = {"Pencils", "Lipstick", "Lipgloss","Lip Balm","Treatment","Palette"};
    private int menu_toolbar_image_array[] = {R.drawable.product6,
            R.drawable.product5, R.drawable.product4,R.drawable.product9,R.drawable.product8,R.drawable.product7
    };

    private List<TreeNode> treeNodes = new ArrayList<TreeNode>();
    private Context parentContext;
    private LayoutInflater layoutInflater;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    static public class TreeNode {
        Object parent;
        List<Object> childs = new ArrayList<Object>();
    }

    public ListViewAdapter(Context view, int myPaddingLeft) {
        parentContext = view;
        this.myPaddingLeft = myPaddingLeft;
    }

    public List<TreeNode> GetTreeNode() {
        return treeNodes;
    }

    public void UpdateTreeNode(List<TreeNode> nodes) {
        treeNodes = nodes;
    }

    public void RemoveAll() {
        treeNodes.clear();
    }

    public Object getChild(int groupPosition, int childPosition) {
        return treeNodes.get(groupPosition).childs.get(childPosition);
    }

    public int getChildrenCount(int groupPosition) {
        return treeNodes.get(groupPosition).childs.size();
    }

    static public TextView getTextView(Context context) {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ItemHeight);
        TextView textView = new TextView(context);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        return textView;
    }


    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            layoutInflater = (LayoutInflater) parentContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.view, null);

            toolbarGrid = (MyGridView) convertView.findViewById(R.id.GridView_toolbar);
            toolbarGrid.setGravity(Gravity.CENTER);
            toolbarGrid.setHorizontalSpacing(5);
            toolbarGrid.setAdapter(getMenuAdapter(menu_toolbar_name_array, menu_toolbar_image_array));
            toolbarGrid.setOnItemClickListener(this);
            toolbarGrid.setExpanded(true);
            toolbarGrid.setFocusable(false);

        }
        return convertView;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        TextView textView = getTextView(this.parentContext);
        textView.setText(getGroup(groupPosition).toString());
        textView.setPadding(myPaddingLeft + PaddingLeft, 0, 0, 0);
        return textView;
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public Object getGroup(int groupPosition) {
        return treeNodes.get(groupPosition).parent;
    }

    public int getGroupCount() {
        return treeNodes.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }

    private SimpleAdapter getMenuAdapter(String[] menuNameArray,
                                         int[] imageResourceArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuNameArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", imageResourceArray[i]);
            map.put("itemText", menuNameArray[i]);
            data.add(map);
        }
        SimpleAdapter simperAdapter = new SimpleAdapter(parentContext, data,
                R.layout.item_menu, new String[]{"itemImage", "itemText"},
                new int[]{R.id.item_image, R.id.item_text});


        return simperAdapter;
    }



}
