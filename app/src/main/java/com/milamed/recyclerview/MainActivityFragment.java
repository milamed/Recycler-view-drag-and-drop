package com.milamed.recyclerview;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.milamed.recyclerview.helper.OnStartDragListener;
import com.milamed.recyclerview.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements ResultDialog.ResultDialogListener,OnStartDragListener {

    private static final String CONTENT_LIST_KEY = "content_list_key";
    private ArrayList<DataModel> mContentList;
    private MyAdapter myAdapter;
    private ItemTouchHelper mItemTouchHelper;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        if (savedInstanceState != null) {
            mContentList = (ArrayList<DataModel>) savedInstanceState.getSerializable(CONTENT_LIST_KEY);
        } else {
            mContentList = new ArrayList<>();
            initContent();
        }
        myAdapter = new MyAdapter(getActivity().getApplicationContext(), mContentList,this);
        recyclerView.setAdapter(myAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(myAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CONTENT_LIST_KEY, mContentList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add: {
                Log.v("test", "add menu item clicked");
                addItemPerform();
            }
                break;

            case R.id.action_remove:
            {
                removeItemPerform();
            }

        break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeItemPerform() {
        int pos=0;
        while(pos<mContentList.size())
        {
            if(mContentList.get(pos).isChecked())
            {
                mContentList.remove(pos);
                myAdapter.notifyItemRemoved(pos);
            }
            else {pos++;}
        }
    }
    private void initContent() {
        mContentList = new ArrayList<>();

        for (int i = 0; i < 2000; i++) {
            DataModel dataModel = new DataModel("user " + i, "Description " + i, R.drawable.user);
            mContentList.add(dataModel);
        }
    }
    private void addItemPerform() {
        ResultDialog resultDialog = ResultDialog.newInstance(this);
        resultDialog.show(getFragmentManager(), "");
    }

    @Override
    public void onSave(String title, String description) {
        DataModel dataModel = new DataModel(title, description, R.drawable.user);
        mContentList.add(0, dataModel);

        myAdapter.notifyItemInserted(0);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
